package com.jw.model.common;

import java.io.Serializable;

/**
 * 返回结果集
 * 
 * @author Zeng
 *
 */
public class AutoResult implements Serializable {
	private static final long serialVersionUID = 1134066180339297918L;

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public AutoResult() {

	}
	
	public AutoResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public AutoResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static AutoResult build(Integer status, String msg) {
		return new AutoResult(status, msg, null);
	}

	public static AutoResult build(Integer status, String msg, Object data) {
		return new AutoResult(status, msg, data);
	}
	
	public static AutoResult ok() {
		return new AutoResult(null);
	}

	public static AutoResult ok(Object data) {
		return new AutoResult(data);
	}

	public static AutoResult error(String msg) {
		return new AutoResult(500, msg, "");
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
