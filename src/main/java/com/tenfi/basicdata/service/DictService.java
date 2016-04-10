package com.tenfi.basicdata.service;

import java.util.List;
import java.util.Map;

import com.tenfi.basicdata.model.Dict;
import com.tenfi.core.service.TreeService;

/**
 * @author Administrator
 */
public interface DictService extends TreeService<Dict, Integer>{
	public List<Dict> getChildren(String parentId);
	
	public List<Dict> getSonType(String parentCode);
	
	public String getNameByCode(String parent,String code);
	/**
	 * 
	 * <p>date: 2015年7月24日 下午6:17:13</p>
	 * <p>description:  </p>
	 * @author fang.zhang/张芳
	 * @param
	 * @return
	 */
	public String getBaseDataById(String tableName,String id, String showColumn, String idType);
	/**
	 * 根据parent的code获取子列
	 * <p>date: 2015年9月15日 下午2:53:02</p>
	 * <p>description:  </p>
	 * @author fang.zhang/张芳
	 * @param 比如：accountOperationType
	 * @return {key:1 value分期开户
			    key:2 value分期绑卡...}
	 */
	public Map<String, Object> getNameByParent(String parentCode);
}
