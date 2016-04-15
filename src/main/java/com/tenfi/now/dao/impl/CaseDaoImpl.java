package com.tenfi.now.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.now.dao.CaseDao;
import com.tenfi.now.model.Case;

@Repository
public class CaseDaoImpl extends BaseDaoImpl<Case, Long> implements CaseDao{

}
