package com.ztace.vote.service.servicelmp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztace.vote.entity.VoteInfo;
import com.ztace.vote.mapper.VoteMapper;
import com.ztace.vote.service.VoteService;


/**
 * 微信投票服务层实现
 * @author cx
 *
 */
@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	private VoteMapper voteMapper;
	
	@Override
	public int save(VoteInfo voteInfo) {
		// TODO Auto-generated method stub
		int result=0;
		if(null!=voteInfo){
			result=voteMapper.insert(voteInfo);
		}
		return result;
	}

	@Override
	public List<VoteInfo> queryVoteInfoByUserid(int userid) {
		// TODO Auto-generated method stub
		return voteMapper.queryVoteInfoByUserid(userid);
	}

}
