package com.leo.microservice.productservice.exception;

import java.util.Date;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

import com.leo.microservice.productservice.dto.ErrorDto;
import com.leo.microservice.productservice.service.ProductLogService;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	ProductLogService productLogService;

	@Autowired
	ErrorDto errorDto;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDto> resourceNotFoundExceptionHandler(ResourceNotFoundException error,
			WebRequest request) {
		productLogService.sendLog("ResourceNotFoundException occurred: " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Invalid Product")
				.withDetails(error.getMessage())
				.withErrorType(ResourceNotFoundException.class.getSimpleName())
				.withErrorCode("P404")
				.build();
		
		return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException error) {
		
		/* SonarLint non-compliant. possible null pointer exception. will try to work on this
		 * this is a better customized error message
		 * 
		FieldError fieldError = error.getFieldError();
		productLogService.sendLog(error.fieldError.getDefaultMessage());
		System.out.println("Invalid value: " +error.getFieldError().getRejectedValue() +" for field: " +error.getFieldError().getField() +" in " +error.getFieldError().getObjectName() +" class");
		*/
		productLogService.sendLog("MethodArgumentNotValidException occurred: " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Invalid Field")
				.withDetails(error.getMessage())
				.withErrorType(MethodArgumentNotValidException.class.getSimpleName())
				.withErrorCode("P400")
				.build();
		
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDto> productAPINullPointerExceptionHandler(NullPointerException error, WebRequest request) {

		productLogService.sendLog("NullPointerException occurred: " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Null object")
				.withDetails(error.getMessage())
				.withErrorType(NullPointerException.class.getSimpleName())
				.withErrorCode("P400")
				.build();

		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDto> globalExceptionHandler(HttpRequestMethodNotSupportedException error, WebRequest request) {

		productLogService.sendLog("Method Request not available:  " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Method Request not supported")
				.withDetails(error.getMessage())
				.withErrorType(HttpRequestMethodNotSupportedException.class.getSimpleName())
				.withErrorCode("P405")
				.build();

		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(ProductAPIException.class)
	public ResponseEntity<ErrorDto> productAPIExceptionHandler(ProductAPIException error, WebRequest request) {

		productLogService.sendLog("Base RunTime Exception. A runtime error occurred: " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Invalid Field/s")
				.withDetails(error.getMessage())
				.withErrorType(MethodArgumentNotValidException.class.getSimpleName())
				.withErrorCode("P400")
				.build();

		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> globalExceptionHandler(Exception error, WebRequest request) {

		productLogService.sendLog("Base Exception Reached. Unexpected error occurred: " + error.getLocalizedMessage());
		errorDto = errorDto.builder()
				.withTitle("Unexpected Error")
				.withDetails(error.getClass().getSimpleName())
				.withErrorType(Exception.class.getSimpleName())
				.withErrorCode("P500")
				.build();

		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
