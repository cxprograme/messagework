package com.ztace.message.entity;

/**
 * 消息
 * @author admin
 *
 */
public class Message {

	/**
	 * 消息编号
	 */
	private Integer id;
	
	/**
	 * 指令
	 */
	private String command;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 用户id
	 * 
	 */
	private Integer userId;
	
	/**
	 * 用户信息
	 */
	private User user;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", command=" + command + ", description=" + description + ", content=" + content
				+ ", userId=" + userId + ", user=" + user + "]";
	}
	
	
	
}
