package com.leo.microservice.productservice.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorHandler {
	
	private String details;
	private String message;
	private Date timestamp;

}
