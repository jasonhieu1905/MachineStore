package com.machine.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Banner;
import com.machine.model.Category;
import com.machine.model.Contact;
import com.machine.model.Product;
import com.machine.service.BannerService;
import com.machine.service.CategoryService;
import com.machine.service.ContactService;
import com.machine.service.ProductService;

@Controller
@RequestMapping("/detail")
public class ProductDetail {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "/{typeId}/{categoryId}/{productId}", method = RequestMethod.GET)
	public String getProductDetail(@PathVariable int typeId, @PathVariable int categoryId, @PathVariable int productId,
			ModelMap model) {

		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();

		List<Category> accCatalogues = new ArrayList<>();
		accCatalogues = categoryService.getCategoriesAccessories();

		Product product = productService.getProductById(productId);
		String[] imageList = product.getZoomImage().split(",");

		Category currentCategory = new Category();
		String currentPage = "";
		if (typeId == 1) {
			currentPage = "product";
			for (int i = 0; i < mainCatalogues.size(); i++) {
				if (mainCatalogues.get(i).getId().equals(categoryId)) {
					currentCategory = mainCatalogues.get(i);
				}
			}
		} else if (typeId == 2) {
			currentPage = "accessories";
			for (int i = 0; i < accCatalogues.size(); i++) {
				if (accCatalogues.get(i).getId().equals(categoryId)) {
					currentCategory = accCatalogues.get(i);
				}
			}
		}
		if (!currentCategory.getProductList().isEmpty()) {
//			currentCategory.getProductList().sort(new Comparator<Product>() {
//				@Override
//				public int compare(Product o1, Product o2) {
//					if (o1.getPriorityOrder() <= o2.getPriorityOrder())
//						return 1;
//					else
//						return -1;
//				}
//			});
			
			Collections.sort(currentCategory.getProductList(),new Comparator<Product>() {

				@Override
				public int compare(Product o1, Product o2) {
					if (o1.getPriorityOrder() <= o2.getPriorityOrder())
						return 1;
					else
						return -1;
				}
			});
		}
		
		List<Banner> banners = new ArrayList<>();
		banners = bannerService.listAllBanners();
		Contact contact = contactService.getContact();	
		model.addAttribute("contact", contact);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("product", product);
		model.addAttribute("imageList", imageList);
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("banners", banners);
		model.addAttribute("accessPage", UserHomePage.ACCESS_PAGE);
		return "detail";

	}
}
