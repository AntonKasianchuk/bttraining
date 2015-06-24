package com.bttraining.dao.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.configuration.Configurator;
import com.bttraining.dao.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {
	private BraintreeGateway gateway = Configurator.getBraintreeGateway();
	private static final String EMPTY_CUSTOMER_ID = "";

	@Override
	public Result<Customer> createCustomer(CustomerRequest customerRequest) {
		Result<Customer> result = gateway.customer().create(customerRequest);
		return result;
	}

	@Override
	public Result<Customer> updateCustomer(String customerId,
			CustomerRequest customerRequest) {
		Result<Customer> result = gateway.customer().update(customerId,
				customerRequest);
		return result;
	}

	@Override
	public ResourceCollection<Customer> getAllCustomers() {
		CustomerSearchRequest request = new CustomerSearchRequest().id().is(EMPTY_CUSTOMER_ID);
		ResourceCollection<Customer> customers = gateway.customer().search(
				request);
		return customers;
	}

	@Override
	public ResourceCollection<Customer> getCustomerById(String customerId) {
		CustomerSearchRequest request = new CustomerSearchRequest().id().is(
				customerId);
		ResourceCollection<Customer> collection = gateway.customer().search(
				request);
		return collection;
	}
}
