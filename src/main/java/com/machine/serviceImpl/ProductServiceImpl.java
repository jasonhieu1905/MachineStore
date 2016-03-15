package com.machine.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.CategoryDAO;
import com.machine.dao.ProductDAO;
import com.machine.model.Category;
import com.machine.model.Product;

import com.machine.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	private CategoryDAO categoryDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}

	@Override
	public List<Product> getProductByCategoryId(int cateId) {
		return productDAO.getProductByCategoryId(cateId);
	}

	@Override
	public HashMap<Integer, List<Product>> getMainProductByCategoryAndPriority() {
		List<Category> categories = categoryDAO.getCategoriesMainProduct();
		return productDAO.getMainProductByCategoryAndPriority(categories);
	}
	

}
