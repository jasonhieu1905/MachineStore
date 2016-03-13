package com.machine.dao;

import java.util.List;

import com.machine.model.Category;

public interface CategoryDAO {

	List<Category> getAllCategories();

	Category getCategoryById(int id);

	List<Category> getCategoriesMainProduct();

	void addNewCategory(Category category);
}
