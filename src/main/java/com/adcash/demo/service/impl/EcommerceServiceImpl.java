package com.adcash.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.adcash.demo.model.Category;
import com.adcash.demo.model.Product;
import com.adcash.demo.service.EcommerceService;

public class EcommerceServiceImpl implements EcommerceService {

	private static Map<Integer,String> authorizedUserMap;
	private static List<Product> products= new ArrayList<Product>();
	private static List<Category> categories= new ArrayList<Category>();
	private static Map<Integer,List<Integer>> catalog= new ConcurrentHashMap<Integer,List<Integer>>();
	
	static {
		authorizedUserMap= new HashMap<Integer,String>();
		authorizedUserMap.put(1, "smita");
		authorizedUserMap.put(2, "lokkesh");
		authorizedUserMap.put(3, "jack");
		authorizedUserMap.put(4, "mike");
		authorizedUserMap.put(5, "john");
	}
	public List<Category> getCategories() {
		return categories;
	}

	public boolean isAuthorizedUser(int userId) {
		return authorizedUserMap.containsKey(userId);
	}

	public boolean deleteCategory(int id) {
		
		Iterator<Category> it= categories.iterator();
		while(it.hasNext()) {
			Category p =it.next();
			if(p.getCategoryId()== id) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public String updateCategory(int id, String categoryName) {
		for(Category c: categories) {
			if(c.getCategoryId()== id) {
				c.setName(categoryName);
			}
		}
		
		return "Updated";
	}

	public String createCategory(int id, String categoryName) {
		Category c= new Category(id,categoryName);
		categories.add(c);
		return "Created Category";
	}

	public List<Product> getProducts() {
		return products;
	}

	public boolean deleteProduct(int id) {
		boolean status= false;
		
		Iterator<Product> it= products.iterator();
		while(it.hasNext()) {
			Product p =it.next();
			if(p.getProductId()== id) {
				it.remove();
				status=true;
			}
		}
		for(int catId: catalog.keySet()) {
			Iterator<Integer> itr= catalog.get(id).iterator();
			while(itr.hasNext()) {
				if(itr.next()== id) {
					itr.remove();
				}
			}
		}
		return status;
	}

	public String updateProduct(int id, String productName) {
		 for(Product p: products) {
			 if(p.getProductId()==id) {
				 p.setName(productName);
			 }
		 }
		return "Updated";
	}

	public List<Product> getProductOfDiscreteCategory(int categoryId) {
		List<Product> productList= new ArrayList<Product>();
		for(Integer prodId: catalog.get(categoryId)) {
			for(Product p: products) {
				if(p.getProductId()== prodId) {
					productList.add(p);
				}
			}
			
		}
		return productList;
	}

	public void createProduct(int productId, String productName,List<Integer> multipleCategories) {
		Product p= new Product(productId,productName);
		products.add(p);
		
		for(int catId:multipleCategories) {
			List<Integer> productIds= new ArrayList<Integer>();
			productIds.add(p.getProductId());
			List<Integer> previousProductIds= catalog.put(catId, productIds);
			if(previousProductIds !=null) {
				previousProductIds.add(p.getProductId());
				catalog.put(catId, previousProductIds);
			}
		}

	}

}
