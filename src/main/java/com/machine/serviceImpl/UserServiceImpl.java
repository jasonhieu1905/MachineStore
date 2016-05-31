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
		try{
			return userDAO.getUserByUsernameAndPassword(username, password);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		try{
			return userDAO.getUserByUsername(username);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		try{
			 userDAO.updateUser(user);
		}catch(Exception e){
			System.out.println("Can not update user" + e.toString());
		}
	}
	
	

}
