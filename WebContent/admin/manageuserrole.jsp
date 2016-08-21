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
<link href="<%=basePath %>css/manageuserrole.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function getallrole(){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getallrole',
		data:{},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#addrolename');
			var str ="";
			if(List.length > 0){
				for(var i=0;i<List.length;i++){
					var roleid = List[i].r_roleid;
					var rolename = List[i].r_rolename;
					str+="<option value="+roleid+">"+rolename+"</option>";
				}
			}
			tbody.html(str);
		},error:function(){
			alert("fail");
		}
	});
};
function  getalluserrole(){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getalluserrole',
		data:{},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#usertable');
			var str ="";
			if(List.length > 0){
				for(var i=0;i<List.length;i++){
					var userid = List[i].userid;
					var rolename = List[i].rolename;
					var id= List[i].id;
					str+="<tr id=''>"+
			 		"<td><input name='' type='checkbox' value='' id=''/></td>"+
			        "<td><input class='newsuneditable' id='roleid' value='"+userid+"' readonly='readonly'></input></td>"+ 
			        "<td><input class='newsuneditable' id='rolename' value='"+rolename+"' readonly='readonly'></input></td>"+
			        "<td class='newseditable'>"+
						"<a href='#' class='tablelink delete' id='delete"+id+"'> 删除</a>"+
						"</td></tr>"; 
				}
			}
			tbody.html(str);
			},error:function(){
				
			}
		});
}
$(document).ready(function(){
	getallrole();
	getalluserrole();
	$('.tablelink.delete').live('click',function(){
		$('.tip').show();
		var deleteid = $(this).attr("id");
		var id = deleteid.substring(6,deleteid.length);
		$('#deleid').val(id);
			
 	});
 	$('#cancelDelete').click(function(){
 		$('.tip').hide();
 	});
 	$('#cancelbtn').click(function(){
 		$('#ifAdd').hide();
 	});
 	$('#adduserrole').click(function(){
 		$('#ifAdd').show();
 	});
});
</script>
</head>
<body>
	

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=basePath %>admin/myInfo.jsp" >首页></a></li>
    <li><a href="#">用户管理></a></li>
    <li><a href="#">管理用户</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <input type="button" value="增加用户角色" id="adduserrole">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
       <%--  <th>编号<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th> --%>
        <th>账号</th>
        <th>角色名</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="usertable">
        
        
 		<tr id="">
 		<td><input name="" type="checkbox" value="" id="  "/></td>
        <td><input class="newsuneditable" id="userid" value="admin" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="rolename" value="超级管理员" readonly="readonly"></input></td>
        <td class="newseditable">
			<a href="#" class="tablelink delete" id="delete"> 删除</a>
			</td>
 		</tr> 
 		
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">  </i>个纪录</div>
         <ul class="paginList">
        <li class="paginItem"><a href=getallnews.jsp?currentPage=1 target='_self' >首页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self'>上页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self'>下页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self' >末页</a></li>
        </ul> 
    </div>
  
  <%--   
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
     --%>
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
          <form action="<%=basePath %>deleteuserrole">
        	<input type="hidden" id="deleid" name="deleid">
        <input name="" type="submit"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" id="cancelDelete" value="取消" />
        </form>
        </div>
    </div>
    
    
    <div id="ifAdd">
		<form action="<%=basePath%>adduserrole">
			账号　　:<input type="text" name="userid" ></br></br>
			角色名:<!-- <input type="text" name="roleid" id="addrolename"> -->
			<select name="roleid" id="addrolename">
			
			</select></br></br>
			<input type="submit" value="确定" id="subbtn">
			<input type="button" value="取消" id="cancelbtn">
		</form>
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