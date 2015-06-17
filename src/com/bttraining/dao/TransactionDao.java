package com.bttraining.dao;

import com.braintreegateway.Transaction;

public interface TransactionDao {
	Transaction getTransactionById(String transactionId);
}
