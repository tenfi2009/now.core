package com.tenfi.core.service.impl;

import java.io.Serializable;

import com.tenfi.core.model.BasicDataVO;
import com.tenfi.core.service.BasicDataService;
@SuppressWarnings({ "rawtypes" })
public abstract class BasicDataServiceImpl <T extends BasicDataVO,ID extends Serializable> extends  VOServiceImpl<T, ID> implements BasicDataService<T,ID>{

}
