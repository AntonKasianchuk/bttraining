package com.bttraining.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.TransactionSearchRequest;
import com.bttraining.configuration.Configurator;
import com.bttraining.service.TransactionService;
import com.bttraining.util.converter.TransactionConverter;
import com.bttraining.web.dto.TransactionDTO;

public class TransactionServiceImpl implements TransactionService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();
	private TransactionConverter transactionConverter = new TransactionConverter();

	@Override
	public Result<Transaction> doTransactionByMethodNonceAndAmount(
			String methodNonce, String amountString) {
		TransactionRequest txRequest = new TransactionRequest().amount(
				new BigDecimal(amountString)).paymentMethodNonce(methodNonce);
		Result<Transaction> result = gateway.transaction().sale(txRequest);
		return result;
	}

	@Override
	public Result<Transaction> submitTransactionForSettlement(String transactionId) {
		Result<Transaction> result = gateway.transaction().submitForSettlement(transactionId);
		return result;
	}

	@Override
	public Result<Transaction> voidTransaction(String transactionId) {
		Result<Transaction> result = gateway.transaction().voidTransaction(transactionId);
		return result;
	}

	@Override
	public Set<Transaction> getTransactionsByCustomerId(
			String customerId) {
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest()
				.customerId().is(customerId);
		ResourceCollection<Transaction> resourceTransactions = gateway.transaction()
				.search(transactionSearchRequest);
		Set<Transaction> result = new HashSet<Transaction>();
		for (Transaction resourceTransaction : resourceTransactions) {
			String transactionId = resourceTransaction.getId();
			Transaction transaction = gateway.transaction().find(transactionId);
			result.add(transaction);
		}
		return result;
	}

	@Override
	public Set<TransactionDTO> getTransactionDTOsByCustomerId(String customerId) {
		Set<Transaction> transactions = getTransactionsByCustomerId(customerId);
		Set<TransactionDTO> result = new HashSet<TransactionDTO>();
		for (Transaction transaction : transactions) {
			TransactionDTO transactionDTO = transactionConverter.generateTransactionDTO(transaction);
			result.add(transactionDTO);
		}
		return result;
	}
	
}
