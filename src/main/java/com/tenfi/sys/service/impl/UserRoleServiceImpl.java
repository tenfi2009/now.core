/**
 * <b>包名：</b>com.matrix.sys.service.impl<br/>
 * <b>文件名：</b>UserRoleServiceImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:39:10<br/>
 * <br/>
 */
package com.tenfi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.tenfi.core.dao.BaseDao;
import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.sys.dao.UserRoleDao;
import com.tenfi.sys.model.Role;
import com.tenfi.sys.model.User;
import com.tenfi.sys.model.UserRole;
import com.tenfi.sys.service.UserRoleService;

/**
 * <b>类名称：</b>UserRoleServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:39:10<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole,String> implements UserRoleService {
	@Autowired
	private UserRoleDao dao;
	
	
	@Override
	public UserRoleDao getDao() {
		return dao;
	}
	
	public List<Role> getRoleByUserId(String userId){
		List<Role> roleList = null;
		Search search = new Search(UserRole.class);
		search.addField("role");
		search.addFilterEqual("user.id", userId);
		search.setResultMode(ISearch.RESULT_ARRAY);
		List<Object[]> list = dao.search(search);
		if(null != list && list.size() > 0){
			roleList = new ArrayList<Role>(list.size());
			for (Object[] array : list) {
				roleList.add((Role)array[0]);
			}
		}else{
			roleList = new ArrayList<Role>();
		}
		return roleList;
	}
	public void save(String userId,List<String> roleIds){
		//先删除该用户的所有角色
		dao.deleteByUserId(userId);
		
		if(null == roleIds || 0 == roleIds.size()){
			return;
		}
		
		//插入新分配的角色
		UserRole ur = null;
		Role role = null;
		User user = new User();
		user.setId(userId);

		for(int i=0,size = roleIds.size(); i < size; i++){
			ur = new UserRole();
			ur.setUser(user);
			
			role = new Role();
			role.setId(roleIds.get(i));
			
			ur.setRole(role);
			
			dao.save(ur);
		    if ( i % 20 == 0 ) {
		        dao.flush();
		        dao.clear();
		    }
		}
	}
}
