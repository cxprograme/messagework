package com.ztace.message.mapper;

import java.util.List;

import com.ztace.message.entity.Message;

/**
 * 消息的Mapper接口
 * @author admin
 *
 */
public interface MessageMapper {
	
	
	/**
	 * 新增指令信息
	 * @return
	 */
	public int insert(Message message);
	
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
	public Message queryMessageById(int mid);
	
	/**
	 * 修改消息信息
	 * @param mid
	 * @return
	 */
	public int update(Message message);
	
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
	 * 查询所有的消息
	 * @return
	 */
	public List<Message> queryAllMessages();
	
	
	public List<Message>  queryMesAndUser();
}
