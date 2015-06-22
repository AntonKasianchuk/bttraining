package com.bttraining.service.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	@Override
	public String getClientTokenByCustomer(String customerId) {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
				.customerId(customerId);
		String clientToken = gateway.clientToken().generate(clientTokenRequest);
		return clientToken;
	}
}
