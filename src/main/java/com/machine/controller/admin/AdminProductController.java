package com.machine.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminProductController {

	@RequestMapping(value="/editCategory/{id}",method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int id,ModelMap model){
		
		return null;
	}
}
