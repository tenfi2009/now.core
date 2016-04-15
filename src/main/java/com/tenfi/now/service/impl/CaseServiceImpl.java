package com.tenfi.now.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.now.dao.CaseDao;
import com.tenfi.now.model.Case;
import com.tenfi.now.service.CaseService;

@Service
public class CaseServiceImpl extends BaseServiceImpl<Case, Long> implements CaseService {

	@Autowired
	private CaseDao dao;

	public CaseDao getDao() {
		return dao;
	}

	public void setDao(CaseDao dao) {
		this.dao = dao;
	}
	
	
}
