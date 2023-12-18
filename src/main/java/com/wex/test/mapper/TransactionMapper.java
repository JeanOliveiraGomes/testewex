package com.wex.test.mapper;

import java.util.ArrayList;
import java.util.List;

import com.wex.test.dto.TransactionDTO;
import com.wex.test.model.Transaction;

public class TransactionMapper {

	public static TransactionDTO transactionToTransactionDTO(Transaction transaction) {
		return TransactionDTO.builder()
				.id(transaction.getId())
				.description(transaction.getDescription())
				.transactionDate(transaction.getTransactionDate())
				.purchaseAmount(transaction.getPurchaseAmount())
				.build();
	}
	
	public static Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
		return Transaction.builder()
				.id(transactionDTO.getId())
				.description(transactionDTO.getDescription())
				.transactionDate(transactionDTO.getTransactionDate())
				.purchaseAmount(transactionDTO.getPurchaseAmount())
				.build();
	}
	
	public static List<TransactionDTO> listTransactionDTOsToListTransactions(List<Transaction> listTransactions) {
		List<TransactionDTO> listDTOs = new ArrayList();
		listTransactions.forEach((entity) -> {
			listDTOs.add(transactionToTransactionDTO(entity));
		});
		return listDTOs;
	}
}
