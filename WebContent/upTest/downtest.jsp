<%@ page language="java" import="java.io.*" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>下载测试</title>
</head>
<body>
<%
String filepath = getServletContext().getRealPath("/") +"upload/"; 
String names = ""; 
try { 
File f = new File(filepath); 
if (f.isDirectory()) 
{ 
File[] fList = f.listFiles(); 
for (int j = 0; j < fList.length; j++) { 
File file = fList[j]; 
if (file.isFile()) 
{ 
names += file.getName(); 
} 
} 
} 
}
catch (Exception e){} 
%>  

<p><a href="<%=basePath%>downloadServlet?filename=<%=names%>"><%=names%></a></p>
</body>
</html>