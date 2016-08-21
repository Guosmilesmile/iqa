<%@page import="cn.edu.xmu.table.SlideNewsTable"%>
<%@page import="cn.edu.xmu.daoimpl.SlideNewsDaoImpl"%>
<%@page import="cn.edu.xmu.dao.SlideNewsDao"%>
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
/* function deleteMenu(menuId){
	document.getElementById('deleteId').value = menuId;   //赋值给隐藏域字段
	document.deleteMenuForm.submit();                     //提交表单
}  */

$(document).ready(function(){
	
	$(".edit").click(function(){
		var id = $(this).attr("id").toString().substring(4);
		window.location.href="updateslidenews.jsp?slidenewsid="+id;
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
	            url: "<%=basePath %>deleteslidenews",
	            data: {
	            	slidenewsid:id,
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
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        <%
       		SlideNewsDao slidenewsDao = new SlideNewsDaoImpl();
        	Map param = new HashMap();
			List<SlideNews> slidenewsList = slidenewsDao.findForPage(0, 20, SlideNewsTable.SN_ID, "desc", param);
			if(!slidenewsList.isEmpty() && slidenewsList.size() > 0){
 			for(SlideNews slidenews : slidenewsList){
 				int id = slidenews.getSn_id();
 				String title = slidenews.getSn_titles();
 		%>
 		<tr id="menuContent<%=slidenews.getSn_id() %>">
 		<td><input name="" type="checkbox" value="" id="checkBox<%=slidenews.getSn_id() %> "/></td>
        <td><input class="newsuneditable" id="number<%=slidenews.getSn_id() %>" value="<%=slidenews.getSn_id() %>" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="title<%=slidenews.getSn_id() %>" value="<%=title %>" readonly="readonly"></input></td>
        <td class="newseditable">
        	<a href="#" class="tablelink edit" id="edit<%=slidenews.getSn_id() %>">修改</a>
			<a href="#" class="tablelink delete" id="delete<%=slidenews.getSn_id() %>"> 删除</a>
			<a href="#" class="tablelink confirmAlter" id="confirmAlter<%=slidenews.getSn_id() %>">   确认</a>
			</td>
 		</tr> 
 		<%
 			}}
        %>
        
        
        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue"> <%=slidenewsList.size() %> </i>个纪录</div>
         <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="javascript:;">1</a></li>
        <li class="paginItem "><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
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