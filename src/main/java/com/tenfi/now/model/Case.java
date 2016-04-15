package com.tenfi.now.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "t_case")
public class Case extends com.tenfi.core.model.Entity<Long>{

	/** 
	 * <p>description:  </p>
	 * @date 2016年4月12日 下午4:19:50
	 */
	private static final long serialVersionUID = -67001084604574995L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="parent",cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Drug> drugList;
	
	private String name;
	
	private Integer sex;
	
	private Integer age;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	/** 费用总额*/
	@Column(name="fee_sum_amount")
	private BigDecimal feeSumAmount;
	
	/**创建时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:SS")
	@Column(name="create_time")
	private Date createTime;
	
	/**最后更新时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:SS")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public BigDecimal getFeeSumAmount() {
		return feeSumAmount;
	}

	public void setFeeSumAmount(BigDecimal feeSumAmount) {
		this.feeSumAmount = feeSumAmount;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Drug> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<Drug> drugList) {
		this.drugList = drugList;
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
