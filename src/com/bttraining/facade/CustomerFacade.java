package com.bttraining.facade;

import java.util.Map;
import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerEditDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

public interface CustomerFacade {
	Set<String> getCustomerIds();

	CustomerDTO getCustomerDTOById(String customerId);

	CustomerEditDTO updateCustomer(String customerId, Map<String, String[]> customerParameterMap);

	CustomerRegisterDTO createCustomer(Map<String, String[]> customerParameterMap);
}
