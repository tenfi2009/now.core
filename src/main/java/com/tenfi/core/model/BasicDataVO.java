package com.tenfi.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicDataVO<ID extends Serializable> extends VO<ID> {

	/** 
	 * <p>description:  </p>
	 * @date 2015年11月5日 下午12:56:41
	 */
	private static final long serialVersionUID = 1L;

	/** 编码 */
	@Column(length = 32)
	private String code;

	/** 名称 */
	@Column(length = 64)
	private String name;

	/** 排序号 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/** 描述 */
	@Column(length = 254)
	private String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
