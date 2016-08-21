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
<link rel="stylesheet" href="<%=basePath%>css/jquery.jdownload.css">
<title>厦门大学本科教学质量保障体系</title>
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
function getDownloads(){
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
     },
     success: function(newsListJsonStr){
     	var ul_body = $("#downloads");
     	var str ="";
     	var newsList = eval('(' + newsListJsonStr + ')');
     	if(newsList.length > 0){
     		var downloadNum = 0;
     		for (var i = 0 ;i < newsList.length;i++){
     			if (newsList[i].n_content != "" && newsList[i].n_titles != "") {
     				//截取下载内容的路径
         			var a = newsList[i].n_content.indexOf("href");
    				var b = newsList[i].n_content.indexOf('"',a+6);
    				var content = newsList[i].n_content.substring(a+6,b);
    				//截取下载内容的类型
    				var x = newsList[i].n_content.indexOf("title");
    				var y = newsList[i].n_content.indexOf('"',x+7);
    				var downloadType = newsList[i].n_content.substring(x+7,y);
    				
    				if(downloadType == 'doc.png'){
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".doc'>下载 <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'pdf.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".pdf'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'xls.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".xls'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'txt.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".txt'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'ppt.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".ppt'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'rar.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".rar'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}else if (downloadType == 'zip.png') {
    					downloadNum ++;
    					str +=" <li><div class='newsdesc'><h3>"+newsList[i].n_content+"</h3><div class='clear'></div></div><a class='downicon' href='"+content+"' download='"+newsList[i].n_titles+".zip'>下载  <img src='../images/newModel/download_icon.png'/></a></li>";
    				}
				}
       	}
     		if (downloadNum == 0) {
     			var emptyStr = "<li><div class='newsdesc'><h3>NO RELATED INFORMATION NOW.</h3>"
     	 		    +"</div></li>";
     	 		    ul_body.html(emptyStr);
			}else{
				ul_body.html(str);
			}
     		
     	}
     	else{
     		var emptyStr = "<li><div class='newsdesc'><h3>暂无相关信息</h3>"
 		    +"</div></li>";
 		    ul_body.html(emptyStr);
     	}
		},
     error: function(){}
     	
	});
}

$(document).ready(function () {
	getDownloads();

});
</script>
</head>
<body>
<%@include file="IQAhead.jsp"%>

<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="newscontent">
   <div class="downloadcenter">
   <div class="con_header">
    <h2>下载中心</h2>
    <!-- <a class="readmore" id="moreDownloads">更多>></a> -->
   </div>
    <ul id="downloads">
    <%--  <li>
    <div class="newsdesc">
    <h3>Something happened</h3>
    <div class="clear"></div>
    <!-- <p>there're descriptions of what's going on on the earth</p> -->
    </div>
    <a class="downicon" href="#">Dowload <img src="<%=basePath%>images/newModel/download_icon.png"/></a> 
    </li>
     --%>
    
    
    </ul>
    
    
    
   </div>
  </div>
   <!-- end .content -->
  </div>  <!-- end .container --></div>
  <jsp:include page="IQAtail.jsp"></jsp:include>
</body>
</html>