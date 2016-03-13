package com.machine.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.machine.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
}
