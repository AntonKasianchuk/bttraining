package com.bttraining.service;

import javax.servlet.http.HttpServletRequest;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;

public interface PaymentService {

	Result<Customer> getCustomerResultByCustomerRequest(
			CustomerRequest customerRequest);
	
	CustomerRequest generateCustomerRequest(HttpServletRequest request);

	String getClientTokenByCustomerId(String customerId);
}
