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
	
	/**
	 * 根据openid查询投票人的信息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryVoteInfoByOpenid
	 * 创建人：chenxu 
	 * 时间：2016年11月21日-下午2:34:44 
	 * 手机:
	 * @param openid
	 * @return VoteInfo
	 * @exception 
	 * @since  1.0.0
	 */
	VoteInfo queryVoteInfoByOpenid(String openid);
	
	/**
	 * 更新信息通过openid
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：updateVoteInfoByOpenid
	 * 创建人：chenxu 
	 * 时间：2016年11月22日-上午10:38:53 
	 * 手机:
	 * @param voteInfo
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int updateVoteInfoByOpenid(VoteInfo voteInfo);
	
	
	/**
	 * 批量新增用户信息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：insertBatch
	 * 创建人：chenxu 
	 * 时间：2016年11月23日-下午3:25:58 
	 * 手机:
	 * @param voteInfos
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int insertBatch(List<VoteInfo> voteInfos);
}
