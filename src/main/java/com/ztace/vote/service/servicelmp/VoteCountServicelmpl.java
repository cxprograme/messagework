package com.ztace.vote.service.servicelmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztace.vote.entity.VoteCount;
import com.ztace.vote.mapper.VoteCountMapper;
import com.ztace.vote.service.VoteCountService;

/**
 * 
 * 投票统计实现层
 * VoteCountServicelmpl
 * 创建人:chenxu 
 * 时间：2016年11月19日-下午2:24:02 
 * @version 1.0.0
 *
 */
@Service
public class VoteCountServicelmpl implements VoteCountService{

	@Autowired
	private VoteCountMapper voteCountMapper;

	@Override
	public int save(VoteCount voteCount) {
		// TODO Auto-generated method stub
		int result=0;
		if(null!=voteCount){
			result=voteCountMapper.insert(voteCount);
		}
		return result;
	}

	@Override
	public VoteCount queryByopenidAnduserid(VoteCount voteCount) {
		// TODO Auto-generated method stub
		return voteCountMapper.queryByopenidAnduserid(voteCount);
	}

	@Override
	public int countVoteByUserId(int userid) {
		// TODO Auto-generated method stub
		return voteCountMapper.countVoteByUserId(userid);
	}

}
