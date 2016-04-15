package com.tenfi.now.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.now.model.Drug;
import com.tenfi.now.dao.DrugDao;

@Repository
public class DrugDaoImpl extends BaseDaoImpl<Drug, Long> implements DrugDao{

}
