package com.bttraining.dao;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;

public interface CustomerDao {
	Result<Customer> createCustomer(
			CustomerRequest customerRequest);
	
	Result<Customer> updateCustomer(String customerId,
			CustomerRequest customerRequest);

	ResourceCollection<Customer> getAllCustomers();
	
	ResourceCollection<Customer> getCustomerById(String cutomerId);
}
