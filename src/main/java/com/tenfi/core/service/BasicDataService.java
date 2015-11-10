package com.tenfi.core.service;

import java.io.Serializable;

import com.tenfi.core.model.BasicDataVO;

public interface BasicDataService<T extends BasicDataVO,ID extends Serializable> extends VOService<T,ID> {

}