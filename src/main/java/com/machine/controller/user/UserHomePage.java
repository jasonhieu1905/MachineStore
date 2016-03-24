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
		
		
		/*List<Menu> menu = new ArrayList<>();
		for(int i = 0; i < 2;i++){
			String subName = "Menu title "+i;
			Menu sub = new Menu();
			MenuObject title = new MenuObject();
			title.setId(i+"");
			title.setName(subName);
			
			List<MenuObject> items = new ArrayList<>();
			for(int j = 0 ;j < 3; j++){
				MenuObject item = new MenuObject();	
				item.setId(j+"");
				item.setName(subName +" - item "+j);
				items.add(item);
			}
			sub.setTitle(title);
			sub.setItems(items);
			menu.add(sub);
		}*/
		model.addAttribute("currentPage","home");
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		return "home";

	}
}
