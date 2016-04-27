package com.tenfi.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenFiTriggerListener implements TriggerListener {
	private static Logger logger = LoggerFactory.getLogger(TriggerListener.class);
	@Override
	public String getName() {
		return "AMSTaskTriggerListener";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		logger.info("triggerFired: {}",trigger.getKey().toString());
		
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		logger.info("triggerMisfired: {}",trigger.getKey().toString());
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
		logger.info("triggerComplete: {}",trigger.getKey().toString());
	}

}
