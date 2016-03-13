package com.machine.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Category;
import com.machine.service.CategoryService;

@Controller
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/editCategory/{id}",method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int id,ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		Category category = categoryService.getCategoryById(id);
		List<Category> categories = categoryService.getCategoriesMainProduct();
		modelAndView.addObject("currentCategory",category);
		modelAndView.addObject("categories",categories);
		modelAndView.setViewName("categoryPage");
		return modelAndView;
	}
	
	@RequestMapping(value="/editCategory",method=RequestMethod.POST)
	public ModelAndView updateCategory(@ModelAttribute("currentCategory") Category currentCategory,BindingResult result){
		System.out.println(currentCategory.getName());
		return new ModelAndView("redirect:/listAllCategories");
	}
	
	@RequestMapping(value="/listAllCategories",method = RequestMethod.GET)
	public String proceedLogin(@ModelAttribute("username")String username,@ModelAttribute("password")String password,ModelMap model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories",categories);
		return "adminHomePage";
	}
	
	@RequestMapping(value="/addNewCategory",method = RequestMethod.GET)
	public ModelAndView addNewCategory(){
		List<Category> categories = categoryService.getCategoriesMainProduct();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("newCategory",new Category());
		modelAndView.setViewName("addNewCategoryPage");
		return modelAndView;
	}
	
	@RequestMapping(value="/addNewCategory",method = RequestMethod.POST)
	public ModelAndView handleAddNewCategory(@ModelAttribute("newCategory") Category category,BindingResult result){
		categoryService.addNewCategory(category);
		return new ModelAndView("redirect:/listAllCategories");
	}
	
	
	
}
