package com.machine.dao;

import java.util.List;

import com.machine.model.Category;

public interface CategoryDAO {

	List<Category> getAllCategories();

	Category getCategoryById(int id);

	List<Category> getCategoriesMainProduct();
	
	List<Category> getCategoriesAccessories();

	void addNewCategory(Category category);
	
	void deleteCategories(Category category);
	
	void updateCategory(Category category);
}
