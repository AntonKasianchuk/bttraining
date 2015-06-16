package com.bttraining.service.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.CustomerService;

public class CustomerServiceImp implements CustomerService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	@Override
	public ResourceCollection<Customer> getAllCustomers() {
		CustomerSearchRequest request = new CustomerSearchRequest().id().is(
				"");
		ResourceCollection<Customer> collection = gateway.customer().search(
				request);
		return collection;
	}

}
