package com.bttraining.service;

import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

public interface PaymentService {

	Result<Transaction> doTransactionByMethodNonceAndAmount(String methodNonce,
			String amountString);
	
	Result<Transaction> voidTransaction(String transactionId);

	Result<Transaction> submitTransactionForSettlement(String transactionId);

	ResourceCollection<Transaction> getTransactionsByCustomerId(
			String customerId);
}
