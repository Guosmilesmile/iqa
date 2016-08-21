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
<link href="<%=basePath %>css/filltablepower.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
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
function gettablelist(){
	$.ajax({
		type:'post',
		url:'<%=basePath %>gettablelist',
		data:{},
		success:function(ListJsonStr){
			var listbody = $('#select1');
			var List = eval('(' + ListJsonStr + ')');
			var str="";
			if(List.length>0){
				for(var i=0;i<List.length;i++){
					var id=List[i].t_tableid;
					var tablename = List[i].t_tablename;
					str+="<option value="+id+">"+tablename+"</option>";
				}
			}
			listbody.html(str);
		},error:function(){alert("fail!");}
		});
};
function getRoleFill(pagenow,rownumber){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getrolefill',
		data:{},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#usertable');
			var str ="";
			List = pagebean(List,'blue',pagenow,rownumber);
			if(List.length > 0){
				for(var i=0;i<List.length;i++){
					var id = List[i].rp_id;
					var roleid = List[i].r_roleid;
					var rolename = List[i].r_rolename;
					var tableid=List[i].t_tableid;
					var tablename = List[i].t_tablename;
					var powerid = List[i].rp_powerid;
					str+="<tr id=''>"+
			 		"<td><input name='' type='checkbox' value='' id=''/></td>"+
			        "<td><input class='newsuneditable' id='roleid' value='"+roleid+"' readonly='readonly'></input></td>"+ 
			        "<td><input class='newsuneditable' id='rolename' value='"+rolename+"' readonly='readonly'></input></td>"+
			        "<td><input class='newsuneditable' id='tableid' value='"+tableid+"' readonly='readonly'></input></td>"+
			        "<td><input class='newsuneditable' id='tablename' value='"+tablename+"' readonly='readonly'></input></td>";
			        if(powerid==1)
			        	str+="<td><input class='newsuneditable' id='tablename' value='查看' readonly='readonly'></input></td>";
			        if(powerid==2)
				        str+="<td><input class='newsuneditable' id='tablename' value='填报' readonly='readonly'></input></td>";
				    if(powerid==3)
					    str+="<td><input class='newsuneditable' id='tablename' value='一级审核' readonly='readonly'></input></td>";
					if(powerid==4)
						    str+="<td><input class='newsuneditable' id='tablename' value='二级审核' readonly='readonly'></input></td>";
			        str+="<td class='newseditable'>"+
						"<a href='#' class='tablelink delete' id='delete"+id+"'> 删除</a>"+
						"</td></tr>"; 
				}
			}
			tbody.html(str);
		},error:function(){
			alert("fail");
		}
	});
};


$(document).ready(function(){
		var pagenow = 1;
		var rownumber = 12;
		gettablelist();
		getRoleFill(pagenow,rownumber);
		getallrole();
	 	$('#addroletable').click(function(){
	 		/* $('#ifAdd').show(); */
	 		location.href = "<%=basePath%>admin/addroletablepower.jsp"; 
	 	});
	 	$('#cancelbtn').click(function(){
	 		$('#ifAdd').hide();
	 	});
	 	$('.tablelink.delete').live('click',function(){
			$('.tip').show();
			var deleteid = $(this).attr("id");
			var rp_id = deleteid.substring(6,deleteid.length);
			$('#deleroleid').val(rp_id);
	 	});
	 	$('#cancelDelete').click(function(){
	 		$('.tip').hide();
	 	});
	 	$('#nextpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
	 		if((pagenow+1)<=totalcount){
	 		pagenow +=1;
	 		getRoleFill(pagenow,rownumber);
	 		}
	 	});
	 	$('#prepage').click(function(){
	 		if((pagenow-1) != 0){
	 			pagenow -=1;
	 			getRoleFill(pagenow,rownumber);
	 		}
	 	});
	 	$('#lastpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
			pagenow = totalcount;
			getRoleFill(pagenow,rownumber);
	 	});
	 	$('#firstpage').click(function(){
	 		pagenow = 1;
	 		getRoleFill(pagenow,rownumber);
	 	});
});
</script>
</head>
<body>
	

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=basePath %>admin/myInfo.jsp" >首页></a></li>
    <li><a href="#">表格权限></a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <input type="button" value="增加角色填报权限" id="addroletable">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>角色ID<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>角色名</th>
        <th>报表id</th>
        <th>报表名</th>
        <th>权限</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="usertable">
        
        
 		<tr id="">
 		<td><input name="" type="checkbox" value="" id="  "/></td>
        <td><input class="newsuneditable" id="roleid" value="xxxx" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="rolename" value="xxx" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="tableid" value="xxxx" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="tablename" value="xxxx" readonly="readonly"></input></td>
        <td class="newseditable">
			<a href="#" class="tablelink delete" id="delete"> 删除</a>
			</td>
 		</tr> 
 		
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue" id='blue'>  </i>个纪录</div>
         <ul class="paginList">
        <li class="paginItem"><a id='firstpage' href="#" >首页</a></li>
        <li class="paginItem"><a id='prepage' href="#">上页</a></li>
        <li class="paginItem"><a id='nextpage' href="#">下页</a></li>
        <li class="paginItem"><a id='lastpage' href="#" >末页</a></li>
        </ul> 
    </div>
    
    <%-- <div class="tip" style="display: none;">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="<%=basePath %>images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <form action="">
        <input type="hidden" id="deleroleid">
        <input type="hidden" id="delepowerid">
        <input name="" type="submit"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </form>
        </div>
    
    </div> --%>
    
    <div class="tip" style="display: none;" id="ifDelete">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
      <div class="tipinfo">
        <span><img src="<%=basePath %>images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认删除该项 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
       <div class="tipbtn">
        <form action="<%=basePath %>deleteroletablepower">
        <input type="hidden" id="deleroleid" name="rpid">
        <input name="" type="submit"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" id="cancelDelete" value="取消" />
        </form>
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
	<div id="ifAdd">
		<form action="<%=basePath%>addrolefill">
			角色名:<!-- <input type="text" name="roleid" id="addrolename"> -->
			<select name="roleid" id="addrolename">
			
			</select>
			</br></br>
			表格名:<select id="select1" name="select1">
				<option value="1">xxx</option>
			</select></br></br>
			<input type="submit" value="确定" id="subbtn">
			<input type="button" value="取消" id="cancelbtn">
		</form>
		
		<!-- <ul id="treeDemo" class="ztree"></ul> -->
	</div>
</body>
</html>