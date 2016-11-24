package com.ztace.vote.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztace.message.web.Ajax;
import com.ztace.message.web.AjaxReturn;
import com.ztace.vote.entity.UserInfo;
import com.ztace.vote.service.UserInfoService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 注册页面
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：admin
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-下午4:00:10 
	 * 手机:
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/register")
	public String admin(HttpServletRequest request,Model model){
		return "vote/register";
	}
	
	/**
	 * 保存注册用户信息
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：saveUser
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-下午5:59:45 
	 * 手机:
	 * @return AjaxReturn
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public AjaxReturn saveUser(UserInfo userInfo){
		AjaxReturn result=Ajax.fail().setMsg("保存失败");
		System.err.println(userInfo);
		if(userInfoService.save(userInfo)>0){
			result.setOk(true).setMsg("保存成功");
		}
		return result;
	}
}
