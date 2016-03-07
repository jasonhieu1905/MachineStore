package com.machine.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.UserDAO;
import com.machine.model.User;
import com.machine.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		return userDAO.getUserByUsernameAndPassword(username, password);
	}

}
