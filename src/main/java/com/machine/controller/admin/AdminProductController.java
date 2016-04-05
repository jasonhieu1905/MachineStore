package com.machine.controller.admin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Product;
import com.machine.service.ProductService;
import com.machine.utils.LoginHelper;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value="/products/{id}",method = RequestMethod.GET)
	public ModelAndView getAllProducts(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView();
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}else{
			modelAndView.addObject("username", session.getAttribute("username"));
		}
		List<Product> products = productService.getAllProducts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("productPage");
		modelAndView.addObject("id-enable", id);
		return modelAndView;
	}
	
	@RequestMapping(value="/products/search",method=RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> searchProduct(
			@RequestParam final String keyword) {
		List<HashMap<String, String>> listProduct = new ArrayList<HashMap<String, String>>();
		for (Product city : productService.searchAutoCompleteProduct(keyword)) {
			HashMap<String, String> hmapProduct = new HashMap<String, String>();
			hmapProduct.put("id", city.getId().toString());
			hmapProduct.put("name", city.getName());
			listProduct.add(hmapProduct);
		}
		return listProduct;
	} 
	
	@RequestMapping(value="/deleteProduct",method=RequestMethod.POST)
	public @ResponseBody String deleteProduct(@RequestParam final String productsId){
		String[] prosId = productsId.split(",");
		try{
			for(String proId : prosId){
				productService.deleteProduct(productService.getProductById(Integer.parseInt(proId)));
			}
			return "SUCCESS";
		}catch(Exception e){
			System.out.println("Problem while deleting category :" + e.toString());
			return "FAILED";
		}
	}
	
}
