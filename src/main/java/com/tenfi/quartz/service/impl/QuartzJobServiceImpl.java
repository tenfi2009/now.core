package com.tenfi.quartz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.quartz.dao.QuartzJobDao;
import com.tenfi.quartz.model.QuartzJob;
import com.tenfi.quartz.service.QuartzJobService;

@Service
public class QuartzJobServiceImpl extends BaseServiceImpl<QuartzJob, Long> implements QuartzJobService{

	@Autowired
	private QuartzJobDao dao;

	public QuartzJobDao getDao() {
		return dao;
	}

	public void setDao(QuartzJobDao dao) {
		this.dao = dao;
	}
	
}
