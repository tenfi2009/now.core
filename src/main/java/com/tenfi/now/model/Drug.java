package com.tenfi.now.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_drug")
public class Drug extends com.tenfi.core.model.Entity<Long> {
	
	/** 
	 * <p>description:  </p>
	 * @date 2016年4月13日 下午2:36:31
	 */
	private static final long serialVersionUID = 5514515413918267881L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Case parent;

	private String drugName;
	
	@Column(name="fee_amount")
	private BigDecimal feeAmount;
	
	/** 数量 */
	private Integer number;
	
	/** 费用总额*/
	@Column(name="fee_sum_amount")
	private BigDecimal feeSumAmount;
	
	/**创建时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="create_time")
	private Date createTime;
	
	/**最后更新时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="update_time")
	private Date updateTime;
	
	/** 更新人员,登录的用户账号 */
	@Column(name="update_user",length=32)
	private String updateUser;

	/** 创建者的用户,登录的用户账号 */
	@Column(length = 32,updatable=false)
	private String creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Case getParent() {
		return parent;
	}

	public void setParent(Case parent) {
		this.parent = parent;
	}

	public BigDecimal getFeeSumAmount() {
		return feeSumAmount;
	}

	public void setFeeSumAmount(BigDecimal feeSumAmount) {
		this.feeSumAmount = feeSumAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
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
	
}
