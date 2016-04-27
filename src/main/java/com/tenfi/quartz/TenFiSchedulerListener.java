package com.tenfi.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenFiSchedulerListener implements SchedulerListener {
	private static Logger logger = LoggerFactory.getLogger(TenFiSchedulerListener.class);
	@Override
	public void jobScheduled(Trigger trigger) {
		logger.info("jobScheduled: {},start time: {}, next time: {}",trigger.getKey().toString(),formatDate(trigger.getStartTime()),formatDate(trigger.getNextFireTime()));
	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		logger.info("jobUnscheduled: {}",triggerKey.toString());
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		logger.info("triggerFinalized: {},start time: {}, next time: {}",trigger.getKey().toString(),formatDate(trigger.getStartTime()),formatDate(trigger.getNextFireTime()));
		
	}

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		logger.info("triggerPaused: {}",triggerKey.toString());
		
	}

	@Override
	public void triggersPaused(String triggerGroup) {
		logger.info("triggersPaused: {}",triggerGroup);
		
	}

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		logger.info("triggerResumed: {}",triggerKey.toString());
		
	}

	@Override
	public void triggersResumed(String triggerGroup) {
		logger.info("triggersResumed: {}",triggerGroup);	
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		logger.info("jobAdded: {}",jobDetail.getKey().toString());
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		logger.info("jobDeleted: {}",jobKey.toString());
		
	}

	@Override
	public void jobPaused(JobKey jobKey) {
		logger.info("jobPaused: {}",jobKey.toString());
		
	}

	@Override
	public void jobsPaused(String jobGroup) {
		logger.info("jobsPaused: {}",jobGroup);
		
	}

	@Override
	public void jobResumed(JobKey jobKey) {
		logger.info("jobResumed: {}",jobKey.toString());
		
	}

	@Override
	public void jobsResumed(String jobGroup) {
		logger.info("jobsResumed: {}",jobGroup);
		
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		logger.info("schedulerError: {}, cause: {}",msg,cause.getMessage());
	}

	@Override
	public void schedulerInStandbyMode() {
		logger.info("schedulerInStandbyMode");
	}

	@Override
	public void schedulerStarted() {
		logger.info("schedulerStarted");
		
	}

	@Override
	public void schedulerStarting() {
		logger.info("schedulerStarting");
	}

	@Override
	public void schedulerShutdown() {
		logger.info("schedulerShutdown");
		
	}

	@Override
	public void schedulerShuttingdown() {
		logger.info("schedulerShuttingdown");
		
	}

	@Override
	public void schedulingDataCleared() {
		logger.info("schedulingDataCleared");
	}
	
	
	private static String formatDate(Date date){ 
		if(date==null){
			return "";
		}
	    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
	    return formatter.format(date);
	} 
}
