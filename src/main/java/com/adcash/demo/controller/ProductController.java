package com.adcash.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adcash.demo.model.InputRequest;
import com.adcash.demo.model.Product;
import com.adcash.demo.service.EcommerceService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private EcommerceService service;
	
	
	@GetMapping(value="/products")
	public  @ResponseBody List<Product> getProducts(){
		
		return service.getProducts();
	}
	
	@GetMapping(value="/products/{category_id}")
	public @ResponseBody List<Product> getProductOfDiscreteCategory(@PathVariable("category_id") int categoryId){
		return service.getProductOfDiscreteCategory(categoryId);
	}
	
	@DeleteMapping(value="/products/{id}/{user_id}")
	public @ResponseBody boolean deleteProduct(@PathVariable("id") int id,@PathVariable("user_id") int userId) {
		if(service.isAuthorizedUser(userId)) {
			return service.deleteProduct(id);
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
	
	@PutMapping(value="/products/{id}/{user_id}/{Product_name}")
	public String updateProduct(@PathVariable("id") int id,@PathVariable("user_id") int userId,@PathVariable("Product_name") String ProductName) {
		if(service.isAuthorizedUser(userId)) {
			 service.updateProduct(id,ProductName);
			 return "Updated Product";
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
	
	@PostMapping(value="/products/{id}/{user_id}/{Product_name}")
	public String createProduct(@RequestBody InputRequest request) {
		if(service.isAuthorizedUser(request.getUserId())) {
			 service.createProduct(request.getProductId(),request.getProductName(),request.getMultipleCategories());
			 return "Created Product"+request.getProductId();
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
}
