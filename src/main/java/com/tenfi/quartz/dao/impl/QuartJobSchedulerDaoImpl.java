package com.tenfi.quartz.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.quartz.dao.QuartJobSchedulerDao;
import com.tenfi.quartz.model.QuartzJobScheduler;

@Repository
public class QuartJobSchedulerDaoImpl extends BaseDaoImpl<QuartzJobScheduler, Long> implements QuartJobSchedulerDao{

}
