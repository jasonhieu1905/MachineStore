package com.machine.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

	private static final int activeMenuLeft = 1;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView modelAndView = new ModelAndView();
		if (!LoginHelper.isLogin(session)) {
			return new ModelAndView("redirect:/login");
		} else {
			modelAndView.addObject("username", session.getAttribute("username"));
		}
		List<Product> products = productService.getAllProducts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("productPage");
		modelAndView.addObject("pageId", activeMenuLeft);
		return modelAndView;
	}

	@RequestMapping(value = "/products/search", method = RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> searchProduct(@RequestParam final String keyword) {
		List<HashMap<String, String>> listProduct = new ArrayList<HashMap<String, String>>();
		for (Product city : productService.searchAutoCompleteProduct(keyword)) {
			HashMap<String, String> hmapProduct = new HashMap<String, String>();
			hmapProduct.put("id", city.getId().toString());
			hmapProduct.put("name", city.getName());
			listProduct.add(hmapProduct);
		}
		return listProduct;
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public @ResponseBody String deleteProduct(@RequestParam final String productsId) {
		String[] prosId = productsId.split(",");
		try {
			for (String proId : prosId) {
				productService.deleteProduct(productService.getProductById(Integer.parseInt(proId)));
			}
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println("Problem while deleting category :" + e.toString());
			return "FAILED";
		}
	}

	@RequestMapping(value = "/addNewProduct", method = RequestMethod.GET)
	public String addNewProduct(ModelMap modelMap) {
		modelMap.addAttribute("productForm", new ProductForm());
		List<Category> categories = categoryService.getAllCategories();
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("pageId", activeMenuLeft);
		return "adminAddNewProductPage";
	}

	@RequestMapping(value = "/addNewProduct", method = RequestMethod.POST)
	public String handleAddNewProduct(@ModelAttribute("productForm") ProductForm productForm,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String savedDirectory = FileUtils.directoryImage(request);
		List<MultipartFile> mainFiles = productForm.getMainFileUpload().getFiles();
		List<MultipartFile> detailFiles = productForm.getDetailFileUpload().getFiles();
		Product product = productForm.getProduct();
		product.setCreateddate(new Date());
		if (mainFiles != null && mainFiles.size() > 0) {
			for (MultipartFile file : mainFiles) {
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
		return "redirect:/products";
	}

	@RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable int id, ModelMap modelMap) {
		Product product = productService.getProductById(id);
		ProductForm productForm = new ProductForm();
		productForm.setProduct(product);
		modelMap.addAttribute("productForm", productForm);
		modelMap.addAttribute("product", productForm.getProduct());
		List<Category> categories = categoryService.getAllCategories();
		modelMap.addAttribute("categories", categories);

		// main Image
		modelMap.addAttribute("mainImage", product.getImage());

		// detail Image
		List<String> detailImage = Arrays.asList(product.getZoomImage().split(","));
		modelMap.addAttribute("detailImages", detailImage);
		modelMap.addAttribute("pageId", activeMenuLeft);
		return "adminEditProduct";
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public String handleEditProduct(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String savedDirectory = FileUtils.directoryImage(request);
		Product product = productForm.getProduct();
		int idProduct = product.getId().intValue();
		Product tempProduct = productService.getProductById(idProduct);
		product.setCreateddate(tempProduct.getCreateddate());
		List<MultipartFile> mainFiles = productForm.getMainFileUpload().getFiles();
		List<MultipartFile> detailFiles = productForm.getDetailFileUpload().getFiles();
		if (mainFiles.get(0).getSize() > 0) {
			MultipartFile file = mainFiles.get(0);
			String mainFileUploadName = file.getOriginalFilename();
			product.setImage(mainFileUploadName);
			file.transferTo(new File(savedDirectory + mainFileUploadName));
		}

		if (null != detailFiles && detailFiles.get(0).getSize() > 0) {
			String newImagesDetail = product.getZoomImage() + ",";
			for (MultipartFile file : detailFiles) {
				String imageDetailName = file.getOriginalFilename();
				newImagesDetail += imageDetailName;
				file.transferTo(new File(savedDirectory + imageDetailName));
			}
			newImagesDetail = FileUtils.removeLastCharacterIfComma(newImagesDetail);
			product.setZoomImage(newImagesDetail);
		}
		productService.updateProduct(product);

		return "redirect:/editProduct/" + idProduct;
	}

	@RequestMapping(value = "/product/deleteDetailImage", method = RequestMethod.POST)
	public @ResponseBody String deleteDetailImage(@RequestParam final String imageDetail, @RequestParam final int id) {
		try {
			Product product = productService.getProductById(id);
			String[] detailImages = product.getZoomImage().split(",");
			String newImages = "";
			for (String detailImage : detailImages) {
				if (!detailImage.equals(imageDetail)) {
					newImages += detailImage;
					newImages += ",";
				}
			}
			newImages = FileUtils.removeLastCharacterIfComma(newImages);
			product.setZoomImage(newImages);
			productService.updateProduct(product);
			return "SUCCESS";
		} catch (Exception e) {
			System.out.println("Problem while deleting category :" + e.toString());
			return "FAILED";
		}
	}

}
