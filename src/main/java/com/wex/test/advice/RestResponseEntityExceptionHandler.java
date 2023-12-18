package com.wex.test.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wex.test.exception.NegativeAmountTransactionException;
import com.wex.test.exception.TransactionNotFoundException;
import com.wex.test.exception.TransactionOlderThanSixMothException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(value 
		      = { NegativeAmountTransactionException.class, NegativeAmountTransactionException.class })
		    protected ResponseEntity<Object> handleConflict(
		      RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = ex.getLocalizedMessage();
		        return ResponseEntity.badRequest().body(bodyOfResponse);
		    }
	 
	 @ExceptionHandler(value 
		      = { TransactionNotFoundException.class, TransactionNotFoundException.class })
		    protected ResponseEntity<Object> handleConflict2(
		      RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = ex.getLocalizedMessage();
		        return ResponseEntity.notFound().build();
		    }
	 
	 @ExceptionHandler(value 
		      = { TransactionOlderThanSixMothException.class, TransactionOlderThanSixMothException.class })
		    protected ResponseEntity<Object> handleConflict3(
		      RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = ex.getLocalizedMessage();
		        return ResponseEntity.badRequest().body(bodyOfResponse);
		    }
}
