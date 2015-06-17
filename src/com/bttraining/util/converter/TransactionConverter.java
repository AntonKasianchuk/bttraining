package com.bttraining.util.converter;

import com.braintreegateway.Transaction;
import com.bttraining.web.dto.TransactionDTO;

public class TransactionConverter {

	public TransactionDTO generateTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setDate(transaction.getUpdatedAt().getTime());
		String firstName = "";
		if(transaction.getCustomer().getFirstName() != null) {
			firstName = transaction.getCustomer().getFirstName();
		}
		transactionDTO.setCustomerName(transaction.getCustomer().getLastName()
				+ " " + firstName);
		transactionDTO.setType(transaction.getType().name());
		transactionDTO.setStatus(transaction.getStatus().name());
		transactionDTO.setAmount(transaction.getAmount().toEngineeringString());
		transactionDTO.setPaymentInformation(transaction.getPaymentInstrumentType());
		return transactionDTO;
	}
}
