package com.adcash.demo.model;

import java.util.List;

public class InputRequest {
	
	private String productName;
	private int productId;
	private int userId;
	
	private List<Integer> multipleCategories;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getMultipleCategories() {
		return multipleCategories;
	}

	public void setMultipleCategories(List<Integer> multipleCategories) {
		this.multipleCategories = multipleCategories;
	}
	
	
}
