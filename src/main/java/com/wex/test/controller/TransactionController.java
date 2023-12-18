package com.wex.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wex.test.dto.TransactionDTO;
import com.wex.test.exception.TransactionNotFoundException;
import com.wex.test.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;
	
	@Operation(summary = "This method will save a transaction")
	@PostMapping
	public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
		return ResponseEntity.ok(transactionService.saveTransaction(transactionDTO));
	}
	
	@Operation(summary = "This method will retrive a transaction")
	@GetMapping
	public ResponseEntity<List<TransactionDTO>> retrieveListOfTransaction() {
		return ResponseEntity.ok(transactionService.findListOfTransactions());
		
	}
	
	@Operation(summary = "This method will retrive a transaction by its identifier")
	@GetMapping("/{id}")
	public ResponseEntity<TransactionDTO> retrieveTransaction(@PathVariable Long id, @RequestParam(required = true) String currency) {
		return ResponseEntity.ok(transactionService.findTransaction(id, currency));

		
	}

}
