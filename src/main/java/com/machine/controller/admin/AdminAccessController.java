package com.machine.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private static final int activeMenuLeft = 3;
	
	@RequestMapping(value="/adminAllAccess",method = RequestMethod.GET)
	public ModelAndView listAllAccessPage(ModelAndView modelMap){
		if(!LoginHelper.isLogin(session)){
			return new ModelAndView("redirect:/login");
		}else{
			modelMap.addObject("username", session.getAttribute("username"));
		}
		
		List<Access> accesses = accessService.listAllAccess();
		modelMap.addObject("accesses", accesses);
		modelMap.setViewName("adminAccessPage");
		modelMap.addObject("pageId", activeMenuLeft);
		return modelMap;
	}
	
	@RequestMapping(value="/adminAccessByTime",method = RequestMethod.GET)
	public ModelAndView changeAccessByPeriodOfTime(@RequestParam final String startDate,@RequestParam final String endDate,ModelAndView modelMap){
		long longStartDate = Long.valueOf(startDate);
		long longEndDate = Long.valueOf(endDate); 
		List<Access> accesses = accessService.listAllAccessByPeriodTime(new Date(longStartDate), new Date(longEndDate));
		modelMap.addObject("accesses", accesses);
		modelMap.setViewName("adminAccessPage");
		modelMap.addObject("pageId", activeMenuLeft);
		modelMap.addObject("startDate",startDate);
		modelMap.addObject("endDate",endDate);
		return modelMap;
	}
}
