package com.machine.service;

import com.machine.model.User;

public interface UserService {

	User getUserByUsernameAndPassword(String username,String password);
	
}
