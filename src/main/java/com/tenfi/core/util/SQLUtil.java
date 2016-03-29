package com.tenfi.core.util;

import org.apache.commons.lang3.StringUtils;

public class SQLUtil {
	/**
	 * <b>描述：</b<br/>对LIKE查询进行转义:<br/>
	 * filter.append(" and {deviceCode} like '%").append(SQLUtil.escapeSQLLike((String)params.get("deviceCode"))).append("%' escape'/'");
	 * @param s
	 * @return
	 */
	public static String escapeSQLLike(String s) {
		if (StringUtils.isEmpty(s)) {
			return s;
		}
		s = s.trim();
		String str = StringUtils.replace(s, "_", "/_");
		str = StringUtils.replace(str, "%", "/%");
		str = StringUtils.replace(str, "?", "_");
		str = StringUtils.replace(str, "*", "%");
		str = StringUtils.replace(str, "'", "''");
		return str;
	}
}
