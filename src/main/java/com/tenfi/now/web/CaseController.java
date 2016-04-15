package com.tenfi.now.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.tenfi.core.model.Page;
import com.tenfi.core.web.AjaxResult;
import com.tenfi.core.web.BaseController;
import com.tenfi.now.model.Case;
import com.tenfi.now.service.CaseService;

@Controller
@RequestMapping(value = "/module/case")
public class CaseController extends BaseController {
	
	@Autowired
	private CaseService service;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "now/case/list";
	}
	
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		
		Page<Case> page = new Page<Case>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		page.getSorts().add(new Sort("createTime",false));
		
		
		Map queryParams = new HashMap();
		
		page = service.findPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(String parentId) {
		return "now/case/addNew";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String id) {
		return "now/case/edit";
	}
	
	@RequestMapping(value="/save", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public AjaxResult save(Case Case) {
		AjaxResult rs = new AjaxResult();
		try{
			service.save(Case);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult remove(@PathVariable Long id) {
		AjaxResult rs = new AjaxResult();
		if(null == id){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除时参数id不能为空！");
			return rs;
		}
		try{
			service.removeById(id);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("删除成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除失败！<br/>" + e.getMessage());
		}
		
		return rs;
	}
}
