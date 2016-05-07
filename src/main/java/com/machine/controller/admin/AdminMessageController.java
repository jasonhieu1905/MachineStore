package com.machine.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Message;
import com.machine.service.MessageService;
import com.machine.utils.LoginHelper;

@Controller
public class AdminMessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	HttpSession session;

	private static final int activeMenuLeft = 5;

	@RequestMapping(value = "/adminMessage", method = RequestMethod.GET)
	public ModelAndView listAllBanner(ModelAndView model) {
		if (!LoginHelper.isLogin(session)) {
			return new ModelAndView("redirect:/login");
		} else {
			model.addObject("username", session.getAttribute("username"));
		}
		model.addObject("pageId", activeMenuLeft);
		model.addObject("messages",messageService.getAllMessages());
		model.setViewName("adminMessage");
		return model;
	};
	
	@RequestMapping(value="/deleteMessage",method = RequestMethod.POST)
	public @ResponseBody String deleteMessage(@RequestParam final String listId,HttpServletRequest request) {
		String[] messagesId = listId.split(",");
		try{
			for(String messageId : messagesId){
				Message message = messageService.getMessageById(Integer.parseInt(messageId));
				messageService.deleteMessage(message);
			}
		}catch(Exception e){
			return "can not delete message : " + e.toString();
		}
		return "success";
	} 

}
