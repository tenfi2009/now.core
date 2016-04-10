package com.tenfi.enums;



/**
 * 
 * <B>描述：</B><BR>
 * 值对象状态枚举 <BR>
 * 
 * @author rong yang
 * @version 1.0 2013-06-22
 * 
 */
public enum Status implements EnumType {
	SAVE("保存", 1),
	VALID("有效", 2),
	CANCEL("无效", 3),
	PUBLISH("发布", 4);

	private final String displayName;
	private final int sequence;

	private Status(String displayName, int sequence) {
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

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("{");
		s.append("\"name\":\"").append(name()).append("\"");
		s.append(",\"displayName\":\"").append(displayName).append("\"");
		s.append(",\"value\":\"").append(getValue()).append("\"");
		s.append(",\"sequence\":\"").append(sequence).append("\"");
		s.append("}");
		return s.toString();
	}
}
