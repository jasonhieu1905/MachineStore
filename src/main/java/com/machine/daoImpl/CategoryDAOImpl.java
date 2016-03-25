package com.machine.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.CategoryDAO;
import com.machine.model.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		return getSession().createQuery("from Category").list();
	}

	@Override
	public Category getCategoryById(int id) {
		return (Category) getSession().getNamedQuery("Category.findById").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesMainProduct() {
		return (List<Category>) getSession().getNamedQuery("Category.findByMainProduct").setParameter("id", 1).list();
	}

	@Override
	public void addNewCategory(Category category) {
		 getSession().persist(category);;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesAccessories() {
		return (List<Category>) getSession().getNamedQuery("Category.findByMainProduct").setParameter("id", 2).list();
	}

	@Override
	public void deleteCategories(Category category) {
		getSession().delete(category);
	}

	@Override
	public void updateCategory(Category category) {
		getSession().update(category);
	}
	
	
	
}
