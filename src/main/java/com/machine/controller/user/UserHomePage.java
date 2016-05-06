package com.machine.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Banner;
import com.machine.model.Category;
import com.machine.model.Product;
import com.machine.service.BannerService;
import com.machine.service.CategoryService;

@Controller
@RequestMapping("/home")
public class UserHomePage {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BannerService bannerService;

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("name", "JCG Hello World!");
		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();
		for (int i = 0; i < mainCatalogues.size(); i++) {
			if (!mainCatalogues.get(i).getProductList().isEmpty()) {
				mainCatalogues.get(i).getProductList().sort(new Comparator<Product>() {
					@Override
					public int compare(Product o1, Product o2) {
						if (o1.getPriorityOrder() <= o2.getPriorityOrder())
							return 1;
						else
							return -1;
					}
				});
			}
		}

		List<Category> accCatalogues = new ArrayList<>();
		accCatalogues = categoryService.getCategoriesAccessories();
		List<Banner> banners = new ArrayList<>();
		banners = bannerService.listAllBanners();
		
		model.addAttribute("currentPage", "home");
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("banners", banners);
		return "home";

	}
}
