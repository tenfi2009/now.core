package com.tenfi.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.googlecode.genericdao.search.Search;
import com.matrix.core.exception.BizException;
import com.tenfi.core.model.Page;
import com.tenfi.core.service.impl.BasicDataServiceImpl;
import com.tenfi.sys.dao.OrganizationDao;
import com.tenfi.sys.dao.UserDao;
import com.tenfi.sys.model.Organization;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.User;
import com.tenfi.sys.service.OrganizationService;
import com.tenfi.sys.service.UserService;

@Service
public class UserServiceImpl extends BasicDataServiceImpl<User,String> implements UserService{
	@Autowired
	private UserDao dao;
	
	@Autowired
	PasswordService passwordService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	public UserDao getDao(){
		return dao;
	}
	
	@Override
	protected void check(User user) {
		//校验用户账号是否重复
		Search search = new Search(User.class);
		search.addFilterEqual("account", user.getAccount());
		search.addFilterNotEqual("id", user.getId());
		
		List<User> users = dao.search(search);
		if(null != users && users.size() > 0){
			throw new BizException("用户账号【"+ user.getAccount() +"】已经存在，不能重复！");
		}
	}
	

	@Override
	public User findUserByAccount(String account){
		return dao.findUserByAccount(account);
	}

	@Override
	protected void setAddNew(User user) {
		super.setAddNew(user);
		//默认启用
		user.setIsEnable(true);
		//password加密
		String encryptedValue = passwordService.encryptPassword(user.getPassword());
		user.setPassword(encryptedValue);
	}
	
	public void updatePassword(String userId, String password) {
		User user = get(userId);
		//password加密
		String encryptedValue = passwordService.encryptPassword(password);
		user.setPassword(encryptedValue);
		save(user);
	}
	
	public Map<String,Resource> getRightResourceByUser(User user){
		return dao.findRightResourceByUser(user);
	}
	
	/**
	 * 验证用户的当前密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean verifyCurrentPassword(String userId,String password){
		User user = dao.find(userId);
		return passwordService.passwordsMatch(password, user.getPassword());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<User> findPage(Page<User> page, Map queryParams, Integer isSub) {
		if(isSub != null && isSub.intValue() == 1){
			String filters = (String) queryParams.get("filters");
			filters = HtmlUtils.htmlUnescape(filters);
			if(StringUtils.isNotEmpty(filters)){
				Gson gson = new Gson();
				Map map = gson.fromJson(filters, Map.class);
				ArrayList rules = (ArrayList) map.get("rules");
				ArrayList newRules = new ArrayList();
				for(Object rule : rules){
					String ruleStr = rule.toString();
					Map ruleMap = gson.fromJson(ruleStr, Map.class);
					Object name = ruleMap.get("f");
					if(name != null && "org.id".equals(name.toString())){
						String orgId = ruleMap.get("v").toString();
						
						Organization org = this.organizationService.get(orgId);
						String levelCode = org.getLevelCode();
						Search search = new Search();
						search.addFilterLike("levelCode", levelCode+'%');
						
						if(StringUtils.isNotEmpty(levelCode)){
							Map orgMap = new HashMap();
							orgMap.put("t", "s");
							orgMap.put("f", "org.levelCode");
							orgMap.put("op", "bw");
							orgMap.put("v", levelCode);
							String orgRule = gson.toJson(orgMap);
							newRules.add(orgRule);
						}else{
							newRules.add(rule);
						}
						
					}else{
						newRules.add(rule);
					}
				}
				map.put("rules", newRules);
				StringBuffer newFitler = new StringBuffer();
				newFitler.append("{");
				Set<String> keys = map.keySet();
				for(String key : keys){
					Object value = map.get(key);
					newFitler.append("\"").append(key).append("\":");
					if(value instanceof String){
						newFitler.append("\"").append(value).append("\"");
					}else{
						newFitler.append(value);
					}
					newFitler.append(",");
				}
				String newFitlerStr = newFitler.toString();
				newFitlerStr = newFitlerStr.substring(0, newFitlerStr.length()-1);
				newFitlerStr = newFitlerStr+"}";
				queryParams.put("filters", newFitlerStr);
				return this.dao.findPage(page, queryParams);
			}
			return this.dao.findPage(page, queryParams);
		}else{
			return this.dao.findPage(page, queryParams);
		}
	}
	
	@Override
	public Page<User> findPage(Page<User> page, List<Map<String, Object>> queryParams, String groupId, Integer isSub) {
		
		return this.dao.findPage(page, queryParams, groupId, isSub);
	}
	
}
