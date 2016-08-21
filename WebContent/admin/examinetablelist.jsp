<%@ page language="java"
	import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<title>表格审核</title>
<link href="<%=basePath %>css/manageuser.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
<style type="text/css">
#watchalldetail {
	float: right;
	width: 60px;
	height: 30px;
}

</style>
<script type="text/javascript">
function getexamtable(userid,pagenow,rownumber){
	$.ajax({
		type:'post',
		url:'<%=basePath%>getuserexam',
		data:{userid:userid},
		success:function(ListJsonList){
			
			var List = eval('(' + ListJsonList + ')');
			var str ="";
			var tbody = $('#examtable');
			if(List.length > 0){
				List = pagebean(List,'blue',pagenow,rownumber);
				var i=0;
				while(i<List.length){
					var tablename = List[i].tablename;
					var rolename = List[i].rolename;
					var tableid = List[i].tableid;
					var fillsituation = List[i].rf_fillsituation;
					var reviewsituation = List[i].rf_reviewsituation;
					var roleid = List[i].roleid;
					str+="<tr id=''>"+
					"<td><input name='' type='checkbox' value='' id='' /></td>"+
					"<td><input class='newsuneditable' id='tableid' value='"+tableid+"'"+
					"readonly='readonly'></input></td>"+
					"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
					"readonly='readonly'></input></td>";
					
					var finish=0; 
					var all=1;				
					if(!fillsituation==0)
					     finish++;					
					var j=i+1; 					
					while(j<List.length && List[j].tableid==tableid ){						
						all++;
						if(!List[j].rf_fillsituation==0)
						     finish++;						
						j++;
					}
					
					str+="<td><input class='newsuneditable' id='tablename' value='"+finish+"/"+all+"' "
					 +"readonly='readonly'></input></td>";
					
					str+="<td class='newseditable'><a href='#' class='tablelink watchdetail'"+
					"id='"+tableid+"'>查看明细     </a></td></tr>";
					
					i=j;
					//i++;
				}
			}
			tbody.html(str); 
		},error:function(){
			alert('fail');
		}
	});
};
function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
};

$(document).ready(function(){
	var pagenow = 1;
	var rownumber = 10;
	var userid  = getCookie('userid');
	getexamtable(userid,pagenow,rownumber);
	$('.tablelink.watchdetail').live('click',function(){	    
		var tableid= $(this).attr('id');
		//alert(tableid);
		location.href="examinetable.jsp?tableid="+tableid;
	});
	$('#watchalldetail').live('click',function(){
		location.href="examinetable.jsp?tableid=";		
    });
	
	$('#nextpage').click(function(){
 		var total = $('#blue').html();
		var totalcount = Math.ceil(total/rownumber);
 		if((pagenow+1)<=totalcount){
 		pagenow +=1;
 		getexamtable(userid,pagenow,rownumber);
 		}
 	});
 	$('#prepage').click(function(){
 		if((pagenow-1) != 0){
 			pagenow -=1;
 			getexamtable(userid,pagenow,rownumber);
 		}
 	});
 	$('#lastpage').click(function(){
 		var total = $('#blue').html();
		var totalcount = Math.ceil(total/rownumber);
		pagenow = totalcount;
		getexamtable(userid,pagenow,rownumber);
 	});
 	$('#firstpage').click(function(){
 		pagenow = 1;
 		getexamtable(userid,pagenow,rownumber);
 	});
});


</script>
</head>
<body>


	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath %>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">表格操作></a></li>
			<li><a href="#">表格审核</a></li>
		</ul>
		
		<input type="button" value="查看总明细" id="watchalldetail">
	</div>

	<div class="rightinfo">

		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>表格id<i class="sort"><img
							src="<%=basePath %>images/px.gif" /></i></th>
					<th>报表名称</th>
					<th>填报进度（完成/总量）</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="examtable">
			</tbody>
		</table>

		<div class="pagin">
					<!-- <div class="message">
						共<i class="blue" id='blue'> </i>个纪录
					</div> -->
					<ul class="paginList">
						<li class="paginItem"><a id='firstpage' href="#">首页</a></li>
						<li class="paginItem"><a id='prepage' href="#">上页</a></li>
						<li class="paginItem"><a id='nextpage' href="#">下页</a></li>
						<li class="paginItem"><a id='lastpage' href="#">末页</a></li>
					</ul>
		</div>
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