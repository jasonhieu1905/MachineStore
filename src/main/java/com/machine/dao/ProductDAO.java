package com.machine.dao;

import java.util.HashMap;
import java.util.List;

import com.machine.model.Category;
import com.machine.model.Product;

public interface ProductDAO {

	List<Product> getAllProducts();
	Product getProductById(int id);
	List<Product> getProductByCategoryId(int cateId);
	HashMap<Integer,List<Product>> getMainProductByCategoryAndPriority(List<Category> categories);
	List<Product> searchAutoCompleteProduct(String keyword);
	void deleteProduct(Product product);
}
