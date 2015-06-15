package com.bttraining.service;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.web.dto.CustomerDTO;

public interface RegisterService {

	Result<Customer> getCustomerResultByCustomerRequest(
			CustomerRequest customerRequest);

	CustomerRequest generateCustomerRequest(CustomerDTO customerDTO);

	String getClientTokenByResult(Result<Customer> result);
}
