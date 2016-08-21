<%@page import="cn.edu.xmu.table.NewsTable"%>
<%@page import="cn.edu.xmu.daoimpl.NewsDaoImpl"%>
<%@page import="cn.edu.xmu.dao.NewsDao"%>
<%@ page language="java"
	import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String userid ="";
	String college ="";
	Cookie[] cookies =request.getCookies();
	for(Cookie cookie : cookies){
    if(cookie.getName().equals("userid"))
    	userid=cookie.getValue();
    if(cookie.getName().equals("college"))
    	college=cookie.getValue();
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宣传列表管理</title>
<link href="<%=basePath %>css/manageuser.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
<script type="text/javascript">
function getexamtable(tableid,pagenow,rownumber){
	$.ajax({
		type:'post',
		url:'<%=basePath%>gettabledetail',
		data:{tableid:tableid},
		success:function(ListJsonList){
			
			var List = eval('(' + ListJsonList + ')');
			var str ="";
			var tbody = $('#usertable');
			if(List.length > 0){
				List = pagebean(List,'blue',pagenow,rownumber);
				for(var i=0;i<List.length;i++){
					var tablename = List[i].tablename;
					var rolename = List[i].rolename;
					var tableid = List[i].tableid;
					var fillsituation = List[i].rf_fillsituation;
					var reviewsituation = List[i].rf_reviewsituation;
					var roleid = List[i].roleid;
					str+="<tr id=''>"+
					"<td><input name='' type='checkbox' value='' id='' /></td>"+
					"<td><input style='width:80px;' class='newsuneditable' id='tableid' value='"+tableid+"'"+
					"readonly='readonly'></input></td>"+
					"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
					"readonly='readonly'></input></td>"+
					"<td><input class='roleid' id='rolename"+roleid+"' value='"+rolename+"'"+
					"readonly='readonly'></input></td>";
					if(fillsituation==0)
					str+="<td><input class='newsuneditable' style='width:120px;' id='tablename' value='未提交'"+
						"readonly='readonly'></input></td>";
					else{
						str+="<td><input class='newsuneditable' style='width:120px;' id='tablename' value='已提交'"+
						"readonly='readonly'></input></td>";
					}
					if(reviewsituation==0)
					str+="<td><input class='newsuneditable' id='tablename' value='未审核'"+
					"readonly='readonly'></input></td>";
		
					else if(reviewsituation==2)
							str+="<td><input class='newsuneditable' id='tablename' value='一级审核通过'"+
							"readonly='readonly'></input></td>";
					else if(reviewsituation==3)
						str+="<td><input class='newsuneditable' id='tablename' value='二级审核通过'"+
						"readonly='readonly'></input></td>";
					else if(reviewsituation==4)
							str+="<td><input class='newsuneditable' id='tablename' value='一级审核不通过'"+
							"readonly='readonly'></input></td>";
					else if(reviewsituation==5)
								str+="<td><input class='newsuneditable' id='tablename' value='二级审核不通过'"+
								"readonly='readonly'></input></td>";
					if(fillsituation==1)
						str+="<td class='newseditable'><a href='#' name="+fillsituation+" class='tablelink watch'"+
						"id='watch_"+reviewsituation+"_"+tableid+"_"+roleid+"'>查看     </a></td></tr>";
					else
						str+="<td class='newseditable'><a href='#' name="+fillsituation+" class='tablelink watch'"+
						"id='watch_"+reviewsituation+"_"+tableid+"_"+roleid+"'></a></td></tr>";
				}
			}
			tbody.html(str); 
		},error:function(){
			alert('fail');
		}
	});
};
function changeexamsituation(id,flag,userid,roleid){
	$.ajax({
		type:'post',
		url:'<%=basePath%>changeexamsituation',
		data:{id:id,flag:flag,userid:userid,roleid:roleid},
		success:function(){
			location.reload();
		},error:function(){
			alert('fail');
		}
		
	});
}
function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
};
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}
	var collegeflag =false;
	
