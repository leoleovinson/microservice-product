package com.leo.microservice.productservice.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.microservice.productservice.dto.ProductDto;
import com.leo.microservice.productservice.entity.Product;
import com.leo.microservice.productservice.repository.ProductRepository;
import com.leo.microservice.productservice.service.ProductService;
import com.leo.microservice.productservice.util.ProductConverter;
import com.leo.microservice.productservice.util.ProductUtilities;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<ProductDto> getAllProductsDto() {
//		return getAllProducts().stream().map(prod -> convertProductEntityToProductDto(prod)).toList();
		return getAllProducts().stream().map(this::convertProductEntityToProductDto).toList();
	}

	@Override
	public void addProduct(ProductDto productdto) {
		productRepository.save(convertProductDtoToProductEntity(productdto));
	}

	private Product convertProductDtoToProductEntity(ProductDto productdto) {
//		Product product = new Product();
//		product.setProductId(productdto.getProductId());
//		product.setProductName(productdto.getProductName());
//		product.setProductDescription(productdto.getProductDescription());
//		product.setDateCreated(ProductUtilities.getDateAndTime());
		return productConverter.dtoToEntity(productdto);
	}

	private ProductDto convertProductEntityToProductDto(Product product) {
//		ProductDto productdto = new ProductDto();
//		productdto.setProductId(product.getProductId());
//		productdto.setProductName(product.getProductName());
//		productdto.setProductDescription(product.getProductDescription());
		return productConverter.entityToDto(product);
	}

	@Override
	public Product findProductById(Long productId) {
		Optional<Product> prod = productRepository.findById(productId);
		if (prod.isPresent()) {
			return prod.get();
		}
		return null;
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productdto) {
		if (findProductById(id) != null) {
			Product product = findProductById(id);
			
			if(productdto.getProductName() != null) product.setProductName(productdto.getProductName());
			if(productdto.getProductDescription() != null) product.setProductDescription(productdto.getProductDescription());
			product.setPrice(productdto.getPrice());
			product.setDateCreated(ProductUtilities.getDateAndTime());
			productdto.setProductId(id);
			updateProductDetails(product);
			return productdto;
		}
		return null;
	}

	@Override
	public void updateProductDetails(Product product) {
		productRepository.save(product);
	}

	@Override
	public ProductDto getProduct(Long id) {
		Product product = findProductById(id);
		if(product != null) {
			return convertProductEntityToProductDto(product);
		}
		return null;
	}

}
