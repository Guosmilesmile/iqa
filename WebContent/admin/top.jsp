<%@page import="java.net.URLDecoder"%>
<%@page import="cn.edu.xmu.entity.User"%>
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
<title>后台上侧页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<%-- <%
//User user = (User)request.getSession().getAttribute("User");
session = request.getSession();
String username= (String)session.getAttribute("username");
%> --%>
 <%
	String userid ="";
 	String username ="";
 	String deptxi="";
		Cookie[] cookies =request.getCookies();
	for(Cookie cookie : cookies){
	    if(cookie.getName().equals("userid"))
	    	userid=cookie.getValue();
	    if(cookie.getName().equals("username"))
	    	username=cookie.getValue();
	    if(cookie.getName().equals("deptxi"))
	    	deptxi=cookie.getValue();
	}
	username=URLDecoder.decode(username, "UTF-8");
	username=URLDecoder.decode(username, "UTF-8");
	deptxi=URLDecoder.decode(deptxi, "UTF-8");
	deptxi=URLDecoder.decode(deptxi, "UTF-8");
	%>
<body>
    <div class="topbg">
    <div class="topleft">
    <a href="main.jsp" target="_parent">
    <img src="../images/loginlogo.png" title="系统首页" />
    <h2>厦门大学本科教学质量保障体系后台管理</h2>
    </a>
    </div>
        
    <ul class="nav">
    <li><a href="myInfo.jsp" target="rightFrame"><img src="../images/i07.png" title="我的信息" />我的信息</a></li>
    <li><a href="main.jsp"  target="#"><img src="../images/i02.png" title="宣传管理" />宣传管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../images/i05.png" title="问卷管理" />问卷管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../images/icon01.png" title="用户管理" />用户管理</a></li>
  <!--   <li><a href="main.jsp"  target="#"><img src="../images/i09.png" title="通知管理" />通知管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../images/i08.png" title="栏目管理" />栏目管理</a></li>
    <li><a href="main.jsp"  target="#"><img src="../images/i06.png" title="日志管理" />日志管理</a></li> -->
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="../images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="<%=basePath %>login.jsp" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span><%=username%>:<%=deptxi%></span>
    <i>消息</i>
    <b>XX</b>
    </div>    
    
    </div>
    </div>
</body>
</html>
