package com.tenfi.core.util;

import java.util.Collection;

import com.tenfi.core.model.TreeVO;
@SuppressWarnings({ "rawtypes" })
public class TreeUtils {

	public static <T extends TreeVO> String treeToJson(Collection<T> tree) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != tree && tree.size() > 0) {
			for (TreeVO node : tree) {
				sb.append("{\"id\":\"").append(node.getId()).append("\"");

				if (null == node.getParent()) {
					sb.append(",\"pId\":null");
				} else {
					sb.append(",\"pId\":\"").append(node.getParent().getId()).append("\"");
				}
				sb.append(",\"name\":\"").append(node.getName()).append("\"");
				sb.append(",\"fullName\":\"").append(node.getFullName()).append("\"");
				sb.append(",\"isParent\":");
				if (node.getLeaf()) {
					sb.append("false");
				} else {
					sb.append("true");
				}

				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		
		return sb.toString();
	}
}
