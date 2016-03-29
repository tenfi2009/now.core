package com.tenfi.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tenfi.core.web.BaseController;
import com.tenfi.sys.model.OnlineUser;
import com.tenfi.sys.service.ResourceService;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {
	@Autowired
	private ResourceService resourceService;

	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping( method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		OnlineUser onlineUser = getCurrentUser();
		String sysNavAccordion = resourceService.getSysNavAccordion(onlineUser.getId());
		
		model.addAttribute("sysNavAccordion",sysNavAccordion);
		model.addAttribute("userId",onlineUser.getId());
		logger.info("----------home");
		return "/home/index";
	}
}
