package com.leo.microservice.productservice.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ErrorDto {
	
	private String title;
	private String details;
	private String errorType;
	private String errorCode;
	private String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));
	
	public ErrorDto(String title, String details, String errorType, String errorCode) {
		this.title = title;
		this.details = details;
		this.errorType = errorType;
		this.errorCode = errorCode;
	}
	
	public ErrorDtoBuilder builder() {
		return new ErrorDtoBuilder();
	}

	public class ErrorDtoBuilder {
		private String title;
		private String details;
		private String errorType;
		private String errorCode;
		
		public ErrorDtoBuilder withTitle(String title){
			this.title = title;
			return this;
		}
		
		public ErrorDtoBuilder withDetails(String details){
			this.details = details;
			return this;
		}
		
		public ErrorDtoBuilder withErrorType(String errorType){
			this.errorType = errorType;
			return this;
		}
		
		public ErrorDtoBuilder withErrorCode(String errorCode){
			this.errorCode = errorCode;
			return this;
		}
		
		public ErrorDto build() {
			return new ErrorDto(title,details,errorType,errorCode);
		}
		
	}

}
