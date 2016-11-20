package com.ztace.vote.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztace.message.web.Ajax;
import com.ztace.message.web.AjaxReturn;
import com.ztace.vote.entity.UserInfo;
import com.ztace.vote.entity.VoteCount;
import com.ztace.vote.entity.VoteInfo;
import com.ztace.vote.service.UserInfoService;
import com.ztace.vote.service.VoteCountService;
import com.ztace.vote.service.VoteService;
import com.ztace.vote.util.CheckUtil;

import net.sf.json.JSONObject;

/**
 * 投票管理控制
 * 
 * @author cx
 *
 */
@Controller
@RequestMapping("/vote")
public class VoteController {
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	private Logger logger;

	@Autowired
	private VoteCountService voteCountService;
	/**
	 * 数据传递接口
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/entry")
	public void entryWechat(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("消息传递");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		PrintWriter out;
		try {
			out = response.getWriter();
			if (CheckUtil.check(signature, timestamp, nonce)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 投票初始页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String voteIndex(Model model) {
		System.out.println("进入");
///*		try {
//			WeChatOauth2Token wt = WeChatUtil.getOauth2Token(code);
//			// 验证token是否过期
//			WeChatUtil.checkToken(wt.getAccessToken(), wt.getOpenId());
//			// 获取用户信息
//			VoteInfo snsUserInfo = WeChatUtil.getOauth2UserInfo(wt.getAccessToken(), wt.getOpenId());
//			
//			// 将用户openId放入session中
//			request.getSession().setAttribute("openId", snsUserInfo.getOpenid());
//			// 设置要传递的参数
//			request.setAttribute("snsUserInfo", snsUserInfo);
//			System.out.println("用户信息:" + snsUserInfo.toString());
//			// 将用户信息存入数据库
//			// DbUtil.insertUserInfo(snsUserInfo);
//			voteService.save(snsUserInfo);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}*/
		VoteInfo voteInfo=new VoteInfo();
		voteInfo.setOpenid("jdksdjskdjskjdjskdskdks");
		voteInfo.setNickname("cxxcx");
		JSONObject json = JSONObject.fromObject(voteInfo);
		model.addAttribute("voteInfo", json);
		return "/vote/index";
	}

	/**
	 * 查询注册用户的信息
	 */
	@ResponseBody
	@RequestMapping("/index/data")
	public List<UserInfo> voteData() {
		
		List<UserInfo> userInfos=userInfoService.queryAllUserInfo();
		return userInfos;
	}
	
	/**
	 * 
	 * 投票接口
	 * 方法名：votePoll
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午1:57:36 
	 * 手机:
	 * @param id
	 * @param openid
	 * @return AjaxReturn
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/index/poll")
	public AjaxReturn votePoll(@RequestParam("id") int id,@RequestParam("voterId") String voterId){
		AjaxReturn result=Ajax.fail().setMsg("投票失败");
		System.out.println("注册用户编号id："+id+"投票者的openid："+voterId);
		VoteCount voteCount=new VoteCount();
		voteCount.setUserid(id);
		voteCount.setOpenid(voterId);
		//查询是否已经投票
		VoteCount voteCount2=voteCountService.queryByopenidAnduserid(voteCount);
		if(voteCount2==null){
			if(voteCountService.save(voteCount)>0){
				result.setOk(true).setMsg("Vote Success");
			}
		}else{
			result.setOk(true).setMsg("Vote Exits");
		}
		
		return result;
	}
	
	/**
	 * 
	 * 注册用户详情页，显示投票人员
	 * 方法名：userDetail
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午4:23:32 
	 * 手机:
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/detail")
	public String userDetail(@RequestParam("userid") int userid,Model model){
		//查询注册用户信息显示
		//UserInfo userInfo=userInfoService.queryUserInfoByUserid(userid);
		UserInfo userInfo=userInfoService.queryUserInfoByid(userid);
		int num=voteCountService.countVoteByUserId(userid);
		if(null!=userInfo){
			userInfo.setVotenum(num);	
		}
		System.err.println("查询指定用户的信息:"+userInfo);
		model.addAttribute("userInfo", userInfo);
		
		//查询指定用户的投票者信息
		List<VoteInfo> voteInfos=voteService.queryVoteInfoByUserid(userid);
		if(voteInfos.size()>0){
			model.addAttribute("voteInfos", voteInfos);
		}
		return "/vote/detail";
	}
	
	/**
	 * 投票规则
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：rule
	 * 创建人：chenxu 
	 * 时间：2016年11月20日-上午10:14:58 
	 * 手机:
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/rule")
	public String rule(){
		return "/vote/rule";
	}
	/**
	 * 测试统计数量
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：countVoteByUserId
	 * 创建人：chenxu 
	 * 时间：2016年11月19日-下午11:40:19 
	 * 手机: void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/count")
	public void countVoteByUserId(){
		int id=1;
		int result=voteCountService.countVoteByUserId(id);
		System.err.println("投票统计数:"+result);
	}
}
