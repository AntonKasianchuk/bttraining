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

	@Mock
	private TransactionDao transactionDao;

	@Mock
	private BraintreeGateway braintreeGateway;

	@InjectMocks
	private TransactionService transactionService = new TransactionServiceImpl();

	@Test
	public void shouldDoTransactionByMethodNonceAndAmount() throws Exception {
		String paymentMethodNonce = "payment_method_nonce";
		String amount = "1234";
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionRequest transactionRequest = mock(TransactionRequest.class);
		whenNew(TransactionRequest.class).withNoArguments().thenReturn(transactionRequest);
		when(transactionRequest.amount(new BigDecimal(amount))).thenReturn(transactionRequest);
		when(transactionRequest.paymentMethodNonce(paymentMethodNonce)).thenReturn(transactionRequest);
		
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.sale(transactionRequest)).thenReturn(
				expectedResult);
		
		Result<Transaction> actualResult = transactionService.doTransactionByMethodNonceAndAmount(paymentMethodNonce, amount);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldVoidTransaction() {
		// given
		String transactionId = "id";
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.voidTransaction(transactionId)).thenReturn(
				expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.voidTransaction(transactionId);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldSubmitTransactionForSettlement() {
		// given
		String transactionId = "id";
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.submitForSettlement(transactionId)).thenReturn(
				expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.submitTransactionForSettlement(transactionId);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldRefundTransaction() {
		// given
		String transactionId = "id";
		@SuppressWarnings("unchecked")
		Result<Transaction> expectedResult = (Result<Transaction>) mock(Result.class);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.submitForSettlement(transactionId)).thenReturn(
				expectedResult);

		// when
		Result<Transaction> actualResult = transactionService
				.submitTransactionForSettlement(transactionId);

		// then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldGetTransactionsByCustomerId() throws Exception {
		// given
		String customerId = "id";
		String[] expectedTransactionIds = new String[] { "123", "abc", "4d5e6f" };
		Set<Transaction> expectedTransactionSet = generateExpectedTransactionSet(expectedTransactionIds.length);
		
		@SuppressWarnings("unchecked")
		ResourceCollection<Transaction> resourceTransactions = (ResourceCollection<Transaction>) mock(ResourceCollection.class);
		TransactionSearchRequest transactionSearchRequest = mock(TransactionSearchRequest.class);
		whenNew(TransactionSearchRequest.class).withNoArguments().thenReturn(transactionSearchRequest);
		@SuppressWarnings("unchecked")
		TextNode<TransactionSearchRequest> textNode = (TextNode<TransactionSearchRequest>)mock(TextNode.class);
		when(transactionSearchRequest.customerId()).thenReturn(textNode);
		when(textNode.is(customerId)).thenReturn(transactionSearchRequest);
		TransactionGateway transaction = mock(TransactionGateway.class);
		when(braintreeGateway.transaction()).thenReturn(transaction);
		when(transaction.search(transactionSearchRequest)).thenReturn(
				resourceTransactions);
		List<Transaction> transactionList = generateTransactionListByIds(expectedTransactionIds);
		Iterator<Transaction> iterator = transactionList.iterator();
		when(resourceTransactions.iterator()).thenReturn(iterator);
		
		setTransactionDaoBehaviourWhenGetTxById(expectedTransactionIds, expectedTransactionSet);
		
		//when
		Set<Transaction> actualTransactionSet = transactionService.getTransactionsByCustomerId(customerId);
		
		//then
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
	
	private Set<Transaction> generateExpectedTransactionSet(int transactionNumber) {
		Set<Transaction> expectedTransactionSet = new HashSet<>();
		for (int i = 0; i < transactionNumber; i++) {
			expectedTransactionSet.add(mock(Transaction.class));
		}
		return expectedTransactionSet;
	}
	
	private void setTransactionDaoBehaviourWhenGetTxById(String[] inputIds, Set<Transaction> outputTransactionSet) {
		int i = 0;
		for (Transaction transaction : outputTransactionSet) {			
			when(transactionDao.getTransactionById(inputIds[i])).thenReturn(transaction);
			i++;
		}
	}
}
