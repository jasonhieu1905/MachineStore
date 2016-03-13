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
public class AdminHomePageController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String doLogin(ModelMap model) {
		return "login";

	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String proceedLogin(@ModelAttribute("username")String username,@ModelAttribute("password")String password,ModelMap model) {
		User userLogin = userService.getUserByUsernameAndPassword(username, password);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories",categories);
		return "adminHomePage";
	}
	
	
}
