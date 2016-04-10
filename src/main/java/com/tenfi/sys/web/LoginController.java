package com.tenfi.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author rong yang
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping
	public String login() {
		logger.info("------------logining");
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(HttpServletRequest request,@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		//是否已经登录系统
		Subject currentUser = SecurityUtils.getSubject();
		
        if (currentUser.isAuthenticated() && currentUser.getPrincipal().equals(userName)) {
        	return  "redirect:/";
        } else if (currentUser.isAuthenticated()) {
        	currentUser.logout();
        }
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		
		return "login/login";
	}
}
