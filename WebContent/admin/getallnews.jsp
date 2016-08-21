<%@page import="cn.edu.xmu.table.NewsTable"%>
<%@page import="cn.edu.xmu.daoimpl.NewsDaoImpl"%>
<%@page import="cn.edu.xmu.dao.NewsDao"%>
<%@ page language="java" import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宣传列表管理</title>
<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 	
	$(".edit").click(function(){
		var id = $(this).attr("id").toString().substring(4);
		window.location.href="updatenews.jsp?newsid="+id;
	});
	
	$(".delete").click(function(){
		var id = $(this).attr("id").toString().substring(6);
		$("#ifDelete").show();
		$("#confirmDelete").attr("name",id);
	});
	//确认删除
	$("#confirmDelete").click(function(){
		/*删除菜单*/
		var id = $(this).attr("name");
		 $.ajax({
	            type: 'get',
	            url: "<%=basePath %>deletenews",
	            data: {
	            	newsid:id,
	            },
	            success: function(msg){
	            	if(msg == "1"){
	            		alert("删除成功");
	            		$("#menuContent"+id).remove();
	            		/*应该提示删除成功*/
	            	}
	        		
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
	                alert(XMLHttpRequest.status);
	                alert(XMLHttpRequest.readyState);
	                alert(textStatus);
	            },
	 		});
		 $("#ifDelete").hide();
	});
	//取消删除
	$("#cancelDelete").click(function(){
		$("#ifDelete").hide();
	});
});
</script>
</head>
<body>
	<%	
		int pageSize =15;
		NewsDao newsdao = new NewsDaoImpl();
		Map param = new HashMap();
		int rowcount = newsdao.getNewsCount(param);
		//计算页数
		int pageCount;
		if(rowcount%pageSize!=0){
			if(rowcount<pageSize){
				pageCount = 1;	
			}
			else{
				pageCount = (rowcount/pageSize==0)?(rowcount/pageSize):(rowcount/pageSize+1);	
			}
		}
		else{
			pageCount = (rowcount/pageSize==0)?0:rowcount/pageSize;
		}
		
		String currentPage_s=request.getParameter("currentPage");//reques获取当前页码  
		int currentPage=1;       //初始化为第一页  
		if(currentPage_s!=null){        
		      currentPage=Integer.valueOf(currentPage_s);  
		}  
		if(currentPage<1){  
		      currentPage=1;  
		}  
		if(currentPage>pageCount){  
		      currentPage=pageCount;  
		}  
		int start = (currentPage-1)*pageSize;
		int end = currentPage*pageSize;
		System.out.print("start="+start);
		System.out.print("end="+end);
		//改变当前页的值，调用查询数据的方法，返回数据集合  
		List<News> newsList=newsdao.findForPage(start, pageSize,  NewsTable.N_ID,"desc", param);
	%>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=basePath %>admin/myInfo.jsp" >首页></a></li>
    <li><a href="#">新闻管理></a></li>
    <li><a href="#">新闻列表查看</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>编号<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>新闻标题</th>
        <th>作者</th>
        <th>所属栏目</th>
        <th>所属子栏目</th>
        <th>版本</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        <%
			if(!newsList.isEmpty() && newsList.size() > 0){
 			for(News news : newsList){
 				int id = news.getN_id();
 				String title = news.getN_titles();
 				String author = news.getN_author();
 				String content = news.getN_content();
 				String n_class = news.getN_class();
 				String n_subclass = news.getN_subclass();
 				String version = news.getN_version();
 		%>
 		<tr id="menuContent<%=news.getN_id() %>">
 		<td><input name="" type="checkbox" value="" id="checkBox<%=news.getN_id() %> "/></td>
        <td><input class="newsuneditable" id="number<%=news.getN_id() %>" value="<%=news.getN_id() %>" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="title<%=news.getN_id() %>" value="<%=title %>" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="author<%=news.getN_id() %>" value="<%=author %>" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="n_class<%=news.getN_id() %>" value="<%=n_class %>" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="n_subclass<%=news.getN_id() %>" value="<%=n_subclass %>" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="version<%=news.getN_id() %>" value="<%=version %>" readonly="readonly"></input></td>
        <td class="newseditable">
        	<a href="#" class="tablelink edit" id="edit<%=news.getN_id() %>">修改</a>
			<a href="#" class="tablelink delete" id="delete<%=news.getN_id() %>"> 删除</a>
			<a href="#" class="tablelink confirmAlter" id="confirmAlter<%=news.getN_id() %>">   确认</a>
			</td>
 		</tr> 
 		<%
 			}}
        %>
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue"> <%=rowcount %> </i>个纪录</div>
         <ul class="paginList">
        <li class="paginItem"><a href=getallnews.jsp?currentPage=1 target='_self' >首页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage=<%=currentPage-1%> target='_self'>上页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage=<%=currentPage+1%> target='_self'>下页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage=<%=pageCount%> target='_self' >末页</a></li>
        </ul> 
    </div>
  
    
    <div class="tip" style="display: none;">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="<%=basePath %>images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    <div class="tip" style="display: none;" id="ifDelete">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
      <div class="tipinfo">
        <span><img src="<%=basePath %>images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认删除该菜单项 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" id="confirmDelete" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" id="cancelDelete"/>
        </div>
    </div>
    
   <%--  <div>
    <form name="deleteMenuForm" id="deleteMenuForm" action="<%=basePath %>deleteMenu" method="post">
   		<input type="hidden" id = "deleteId" name="deleteId" value="">
    </form>
    </div> --%>
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>