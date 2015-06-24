package com.bttraining.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionGateway;
import com.bttraining.dao.impl.TransactionDaoImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDAOTest {

	private static final String TRANSACTION_ID = "TEST_TRANSACTION_ID";

	@Mock
	private BraintreeGateway braintreeGateway;

	@InjectMocks
	private TransactionDao transactionDao = new TransactionDaoImpl();

	@Test
	public void shouldGetTransactionById() {
		// given
		Transaction expectedTransaction = mock(Transaction.class);
		TransactionGateway transactionGateway = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transactionGateway);
		when(transactionGateway.find(TRANSACTION_ID)).thenReturn(
				expectedTransaction);

		// when
		Transaction actualTransaction = transactionDao
				.getTransactionById(TRANSACTION_ID);

		// then
		assertEquals(expectedTransaction, actualTransaction);
	}
}
