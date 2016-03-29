/**
 * <b>包名：</b>org.matrix.sys.model<br/>
 * <b>文件名：</b>Resource.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午10:17:02<br/>
 * <br/>
 */
package com.tenfi.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tenfi.core.model.TreeVO;

/**
 * <b>类名称：</b>Resource<br/>
 * <b>类描述：</b>系统可访问资源<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午10:17:02<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_resource")
public class Resource extends TreeVO<String> implements Serializable{
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	/** 上级资源 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Resource parent;
	
	/** 资源类型 */
	@JsonSerialize(using = EnumTypeSerialize.class)
	@Enumerated(EnumType.ORDINAL)
	ResourceType type;

	/** 资源图标 */
	@Column(length = 32)
	@JsonIgnore
	private String icon;
	
	/** 资源URL */
	@Column(length = 255)
	private String uri;
	
	
	/** 资源权限标识 */
	@Column(length = 255)
	private String permission;
	
	
	/** 超级管理员权限 */
	@Column(name = "is_super")
	private Boolean isSuper;
	

	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}


	public Resource getParent() {
		return parent;
	}


	public void setParent(Resource parent) {
		this.parent = parent;
	}


	public ResourceType getType() {
		return type;
	}


	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
	}


	public Boolean getIsSuper() {
		return isSuper;
	}


	public void setIsSuper(Boolean isSuper) {
		this.isSuper = isSuper;
	}
}
