package com.tenfi.core.service;

import java.io.Serializable;

import com.tenfi.core.model.TreeVO;

public interface TreeService <T extends TreeVO,ID extends Serializable> extends BasicDataService<T,ID>{

}
