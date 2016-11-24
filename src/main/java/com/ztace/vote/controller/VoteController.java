package com.ztace.vote.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztace.message.web.Ajax;
import com.ztace.message.web.AjaxReturn;
import com.ztace.vote.entity.AccessToken;
import com.ztace.vote.entity.EncodeTicket;
import com.ztace.vote.entity.UserInfo;
import com.ztace.vote.entity.VoteCount;
import com.ztace.vote.entity.VoteInfo;
import com.ztace.vote.entity.WeChatOauth2Token;
import com.ztace.vote.service.UserInfoService;
import com.ztace.vote.service.VoteCountService;
import com.ztace.vote.service.VoteService;
import com.ztace.vote.util.ReadFileUtil;
import com.ztace.vote.util.TokenUtil;
import com.ztace.vote.util.WeChatUtil;

import net.sf.json.JSONArray;
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
	private static final transient Log log = LogFactory.getLog(VoteController.class);
	private static final int MAX_VOTE=2;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	private Logger logger;

	@Autowired
	private VoteCountService voteCountService;
	
	/**
	 * 投票初始页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String voteIndex(Model model,@RequestParam("code") String code,HttpServletRequest request) {
		VoteInfo snsUserInfo=null;
		try {
			WeChatOauth2Token wt = WeChatUtil.getOauth2Token(code);
			// 验证token是否过期
			WeChatUtil.checkToken(wt.getAccessToken(), wt.getOpenId());
			// 获取用户信息
			snsUserInfo = WeChatUtil.getUserInfoByopenid(wt.getAccessToken(), wt.getOpenId());
			
			// 将用户openId放入session中
			request.getSession().setAttribute("openId", snsUserInfo.getOpenid());
			// 设置要传递的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
			VoteInfo voteInfo=voteService.queryVoteInfoByOpenid(snsUserInfo.getOpenid());
			if(voteInfo==null){
				// 将用户信息存入数据库
				voteService.save(snsUserInfo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(snsUserInfo);
		model.addAttribute("voteInfo", json);
		return "/vote/index1";
	}
	
	
	/***
	 * 测试投票
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：voteIndex
	 * 创建人：chenxu 
	 * 时间：2016年11月21日-下午4:48:15 
	 * 手机:
	 * @param model
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	/*@RequestMapping("/index")
	public String voteIndex(Model model) {
		log.info("进入");
		VoteInfo voteInfo=new VoteInfo();
		voteInfo.setOpenid("ogERLwGst34lgmhBUlAWaHHq13DY");
		voteInfo.setNickname("cxxcx");
		JSONObject json = JSONObject.fromObject(voteInfo);
		model.addAttribute("voteInfo", json);
		return "/vote/index1";
	}
*/
	/**
	 * 初始化数据库
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：init
	 * 创建人：chenxu 
	 * 时间：2016年11月23日-下午3:01:46 
	 * 手机: void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/init")
	public void init(){
		String access_token="3on4O2tggIcQ7NsE_FPYZq_9I7Ke2ruNTnQQuoQt53m1_MdmZlB1cnjeTY666pCecVj6sSgV64Pxa62Elk27vAVG70HCJj4CUOrGYKHqX-7UL7smVUfXP2HNo5cO_cK9YQHdAJAUMD";
		String JsonContext = new ReadFileUtil().ReadFile("/Users/cx/Downloads/fans-openid.json");
		JSONObject jsonObject=JSONObject.fromObject(JsonContext);
		JSONObject jsonObject2=JSONObject.fromObject(jsonObject.getString("data"));
		JSONArray jsonArray=JSONArray.fromObject(jsonObject2.getString("openid"));
		List<VoteInfo> voteInfos=new ArrayList<>();
		int size=jsonArray.size();
		for(int i=0;i<size;i++)
		{
			String openid=jsonArray.get(i).toString();
			VoteInfo voteInfo=WeChatUtil.getUserInfoByopenid(access_token, openid);
			voteInfos.add(voteInfo);
			}
		System.err.println(voteInfos);
		voteService.insertBatch(voteInfos);
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
	 * @param openid	投票人的openid
	 * @return AjaxReturn
	 * @throws UnsupportedEncodingException 
	 * @exception 
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("/index/poll")
	public AjaxReturn votePoll(@RequestParam("id") int id,@RequestParam("openid") String openid) throws UnsupportedEncodingException{
		AjaxReturn result=Ajax.fail().setMsg("投票失败");
		
		VoteInfo voteInfo=voteService.queryVoteInfoByOpenid(openid);
		int isfollow=voteInfo.getIsfollow();
		
		//判断用户是否关注
		if(isfollow==1){
			VoteCount voteCount=new VoteCount();
			voteCount.setUserid(id);
			voteCount.setOpenid(openid);
			//查询当前openid总共投了多少票
			Map<String, String> maps=new HashMap<>();
			maps.put("issue", "1");
			maps.put("openid", openid);
			int num=voteCountService.countVoteByopenidAndissue(maps);
			if(num<MAX_VOTE){
				//查询是否已经投票			
				VoteCount voteCount2=voteCountService.queryByopenidAnduserid(voteCount);
				if(voteCount2==null){
					//已经投票
					if(voteCountService.save(voteCount)>0){
						result.setOk(true).setMsg("Vote Success");
					}
				}else{
					result.setOk(false).setMsg("Vote Exits");
				}
				
			}else{
				//投票超过了2票
				result.setOk(false).setErrCode("max").setMsg("Max");
			}
			
		}else{
			
			//获取全局的AccessToken
			//AccessToken accessToken=WeChatUtil.getAccessToken();
			AccessToken accessToken=TokenUtil.accessToken;
			String accss_token=accessToken.getAccess_token();
			
			//创建ticket的请求参数
			String postJson = "{\"expire_seconds\": 300, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":"+id+"}}}";
			System.err.println("postJson:"+postJson);
			EncodeTicket encodeTicket=WeChatUtil.getTicket(accss_token, postJson);
			
			String ticket=URLEncoder.encode(encodeTicket.getTicket(),   "utf-8");
			String imgsrc=WeChatUtil.GET_ENCODE.replace("TICKET", ticket);
			System.err.println("urlencode"+ticket);
			result.setOk(false).setErrCode("notfollow").setData(imgsrc);
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
