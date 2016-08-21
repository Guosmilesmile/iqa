<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>厦门大学内部质量保障网</title>
    <link rel="shortcut icon" href="../img/LOGO.ico" >
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style-define.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/commonJs.js"></script>
    <script>
    String.prototype.format = String.prototype.f = function () {
        var s = this,
            i = arguments.length;

        while (i--) {
            s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
        }
        return s;
    };
        startList=function(){
            var navRoot=document.getElementById("header-nav");
            var allLi=navRoot.getElementsByTagName("li");
            var sdnav=document.getElementById("nav-sd-bg");
            for(i=0;i<allLi.length;i++){
                node=allLi[i];
                node.onmouseover=function(){
                    if(this.className=="")
                        sdnav.style.visibility="visible";
                    this.className+="current";
                }
                node.onmouseout=function(){
                    this.className=this.className.replace("current","");
                    sdnav.style.visibility="hidden";
                }
            }
        }
        window.onload=startList;
    </script>
        
    <script src="../js/jquery.slideBox.js"></script>
    <style>
        div.slideBox{ position:relative; width:280px; height:190px; overflow:hidden;}
        div.slideBox ul.items{ position:absolute; float:left; background:none; list-style:none; padding:0px; margin:0px;}
        div.slideBox ul.items li{ float:left; background:none; list-style:none; padding:0px; margin:0px;}
        div.slideBox ul.items li a{ float:left; line-height:normal !important; padding:0px !important; border:none/*For IE.ADD.JENA.201206300844*/;}
        div.slideBox ul.items li a img{ width:280px;
            height: 190px;
            margin:0px !important; padding:0px !important; display:block; border:none/*For IE.ADD.JENA.201206300844*/;}
        div.slideBox div.tips{ position:absolute; bottom:0px; width:100%; overflow:hidden;}
        div.slideBox div.tips div.title{   height:20px;}
        div.slideBox div.tips div.title a{
            display: block;
            color:#000;
            text-align: center; text-decoration:none;}
        div.slideBox div.tips div.title a:hover{ text-decoration:underline !important;}
        div.slideBox div.tips div.nums{
            height:20px;
            padding-left:100px;
        }
        div.slideBox div.tips div.nums a{
            display:inline-block;
            float:left/*For IE.ADD.JENA.201206300844*/;
            width: 15px;
            height: 15px;
            text-indent:9999px;
            margin-left:3px;
            overflow:hidden;
            background: url("../img/img-slide-icon.png");
        }
        div.slideBox div.tips div.nums a.active{
            background-position:0px -12px; }
    </style>
    <script>
    function getSlideImage(){
    	$.ajax({
            type: 'get',
            url: "<%=basePath%>getslidenews",
            async:false,
            data: {
            	pagination:"false",
            	rows:5,
            	version:"ch",
            },
            success: function(slidenewsListJsonStr){
            	var ul_body = $("#demo3");
    			
            	var str="<ul class='items'>";
               	
            	var slidenewsList = eval('(' + slidenewsListJsonStr + ')');
            	if(slidenewsList.length > 0){
            		for (var i = 0 ;i < slidenewsList.length;i++){
            			if(slidenewsList[i].sn_imgurl==""){
            				slidenewsList[i].sn_imgurl="images/newModel/pic_11.png";
            			}
            			str+="<li><a href='#' title='"+slidenewsList[i].sn_titles+"'><img src='<%=basePath%>"+slidenewsList[i].sn_imgurl+"' onerror= javascript:this.src='../img/footer-logo.png'></a></li>";
              		}
            		str+="</ul>";
            		
            		ul_body.html(str);
            		$(".sss").click(function(){
       					var id = $(this).attr("id").toString().substring(11);
       					window.location.href="./slidenews.jsp?contentId="+id;
       				});
            		//此处调用图片滚动方法
            		//play();
            		$('#demo3').slideBox({
                        duration : 0.3,//滚动持续时间，单位：秒
                        easing : 'linear',//swing,linear//滚动特效
                        delay : 5,//滚动延迟时间，单位：秒
                        hideClickBar : false,//不自动隐藏点选按键
                        clickBarRadius : 10
                    });
            	}
    		},
            error: function(){}
            	
    	});
    }
    function getLatestNews(){
    	$.ajax({
            type: 'get',
            url: "<%=basePath%>GetCH2NewsList",
            data: {
            	pagination:"true",
            	rows:10,
            	menuId:35,
            	n_subclass:71
            },
            dataType:'json',
            success: function(newsListJsonStr){
            	var ul_body = $("#newslist");
            	var str ="";
            	var newsList = newsListJsonStr.rows;
            	if(newsList.length > 0){
            		for (var i = 0 ;i < newsList.length;i++){
            			if(newsList[i].n_titles==""){
    						newsList[i].n_titles = "  ";
            			}
            			if(newsList[i].n_titles.length>30){
            				newsList[i].n_titles = newsList[i].n_titles.substr(0,30);
            				newsList[i].n_titles+="...";
            			}
            			
    					if(newsList[i].n_summary==""){
    						newsList[i].n_summary = "click in";
            			}
    					var publishTime = newsList[i].n_publishtime;
    					var time = getLocalTime(publishTime);
             			str+="<a href='Third-Page.jsp?contentId="+newsList[i].n_id+"'>"+newsList[i].n_titles+"<span>"+time+"</span></a>";;

            			if (i==1) {
    					str += " <div class='clear'></div>";
    					}
            			
              	}
            		ul_body.html(str);
            		$(".ss").click(function(){
       					var id = $(this).attr("id").toString().substring(11);
       					window.location.href="./document.jsp?contentId="+id;
       				});
            	}
    		},
            error: function(){}
            	
    	});
    }
    function getAnnounce(){
    	$.ajax({
            type: 'post',
            url: "<%=basePath%>GetCH2NewsList",
            data: {
            	pagination:"false",
            	rows:10,
            	menuId:35,
            	n_subclass:70
            },
            async: false,
            dataType:'json',
            success: function(newsListJson){
            	var div_body = $(".news-display-block-content");
            	var newsList = newsListJson.rows;
            	if(newsList.length > 0){
            		var trList = "";
            		var tr = "<a href='{0}' title='{1}'><i></i>{1}<span>{2}</span></a>";
                	for(var i = 0; i < newsList.length; i++)
                	{
                		if(newsList[i].n_titles==""){
    						newsList[i].n_titles = "  ";
            			}
            			if(newsList[i].n_titles.length>30){
            				newsList[i].n_titles = newsList[i].n_titles.substr(0,30);
            				newsList[i].n_titles+="...";
            			}
                		trList = trList+tr.format("Third-Page.jsp?contentId="+newsList[i].n_id, newsList[i].n_titles,getLocalTime(newsList[i].n_publishtime));
                	}
            		div_body.html(trList);
            	}
            	else{
            		var emptyStr = "<li><div class='question'><h3>暂无相关信息</h3>"
        		    +"</div></li>";
        		    div_body.html(emptyStr);
            	}
    		},
            error: function(){}
    	});
    };
    
    function getLocalTime(val) {     
    	if(val != null && val != ""){
    		var date = new Date(val);
            return date.getFullYear() + '-' + addZero((date.getMonth() + 1)) + '-' + addZero(date.getDate());
    	}
    }
    
    //将月份和日不足10的再前面补零
    function addZero(s) {
        return s < 10 ? '0' + s: s;
    }

    
    $(document).ready(function () { 
    	getLatestNews();
    	getSlideImage();
    	getAnnounce();
//     	$("#moreNews").click(function(){
//     		$("#n_subclass").val(1);
//     		$("#getnews").submit();
//     	});
    });
    
    </script>

