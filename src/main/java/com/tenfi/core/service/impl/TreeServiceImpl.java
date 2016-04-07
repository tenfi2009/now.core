package com.tenfi.core.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.googlecode.genericdao.search.Search;
import com.matrix.core.exception.BizException;
import com.tenfi.core.model.TreeVO;
import com.tenfi.core.service.TreeService;
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class TreeServiceImpl <T extends TreeVO,ID extends Serializable> extends  BasicDataServiceImpl<T, ID> implements TreeService<T,ID>{
	public static final String LEVEL_CODE_DELIMITER = "-";
	@Override
	protected void setAddNew(T entity) {
		//树节点相关属性的设置
		if (isRootNode(entity)){
			//根节点
			try {
				BeanUtils.setProperty(entity, "parent", null);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
			int count = getChildrenSize(null) + 1;
			entity.setLevelCode(StringUtils.leftPad(""+count, getlevelCodeLength(),"0"));
			entity.setDegree(0);//根结点设置为0
			entity.setLeaf(true);
		}else{
			TreeVO parent = (TreeVO)getDao().find(entity.getParent().getId());
			int count = getChildrenSize(parent.getId()) + 1;
			String levelCode = parent.getLevelCode()+LEVEL_CODE_DELIMITER + StringUtils.leftPad(""+count, getlevelCodeLength(),"0");
			entity.setLevelCode(levelCode);
			entity.setDegree(parent.getDegree()+ 1);
			entity.setLeaf(true);
			
			if(parent.getLeaf()){
				updateLeaf(entity.getParent().getId(),false);
			}
		}
		super.setAddNew(entity);
	}
	/**
	 * 是否要结点
	 * @param entity
	 * @return
	 */
	private boolean isRootNode(T entity){
		boolean isRoot = true;
		if (null != entity.getParent() 
				&& null != entity.getParent().getId()
				&& StringUtils.isNotEmpty(entity.getParent().getId().toString())){
			isRoot = false;
		}
		return isRoot;
	}
	protected void setUpdate(T entity) {
		if (isRootNode(entity)){
			try {
				BeanUtils.setProperty(entity, "parent", null);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}
		super.setUpdate(entity);
	}
	
	@Override
	protected void afterRemove(T entity) {
		super.afterRemove(entity);
		if(null != entity.getParent()){
			int count = getChildrenSize(entity.getParent().getId());
			if(0 == count){
				updateLeaf(entity.getParent().getId(),true);
			}
		}
	}


	public List<T> getChildren(String parentId){
		Search search = new Search();
		if(StringUtils.isEmpty(parentId)){
			search.addFilterNull("parent.id");
		}else{
			search.addFilterEqual("parent.id", parentId);
		}
		
		search.addSortAsc("sortNo");
		
		return getDao().search(search);
	}

	/**
	 * 如是 parentId 为null 或空，则返回根节点个数
	 * @param parentId
	 * @return
	 */
	public int getChildrenSize(Serializable parentId){
		Search search = new Search();
		if(null == parentId || StringUtils.isEmpty(parentId.toString())){
			search.addFilterNull("parent.id");
		}else{
			search.addFilterEqual("parent.id",parentId);
		}
		return getDao().count(search);
	}
	
	protected void updateLeaf(Serializable id,Boolean isLeaf) {
		Map propertys = new HashMap();
		propertys.put("leaf", isLeaf);
		getDao().updateById(getDao().getEntityClass(), id, propertys);
	}
	
	protected void beforeRemove(T entity) {
		int count = getChildrenSize(entity.getId());
		if(count > 0){
			throw new BizException("该记录还有下级数据，不能删除！");
		}
		super.beforeRemove(entity);
	}
	//获取层次码的长度
	protected int getlevelCodeLength() {
		return 4;
	}
}
