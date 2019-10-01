package com.adcash.demo.model;

public class Category {

	
	private int categoryId;
	private String name;
	public Category() {}
	public Category(int id, String categoryName) {
		this.categoryId=id;
		this.name=categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}