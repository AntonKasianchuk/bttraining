package com.bttraining.facade.converter;

import com.braintreegateway.Transaction;
import com.bttraining.web.dto.TransactionInfoDTO;

public class TransactionConverter {

	public TransactionInfoDTO generateTransactionDTO(Transaction transaction) {
		TransactionInfoDTO transactionDTO = new TransactionInfoDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setDate(transaction.getUpdatedAt().getTime());
		String lastName = "";
		if (transaction.getCustomer().getLastName() != null) {
			lastName = transaction.getCustomer().getLastName();
		}
		String firstName = "";
		if (transaction.getCustomer().getFirstName() != null) {
			firstName = transaction.getCustomer().getFirstName();
		}
		transactionDTO.setCustomerName(lastName + " " + firstName);
		transactionDTO.setType(transaction.getType().name());
		transactionDTO.setStatus(transaction.getStatus().name());
		transactionDTO.setAmount(transaction.getAmount().toEngineeringString());
		transactionDTO.setPaymentInformation(transaction
				.getPaymentInstrumentType());

		return transactionDTO;
	}
}
