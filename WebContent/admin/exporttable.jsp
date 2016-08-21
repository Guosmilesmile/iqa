<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String userid ="";
	Cookie[] cookies =request.getCookies();
	for(Cookie cookie : cookies){
    if(cookie.getName().equals("userid"))
    	userid=cookie.getValue();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>css/exporttable.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script>
function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
};

	function getuserfill(userid){
		$.ajax({
			type:'post',
			url:'<%=basePath %>getuserfill',
			data:{userid:userid},
			success:function(ListJsonStr){
				var List = eval('(' + ListJsonStr + ')');
				var tbody = $('#usertableable');
				var str ="";
				if(List.length > 0){
					for(var i=0;i<List.length;i++){
						var tableid = List[i].tableid;
						var tablename = List[i].tablename;
						str+="<tr id=''>"+
							"<td><input name='' type='checkbox' value='' id='' /></td>"+
							"<td><input class='newsuneditable' id='tableid' value='"+tableid+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
							"readonly='readonly'></input></td>"+
							"<td class='newseditable'><a href='#' class='tablelink edit'"+
							"id='fill_"+tableid+"'>导出</a></td></tr>";
					}
				}
				tbody.html(str);
			},error:function(){
				alert("fail");
			}
		});
	};
	$(document).ready(function() {
		$('#chooseul li:eq(0)').css('border', '1px solid black');
		var userid  = getCookie('userid');
		getuserfill(userid);
		$('#chooseul li').click(function() {
			$(this).css('border', '1px solid black');
			$(this).siblings().css('border', '0px');
			var indexs = $(this).index();
			if (indexs == 0) {
				getuserfill(userid);
				$('#tipone').show();
				$('#tiptwo').hide();
				$('#tablewhere').html('可填报表格');
			} 
		});
		
		$('.tablelink.edit').live('click',function(){
			var fillid = $(this).attr('id');
			var tableid = fillid.substring(5,fillid.length);
			location.href="../exportServlet?tableid="+tableid;
		});
		
		$('.tablelink.editt').live('click',function(){
			var tableid = '12';
			 location.href = "../gotofilltable?fillid="+tableid; 
		});
		
	});
</script>
<title>Insert title here</title>
</head>
<body>
	
	<input type='hidden' id='topuserid' values='<%=userid%>'>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">用户管理></a></li>
			<li><a href="#">表格导出></a></li>
		</ul>
	</div>
	<div id='tipone' class='tip'>
		<div class="rightinfo">

			<table class="tablelist">
				<thead>
					<tr>
						<th><input name="" type="checkbox" value="" checked="checked" /></th>
						<th>表报id<i class="sort"><img
								src="<%=basePath%>images/px.gif" /></i></th>
						<th>表名</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="usertableable">


					<tr id="">
						<td><input name="" type="checkbox" value="" id="  " /></td>
						<td><input class="newsuneditable" id="userid" value=""
							readonly="readonly"></input></td>
						<td><input class="newsuneditable" id="username" value=""
							readonly="readonly"></input></td>
						<td class="newseditable"><a href="#" class="tablelink edit"
							id="edit">导出</a></td>
					</tr>

				</tbody>
			</table>


			<div class="pagin">
				<div class="message">
					共<i class="blue"> </i>个纪录
				</div>
				<ul class="paginList">
					<li class="paginItem"><a href=getallnews.jsp?currentPage=1
						target='_self'>首页</a></li>
					<li class="paginItem"><a href=getallnews.jsp?currentPage=
						target='_self'>上页</a></li>
					<li class="paginItem"><a href=getallnews.jsp?currentPage=
						target='_self'>下页</a></li>
					<li class="paginItem"><a href=getallnews.jsp?currentPage=
						target='_self'>末页</a></li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>




	</div>
	<div id='tiptwo' class='tip'>
		<div id='tipone' class='tip'>
			<div class="rightinfo">

				<table class="tablelist">
					<thead>
						<tr>
							<th><input name="" type="checkbox" value=""
								checked="checked" /></th>
							<th>报表id<i class="sort"><img
									src="<%=basePath%>images/px.gif" /></i></th>
							<th>表名</th>
							<th>填报时间</th>
							<th>审批id</th>
							<th>审批状态</th>
							<th>审批时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="usertable">


						<tr id="">
							<td><input name="" type="checkbox" value="" id="  " /></td>
							<td><input class="newsuneditable" id="userid" value="12"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="username" value="4-2-3优势专业情况"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="password" value="2014-4-16"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="islive" value=""
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="islive" value="未审核"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="islive" value=""
								readonly="readonly"></input></td>
							<td class="newseditable"><a href="#" class="tablelink editt"
								id="edit">修改</a> <a href="#" class="tablelink delete"
								id="delete">删除</a></td>
						</tr>

					</tbody>
				</table>


				<div class="pagin">
					<div class="message">
						共<i class="blue"> </i>个纪录
					</div>
					<ul class="paginList">
						<li class="paginItem"><a href=getallnews.jsp?currentPage=1
							target='_self'>首页</a></li>
						<li class="paginItem"><a href=getallnews.jsp?currentPage=
							target='_self'>上页</a></li>
						<li class="paginItem"><a href=getallnews.jsp?currentPage=
							target='_self'>下页</a></li>
						<li class="paginItem"><a href=getallnews.jsp?currentPage=
							target='_self'>末页</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>