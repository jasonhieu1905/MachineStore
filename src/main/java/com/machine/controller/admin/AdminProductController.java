package com.machine.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.machine.dto.ProductForm;
import com.machine.model.Category;
import com.machine.model.Product;
import com.machine.service.CategoryService;
import com.machine.service.ProductService;
import com.machine.utils.FileUtils;
import com.machine.utils.LoginHelper;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
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
	
	@RequestMapping(value="/addNewProduct",method = RequestMethod.GET)
	public String addNewProduct(ModelMap modelMap){
		modelMap.addAttribute("productForm", new ProductForm());
		List<Category> categories = categoryService.getAllCategories();
		modelMap.addAttribute("categories", categories);
		
		return "adminAddNewProductPage";
	}
	
	@RequestMapping(value="/addNewProduct",method = RequestMethod.POST)
	public String handleAddNewProduct(@ModelAttribute("productForm") ProductForm productForm,HttpServletRequest request) throws IllegalStateException, IOException{
		String savedDirectory = FileUtils.directoryImage(request);
		List<MultipartFile> mainFiles = productForm.getMainFileUpload().getFiles();
		List<MultipartFile> detailFiles = productForm.getDetailFileUpload().getFiles();
		Product product = productForm.getProduct();
		product.setCreateddate(new Date());
		if(mainFiles != null && mainFiles.size() > 0){
			for(MultipartFile file : mainFiles){
				String fileName = file.getOriginalFilename();
              if (!"".equalsIgnoreCase(fileName)) {
            	  file.transferTo(new File(savedDirectory + fileName));
            	  product.setImage(fileName);
              }
			}
		}
		if (null != detailFiles && detailFiles.size() > 0) {
			String detailsImageName = "";
            for (MultipartFile multipartFile : detailFiles) {
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    multipartFile.transferTo(new File(savedDirectory + fileName));
                    detailsImageName += fileName;
                    detailsImageName += ",";
                }
            }
            detailsImageName = FileUtils.removeLastCharacterIfComma(detailsImageName);
            product.setZoomImage(detailsImageName);
        }
		productService.addNewProduct(product);
		return "redirect:/adminBanner/4";
	}
	
	@RequestMapping(value="/editProduct/{id}",method = RequestMethod.GET)
	public String editProduct(@PathVariable int id,ModelMap modelMap){
		Product product = productService.getProductById(id);
		ProductForm productForm = new ProductForm();
		productForm.setProduct(product);
		modelMap.addAttribute("productForm", productForm);
		List<Category> categories = categoryService.getAllCategories();
		modelMap.addAttribute("categories", categories);
		
		return "adminEditProduct";
	}
}
