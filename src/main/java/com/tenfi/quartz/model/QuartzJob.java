package com.tenfi.quartz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * <p>date: 2016年1月19日 下午2:09:34</p> 
 * <p>description:  </p>
 * @package com.matrix.ams.quartz.model
 * @author fang.zhang/张芳
 *
 */
@Entity
@Table(name = "t_quartz_job")
public class QuartzJob extends com.tenfi.core.model.Entity<Long> {

	/** 
	 * <p>description:  </p>
	 * @date 2016年4月22日 下午3:05:27
	 */
	private static final long serialVersionUID = -2367396396131916239L;
	
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/** 作业组**/
	@Column(name="job_group")
	private String jobGroup;
	
	/** 作业名称 */
	@Column(name="job_name")
	private String jobName;
	
	/** 作业描述 */
	@Column(name="description")
	private String description;
	
	/** 实现类 */
	@Column(name="class_name")
	private String className;
	
	/** 其它参数 */
	@Column(name="params")
	private String params;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}