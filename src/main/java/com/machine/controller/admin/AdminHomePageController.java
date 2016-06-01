package com.machine.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String doLogin(ModelMap model) {
		return "login";

	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String proceedLogin(@ModelAttribute("username")String username,@ModelAttribute("password")String password,ModelMap model) {
		User userLogin = userService.getUserByUsernameAndPassword(username, password);
		if(userLogin == null){
			return "redirect:/login" ;
		}
		session.setAttribute("admin", "true");
		session.setAttribute("username", username);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories",categories);
		model.addAttribute("username",username);
		model.addAttribute("pageId",0);
		return "adminHomePage";
	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		session.removeAttribute("admin");
		session.removeAttribute("username");
		return "redirect:/login";
	}
	
}
