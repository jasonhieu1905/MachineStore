package com.machine.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.UserDAO;
import com.machine.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		Query query = getSession().createQuery("from User Where username=:username and password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return (User) query.list().get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		Query query = getSession().createQuery("from User Where username=:username");
		query.setParameter("username", username);
		return (User) query.list().get(0);
	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);
	}
	
	
}
