package com.bttraining.service.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.Result;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();
	
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
