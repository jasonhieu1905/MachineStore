package com.machine.dao;

import com.machine.model.User;

public interface UserDAO {
	public User getUserByUsernameAndPassword(String username,String password);
	User getUserByUsername(String username);
	void updateUser(User user);
}
