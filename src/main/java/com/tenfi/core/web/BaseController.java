package com.tenfi.core.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.tenfi.sys.model.OnlineUser;

public class BaseController {
	
	public BaseController() {
		super();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		SimpleDateFormat tsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tsFormat.setLenient(false);
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(tsFormat, true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	/**
	 * 获取当前用户
	 * @return
	 */
	protected OnlineUser getCurrentUser() {
		return (OnlineUser) SecurityUtils.getSubject().getPrincipal();
	}
	
}
