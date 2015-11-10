package com.tenfi.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TreeVO<ID extends Serializable> extends BasicDataVO<ID> {
	/** 树的层级编码 */
	@Column(name = "level_code", length = 254)
	private String levelCode;
	
	/** 全称 **/
	@Column(name="full_name",length = 254)
	private String fullName;

	/** 树的度 */
	@Column
	private Integer degree;
	
	/** 是否叶子节点 */
	@Column(name = "is_leaf")
	private Boolean leaf;

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/** 配合生成TreeGrid */
	@Transient
	public Serializable getParentId(){
		if(null != getParent()){
			return getParent().getId();
		}
		return null;
	}
	
	@Transient
	public Boolean getLoaded(){
		return !leaf;
	}
	
	@Transient
	public Boolean getExpanded(){
		return !leaf;
	}
	
	/** 配合生成TreeGrid  end */
	
	@SuppressWarnings("rawtypes")
	@JsonIgnore
	public abstract TreeVO getParent();
}
