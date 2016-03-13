package com.machine.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.ProductDAO;
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
	
}
