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
<title>厦门大学本科教学质量保障体系</title>
<style type="text/css"></style>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.1.min.js"></script>
<script src="<%=basePath%>js/myJs.js"></script>
<script type="text/javascript">
function getSlideImage(){
	$.ajax({
        type: 'get',
        url: "<%=basePath%>getslidenews",
        data: {
        	pagination:"false",
        	rows:6,
        	version:"ch",
        },
        success: function(slidenewsListJsonStr){
        	var ul_body = $("#slideimage");
        	var str ="";
        	var slidenewsList = eval('(' + slidenewsListJsonStr + ')');
        	if(slidenewsList.length > 0){
        		for (var i = 0 ;i < slidenewsList.length;i++){
        			if(slidenewsList[i].sn_imgurl==""){
        				slidenewsList[i].sn_imgurl="images/newModel/pic_11.png";
        			}
        			str +="<li><a class='sss' href='#' id='readContent"+slidenewsList[i].sn_id+"'>"+
        			"<img src =<%=basePath%>" + slidenewsList[i].sn_imgurl+">"+"</a></li>";
        				
          		}
        		ul_body.html(str);
        		$(".sss").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="./slidenews.jsp?contentId="+id;
   				});
        		//此处调用图片滚动方法
        		play();
        	}
		},
        error: function(){}
        	
	});
}


function getLatestNews(){
	$.ajax({
        type: 'get',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	n_subclass:"1",
        	version:"ch",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#newslist");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
        			if(newsList[i].n_imgurl==""){
        				newsList[i].n_imgurl="images/newModel/display_pic.png";
        			}
        			if(newsList[i].n_titles==""){
						newsList[i].n_titles = "  ";
        			}
        			if(newsList[i].n_titles.length>10){
        				newsList[i].n_titles = newsList[i].n_titles.substr(0,16);
        			}
        			
					if(newsList[i].n_summary==""){
						newsList[i].n_summary = "click in";
        			}
        			newsList[i].n_titles+="...";
        			str +="<li> <a class='ss' href='#' id='readContent"+newsList[i].n_id+"'><img class=displayimg title="+newsList[i].n_summary+ " src=<%=basePath%>"+newsList[i].n_imgurl + "><h3>"
        				+newsList[i].n_titles+"</h3></a><div class='clear'></div>"
        				+"<p>"+"</p></li>";

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

function getLatestFiles(){
	$.ajax({
        type: 'get',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	n_subclass:"2"
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#fileList");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
					
        			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
    				{
    				var a=newsList[i].n_content.indexOf("href");
    				var b=newsList[i].n_content.indexOf('"',a+6);       			
    				var content=newsList[i].n_content.substring(a+6,b);
        		    str +="<li><div class='description'><a href='"+content+"' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		    "</h3></a></div></div>";
    				}
        			else{
            		    str +="<li><div class='description'><a href='#' class='readContent' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
            		    "</h3></a></div><p class='desc_p'>"+year+"-"+month+"-"+day
             		    +"</p></div>";
        				
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

function getLatestReports(){
	$.ajax({
        type: 'get',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	n_subclass:"3",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#reportsList");
        	var onereport = $("#onereport");
        	
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
        			//时间设置
        			/* var publishTime = newsList[i].n_publishtime;
        			var dd = new Date(publishTime);
        			var year = dd.getFullYear();
        			var month = dd.getMonth()+1;
        			var day = dd.getDate(); */
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
        				str +="<li ><div class='description'><a><h3 class='' id='readContent"+newsList[i].n_id
           			 	+"'>"+newsList[i].n_titles+
            		    "</h3></a></div></div>";
        			}else{
        				str +="<li ><div class='description'><a href='#'><h3 class='ss' id='readContent"+newsList[i].n_id
           			 	+"'>"+newsList[i].n_titles+
            		    "</h3></a></div></div>";
        			}
    			}
        			

          	}
        		
        		ul_body.html(str);
        		var str2 = "";
        		str2 = "<p>"+newsList[0].n_titles+newsList[0].n_content+"</p>";
        		onereport.html(str2);
        		
        		$(".ss").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="./document.jsp?contentId="+id;
   				});
        	}else{
        		str ="<li ><div class='description'><h3>"+"暂无相关信息"+"</h3></div></li>";
        		ul_body.html(str);
        	}
		},
        error: function(){}
        	
	});
}

function getQuestionnaires(){
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	n_subclass:"30",//问卷
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
   					window.location.href="<%=basePath%>login.jsp";
   				});
        	}
        	else{
        		var emptyStr = "<li><div class='question'><h3>暂无相关信息</h3>"
    		    +"</div></li>";
    		    div_body.html(emptyStr);
        	}
		},
        error: function(){}
        	
	});
}


$(document).ready(function () { 
	getSlideImage();
	getLatestNews();
	getLatestFiles();
	getLatestReports();
	getQuestionnaires();
	$("#moreNews").click(function(){
		$("#n_subclass").val(1);
		$("#getnews").submit();
	});
	$("#moreFiles").click(function(){
		$("#n_subclass").val(2);
		$("#getnews").submit();
	});
	$("#moreReports").click(function(){
		$("#n_subclass").val(3);
		$("#getnews").submit();
	});
	
});
</script>
</head>
<body>
<%@include file="IQAhead.jsp"%>
<div class="play" id="play">
	<a href="javascript:" id="next">>><div class="nextImg"><img width="80" height="54" src="" /></div></a>
	<a href="javascript:" id="prev"><<<div class="prevImg"><img width="80" height="54" src="" /></div></a>
	<ol></ol>
	<ul id="slideimage">
	</ul>
</div>
<!--end .play-->
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="sidebar1">
 	<!-- <h1>最新</h1> -->
    <h2>问卷</h2>
    <div class="question" id="quetionnaire">
    </div><div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a>
    
    <!-- <div class="clear"></div>
    <h1>报告内容</h1>
    <div class="clear"></div>
    <h3 id=reporttitle></h3>
    <div class="report" id = "onereport">
    </div>
    <div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a> -->
    
     <div class="clear"></div>
    <h2>报告内容</h2>
    <div class="clear"></div>
    <div id="onereport"></div>
    
  </div>
  <!-- end .sidebar1 -->
  <div class="content">
   <div class="displaymenu">
    <div class="con_header">
    <h2>新闻</h2>
    <a class="readmore" href="#" id="moreNews">更多>></a>
    </div>
   <!-- 新闻模块 -->
    <ul id="newslist">
    </ul>
   </div>
   <!-- end .displaymenu-->
    <div class="download">
   <div class="con_header">
    <h1></h1>
    <h2>文件</h2>
    <a class="readmore" href="#" id="moreFiles">更多>></a>
   </div>
    <ul id="fileList">
    </ul>
   </div>
      <!-- end .displaymenu-->
    <div class="download">
   <div class="con_header">
    <h2>报告</h2>
    <a class="readmore" href="#" id="moreReports">更多>></a>
   </div>
    <ul id="reportsList">
    </ul>
   </div>
   
  </div>
   <!-- end .content -->
  </div>
    <!-- end .container --></div>
  <jsp:include page="IQAtail.jsp"></jsp:include>
  
<form id="getnews" action="<%=basePath%>getallnews" method="post">
	<input type="hidden" id = "language" >
	<input type="hidden" name = "version" value="ch">
	<input type="hidden" id="n_subclass" name="n_subclass" value="">
</form>
<form id="getContent" action="<%=basePath%>getContent" method="post">
	<input type="hidden" id="contentId" name="contentId" value="">
	<input type="hidden" name = "version" value="en">
</form>
</body>
</html>