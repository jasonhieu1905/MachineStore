package com.machine.service;

import java.util.HashMap;
import java.util.List;

import com.machine.model.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Product getProductById(int id);
	List<Product> getProductByCategoryId(int cateId);
	HashMap<Integer,List<Product>> getMainProductByCategoryAndPriority();
	List<Product> searchAutoCompleteProduct(String keyword);
	void deleteProduct(Product product);
}
