package com.leo.microservice.productservice.exception;

import org.springframework.stereotype.Component;

public class ProductAPIException extends RuntimeException {
	
	public ProductAPIException(String message) {
		super(message);
	}
}
