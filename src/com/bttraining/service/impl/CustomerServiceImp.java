package com.bttraining.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
		ResourceCollection<Customer> customers = gateway.customer().search(
				request);
		return customers;
	}

	@Override
	public Set<String> getCustomerIds() {
		ResourceCollection<Customer> customers = getAllCustomers();
		Set<String> customerIds = new HashSet<String>();
		for (Customer customer : customers) {
			customerIds.add(customer.getId());
		}
		return customerIds;
	}

}
