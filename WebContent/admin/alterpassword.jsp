<%@page import="cn.edu.xmu.entity.User"%>
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
<title>Insert title here</title>
<link href="../css/alterpassword.css" rel="stylesheet" type="text/css" >
</head>
<%
User user = (User)request.getSession().getAttribute("User");
%>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">修改密码</a></li>
	    </ul>
    </div>
	<div>
		<form id="formtable" action="alterpw">
				用 户 id　<input type="text" name="userid" id="userid" value="<%=user.getU_userid()%>" readonly="readonly"><br/><br/>
				用 户 名 　<label><%=user.getU_username()%></label><br/><br/>
				新 密 码 　<input type="text" name="password" id="password"><br/><br/>
				<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>