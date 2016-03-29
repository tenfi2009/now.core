/**
 * <b>包名：</b>com.matrix.sys.service.impl<br/>
 * <b>文件名：</b>RoleResourceServiceImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:38:03<br/>
 * <br/>
 */
package com.tenfi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.sys.dao.RoleResourceDao;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.Role;
import com.tenfi.sys.model.RoleResource;
import com.tenfi.sys.service.RoleResourceService;

/**
 * <b>类名称：</b>RoleResourceServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:38:03<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource,String> implements RoleResourceService {

	@Autowired
	private RoleResourceDao dao;
	
	
	@Override
	public RoleResourceDao getDao() {
		return dao;
	}
	
	public void save(String roleId,List<String> resourceIds){
		//先删除该角色的所有资源,
		dao.deleteByRoleId(roleId);
		
		if(null == resourceIds || 0 == resourceIds.size()){
			return;
		}
		
		//插入新分配的资源
		RoleResource rr = null;
		Resource res = null;
		Role role = new Role();
		role.setId(roleId);
		for(int i=0,size = resourceIds.size(); i < size; i++){
			rr = new RoleResource();
			res = new Resource();
			res.setId(resourceIds.get(i));
			rr.setResource(res);
			rr.setRole(role);
			dao.save(rr);
		    if ( i % 20 == 0 ) {
		        dao.flush();
		        dao.clear();
		    }
		}
	}
	
	/**
	 * 描述:分配给角色的资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> getAssignResource(String roleId){
		List<Resource> resList = null;
		Search search = new Search(RoleResource.class);
		search.addField("resource");
		search.addFilterEqual("role.id", roleId);
		search.setResultMode(ISearch.RESULT_ARRAY);
		List<Object[]> list = dao.search(search);
		if(null != list && list.size() > 0){
			resList = new ArrayList<Resource>(list.size());
			for (Object[] array : list) {
				resList.add((Resource)array[0]);
			}
		}else{
			resList = new ArrayList<Resource>();
		}
		return resList;
	}

}
