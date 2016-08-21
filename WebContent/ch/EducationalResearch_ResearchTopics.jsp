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
<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.1.min.js"></script>
<script src="<%=basePath%>js/myJs.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/mycss-cn.css">
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

function getResearchPaper(n_sub,moduleid,module){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:8,
        	menuId:menuId,
        	n_subclass:n_sub
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#"+moduleid);
        	
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#"+module).show();
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
        			if(newsList[i].n_content == ""){
        			    str +="<li><div class='description'><a href='#'  id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		        "</h3></a>"
        		        +"</div></li>";
        			}
        			else{
        				str +="<li><div class='description'><a href='<%=basePath%>ch/document.jsp?contentId="+newsList[i].n_id+"' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'><h3>"+newsList[i].n_titles+
        		        "</h3></a>"
        		        +"</div></li>";
        			}
    			}
          	}
        		ul_body.html(str);
        		if(moduleid == "paperlist"){
        			var onereport = $("#onereport");
        			var str2 = "";
            		str2 = "<p>"+newsList[0].n_content+"</p>";
            		onereport.html(str2);	
        		}
        		
        		/* $(".ss").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				}); */
        	}else{
        		str ="<li ><div class='description'><h3>"+"暂无此模块的内容"+"</h3></div></li>";
        		ul_body.html(str);
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
        		var emptyStr = "<li><div class='question'><h3>暂无相关信息</h3>"
    		    +"</div></li>";
    		    div_body.html(emptyStr);
        	}
		},
        error: function(){}
        	
	});
}

$(document).ready(function () {
	getResearchPaper(293,"projectlist","ProjectModule");
	getQuestionnaires();
	
	$("#moreProject").click(function(){
		$("#n_subclass").val(293);
		$("#getnews").submit();
	});
});
</script>
</head>
<body>
<%-- <%@ include file="IQAhead.jsp"%> --%>
<jsp:include page="IQAhead.jsp" flush="true" /> 
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="sidebar1">
    <!-- <h1>最新</h1> -->
    <h2>问卷</h2>
    <div class="question" id="quetionnaire">
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
    </div><div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a>
    <div class="clear"></div>
    <h1>报告内容</h1>
    <div class="clear"></div>
    <div class="report" id = "onereport">
    </div>
    <div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a>
  </div>
  <!-- end .sidebar1 -->
  <div class="content">  
    <div class="download" id="ProjectModule">
   <div class="con_header">
    <h2>研究课题</h2>
    <a class="readmore" href="#" id="moreProject">更多>></a>
   </div>
    <ul id="projectlist">
    </ul>
   </div>
   <!-- end .displaymenu-->
   
  </div>
   <!-- end .content -->
  </div>  <!-- end .container --></div>
<jsp:include page="IQAtail.jsp"></jsp:include>

  
  
<form id="getnews" action="<%=basePath%>getallnews" method="post">
	<input type="hidden" id = "language" >
	<input type="hidden" id="n_subclass" name="n_subclass" value="">
</form>
<form id="getContent" action="<%=basePath%>getContent" method="post">
	<input type="hidden" id="contentId" name="contentId" value="">
</form>
</body>
</html>