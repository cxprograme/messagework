package com.ztace.vote.service;

import java.util.List;

import com.ztace.vote.entity.VoteInfo;

/**
 * 微信投票服务层接口
 * @author cx
 *
 */
public interface VoteService {
	/**
	 * 新增
	 * @param userInfo
	 * @return
	 */
	int save(VoteInfo voteInfo);
	
	
	/**
	 * 查询指定用户的投票者信息详情
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryVoteInfoByUserid
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-上午8:04:18 
	 * 手机:
	 * @param userid
	 * @return VoteInfo
	 * @exception 
	 * @since  1.0.0
	 */
	List<VoteInfo> queryVoteInfoByUserid(int userid);
}
