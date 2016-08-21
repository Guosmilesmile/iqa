<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台主页的左侧栏</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	
</script>


</head>
	<%-- <%
		session = request.getSession();
		String one = session.getAttribute("manageuser").toString();
		String two = session.getAttribute("managerole").toString();
		String Three = session.getAttribute("managetable").toString();
		String userid = session.getAttribute("userid").toString();
	 %> --%>
	 <%
	String userid ="";
	String manageuser="?";
	String managerole ="?";
	String managetable="?";
	String highestPower="0";
	int num =0;
		Cookie[] cookies =request.getCookies();
		num = cookies.length;
	for(Cookie cookie : cookies){
	    if(cookie.getName().equals("userid"))
	    	userid=cookie.getValue();
	    if(cookie.getName().equals("manageuser"))
	    	manageuser=cookie.getValue();
	    if(cookie.getName().equals("managerole"))
	    	managerole=cookie.getValue();
	    if(cookie.getName().equals("managetable"))
	    	managetable=cookie.getValue();
	    if(cookie.getName().equals("highestPower"))
	    	highestPower=cookie.getValue();
	}
	%>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span></div>
    
    <!-- 后台主页左侧菜单  -->
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span></span>我的信息
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="myInfo.jsp" target="rightFrame">基本信息</a><i></i></li>
        <!-- <li><cite></cite><a href="alterpassword.jsp"   target="rightFrame">修改密码</a><i></i></li> -->
        </ul>    
    </dd>
        
     <%if(userid.equals("admin")){ %>
    <dd><div class="title"><span></span>宣传管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="publishNews.jsp" target="rightFrame">发布信息</a><i></i></li>
        <li><cite></cite><a href="getallnews.jsp"  target="rightFrame">信息列表</a><i></i></li>
        <li><cite></cite><a href="getallreports.jsp"  target="rightFrame">报告列表管理</a><i></i></li>
        <li><cite></cite><a href="publishSlideNews.jsp"  target="rightFrame">发布滑动新闻</a><i></i></li>
        <li><cite></cite><a href="getallslidenews.jsp"  target="rightFrame">滑动新闻列表</a><i></i></li>
    </ul>    
    </dd>  

    <dd>
    <div class="title">
    <span></span>问卷管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="main.jsp"  target="rightFrame">发布问卷</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">回收问卷</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">问卷结果</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">问卷审核</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">删除问卷</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">数据报告</a><i></i></li>
    </ul>     
    </dd> 
<%} %>
	<%-- <%if(one.equals("1")||userid.equals("admin")) {%>
    <dd><div class="title"><span></span>用户管理</div>
    <ul class="menuson">
       <!--  <li><cite></cite><a href="main.jsp"  target="#">审核注册</a><i></i></li> -->
        <li><cite></cite><a href="adduser.jsp"   target="rightFrame">增加用户</a><i></i></li>
        <li><cite></cite><a href="manageuser.jsp"   target="rightFrame">管理用户</a><i></i></li>
    </ul>
    </dd>
	<%}else{}%> --%>
	<%if(managerole.equals("1")||userid.equals("admin")) {%>
 	<dd><div class="title"><span></span>角色管理</div>
    <ul class="menuson">
       <!--  <li><cite></cite><a href="main.jsp"  target="#">审核注册</a><i></i></li> -->
        <!-- <li><cite></cite><a href="addrole.jsp"   target="rightFrame">增加角色</a><i></i></li> -->
        <li><cite></cite><a href="managerole.jsp"   target="rightFrame">管理角色</a><i></i></li>
        <li><cite></cite><a href="managerolepower.jsp"   target="rightFrame">管理角色权限</a><i></i></li>
        <li><cite></cite><a href="manageuserrole.jsp"   target="rightFrame">管理用户角色</a><i></i></li>
    </ul>
    </dd>
    <%}else{}%>
    <%if(managetable.equals("1")||userid.equals("admin")) {%>
    <dd><div class="title"><span></span>表格权限</div>
    <ul class="menuson">
        <li><cite></cite><a href="tablepower.jsp" target="rightFrame">表格权限</a><i></i></li>
        <!-- <li><cite></cite><a href="examinetablepower.jsp" target="rightFrame">审查权限</a><i></i></li> -->
    </ul>
    </dd>  
     <%}else{}%>
   
    <%if(userid.equals("admin")){ %>
    <dd><div class="title"><span></span>通知管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="main.jsp"  target="#">用户反馈</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">抽奖</a><i></i></li>
        <li><cite></cite><a href="main.jsp"  target="#">站内通知</a><i></i></li>
    </ul>
    
    </dd> 

    <dd><div class="title"><span></span>栏目管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="addMenu.jsp" target="rightFrame">新增栏目</a><i></i></li>
        <li><cite></cite><a href="<%=basePath%>getMenu" target="rightFrame">编辑栏目</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span></span>日志管理</div></dd>   
    
    <%} %>
     <%if(userid.equals("admin")){ %>
      <dd><div class="title"><span></span>发布报表</div>
    <ul class="menuson">
    <li><cite></cite><a href="uploadModel.jsp" target="rightFrame">上传表格模板</a><i></i></li>
    <li><cite></cite><a href="<%=basePath%>listFileServlet" target="rightFrame">管理表格模板</a><i></i></li>
    <li><cite></cite><a href="publishtable.jsp" target="rightFrame">表格发布</a><i></i></li>
    </ul>
    </dd> 
    <dd><div class="title"><span></span>表格操作</div>
    <ul class="menuson">
    	<li><cite></cite><a href="watchtable.jsp" target="rightFrame">表格查看</a><i></i></li>
        <li><cite></cite><a href="filltable.jsp" target="rightFrame">表格填报</a><i></i></li>
        <!-- <li><cite></cite><a href="reviewsituation.jsp" target="rightFrame">审核状态</a><i></i></li> -->
        <li><cite></cite><a href="collecttable.jsp" target="rightFrame">表格审核</a><i></i></li>
        <!-- <li><cite></cite><a href="exporttable.jsp" target="rightFrame">表格导出</a><i></i></li> -->
    </ul>
    </dd>  
    <%}else if(highestPower.equals("4")){ %>
    <dd><div class="title"><span></span>表格操作</div>
    <ul class="menuson">
    	<li><cite></cite><a href="watchtable.jsp" target="rightFrame">表格查看</a><i></i></li>
        <li><cite></cite><a href="filltable.jsp" target="rightFrame">表格填报</a><i></i></li>
        <!-- <li><cite></cite><a href="reviewsituation.jsp" target="rightFrame">审核状态</a><i></i></li> -->
        <li><cite></cite><a href="collecttable.jsp" target="rightFrame">表格审核</a><i></i></li>
        <!-- <li><cite></cite><a href="exporttable.jsp" target="rightFrame">表格导出</a><i></i></li> -->
    </ul>
    </dd>  
    <%}else{ %>
    <dd><div class="title"><span></span>表格操作</div>
    <ul class="menuson">
    	<li><cite></cite><a href="watchtable.jsp" target="rightFrame">表格查看</a><i></i></li>
        <li><cite></cite><a href="filltable.jsp" target="rightFrame">表格填报</a><i></i></li>
        <!-- <li><cite></cite><a href="reviewsituation.jsp" target="rightFrame">审核状态</a><i></i></li> -->
        <li><cite></cite><a href="examinetable.jsp" target="rightFrame">表格审核</a><i></i></li>
        <!-- <li><cite></cite><a href="exporttable.jsp" target="rightFrame">表格导出</a><i></i></li> -->
    </ul>
    </dd>  
    <%}%>
    
    <dd><div class="title"><span></span>统计分析</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>admin/statistcAndAnalysis.jsp" target="rightFrame">数据分析报告</a><i></i></li>
    </ul>
    </dd>  
    </dl>
    
</body>
</html>
