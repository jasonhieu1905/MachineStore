package com.machine.controller.admin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

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
		String resourcePath = FileUtils.resourcesPath(request);
		List<MultipartFile> mainFiles = productForm.getMainFileUpload().getFiles();
		List<MultipartFile> detailFiles = productForm.getDetailFileUpload().getFiles();
		Product product = productForm.getProduct();
		product.setCreateddate(new Date());
		if (mainFiles.get(0).getSize() > 0) {
			MultipartFile file = mainFiles.get(0);
			String fileName = file.getOriginalFilename();
			// File convFile = new File(fileName);
			// file.transferTo(convFile);
			// file.transferTo(new File(savedDirectory + mainFileUploadName));
			product.setImage(saveImage(file.getInputStream(), fileName, resourcePath, true));
		}

		if (null != detailFiles && detailFiles.get(0).getSize() > 0) {
			String newImagesDetail = "";
			for (MultipartFile file : detailFiles) {
				String imageDetailName = file.getOriginalFilename();
				newImagesDetail += saveImage(file.getInputStream(), imageDetailName, resourcePath, false) + ",";
				// file.transferTo(new File(savedDirectory + imageDetailName));
			}
			newImagesDetail = FileUtils.removeLastCharacterIfComma(newImagesDetail);
			product.setZoomImage(newImagesDetail);
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
		String resourcePath = FileUtils.resourcesPath(request);
		Product product = productForm.getProduct();
		int idProduct = product.getId().intValue();
		Product tempProduct = productService.getProductById(idProduct);
		product.setCreateddate(tempProduct.getCreateddate());
		List<MultipartFile> mainFiles = productForm.getMainFileUpload().getFiles();
		List<MultipartFile> detailFiles = productForm.getDetailFileUpload().getFiles();
		if (mainFiles.get(0).getSize() > 0) {
			MultipartFile file = mainFiles.get(0);
			String fileName = file.getOriginalFilename();
			// File convFile = new File(fileName);
			// file.transferTo(convFile);
			// file.transferTo(new File(savedDirectory + mainFileUploadName));
			product.setImage(saveImage(file.getInputStream(), fileName, resourcePath, true));
		}

		if (null != detailFiles && detailFiles.get(0).getSize() > 0) {
			String newImagesDetail = product.getZoomImage() + ",";
			for (MultipartFile file : detailFiles) {
				String imageDetailName = file.getOriginalFilename();
				newImagesDetail += saveImage(file.getInputStream(), imageDetailName, resourcePath, false) + ",";
				// file.transferTo(new File(savedDirectory + imageDetailName));
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

	private String saveImage(InputStream img, String filename, String resourcesPath, boolean isCeateThumbnail) {
		try {
			BufferedImage image = ImageIO.read(img);
			int originHeight = image.getHeight();
			int originWidth = image.getWidth();
			int maxSize = 1000;
			int thumbnailSize = 300;
			Mode scaleMode = Mode.AUTOMATIC;

			if (originHeight > originWidth) {
				// scale to width
				scaleMode = Scalr.Mode.FIT_TO_WIDTH;
			} else if (originWidth >= originHeight) {
				scaleMode = Scalr.Mode.FIT_TO_HEIGHT;
			}

			BufferedImage outputImage = Scalr.resize(image, Scalr.Method.QUALITY, scaleMode, maxSize);
			if (scaleMode.equals(Scalr.Mode.FIT_TO_WIDTH) && outputImage.getHeight() > maxSize) {
				// the height is too large, resize again
				outputImage = Scalr.resize(outputImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT, maxSize);
			} else if (scaleMode.equals(Scalr.Mode.FIT_TO_HEIGHT) && outputImage.getWidth() > maxSize) {
				// the width is too large, resize again
				outputImage = Scalr.resize(outputImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, maxSize);
			}

			int paddingSize = 0;
			if (outputImage.getWidth() != maxSize) {
				// we need padding on the width axis
				paddingSize = (maxSize - outputImage.getWidth()) / 2;
			} else if (outputImage.getHeight() != maxSize) {
				// we need padding on the height axis
				paddingSize = (maxSize - outputImage.getHeight()) / 2;
			}

			// we need padding?
			if (paddingSize > 0) {
				// add the padding to the image
				outputImage = Scalr.pad(outputImage, paddingSize, Color.WHITE);
				// now we have to crop the image because the padding was added
				// to all sides
				int x = 0, y = 0, width = 0, height = 0;
				if (outputImage.getWidth() > maxSize) {
					// set the correct range
					x = paddingSize;
					y = 0;
					width = outputImage.getWidth() - (2 * paddingSize);
					height = outputImage.getHeight();
				} else if (outputImage.getHeight() > maxSize) {
					// set the correct range
					x = 0;
					y = paddingSize;
					width = outputImage.getWidth();
					height = outputImage.getHeight() - (2 * paddingSize);
				}

				// Crop the image
				if (width > 0 && height > 0) {
					outputImage = Scalr.crop(outputImage, x, y, width, height);
				}
			}

			String name = FilenameUtils.removeExtension(filename);
			String extension = FilenameUtils.getExtension(filename);
			String imageName = name + "_" + new Date().getTime() + "." + extension;

			if (isCeateThumbnail) {
				BufferedImage thumbImg = Scalr.resize(outputImage, Scalr.Method.QUALITY, scaleMode, thumbnailSize);
				thumbImg.flush();
				outputImage.flush();
				File thumnail = new File(resourcesPath + "thumbnail/" + imageName);
				ImageIO.write(thumbImg, extension, thumnail);
			} else {
				outputImage.flush();
				File detail = new File(resourcesPath + "images/" + imageName);
				ImageIO.write(outputImage, extension, detail);
			}

			return imageName;

		} catch (Exception e) {
			System.out.println("Can not create thumbnail :" + e.toString());
			e.printStackTrace();
		}
		return "";
	}
}
