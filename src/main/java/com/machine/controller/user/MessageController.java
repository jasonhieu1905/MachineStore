package com.machine.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Message;
import com.machine.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/addNewMessage",method = RequestMethod.POST)
	public ModelAndView listAllAccessPage(@ModelAttribute("message") Message message){
		messageService.addNewMessage(message);
		return new ModelAndView("redirect:/contact");
	}
	
}
