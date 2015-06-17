package com.bttraining.service;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;

public interface RegisterService {

	Result<Customer> getCustomerResultByCustomerRequest(
			CustomerRequest customerRequest);

	String getClientTokenByResult(Result<Customer> result);

}
