/**
 * <b>包名：</b>org.matrix.sys.model<br/>
 * <b>文件名：</b>UserRole.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午10:44:16<br/>
 * <br/>
 */
package com.tenfi.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <b>类名称：</b>UserRole<br/>
 * <b>类描述：</b>用户角色分配表<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午10:44:16<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_user_role")
public class UserRole extends com.tenfi.core.model.Entity<String> implements Serializable {
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public UserRole() {
		super();
	}

	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
