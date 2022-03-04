package com.leo.microservice.productservice.util;

import java.util.Date;

public class ProductUtilities {
	
	private ProductUtilities() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Date getDateAndTime() {
		return new Date();
	}

}
