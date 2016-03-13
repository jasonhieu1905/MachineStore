package com.machine.dao;

import java.util.List;

import com.machine.model.Product;

public interface ProductDAO {

	List<Product> getAllProducts();
	Product getProductById(int id);
	List<Product> getProductByCategoryId(int cateId);
}
