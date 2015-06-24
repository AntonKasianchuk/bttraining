package com.bttraining.facade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.bttraining.facade.converter.TransactionConverter;
import com.bttraining.facade.impl.TransactionFacadeImpl;
import com.bttraining.service.TransactionService;
import com.bttraining.web.dto.TransactionDTO;
import com.bttraining.web.dto.TransactionInfoDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionFacadeImpl.class)
public class TransactionFacadeTest {

	private static final String METHOD_NONCE = "TEST_METHOD_NONCE";
	private static final String AMOUNT = "TEST_AMOUNT";
	private static final String TRANSACTION_ID = "TEST_TRANSACTION_ID";
	private static final String TRANSACTION_STATUS = "SETTLED";
	private static final Boolean SUCCESS = Boolean.TRUE;
	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";

	@Mock
	private TransactionService transactionService;

	@Mock
	private TransactionConverter transactionConverter;

	@InjectMocks
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	private TransactionDTO expectedTransactionDTO;
	private Result<Transaction> transactionResult;
	private Transaction transaction;
	private Status status;

	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		expectedTransactionDTO = mock(TransactionDTO.class);
		transactionResult = (Result<Transaction>) mock(Result.class);
		transaction = mock(Transaction.class);
		status = Transaction.Status.SETTLED;
	}

	@Test
	public void shouldDoTransactionByMethodNonceAndAmount() throws Exception {
		// given
		whenNew(TransactionDTO.class).withNoArguments().thenReturn(
				expectedTransactionDTO);
		when(
				transactionService.doTransactionByMethodNonceAndAmount(
						METHOD_NONCE, AMOUNT)).thenReturn(transactionResult);
		when(transactionResult.getTarget()).thenReturn(transaction);
		when(transaction.getId()).thenReturn(TRANSACTION_ID);
		when(transaction.getStatus()).thenReturn(status);
		when(transactionResult.isSuccess()).thenReturn(SUCCESS);

		// when
		TransactionDTO actualTransactionDTO = transactionFacade
				.doTransactionByMethodNonceAndAmount(METHOD_NONCE, AMOUNT);

		// then
		verify(expectedTransactionDTO, times(1)).setTransactionId(
				TRANSACTION_ID);
		verify(expectedTransactionDTO, times(1)).setTransactionStatus(
				TRANSACTION_STATUS);
		verify(expectedTransactionDTO, times(1)).setSuccess(SUCCESS);
		Assert.assertEquals(expectedTransactionDTO, actualTransactionDTO);
	}

	@Test
	public void shouldSubmitTransactionForSettlement() throws Exception {
		// given
		whenNew(TransactionDTO.class).withNoArguments().thenReturn(
				expectedTransactionDTO);
		when(transactionService.submitTransactionForSettlement(TRANSACTION_ID))
				.thenReturn(transactionResult);
		when(transactionResult.getTarget()).thenReturn(transaction);
		when(transaction.getStatus()).thenReturn(status);
		when(transactionResult.isSuccess()).thenReturn(SUCCESS);

		// when
		TransactionDTO actualTransactionDTO = transactionFacade
				.submitTransactionForSettlement(TRANSACTION_ID);

		// then
		verify(expectedTransactionDTO, times(1)).setTransactionStatus(
				TRANSACTION_STATUS);
		verify(expectedTransactionDTO, times(1)).setSuccess(SUCCESS);
		Assert.assertEquals(expectedTransactionDTO, actualTransactionDTO);
	}

	@Test
	public void shouldVoidTransaction() throws Exception {
		// given
		whenNew(TransactionDTO.class).withNoArguments().thenReturn(
				expectedTransactionDTO);
		when(transactionService.voidTransaction(TRANSACTION_ID)).thenReturn(
				transactionResult);
		when(transactionResult.getTarget()).thenReturn(transaction);
		when(transaction.getStatus()).thenReturn(status);
		when(transactionResult.isSuccess()).thenReturn(SUCCESS);

		// when
		TransactionDTO actualTransactionDTO = transactionFacade
				.voidTransaction(TRANSACTION_ID);

		// then
		verify(expectedTransactionDTO, times(1)).setTransactionStatus(
				TRANSACTION_STATUS);
		verify(expectedTransactionDTO, times(1)).setSuccess(SUCCESS);
		Assert.assertEquals(expectedTransactionDTO, actualTransactionDTO);
	}

	@Test
	public void shouldRetrieveTransactionInfoDTOsByCustomerId() throws Exception {
		// given
		@SuppressWarnings("unchecked")
		HashSet<TransactionInfoDTO> expectedTransactionInfoDTOSet = (HashSet<TransactionInfoDTO>) mock(HashSet.class);
		whenNew(HashSet.class).withNoArguments().thenReturn(
				expectedTransactionInfoDTOSet);
		@SuppressWarnings("unchecked")
		Set<Transaction> transactionSet = (Set<Transaction>) mock(Set.class);
		when(transactionService.getTransactionsByCustomerId(CUSTOMER_ID))
				.thenReturn(transactionSet);
		@SuppressWarnings("unchecked")
		Iterator<Transaction> iterator = mock(Iterator.class);
		when(iterator.hasNext()).thenReturn(true, false);
		when(iterator.next()).thenReturn(transaction);
		when(transactionSet.iterator()).thenReturn(iterator);
		TransactionInfoDTO transactionInfoDTO = mock(TransactionInfoDTO.class);
		when(transactionConverter.generateTransactionDTO(transaction))
				.thenReturn(transactionInfoDTO);

		// when
		Set<TransactionInfoDTO> actualTransactionInfoDTOSet = transactionFacade
				.getTransactionInfoDTOsByCustomerId(CUSTOMER_ID);

		// then
		verify(expectedTransactionInfoDTOSet, times(1)).add(
				transactionInfoDTO);
		Assert.assertEquals(expectedTransactionInfoDTOSet, actualTransactionInfoDTOSet);
	}

	@Test
	public void shouldRefundTransaction() throws Exception {
		// given
		whenNew(TransactionDTO.class).withNoArguments().thenReturn(
				expectedTransactionDTO);
		when(transactionService.refundTransaction(TRANSACTION_ID)).thenReturn(
				transactionResult);
		when(transactionResult.getTarget()).thenReturn(transaction);
		when(transaction.getStatus()).thenReturn(status);
		when(transactionResult.isSuccess()).thenReturn(SUCCESS);

		// when
		TransactionDTO actualTransactionDTO = transactionFacade
				.refundTransaction(TRANSACTION_ID);

		// then
		verify(expectedTransactionDTO, times(1)).setTransactionStatus(
				TRANSACTION_STATUS);
		verify(expectedTransactionDTO, times(1)).setSuccess(SUCCESS);
		Assert.assertEquals(expectedTransactionDTO, actualTransactionDTO);
	}
}
