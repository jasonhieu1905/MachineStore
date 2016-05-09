package com.machine.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Access;
import com.machine.model.Banner;
import com.machine.model.Category;
import com.machine.model.Contact;
import com.machine.model.Product;
import com.machine.service.AccessService;
import com.machine.service.BannerService;
import com.machine.service.CategoryService;
import com.machine.service.ContactService;

@Controller
@RequestMapping("/home")
public class UserHomePage {

	@Autowired
	CategoryService categoryService;
	
	@Autowired	
	BannerService bannerService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AccessService accessService;
	
	public static int ACCESS_PAGE = 0;
	
	@Autowired
	HttpSession session;

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
		
		ACCESS_PAGE = accessService.allAcccess();
	    
		Contact contact = contactService.getContact();	
		model.addAttribute("contact", contact);
		model.addAttribute("currentPage", "home");
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("banners", banners);
		model.addAttribute("accessPage", ACCESS_PAGE);
		
		if(session.getAttribute("ACCESS_PAGE") == null){
			session.setAttribute("ACCESS_PAGE", ACCESS_PAGE);
			Access access = accessService.getAccessToday();
			if(access == null){
				access = new Access();
				access.setAccessdate(new Date());
				access.setAccessnumber(1);
				accessService.addAccessPage(access);
			}
			access.setAccessnumber(access.getAccessnumber()+1);
			accessService.updateAccess(access);
		}
		return "home";

	}
}
