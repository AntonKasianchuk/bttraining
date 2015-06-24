package com.bttraining.facade.impl;

import java.util.HashSet;
import java.util.Set;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.bttraining.facade.TransactionFacade;
import com.bttraining.facade.converter.TransactionConverter;
import com.bttraining.service.TransactionService;
import com.bttraining.service.impl.TransactionServiceImpl;
import com.bttraining.web.dto.TransactionDTO;
import com.bttraining.web.dto.TransactionInfoDTO;

public class TransactionFacadeImpl implements TransactionFacade {
	private TransactionService transactionService = new TransactionServiceImpl();
	private TransactionConverter transactionConverter = new TransactionConverter();

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public void setTransactionConverter(TransactionConverter transactionConverter) {
		this.transactionConverter = transactionConverter;
	}

	@Override
	public TransactionDTO doTransactionByMethodNonceAndAmount(
			String methodNonce, String amountString) {
		TransactionDTO transactionCreateDTO = new TransactionDTO();
		Result<Transaction> result = transactionService
				.doTransactionByMethodNonceAndAmount(methodNonce, amountString);
		String transactionId = result.getTarget().getId();
		String transactionStatus = result.getTarget().getStatus().toString();
		boolean success = result.isSuccess();
		transactionCreateDTO.setSuccess(success);
		transactionCreateDTO.setTransactionId(transactionId);
		transactionCreateDTO.setTransactionStatus(transactionStatus);
		return transactionCreateDTO;
	}

	@Override
	public TransactionDTO submitTransactionForSettlement(String transactionId) {
		TransactionDTO transactionDTO = new TransactionDTO();
		Result<Transaction> result = transactionService
				.submitTransactionForSettlement(transactionId);
		String transactionStatus = result.getTarget().getStatus().toString();
		boolean success = result.isSuccess();
		transactionDTO.setSuccess(success);
		transactionDTO.setTransactionStatus(transactionStatus);
		return transactionDTO;
	}

	@Override
	public TransactionDTO voidTransaction(String transactionId) {
		TransactionDTO transactionDTO = new TransactionDTO();
		Result<Transaction> result = transactionService
				.voidTransaction(transactionId);
		String transactionStatus = result.getTarget().getStatus().toString();
		boolean success = result.isSuccess();
		transactionDTO.setSuccess(success);
		transactionDTO.setTransactionStatus(transactionStatus);
		return transactionDTO;
	}

	@Override
	public Set<TransactionInfoDTO> getTransactionInfoDTOsByCustomerId(
			String customerId) {
		Set<TransactionInfoDTO> result = new HashSet<TransactionInfoDTO>();
		Set<Transaction> transactions = transactionService
				.getTransactionsByCustomerId(customerId);
		for (Transaction transaction : transactions) {
			TransactionInfoDTO transactionDTO = transactionConverter
					.generateTransactionDTO(transaction);
			result.add(transactionDTO);
		}
		return result;
	}

	@Override
	public TransactionDTO refundTransaction(String transactionId) {
		TransactionDTO transactionDTO = new TransactionDTO();
		Result<Transaction> result = transactionService
				.refundTransaction(transactionId);
		String transactionStatus = result.getTarget().getStatus().toString();
		boolean success = result.isSuccess();
		transactionDTO.setSuccess(success);
		transactionDTO.setTransactionStatus(transactionStatus);
		return transactionDTO;
	}
}
