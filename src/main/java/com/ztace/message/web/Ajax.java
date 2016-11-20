package com.ztace.message.web;

/**
 * Ajax 返回信息
 * @author Crane(hehebaiy@gmail.com)
 * <br/>
 * <br/>
 */
public abstract class Ajax {

    public static AjaxReturn ok() {
        AjaxReturn re = new AjaxReturn();
        re.ok = true;
        return re;
    }

    public static AjaxReturn fail() {
        AjaxReturn re = new AjaxReturn();
        re.ok = false;
        return re;
    }
    
    public static AjaxTableReturn tableDataOk(long total,Object rows) {
    	AjaxTableReturn re = new AjaxTableReturn();
    	re.ok = true;
    	re.total = total;
    	re.rows = rows;
    	return re;
    }
    
    public static AjaxTableReturn tableDatafail(String msg) {
    	AjaxTableReturn re = new AjaxTableReturn();
    	re.ok = false;
    	re.msg = msg;
    	return re;
    }

    public static AjaxReturn expired() {
        AjaxReturn re = new AjaxReturn();
        re.ok = false;
        re.msg = "ajax.expired";
        return re;
    }

}
