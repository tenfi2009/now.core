/**
 * <b>包名：</b>com.matrix.sys.service<br/>
 * <b>文件名：</b>ResourceService.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:35:49<br/>
 * <br/>
 */
package com.tenfi.sys.service;

import com.tenfi.core.service.TreeService;
import com.tenfi.sys.model.Resource;

/**
 * <b>类名称：</b>ResourceService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:35:49<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public interface ResourceService extends TreeService<Resource,String>{
	
	/**
	 * 获取指用户的系统导航Accordion
	 * @param userId 
	 * @return 
	 */
	public String getSysNavAccordion(String userId);

}
