/**
 * <b>包名：</b>com.matrix.sys.dao<br/>
 * <b>文件名：</b>RoleResourceDao.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午11:10:40<br/>
 * <br/>
 */
package com.tenfi.sys.dao;

import com.tenfi.core.dao.BaseDao;
import com.tenfi.sys.model.RoleResource;

/**
 * <b>类名称：</b>RoleResourceDao<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午11:10:40<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public interface RoleResourceDao extends BaseDao<RoleResource,String>{
	public void deleteByRoleId(String roleId);
}
