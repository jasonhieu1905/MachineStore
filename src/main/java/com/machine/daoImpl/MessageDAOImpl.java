package com.machine.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.MessageDAO;
import com.machine.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addNewMessage(Message message) {
		getSession().persist(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessages() {
		return  getSession().getNamedQuery("Message.findAll").list();
	}

	@Override
	public void deleteMessage(Message message) {
		getSession().delete(message);
	}

	@Override
	public Message getMessageById(int id) {
		return (Message) getSession().getNamedQuery("Message.findById").setParameter("id", id).list().get(0);
	}

}
