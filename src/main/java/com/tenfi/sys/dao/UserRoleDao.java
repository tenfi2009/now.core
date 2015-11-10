/**
 * <b>包名：</b>com.matrix.sys.dao<br/>
 * <b>文件名：</b>UserRoleDao.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午11:11:10<br/>
 * <br/>
 */
package com.tenfi.sys.dao;

import com.tenfi.core.dao.BaseDao;
import com.tenfi.sys.model.UserRole;

/**
 * <b>类名称：</b>UserRoleDao<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午11:11:10<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public interface UserRoleDao extends BaseDao<UserRole,String>{
	public void deleteByUserId(String userId);
}
