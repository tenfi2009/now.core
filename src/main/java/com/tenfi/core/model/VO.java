package com.tenfi.core.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tenfi.enums.EnumTypeSerialize;
import com.tenfi.enums.Status;

/**
 * 
 * <B>描述：</B><BR>
 * 值对象最高基类 <BR>
 * 
 * @author rong yang
 * @version 1.0 2013-06-22
 * 
 */
@MappedSuperclass
public abstract class VO<ID extends Serializable> extends Entity<ID>{
	/** 
	 * <p>description:  </p>
	 * @date 2015年11月5日 下午12:56:13
	 */
	private static final long serialVersionUID = 1L;

	/** 创建时间 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "create_time",updatable=false)
	private Timestamp createTime;

	/** 最近一次更新时间 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "update_time")
	private Timestamp updateTime;
	
	/** 更新人员,登录的用户账号 */
	@Column(name="update_user",length = 36)
	private String updateUser;

	/** 创建者的用户,登录的用户账号 */
	@Column(length = 36,updatable=false)
	private String creator;

	/**
	 * 状态
	 * 
	 * @see com.tenfi.enums.matrix.core.common.enums.Status
	 */
	@JsonSerialize(using = EnumTypeSerialize.class)// 页面展示时显示枚举的displayName
	@Enumerated(EnumType.ORDINAL)// 存入数据库时,存的是枚举的序号从0开始
	private Status status;

	

	public VO() {
		super();
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
