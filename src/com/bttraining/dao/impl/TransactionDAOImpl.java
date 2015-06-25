package com.bttraining.dao.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Transaction;
import com.bttraining.configuration.Configurator;
import com.bttraining.dao.TransactionDAO;

public class TransactionDAOImpl implements TransactionDAO {
	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	@Override
	public Transaction getTransactionById(String transactionId) {
		Transaction transaction = gateway.transaction().find(transactionId);
		return transaction;
	}

}
