package com.bttraining.service;

import java.util.Set;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.bttraining.web.dto.TransactionDTO;

public interface TransactionService {

	Result<Transaction> doTransactionByMethodNonceAndAmount(String methodNonce,
			String amountString);
	
	Result<Transaction> voidTransaction(String transactionId);

	Result<Transaction> submitTransactionForSettlement(String transactionId);

	Set<Transaction> getTransactionsByCustomerId(
			String customerId);
	
	Set<TransactionDTO> getTransactionDTOsByCustomerId(
			String customerId);
}