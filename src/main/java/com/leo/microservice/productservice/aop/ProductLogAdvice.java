package com.leo.microservice.productservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.microservice.productservice.service.ProductLogService;

@Aspect
@Component
public class ProductLogAdvice {
	
//	Logger log = LoggerFactory.getLogger(ProductLogAdvice.class);
	
	@Autowired
	ProductLogService productLogService;
	
	@Pointcut(value = "execution (* com.leo.microservice.productservice.controller.*.*(..) )")
	public void productControllerPointCut() {
		//point cut for product controller
	}
	
	@Around("productControllerPointCut()")
	public Object myProductLogger(ProceedingJoinPoint pcj) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		String methodName = pcj.getSignature().getName();
		String className = pcj.getTarget().getClass().getSimpleName();
		
		//SENDING LOG - Class and method called + arguments sent
		productLogService.sendLog("From class: " +className);
		productLogService.sendLog("Invoked method: " +methodName);
		productLogService.sendLog("Argumenst passed: " +mapper.writeValueAsString(pcj.getArgs()));
		
		Object obj = pcj.proceed();
		
		//SENDING LOG - whole response entity
		productLogService.sendLog("Resulting Reponse: " +mapper.writeValueAsString(obj));

		return obj;
	}

}
