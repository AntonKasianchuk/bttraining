package com.bttraining.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.CustomerService;
import com.bttraining.util.converter.CustomerConverter;
import com.bttraining.web.dto.CustomerDTO;

public class CustomerServiceImp implements CustomerService {
	private BraintreeGateway gateway = Configurator.getBraintreeGateway();
	private CustomerConverter customerConverter = new CustomerConverter();
	
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

	@Override
	public ResourceCollection<Customer> getCustomerById(String customerId) {
		CustomerSearchRequest request = new CustomerSearchRequest().id().is(customerId);
		ResourceCollection<Customer> collection = gateway.customer().search(
				request);
		return collection;
	}

	@Override
	public CustomerDTO getCustomerDTOById(String customerId) {
		ResourceCollection<Customer> customerCollection = getCustomerById(customerId);
		Customer customer = customerCollection.getFirst();
		CustomerDTO customerDTO = customerConverter.generateCustomerDTO(customer);
		return customerDTO;
	}
	
	@Override
	public Result<Customer> updateCustomer(String customerId, CustomerRequest customerRequest) {
		Result<Customer> result = gateway.customer().update(customerId, customerRequest);
		return result;
	}
}
