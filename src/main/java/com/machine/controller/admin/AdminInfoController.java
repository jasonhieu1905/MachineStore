package com.machine.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.User;
import com.machine.service.UserService;
import com.machine.utils.LoginHelper;

@Controller
public class AdminInfoController {

	@Autowired
	HttpSession session;
	
	@Autowired
	private UserService	 userService;

	private static final int activeMenuLeft = 6;

	@RequestMapping(value = "/adminInfo", method = RequestMethod.GET)
	public ModelAndView getUserInfo(ModelAndView model) {
		if (!LoginHelper.isLogin(session)) {
			return new ModelAndView("redirect:/login");
		} else {
			model.addObject("username", session.getAttribute("username"));
		}
		User user = userService.getUserByUsername(session.getAttribute("username").toString());
		model.setViewName("adminInfo");
		model.addObject("user",user);
		model.addObject("pageId", activeMenuLeft);
		return model;
	};
	
	@RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST)
	public String updateUserInfo(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/logout";
	};

}
