$(function(){
			var url = window.location.href,
			indexReg = /index/,
			registerReg = /register/,
			searchReg = /search/,
			detailReg = /detail/,
			limit = 10,
			offset = 0;
			var voteFn={
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
							str += '<li>'        
				                + '<div class="head">'
				                + '<a href="/vote/detail?userid=' + objs[i].id + '">'
				                + '<img src="' + objs[i].headimgurl + '" alt="">'
				                + '</a>'
				                + '</div>'
				                + '<div class="up">'
				                + '<div class="vote">'
				                + '<span>' + objs[i].votenum + '票</span>'
				                + '</div>'
				                + '<div class="btn" id=' + objs[i].id + '>'
				                + '投TA一票'
				                + '</div>'
				                + '</div>'
				                + '<div class="descr">'
				                + '<a href="/vote/detail/' + objs[i].id + '">'
				                + '<div>'
				                + '<span>' + objs[i].nickname + '</span>'
				                + '<span>|</span>'
				                + '<span>编号#' + objs[i].id + '</span>'
				                + '</div>'
				                + '</a>'
				                + '</div>'
				               	+ '</li>';
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
					/**
					 * [发起投票请求]
					 * @param  {String} id      [被投票者id号]
					 * @param  {String} voterId [投票者id号]
					 * @param  {Element} _this  [元素]
					 */
					voteRequest: function(id, voterId, _this) {
						$.ajax({
							url: '/vote/index/poll?id=' + id + '&voterId=' + voterId,
							type: 'GET',
							success: function(data) {
								if(data.ok){
									parent.layer.msg(data.msg,{icon:1,time:2000,shade: [0.8, '#393D49']}, function(){
										parent.layer.close(parent.layer.getFrameIndex(window.name)); //关闭弹窗
								
									});
								}
								/* data = JSON.parse(data);
								if(data.errno === 0) {
									var voteNum = $(_this).siblings('.vote').children('span').html();
									var reg = /(\d*)/;
									voteNum = reg.exec(voteNum)[1];
									$(_this).siblings('.vote').children('span').html(++voteNum + '票');
									$(_this).siblings('.vote').addClass('bounceIn');
								}else {
									alert(data.msg);
								} */
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
					}
				});
				
				
			}
			
		})