package com.adcash.demo.model;

public class Product {

	
	private int productId;
	private String name;
	
	public Product() {}
	public Product(int productId2, String productName) {
	this.productId=productId2;
	this.name=productName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
