package com.tenfi.quartz.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.quartz.dao.QuartzJobDao;
import com.tenfi.quartz.model.QuartzJob;

@Repository
public class QuartzJobDaoImpl extends BaseDaoImpl<QuartzJob, Long> implements QuartzJobDao{

}
