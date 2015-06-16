package com.bttraining.service;

import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.ResourceCollection;
import com.bttraining.web.dto.CustomerDTO;

public interface CustomerService {
	ResourceCollection<Customer> getAllCustomers();

	Set<String> getCustomerIds();

	ResourceCollection<Customer> getCustomerById(String cutomerId);
	
	CustomerDTO getCustomerDTOById(String cutomerId);

}
