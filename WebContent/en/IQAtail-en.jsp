<%@ page language="java" import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>XMU IQA</title>
<style type="text/css"></style>
<link rel="stylesheet" href="<%=basePath%>css/mycss.css">
<script src="<%=basePath%>js/myJs.js"></script>
</head>

<body>
  <div class="footer">
    <div class="footerinfo">
    <h4>QUICK LINKS</h4>
    <a href="http://gs.xmu.edu.cn/">Graduate School</a><br/>
    <a href="http://xdkc.xmu.edu.cn/">Curriculum Mapping</a><br/>
    <a href="http://xsctemp.xmu.edu.cn/">Students' Affairs Office</a><br/>
    <a href="http://jwc.xmu.edu.cn/">Academic Affairs Office</a><br/>    
    <a href="http://ctld.xmu.edu.cn/">Center for Teaching and Learning Development</a>
    </div>
    <div class="footerinfo2">
    <h4>CONTACT US</h4>
    <p>Email：jwc@xmu.edu.cn </p>
    <p>Fax  ：2096192</p>
    </div>
    <img src="<%=basePath %>images/newModel/logo_bottom.png"/>
    <!-- end .footer --></div>
</body>
</html>