$(document).ready(function(){
	var Request = new Object();
	Request = GetRequest();	
	var tableid = Request['tableid'];
	//alert(tableid);
	var pagenow = 1;
	var rownumber = 10;
	var userid  = getCookie('userid');
	var college  = getCookie('deptxi');
	if(collegeflag == false){
		college = decodeURI(college);
		collegeflag = true;
	}
	getexamtable(tableid,pagenow,rownumber);
	$('.tablelink.pass').live('click',function(){
		var passid = $(this).attr('id');
		var temp = passid.lastIndexOf("_");
		var roleid = passid.substring(temp+1);
		var tableid = passid.substring(5,temp);
		changeexamsituation(tableid,1,userid,roleid);
	/* 	var rolename = $('#rolename').val();
		changeexamsituation(passid,1,userid,rolename); */
	});
	$('.tablelink.unpass').live('click',function(){
		var unpassid = $(this).attr('id');
		var temp = unpassid.lastIndexOf("_");
		var roleid = unpassid.substring(temp+1);
		var tableid = unpassid.substring(7,temp);
		changeexamsituation(tableid,2,userid,roleid);
	});
	$('.tablelink.watch').live('click',function(){
		var watchid = $(this).attr('id');
		var temp = watchid.lastIndexOf("_");
		var str = watchid.substring(0,temp);
		var roleid  = watchid.substring(temp+1,watchid.length);
		temp = str.lastIndexOf("_");
		var tableid  = str.substring(temp+1,str.length);
		str = str.substring(0,temp);
		temp = str.lastIndexOf("_");
		var reviewsituation  = str.substring(temp+1,str.length);
		var fillsituation =  $(this).attr('name');
		location.href="<%=basePath %>gotowatchtable?tableid="+tableid+"&roleid="+roleid+"&showtrue=1&reviewsituation="+reviewsituation+"&fillsituation="+fillsituation;
	});
	$('#nextpage').click(function(){
 		var total = $('#blue').html();
		var totalcount = Math.ceil(total/rownumber);
 		if((pagenow+1)<=totalcount){
 		pagenow +=1;
 		getexamtable(userid,college,pagenow,rownumber);
 		}
 	});
 	$('#prepage').click(function(){
 		if((pagenow-1) != 0){
 			pagenow -=1;
 			getexamtable(userid,college,pagenow,rownumber);
 		}
 	});
 	$('#lastpage').click(function(){
 		var total = $('#blue').html();
		var totalcount = Math.ceil(total/rownumber);
		pagenow = totalcount;
		getexamtable(userid,college,pagenow,rownumber);
 	});
 	$('#firstpage').click(function(){
 		pagenow = 1;
 		getexamtable(userid,college,pagenow,rownumber);
 	});
});


</script>
</head>
<body>


	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath %>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">用户管理></a></li>
			<li><a href="#">管理用户</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>表格id<i class="sort"><img
							src="<%=basePath %>images/px.gif" /></i></th>
					<th>报表名称</th>
					<th>填报人</th>
					<th>填报状态</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="usertable">


				<!-- <tr id="">
					<td><input name="" type="checkbox" value="" id="  " /></td>
					<td><input class="newsuneditable" id="userid" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="username" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="password" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="islive" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="islive" value=""
						readonly="readonly"></input></td>
					<td class="newseditable"><a href="#" class="tablelink edit"
						id="edit">通过</a> <a href="#" class="tablelink delete" id="delete">不通过</a>
						<a href="#" class="tablelink delete" id="delete">取消</a></td>
				</tr> -->

			</tbody>
		</table>


		<div class="pagin">
					<div class="message">
						共<i class="blue" id='blue'> </i>个纪录
					</div>
					<ul class="paginList">
						<li class="paginItem"><a id='firstpage' href="#">首页</a></li>
						<li class="paginItem"><a id='prepage' href="#">上页</a></li>
						<li class="paginItem"><a id='nextpage' href="#">下页</a></li>
						<li class="paginItem"><a id='lastpage' href="#">末页</a></li>
					</ul>
		</div>
		


		<div class="tip" style="display: none;">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="<%=basePath %>images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>

		<div class="tip" style="display: none;" id="ifDelete">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>
			<div class="tipinfo">
				<span><img src="<%=basePath %>images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认删除该菜单项 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定"
					id="confirmDelete" />&nbsp; <input name="" type="button"
					class="cancel" value="取消" id="cancelDelete" />
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
			账 号 <input id="alterid" type="text" name="userid" value=""
				readonly="readonly"></br>
			</br> 新密码 <input id="alterpassword" name="password" type="text" value=""></br>
			</br> <input id="altersubmit" type="submit" value="确定"> <input
				id="altercancel" type="button" value="取消">
		</form>
	</div>
	<div id="changelive">
		<form action="changelive">
			<input id="changeid" type="hidden" name="userid" value=""
				readonly="readonly"></br>
			</br> <input id="changeliveid" type="hidden" name="liveid" value=""
				readonly="readonly"></br>
			</br> <input id="changesubmit" type="submit" value="确定"> <input
				id="changecancel" type="button" value="取消">
		</form>
	</div>
	<a href="<%=basePath%>exportDetailServlet?userid=<%=userid%>">导出明细</a>
</body>
</html>