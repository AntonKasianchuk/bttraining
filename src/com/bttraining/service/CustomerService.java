package com.bttraining.service;

import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.ResourceCollection;

public interface CustomerService {

	ResourceCollection<Customer> getAllCustomers();

	Set<String> getCustomerIds();
}
