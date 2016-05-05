package com.machine.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Access;
import com.machine.model.Contact;
import com.machine.service.ContactService;
import com.machine.utils.LoginHelper;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	
	@RequestMapping(value="/contact",method = RequestMethod.GET)
	public ModelAndView listAllAccessPage(ModelAndView modelMap){
		Contact contact = contactService.getContact();
		modelMap.setViewName("contact");
		modelMap.addObject("contact",contact);
		return modelMap;
	}
}
