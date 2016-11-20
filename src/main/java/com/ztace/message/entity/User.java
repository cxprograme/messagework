package com.ztace.message.entity;

/**
 * 用户信息
 * 
 * User
 * 创建人:chenxu 
 * 时间：2016年5月26日-下午1:31:10 
 * @version 1.0.0
 *
 */
public class User {

	
	private Integer id;	//用户id
	private String username; //用户名
	private Integer sex;		//性别
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + "]";
	}
	
	
	
}
