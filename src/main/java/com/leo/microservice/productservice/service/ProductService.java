package com.leo.microservice.productservice.service;

import java.util.List;

import com.leo.microservice.productservice.dto.ProductDto;
import com.leo.microservice.productservice.entity.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	void addProduct(ProductDto productdto);
	
	public List<ProductDto> getAllProductsDto();
	
	Product findProductById(Long productId);
	
	ProductDto updateProduct(Long id, ProductDto productdto);
	
	void updateProductDetails(Product product);
	
	ProductDto getProduct(Long id);

}
