package com.tenfi.now.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenfi.core.model.Page;
import com.tenfi.core.web.AjaxResult;
import com.tenfi.core.web.BaseController;
import com.tenfi.now.model.Case;
import com.tenfi.now.model.Drug;
import com.tenfi.now.service.DrugService;

@Controller
@RequestMapping(value = "/module/drug")
public class DrugController extends BaseController{

	@Autowired
	private DrugService service;
	
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			@RequestParam("userId") String userId) {
		
		Page<Drug> page = new Page<Drug>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
//		page.getSorts().add(new Sort("createTime",false));
		
		
		Map queryParams = new HashMap();
		
		String rules = "[{\"t\":\"l\",\"f\":\"parent.id\",\"op\":\"eq\",\"v\":\""+userId+"\"}]";
		String filters = "{\"groupOp\":\"AND\",\"rules\":"+rules+"}";
		queryParams.put("filters", filters);
		
		page = service.findPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/editOrDel", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult edit(@ModelAttribute("drug") Drug drug, String oper, Long parentId) {
		AjaxResult rs = new AjaxResult();
		if(oper.equals("edit")){
			Case case1 = new Case();
			case1.setId(parentId);
			drug.setParent(case1);
			if (0 == drug.getId().intValue()) {
				drug.setCreator(getCurrentUser().getName());
//				drug.setCreateTime(new Date());
			} else {
				drug.setUpdateUser(getCurrentUser().getName());
//				drug.setUpdateTime(new Date());
			}
			service.save(drug);	
		}else if(oper.equals("del")){
			return rs;
		}
		return rs;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(ModelMap model, Long userId) {
		model.put("userId", userId);
		return "now/drug/list";
	}
}
