<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath %>css/adduser.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=basePath %>admin/myInfo.jsp" >首页></a></li>
    <li><a href="#">用户管理></a></li>
    <li><a href="#">增加用户</a></li>
    </ul>
    </div>
<form action="adduser" >
	账号：<input name="userid" type="text"><br/><br/>
	用户名:<input name="username" type="text"><br/><br/>
	密码:<input name="password" type="text"><br/><br/>
	<input type="submit" value="确认">
</form>
</body>
</html>