<%@ page language="java" import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>厦大质量保证</title>
<style type="text/css"></style>
<script type="text/javascript" src="./js/jquery-1.3.1.min.js"></script>
<link rel="stylesheet" href="css/mycss-cn.css">
<link href="css/forPagenation.css" rel="stylesheet"/>
<script src="js/myJs.js"></script>
<script type="text/javascript">
function toPage(currentPage,n_subclass){
	window.location.href="getallnews?page="+currentPage+"&n_subclass="+n_subclass+"&language="+<%=session.getAttribute("language")%>;
}
$(document).ready(function () { 
    var pages = '<%=request.getAttribute("pages") %>';
    var currentPage = '<%=request.getAttribute("currentPage") %>';
    var n_subclass = '<%=request.getAttribute("n_subclass") %>';
	$(".pageBtn").click(function(){
		var id = $(this).attr("id").toString().substring(7);
		/* alert(id); */
		if(id == 'first'){
			//跳转到第一页
			currentPage = 1;
		}else if(id == 'last'){
			//跳转到最后一页
			currentPage = pages;
		}else if(id == 'pre'){
			//跳转到上一页
			currentPage --;
		}else if(id == 'next'){
			//跳转到下一页
			currentPage ++;
		}else{
			//跳转到第id页
			currentPage = id;
		}
		 if(currentPage <= pages){
			toPage(currentPage,n_subclass);
		} 
	});
	$(".readContent").click(function(){
		var id = $(this).attr("id").toString().substring(11);
		window.location.href="document.jsp?contentId="+id;
	});
});
</script>
</head>

<body>
<div class="header">
<div class="top"></div>
<a href="#"><img class="logo" src="images/newModel/logo.png"/></a>
<ul id="navigation"> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">简介</a> 
     <ul> 
      <li><a href="#">概况</a></li> 
      <li><a href="#">教育质量系统框架</a></li> 
      <li><a href="#">质量评估框架</a></li>
      <li><a href="#">组织保障</a></li>
      <li><a href="#">文件</a></li>   
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">本科评估</a> 
     <ul> 
      <li><a href="#">概况</a></li> 
      <li><a href="#">文件</a></li>
      <li><a href="#">报告</a></li> 
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">课程评价</a> 
     <ul> 
      <li><a href="#">概况</a></li> 
      <li><a href="#">文件</a></li>
      <li><a href="#">报告</a></li>
      <li><a href="#">问卷</a></li> 
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">学习经历调查</a> 
     <ul> 
      <li><a href="#">概况</a></li> 
      <li><a href="#">文件</a></li>
      <li><a href="#">报告</a></li>
      <li><a href="#">问卷</a></li> 
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">教学基本状态数据</a> 
     <ul> 
      <li><a href="#">概况</a></li> 
      <li><a href="#">文件</a></li>
      <li><a href="#">数据</a></li>
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">课堂听课</a> 
     <ul> 
      <li><a href="#">文件</a></li>
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">教师发展</a> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">教学研究</a> 
     <ul> 
      <li><a href="#">教学成果奖</a></li> 
      <li><a href="#">研究论文</a></li>
      <li><a href="#">研究课题</a></li>
      <li><a href="#">项目评审</a></li>
     </ul> 
    </li> 
    <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
     <a href="#">下载中心</a> 
    </li>
</ul>
<div class="info">
<a class="headerLink" href="index.html">English version</a>
<div class="clear"></div>
  <input id="headerbutton" type="submit" value="" />
  <input id="headerinput" type="text" name="search" />
<div class="clear"></div>
 <input id="headerlogin" type="submit" value="login" />
</div>

</div>
    <!-- end .header -->
<div class="container">
  <div class="sidebar2">
    <h1>最新</h1>
    <h2>问卷</h2>
    <div class="question">
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
    </div><div class="clear"></div>
    <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">更多>></a>
  </div>
  <!-- end .sidebar1 -->
  <div class="content">
   <div class="download">
   <div class="con_header">
   <c:if test="${n_subclass == 1 }">
    <h1>新闻</h1>
    <h2>资讯</h2>
   </c:if>
   <c:if test="${n_subclass == 2 }">
    <h1>文件</h1>
    <h2>列表</h2>
   </c:if>
   <c:if test="${n_subclass == 3 }">
    <h1>报告</h1>
    <h2>列表</h2>
   </c:if>
     <hr style="position: relative; width:100%;">
   </div>
    <ul>
    <c:forEach items="${newslist.rows }" var="item" varStatus="status">
	    <li>
	    <div class="description">
	    <h3>${item.n_titles }</h3>
	    <div class="clear"></div>
	    <p>${item.n_summary }</p>
	    </div>
	    <a class='displayicon readContent' href='#' id='readContent${item.n_id }'>查看详情 <img src='./images/newModel/arrow.png'/></a>
	    <hr style=" position:relative;border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:100%;">  
	    </li>
 	</c:forEach>
   </ul>
   </div>
   <!-- <a class="readmore" href="#">下一页>></a>  -->
   </div> 
   
   <!-- end .content -->
  </div>
    <br>
    <div class="digg">
            <%
            int pageSize = 10;//暂时
            GridDataModel newsModel = new GridDataModel();
			List<News> newsList = newsModel.getRows();
            int pages = Integer.parseInt(request.getAttribute("pages").toString());
            int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
            int pageNum = 7;//控制最多显示7个页面数
            int pageHalf = pageNum/2;//暂时等于3，表示中间页面两边均等的页面数
            if(currentPage == 1){
            %>
			<a class="disabled">首页 </a>
			<a class="disabled">上一页</a>
			<%}else{ %>
			<a class="pageBtn" id="pageBtnfirst">首页 </a>
			<a class="pageBtn" id="pageBtnpre">上一页</a>
			<%} 
            if(pageNum < pages){
            	if(currentPage <= pageHalf+1){
            		for(int i = 1;i <= pageNum;i++){
                		if(i == currentPage){
               			%>
               			<a class="pageBtn current" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}else{
               			%>
               			<a class="pageBtn" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}
                	}
            	}else if((pages-currentPage) < pageHalf){
            		for(int i = pages-(pageNum-1);i <= pages;i++){
                		if(i == currentPage){
               			%>
               			<a class="pageBtn current" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}else{
               			%>
               			<a class="pageBtn" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}
                	}
            	}else{
            		for(int i = currentPage-3;i <= currentPage+3;i++){
                		if(i == currentPage){
               			%>
               			<a class="pageBtn current" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}else{
               			%>
               			<a class="pageBtn" id="pageBtn<%=i %>"><%=i %></a>
               			<%
                		}
                	}
            	}   	
            }
            else{
            	//for(int i = 1;i <= 4;i++){
            	for(int i = 1;i <= pages;i++){
            		if(i == currentPage){
           			%>
           			<a class="pageBtn current" id="pageBtn<%=i %>"><%=i %></a>
           			<%
            		}else{
           			%>
           			<a class="pageBtn" id="pageBtn<%=i %>"><%=i %></a>
           			<%
            		}
            	}
            }
            if(currentPage == pages){
           	%>
           	<a class="disabled">下一页</a>
			<a class="disabled">尾页</a>
           	<%
            }else{
           	%>
           	<a class="pageBtn"  id="pageBtnnext">下一页</a>
			<a class="pageBtn"  id="pageBtnlast">尾页</a>
           	<%
            }
			%>
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
    <img src="images/newModel/logo_bottom.png"/>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>