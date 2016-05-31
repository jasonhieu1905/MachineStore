package com.machine.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.machine.dto.ContactForm;
import com.machine.model.Contact;
import com.machine.service.ContactService;
import com.machine.utils.FileUtils;
import com.machine.utils.LoginHelper;

@Controller
public class AdminContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	HttpSession session;
	
	private static final int activeMenuLeft = 2;

	@RequestMapping(value = "/admin/contact", method = RequestMethod.GET)
	public String listContact(final Model modelMap) {
		if(!LoginHelper.isLogin(session)){
			return "redirect:/login";
		}else{
			modelMap.addAttribute("username", session.getAttribute("username"));
		}
		Contact contact = contactService.getContact();
		ContactForm contactForm = new ContactForm();
		contactForm.setContact(contact);
		if(contact.getIsoimage().isEmpty()){ 
			modelMap.addAttribute("images", "");
		}else{
			modelMap.addAttribute("images",Arrays.asList(contact.getIsoimage().split(",")));
		}
		modelMap.addAttribute("contactForm",contactForm);
		modelMap.addAttribute("pageId", activeMenuLeft);
		return "contactPage";
	}


	@RequestMapping(value="/admin/contact",method = RequestMethod.POST)
	public synchronized String updateContact(@ModelAttribute("contactForm") ContactForm contactForm,HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException{
		String savedDirectory = FileUtils.directoryImage(request);
		List<MultipartFile> files = contactForm.getFileUpload().getFiles();
		Contact contact = contactForm.getContact();
		String listIsoImage = contact.getIsoimage();
		if(!listIsoImage.isEmpty()){
			listIsoImage += ",";
		}
		//Handler image iso
		if (null != files && files.size() > 0) {
            for (int i=0;i<files.size();i++) {
            	MultipartFile multipartFile = files.get(i);
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                	listIsoImage += fileName;
                	multipartFile.transferTo(new File(savedDirectory + fileName));
                }
                if(i!= files.size()-1){
                	listIsoImage += ",";
                }
            }
            listIsoImage = FileUtils.removeLastCharacterIfComma(listIsoImage);
            contact.setIsoimage(listIsoImage);
        }
		
		
		//Handle image banner footer
		List<MultipartFile> bannerFooterFile = contactForm.getBannerFooterUpload().getFiles();
		if(null != bannerFooterFile && bannerFooterFile.size() > 0){
			for (int i=0;i<bannerFooterFile.size();i++) {
            	MultipartFile multipartFile = bannerFooterFile.get(i);
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                	listIsoImage += fileName;
                	multipartFile.transferTo(new File(savedDirectory + fileName));
                	contact.setBannerfooter(fileName);
                }
            }
		}
		
		contactService.updateContact(contact);
		try {
            wait(2000);
        } catch (Exception e) {
        	System.out.println("Error while upload contact");
        }
		return "redirect:/admin/contact";
	}
	
	@RequestMapping(value = "/contact/deleteIsoImage", method = RequestMethod.POST)
	public @ResponseBody void deleteIsoImage(@RequestParam final String imageDeleted,HttpServletRequest request) throws InterruptedException{
		Contact contact = contactService.getContact();
		String newIsoImage = "";
		String[] isoImages = contact.getIsoimage().split(",");
		for(int i=0;i<isoImages.length;i++){
			if(!isoImages[i].equals(imageDeleted)){
				newIsoImage += isoImages[i];
				if(i != isoImages.length-1){
					newIsoImage += ",";
				}
			}
			
		}
		newIsoImage = FileUtils.removeLastCharacterIfComma(newIsoImage);
		contact.setIsoimage(newIsoImage);
		contactService.updateContact(contact);
		FileUtils.removeImageResources(imageDeleted, request);
		try {
            wait(1000);
        } catch (Exception e) {
        	System.out.println("Error while upload contact");
        }
	}

}
