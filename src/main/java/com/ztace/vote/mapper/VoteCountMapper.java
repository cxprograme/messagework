package com.ztace.vote.mapper;

import com.ztace.vote.entity.VoteCount;

/**
 * 
 * 投票统计数据层
 * VoteCountMapper
 * 创建人:chenxu 
 * 时间：2016年11月19日-下午2:19:13 
 * @version 1.0.0
 *
 */
public interface VoteCountMapper {
	
	/**
	 * 
	 * 保存投票统计
	 * 方法名：insert
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午2:21:47 
	 * 手机:
	 * @param voteCount
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int insert(VoteCount voteCount);
	
	/**
	 * 
	 * 查询指定用户id和投票者openid
	 * 方法名：queryByopenidAnduserid
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午3:27:26 
	 * 手机:
	 * @param voteCount
	 * @return VoteCount
	 * @exception 
	 * @since  1.0.0
	 */
	VoteCount queryByopenidAnduserid(VoteCount voteCount);
	
	/**
	 * 查询指定用户id的投票数
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：countVoteByUserId
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午11:03:48 
	 * 手机:
	 * @param userid
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int countVoteByUserId(int userid);
}
