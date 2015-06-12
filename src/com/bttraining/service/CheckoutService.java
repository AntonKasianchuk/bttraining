package com.bttraining.service;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

public interface CheckoutService {

	Result<Transaction> doTransactionByMethodNonceAndAmount(String methodNonce,
			String amountString);
}
