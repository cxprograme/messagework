package com.ztace.message.service;

import java.util.List;

import com.ztace.message.entity.Message;

/**
 * 
 * 消息的service接口
 * @author admin
 *
 */
public interface MessageService {
	
	/**
	 * 新增指令信息
	 * @return
	 */
	public int save(Message message);
	
	/**
	 * 批量新增消息
	 * @param messages
	 * @return
	 */
	public int insertBatch(List<Message> messages);
	
	/**
	 * 查询指定编号的消息信息
	 * @param mid
	 * @return
	 */
	public Message findMessageById(int mid);
	
	/**
	 * 修改消息信息
	 * @param mid
	 * @return
	 */
	public int modify(Message message);
	
	/**
	 * 删除单条消息信息
	 * @param mid
	 * @return
	 */
	public int delete(int mid);
	
	/**
	 * 批量删除消息
	 * @param mids
	 * @return
	 */
	public int deleteBatch(int[] mids);
	
	/**
	 * 查询所有消息 
	 * @return
	 */
	public List<Message> findAllMessages();
	
	
	/**
	 * 查询用户关联消息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryMesAndUser
	 * 创建人：chenxu 
	 * 时间：2016年5月26日-下午2:02:15 
	 * 手机:
	 * @return List<Message>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Message>  queryMesAndUser();
}
