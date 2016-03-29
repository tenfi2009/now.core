package com.tenfi.sys.web;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenfi.core.web.AjaxResult;
import com.tenfi.core.web.BaseController;
import com.tenfi.sys.service.UserRoleService;

/**
 * @description 用户角色管理
 * @author rong yang
 *
 */
@Controller
@RequestMapping(value = "/sys/ur")
public class UserRoleController extends BaseController{

	@Autowired
	private UserRoleService service;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(String userId,String roleIds) {
		AjaxResult rs = new AjaxResult();
		try{
			List<String> ids = null;
			if(StringUtils.isNotEmpty(roleIds)){
				String[] a = roleIds.split(",");
				ids = Arrays.asList(a);
			}
			
			service.save(userId,ids);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
}
