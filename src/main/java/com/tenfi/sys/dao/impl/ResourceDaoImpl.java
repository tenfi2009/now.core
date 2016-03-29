/**
 * <b>包名：</b>com.matrix.sys.dao.impl<br/>
 * <b>文件名：</b>ResourceDaoImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午11:14:05<br/>
 * <br/>
 */
package com.tenfi.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.sys.dao.ResourceDao;
import com.tenfi.sys.model.Resource;

/**
 * <b>类名称：</b>ResourceDaoImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午11:14:05<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Long> implements ResourceDao{

}
