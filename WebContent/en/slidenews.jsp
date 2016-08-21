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
<title>XMU IQA</title>
<script type='text/javascript' src='/IQA/dwr/interface/SlideNewsDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.1.min.js"></script>
<script src="<%=basePath%>js/myJs.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/mycss-cn.css">
<style type="text/css"></style>
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
	SlideNewsDao.getSlideNewsRecordById(id, function callback(data){
	var content = data.sn_content;
	var title = data.sn_titles;
	
	document.getElementById("newsTitle").innerHTML = title;
	document.getElementById("newsContent").innerHTML = content;
});
}

$(document).ready(function (){
	getNewsContent();
});

</script>
</head>
<body>
<jsp:include page="IQAhead-en.jsp"></jsp:include>
    <!-- end .header -->
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="newscontent">
  <p class="newstitle" id="newsTitle"></p>
  <p class="newssubtitle" id="publishTime"></p>
  <hr />
  <p class="news" id="newsContent"></p>
   <div class="attachment">
   		<a href="#"></a>
   </div>
  </div>
   <!-- end .content -->
  </div>
  <!-- end .container -->
</div>
  
  <div class="footer">
    <div class="footerinfo">
    <h4>快速链接</h4>
    <hr style="color:#81a9c5;width:100%;">
    <a href="#">厦门大学</a>
    <a href="#">国际机构</a>
    </div>
    <div class="footerinfo">
    <h4>联系我们</h4>
    <hr style="color:#81a9c5;width:100%;">
    <p>电子邮箱: something@xmu.edu.cn</p>
    <p>电话号码: 120439340</p>
    </div>
    <img src="../images/newModel/logo_bottom.png"/>
    </div>
    <!-- end .footer -->
</body>
</html>