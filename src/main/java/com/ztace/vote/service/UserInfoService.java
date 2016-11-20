package com.ztace.vote.service;

import java.util.List;

import com.ztace.vote.entity.UserInfo;

/**
 * 
 * 注册用户服务层
 * UserInfoService
 * 创建人:chenxu 
 * 时间：2016年11月19日-上午9:47:29 
 * @version 1.0.0
 *
 */
public interface UserInfoService {

	/**
	 * 
	 * 查询注册用户信息
	 * 方法名：queryAllUserInfo
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-上午9:48:11 
	 * 手机:
	 * @return List<UserInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	List<UserInfo> queryAllUserInfo();
	
	/**
	 * 查询指定用户的信息(包含统计投票)
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryUserInfoByUserid
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午11:39:20 
	 * 手机:
	 * @param userid
	 * @return UserInfo
	 * @exception 
	 * @since  1.0.0
	 */
	UserInfo queryUserInfoByUserid(int userid);
	
	/**
	 * 查询指定用户的详细信息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：queryUserInfoByid
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-上午8:57:36 
	 * 手机:
	 * @param userid
	 * @return UserInfo
	 * @exception 
	 * @since  1.0.0
	 */
	UserInfo queryUserInfoByid(int userid);
	
	
	/**
	 * 保存用户信息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：insert
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-下午6:10:59 
	 * 手机:
	 * @param userInfo
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int  save(UserInfo userInfo);
}
