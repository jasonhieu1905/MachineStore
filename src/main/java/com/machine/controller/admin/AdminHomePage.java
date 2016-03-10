package com.machine.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Category;
import com.machine.model.User;
import com.machine.service.CategoryService;
import com.machine.service.UserService;

@Controller
public class AdminHomePage {

	/*@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	*/
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String doLogin(ModelMap model) {
		return "login";

	}
	
	/*@RequestMapping(value="/login",method = RequestMethod.POST)
	public String proceedLogin(@ModelAttribute("username")String username,@ModelAttribute("password")String password) {
		User userLogin = userService.getUserByUsernameAndPassword(username, password);
		System.out.println(userLogin.getUsername());
		List<Category> categories = categoryService.getAllCategories();
		System.out.println(categories.get(0).getName());
		return "adminHomePage";
	}*/
	
	
}
