package com.bttraining.facade.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

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
		final Transaction transaction = mock(Transaction.class);
		when(transaction.getId()).thenReturn(ID);
		when(transaction.getUpdatedAt()).thenReturn(DATE);
		Customer customer = mock(Customer.class);
		when(customer.getFirstName()).thenReturn("first");
		when(customer.getLastName()).thenReturn("last");
		when(transaction.getCustomer()).thenReturn(customer);
		Type type = Transaction.Type.CREDIT;
		when(transaction.getType()).thenReturn(type);
		Status status = Transaction.Status.SETTLED;
		when(transaction.getStatus()).thenReturn(status);
		BigDecimal amount = mock(BigDecimal.class);
		when(transaction.getAmount()).thenReturn(amount);
		when(amount.toEngineeringString()).thenReturn(AMOUNT);
		when(transaction.getPaymentInstrumentType()).thenReturn(
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
