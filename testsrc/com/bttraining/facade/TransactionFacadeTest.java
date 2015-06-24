package com.bttraining.facade;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Assert;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionFacadeImpl.class)
public class TransactionFacadeTest {

	private static final String METHOD_NONCE = "TEST_METHOD_NONCE";
	private static final String AMOUNT = "TEST_AMOUNT";
	private static final String TRANSACTION_ID = "TEST_TRANSACTION_ID";
	private static final String TRANSACTION_STATUS = "SETTLED";
	private static final Boolean SUCCESS = Boolean.TRUE;

	@Mock
	private TransactionService transactionService;

	@Mock
	private TransactionConverter transactionConverter;

	@InjectMocks
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	@Test
	public void shouldDoTransactionByMethodNonceAndAmount() throws Exception {
		// given
		TransactionDTO expectedTransactionDTO = mock(TransactionDTO.class);
		whenNew(TransactionDTO.class).withNoArguments().thenReturn(
				expectedTransactionDTO);
		@SuppressWarnings("unchecked")
		Result<Transaction> transactionResult = (Result<Transaction>) mock(Result.class);
		when(
				transactionService.doTransactionByMethodNonceAndAmount(
						METHOD_NONCE, AMOUNT)).thenReturn(transactionResult);
		Transaction transaction = mock(Transaction.class);
		when(transactionResult.getTarget()).thenReturn(transaction);
		when(transaction.getId()).thenReturn(TRANSACTION_ID);
		Status status = Transaction.Status.SETTLED;
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
}
