package com.wex.test.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionNotFoundException extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TransactionNotFoundException(String message) {
		super();
		this.message = message;
	}

}
