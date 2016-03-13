package com.machine.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.ProductDAO;
import com.machine.model.Product;

import com.machine.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
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
	

}
