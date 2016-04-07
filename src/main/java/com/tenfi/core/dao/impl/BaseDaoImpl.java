package com.tenfi.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.IMutableSearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.matrix.core.exception.BizException;
import com.tenfi.core.dao.BaseDao;
import com.tenfi.core.model.Page;
import com.tenfi.core.util.FilterParser;


public class BaseDaoImpl<T, ID extends Serializable>  extends GenericDAOImpl<T, ID> implements BaseDao<T, ID> {
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public Class<T> getEntityClass(){
		 return persistentClass;
	}


	public Page<T> findPage(Page<T> page, Map queryParams) {
		IMutableSearch search = getFindPageSearch(queryParams);
		//增加排序
		if(null != page.getSorts() && page.getSorts().size() > 0){
			search.getSorts().clear();
			
			search.setSorts(page.getSorts());
		}
		
		search.setMaxResults(page.getPageSize());
		search.setFirstResult(page.getStartIndex());
		SearchResult<T>  sr = searchAndCount(search);
		
		page.setTotalCount(sr.getTotalCount());
		page.setResult(sr.getResult());
		
		return page;
	}
	
	
	public void updateById(Class clazz,ID id,Map<String,?> propertys){
		if(null == id || null == propertys || 0 == propertys.size()){
			throw new BizException("updateById方法参数有误！");
		}
		Object[] values = new Object[propertys.size()+1];
		StringBuffer hql = new StringBuffer();
		hql.append("update ").append(clazz.getName()).append(" t set ");
		int i = 0;
		for(Map.Entry<String, ?> property : propertys.entrySet()){
			hql.append(" t.").append(StringUtils.replace(property.getKey(), "_", ".")).append(" = ?,");
			values[i++] = property.getValue();
		}
		hql.setLength(hql.length()-1);
		
		hql.append(" where t.id = ? ");
		values[i] = id;
		
		logger.debug(hql.toString());
		Query query = getSession().createQuery(hql.toString());
		for(int j = 0; j < values.length; j ++){
			query.setParameter(j, values[j]);
		}
		
		query.executeUpdate();
		flush();
	}
	
	public void updateByProperty(Class clazz,Map<String,?> updateClause,Map<String,?> whereClause){
		if(null == updateClause || 0 == updateClause.size()){
			throw new BizException("updateByProperty方法参数有误！");
		}
		
		StringBuffer hql = new StringBuffer();
		hql.append("update ").append(clazz.getName()).append(" t set ");
		for(Map.Entry<String, ?> property : updateClause.entrySet()){
			hql.append(" t.").append(StringUtils.replace(property.getKey(), "_", ".")).append("= :").append(property.getKey()).append(",");
		}
		hql.setLength(hql.length()-1);
		
		if(null != whereClause && whereClause.size() > 0){
			hql.append(" where ");
			for(Map.Entry<String, ?> property : whereClause.entrySet()){
				hql.append(" t.").append(StringUtils.replace(property.getKey(), "_", ".")).append(" = :").append(property.getKey()).append(" and");
			}
			hql.setLength(hql.length()-3);
		}
		
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(updateClause);
		if(null != whereClause && whereClause.size() > 0){
			query.setProperties(whereClause);
		}
		
		query.executeUpdate();
		flush();
	}

	public void deleteByProperty(Class clazz,Map<String,?> whereClause){
		StringBuffer hql = new StringBuffer();
		hql.append("delete from ").append(clazz.getName()).append(" t");
		
		if(null != whereClause && whereClause.size() > 0){
			hql.append(" where ");
			for(Map.Entry<String, ?> property : whereClause.entrySet()){
				hql.append(" t.").append(StringUtils.replace(property.getKey(), "_", ".")).append(" = :").append(property.getKey()).append(" and");
			}
			hql.setLength(hql.length()-3);
		}
		
		Query query = getSession().createQuery(hql.toString());
		if(null != whereClause && whereClause.size() > 0){
			query.setProperties(whereClause);
		}
		
		query.executeUpdate();
		flush();
	}

	@Override
	public Search getFindPageSearch(Map queryParams) {
		Search search = new Search(persistentClass);
		if(queryParams.containsKey("filters")){
			String filters  = (String)queryParams.get("filters");
			Object[] a = FilterParser.parser(filters);
			search.addFilterCustom((String)a[0], (List)a[1]);
		}
		return search;
	}
	
	public boolean exists(T entity){
		return _exists(entity);
	}
	
	public void clear(){
		this.getSession().clear();
	}
	public void evict(Object o){
		this.getSession().evict(o);
	}
}
