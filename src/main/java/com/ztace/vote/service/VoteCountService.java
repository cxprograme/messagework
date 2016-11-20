package com.ztace.vote.service;

import com.ztace.vote.entity.VoteCount;

/**
 * 投票统计服务层接口
 * 
 * VoteCountService
 * 创建人:chenxu 
 * 时间：2016年11月19日-下午2:22:37 
 * @version 1.0.0
 *
 */
public interface VoteCountService {

	/**
	 * 
	 * 保存投票统计
	 * 方法名：save
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午2:23:14 
	 * 手机:
	 * @param voteCount
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int save(VoteCount voteCount);
	
	/**
	 * 
	 * 查询指定用户id和投票者id
	 * 方法名：queryByopenidAnduserid
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午3:28:51 
	 * 手机:
	 * @param voteCount
	 * @return VoteCount
	 * @exception 
	 * @since  1.0.0
	 */
	VoteCount queryByopenidAnduserid(VoteCount voteCount);
	
	/**
	 * 查询指定用户id的投票统计
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：countVoteByUserId
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午11:04:33 
	 * 手机:
	 * @param userid
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int countVoteByUserId(int userid);
}
