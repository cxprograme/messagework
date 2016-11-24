package com.ztace.vote.entity;


/**
 * 文本对象的实体类
 * 
 * TextMessage
 * 创建人:chenxu 
 * 时间：2016年11月22日-上午9:22:45 
 * @version 1.0.0
 *
 */
public class TextMessage extends BaseMessage {


	private String Content;
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	@Override
	public String toString() {
		return "TextMessage [Content=" + Content + ", MsgId=" + MsgId + "]";
	}
	
	
	
}
