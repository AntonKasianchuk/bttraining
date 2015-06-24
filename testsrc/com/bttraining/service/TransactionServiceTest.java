package com.bttraining.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.TextNode;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionGateway;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.TransactionSearchRequest;
import com.bttraining.dao.TransactionDao;
import com.bttraining.service.impl.TransactionServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionServiceImpl.class)
public class TransactionServiceTest {

	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";
	private static final String PAYMENT_METHOD_NONCE = "TEST_PAYMENT_METHOD_NONCE";
	private static final String PAYMENT_AMOUNT = "100";
	private static final String TRANSACTION_ID = "TEST_TRANSACTION_ID";
	private static final String[] TRANSACTION_IDS = { "TEST_TRANSACTION_ID1",
			"TEST_TRANSACTION_ID2", "TEST_TRANSACTION_ID3" };

	@Mock
	private TransactionDao transactionDao;

	@Mock
	private BraintreeGateway braintreeGateway;

	@InjectMocks
	private TransactionService transactionService = new TransactionServiceImpl();

	@Test
	public void shouldDoTransactionByMethodNonceAndAmount() throws Exception {
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionRequest transactionRequest = mock(TransactionRequest.class);
		whenNew(TransactionRequest.class).withNoArguments().thenReturn(
				transactionRequest);
		when(transactionRequest.amount(new BigDecimal(PAYMENT_AMOUNT)))
				.thenReturn(transactionRequest);
		when(transactionRequest.paymentMethodNonce(PAYMENT_METHOD_NONCE))
				.thenReturn(transactionRequest);

		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.sale(transactionRequest)).thenReturn(expectedResult);

		Result<Transaction> actualResult = transactionService
				.doTransactionByMethodNonceAndAmount(PAYMENT_METHOD_NONCE,
						PAYMENT_AMOUNT);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldVoidTransaction() {
		// given
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.voidTransaction(TRANSACTION_ID)).thenReturn(
				expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.voidTransaction(TRANSACTION_ID);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldSubmitTransactionForSettlement() {
		// given
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.submitForSettlement(TRANSACTION_ID)).thenReturn(
				expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.submitTransactionForSettlement(TRANSACTION_ID);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldRefundTransaction() {
		// given
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.refund(TRANSACTION_ID)).thenReturn(expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.refundTransaction(TRANSACTION_ID);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldGetTransactionsByCustomerId() throws Exception {
		// given
		Set<Transaction> expectedTransactionSet = generateExpectedTransactionSet(TRANSACTION_IDS.length);

		@SuppressWarnings("unchecked")
		ResourceCollection<Transaction> resourceTransactions = (ResourceCollection<Transaction>) mock(ResourceCollection.class);
		TransactionSearchRequest transactionSearchRequest = mock(TransactionSearchRequest.class);
		whenNew(TransactionSearchRequest.class).withNoArguments().thenReturn(
				transactionSearchRequest);
		@SuppressWarnings("unchecked")
		TextNode<TransactionSearchRequest> textNode = (TextNode<TransactionSearchRequest>) mock(TextNode.class);
		when(transactionSearchRequest.customerId()).thenReturn(textNode);
		when(textNode.is(CUSTOMER_ID)).thenReturn(transactionSearchRequest);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.search(transactionSearchRequest)).thenReturn(
				resourceTransactions);
		List<Transaction> transactionList = generateTransactionListByIds(TRANSACTION_IDS);
		Iterator<Transaction> iterator = transactionList.iterator();
		when(resourceTransactions.iterator()).thenReturn(iterator);

		setTransactionDaoBehaviourWhenGetTxById(TRANSACTION_IDS,
				expectedTransactionSet);

		// when
		Set<Transaction> actualTransactionSet = transactionService
				.getTransactionsByCustomerId(CUSTOMER_ID);

		// then
		assertEquals(expectedTransactionSet, actualTransactionSet);
	}

	private List<Transaction> generateTransactionListByIds(String[] ids) {
		List<Transaction> transactionList = new ArrayList<>(ids.length);
		for (String id : ids) {
			Transaction transaction = createTransactionMockAndSetBehaviour(id);
			transactionList.add(transaction);
		}
		return transactionList;
	}

	private Transaction createTransactionMockAndSetBehaviour(String id) {
		Transaction transaction = mock(Transaction.class);
		when(transaction.getId()).thenReturn(id);
		return transaction;
	}

	private Set<Transaction> generateExpectedTransactionSet(
			int transactionNumber) {
		Set<Transaction> expectedTransactionSet = new HashSet<>();
		for (int i = 0; i < transactionNumber; i++) {
			expectedTransactionSet.add(mock(Transaction.class));
		}
		return expectedTransactionSet;
	}

	private void setTransactionDaoBehaviourWhenGetTxById(String[] inputIds,
			Set<Transaction> outputTransactionSet) {
		int i = 0;
		for (Transaction transaction : outputTransactionSet) {
			when(transactionDao.getTransactionById(inputIds[i])).thenReturn(
					transaction);
			i++;
		}
	}
}
