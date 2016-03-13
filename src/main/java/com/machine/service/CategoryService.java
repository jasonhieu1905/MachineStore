package com.machine.service;

import java.util.List;

import com.machine.model.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	Category getCategoryById(int id);
	List<Category> getCategoriesMainProduct();
	void addNewCategory(Category category);
	List<Category> getCategoriesAccessories();
}
