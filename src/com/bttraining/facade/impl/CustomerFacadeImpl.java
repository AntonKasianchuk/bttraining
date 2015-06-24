package com.bttraining.facade.impl;

import java.util.Map;
import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.facade.CustomerFacade;
import com.bttraining.facade.converter.CustomerConverter;
import com.bttraining.service.CustomerService;
import com.bttraining.service.RegisterService;
import com.bttraining.service.impl.CustomerServiceImpl;
import com.bttraining.service.impl.RegisterServiceImpl;
import com.bttraining.web.dto.CustomerInfoDTO;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

public class CustomerFacadeImpl implements CustomerFacade {
	private CustomerService customerService = new CustomerServiceImpl();
	private RegisterService registerService = new RegisterServiceImpl();
	private CustomerConverter customerConverter = new CustomerConverter();

	@Override
	public Set<String> getCustomerIds() {
		Set<String> customerIdSet = customerService.getCustomerIds();
		return customerIdSet;
	}

	@Override
	public CustomerInfoDTO getCustomerDTOById(String customerId) {
		ResourceCollection<Customer> customerCollection = customerService
				.getCustomerById(customerId);
		Customer customer = customerCollection.getFirst();
		CustomerInfoDTO customerDTO = customerConverter
				.generateCustomerDTO(customer);
		return customerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(String customerId,
			Map<String, String[]> customerParameterMap) {
		CustomerDTO customerDTO = new CustomerDTO();

		CustomerInfoDTO customerInfoDTO = generateCustomerDTO(customerParameterMap);
		CustomerRequest customerRequest = generateCustomerRequest(customerInfoDTO);

		Result<Customer> customerResult = customerService.updateCustomer(
				customerId, customerRequest);
		customerDTO.setCustomerInfoDTO(customerInfoDTO);
		customerDTO.setSuccess(customerResult.isSuccess());
		return customerDTO;
	}

	@Override
	public CustomerRegisterDTO createCustomer(
			Map<String, String[]> customerParameterMap) {
		CustomerRegisterDTO customerRegisterDTO = new CustomerRegisterDTO();
		CustomerInfoDTO customerInfoDTO = generateCustomerDTO(customerParameterMap);
		CustomerRequest customerRequest = generateCustomerRequest(customerInfoDTO);
		Result<Customer> customerResult = customerService
				.createCustomer(customerRequest);
		boolean isSuccess = customerResult.isSuccess();
		if (isSuccess) {
			Customer customer = customerResult.getTarget();
			String clientToken = registerService
					.getClientTokenByCustomer(customer.getId());
			customerRegisterDTO.setClientToken(clientToken);
		}
		customerRegisterDTO.setSuccess(isSuccess);
		return customerRegisterDTO;
	}

	private CustomerInfoDTO generateCustomerDTO(
			Map<String, String[]> customerParameterMap) {
		CustomerInfoDTO customerDTO = customerConverter
				.generateCustomerDTO(customerParameterMap);
		return customerDTO;
	}

	private CustomerRequest generateCustomerRequest(CustomerInfoDTO customerInfoDTO) {
		CustomerRequest customerRequest = customerConverter
				.generateCustomerRequest(customerInfoDTO);
		return customerRequest;
	}

	@Override
	public String getClientTokenByCustomerId(String customerId) {
		ResourceCollection<Customer> customerCollection = customerService
				.getCustomerById(customerId);
		Customer customer = customerCollection.getFirst();
		String clientToken = registerService.getClientTokenByCustomer(customer
				.getId());
		return clientToken;
	}
}
