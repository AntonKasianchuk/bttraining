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
import com.bttraining.dao.TransactionDao;
import com.bttraining.dao.impl.TransactionDaoImpl;
import com.bttraining.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();
	private TransactionDao transactionDao = new TransactionDaoImpl();

	@Override
	public Result<Transaction> doTransactionByMethodNonceAndAmount(
			String methodNonce, String amountString) {
		TransactionRequest txRequest = new TransactionRequest().amount(
				new BigDecimal(amountString)).paymentMethodNonce(methodNonce);
		Result<Transaction> result = gateway.transaction().sale(txRequest);
		return result;
	}

	@Override
	public Result<Transaction> submitTransactionForSettlement(
			String transactionId) {
		Result<Transaction> result = gateway.transaction().submitForSettlement(
				transactionId);
		return result;
	}

	@Override
	public Result<Transaction> voidTransaction(String transactionId) {
		Result<Transaction> result = gateway.transaction().voidTransaction(
				transactionId);
		return result;
	}

	@Override
	public Result<Transaction> refundTransaction(String transactionId) {
		Result<Transaction> result = gateway.transaction().refund(
				transactionId);
		return result;
	}
	
	@Override
	public Set<Transaction> getTransactionsByCustomerId(String customerId) {
		ResourceCollection<Transaction> resourceTransactions = getResourceTransactionsByCustomerId(customerId);
		Set<Transaction> transactionSet = new HashSet<Transaction>();
		for (Transaction resourceTransaction : resourceTransactions) {
			String transactionId = resourceTransaction.getId();
			Transaction transaction = transactionDao
					.getTransactionById(transactionId);
			transactionSet.add(transaction);
		}
		return transactionSet;
	}
	
	private ResourceCollection<Transaction> getResourceTransactionsByCustomerId(String customerId) {
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest()
		.customerId().is(customerId);
		ResourceCollection<Transaction> resourceTransactions = gateway
				.transaction().search(transactionSearchRequest);
		return resourceTransactions;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	public void setGateway(BraintreeGateway gateway ) {
		this.gateway = gateway;
	}
}
