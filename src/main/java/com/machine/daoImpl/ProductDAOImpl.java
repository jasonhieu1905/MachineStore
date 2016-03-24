package com.machine.daoImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.ProductDAO;
import com.machine.model.Category;
import com.machine.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		return getSession().getNamedQuery("Product.findAll").list();
	}

	@Override
	public Product getProductById(int id) {
		return (Product) getSession().getNamedQuery("Product.findById").list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByCategoryId(int cateId) {
		return getSession().getNamedQuery("Product.findByCategoryId").setParameter("cateId", cateId).list();
	}

	@Override
	public HashMap<Integer, List<Product>> getMainProductByCategoryAndPriority(List<Category> categories) {
		HashMap<Integer, List<Product>> productsByCategoryAndPrio = new HashMap<>();
		categories.stream().forEach(cate->{
			int cateId = cate.getId();
			@SuppressWarnings("unchecked")
			List<Product> productByCate = getSession().getNamedQuery("Product.findByCategoryId").setParameter("cateId", cateId).list();
			Collections.sort(productByCate,new Comparator<Product>() {

				@Override
				public int compare(Product o1, Product o2) {
					return o1.getPriorityOrder() - o2.getPriorityOrder();
				}
			});
			productsByCategoryAndPrio.put(cateId, productByCate);
		});
		return productsByCategoryAndPrio;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchAutoCompleteProduct(String keyword) {
		return getSession().getNamedQuery("Product.searchProduct").setParameter("keyword", "%" + keyword + "%").list();
	}

	@Override
	public void deleteProduct(Product product) {
		getSession().delete(product);
	}
	
}
