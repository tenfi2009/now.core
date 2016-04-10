package com.tenfi.basicdata.dao;

import com.tenfi.basicdata.model.Dict;
import com.tenfi.core.dao.BaseDao;

/**
 * @author Administrator
 */
public interface DictDao extends BaseDao<Dict, Integer>{
	
	public String getBaseDataById(String tableName, String id, String showColumn, String idType);

}
