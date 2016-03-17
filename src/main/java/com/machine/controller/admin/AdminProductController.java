package com.machine.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.machine.model.Product;
import com.machine.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value="/products",method = RequestMethod.GET)
	public ModelAndView getAllProducts(){
		ModelAndView modelAndView = new ModelAndView();
		List<Product> products = productService.getAllProducts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("productPage");
		
		return modelAndView;
	}
}
