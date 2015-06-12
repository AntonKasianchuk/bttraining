package com.bttraining.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.Configuration;
import com.bttraining.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	private BraintreeGateway gateway = Configuration.getBraintreeGateway();

	@Override
	public Result<Customer> getCustomerResultByCustomerRequest(
			CustomerRequest customerRequest) {
		Result<Customer> result = gateway.customer().create(customerRequest);
		return result;
	}

	@Override
	public CustomerRequest generateCustomerRequest(HttpServletRequest request) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.firstName(request.getParameter("first_name"));
		customerRequest.lastName(request.getParameter("last_name"));
		customerRequest.company(request.getParameter("company"));
		customerRequest.email(request.getParameter("email"));
		customerRequest.phone(request.getParameter("phone"));
		customerRequest.fax(request.getParameter("fax"));
		customerRequest.website(request.getParameter("website"));
		return customerRequest;
	}

	@Override
	public String getClientTokenByCustomerId(String customerId) {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
		.customerId(customerId);
		String clientToken = gateway.clientToken().generate(clientTokenRequest);
		return clientToken;
	}
	
}
