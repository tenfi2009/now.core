/**
 * <b>包名：</b>org.matrix.core.service<br/>
 * <b>文件名：</b>BaseService.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午9:47:12<br/>
 * <br/>
 */
package com.tenfi.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.tenfi.core.dao.BaseDao;
import com.tenfi.core.model.Entity;
import com.tenfi.core.model.Page;

/**
 * <b>类名称：</b>BaseService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午9:47:12<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public interface BaseService<T extends Entity,ID extends Serializable> {
	public BaseDao getDao();
	
	public boolean save(T entity);
	
	public void save(Collection<T> entites);
	
	public boolean exists(T entity);
	
	public void remove(T entity);
	
	public void removeById(ID id);
	
	public void remove(Collection<T> entites);

	public void removeByIds(Collection<ID> ids);
	
	public T get(ID id);
	
	public Collection<T> get(Collection<ID> ids);
	
	public Collection<T> getAll();
	
	public Page<T> findPage(Page<T> page, Map queryParams);
	public void evict(Object o) ;
}
