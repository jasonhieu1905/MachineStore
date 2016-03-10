package com.machine.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class Product {
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("name", "JCG Hello World!");
		return "product";

	}
}
