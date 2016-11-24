$(function(){
			var url = window.location.href,
			indexReg = /index/,
			registerReg = /register/,
			searchReg = /search/,
			detailReg = /detail/,
			limit = 10,
			offset = 0;
			var voteFn={
					init:function(){
						$("img").on("error",function(){
							$(this).attr("src","../static/images/vote/noimage.gif");	
						})
					},
					/**
					 * [将数据存储在本地]
					 * @param {String} key [键值]
					 * @param {Object} obj [存储内容]
					 */
					setStorage: function(key, obj) {
						localStorage.setItem(key, JSON.stringify(obj));
					},

					/**
					 * [获取本地存储数据]
					 * @param {String} key [键值］
					 * @return {Object}    [存储内容]
					 */
					getStorage: function(key) {
						return JSON.parse(localStorage.getItem(key));
					},

					/**
					 * [删除本地存储数据]
					 * @param {String} key [键值］
					 */
					delteStorage: function(key) {
						localStorage.removeItem(key);
					},
					
					/**
					 * [拼接首页用户信息字符串]
					 * @param  {Array} objs [用户信息数组]
					 * @return {String}     [用户信息字符串]
					 */
					userStr: function(objs) {
						var str = '';
						for(var i=0; i<objs.length; i++) {
							str+="<li>"+
									"<div class='head'>"+
									"<a href='/vote/detail?userid=" + objs[i].id + "'> <img src='" + objs[i].headimgurl + "' alt=''></a>"+
									"</div>"+
									"<div class='descr'>"+
									"<a href='/vote/detail'>"+
									"<div>"+
									"<span>" + objs[i].nickname + "</span>"+
									"</div>"+
									"<p>编号#" + objs[i].id + "</p>"+
									"</a>"+
									"</div>"+
									"<div class='autophoto'>"+
									"<img src='../"+objs[i].photo +"' alt=''>"+
									"</div>"+
									"<div class='up'>"+
									"<div class='vote'>"+
									"<span class='vm"+objs[i].id+"' data-userid='"+objs[i].id+"'>"+ objs[i].votenum +"</span>"+
									"<span>票</span>"+
									"</div>"+
									"<div class='btn'  id='"+ objs[i].id + "'>投TA一票</div>"+
									"</div>"+
									"</li>";
						}
						return str;
					},
					
					/**
					 * [拼接个人详细页信息字符串]
					 * @param  {Object} obj [个人信息对象]
					 * @return {String}     [个人信息字符串]
					 */
					detailPersonalStr: function(obj) {
						var str ='<div class="pl">'
								+'<div class="head">'
								+'<img src="' + obj.head_icon + '" alt="">'
								+'</div>'
								+'<div class="p_descr">'
								+'<p>' + obj.username + '</p>'
								+'<p>编号#' + obj.id + '</p>'
								+'</div>'
							    +'</div>'
							    +'<div class="pr">'
								+'<div class="p_descr pr_descr">'
								+'<p>' + obj.vote + '票</p>'
								+'</div>'
							    +'</div>'
							    +'<div class="motto">'
								+'' + obj.description + ''
								+'</div>';
						return str;
					},
					
					/**
					 * [投票事件绑定]
					 */
					userPoll: function() {
						$('.btn').off();
						$('.btn').click(function(event) {
							var _this = this;
							var id = $(this).attr('id');
							
							var voteUser = voteFn.getStorage('voteInfo');
							if (voteUser) {
								voteFn.voteRequest(id, voteUser.openid, this);
								
							}else {
							 /*    $('.mask').show();
								voteFn.signIn(); */ 
							}
							
						});
					},
					
					hideEncode:function(obj){
						$(".mask").click(function(){
							obj.hide();
						});
					},
					/**
					 * [发起投票请求]
					 * @param  {String} id      [被投票者id号]
					 * @param  {String} voterId [投票者id号]
					 * @param  {Element} _this  [元素]
					 */
					voteRequest: function(id, voterId, _this) {
						$.ajax({
							url: '/vote/index/poll?id=' + id + '&openid=' + voterId,
							type: 'GET',
							success: function(data) {
								if(data.ok){
									//未投票
									parent.layer.msg(data.msg,{icon:1,time:1000,shade: [0.8, '#393D49']}, function(){
										parent.layer.close(parent.layer.getFrameIndex(window.name)); //关闭弹窗
										
									});
									var val=$(".vm"+id).text();
									console.log(val);
									val++;
									console.log(val);
									$(".vm"+id).text(val);
								}else if(!data.ok){
									console.log(data);
									//未关注
									if(data.errCode=="notfollow"){
										console.log(111);
										console.log(data.data);
										$(".mask").show();
										$(".mask img").attr("src",data.data);
										voteFn.hideEncode($(".mask"));
									}else if(data.errCode=="max"){
										//投票数量达上限
										parent.layer.msg(data.msg,{icon:1,time:2000,shade: [0.8, '#393D49']}, function(){
											parent.layer.close(parent.layer.getFrameIndex(window.name)); //关闭弹窗
											
										});
									}else{
										//已投票
										parent.layer.msg(data.msg,{icon:1,time:2000,shade: [0.8, '#393D49']}, function(){
											parent.layer.close(parent.layer.getFrameIndex(window.name)); //关闭弹窗
											
										});
									}
								}
							}
						});
					},
			}
			
			
			if(indexReg.test(url)){
				var voteInfo=$("body").data("vote");
				voteFn.setStorage("voteInfo", voteInfo);
				$.ajax({
					url: '/vote/index/data',
					type: 'GET',
					contentType:'application/json',
					dataType: 'json',
                    cache:false,
					success: function(data) {
						$('.coming').append(voteFn.userStr(data));
						voteFn.userPoll();
						voteFn.init();
					}
				});
				
				
			}
			
		
			
		})