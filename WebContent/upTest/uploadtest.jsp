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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.js"></script>
<title>导入测试</title>

<script type="text/javascript">
	$(function(){
		$(".thumbs a").click(function(){
			var largePath  = $(this).attr("href");
			var largeAlt = $(this).attr("title");
			$("#largeImg").attr({
				src : largePath,
				alt : largeAlt
			});
			return false;
		});
	})
	
	
	

</script>


</head>
<body>
     ${result}</br>
              成功更新：${count}行
</body>
</html>