package com.machine.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.machine.dto.BannerForm;
import com.machine.model.Banner;
import com.machine.service.BannerService;
import com.machine.utils.FileUtils;
import com.machine.utils.LoginHelper;

@Controller
public class AdminBannerController {
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
    ServletContext context;
	
	@Autowired
	private BannerService bannerService;
	
	private static final int activeMenuLeft = 4;
	
	@RequestMapping(value="/adminBanner",method = RequestMethod.GET)
	public ModelAndView listAllBanner(ModelMap model) {
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}else{
			model.addAttribute("username", session.getAttribute("username"));
		}
		model.addAttribute("pageId", activeMenuLeft);
		return new ModelAndView("adminBannerPage","banners",bannerService.listAllBanners());
	}
	
	@RequestMapping(value="/updatePriorityBanner",method = RequestMethod.POST)
	public @ResponseBody String updatePriorityBanner(
			@RequestParam final int id,@RequestParam final int priority) {
		Banner banner = bannerService.getBannerById(id);
		banner.setPriority(priority);
		try{
			bannerService.updateBanner(banner);
		}catch(Exception e){
			return "can not update banner : " + e.toString();
		}
		return "success";
	} 
	
	@RequestMapping(value="/deleteBanner",method = RequestMethod.POST)
	public @ResponseBody String deleteBanner(@RequestParam final String listId,HttpServletRequest request) {
		String[] bannersId = listId.split(",");
		try{
			for(String bannerId : bannersId){
				Banner banner = bannerService.deleteBanner(Integer.parseInt(bannerId));
				FileUtils.removeImageResources(banner.getImage(),request);
			}
		}catch(Exception e){
			return "can not update banner : " + e.toString();
		}
		return "success";
	} 
	
	@RequestMapping(value="/addNewBanner",method = RequestMethod.GET)
	public String addNewBanner(ModelMap model) {
		model.addAttribute("bannerForm", new BannerForm());
		model.addAttribute("pageId", activeMenuLeft);
		return "adminAddNewBanner";
	} 
	
	@RequestMapping(value="/addNewBanner",method = RequestMethod.POST)
	public String acceptAddNewBanner(@ModelAttribute("bannerForm") BannerForm bannerForm,HttpServletRequest request) throws IllegalStateException, IOException{
		String savedDirectory = FileUtils.directoryImage(request);
		List<MultipartFile> files = bannerForm.getFileUpload().getFiles();
		if (null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    multipartFile.transferTo(new File(savedDirectory + fileName));
                    bannerForm.getBanner().setImage(fileName);
                    bannerService.addNewBanner(bannerForm.getBanner());
                }
            }
        }
		return "redirect:/adminBanner";
	}
	
}
