package com.machine.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Category;
import com.machine.service.CategoryService;	

@Controller
@RequestMapping("/home")
public class UserHomePage {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("name", "JCG Hello World!");
		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();
		
		List<Category> accCatalogues = new ArrayList<>();
		accCatalogues = categoryService.getCategoriesAccessories();
		
		model.addAttribute("currentPage","home");
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		return "home";

	}
}
