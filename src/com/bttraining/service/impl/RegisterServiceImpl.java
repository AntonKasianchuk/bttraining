package com.bttraining.service.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.RegisterService;
import com.bttraining.web.dto.CustomerDTO;

public class RegisterServiceImpl implements RegisterService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	@Override
	public Result<Customer> getCustomerResultByCustomerRequest(
			CustomerRequest customerRequest) {
		Result<Customer> result = gateway.customer().create(customerRequest);
		return result;
	}

	@Override
	public CustomerRequest generateCustomerRequest(CustomerDTO customerDTO) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.firstName(customerDTO.getFirstName());
		customerRequest.lastName(customerDTO.getLastName());
		customerRequest.company(customerDTO.getCompany());
		customerRequest.email(customerDTO.getEmail());
		customerRequest.phone(customerDTO.getPhone());
		customerRequest.fax(customerDTO.getFax());
		customerRequest.website(customerDTO.getWebsite());
		return customerRequest;
	}

	private String getCustomerIdByResult(Result<Customer> result) {
		String customerId = result.getTarget().getId();
		return customerId;
	}

	@Override
	public String getClientTokenByResult(Result<Customer> result) {
		String customerId = getCustomerIdByResult(result);
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
				.customerId(customerId);
		String clientToken = gateway.clientToken().generate(clientTokenRequest);
		return clientToken;
	}
}
