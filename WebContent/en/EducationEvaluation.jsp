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
<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.1.min.js"></script>
<title>XMU IQA</title>
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
function getOverview(){
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
        	n_subclass:"5",
            version:"en",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#overview");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		str += "<p class='overviewP'><span>"+newsList[0].n_content.substr(0,200)+"...</span><a class='readmoreForOverview readContent' href='#' id='readContent"+newsList[0].n_id+"'>Read More>></a></p>";
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>en/document.jsp?contentId="+id;
   				});
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
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"2",
            version:"en",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#fileList");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
        			/* var publishTime = newsList[i].n_publishtime;
        			var time = new Date(publishTime);
        			var year = time.getFullYear();
        			var month = time.getMonth() + 1;
        			var date = time.getDate();   */
        			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
    				{
    				var a=newsList[i].n_content.indexOf("href");
    				var b=newsList[i].n_content.indexOf('"',a+6);       			
    				var content=newsList[i].n_content.substring(a+6,b);
        		    str +="<li><div class='description'><a href='"+content+"' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		    "</h3></a>";
    				}
    			else{

        			if (newsList[i].n_content == ""||newsList[i].n_content == null) {
        				str +="<li><div class='description'><a href='#' class='' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
            		    "</h3></a>"
            		    +"</div></li>";
        			}else{
        				str +="<li><div class='description'><a href='#' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
            		    "</h3></a>"
            		    +"</div></li>";
        			}
    			}

          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				})
        	}
        	else{
        		var emptyStr = "<li><div class='description'><h3>NO RELATED INFORMATION NOW</h3>"
        		    +"</div></li>";
            		ul_body.html(emptyStr);
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
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"3",
        	version:"en",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#reportsList");
        	var latestReportContent = $("#latestReportContent");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
        			/* var publishTime = newsList[i].n_publishtime;
        			var time = new Date(publishTime);
        			var year = time.getFullYear();
        			var month = time.getMonth() + 1;
        			var date = time.getDate(); */   
        			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
    				{
    				var a=newsList[i].n_content.indexOf("href");
    				var b=newsList[i].n_content.indexOf('"',a+6);       			
    				var content=newsList[i].n_content.substring(a+6,b);
        		    str +="<li><div class='description'><a href='"+content+"' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		    "</h3></a>";
    				}
    			else{
        			if (newsList[i].n_content == ""||newsList[i].n_content == null) {
        				str +="<li><div class='description'><a class='' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
            		    "</h3></a>"
            		    +"</div></li>";
        			}else{
        				str +="<li><div class='description'><a href='#' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
            		    "</h3></a>"
            		    +"</div></li>";
        			}
    			}

          	}
        		ul_body.html(str);
        		var contentStr ="<div class='report'><h3>"+newsList[0].n_titles+"</h3>"+newsList[0].n_content+"</div><div class='clear'></div><hr style='border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;'><a class='readmore' href='document.jsp?contentId="+newsList[0].n_id+"'>ReadMore>></a>";
        	   
        		latestReportContent.html(contentStr);
        		
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>en/document.jsp?contentId="+id;
   				})
        	}
        	else{
        		var emptyStr = "<li><div class='description'><h3>暂无相关信息</h3>"
        		    +"</div></li>";
            		ul_body.html(emptyStr);
        	}
		},
        error: function(){}
        	
	});
}

function getQuestionnaires(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"30",//问卷
        	version:"en",
        },
        success: function(newsListJsonStr){
        	var div_body = $("#quetionnaire");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){

        		    str +="<a href='#' class='readQuestionnaire' id='readContent"+newsList[i].n_id+"'>"+newsList[i].n_titles+"</a><p></p>";

          	}
        		div_body.html(str);
        		$(".readQuestionnaire").click(function(){
   				//	var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>login.jsp";
   				})
        	}
        	else{
        		var emptyStr = "<li><div class='question'><h3>NO RELATED INFORMATION NOW</h3>"
    		    +"</div></li>";
    		    div_body.html(emptyStr);
        	}
		},
        error: function(){}
        	
	});
}
$(document).ready(function () {
	getOverview();
	getLatestFiles();
	getLatestReports();
	getQuestionnaires();
	
	$("#moreOverview").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		$("#n_subclass").val(5);
		$("#getcontent").submit();
		
	});
	$("#moreFiles").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		var Request = new Object();
		Request = GetRequest();
		var menuId = Request['id'];
		$("#menuId").val(menuId);
		$("#n_subclass").val(2);
		$("#getnews").submit();
	});
	$("#moreReports").click(function(){
		<%-- var value='<%=session.getAttribute("language")%>';
		$("#language").val = value; --%>
		var Request = new Object();
		Request = GetRequest();
		var menuId = Request['id'];
		$("#menuId").val(menuId);
		$("#n_subclass").val(3);
		$("#getnews").submit();
	});
	
	
});
</script>
</head>
<body>
<%@include file="IQAhead-en.jsp"%>
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="sidebar1">
    <h2>QUESTIONAIRE</h2>
    <div class="question" id="quetionnaire">
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
    </div><div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">ReadMore>></a>
    
    <div class="clear"></div>
    <h2>REPORTCONTENTS</h2>
    <div class="clear"></div>
    <div id="latestReportContent"></div>
    
  </div>
  <!-- end .sidebar1 -->
  <div class="content">
      <div class="displaymenu" id="Overview">
    <div class="con_header">
    <h2>OVERVIEW</h2>
    </div>
    <div id="overview">
    
    </div>
    
   </div>
   <!-- end .displaymenu-->
   <div class="download" id="FileModule">
   <div class="con_header">
    <h2>FILES</h2>
    <a class="readmore" href="#" id="moreFiles">ReadMore>></a>
   </div>
    <ul id="fileList">
    </ul>
   </div>
    <!-- end .displaymenu-->
    <div class="download" id="reportModule">
   <div class="con_header">
    <h2>REPORTS</h2>
    <a class="readmore" href="#" id="moreReports">ReadMore>></a>
   </div>
    <ul id="reportsList">
    </ul>
   </div>
   
  </div>
   <!-- end .content -->
  </div>  <!-- end .container --></div>
<jsp:include page="IQAtail-en.jsp"></jsp:include>

  
  
<form id="getnews" action="<%=basePath%>getallnews" method="post">
	<input type="hidden" id="n_subclass" name="n_subclass" value="">
	<input type="hidden" id = "version" name="version" value="en">
	<input type="hidden" id="menuId" name="menuId" value="">
</form>
<form id="getContent" action="<%=basePath%>getContent" method="post">
	<input type="hidden" id="contentId" name="contentId" value="">
	<input type="hidden" id="n_subclass" name="n_subclass" value="">
	<input type="hidden" name = "version" value="en">
</form>
</body>
</html>