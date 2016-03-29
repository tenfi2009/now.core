/**
 * <b>包名：</b>com.matrix.sys.dao.impl<br/>
 * <b>文件名：</b>OrganizationDaoImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午11:13:42<br/>
 * <br/>
 */
package com.tenfi.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.core.util.SQLUtil;
import com.tenfi.sys.dao.OrganizationDao;
import com.tenfi.sys.model.Organization;

/**
 * <b>类名称：</b>OrganizationDaoImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午11:13:42<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
@Repository
public class OrganizationDaoImpl extends BaseDaoImpl<Organization, String> implements OrganizationDao {

	@Override
	public Search getFindPageSearch(Map params) {
		Search search = super.getFindPageSearch(params);
		StringBuffer filter  = new StringBuffer(" 1=1 ");
		if (StringUtils.isNotEmpty((String)params.get("orgId"))){
			filter.append(" and {org.id} = '").append((String)params.get("orgId")).append("'");
		}
		
		if(params.containsKey("parentId")){
			if (StringUtils.isNotEmpty((String)params.get("parentId"))){
				filter.append(" and {parent.id} = '").append((String)params.get("parentId")).append("'");
			}else{
				filter.append(" and {parent.id} = null");
			}
		}
		
		
		if (StringUtils.isNotEmpty((String)params.get("levelCode"))){
			filter.append(" and {org.levelCode} like '").append((String)params.get("levelCode")).append("%'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("status"))){
			filter.append(" and {status} = '").append((String)params.get("status")).append("'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("code"))){
			filter.append(" and {code} like '%").append(SQLUtil.escapeSQLLike((String)params.get("code"))).append("%' escape'/'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("name"))){
			filter.append(" and {name} like '%").append(SQLUtil.escapeSQLLike((String)params.get("name"))).append("%' escape'/'");
		}
		
		
		search.addFilterCustom(filter.toString());
		
		if (null != params.get("exclude")){
			//filter.append(" and {id} not in (").append((List)params.get("exclude")).append(")");
			search.addFilterNotIn("id", (List)params.get("exclude"));
		}
		
		if (null != params.get("include")){
			search.addFilterIn("id", (List)params.get("include"));
		}
		
		
		search.addSortAsc("sortNo");
		
		
		return search;
	}

}
