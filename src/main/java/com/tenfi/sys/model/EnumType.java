package com.tenfi.sys.model;

/**
 * 枚举类型的定义
 * 
 * @author rong yang
 * 
 */
public interface EnumType {
	/**
	 * 获取枚举的显示名字
	 * 
	 * @return
	 */
	public String getDisplayName();

	/**
	 * 获取枚举的值
	 * 
	 * @return
	 */
	public int getValue();

	/**
	 * 获取枚举的序号
	 * 
	 * @return
	 */
	public int getSequence();
}
