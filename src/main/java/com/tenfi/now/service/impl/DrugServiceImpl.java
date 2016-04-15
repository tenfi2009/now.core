package com.tenfi.now.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenfi.core.service.impl.BaseServiceImpl;
import com.tenfi.now.dao.DrugDao;
import com.tenfi.now.model.Drug;
import com.tenfi.now.service.DrugService;

@Service
public class DrugServiceImpl extends BaseServiceImpl<Drug, Long> implements DrugService{

	@Autowired
	private DrugDao dao;

	public DrugDao getDao() {
		return dao;
	}

	public void setDao(DrugDao dao) {
		this.dao = dao;
	}
	
}
