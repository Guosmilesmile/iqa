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
<link href="<%=basePath %>css/manageuser.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">

function Getalluser(pagenow){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getalluser',
		data:{pagenow:pagenow},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#usertable');
			var str ="";
			if(List.length > 0){
				for(var i=0;i<List.length;i++){
					var userid = List[i].u_userid;
					var username = List[i].u_username;
					var password = List[i].u_password;
					var islive = List[i].u_islive;
					str+="<tr id=''>"+
					"<td><input name='' type='checkbox' value='' id=''/></td>"+
					"<td><input class='newsuneditable' id='userid' value='"+userid+"' readonly='readonly'></input></td>"+ 
				    "<td><input class='newsuneditable' id='username' value='"+username+"' readonly='readonly'></input></td>"+
				    "<td><input class='newsuneditable' id='password' value='"+password+"' readonly='readonly'></input></td>"+
				    "<td><input class='newsuneditable' id='islive' value='"+islive+"' readonly='readonly'></input></td>"+
				    "<td class='newseditable'>"+
				        	"<a href='javascript:;'  class='tablelink edit' id='edit"+userid+"'>修改密码</a>"+
							"<a href='javascript:;' class='tablelink delete' id='change"+userid+islive+"'> 启用转换</a>"+
					"</td></tr> ";
				}
			}
			tbody.html(str);
		},
		error:function(){
			alert("fail");
		}
		
	});
}
$(document).ready(function(){
 	var pagenow =1;
	Getalluser(pagenow);
	$('.tablelink.edit').live('click',function(){
		 var userid = $(this).attr("id");
		 userid = userid.substring(4,userid.length);
		 $('#alterid').val(userid);
		 $('#alterpwd').show();
	});
	$('.tablelink.delete').live('click',function(){
		 var userid = $(this).attr("id");
		 var islive = userid.substring(userid.length-1,userid.length);
		 $('#changeliveid').val(islive);
		 userid = userid.substring(6,userid.length-1);
		 $('#changeid').val(userid);
		 
		 
		 $('#changelive').show();
	});
	$('#changecancel').click(function(){
		$('#changelive').hide();
	})
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
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>账号<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>用户名</th>
        <th>密码</th>
        <th>是否启用</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="usertable">
        
        
 		<tr id="">
 		<td><input name="" type="checkbox" value="" id="  "/></td>
        <td><input class="newsuneditable" id="userid" value="" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="username" value="" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="password" value="" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="islive" value="" readonly="readonly"></input></td>
        <td class="newseditable">
        	<a href="#" class="tablelink edit" id="edit">修改密码</a>
			<a href="#" class="tablelink delete" id="delete"> 启用转换</a>
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
	<div id="alterpwd">
		<form action="alterpw">
			账　号　<input id="alterid" type="text" name="userid" value="" readonly="readonly"></br></br>
			新密码　<input id="alterpassword" name="password" type="text" value=""></br></br>
			<input id="altersubmit" type="submit" value="确定">
			<input id="altercancel" type="button" value="取消">
		</form>
	</div>
	<div id="changelive">
		<form action="changelive">
			<input id="changeid" type="hidden" name="userid" value="" readonly="readonly"></br></br>
			<input id="changeliveid" type="hidden" name="liveid" value="" readonly="readonly"></br></br>
			<input id="changesubmit" type="submit" value="确定">
			<input id="changecancel" type="button" value="取消">
		</form>
	</div>
</body>
</html>