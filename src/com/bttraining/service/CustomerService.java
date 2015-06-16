package com.bttraining.service;

import com.braintreegateway.Customer;
import com.braintreegateway.ResourceCollection;

public interface CustomerService {

	ResourceCollection<Customer> getAllCustomers();
}
