package com.leo.microservice.productservice.service;

import com.leo.microservice.productservice.dto.ProductLogDto;

public interface ProductLogService {
	
	void sendLog(String message);

}
