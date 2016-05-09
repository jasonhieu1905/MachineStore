package com.machine.controller.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.machine.model.Banner;
import com.machine.model.Category;
import com.machine.model.CategoryHeader;
import com.machine.model.Contact;
import com.machine.model.Product;
import com.machine.model.SearchProduct;
import com.machine.service.BannerService;
import com.machine.service.CategoryService;
import com.machine.service.ContactService;
import com.machine.service.ProductService;

@Controller
public class ProductCatagoy {
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	private ContactService contactService;
	
	@ResponseBody
	@RequestMapping(value = "/search/auto")
	public List<SearchProduct>  getSearchResultViaAjax(@RequestBody String search) throws JSONException {
		JSONObject objectSearch = new JSONObject(search);
		String keyword = (String) objectSearch.get("query");
		List<Product> list = productService.searchAutoCompleteProduct(keyword);
		List<SearchProduct> result = new ArrayList<>();	
		for(Product p : list){
			SearchProduct sp = new SearchProduct();
			sp.setImage(p.getImage());
			sp.setName(p.getName());
			sp.setId(p.getId());
			sp.setCategoryId(p.getCategoryId().getId());
			sp.setCatagoryName(p.getCategoryId().getName());
			sp.setType(p.getCategoryId().getType());
			result.add(sp);
		}
		//AjaxResponseBody result = new AjaxResponseBody();
		//result.setProducts(list);
		
		//logic
		return result;

	}	
	
	@ResponseBody
	@RequestMapping(value = "/menu/catagories", method = RequestMethod.GET)
	public List<CategoryHeader>  getCatagoryViaAjax(){
		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();
		List<CategoryHeader> result = new ArrayList<>();
		for(Category cat : mainCatalogues){
			CategoryHeader tmp = new CategoryHeader();
			tmp.setId(cat.getId());
			tmp.setName(cat.getName());
			tmp.setType(cat.getType());
			result.add(tmp);
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/menu/accessories", method = RequestMethod.GET)
	public List<CategoryHeader>  getAccessoriesViaAjax(){
		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesAccessories();
		List<CategoryHeader> result = new ArrayList<>();
		for(Category cat : mainCatalogues){
			CategoryHeader tmp = new CategoryHeader();
			tmp.setId(cat.getId());
			tmp.setName(cat.getName());
			tmp.setType(cat.getType());
			result.add(tmp);
		}
		return result;

	}
	
	
	@RequestMapping(value = "/search/full", method = RequestMethod.GET)
	public String search(@RequestParam(value="query") String data, ModelMap model) throws JSONException{
		List<Category> mainCatalogues = new ArrayList<>();
		mainCatalogues = categoryService.getCategoriesMainProduct();

		List<Category> accCatalogues = new ArrayList<>();
		accCatalogues = categoryService.getCategoriesAccessories();	
		
		//JSONObject objectSearch = new JSONObject(data);
		//String keyword = (String) objectSearch.get("query");
		List<Product> searchProducts = productService.searchAutoCompleteProduct(data);
		List<SearchProduct> list = new ArrayList<>();
		for(Product p : searchProducts){
			SearchProduct sp = new SearchProduct();
			sp.setName(p.getName());
			sp.setImage(p.getImage());
			sp.setId(p.getId());
			sp.setCategoryId(p.getCategoryId().getId());
			sp.setCatagoryName(p.getCategoryId().getName());
			sp.setType(p.getCategoryId().getType());
			list.add(sp);
		}
		

		List<Banner> banners = new ArrayList<>();
		banners = bannerService.listAllBanners();
		Contact contact = contactService.getContact();	
		model.addAttribute("contact", contact);
		//model.addAttribute("currentPage", currentPage);
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("products", list);
		model.addAttribute("banners", banners);
		model.addAttribute("accessPage", UserHomePage.ACCESS_PAGE);
		return "search";
	}
	
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
		
		List<Banner> banners = new ArrayList<>();
		banners = bannerService.listAllBanners();
		Contact contact = contactService.getContact();	
		model.addAttribute("contact", contact);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("catalogues", mainCatalogues);
		model.addAttribute("accessories", accCatalogues);
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("banners", banners);
		model.addAttribute("accessPage", UserHomePage.ACCESS_PAGE);
		return "category";
	}
}
