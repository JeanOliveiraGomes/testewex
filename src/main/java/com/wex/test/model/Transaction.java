package com.wex.test.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@Size(max = 50, message = "Description max size 50")
	private String description;
	
	private LocalDate transactionDate;
	
	@NotNull(message = "Amount may not be null")
	private BigDecimal purchaseAmount;
	
}
