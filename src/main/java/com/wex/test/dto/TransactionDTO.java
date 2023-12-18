package com.wex.test.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable{

	private static final long serialVersionUID = -3700500601468997995L;

	private Long id;
	
	private String description;
	
	private LocalDate transactionDate;
	
	private BigDecimal purchaseAmount;
	
	private Double exchangeRate;
	
	private BigDecimal convertedAmount;
}
