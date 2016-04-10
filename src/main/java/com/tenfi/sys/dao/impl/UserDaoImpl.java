package com.tenfi.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.tenfi.core.dao.impl.BaseDaoImpl;
import com.tenfi.core.model.Page;
import com.tenfi.enums.ResourceType;
import com.tenfi.enums.Status;
import com.tenfi.sys.dao.UserDao;
import com.tenfi.sys.dao.UserRoleDao;
import com.tenfi.sys.model.Organization;
import com.tenfi.sys.model.Resource;
import com.tenfi.sys.model.Role;
import com.tenfi.sys.model.User;
import com.tenfi.sys.model.UserRole;
import com.tenfi.sys.service.OrganizationService;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {
    @Autowired
	private OrganizationService orgService;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
	@Override
	public User findUserByAccount(String account){
		Search search = new Search(User.class);
		search.addFilterEqual("account", account);
		return this.searchUnique(search);
	}

	@Override
	public List<Role> findRoles(String userId) {
		List<Role> roles = null;
		Search search = new Search(UserRole.class);
		search.addFilterEqual("user.id", userId);
		List<UserRole> userRoles =  userRoleDao.search(search);
		if(null != userRoles && userRoles.size() > 0){
			roles = new ArrayList<Role>(userRoles.size());
			for(UserRole userRole : userRoles){
				roles.add(userRole.getRole());
			}
		}
		
		return roles;
	}

	@Override
	public Search getFindPageSearch(Map queryParams) {
		Search search = super.getFindPageSearch(queryParams);
		search.addFilterNotEmpty("org.id");
		return search;
	}
	
	public Map<String,Resource> findRightResourceByUser(User user){
		Map<String,Resource> map = new HashMap<String,Resource>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT R.id,R.parent_id,R.name,R.uri,R.type,R.sort_no,R.description,R.icon ");
		sql.append("FROM t_sys_resource R,t_sys_role_resource RR, t_sys_user_role UR,t_sys_role RO ");
		sql.append("WHERE R.id = RR.resource_id AND RR.role_id=UR.role_id AND UR.role_id=RO.id ");
		sql.append("AND RO.status = ? AND UR.user_id = ? order by R.sort_no asc");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		
		sqlQuery.setParameter(0, Status.VALID.ordinal());
		sqlQuery.setParameter(1, user.getId());
		List<Object[]> list = sqlQuery.list();
		
		if(null != list && list.size() > 0){
			Resource res = null;
			Resource parent = null;
			for(Object[] obj : list){
				res = new Resource();
				res.setId((String)obj[0]);
				
				//设置父节点
				if(StringUtils.isNotEmpty((String)obj[1])){
					parent = new Resource();
					parent.setId((String)obj[1]);
					res.setParent(parent);
				}else{
					res.setParent(null);
				}
				
				
				res.setName((String)obj[2]);
				res.setUri((String)obj[3]);
				res.setType(ResourceType.valueOf((Integer)obj[4]));
				res.setSortNo((Integer)obj[5]);
				res.setDescription((String)obj[6]);
				res.setIcon((String)obj[7]);
				map.put(res.getId(), res);
			}
		}
		
		return map;
	}
	public Page<User> findPage(Page<User> page, List<Map<String, Object>> queryParams, String groupId, Integer isSub){
		String hql = "select u from User u where u.id not in(select distinct gu.userId from GroupUser gu where gu.groupId = :id)";
        if (queryParams.size() > 0) {
			for (Map queryParam : queryParams) {
				Iterator<Map.Entry<String, Object>> it = queryParam.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, Object> entry = it.next();
					if ("g.id".equals(entry.getKey())) {
						if(1==isSub){
							Organization org = orgService.get(entry.getValue().toString());
							hql += " and u.org.levelCode like'" + org.getLevelCode()+ "%'";
						}else{
							hql += " and u.org.id ='" + entry.getValue()+ "'";
						}
					} else if ("name".equals(entry.getKey())) {
						hql += " and u.name ='" + entry.getValue()+ "'";
					} 
				}
			}
		}
        Query query = this.getSession().createQuery(hql);
        query.setParameter("id", groupId);
		query.setFirstResult(page.getStartIndex()); 
		query.setMaxResults(page.getPageSize());
		List<User> result = query.list();
		page.setResult(result);
		page.setTotalCount(this.countListUser(queryParams,groupId, isSub));
		return page;
	}
	public int countListUser(List<Map<String, Object>> queryParams,String groupId,Integer isSub){
		String hql = "select u from User u where u.id not in(select distinct gu.userId from GroupUser gu where gu.groupId = :id)";
		if (queryParams.size() > 0) {
			for (Map queryParam : queryParams) {
				Iterator<Map.Entry<String, Object>> it = queryParam.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, Object> entry = it.next();
					if ("g.id".equals(entry.getKey())) {
						if(1==isSub){
							Organization org = orgService.get(entry.getValue().toString());
							hql += " and u.org.levelCode like'" + org.getLevelCode()+ "%'";
						}else{
							hql += " and u.org.id ='" + entry.getValue()+ "'";
						}
					} else if ("name".equals(entry.getKey())) {
						hql += " and u.name ='" + entry.getValue()+ "'";
					} 
				}
			}
		}
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", groupId);
		int count = query.list().size();
		return count;
	}
}
