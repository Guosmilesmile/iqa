<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>厦门大学本科教学质量保障体系</title>
<style type="text/css"></style>
<script type="text/javascript" src="../js/jquery-1.3.1.min.js"></script>
<script src="../js/myJs.js"></script>
<link rel="stylesheet" href="../css/mycss-cn.css">
<script type="text/javascript">
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   //alert(url);
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
function getLatestNews(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	$.ajax({
        type: 'post',
        url: "../getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"1"
        },
        success: function(newsListJsonStr){
        	
        	var ul_body = $("#newslist");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#newsModule").show();
        		for (var i = 0 ;i < newsList.length;i++){
        			
        			if(newsList[i].n_imgurl==""){
        				newsList[i].n_imgurl="../images/newModel/display_pic.png";
        			}
        			newsList[i].n_imgurl="../images/newModel/display_pic.png";
        			str +="<li> <img class=displayimg src=<%=basePath%>"+newsList[i].n_imgurl+"><h3>"
        				+newsList[i].n_titles+"</h3><div class='clear'></div>"
        				+"<p>"
        				+"</p><a class='displayicon readContent' href='#' id='readContent"+newsList[i].n_id+"'>Read More <img src='../images/newModel/arrow.png'/></a></li>"

        			if (i==1) {
					str += " <div class='clear'></div>";
					}
        			
          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				});
        	}
        	else{//新闻内容为空
        		$("#newsModule").hide();
        	}
		},
        error: function(){}
        	
	});
}

function getLatestFiles(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	
	$.ajax({
        type: 'post',
        url: "../getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"2"
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#fileList");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#FileModule").show();
        		for (var i = 0 ;i < newsList.length;i++){
        			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
    				{
    				var a=newsList[i].n_content.indexOf("href");
    				var b=newsList[i].n_content.indexOf('"',a+6);       			
    				var content=newsList[i].n_content.substring(a+6,b);
        		    str +="<li><div class='description'><a href='"+content+"' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		    "</h3></a>";
    				}
    			else{

        		    str +="<li><div class='description'><h3>"+newsList[i].n_titles+
        		    "</h3><div class='clear'></div><p>"
        		    +"</p></div><a class='displayicon readContent' href='#' id='readContent"+newsList[i].n_id+"'>Read More <img src='../images/newModel/arrow.png'/></a></li><hr style='border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;'>"
    			}
          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				})
        	}
        	else{
        		$("#FileModule").hide();
        	}
		},
        error: function(){}
        	
	});
}

function getLatestReports(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	$.ajax({
        type: 'post',
        url: "../getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"3",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#reportsList");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#reportModule").show();
        		for (var i = 0 ;i < newsList.length;i++){
        			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
    				{
    				var a=newsList[i].n_content.indexOf("href");
    				var b=newsList[i].n_content.indexOf('"',a+6);       			
    				var content=newsList[i].n_content.substring(a+6,b);
        		    str +="<li><div class='description'><a href='"+content+"' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		    "</h3></a>";
    				}
    			else{

        		    str +="<li><div class='description'><h3>"+newsList[i].n_titles+
        		    "</h3><div class='clear'></div><p>"
        		    +"</p></div><a class='displayicon readContent' href='#' id='readContent"+newsList[i].n_id+"'>Read More <img src='../images/newModel/arrow.png'/></a></li><hr style='border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;'>"
    			}
          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				})
        	}
        	else{
        		$("#reportModule").hide();
        	}
		},
        error: function(){}
        	
	});
}

$(document).ready(function () {
	getLatestNews();
	getLatestFiles();
	getLatestReports();
	
	$("#moreNews").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		$("#n_subclass").val(1);
		$("#getnews").submit();
		
	});
	$("#moreFiles").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		$("#n_subclass").val(2);
		$("#getnews").submit();
	});
	$("#moreReports").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		$("#n_subclass").val(3);
		$("#getnews").submit();
	});
	
	
});
</script>
</head>
<body>
<%-- <%@ include file="IQAhead.jsp"%> --%>
<jsp:include page="IQAhead.jsp" flush="true" /> 
<div class="container">
  <div class="sidebar1">
    <h1>最新</h1>
    <h2>问卷</h2>
    <div class="question">
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
    </div><div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a>
  </div>
  <!-- end .sidebar1 -->
  <div class="content">
   <div class="displaymenu" id="newsModule">
    <div class="con_header">
    <h1>最近</h1>
    <h2>新闻</h2>
    <a class="readmore" href="#" id="moreNews">更多>></a>
     <hr style="position: relative; width:100%;">
    </div>
   <!-- 最新新闻模块 -->
    <ul id="newslist">
    </ul>
   </div>
   <!-- end .displaymenu-->
   <div class="download" id="FileModule">
   <div class="con_header">
    <h1>最新</h1>
    <h2>文件</h2>
    <a class="readmore" href="#" id="moreFiles">更多>></a>
     <hr style="position: relative; width:100%;">
   </div>
    <ul id="fileList">
    </ul>
   </div>
    <!-- end .displaymenu-->
    <div class="download" id="reportModule">
   <div class="con_header">
    <h1>最新</h1>
    <h2>报告</h2>
    <a class="readmore" href="#" id="moreReports">更多>></a>
     <hr style="position: relative; width:100%;">
   </div>
    <ul id="reportsList">
    </ul>
   </div>
   
  </div>
   <!-- end .content -->
  </div>
  <div class="footer">
    <div class="footerinfo">
    <h4>快速链接</h4>
    <hr style="color:#81a9c5;width:100%;">
    <a href="#">Xiamen University</a>
    <a href="#">International Facilities</a>
    </div>
    <div class="footerinfo">
    <h4>联系我们</h4>
    <hr style="color:#81a9c5;width:100%;">
    <p>电子邮箱: something@xmu.edu.cn</p>
    <p>电话号码: 120439340</p>
    </div>
    <img src="../images/newModel/logo_bottom.png"/>
    <!-- end .footer --></div>
  <!-- end .container --></div>
  
  
<form id="getnews" action="getallnews" method="post">
	<input type="hidden" id = "language" >
	<input type="hidden" id="n_subclass" name="n_subclass" value="">
</form>
<form id="getContent" action="getContent" method="post">
	<input type="hidden" id="contentId" name="contentId" value="">
</form>
</body>
</html>