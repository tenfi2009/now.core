package com.tenfi.sys.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenfi.sys.dao.UserDao;
import com.tenfi.sys.model.OnlineUser;
import com.tenfi.sys.model.User;

public class TenfiJdbcRealm extends AuthorizingRealm{
	
	@Autowired
	private UserDao userDao;

	 /** 
     * 为当前登录的Subject授予角色和权限 
     * @see  经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */ 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	 /** 
     * 验证当前登录的Subject 
     * @see  经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */ 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String account = authcToken.getUsername();
		if (StringUtils.isEmpty(account)) {
			throw new AccountException("用户名不能为空！");
		}
		
		User user = userDao.findUserByAccount(account);
		if(null == user){
			throw new UnknownAccountException("用户名或密码不正确！请重新输入。");
		}
		
		if(!user.getIsEnable()){
			throw new LockedAccountException("您的用户已锁定，请联系管理员处理！");
		}
	
		SimpleAuthenticationInfo info = null;
		try {
			String orgId = null;
			String orgName = null;
			if (null != user.getOrg()) {
				orgId = user.getOrg().getId();
				orgName = user.getOrg().getName();
			}
			// 在线用户
			OnlineUser onlineUser = new OnlineUser(user.getId(),user.getAccount(),user.getName(), orgId, orgName);
			info = new SimpleAuthenticationInfo(onlineUser, user.getPassword().toCharArray(), getName());
			// info = new SimpleAuthenticationInfo("user", "password", getName());
		} catch (Exception e) {
			final String message = "There was a exception error while authenticating user [" + account + "]";
			throw new AuthenticationException(message, e);
		} finally {
		}

		return info;
	}

}
