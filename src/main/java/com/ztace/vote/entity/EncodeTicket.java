package com.ztace.vote.entity;


/**
 * 二维码的ticket实体类
 * 
 * Ticket
 * 创建人:chenxu 
 * 时间：2016年11月21日-下午3:58:39 
 * @version 1.0.0
 *
 */
public class EncodeTicket {
		private String ticket;	//获取二维码的ticket
		
		private int expire_seconds;	//超时时间
		
		private String url;	//地址

		public String getTicket() {
			return ticket;
		}

		public void setTicket(String ticket) {
			this.ticket = ticket;
		}

		public int getExpire_seconds() {
			return expire_seconds;
		}

		public void setExpire_seconds(int expire_seconds) {
			this.expire_seconds = expire_seconds;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String toString() {
			return "EncodeTicket [ticket=" + ticket + ", expire_seconds=" + expire_seconds + ", url=" + url + "]";
		}
		
		
		
}
