package com.dcits.model;

import java.io.Serializable;

/**
 * <p>Title: </p>
 *
 * <p>Description: JSON model</p>
 *
 * <p>Copyright: Copyright (c) 2013 DigitalChina corporation</p>
 *
 * <p>Company: DigitalChina</p>
 *
 * @author guddle
 * @version 1.0
 * @2013-10-13
 */
public class Json implements Serializable {

	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 其他信息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
