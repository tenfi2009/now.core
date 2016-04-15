package com.tenfi.basicdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.matrix.core.exception.BizException;
import com.tenfi.basicdata.dao.DictDao;
import com.tenfi.basicdata.model.Dict;
import com.tenfi.basicdata.service.DictService;
import com.tenfi.core.service.impl.TreeServiceImpl;
import com.tenfi.enums.Status;

/**
 * @author Administrator
 */
@Service
public class DictServiceImpl extends TreeServiceImpl<Dict, Integer> implements DictService{
	@Autowired
	private DictDao dao;
	
	@Override
	public DictDao getDao() {
		return dao;
	}
	@Override
	public List<Dict> getSonType(String code) {
		Search search = new Search(Dict.class);
		search.addFilterEqual("parent.code", code);
		search.addFilterEqual("status", Status.VALID);
		return this.dao.search(search);
	}

	
	protected void beforeSave(Dict dict) {
			Search search = new Search(Dict.class);
			if(null != dict.getParent()){
				search.addFilterEqual("parent.id", dict.getParent().getId());
			}
			search.addFilterEqual("code", dict.getCode());
			if(null != dict.getId()){
				search.addFilterNotEqual("id", dict.getId());
			}
			
			List<Dict> list = dao.search(search);
			if(null!=list&&list.size()>0){
				throw new BizException("字典编码重复！");
			}
	}
	@Override
	public String getNameByCode(String parent, String code) {
		Search search = new Search(Dict.class);
		search.addFilterEqual("parent.code", parent);
		search.addFilterEqual("code", code);
		Dict dict = dao.searchUnique(search);
		return dict.getName();
	}
	
	public Map<String, Object> getNameByParent(String parentCode){
		Search search = new Search(Dict.class);
		search.addFilterEqual("parent.code", parentCode);
		List<Dict> list = dao.search(search);
		Map<String, Object> map = new HashMap<String, Object>();
		for(Dict dict : list){
			String code = dict.getCode();
			String name = dict.getName();
			map.put(code, name);
		}
		return map;
	}
	@Override
	public String getBaseDataById(String tableName, String id, String showColumn, String idType) {
		
		return dao.getBaseDataById(tableName, id, showColumn, idType);
	}
}
