package com.adcash.demo.service;

import java.util.List;

import com.adcash.demo.model.Category;
import com.adcash.demo.model.Product;

public interface EcommerceService {

	public List<Category> getCategories();

	boolean isAuthorizedUser(int userId);

	boolean deleteCategory(int id);

	String updateCategory(int id, String categoryName);

	String createCategory(int id, String categoryName);

	List<Product> getProducts();

	boolean deleteProduct(int id);

	String updateProduct(int id, String productName);

	List<Product> getProductOfDiscreteCategory(int categoryId);

	void createProduct(int productId, String productName, List<Integer> multipleCategories);

}
