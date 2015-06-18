package com.bttraining.service;

import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.web.dto.CustomerDTO;

public interface CustomerService {
	Result<Customer> createCustomer(CustomerRequest customerRequest);
	
	Result<Customer> updateCustomer(String customerId,
			CustomerRequest customerRequest);
	
	ResourceCollection<Customer> getAllCustomers();

	ResourceCollection<Customer> getCustomerById(String cutomerId);

	Set<String> getCustomerIds();

}
