package com.tenfi.core.util;

import java.beans.PropertyEditorSupport;

import com.matrix.core.exception.BizException;
import com.tenfi.sys.model.EnumType;

public class ResourceTypeEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		String text = null;
		if(null != this.getValue()){
			text = ((ResourceType)this.getValue()).getDisplayName();
		}
		return text;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ResourceType[]  a = ResourceType.values();
		for(EnumType e : a){
			if(e.getValue() == Integer.valueOf(text).intValue()){
				setValue(e);
				return;
			}
		}
		throw new BizException(text+" is not valid value in "+ ResourceType.class.getName());
	}
}
