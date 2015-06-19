package com.bttraining.facade;

import java.util.Set;

import com.bttraining.web.dto.TransactionDTO;
import com.bttraining.web.dto.TransactionInfoDTO;

public interface TransactionFacade {
	TransactionDTO doTransactionByMethodNonceAndAmount(String methodNonce,
			String amountString);

	TransactionDTO submitTransactionForSettlement(String transactionId);

	TransactionDTO voidTransaction(String transactionId);

	Set<TransactionInfoDTO> getTransactionInfoDTOsByCustomerId(String customerId);

	TransactionDTO refundTransaction(String transactionId);
}
