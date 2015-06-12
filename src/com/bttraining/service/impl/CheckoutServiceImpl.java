package com.bttraining.service.impl;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.bttraining.Configuration;
import com.bttraining.service.CheckoutService;

public class CheckoutServiceImpl implements CheckoutService {

	private BraintreeGateway gateway = Configuration.getBraintreeGateway();

	@Override
	public Result<Transaction> doTransactionByMethodNonceAndAmount(
			String methodNonce, String amountString) {
		TransactionRequest txRequest = new TransactionRequest().amount(
				new BigDecimal(amountString)).paymentMethodNonce(methodNonce);
		Result<Transaction> result = gateway.transaction().sale(txRequest);
		return result;
	}

}
