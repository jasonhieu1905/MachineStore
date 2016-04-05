package com.machine.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.machine.model.Access;
import com.machine.service.AccessService;
import com.machine.utils.LoginHelper;

@Controller
public class AdminAccessController {

	@Autowired
	HttpSession session;
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping(value="/adminAllAccess/{id}",method = RequestMethod.GET)
	public ModelAndView listAllAccessPage(ModelAndView modelMap,@PathVariable int id){
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}else{
			modelMap.addObject("username", session.getAttribute("username"));
		}
		
		List<Access> accesses = accessService.listAllAccess();
		modelMap.addObject("accesses", accesses);
		modelMap.setViewName("adminAccessPage");
		modelMap.addObject("id-enable", id);
		return modelMap;
	}
}
