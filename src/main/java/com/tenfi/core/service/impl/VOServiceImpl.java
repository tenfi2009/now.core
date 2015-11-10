package com.tenfi.core.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.googlecode.genericdao.search.Search;
import com.tenfi.core.model.VO;
import com.tenfi.core.service.VOService;
import com.tenfi.sys.model.Status;
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class VOServiceImpl <T extends VO, ID extends Serializable> extends  BaseServiceImpl<T, ID> implements VOService<T,ID>{
	@Override
	public boolean save(T entity) {
		if(!exists(entity)){
			setAddNew(entity);
		}else{
			setUpdate(entity);
		}
		return super.save(entity);
	}

	/**
	 * 新增时设置默认的属性
	 * @param entity
	 */
	protected void setAddNew(T entity) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setStatus(Status.SAVE);
		entity.setCreateTime(now);
		entity.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
		setDefaultValue(entity);
	}
	
	/**
	 * 设置默认值
	 */
	protected void setDefaultValue(T entity){
		
	}
	/**
	 * 更新时时设置默认的属性
	 * @param entity
	 */
	protected void setUpdate(T entity) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setUpdateTime(now);
		entity.setUpdateUser(getCurrentUser().getName());
	}
	

	public void updateStatus(ID id, Status status){
		Map propertys = new HashMap();
		propertys.put("status", status);
		getDao().updateById(getDao().getEntityClass(), id, propertys);
	}
	
	/**
	 * 
	 * <p>date: 2015年5月23日 下午2:49:01</p>
	 * <p>description:  获取所有的有效对象</p>
	 * @author rong.yang/阳荣
	 * @param
	 * @return
	 */
	
	public List<T> getValidObject(){
		Search search = new Search(getDao().getEntityClass());
		search.addFilterEqual("status", Status.VALID);
		return getDao().search(search);
	}
	
	public void submit(ID id){
		T vo = get(id);
		submit(vo);
	}
	
	public void submit(T vo){
		check(vo);
		beforeSubmit(vo);
		updateStatus((ID)vo.getId(),Status.VALID);
		afterSubmit(vo);
	}
	
	protected void check(T vo) {

	}
	
	protected void beforeSubmit(T entity) {

	}

	protected void afterSubmit(T entity) {

	}
}
