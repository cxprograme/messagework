/**
 * 页面加载
 */
$(function(){
	render();
	var content = "客官，来啦，坐吧！<br/>回复[查看]收取更多精彩内容。";
	content += "<br/>回复[帮助]可以查看所有可用的指令。";
	// 添加公众号的开场白
	appendDialog("talk_recordbox","公众号",content);
	render();
});

/**
 * 发送消息
 * @param basePath
 */
function send() {
	var content = $("#content").val();
	if(!content) {		      //content="",null,undefined,0 
		alert("请输入内容！");
		return;
	}
	$.ajax({
		url : $("#basePath").val() + "AutoReplayServlet",
		type : "POST",
		dataType : "text",
		timeout : 10000,
		success : function (data) {
			appendDialog("talk_recordboxme","My账号",content);  //将我发送的内容显示在容器中
			appendDialog("talk_recordbox","公众号",data);			//将回复内容显示在容器中
			$("#content").val("");
			render();
		},
		data : {"content":content}
	});
}

/**
 * 渲染方法，加载滚动条
 */
function render() {
	 // 将要应用 jScrollPane 的标签
    var $el = $('#jp-container').jScrollPane({
        verticalGutter  : -16
    }),
    // 扩展的函数和参数    
    extensionPlugin = {
        extPluginOpts   : {
        // 鼠标移开容器多少毫秒后滚动条消失
        mouseLeaveFadeSpeed : 100,
        // 鼠标移到容器内的元素上多少毫秒后滚动条消失
        hovertimeout_t : 1000,
        //如果设置为false,当鼠标移动到滚动条上时滚动条就会显示，离开时就隐藏
        //如果设置为 true,同样会出现上面的情况，但是滚动条仍然会在鼠标移到容器内元素上 hovertimeout_t 毫秒后隐藏
        //而且当我们开始滚动后就显示，停止滚动就隐藏。
        useTimeout : true,
        //该扩展用于 插件宽度>设备宽度 的设备
        deviceWidth : 980
       },
        hovertimeout: null, // 多长时间后滚动条消失
        isScrollbarHover: true,// true if the mouse is over the scrollbar
        elementtimeout: null,   // avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar
        isScrolling : false,// true if scrolling
        addHoverFunc: function() {
            // run only if the window has a width bigger than deviceWidth
            if( $(window).width() <= this.extPluginOpts.deviceWidth ) return false;
            var instance = this;
            // functions to show / hide the scrollbar
            $.fn.jspmouseenter  = $.fn.show;
            $.fn.jspmouseleave  = $.fn.fadeOut;
            // hide the jScrollPane vertical bar
            var $vBar = this.getContentPane().siblings('.jspVerticalBar').hide();
            /*
            * mouseenter / mouseleave events on the main element
            * also scrollstart / scrollstop - @James Padolsey : http://james.padolsey.com/javascript/special-scroll-events-for-jquery/
            */
            $el.bind('mouseenter.jsp',function() {
            // show the scrollbar
            $vBar.stop( true, true ).jspmouseenter();
            if( !instance.extPluginOpts.useTimeout ) return false;
            // hide the scrollbar after hovertimeout_t ms
            clearTimeout( instance.hovertimeout );
            instance.hovertimeout   = setTimeout(function() {
            // if scrolling at the moment don't hide it
            if( !instance.isScrolling )
                $vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
            }, instance.extPluginOpts.hovertimeout_t );
        }).bind('mouseleave.jsp',function() {
            // hide the scrollbar
            if( !instance.extPluginOpts.useTimeout )
                $vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
            else {
                clearTimeout( instance.elementtimeout );
                if( !instance.isScrolling )
                    $vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
                }
                                 
            });
                             
            if( this.extPluginOpts.useTimeout ) {
            $el.bind('scrollstart.jsp', function() {
            // when scrolling show the scrollbar
            clearTimeout( instance.hovertimeout );
            instance.isScrolling = true;
            $vBar.stop( true, true ).jspmouseenter();
            }).bind('scrollstop.jsp', function() {
            // when stop scrolling hide the scrollbar (if not hovering it at the moment)
            clearTimeout( instance.hovertimeout );
            instance.isScrolling = false;
            instance.hovertimeout = setTimeout(function() {
            if( !instance.isScrollbarHover )
                $vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
            }, instance.extPluginOpts.hovertimeout_t );
        });
          // wrap the scrollbar
          // we need this to be able to add the mouseenter / mouseleave events to the scrollbar
          var $vBarWrapper  = $('<div/>').css({
          position  : 'absolute',
          left: $vBar.css('left'),
          top   : $vBar.css('top'),
          right: $vBar.css('right'),
          bottom: $vBar.css('bottom'),
          width : $vBar.width(),
          height : $vBar.height()
        }).bind('mouseenter.jsp',function() {
            clearTimeout( instance.hovertimeout );
            clearTimeout( instance.elementtimeout );
            instance.isScrollbarHover   = true;
            // show the scrollbar after 100 ms.
            // avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar                             
            instance.elementtimeout = setTimeout(function() {
                $vBar.stop( true, true ).jspmouseenter();
            }, 100 );  
            }).bind('mouseleave.jsp',function() {
            // hide the scrollbar after hovertimeout_t
            clearTimeout( instance.hovertimeout );
            instance.isScrollbarHover   = false;
            instance.hovertimeout = setTimeout(function() {
            // if scrolling at the moment don't hide it
            if( !instance.isScrolling )
                $vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
            }, instance.extPluginOpts.hovertimeout_t );
                });
                             
            $vBar.wrap( $vBarWrapper );
            }
        }
    },
                     
    // the jScrollPane instance
    jspapi = $el.data('jsp');
    // extend the jScollPane by merging
    $.extend( true, jspapi, extensionPlugin );
    jspapi.addHoverFunc();
}

/**
 * 向聊天记录中添加聊天内容
 * @param myClass 添内容的样式
 * @param name 发送消息的账号名称
 * @param content 发送的内容
 */
function appendDialog(myClass,name,content) {
	var div = "";
	div += "<div class='" + myClass + "'>";
	div += "<div class='user'><img src='" + $("#basePath").val() + "static/images/thumbs/" + myClass + ".jpg'/>" + name + "</div>";
	div += "<div class='talk_recordtextbg'>&nbsp;</div>";
	div += "<div class='talk_recordtext'>";
	div += "<h3>" + content + "</h3>";
	div += "<span class='talk_time'>" + getCurrentDate() + "</span>";
	div += "</div>";
	div += "</div>";
	$('#jp-container').children().eq(0).children().eq(0).append(div);
}

/**
 * 获取当前系统时间
 * @returns {String} 当前系统时间
 */
function getCurrentDate() {
	var date = new Date();
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
}