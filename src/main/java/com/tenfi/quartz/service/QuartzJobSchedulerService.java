package com.tenfi.quartz.service;

import java.util.Map;

import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;

import com.tenfi.core.service.BaseService;
import com.tenfi.quartz.model.QuartzJobScheduler;

public interface QuartzJobSchedulerService extends BaseService<QuartzJobScheduler, Long>{

	public void saveJobScheduler(QuartzJobScheduler jobScheduler);
	
	public void deleteJobScheduler(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description：增加定时任务
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午1:15:46
	 * @param targetObject
	 * @param targetMethod
	 * @param cronExpression
	 * @return void
	 */
	public void addJob(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description：查询定时任务
	 * @author：daoping.liu
	 * @date：2016年1月18日 下午2:30:49
	 * @return
	 * @throws Exception
	 * @return List<QuartzDto>
	 */
	public Map<String,Object> findJobDetail(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description： 暂停任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param quartzDto
	 * @throws SchedulerException
	 * @return void
	 */
	public void executePauseJob(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description： 恢复任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param quartzDto
	 * @throws SchedulerException
	 * @return void
	 */
	public void executeResumeJob(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description： 删除任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param quartzDto
	 * @throws SchedulerException
	 * @return void
	 */
	public void deleteJob(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description： 立即运行任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午12:59:13
	 * @param quartzDto
	 * @throws SchedulerException
	 * @return void
	 */
	public void executeTriggerJob(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description：停止任务
	 * @author：daoping.liu
	 * @date：2016年1月19日 下午5:00:05
	 * @param quartzDto
	 * @return void
	 */
	public void executeInterrupt(QuartzJobScheduler jobScheduler);
	
	/**
	 * @Description：查看任务状态
	 * @author：daoping.liu
	 * @date：2016年1月21日 上午10:45:18
	 * @param quartzDto
	 * @return
	 * @return TriggerState
	 */
	public TriggerState getTriggerState(QuartzJobScheduler jobScheduler);
}
