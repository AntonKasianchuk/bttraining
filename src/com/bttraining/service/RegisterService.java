package com.bttraining.service;

import com.braintreegateway.Customer;
import com.braintreegateway.Result;

public interface RegisterService {
	String getClientTokenByResult(Result<Customer> result);
}
