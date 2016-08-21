<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>css/filltable.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
<script>
</script>
<title>下载表格模板</title>
</head>
<body>

	<input type='hidden' id='topuserid'>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">发布报表></a></li>
			<li><a href="#" id='tablewhere'>管理表格模板</a></li>
		</ul>
	</div>
	<div id='tiptwo' class='tip'>
		<div id='tipone' class='tip'>
			<div class="rightinfo">

				<table class="tablelist">
					<thead>
						<tr>	
							<th>表名<i class="sort"><img
									src="<%=basePath%>images/px.gif" /></i></th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="userwatchtable">
					       <c:forEach var="me" items="${fileNameMap}">
       <c:url value="./downloadServlet" var="downurl">
       <c:param name="filename" value="${me.key}"></c:param>
       </c:url >
       <c:url value="./deleteFileServlet" var="deletefileurl">
       <c:param name="filename" value="${me.key}"></c:param>
       </c:url >
                
         <br/>

						<tr>
						<td>${me.value}</td>
						<td><a href="${downurl}">下载</a></td>
						</tr>
       </c:forEach>
					</tbody>
				</table>


				<div class="pagin">
					<div class="message">
					</div>
					<ul class="paginList">
						<li class="paginItem"><a id='firstpage' href="#">首页</a></li>
						<li class="paginItem"><a id='prepage' href="#">上页</a></li>
						<li class="paginItem"><a id='nextpage' href="#">下页</a></li>
						<li class="paginItem"><a id='lastpage' href="#">末页</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>