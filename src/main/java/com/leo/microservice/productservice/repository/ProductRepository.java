package com.leo.microservice.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.microservice.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
