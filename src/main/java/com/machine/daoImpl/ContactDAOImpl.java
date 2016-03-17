package com.machine.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.ContactDAO;
import com.machine.model.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Contact getContact() {
		return (Contact) getSession().getNamedQuery("Contact.findAll").list().get(0);
	}

}
