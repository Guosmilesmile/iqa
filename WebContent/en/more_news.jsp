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
<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/mycss.css">
<link href="<%=basePath%>css/forPagenation.css" rel="stylesheet"/>
<script src="<%=basePath%>js/myJs.js"></script>
<script type="text/javascript">
function toPage(currentPage,n_subclass){
	window.location.href="getallnews?page="+currentPage+"&n_subclass="+n_subclass+"&version=en";
}
function getQuestionnaires(){
	var menuId = '<%=request.getAttribute("menuId") %>';
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"30",//问卷
        	version:"en",
        },
        success: function(newsListJsonStr){
        	var div_body = $("#quetionnaire");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){

        		    str +="<a href='#' class='readQuestionnaire' id='readContent"+newsList[i].n_id+"'>"+newsList[i].n_titles+"</a><p></p>";

          	}
        		div_body.html(str);
        		$(".readQuestionnaire").click(function(){
   				//	var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>login.jsp";
   				})
        	}
        	else{
        		var emptyStr = "<li><div class='question'><h3>NO RELATED INFORMATION NOW</h3>"
    		    +"</div></li>";
    		    div_body.html(emptyStr);
        	}
		},
        error: function(){}
        	
	});
}

$(document).ready(function () { 
	getQuestionnaires();
	
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
		window.location.href="<%=basePath%>en/document.jsp?contentId="+id;
	});
});
</script>
</head>

<body>
<%-- <%@ include file="IQAhead-en.jsp"%> --%>
<jsp:include page="IQAhead-en.jsp"></jsp:include>
<!-- end .header -->
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="sidebar2">
   <!--  <h1>Latest</h1> -->
    <h2>QUESTIONNARIE</h2>
    <div class="question" id="quetionnaire">
      <!-- <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p>
      <a href="#">Question</a>
       <p> description.....</p> -->
    </div><div class="clear"></div>
     <hr style="border:1px dashed #999;border-bottom:0;border-right:0; border-left:0;width:90%;">
    <a class="readmore" href="#">Read More>></a>
  </div>
  <!-- end .sidebar1 -->
  <div class="content">
   <div class="download">
   <div class="con_header">
   <c:if test="${n_subclass == 1 }">
    <h2>NEWS</h2>
   </c:if>
   <c:if test="${n_subclass == 2 }">
    <h2>FILES</h2>
   </c:if>
   <c:if test="${n_subclass == 3 }">
    <h2>REPORTS</h2>
   </c:if>
   <c:if test="${n_subclass == 4 }">
    <h2>DATA</h2>
   </c:if>
     <c:if test="${n_subclass == 291 }">
    <h2>TeachingAchievementAwards</h2>
   </c:if>
   <c:if test="${n_subclass == 292 }">
    <h2>ResearchPapers</h2>
   </c:if>
   <c:if test="${n_subclass == 293 }">
    <h2>ResearchTopics</h2>
   </c:if>
   <c:if test="${n_subclass == 294 }">
    <h2>ProjectAppraisal</h2>
    </c:if>
   </div>
    <ul>
    <c:forEach items="${newslist.rows }" var="item" varStatus="status">
	    <li>
	    <div class="description">
	    	<c:if test="${item.n_content == ''}">
	    	<a href="#"><h3 class="" id='readContent${item.n_id }'> ${item.n_titles }</h3></a>    	
	    	</c:if>
	    	<c:if test="${item.n_content != ''}">
	    	<a href="#"><h3 class="readContent" id='readContent${item.n_id }'> ${item.n_titles }</h3></a>    	
	    	</c:if>
	    </div>
	     <%-- <p class="desc_p">${item.n_publishtime }</p> --%>
	    </li>
 	</c:forEach>
   </ul>
   <div class="clear"></div>
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
			<a class="disabled">First</a>
			<a class="disabled">Pre</a>
			<%}else{ %>
			<a class="pageBtn" id="pageBtnfirst">First</a>
			<a class="pageBtn" id="pageBtnpre">Pre</a>
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
           	<a class="disabled">Next</a>
			<a class="disabled">Last</a>
           	<%
            }else{
           	%>
           	<a class="pageBtn"  id="pageBtnnext">Next</a>
			<a class="pageBtn"  id="pageBtnlast">Last</a>
           	<%
            }
			%>
  </div>
   </div>
   </div> 
   
   <!-- end .content -->
  </div>
  </div>
  <jsp:include page="IQAtail-en.jsp"></jsp:include>
</body>
</html>