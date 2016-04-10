/**
 * <b>包名：</b>com.matrix.sys.enums<br/>
 * <b>文件名：</b>ResourceType.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午10:35:14<br/>
 * <br/>
 */
package com.tenfi.enums;



/**
 * <b>类名称：</b>ResourceType<br/>
 * <b>类描述：</b>资源类型枚举<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午10:35:14<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
public enum ResourceType  implements EnumType{
	GROUP("资源组", 1),
	MENU("菜单", 2),
	FUNCTION("功能", 3);

	private final String displayName;
	private final int sequence;

	private ResourceType(String displayName, int sequence) {
		this.displayName = displayName;
		this.sequence = sequence;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public int getValue() {
		return ordinal();
	}

	@Override
	public int getSequence() {
		
		return sequence;
		
	}
	
	
	public static ResourceType valueOf(int ordinal){
		ResourceType[] aa = ResourceType.values();
		for(ResourceType a : aa ){
			if(a.ordinal() == ordinal){
				return a;
			}
		}
		return null;
	}
}
