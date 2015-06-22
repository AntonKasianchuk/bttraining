package com.bttraining.facade.converter;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import com.braintreegateway.Customer;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.braintreegateway.Transaction.Type;
import com.bttraining.web.dto.TransactionInfoDTO;

public class TransactionConverterTest {

	private static final String ID = "test first name";
	private static final Calendar DATE = Calendar.getInstance();
	private static final String TYPE = "CREDIT";
	private static final String STATUS = "SETTLED";
	private static final String AMOUNT = "test_amount";
	private static final String PAYMENT_INFORMATION = "test_payment_information";
	private static final String CUSTOMER_NAME = "last first";

	private final TransactionConverter unit = new TransactionConverter();

	@Test
	public void shouldConvertCustomerToCustomerDTO() {
		// given
		final Transaction transaction = Mockito.mock(Transaction.class);
		Mockito.when(transaction.getId()).thenReturn(ID);
		Mockito.when(transaction.getUpdatedAt()).thenReturn(DATE);
		Customer customer = Mockito.mock(Customer.class);
		Mockito.when(customer.getFirstName()).thenReturn("first");
		Mockito.when(customer.getLastName()).thenReturn("last");
		Mockito.when(transaction.getCustomer()).thenReturn(customer);
		Type type = Transaction.Type.CREDIT;
		Mockito.when(transaction.getType()).thenReturn(type);
		Status status = Transaction.Status.SETTLED;
		Mockito.when(transaction.getStatus()).thenReturn(status);
		BigDecimal amount = Mockito.mock(BigDecimal.class);
		Mockito.when(transaction.getAmount()).thenReturn(amount);
		Mockito.when(amount.toEngineeringString()).thenReturn(AMOUNT);
		Mockito.when(transaction.getPaymentInstrumentType()).thenReturn(
				PAYMENT_INFORMATION);

		// when
		TransactionInfoDTO transactionInfoDTO = unit
				.generateTransactionDTO(transaction);

		// then
		assertEquals(transactionInfoDTO.getId(), ID);
		assertEquals(transactionInfoDTO.getDate(), DATE.getTime());
		assertEquals(transactionInfoDTO.getCustomerName(), CUSTOMER_NAME);
		assertEquals(transactionInfoDTO.getStatus(), STATUS);
		assertEquals(transactionInfoDTO.getType(), TYPE);
		assertEquals(transactionInfoDTO.getAmount(), AMOUNT);
		assertEquals(transactionInfoDTO.getPaymentInformation(),
				PAYMENT_INFORMATION);

	}
}
