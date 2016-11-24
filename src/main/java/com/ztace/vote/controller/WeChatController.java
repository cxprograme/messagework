package com.ztace.vote.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ztace.vote.entity.AccessToken;
import com.ztace.vote.entity.VoteCount;
import com.ztace.vote.entity.VoteInfo;
import com.ztace.vote.service.VoteCountService;
import com.ztace.vote.service.VoteService;
import com.ztace.vote.util.CheckUtil;
import com.ztace.vote.util.MessageUtil;
import com.ztace.vote.util.WeChatUtil;

/**
 * 微信消息请求，响应，事件推送控制层
 * 
 * WeChatController
 * 创建人:chenxu 
 * 时间：2016年11月22日-上午9:37:05 
 * @version 1.0.0
 *
 */

@Controller
@RequestMapping("/wc")
public class WeChatController {
	
	@Autowired 
	private VoteService voteService;
	
	@Autowired
	private VoteCountService voteCountService;
	/**
	 * 数据传递接口
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/entry",method=RequestMethod.GET)
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
	 * 消息的响应与回复
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：encodeEvent
	 * 创建人：chenxu 
	 * 时间：2016年11月22日-上午9:20:15 
	 * 手机:
	 * @param request
	 * @param response void
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value="/entry",method=RequestMethod.POST)
	public void encodeEvent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
				}
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					//获得发送方的openid(此处及投票人的openid)
					String openid=map.get("FromUserName");
					//先到vote_details中的判断用户是否关注 如果未关注，把关注状态改为1
					VoteInfo voteInfo=voteService.queryVoteInfoByOpenid(openid);
					if(voteInfo!=null){
						//授权进入的用户，且用户信息以保存，未改关注状态
						if(voteInfo.getIsfollow()==0){
							voteInfo.setIsfollow(1);
							voteService.updateVoteInfoByOpenid(voteInfo);
						}
					}else{
						//该用户不存，通过关注公众号方式投票
						AccessToken accessToken=WeChatUtil.getAccessToken();
						
						String accss_token=accessToken.getAccess_token();
						VoteInfo voteInfo2=WeChatUtil.getUserInfoByopenid(accss_token, openid);
						//将用户信息保存到数据库中，并将关注状态更改为1
						voteInfo2.setIsfollow(1);
						voteService.save(voteInfo2);
					}
					
					//扫描了带参数的二维码
					//获取到二维码中参数
					String key=map.get("EventKey");
					if(key!=null&&!key.equals("")){
						int id=Integer.parseInt(key.substring(key.lastIndexOf("_")+1).trim());
						//将userid==key  openid==openid 插入到vote_count 中，即投票
						VoteCount voteCount=new VoteCount();
						voteCount.setUserid(id);
						voteCount.setOpenid(openid);
						VoteCount voteCount1=voteCountService.queryByopenidAnduserid(voteCount);
						if(voteCount1==null){
							//如果没有投票则投票
							voteCountService.save(voteCount);
						}
					}
				}else if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(eventType)){
					//取消关注触发事件
					VoteInfo voteInfo=voteService.queryVoteInfoByOpenid(fromUserName);
					if(null!=voteInfo){
						if(voteInfo.getIsfollow()==1){
							voteInfo.setIsfollow(0);
							//更新关注状态
							voteService.updateVoteInfoByOpenid(voteInfo);
						}
					}
					
				}
				else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					System.err.println(map);
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}else if(MessageUtil.MESSAGE_SCAN.equals(eventType)){	//用户已关注
					String key=map.get("EventKey");
					System.err.println(key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}
			
			System.out.println(message);
			
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}

	}

}
