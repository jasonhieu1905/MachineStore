package com.machine.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.CategoryDAO;
import com.machine.model.Category;
import com.machine.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private CategoryDAO categoryDAO;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	@Override
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategories();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryDAO.getCategoryById(id);
	}

	@Override
	public List<Category> getCategoriesMainProduct() {
		return categoryDAO.getCategoriesMainProduct();
	}


	@Override
	public void addNewCategory(Category category) {
		categoryDAO.addNewCategory(category);
	}


	@Override
	public List<Category> getCategoriesAccessories() {
		return categoryDAO.getCategoriesAccessories();
	}


	@Override
	public void deleteCategories(Category category) {
		categoryDAO.deleteCategories(category);
	}
	
	

}
