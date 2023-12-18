package com.wex.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.wex.test.dto.TransactionDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransationControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	String getUri(String endpoint) {
		return "http://localhost:" + port + "/api"+endpoint;
	}
	
	@Test
	void saveTransactionSuccess() throws Exception {
		
		TransactionDTO dto = TransactionDTO.builder().transactionDate(LocalDate.now()).purchaseAmount(BigDecimal.valueOf(1l)).description("new purchase").build();
	       
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	        
		HttpEntity<TransactionDTO> request = new HttpEntity<>(dto, headers);
		
		ResponseEntity<TransactionDTO> result = this.restTemplate.postForEntity(getUri(""), request, TransactionDTO.class);
		
		//Verify request succeed
        Assert.isTrue(result.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void saveTransactionErrorAmountNegative() throws Exception {
		
		TransactionDTO dto = TransactionDTO.builder().transactionDate(LocalDate.now()).purchaseAmount(BigDecimal.valueOf(0l)).description("new purchase").build();
	       
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	        
		HttpEntity<TransactionDTO> request = new HttpEntity<>(dto, headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity(getUri(""), request, String.class);
		
		//Verify request succeed
        Assert.isTrue(result.getStatusCode().is4xxClientError());
	}
	
	@Test
	void findTransactionSuccess() throws Exception {
		
		TransactionDTO dto = TransactionDTO.builder().transactionDate(LocalDate.now()).purchaseAmount(BigDecimal.valueOf(1l)).description("new purchase").build();
	       
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	        
		HttpEntity<TransactionDTO> request = new HttpEntity<>(dto, headers);
		
		ResponseEntity<TransactionDTO> result = this.restTemplate.getForEntity(getUri("/1?currency=Real"), TransactionDTO.class);
		
		//Verify request succeed
        Assert.isTrue(result.getStatusCode().is2xxSuccessful());
	}
	

	@Test
	void findListTransactionSuccess() throws Exception {
		
		TransactionDTO dto = TransactionDTO.builder().transactionDate(LocalDate.now()).purchaseAmount(BigDecimal.valueOf(1l)).description("new purchase").build();
	       
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	        
		HttpEntity<TransactionDTO> request = new HttpEntity<>(dto, headers);
		
		ResponseEntity<List> result = this.restTemplate.getForEntity(getUri(""), List.class);
		
		//Verify request succeed
        Assert.isTrue(result.getStatusCode().is2xxSuccessful());
	}
}
