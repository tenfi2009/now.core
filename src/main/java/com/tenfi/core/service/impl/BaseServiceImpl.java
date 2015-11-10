/**
 * <b>包名：</b>org.matrix.core.service.impl<br/>
 * <b>文件名：</b>BaseServiceImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午9:47:46<br/>
 * <br/>
 */
package com.tenfi.core.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.tenfi.core.model.Entity;
import com.tenfi.core.model.Page;
import com.tenfi.core.service.BaseService;
import com.tenfi.sys.model.OnlineUser;

/**
 * <b>类名称：</b>BaseServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午9:47:46<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseServiceImpl<T extends Entity, ID extends Serializable> implements BaseService<T, ID> {
	public boolean save(T entity) {
		beforeSave(entity);
		boolean rs = getDao().save(entity);
		getDao().flush();
		afterSave(entity);
		return rs;
	}

	public void save(Collection<T> entites) {
		if (null != entites && entites.size() > 0) {
			for (T a : entites) {
				save(a);
			}
		}
	}

	public T get(ID id) {
		return (T) getDao().find(id);
	}

	public Collection<T> get(Collection<ID> ids) {
		if (null == ids || 0 == ids.size()) {
			return null;
		}

		ID[] aa = (ID[]) new Object[ids.size()];
		aa = ids.toArray(aa);
		T[] rs = (T[]) getDao().find(aa);

		return Arrays.asList(rs);
	}

	public Collection<T> getAll() {
		return getDao().findAll();
	}

	public Page<T> findPage(Page<T> page, Map queryParams) {
		return getDao().findPage(page, queryParams);
	}

	public void remove(T entity) {
		beforeRemove(entity);
		getDao().remove(entity);
		getDao().flush();
		afterRemove(entity);
	}

	public void removeById(ID id) {
		T a = get(id);
		remove(a);
	}

	public void remove(Collection<T> entites) {
		if (null != entites && entites.size() > 0) {
			for (T a : entites) {
				remove(a);
			}
		}
	}

	public void removeByIds(Collection<ID> ids) {
		if (null == ids || 0 == ids.size()) {
			return;
		}

		ID[] aa = (ID[]) new Object[ids.size()];
		aa = ids.toArray(aa);
		T[] rs = (T[]) getDao().find(aa);
		for (T a : rs) {
			remove(a);
		}
	}
	
	protected OnlineUser getCurrentUser() {
		return (OnlineUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	public boolean exists(T entity){
		return getDao().exists(entity);
	}

	protected void beforeSave(T entity) {

	}

	protected void afterSave(T entity) {

	}

	protected void beforeRemove(T entity) {

	}

	protected void afterRemove(T entity) {

	}
	
	public void evict(Object o) {
		getDao().evict(o);
	}

}
