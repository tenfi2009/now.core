package com.tenfi.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.genericdao.search.Sort;

public class Page<T> {

	/**
	 * 记录总数
	 */
	private int totalCount;
	
	/**
	 * 记录总页数
	 */
	private int totalPage;

	/**
	 * 每页显示多少条记录
	 */
	private int pageSize = 15;

	/** 开始记录号 ,从零开始**/
	private int startIndex = 0;
	
	/** 当前页数码  从1开始**/
	private int currPage;
	
	/** 数据结果 **/
	private List<T> result;

	/** 排序对象 **/
	@JsonIgnore
	private List<Sort> sorts = new ArrayList<Sort>();

	public Page() {

	}

	public Page(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page(int startIndex, int pageSize) {
		this.startIndex = startIndex;
		this.pageSize = pageSize;

	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	public int getTotalPage() {
		int mod = totalCount % pageSize;
		if(0 == mod){
			totalPage = (int)(totalCount / pageSize);
		}else{
			totalPage = (int)((totalCount - mod) / pageSize) + 1;
		}
		
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		if(0 != currPage){
			return currPage;
		}
		int mod = (startIndex+1) % pageSize;
		if(0 == mod){
			currPage = (int)((startIndex+1) / pageSize);
		}else{
			currPage = (int)((startIndex + 1 - mod) / pageSize) + 1;
		}
		
		
		
		return currPage;
	}

	public void setCurrPage(int currpage) {
		this.currPage = currpage;
		startIndex = (currpage-1) * pageSize;
	}
}
