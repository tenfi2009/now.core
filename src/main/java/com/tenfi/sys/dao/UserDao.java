package com.tenfi.sys.dao;

import java.util.List;
import java.util.Map;

import com.tenfi.core.dao.BaseDao;
import com.tenfi.core.model.Page;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.Role;
import com.tenfi.sys.model.User;

public interface UserDao extends BaseDao<User,String>{
	/**
	 * 根据用户登录账号查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	/**
	 * 
	 * <br>描述:</b>根据用户的名称（登录账号）查找该用户拥有的角色列表<br/>
	 * @param name
	 * @return 
	 * List<Role>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Role> findRoles(String userId);
	
	public Map<String,Resource> findRightResourceByUser(User user);
	
	public Page<User> findPage(Page<User> page, List<Map<String, Object>> queryParams, String groupId, Integer isSub);
}
