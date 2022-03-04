package com.leo.microservice.productservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.leo.microservice.productservice.dto.ProductLogDto;
import com.leo.microservice.productservice.service.ProductLogService;

@Service
public class ProductLogServiceImpl implements ProductLogService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public void sendLog(String message) {
		System.out.println("testing this line of code" +message);
		kafkaTemplate.send("logs-topic", message);
	}

}
