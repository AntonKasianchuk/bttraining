package com.bttraining.service;

import com.braintreegateway.Customer;

public interface RegisterService {
	String getClientTokenByCustomer(Customer result);
}
