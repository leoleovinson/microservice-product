package com.leo.microservice.productservice.exception;

import java.util.Date;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.leo.microservice.productservice.service.ProductLogService;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	ProductLogService productLogService;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorHandler> resourceNotFoundExceptionHandler(ResourceNotFoundException error, WebRequest request){
//		System.out.println("ASDSAD" +error +request.getContextPath() +request.getHeader(null));
		productLogService.sendLog("ResourceNotFoundException occurred: " +error.getLocalizedMessage());
		ErrorHandler errorHandler = new ErrorHandler(request.getDescription(false), error.getLocalizedMessage(), new Date());
		return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorHandler> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException error){
//		System.out.println("ASDSAD" +error +request.getContextPath() +request.getHeader(null));
		productLogService.sendLog("MethodArgumentNotValidException occurred: " +error.getLocalizedMessage());
		//git test
		ErrorHandler errorHandler = new ErrorHandler("Field data error", error.getBindingResult().getFieldError().toString(), new Date());
		return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(ProductAPIException.class)
//	public ResponseEntity<ErrorHandler> productAPIExceptionHandler(ProductAPIException error, WebRequest request){
//		System.out.println("ASDSAD" +error +request.getContextPath() +request.getHeader(null));
//		ErrorHandler errorHandler = new ErrorHandler(request.getDescription(false), error.getLocalizedMessage(), new Date());
//		return new ResponseEntity<>(errorHandler, HttpStatus.INTERNAL_SERVER_ERROR); 
//	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorHandler> globalExceptionHandler(Exception error, WebRequest request){
//		System.out.println("ASDSAD" +error +request.getContextPath() +request.getHeader(null));
		productLogService.sendLog("Unexpected error occurred");
		ErrorHandler errorHandler = new ErrorHandler(request.getDescription(false), error.getLocalizedMessage() , new Date());
		return new ResponseEntity<>(errorHandler, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
