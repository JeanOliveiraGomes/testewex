package com.wex.test.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.wex.test.dto.RatesOfExchangeResponse;
import com.wex.test.dto.TransactionDTO;
import com.wex.test.exception.NegativeAmountTransactionException;
import com.wex.test.exception.TransactionNotFoundException;
import com.wex.test.exception.TransactionOlderThanSixMothException;
import com.wex.test.mapper.TransactionMapper;
import com.wex.test.model.Transaction;
import com.wex.test.repository.TransactionRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	private final FiscalDataAPI fiscalDataAPI;

	public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
		validateAmountTransaction(transactionDTO);
		Transaction transaction = TransactionMapper.transactionDTOToTransaction(transactionDTO);
		return TransactionMapper.transactionToTransactionDTO(transactionRepository.save(transaction));
	}
	
	public List<TransactionDTO> findListOfTransactions(){
		return TransactionMapper.listTransactionDTOsToListTransactions(transactionRepository.findAll());
	}
	
	public TransactionDTO findTransaction(Long id, String currency){
		Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found!"));
		validateTransaction(transaction);
				
		RatesOfExchangeResponse ratesOfExchangeResponse = fiscalDataAPI.getRatesOfExchange("currency, exchange_rate,record_date", createApiFilter(currency).toString());
		TransactionDTO dto = configureResponseResult(transaction, ratesOfExchangeResponse);
		return dto;
	}

	private TransactionDTO configureResponseResult(Transaction transaction,
			RatesOfExchangeResponse ratesOfExchangeResponse) {
		TransactionDTO dto = TransactionMapper.transactionToTransactionDTO(transaction);
		dto.setConvertedAmount(dto.getPurchaseAmount().multiply(new BigDecimal(ratesOfExchangeResponse.getData().get(0).getExchange_rate())));
		dto.setConvertedAmount(dto.getConvertedAmount().setScale(2, RoundingMode.HALF_EVEN));
		dto.setExchangeRate(Double.valueOf(ratesOfExchangeResponse.getData().get(0).getExchange_rate()));
		return dto;
	}

	private StringBuilder createApiFilter(String currency) {
		StringBuilder sb = new StringBuilder();
		sb.append("currency:eq:");
		sb.append(currency + ",");
		sb.append("record_date:gte:");
		sb.append(LocalDate.now().minusMonths(6).toString()+ "&");
		sb.append("sort=-record_date&page[number]=1&page[size]=1");
		return sb;
	}
	
	public void validateTransaction(Transaction transaction) {
		if (transaction.getTransactionDate().isBefore(LocalDate.now().minusMonths(6))) {
			throw new TransactionOlderThanSixMothException("Trasaction older than six months");
		}
	}
	
	private void validateAmountTransaction(TransactionDTO transactionDTO) {
		if(Objects.isNull(transactionDTO) || Objects.isNull(transactionDTO.getPurchaseAmount()) || !(transactionDTO.getPurchaseAmount().compareTo(BigDecimal.ZERO) > 0) ) {
			throw new NegativeAmountTransactionException("Amount must be positive");
		}
	}
}
