/**
 * <b>包名：</b>com.matrix.sys.dao.impl<br/>
 * <b>文件名：</b>UserRoleDaoImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午11:15:16<br/>
 * <br/>
 */
package com.tenfi.sys.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.sys.dao.UserRoleDao;
import com.tenfi.sys.model.UserRole;

/**
 * <b>类名称：</b>UserRoleDaoImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午11:15:16<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, String> implements UserRoleDao{
	public void deleteByUserId(String userId){
		String hql = "delete UserRole t where t.user.id = :userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.executeUpdate();
		flush();
	}
}
