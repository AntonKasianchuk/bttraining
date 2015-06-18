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
import com.bttraining.service.impl.CustomerServiceImp;
import com.bttraining.service.impl.RegisterServiceImpl;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerEditDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

public class CustomerFacadeImpl implements CustomerFacade {
	private CustomerService customerService = new CustomerServiceImp();
	private RegisterService registerService = new RegisterServiceImpl();
 	private CustomerConverter customerConverter = new CustomerConverter();
	
	@Override
	public Set<String> getCustomerIds() {
		Set<String> customerIdSet = customerService.getCustomerIds();
		return customerIdSet;
	}
	
	@Override
	public CustomerDTO getCustomerDTOById(String customerId) {
		ResourceCollection<Customer> customerCollection = customerService.getCustomerById(customerId);
		Customer customer = customerCollection.getFirst();
		CustomerDTO customerDTO = customerConverter.generateCustomerDTO(customer);
		return customerDTO;
	}

	@Override
	public CustomerEditDTO updateCustomer(String customerId, Map<String, String[]> customerParameterMap) {
		CustomerEditDTO customerEditDTO = new CustomerEditDTO();
		
		CustomerDTO customerDTO = generateCustomerDTO(customerParameterMap);
		CustomerRequest customerRequest = generateCustomerRequest(customerDTO);
		
		Result<Customer> customerResult =  customerService.updateCustomer(customerId, customerRequest);
		customerEditDTO.setCustomerDTO(customerDTO);
		customerEditDTO.setEdited(customerResult.isSuccess());
		return customerEditDTO;
	}

	@Override
	public CustomerRegisterDTO createCustomer(Map<String, String[]> customerParameterMap) {
		CustomerRegisterDTO customerRegisterDTO = new CustomerRegisterDTO();
		CustomerDTO customerDTO = generateCustomerDTO(customerParameterMap);
		CustomerRequest customerRequest = generateCustomerRequest(customerDTO);
		Result<Customer> customerResult = customerService.createCustomer(customerRequest);
		boolean isSuccess = customerResult.isSuccess();
		if (isSuccess) {
			Customer customer = customerResult.getTarget();
			String clientToken = registerService.getClientTokenByCustomer(customer);
			customerRegisterDTO.setClientToken(clientToken);
		}
		customerRegisterDTO.setRegistered(isSuccess);
		return customerRegisterDTO;
	}
	
	private CustomerDTO generateCustomerDTO(Map<String, String[]> customerParameterMap) {
		CustomerDTO customerDTO = customerConverter
				.generateCustomerDTO(customerParameterMap);
		return customerDTO;
	}
	
	private CustomerRequest generateCustomerRequest(CustomerDTO customerDTO) {
		CustomerRequest customerRequest = customerConverter
				.generateCustomerRequest(customerDTO);
		return customerRequest;
	}
}
