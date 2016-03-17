package com.machine.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.machine.model.Contact;
import com.machine.service.ContactService;

@Controller
public class AdminContactController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String listContact(ModelMap modelMap){
		Contact contact = contactService.getContact();
		modelMap.addAttribute("contact",contact);
		return "contactPage";
	}
	
	
	
	
}
