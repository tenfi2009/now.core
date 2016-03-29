/**
 * <b>包名：</b>org.matrix.sys.model<br/>
 * <b>文件名：</b>OnlineUser.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-27-下午10:46:17<br/>
 * <br/>
 */
package com.tenfi.sys.model;

import java.io.Serializable;

/**
 * <b>类名称：</b>OnlineUser<br/>
 * <b>类描述：</b>在线用户<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-27 下午10:46:17<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@SuppressWarnings("serial")
public class OnlineUser implements Serializable {
	/** 用户ID **/
	private String id;
	/** 登录账号 **/
	private String account;
	/** 用户显示名称 **/
	private String name;
	
	/** 用户所属组织ID **/
	private String orgId;
	
	/** 用户所属组织名称 **/
	private String orgName;

	public OnlineUser() {
		super();
	}

	public OnlineUser(String id, String account, String name, String orgId, String orgName) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.orgId = orgId;
		this.orgName = orgName;
	}

	public String getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OnlineUser other = (OnlineUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
