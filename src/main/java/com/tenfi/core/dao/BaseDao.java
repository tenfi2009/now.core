package com.tenfi.core.dao;

import java.io.Serializable;
import java.util.Map;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.IMutableSearch;
import com.tenfi.core.model.Page;

public interface BaseDao<T,ID extends Serializable> extends GenericDAO<T, ID>{
	public Class<T> getEntityClass();
	
	public Page<T> findPage(Page<T> page, Map queryParams);
	
	public IMutableSearch getFindPageSearch(Map queryParams);
	/**
	 * /**
	 * <b>描述：</b><br/>
	 *  Map propertys = new HashMap();<br/>
		propertys.put("publishTime", publishTime);<br/>
		propertys.put("user_id", user.getId());<br/>
		dao.updateById(User.class, id,propertys);<br/>
	 * @param clazz
	 * @param id
	 * @param propertys
	 */
	public void updateById(Class clazz,ID id,Map<String,?> propertys);
	/**
	 * <b>描述：</b><br/>
	 *  Map updateClause = new HashMap();<br/>
		updateClause.put("publishTime", publishTime);<br/>
		updateClause.put("user_id", user.getId());<br/>
		Map whereClause = new HashMap();<br/>
		whereClause.put("scheduleTemplate_id", scheduleId);<br/>
		
		dao.updateByProperty(User.class, updateClause, whereClause);<br/>
	 * @param clazz
	 * @param updateClause
	 * @param whereClause
	 */
	public void updateByProperty(Class clazz,Map<String,?> updateClause,Map<String,?> whereClause);
	
	/**
	 * <b>描述：</b><br/>
		Map whereClause = new HashMap();<br/>
		whereClause.put("scheduleTemplate_id", scheduleId);<br/>
		dao.deleteByProperty(User.class, whereClause);<br/>
	 * @param clazz
	 * @param whereClause
	 */
	public void deleteByProperty(Class clazz,Map<String,?> whereClause);
	
	public boolean exists(T entity);
	
	public void clear();
	public void evict(Object o);
}
