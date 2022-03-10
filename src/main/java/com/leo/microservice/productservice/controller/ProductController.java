package com.leo.microservice.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.microservice.productservice.dto.ProductDto;
import com.leo.microservice.productservice.entity.Product;
import com.leo.microservice.productservice.service.ProductLogService;
import com.leo.microservice.productservice.service.ProductService;
import com.leo.microservice.productservice.serviceimpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductLogService productLogService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProductsDto(),HttpStatus.OK); 
	}
	
	@PostMapping("/new")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productdto) {
		productService.addProduct(productdto);
		return new ResponseEntity<>("Product Added ",HttpStatus.CREATED); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productdto){
		ProductDto productDto = productService.updateProduct(id, productdto);
		if (productDto == null) {
			throw new ResourceNotFoundException("Product not found with id: " +id);
		}
		return new ResponseEntity<>(productService.getProduct(id), HttpStatus.FOUND); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
		ProductDto productDto = productService.getProduct(id);		
		if (productService.findProductById(id) == null) {
			throw new ResourceNotFoundException("Product not found with id: " +id);	
		}
		return new ResponseEntity<>(productDto, HttpStatus.FOUND); 
	}

}
