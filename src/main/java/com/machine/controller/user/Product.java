package com.machine.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Menu;
import com.machine.model.MenuObject;

@Controller
@RequestMapping("/product")
public class Product {
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		List<Menu> menu = new ArrayList<>();
		for(int i = 0; i < 5;i++){
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
		}
		model.addAttribute("menu", menu);
		model.addAttribute("name", "JCG Hello World!");
		return "product";

	}
}
