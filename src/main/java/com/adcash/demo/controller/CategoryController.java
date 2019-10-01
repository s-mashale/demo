package com.adcash.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adcash.demo.model.Category;
import com.adcash.demo.service.EcommerceService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private EcommerceService service;
	
	
	@GetMapping(value="/categories")
	public  @ResponseBody List<Category> getCategories(){
		
		return service.getCategories();
	}
	
	@DeleteMapping(value="/categories/{id}/{user_id}")
	public @ResponseBody boolean deleteCategory(@PathVariable("id") int id,@PathVariable("user_id") int userId) {
		if(service.isAuthorizedUser(userId)) {
			return service.deleteCategory(id);
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
	
	@PutMapping(value="/categories/{id}/{user_id}/{category_name}")
	public String updateCategory(@PathVariable("id") int id,@PathVariable("user_id") int userId,@PathVariable("category_name") String categoryName) {
		if(service.isAuthorizedUser(userId)) {
			return service.updateCategory(id,categoryName);
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
	
	@PostMapping(value="/categories/{id}/{user_id}/{category_name}")
	public String create(@PathVariable("id") int id,@PathVariable("user_id") int userId,@PathVariable("category_name") String categoryName) {
		if(service.isAuthorizedUser(userId)) {
			 service.createCategory(id,categoryName);
			 return "Created Category"+id;
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User is not Authorized");
		}
	}
}
