/**
 * <b>包名：</b>com.matrix.sys.service<br/>
 * <b>文件名：</b>UserRoleService.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:38:40<br/>
 * <br/>
 */
package com.tenfi.sys.service;

import java.util.List;

import com.tenfi.core.service.BaseService;
import com.tenfi.sys.model.Role;
import com.tenfi.sys.model.UserRole;

/**
 * <b>类名称：</b>UserRoleService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:38:40<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public interface UserRoleService extends BaseService<UserRole,String>{
	public void save(String userId,List<String> roleIds);
	
	public List<Role> getRoleByUserId(String userId);
}
