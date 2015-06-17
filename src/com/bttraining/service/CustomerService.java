package com.bttraining.service;

import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.web.dto.CustomerDTO;

public interface CustomerService {
	ResourceCollection<Customer> getAllCustomers();

	Set<String> getCustomerIds();

	ResourceCollection<Customer> getCustomerById(String cutomerId);
	
	CustomerDTO getCustomerDTOById(String cutomerId);

	Result<Customer> updateCustomer(String customerId,
			CustomerRequest customerRequest);

}
