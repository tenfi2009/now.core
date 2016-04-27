package com.tenfi.quartz.model;

import java.sql.Time;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "t_quartz_job_scheduler")
public class QuartzJobScheduler extends com.tenfi.core.model.Entity<Long> {
	
	/** 
	 * <p>description:  </p>
	 * @date 2016年4月22日 下午3:10:50
	 */
	private static final long serialVersionUID = 8232503285185429759L;

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/** 作业id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private QuartzJob job;
	
	/** 计划名称 */
	@Column(name="scheduler_name")
	private String schedulerName;
	
	/** 计划类型  1:一次,2:间隔,3:每天,4:每周,5:每月,6:Cron表达式*/
	@Column(name="type")
	private Integer type;
	
	/** 开始日期 */
	@Column(name="start_date")
	private Date startDate;
	
	/** 运行时间*/
	@Column(name="run_time")
	private Time runTime;
	
	/** 间隔时间*/
	@Column(name="time_interval")
	private Integer timeInterval;
	
	/** 间隔时间单位 1:秒,2:分,3:小时*/
	@Column(name="unit")
	private Integer unit;
	
	/** 重复次数*/
	@Column(name="repeat_count")
	private Integer repeatCount;
	
	/** 星期/日    周     是     1-7,星期日是1，星期六是7 */
	@Column(name="day")
	private String day;
	
	/** 表达式*/
	@Column(name="cron")
	private String cron;
	
	/** 最后更新时间*/
	@Column(name = "update_time")
	private Date updateTime;
	
	/** 最后更新人员*/
	@Column(name="update_user")
	private String updateUser;
	
	@Transient
	private String desc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public QuartzJob getJob() {
		return job;
	}
	public void setJob(QuartzJob job) {
		this.job = job;
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
	public String getSchedulerName() {
		return schedulerName;
	}
	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public Time getRunTime() {
		return runTime;
	}
	public void setRunTime(Time runTime) {
		this.runTime = runTime;
	}
	public Integer getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getDesc() {
		StringBuffer desc = new StringBuffer();
		String runTimeDes = "";
		if(null!=getRunTime()){
			runTimeDes = this.getRunTime().toString();
			runTimeDes.length();
			runTimeDes = runTimeDes.substring(0, runTimeDes.length()-3);
		}
		if(1==type){
			if(null != this.getStartDate()){
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				desc.append(formatter.format(this.getStartDate())).append("执行一次");
			}
		}else if(2==type){
			if(1==this.getUnit()){
				desc.append("每隔").append(this.getTimeInterval()).append("秒执行一次");
			}else if(2==this.getUnit()){
				desc.append("每隔").append(this.getTimeInterval()).append("分钟执行一次");
			}else if(3==this.getUnit()){
				desc.append("每隔").append(this.getTimeInterval()).append("小时执行一次");
			}else{
				desc.append("每隔").append(this.getTimeInterval()+this.getUnit()).append("执行一次");
			}
			if(this.getRepeatCount()!=null){
				desc.append(",重复执行"+this.getRepeatCount()+"次");
			}
		}else if(3==type){
			desc.append("每天"+runTimeDes);
		}else if(4==type){
			desc.append("每周");
			if(day.contains("2")){
				desc.append("一,");
			}
			if(day.contains("3")){
				desc.append("二,");
			}
			if(day.contains("4")){
				desc.append("三,");
			}
			if(day.contains("5")){
				desc.append("四,");
			}
			if(day.contains("6")){
				desc.append("五,");
			}
			if(day.contains("7")){
				desc.append("六,");
			}
			if(day.contains("1")){
				desc.append("日,");	
			}

			
			if(desc.indexOf(",") > -1){
				desc = desc.deleteCharAt(desc.length()-1);
			}
			
			desc.append(" "+runTimeDes+"执行");

		}else if(5==type){
			desc.append("每月"+this.getDay()+"号"+runTimeDes);
		}else if(6==type){
			desc.append(this.getCron());
		}
		return desc.toString();
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
