package com.ztace.message.web;

/**
 * Ajax 表格数据封装
 * 
 * @author Crane(hehebaiy@gmail.com) <br/>
 *         <br/>
 */
public class AjaxTableReturn {
	boolean ok;// 返回状态
	String errCode;// 响应代码
	String msg;// 返回消息
	long total;// 返回数据总数
	Object data;// 返回其他对象
	Object rows;// 返回表格数据

	public boolean isOk() {
		return ok;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getMsg() {
		return msg;
	}

	public long getTotal() {
		return total;
	}

	public Object getData() {
		return data;
	}

	public Object getRows() {
		return rows;
	}

	public AjaxTableReturn setOk(boolean ok) {
		this.ok = ok;
		return this;
	}

	public AjaxTableReturn setErrCode(String errCode) {
		this.errCode = errCode;
		return this;
	}

	public AjaxTableReturn setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public AjaxTableReturn setTotal(long total) {
		this.total = total;
		return this;
	}

	public AjaxTableReturn setData(Object data) {
		this.data = data;
		return this;
	}

	public AjaxTableReturn setRows(Object rows) {
		this.rows = rows;
		return this;
	}

}
