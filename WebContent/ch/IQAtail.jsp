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
<link rel="stylesheet" href="<%=basePath%>css/mycss-cn.css">
<title>厦门大学本科教学质量保障体系</title>
</head>
<body>
  <div class="footer">
    <div class="footerinfo">
    <h4>快速链接</h4>
    <a href="http://gs.xmu.edu.cn/">厦门大学研究生院</a><br/>
    <a href="http://xdkc.xmu.edu.cn/">厦门大学课程地图</a><br/>
    <a href="http://xsctemp.xmu.edu.cn/">厦门大学学生处</a><br/>
    <a href="http://jwc.xmu.edu.cn/">厦门大学教务处</a><br/>
    <a href="http://ctld.xmu.edu.cn/"> 厦门大学教师发展中心</a>
    </div>
    <div class="footerinfo">
    <h4>联系我们</h4>
    <p>Email：jwc@xmu.edu.cn </p><br/>
    <p>传  真：2096192</p>
    </div>
    <img src="<%=basePath%>images/newModel/logo_bottom.png"/>
    <!-- end .footer --></div>
</body>
</html>