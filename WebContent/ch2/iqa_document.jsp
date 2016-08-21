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
    <link href="../css/xmu_base.css" type="text/css" rel="stylesheet">
	<link href="../css/style-define.css" type="text/css" rel="stylesheet" >
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="../js/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/myJs.js"></script>
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
        window.onload=startList;
    </script>
    <script>
    String.prototype.format = String.prototype.f = function () {
        var s = this,
            i = arguments.length;

        while (i--) {
            s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
        }
        return s;
    };
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
  
function toPage(currentPage,n_subclass){
	window.location.href="GetCh2NewsList?page="+currentPage+"&n_subclass="+n_subclass;
}

var pageSize = 15;//每页的新闻数量
var total = 0;//总的新闻数
var currentPage = 1;//当前页码
var pageNum = 7;//控制最多显示7个页面数
var pageHalf = pageNum/2;//暂时等于3，表示中间页面两边均等的页面数
var pages = 1;//总的页数
function getAnnounce(){
	$.ajax({
        type: 'post',
        url: "<%=basePath%>GetCH2NewsList",
        data: {
        	pagination:"true",
        	rows:pageSize,
        	menuId:41,
        	page:currentPage,
        },
        async: false,
        dataType:'json',
        success: function(newsListJson){
        	var div_body = $("#announceList");
        	var newsList = newsListJson.rows;
        	total = newsListJson.total;
        	if(newsList.length > 0){
        		var trList = "";
            	var tr = "<tr ><td><i class='icon icon-exclamation-sign'></td> <td style='padding: 5px 5px;width:500px;display:inline-block;border-style:None;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;'><a href='{0}'>{1}</a></td><td>{2}</td></tr>";
            	for(var i = 0; i < newsList.length; i++)
            	{
            		var tempTitle = "";
            		if(newsList[i].n_titles.length > 26)
            			tempTitle = newsList[i].n_titles.substr(0,26)+"...";
            		else
            			tempTitle = newsList[i].n_titles;
            		trList = trList+tr.format("Third-Page.jsp?contentId="+newsList[i].n_id, tempTitle, formatterdate(newsList[i].n_publishtime));
            	}
        		div_body.html(trList);
        		$(".digg").html("");
        		//翻页
        		pages=parseInt(total/pageSize);
        		if(total%pageSize != 0)
        			pages+=1;
        		pageHalf=parseInt(pageNum/2);
        		if(currentPage==1)
        		{
        			$(".digg").append("<a class='disabled'>首页 </a><a class='disabled'>上一页</a>");
        		}else{
        			$(".digg").append("<a class='pageBtn' id='pageBtnfirst'>首页 </a><a class='pageBtn' id='pageBtnpre'>上一页</a>");
        		}
        		if(pageNum<pages)
        		{
        			if(currentPage<=pageHalf+1){
        				for(var i=1;i<=pageNum;i++)
        				{
        					if(i == currentPage)
        						$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
        					else
        						$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
        				}
        			}else if((pages-currentPage) < pageHalf){
        		        for(var i=pages-(pageNum-1);i<=pages;i++){
        					if(i == currentPage){
        		               	$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
        		            }else{
        		               	$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
        		            }
        		        }
        		    }else{
        		        for(var i=currentPage-pageHalf;i<=parseInt(currentPage)+pageHalf;i++){
        					if(i==currentPage){
        						$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
        		            }else{
        		               	$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
        		            }
        		        }
        		    }
        		}else{
        		    for(var i=1;i<=pages;i++){
        				if(i==currentPage){
        					$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
        				}else{
        					$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
        		        }
        		    }
        		}
        		if(currentPage==pages){
        		    $(".digg").append("<a class='disabled'>下一页</a><a class='disabled'>尾页</a>");
        		}else{
        		    $(".digg").append("<a class='pageBtn'  id='pageBtnnext'>下一页</a><a class='pageBtn'  id='pageBtnlast'>尾页</a>");
        		}
        		
        		$(".readQuestionnaire").click(function(){
   				//	var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>login.jsp";
   				});
        		$('.pageBtn').click(function(){
        	 		var id = $(this).attr("id").toString().substring(7);
        	 		/* alert(id); */
        	 		if(id == 'first'){
        	 			//跳转到第一页
        	 			currentPage = 1;
        	 		}else if(id == 'last'){
        	 			//跳转到最后一页
        	 			currentPage = pages;
        	 		}else if(id == 'pre'){
        	 			//跳转到上一页
        	 			currentPage --;
        	 		}else if(id == 'next'){
        	 			//跳转到下一页
        	 			currentPage ++;
        	 		}else{
        	 			//跳转到第id页
        	 			currentPage = id;
        	 		}
        	 		 if(currentPage <= pages){
        	 			getAnnounce();
        	 		} 
        	 	});
        	}
        	else{
        		var emptyStr = "<tr><td><div class='question'><h3>暂无相关信息</h3>"
    		    +"</div></td></tr>";
    		    div_body.html(emptyStr);
        	}
		},
        error: function(){}
	});
}

$(document).ready(function () { 
	getAnnounce();
});
    </script>
    <style>
         	DIV.digg A.disabled {
	BORDER-RIGHT: #eee 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #eee 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #eee 1px solid;
	COLOR: #ddd;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #eee 1px solid;
	}
	DIV.digg A.current {
	BORDER-RIGHT: #005590 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #005590 1px solid;
	PADDING-LEFT: 5px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #005590 1px solid;
	COLOR: #fff;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #005590 1px solid;
	BACKGROUND-COLOR: #005590;
	}
	DIV.digg A {
	BORDER-RIGHT: #aaaadd 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #aaaadd 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #aaaadd 1px solid;
	COLOR: #005590;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #aaaadd 1px solid;
	TEXT-DECORATION: none;
	cursor: pointer;
	}
	DIV.digg {
	margin-top:5px;float:right;
	}
	TD{
	padding:5px;
	}
    </style>
</head>

<body  class="style-bright">
    <div id="wrapper">
        <!--  Header-->
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
        <!-- MAIN-->
        <div id="main" class="container container-fluid" >
            <br class="row-fluid">
                <div id="sub-nav" class="span3" >
                    <span class="sub-nav-title">文档下载</span>
                    <!-- left menu ================================================== -->
                    <ul>
                        <!-- <li><a href="#"><i class="icon icon-chevron-right"></i>联合国立项文件</a></li>
                        <li><a href="#"><i class="icon icon-chevron-right"></i>教育部批准文件</a></li>
                        <li><a href="#"><i class="icon icon-chevron-right"></i>...</a></li> -->
                    </ul>
                </div>
                <div id="main-content" class="span9" >
                    <h3>文档下载</h3>
                    <table id="announceList">
                    </table>
                    <div class="digg">
                    </div>
                </div>

                <div class="clearfix"></div>
            </div>
            
      <footer>
        <div class="footer-inner">
            <div style="display: inline-block;">
                <img src="../img/footer-logo.png" style="margin-top: -60px;">
            </div>
            <div style="display: inline-block">
                <span>厦门大学</span><br>
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
<script src="../js/lib.js"></script>
<script src="../js/app.js"></script>
</body>
</html>