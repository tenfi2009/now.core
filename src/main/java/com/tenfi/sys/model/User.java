package com.tenfi.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tenfi.core.model.BasicDataVO;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_user")
public class User extends BasicDataVO<String> implements Serializable {
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	/** 登录系统的账号 */
	@Column(length = 32,nullable=false)
	private String account;

	/** 密码 */
	@Column(length = 254 , nullable=false)
	@JsonIgnore
	private String password;
	
	@Column(length = 64)
	@JsonIgnore
	private String salt;

	/** 是否启用 */
	@Column(name = "is_enable")
	private Boolean isEnable;

	/** 所属组织 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Organization org;
	
	/** 手机号 */
	@Column(length=24)
	private String mobile;
	
	/** 联系电话 */
	@Column(length = 24)
	private String phone;

	/** 邮箱 */
	@Column(length = 64)
	private String email;


	public User() {
		super();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
