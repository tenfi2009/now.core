package com.tenfi.quartz;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.DailyTimeIntervalTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.spi.MutableTrigger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Constants;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

import com.tenfi.quartz.model.QuartzJobScheduler;
//import com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class TriggerFactoryBean implements FactoryBean<Trigger>, BeanNameAware, InitializingBean {


	/** Constants for the CronTrigger class */
	private static final Constants constants = new Constants(CronTrigger.class);

	private String name;

	private String group;

	private JobDetail jobDetail;

	private JobDataMap jobDataMap = new JobDataMap();

	private Date startTime;

	private long startDelay = 0;

	private String cronExpression;

	private TimeZone timeZone;

	private String calendarName;

	private int priority;

	private int misfireInstruction;

	private String description;

	private String beanName;

	private MutableTrigger trigger;

	private QuartzJobScheduler jobScheduler;
	private String params;
	@Override
	public void afterPropertiesSet() throws ParseException {
		if (this.name == null) {
			this.name = this.beanName;
		}
		if (this.group == null) {
			this.group = Scheduler.DEFAULT_GROUP;
		}
		if (this.jobDetail != null) {
			this.jobDataMap.put("jobDetail", this.jobDetail);
		}
		if (this.startDelay > 0 || this.startTime == null) {
			this.startTime = new Date(System.currentTimeMillis() + this.startDelay);
		}
		if (this.timeZone == null) {
			this.timeZone = TimeZone.getDefault();
		}
		if(jobScheduler!=null){
			/** 计划类型  1:一次,2:间隔,3:每天,4:每周,5:每月,6:Cron表达式*/
			switch(jobScheduler.getType()){
			case 1 : getType1Trigger();break;
			case 2 : getType2Trigger();break;
			case 3 : getType3Trigger();break;
			case 4 : getType4Trigger();break;
			case 5 : getType5Trigger();break;
			case 6 : getType6Trigger();break;
			}
		}else{
			getCronTrigger(this.cronExpression);
		}
//		if(params!=null && params.length()>0){
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//			Map<String,Object> map = gson.fromJson(params,new TypeToken<Map<String,Object>>(){}.getType());
//			this.jobDataMap.putAll(map);
//		}
		trigger.setJobKey(this.jobDetail.getKey());
		trigger.setJobDataMap(this.jobDataMap);
		trigger.setCalendarName(this.calendarName);
		trigger.setPriority(this.priority);
		trigger.setMisfireInstruction(this.misfireInstruction);
		trigger.setDescription(this.description);
	}
	/**
	 * @Description：1:一次
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午12:57:57
	 * @return void
	 */
	public void getType1Trigger(){
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		trigger.setName(this.name);
		trigger.setGroup(this.group);
		trigger.setStartTime(jobScheduler.getStartDate());
		trigger.setRepeatCount(0);
		trigger.setRepeatInterval(0);
		this.trigger = trigger;
	}
	/**
	 * @Description：2:间隔
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午12:58:07
	 * @return void
	 * @throws ParseException 
	 */
	public void getType2Trigger() throws ParseException{
		/** 间隔时间单位 1:秒,2:分,3:小时   */
		DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
		trigger.setStartTime(this.startTime);
		trigger.setRepeatCount(DailyTimeIntervalTriggerImpl.REPEAT_INDEFINITELY);
		trigger.setRepeatInterval(jobScheduler.getTimeInterval());
		switch(jobScheduler.getUnit()){
		case 1 : trigger.setRepeatIntervalUnit(IntervalUnit.SECOND); break;
		case 2 : trigger.setRepeatIntervalUnit(IntervalUnit.MINUTE); break;
		case 3 : trigger.setRepeatIntervalUnit(IntervalUnit.HOUR); break;
		}
		if(jobScheduler.getRepeatCount()!=null){
			trigger.setRepeatCount(jobScheduler.getRepeatCount());
		}
		trigger.setName(this.name);
		trigger.setGroup(this.group);
		this.trigger = trigger;
	}
	/**
	 * @Description：3:每天
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午1:02:05
	 * @return void
	 * @throws ParseException 
	 */
	public void getType3Trigger() throws ParseException{
		LocalTime localTime = jobScheduler.getRunTime().toLocalTime();
		DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
		trigger.setStartTime(this.startTime);
		trigger.setRepeatCount(DailyTimeIntervalTriggerImpl.REPEAT_INDEFINITELY);
		TimeOfDay timeOfDay = new TimeOfDay(localTime.getHour(),localTime.getMinute(),localTime.getSecond());
		trigger.setStartTimeOfDay(timeOfDay);
		LocalTime endLocalTime = localTime.plusSeconds(30);
		TimeOfDay endTimeOfDay = new TimeOfDay(endLocalTime.getHour(),endLocalTime.getMinute(),endLocalTime.getSecond());
		trigger.setEndTimeOfDay(endTimeOfDay);
		trigger.setRepeatInterval(1);
		trigger.setRepeatIntervalUnit(IntervalUnit.HOUR);
		if(jobScheduler.getRepeatCount()!=null && jobScheduler.getRepeatCount()>0){
			trigger.setRepeatCount(jobScheduler.getRepeatCount());
		}
		trigger.setName(this.name);
		trigger.setGroup(this.group);
		this.trigger = trigger;
	}
	/**
	 * @Description：4:每周
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午1:02:11
	 * @return void
	 * @throws ParseException 
	 */
	public void getType4Trigger() throws ParseException{
		LocalTime localTime = jobScheduler.getRunTime().toLocalTime();
//		StringBuffer cron = new StringBuffer("");
////		周     是     1-7,星期日是1，是7 星期六
//		cron.append(localTime.getSecond()).append(" ").append(localTime.getMinute()).append(" ").append(localTime.getHour()).append(" ? * ").append(taskScheduler.getDay());
//		getCronTrigger(cron.toString());
		
		DailyTimeIntervalTriggerImpl trigger = new DailyTimeIntervalTriggerImpl();
		trigger.setStartTime(this.startTime);
		trigger.setRepeatCount(DailyTimeIntervalTriggerImpl.REPEAT_INDEFINITELY);
		TimeOfDay timeOfDay = new TimeOfDay(localTime.getHour(),localTime.getMinute(),localTime.getSecond());
		trigger.setStartTimeOfDay(timeOfDay);
		LocalTime endLocalTime = localTime.plusSeconds(30);
		TimeOfDay endTimeOfDay = new TimeOfDay(endLocalTime.getHour(),endLocalTime.getMinute(),endLocalTime.getSecond());
		trigger.setEndTimeOfDay(endTimeOfDay);
		trigger.setRepeatInterval(1);
		trigger.setRepeatIntervalUnit(IntervalUnit.HOUR);
		if(jobScheduler.getRepeatCount()!=null && jobScheduler.getRepeatCount()>0){
			trigger.setRepeatCount(jobScheduler.getRepeatCount());
		}
		Set<Integer> daysOfWeek = new HashSet<>(7);
		String [] days = jobScheduler.getDay().split(",");
		for(int i=0;i<days.length;i++){
			daysOfWeek.add(Integer.valueOf(days[i]));
		}
		trigger.setDaysOfWeek(daysOfWeek);
		trigger.setName(this.name);
		trigger.setGroup(this.group);
		this.trigger = trigger;
	}
	/**
	 * @Description：5:每月
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午1:02:32
	 * @return void
	 * @throws ParseException 
	 */
	public void getType5Trigger() throws ParseException{
		LocalTime localTime = jobScheduler.getRunTime().toLocalTime();
		StringBuffer cron = new StringBuffer("");
		cron.append(localTime.getSecond()).append(" ").append(localTime.getMinute()).append(" ").append(localTime.getHour()).append(" ").append(jobScheduler.getDay()).append(" * ?");
		getCronTrigger(cron.toString());
	}
	/**
	 * @Description：6:Cron表达式
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午1:02:45
	 * @throws ParseException
	 * @return void
	 */
	public void getType6Trigger() throws ParseException{
		getCronTrigger(jobScheduler.getCron());
	}
	/**
	 * @Description：6:Cron表达式
	 * @author：daoping.liu
	 * @date：2016年1月20日 下午1:02:45
	 * @throws ParseException
	 * @return void
	 */
	public void getCronTrigger(String cronExpression) throws ParseException{
		CronTriggerImpl cti = new CronTriggerImpl();
		cti.setName(this.name);
		cti.setGroup(this.group);
		cti.setStartTime(this.startTime);
		cti.setCronExpression(cronExpression);
		cti.setTimeZone(this.timeZone);
		this.trigger = cti;
	}
	@Override
	public Trigger getObject() {
		return this.trigger;
	}

	@Override
	public Class<?> getObjectType() {
		return Trigger.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	/**
	 * Specify the trigger's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Specify the trigger's group.
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Set the JobDetail that this trigger should be associated with.
	 */
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	/**
	 * Set the trigger's JobDataMap.
	 * @see #setJobDataAsMap
	 */
	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	/**
	 * Return the trigger's JobDataMap.
	 */
	public JobDataMap getJobDataMap() {
		return this.jobDataMap;
	}

	/**
	 * Register objects in the JobDataMap via a given Map.
	 * <p>These objects will be available to this Trigger only,
	 * in contrast to objects in the JobDetail's data map.
	 * @param jobDataAsMap Map with String keys and any objects as values
	 * (for example Spring-managed beans)
	 */
	public void setJobDataAsMap(Map<String, ?> jobDataAsMap) {
		this.jobDataMap.putAll(jobDataAsMap);
	}

	/**
	 * Set a specific start time for the trigger.
	 * <p>Note that a dynamically computed {@link #setStartDelay} specification
	 * overrides a static timestamp set here.
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Set the start delay in milliseconds.
	 * <p>The start delay is added to the current system time (when the bean starts)
	 * to control the start time of the trigger.
	 */
	public void setStartDelay(long startDelay) {
		Assert.isTrue(startDelay >= 0, "Start delay cannot be negative");
		this.startDelay = startDelay;
	}

	/**
	 * Specify the cron expression for this trigger.
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * Specify the time zone for this trigger's cron expression.
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Associate a specific calendar with this cron trigger.
	 */
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	/**
	 * Specify the priority of this trigger.
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Specify a misfire instruction for this trigger.
	 */
	public void setMisfireInstruction(int misfireInstruction) {
		this.misfireInstruction = misfireInstruction;
	}

	/**
	 * Set the misfire instruction via the name of the corresponding
	 * constant in the {@link org.quartz.CronTrigger} class.
	 * Default is {@code MISFIRE_INSTRUCTION_SMART_POLICY}.
	 * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
	 * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_DO_NOTHING
	 * @see org.quartz.Trigger#MISFIRE_INSTRUCTION_SMART_POLICY
	 */
	public void setMisfireInstructionName(String constantName) {
		this.misfireInstruction = constants.asNumber(constantName).intValue();
	}

	/**
	 * Associate a textual description with this trigger.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(MutableTrigger trigger) {
		this.trigger = trigger;
	}

	public QuartzJobScheduler getJobScheduler() {
		return jobScheduler;
	}
	public void setJobScheduler(QuartzJobScheduler jobScheduler) {
		this.jobScheduler = jobScheduler;
	}
	public static Constants getConstants() {
		return constants;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public Date getStartTime() {
		return startTime;
	}

	public long getStartDelay() {
		return startDelay;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public int getPriority() {
		return priority;
	}

	public int getMisfireInstruction() {
		return misfireInstruction;
	}

	public String getDescription() {
		return description;
	}

	public String getBeanName() {
		return beanName;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}