</head>
<body>
<div id="wrapper">
    <header>
            <div class="header-wrapper">
                <div id="header-logo">
                    <div style="display: inline">
                        <img id="img-LOGO" src="../img/LOGO.png">
                        <div style="display: inline-block">
                            <img id="img-XMU" src="../img/xmu-word.png">
                            <img id="img-IQAWeb" src="../img/IQAWeb.png">
                            <img id="img-XMUIQA" src="../img/XMUIQA.png">
                        </div>
                    </div>
                    <div style="margin: -200px 180px 0 0;text-align: right;vertical-align: top;">
                        <img src="../img/header-logo.png" style="height: 250px">
                    </div>
                    <div style="position:absolute;z-index: 999;">
                        <div style="margin: -100px 0 0 800px;position: relative;width: 285px">
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group" style="width: 204px;">
                                    <input id="key" style="width:146px; height: 24px;background: rgba(255,255,255,0.8);display: inline;padding: 0;"type="text" class="form-control" placeholder="请输入要检索的内容">
                                    <a href="javascript:search()" class="search-icon"></a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div id="header-nav">
                        <ul>
                            <li class="no-sub"><a href="Index.jsp">首页</a></li>
                            <li><a href="iqa_inform.jsp?id=1">新闻公告</a>
                            	<ul style="">
									<li><a href="iqa_inform.jsp?id=1">通知公告</a></li>
									<li><a href="iqa_inform.jsp?id=2">热点新闻</a></li>
									<li><a href="iqa_inform.jsp?id=3">图片新闻</a></li>
								</ul>
                            </li>
                            <li>
                                <a href="iqa_overview.jsp?id=1">IQA体系</a>
								<ul style="left:-150px">
									<li><a href="iqa_overview.jsp?id=1">理论体系</a></li>
									<li><a href="iqa_overview.jsp?id=2">监控流程</a></li>
									<li><a href="iqa_overview.jsp?id=3">组织架构</a></li>
									<li><a href="iqa_overview.jsp?id=4">联合国IQA项目</a></li>
								</ul>
                            </li>
                            <li>
                            	<a href="iqa_file.jsp">政策文件</a>
                            	<ul style="left:-220px">
									<li><a href="./iqa_file.jsp?id=1">上级文件</a></li>
									<li><a href="./iqa_file.jsp?id=2">厦门大学本科教学管理制度</a></li>
									<li><a href="./iqa_file.jsp?id=4">厦门大学新生学业导航</a></li>
									<li><a href="./iqa_file.jsp?id=5">厦门大学教师手册</a></li>
								</ul>
                            </li>
							<li >
								<a href="qualitymonitor.jsp?id=0">质量监控</a>
								<ul style="left:-400px">
									<li><a href="qualitymonitor.jsp?id=0" style="padding-left:20px;padding-right:20px;">年度校内自我评估</a></li>
									<li><a href="qualitymonitor.jsp?id=1" style="padding-left:20px;padding-right:20px;">本科课程教学评测</a></li>
									<li><a href="qualitymonitor.jsp?id=2" style="padding-left:20px;padding-right:20px;">学生学习经历调查</a></li>
									<li><a href="qualitymonitor.jsp?id=3" style="padding-left:20px;padding-right:20px;">常态数据动态监测</a></li>
									<li><a href="qualitymonitor.jsp?id=4" style="padding-left:20px;padding-right:20px;">日常教学质量监督</a></li>
								</ul>
							</li>
							<li>
								<a href="iqa_vertify.jsp?id=1">审核评估</a>
								<ul style="left:-100px">
									<li><a href="iqa_vertify.jsp?id=1">上级文件</a></li>
									<li><a href="iqa_vertify.jsp?id=2">通知公告</a></li>
								</ul>
							</li>
							<li class="no-sub"><a href="iqa_document.jsp">文档下载</a></li>
					  		<li class="no-sub"><a href="../login.jsp">用户登录</a></li> 
						</ul>
                    </div>
                </div>
            </div>
            <div id="nav-bg"></div>
            <div id="nav-sd-bg" style="visibility:hidden;"></div>
            <div style="clear: both"></div>
        </header>

    <div class="body-wrapper">
        <div class="wrapper-inner">
            <!--一个新闻块-->
            <div class="news-display-block" style="margin-top: 20px;height: 395px;overflow: hidden;">
                <div class="news-display-block-title">
                    <span><i></i>热点新闻</span>
                    <a href="iqa_inform.jsp?id=2" style="float:right;color:#f9cc5a;text-decoration: none;">更多</a>
                </div>
                <div id="newslist" class="news-display-block-content" style="height: 274px;overflow: hidden;">
                    
                </div>
            </div>
            <i class="news-display-block-tribg"></i>
            <div class="news-display-block" style="margin-top: 32px;height: 370px;overflow: hidden;">
                <div class="news-display-block-title">
                    <span><i></i>通知公告</span>
                    <a href="iqa_inform.jsp?id=1" style="float:right;color:#f9cc5a;text-decoration: none;">更多</a>
                </div>
                <div class="news-display-block-content" style="height: 239px;overflow: hidden;">
                    

                </div>
            </div>
            <i class="news-display-block-tribg"></i>

        </div>
        <div class="wrapper-sider">
            <!-- 轮播 -->
            <div id="demo3" class="slideBox">

            </div>
            <div class="sider-btn-group">
                <a href="qualitymonitor.jsp?id=0" ><i class="sider-btn-icon1"></i>本科评估</a>
                <a href="http://ssfw.xmu.edu.cn/" ><i class="sider-btn-icon2"></i>课程测评</a>
                <a href="http://yiban.xmu.edu.cn/2015" ><i class="sider-btn-icon3"></i>学习调查</a>
                <a href="../login.jsp" ><i class="sider-btn-icon4"></i>状态数据</a>
                <a href="#" ><i class="sider-btn-icon5"></i>课堂听课</a>
            </div>
            <div class="sider-infor-display">
                <div class="news-display-block-title news-display-block-title-underline">
                <span><i></i>相关链接</span>
                </div>
                <ul>
                    <li><a href="http://www.moe.gov.cn/">教育部</a></li>
                    <li><a href="http://www.heec.edu.cn/">教育部高等教育教学评估中心</a></li>
                    <li><a href="http://www.xmu.edu.cn ">厦门大学</a></li>
                    <li><a href="http://jwc.xmu.edu.cn/">厦门大学教务处</a></li>
                    <li><a href="http://metc.xmu.edu.cn/" style="padding-right:0px;">厦门大学现代教育技术与实践训练中心</a></li>
                    <li><a href="http://ctld.xmu.edu.cn/">厦门大学教师发展中心</a></li>
                    <li><a href="http://cxw.xmu.edu.cn/">厦门大学本科生创新网</a></li>
                    <li><a href="http://xdkc.xmu.edu.cn/">厦门大学课程地图</a></li>
                </ul>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>

    <footer>
        <div class="footer-inner">
            <div style="display: inline-block;">
                <img src="../img/footer-logo.png" style="margin-top: -25px;">
            </div>
            <div style="display: inline-block">
                <span>厦门大学教务处</span><span>电话：0592-2137315</span><br>
                <span>地址：福建省厦门市思明区思明南路422号</span><span>邮政编码：361005</span><br>
            </div>
        </div>
        <div class="footer-boot">
            <div style="float:right;margin-right: 200px;color:white;">
                <span>©2013</span>
                <span>厦门大学</span>
                <span>版权所有</span>
            </div>
        </div>
    </footer>
</div>
</body>
</html>