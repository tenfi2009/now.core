package com.tenfi.sys.service;

import java.util.List;
import java.util.Map;

import com.tenfi.core.model.Page;
import com.tenfi.core.service.BasicDataService;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.User;
public interface UserService extends BasicDataService<User,String>{
	
	public User findUserByAccount(String account);
	
	public void updatePassword(String userId, String password);
	
	public Map<String,Resource> getRightResourceByUser(User user);
	
	/**
	 * 验证用户的当前密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean verifyCurrentPassword(String userId,String password);

	@SuppressWarnings("rawtypes")
	public Page<User> findPage(Page<User> page, Map queryParams, Integer isSub);
	/**
	 * 查询不在指定组里的用户
	 * <p>date: 2015年7月16日 下午5:00:24</p>
	 * <p>description:  </p>
	 * @author fang.zhang/张芳
	 * @param
	 * @return
	 */
	public Page<User> findPage(Page<User> page, List<Map<String, Object>> queryParams, String groupId, Integer isSub);
}
