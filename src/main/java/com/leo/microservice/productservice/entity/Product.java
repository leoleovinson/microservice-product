package com.leo.microservice.productservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

	@Id
	@GeneratedValue
	private Long productId;
	private String productName;
	private String productDescription;
	private double price;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;

	public Product() {
	}

	public Product(Long productId, String productName, String productDescription, Date dateCreated) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
