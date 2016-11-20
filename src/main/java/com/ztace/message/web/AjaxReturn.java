package com.ztace.message.web;

/**
 * Web Ajax返回对象封装
 * 
 * @author Crane(hehebaiy@gmail.com) <br/>
 *         <br/>
 */
public class AjaxReturn {
	boolean ok;// 返回状态
	String errCode;// 响应代码
	String msg;// 返回消息
	Object data;// 返回数据对象

	public boolean isOk() {
		return ok;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public AjaxReturn setOk(boolean ok) {
		this.ok = ok;
		return this;
	}

	public AjaxReturn setErrCode(String errCode) {
		this.errCode = errCode;
		return this;
	}

	public AjaxReturn setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public AjaxReturn setData(Object data) {
		this.data = data;
		return this;
	}

}
