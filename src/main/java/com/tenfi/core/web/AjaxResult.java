package com.tenfi.core.web;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class AjaxResult implements Serializable {
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_ERROR = "error";

	private String status;
	private String msg;
	private Object data;
	public AjaxResult() {
		super();
	}
	
	public AjaxResult(String status,String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public AjaxResult(String type) {
		super();
		this.status = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson() throws Exception{
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}

}
