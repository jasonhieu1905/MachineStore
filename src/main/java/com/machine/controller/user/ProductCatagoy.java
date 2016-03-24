package com.machine.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Category;
import com.machine.model.Product;
import com.machine.service.CategoryService;
import com.machine.service.ProductService;

@Controller
public class ProductCatagoy {
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/category/{typeId}/{id}", method = RequestMethod.GET)
	public String homePage(@PathVariable int typeId, @PathVariable int id, ModelMap model) {

		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();

		List<Category> accCatalogues = new ArrayList<>();
		accCatalogues = categoryService.getCategoriesAccessories();

		Category currentCategory = new Category();
		String currentPage ="";
		if (typeId == 1) {
			currentPage = "product";
			for (int i = 0; i < mainCatalogues.size(); i++) {
				if (mainCatalogues.get(i).getId().equals(id)) {
					currentCategory = mainCatalogues.get(i);
					break;
				}
			}
		} else if (typeId == 2) {
			currentPage = "accessories";
			for (int i = 0; i < accCatalogues.size(); i++) {
				if (accCatalogues.get(i).getId().equals(id)) {
					currentCategory = accCatalogues.get(i);
					break;
				}
			}
		}

		if (!currentCategory.getProductList().isEmpty()) {
			currentCategory.getProductList().sort(new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					if (o1.getPriorityOrder() <= o2.getPriorityOrder())
						return 1;
					else
						return -1;
				}
			});
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("currentCategory", currentCategory);
		return "category";
	}
}
