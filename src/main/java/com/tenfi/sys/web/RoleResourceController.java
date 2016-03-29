package com.tenfi.sys.web;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenfi.core.util.TreeUtils;
import com.tenfi.core.web.AjaxResult;
import com.tenfi.core.web.BaseController;
import com.tenfi.sys.service.RoleResourceService;

@Controller
@RequestMapping(value = "/sys/rr")
public class RoleResourceController extends BaseController{

	@Autowired
	private RoleResourceService service;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(String roleId,String resourceIds) {
		AjaxResult rs = new AjaxResult();
		try{
			List<String> ids = null;
			if(StringUtils.isNotEmpty(resourceIds)){
				String[] a = resourceIds.split(",");
				ids = Arrays.asList(a);
			}
			
			service.save(roleId,ids);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/getAssignResourceTree")
	@ResponseBody
	public String  getAssignResourceTree(String roleId) {
		return TreeUtils.treeToJson(service.getAssignResource(roleId));
	}
}
