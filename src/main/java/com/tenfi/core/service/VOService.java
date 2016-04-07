package com.tenfi.core.service;

import java.io.Serializable;
import java.util.List;

import com.tenfi.core.model.VO;
import com.tenfi.sys.model.Status;

public interface VOService<T extends VO, ID extends Serializable> extends BaseService<T, ID> {
	
	public void updateStatus(ID id, Status status);
	
	public void submit(ID id);
	
	public void submit(T vo);
	
	/**
	 * 
	 * <p>date: 2015年5月23日 下午2:49:01</p>
	 * <p>description:  获取所有的有效对象</p>
	 * @author rong.yang/阳荣
	 * @param
	 * @return
	 */
	
	public List<T> getValidObject();
}
