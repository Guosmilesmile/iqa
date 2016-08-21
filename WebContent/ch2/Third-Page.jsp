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
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>厦门大学内部质量保障网</title>
    <link rel="shortcut icon" href="../img/LOGO.ico" >
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style-define.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
    <script type='text/javascript' src='/IQA/dwr/interface/NewsDao.js'></script>
	<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
	<script src="<%=basePath%>js/commonJs.js"></script>
    <script>
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
        function resize(){
            var node=document.getElementById("footer");
            node.className=node.className.replace("footer-page-not-full","");
            var mainHeight=document.getElementById("third-new-inform").offsetHeight+333;
            var winHeight= document.documentElement.clientHeight;
            if(!$("#footer").hasClass("footer-page-not-full") && mainHeight<winHeight)
            {
            	$("#third-new-inform").height(625);
            }
        }
        window.onload=startList;
    </script>
    <script type="text/javascript">
	function GetRequest() {
		   var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		      strs = str.split("&");
		      for(var i = 0; i < strs.length; i ++) {
		         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
		      }
		   }
		   return theRequest;
	}
	function getNewsContent(){
		var Request = new Object();
		Request = GetRequest();
		var id = Request['contentId'];
		NewsDao.getNewsRecordById(id, function callback(data){
		var author = data.n_author;
		var content = data.n_content;
		var title = data.n_titles;
		var publishTime = data.n_publishtime;
		//var year = publishTime.getFullYear();
		//var month = publishTime.getMonth() + 1;
		//var date = publishTime.getDate();
		//var hour = publishTime.getHours();
		//var minute = publishTime.getMinutes();
		//var second = publishTime.getSeconds(); 
		
	//	var time = "发布时间："+year +"年"+month+"月"+date+"日    "+hour+"时"+minute+"分"+second+"秒";
		document.getElementById("author").innerHTML = author;
		document.getElementById("newsTitle").innerHTML = title;
		document.getElementById("publishTime").innerHTML = formatterdate(publishTime);
		document.getElementById("newsContent").innerHTML = content;

		resize();
	});
	}
	
	//window.onload=function (){
		//getNewsContent();
		//alert(1);
		//window.resizeBy(document.documentElement.offsetWidth-1, document.documentElement.offsetHeidth-1);
	//};
	
	$(document).ready(function (){
		getNewsContent();
	});

	/*日期数据格式化函数*/
    function formatterdate(val) {
    	if(val != null && val != ""){
    		var date = new Date(val);
            return date.getFullYear() + '-' + addZero((date.getMonth() + 1)) + '-' + addZero(date.getDate());
    	}
    }
  //将月份和日不足10的再前面补零
    function addZero(s) {
        return s < 10 ? '0' + s: s;
    }
</script>
</head>
<body onResize="resize()">
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

    <div id="third-new-inform">
        <div class="new-inform-title">
            <span id="newsTitle">读取中</span>
        </div>
        <div class="new-inform-content">
            <div class="clearfix"></div>
            <div id="new-time">
                <span id="author"></span>
                <span id="publishTime"></span>
            </div>
            <p id="newsContent">读取中
            </p>
        </div>
    </div>

    <footer id="footer">
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