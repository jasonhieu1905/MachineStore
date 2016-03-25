package com.machine.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Category;
import com.machine.service.CategoryService;
import com.machine.utils.LoginHelper;

@Controller
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/editCategory/{id}",method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int id,ModelMap model){
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}
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
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}
		categoryService.updateCategory(currentCategory);
		return new ModelAndView("redirect:/listAllCategories/0");
	}
	
	@RequestMapping(value="/listAllCategories/{id}",method = RequestMethod.GET)
	public String proceedLogin(@PathVariable int id,@ModelAttribute("username")String username,@ModelAttribute("password")String password,ModelMap model) {
		if(!LoginHelper.isLogin(session)){
			return "redirect:/login";
		}
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories",categories);
		model.addAttribute("id-enable", id);
		return "adminHomePage";
	}
	
	@RequestMapping(value="/addNewCategory",method = RequestMethod.GET)
	public ModelAndView addNewCategory(){
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}
		List<Category> categories = categoryService.getCategoriesMainProduct();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("newCategory",new Category());
		modelAndView.setViewName("addNewCategoryPage");
		return modelAndView;
	}
	
	@RequestMapping(value="/addNewCategory",method = RequestMethod.POST)
	public ModelAndView handleAddNewCategory(@ModelAttribute("newCategory") Category category,BindingResult result){
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}
		categoryService.addNewCategory(category);
		return new ModelAndView("redirect:/listAllCategories/0");
	}
	
	@RequestMapping(value="/deleteCategory",method=RequestMethod.POST)
	public @ResponseBody String deleteCategory(@RequestParam final String categoriesId){
		String[] catesId = categoriesId.split(",");
		try{
			for(String cateId : catesId){
				categoryService.deleteCategories(categoryService.getCategoryById(Integer.parseInt(cateId)));
			}
			return "SUCCESS";
		}catch(Exception e){
			System.out.println("Problem while deleting category :" + e.toString());
			return "FAILED";
		}
	}
	
}
