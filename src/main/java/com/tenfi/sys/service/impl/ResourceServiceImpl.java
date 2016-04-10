/**
 * <b>包名：</b>com.matrix.sys.service.impl<br/>
 * <b>文件名：</b>ResourceServiceImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:36:21<br/>
 * <br/>
 */
package com.tenfi.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Search;
import com.tenfi.core.service.impl.TreeServiceImpl;
import com.tenfi.enums.Status;
import com.tenfi.sys.common.SysConstants;
import com.tenfi.sys.dao.ResourceDao;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.User;
import com.tenfi.sys.service.ResourceService;
import com.tenfi.sys.service.UserService;

/**
 * <b>类名称：</b>ResourceServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:36:21<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
public class ResourceServiceImpl extends TreeServiceImpl<Resource, String> implements ResourceService {
	@Autowired
	private ResourceDao dao;

	@Autowired
	private UserService userService;

	@Override
	public ResourceDao getDao() {
		return dao;
	}

	/**
	 * 获取指定的用户系统导航Accordion
	 * 
	 * @param userId
	 * @return
	 */
	public String getSysNavAccordion(String userId) {
		User user = userService.get(userId);
		// 是否超级管理员
		List<Resource> resList = null;
		if (SysConstants.WEBMASTER.equals(user.getAccount())) {
			resList = getSuperAdminResource();
		} else {
			// 用户有权限的资源
			Map<String, Resource> rightedResource = userService.getRightResourceByUser(user);
			if (null != rightedResource && rightedResource.size() > 0) {
				resList = new ArrayList<Resource>(rightedResource.size());
				Iterator<Resource> it = rightedResource.values().iterator();
				while (it.hasNext()) {
					resList.add(it.next());
				}

				Collections.sort(resList, new Comparator<Resource>() {
					public int compare(Resource o1, Resource o2) {
						int num1 = null == o1.getSortNo() ? 1000 : o1.getSortNo();
						int num2 = null == o2.getSortNo() ? 1000 : o2.getSortNo();
						return num1 - num2;
					}
				});
			}
		}

		StringBuffer accordion = new StringBuffer();
		if (null != resList && resList.size() > 0) {
			int size = resList.size();
			Resource group = null;
			Resource res = null;

			for (int i = 0; i < size; i++) {
				group = resList.get(i);
				if (null != group.getParent()) {
					continue;
				}

				// 是否有下级节点
				boolean hasChildren = false;
				for (int k = 0; k < size; k++) {
					res = resList.get(k);
					if (null != res.getParent() && res.getParent().getId().equals(group.getId())) {
						hasChildren = true;
						break;
					}
				}
//				 <ul class="sidebar-menu">
//			        <li class="active treeview">
//			          <a href="#">
//			            <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
//			          </a>
//			          <ul class="treeview-menu">
//			            <li class="active"><a href="${ctx}/static/index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
//			            <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
//			          </ul>
//			        </li>
//			        <li><a href="documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
//			      </ul>
				
				if (hasChildren) {
					accordion.append("<li class=\"treeview\">");
					accordion.append("<a href=\"#\">");
					accordion.append("<i class=\"").append(group.getIcon()).append("\"></i>");
					accordion.append("<span>").append(group.getName()).append("</span>");
					accordion.append("<i class=\"fa fa-angle-left pull-right\"></i>");
					accordion.append("</a>");
					accordion.append("<ul class=\"treeview-menu\">");
					for (int k = 0; k < size; k++) {
						res = resList.get(k);
						if (null != res.getParent() && res.getParent() .getId().equals(group.getId())) {
							accordion.append("<li class=\"\">");
							accordion.append("<a href=\"").append(res.getUri()).append("\" target=\"center\">");
							accordion.append("<i class=\"").append(res.getIcon()).append("\"></i>");
							accordion.append(res.getName());
							accordion.append("</a>");
							accordion.append("</li>");
						}
					}
					accordion.append("</ul>");
					accordion.append("</li>");
				} else {
					accordion.append("<li class=\"\" >");
					accordion.append("<a href=\"").append(group.getUri()).append("\" target=\"center\">");
					accordion.append("<i class=\"").append(group.getIcon()).append("\"></i>");
					accordion.append("<span class=\"menu-text\">").append(group.getName()).append("</span>");
					accordion.append("</a>");
					accordion.append("<b class=\"arrow\"></b>");
					accordion.append("</li>");
				}
			}

		}

		return accordion.toString();
	}

	private List<Resource> getSuperAdminResource() {
		Search search = new Search(Resource.class);
		search.addFilterEqual("isSuper", true);
		search.addSortAsc("sortNo");
		return dao.search(search);
	}
}
