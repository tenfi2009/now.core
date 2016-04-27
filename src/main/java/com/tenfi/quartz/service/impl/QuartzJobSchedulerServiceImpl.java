package com.tenfi.quartz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.core.exception.BizException;
import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.core.util.SpringBeanContext;
import com.tenfi.quartz.TriggerFactoryBean;
import com.tenfi.quartz.dao.QuartJobSchedulerDao;
import com.tenfi.quartz.model.QuartzJobScheduler;
import com.tenfi.quartz.service.QuartzJobSchedulerService;
import com.tenfi.quartz.service.QuartzJobService;

@Service
public class QuartzJobSchedulerServiceImpl extends BaseServiceImpl<QuartzJobScheduler, Long> implements QuartzJobSchedulerService{

	@Autowired
	private QuartJobSchedulerDao dao;
	
	@Autowired
	private QuartzJobService quartzJobService;
	
	/** 定时任务的类 */
	private Scheduler scheduler;
	public Scheduler getScheduler() {
		if(scheduler==null){
			scheduler = SpringBeanContext.getBean("quartzScheduler",Scheduler.class);
		}
		return scheduler;
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public QuartJobSchedulerDao getDao() {
		return dao;
	}

	public void setDao(QuartJobSchedulerDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void saveJobScheduler(QuartzJobScheduler taskScheduler) {
		taskScheduler.setJob(quartzJobService.get(taskScheduler.getJob().getId()));
		dao.save(taskScheduler);
		addJob(taskScheduler);
	}

	@Override
	public void deleteJobScheduler(QuartzJobScheduler taskScheduler) {
		deleteJob(taskScheduler);
		dao.remove(taskScheduler);
	}
	
	/**
	 * @Description：增加定时任务
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午1:15:46
	 * @param targetObject
	 * @param targetMethod
	 * @param cronExpression
	 * @return void
	 */
	public void addJob(QuartzJobScheduler jobScheduler){
		try {
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
			boolean jobExists = true;
			if(jobDetail==null){
				jobExists= false;
				jobDetail = getJobDetail(jobScheduler);
			}
			Trigger cronTrigger = getTrigger(jobDetail,jobScheduler);
			boolean triggerExists = (getScheduler().getTrigger(cronTrigger.getKey()) != null);
			if(triggerExists){
				getScheduler().rescheduleJob(cronTrigger.getKey(), cronTrigger);
			}else if(jobExists){
				getScheduler().scheduleJob(cronTrigger);
			}else{
				getScheduler().scheduleJob(jobDetail,cronTrigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e);
		}
	}
	/**
	 * @Description：获取定时任务
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午1:16:02
	 * @param targetObject
	 * @param targetMethod
	 * @return
	 * @throws Exception
	 * @return JobDetail
	 */
	@SuppressWarnings("unchecked")
	private JobDetail getJobDetail(QuartzJobScheduler jobScheduler) throws Exception{
		
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName(jobScheduler.getJob().getJobName());
		jobDetail.setGroup(jobScheduler.getJob().getJobGroup());
		jobDetail.setJobClass((Class<Job>)Class.forName(jobScheduler.getJob().getClassName()));
		// Job 和 Trigger 的默认行为是：当 Trigger 完成了所有的触发、Job 在没有 Trigger 与之关联时它们就会从 JobStore 中移除。 
		// 当设置为true时,即使这个Job在所有的trigger都执行完以后它也不会从JobStore中移除,留待以后适当的时候再用其他Trigger去触发它
		jobDetail.setDurability(true);
		jobDetail.setDescription(jobScheduler.getJob().getDescription());
		jobDetail.getJobDataMap().put("volatility", false);
		jobDetail.setRequestsRecovery(false);
//		if(targetClass!=null)
//			jobDetail.getJobDataMap().put("targetClass", targetClass);
//		if(targetObject!=null)
//			jobDetail.getJobDataMap().put("targetObject", targetObject);
//		if(targetMethod!=null)
//			jobDetail.getJobDataMap().put("targetMethod", targetMethod);
//		if(staticMethod!=null)
//			jobDetail.getJobDataMap().put("staticMethod", staticMethod);
//		if(arguments!=null)
//			jobDetail.getJobDataMap().put("arguments", arguments);
//		if(description!=null){
//			jobDetail.getJobDataMap().put("description", description);
//		}
//		if(params!=null && params.length()>0){
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//			Map<String,Object> map = gson.fromJson(params,new TypeToken<Map<String,Object>>(){}.getType());
//			jobDetail.getJobDataMap().putAll(map);
//		}
		
		return jobDetail;
	}
	/**
	 * @Description：获取运行时间
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午1:16:13
	 * @param jobDetail
	 * @param cronExpression
	 * @param targetObject
	 * @param targetMethod
	 * @return
	 * @throws Exception
	 * @return CronTrigger
	 */
	private Trigger getTrigger(JobDetail jobDetail,QuartzJobScheduler jobScheduler) throws Exception{
		TriggerFactoryBean triggerFactoryBean = new TriggerFactoryBean();
		triggerFactoryBean.setGroup(jobScheduler.getJob().getJobGroup());
		triggerFactoryBean.setBeanName(jobScheduler.getJob().getJobName());
		triggerFactoryBean.setParams(jobScheduler.getJob().getParams());
		triggerFactoryBean.setDescription(jobScheduler.getJob().getDescription());
		triggerFactoryBean.setJobDetail(jobDetail);
		triggerFactoryBean.setJobScheduler(jobScheduler);
//		triggerFactoryBean.setCronExpression(jobScheduler.getCronExpression());
		triggerFactoryBean.afterPropertiesSet();
		return triggerFactoryBean.getObject();
	}
	/**
	 * @Description：查看任务状态
	 * @author：daoping.liu
	 * @date：2016年1月21日 上午10:45:18
	 * @param jobScheduler
	 * @return
	 * @return TriggerState
	 */
	public TriggerState getTriggerState(QuartzJobScheduler jobScheduler){
		try {
			TriggerKey triggerKey = new TriggerKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			TriggerState state = getScheduler().getTriggerState(triggerKey);
			return state;
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
	}
	/**
	 * @Description：查询定时任务状态
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午2:30:49
	 * @return
	 * @throws Exception
	 * @return List<QuartzDto>
	 */
	public Map<String,Object> findJobDetail(QuartzJobScheduler jobScheduler){
		Map<String,Object> map = new HashMap<>();
		try{
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
			map.put("triggerState", TriggerState.NONE);
			if(jobDetail!=null){
				// None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除 NORMAL:正常状态 PAUSED：暂停状态 COMPLETE：触发器完成，但是任务可能还正在执行中 BLOCKED：线程阻塞状态 ERROR：出现错误
//			TriggerState { NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED }
				map.putAll(jobDetail.getJobDataMap());
				map.put("jobScheduler",jobScheduler);
				map.put("description",jobDetail.getDescription());
				map.put("durability",jobDetail.isDurable());
				map.put("shouldRecover",jobDetail.requestsRecovery());
				map.put("triggerState",getTriggerState(jobScheduler));
				TriggerKey triggerKey = new TriggerKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
				Trigger trigger = getScheduler().getTrigger(triggerKey);
				if(trigger!=null){
					map.put("description", trigger.getDescription());
					map.put("priority", trigger.getPriority());
					map.put("mayFireAgain", trigger.mayFireAgain());
					map.put("startTime", trigger.getStartTime());
					map.put("endTime", trigger.getEndTime());
					map.put("nextFireTime", trigger.getNextFireTime());
					map.put("previousFireTime", trigger.getPreviousFireTime());
					map.put("finalFireTime", trigger.getFinalFireTime());
					map.put("misfireInstruction", trigger.getMisfireInstruction());
					if(trigger instanceof SimpleTrigger){
						map.put("timesTriggered",((SimpleTrigger) trigger).getTimesTriggered());
					}else if(trigger instanceof DailyTimeIntervalTrigger){
						map.put("timesTriggered",((DailyTimeIntervalTrigger) trigger).getTimesTriggered());
					}
				}
			}
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
		return map;
	}
	/**
	 * @Description： 暂停任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param jobScheduler
	 * @throws SchedulerException
	 * @return void
	 */
	public void executePauseJob(QuartzJobScheduler jobScheduler){
		try{
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			getScheduler().pauseJob(jobKey);
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
	}
	/**
	 * @Description： 恢复任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param jobScheduler
	 * @throws SchedulerException
	 * @return void
	 */
	public void executeResumeJob(QuartzJobScheduler jobScheduler){
		try{
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			getScheduler().resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
	}
	/**
	 * @Description： 删除任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param jobScheduler
	 * @throws SchedulerException
	 * @return void
	 */
	public void deleteJob(QuartzJobScheduler jobScheduler){
		try {
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			List<? extends Trigger> triggersList = getScheduler().getTriggersOfJob(jobKey);
			if(triggersList==null || triggersList.size()==1){
				getScheduler().deleteJob(jobKey);
			}else{
				TriggerKey triggerKey = new TriggerKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
				getScheduler().unscheduleJob(triggerKey);
			}
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
	}
	/**
	 * @Description： 立即运行任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param jobScheduler
	 * @throws SchedulerException
	 * @return void
	 */
	public void executeTriggerJob(QuartzJobScheduler jobScheduler){
		try{
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			getScheduler().triggerJob(jobKey);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	/**
	 * @Description：停止任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午5:00:05
	 * @param jobScheduler
	 * @return void
	 */
	public void executeInterrupt(QuartzJobScheduler jobScheduler){
		try{
			JobKey jobKey = new JobKey(jobScheduler.getJob().getJobName(), jobScheduler.getJob().getJobGroup());
			getScheduler().interrupt(jobKey);
		} catch (SchedulerException e) {
			throw new BizException(e);
		}
	}
}
