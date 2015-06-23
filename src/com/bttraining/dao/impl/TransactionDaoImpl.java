package com.bttraining.dao.impl;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Transaction;
import com.bttraining.configuration.Configurator;
import com.bttraining.dao.TransactionDao;

public class TransactionDaoImpl implements TransactionDao {
	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	@Override
	public Transaction getTransactionById(String transactionId) {
		Transaction transaction = gateway.transaction().find(transactionId);
		return transaction;
	}
	
	public void setGateway(BraintreeGateway gateway) {
		this.gateway = gateway;
	}
}
