package com.leo.microservice.productservice.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
public class ProductDto {

	private Long productId;
	
	@NotNull(message = "Enter a product name")
	private String productName;
	
	@NotNull(message = "Enter a product description")
	private String productDescription;
	
	@Min(value = 1, message = "The value must be positive")
	@Digits(fraction = 2, integer = 10, message = "Must only contain 2 decimal places")
	private double price;


}
