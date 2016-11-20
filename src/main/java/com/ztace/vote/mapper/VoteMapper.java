package com.ztace.vote.mapper;

import java.util.List;

import com.ztace.vote.entity.VoteInfo;

/**
 * 微信投票数据层接口
 * @author cx
 *
 */
public interface VoteMapper {
		
	/**
	 * 新增信息
	 * @param userInfo
	 * @return
	 */
	int insert(VoteInfo voteInfo);
	
	/**
	 * 查询指定用户的投票者详情
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryVoteInfoByUserid
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-上午8:03:39 
	 * 手机:
	 * @param userid
	 * @return VoteInfo
	 * @exception 
	 * @since  1.0.0
	 */
	List<VoteInfo> queryVoteInfoByUserid(int userid);
}
