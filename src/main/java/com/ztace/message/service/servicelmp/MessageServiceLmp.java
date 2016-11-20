package com.ztace.message.service.servicelmp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztace.message.entity.Message;
import com.ztace.message.mapper.MessageMapper;
import com.ztace.message.service.MessageService;

@Service
public class MessageServiceLmp implements MessageService {

	

	@Autowired
	private MessageMapper messageMapper;
	

	public List<Message> findAllMessages() {
		// TODO Auto-generated method stub
		return messageMapper.queryAllMessages();
	}

	public int save(Message message) {
		// TODO Auto-generated method stub
		return messageMapper.insert(message);
	}

	public Message findMessageById(int mid) {
		// TODO Auto-generated method stub
		return messageMapper.queryMessageById(mid);
	}

	public int modify(Message message) {
		// TODO Auto-generated method stub
		return messageMapper.update(message);
	}

	public int delete(int mid) {
		// TODO Auto-generated method stub
		return messageMapper.delete(mid);
	}

	public int deleteBatch(int[] mids) {
		// TODO Auto-generated method stub
		return messageMapper.deleteBatch(mids);
	}

	public int insertBatch(List<Message> messages) {
		// TODO Auto-generated method stub
		return messageMapper.insertBatch(messages);
	}

	public List<Message> queryMesAndUser() {
		// TODO Auto-generated method stub
		return messageMapper.queryMesAndUser();
	}

}
