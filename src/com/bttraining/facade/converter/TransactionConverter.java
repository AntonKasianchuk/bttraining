package com.bttraining.facade.converter;

import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.bttraining.web.dto.TransactionDTO;

public class TransactionConverter {

	public TransactionDTO generateTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setDate(transaction.getUpdatedAt().getTime());
		String lastName = "";
		if (transaction.getCustomer().getFirstName() != null) {
			lastName = transaction.getCustomer().getLastName();
		}
		String firstName = "";
		if (transaction.getCustomer().getFirstName() != null) {
			firstName = transaction.getCustomer().getFirstName();
		}
		transactionDTO.setCustomerName(lastName + " " + firstName);
		transactionDTO.setType(transaction.getType().name());
		Boolean isSettled = Boolean.FALSE;
		if (transaction.getStatus().ordinal() == Status.SETTLED.ordinal()) {
			isSettled = Boolean.TRUE;
		}
		transactionDTO.setIsSettled(isSettled);
		transactionDTO.setStatus(transaction.getStatus().name());
		transactionDTO.setAmount(transaction.getAmount().toEngineeringString());
		transactionDTO.setPaymentInformation(transaction
				.getPaymentInstrumentType());
		
		return transactionDTO;
	}
}
