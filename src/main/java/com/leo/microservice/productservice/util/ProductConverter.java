package com.leo.microservice.productservice.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.leo.microservice.productservice.dto.ProductDto;
import com.leo.microservice.productservice.entity.Product;

@Component
public class ProductConverter {
	
//	ModelMapper mapper = new ModelMapper();
	
	public ProductDto entityToDto(Product product) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(product, ProductDto.class);
	}
	
	public Product dtoToEntity(ProductDto productDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(productDto, Product.class);
	}

}
