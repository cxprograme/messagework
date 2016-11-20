package com.ztace.vote.service.servicelmp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztace.vote.entity.UserInfo;
import com.ztace.vote.mapper.UserInfoMapper;
import com.ztace.vote.service.UserInfoService;

@Service
public class UserInfoServicelmpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	@Override
	public List<UserInfo> queryAllUserInfo() {
		// TODO Auto-generated method stub
		return userInfoMapper.queryAllUserInfo();
	}


	@Override
	public UserInfo queryUserInfoByUserid(int userid) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserInfoByUserid(userid);
	}


	@Override
	public UserInfo queryUserInfoByid(int userid) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserInfoByid(userid);
	}


	@Override
	public int save(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.insert(userInfo);
	}

}
