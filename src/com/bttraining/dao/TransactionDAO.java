package com.bttraining.dao;

import com.braintreegateway.Transaction;

public interface TransactionDAO {
	Transaction getTransactionById(String transactionId);
}
