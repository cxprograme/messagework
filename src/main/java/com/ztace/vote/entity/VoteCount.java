package com.ztace.vote.entity;

/**
 * 
 * 投票统计
 * VoteCount
 * 创建人:chenxu 
 * 时间：2016年11月19日-下午2:20:05 
 * @version 1.0.0
 *
 */
public class VoteCount {
	private int userid;	//注册用户的id
	private String openid;	//投票者的openid
	
	

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "VoteCount [userid=" + userid + ", openid=" + openid + "]";
	}
	
	
}
