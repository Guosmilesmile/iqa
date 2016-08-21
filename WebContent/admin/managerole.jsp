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
<link href="<%=basePath %>css/managerole.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
<script type="text/javascript">
function getallrole(pagenow,rownumber){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getallrole',
		data:{},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#usertable');
			var str ="";
			if(List.length > 0){
				List = pagebean(List,'blue',pagenow,rownumber);
				for(var i=0;i<List.length;i++){
					var roleid = List[i].r_roleid;
					var rolename = List[i].r_rolename;
					var islive = List[i].r_islive;
					str+="<tr id=''>"+
			 		"<td><input name='' type='checkbox' value='' id=''/></td>"+
			        "<td><input class='newsuneditable' id='roleid' value='"+roleid+"' readonly='readonly'></input></td>"+ 
			        "<td><input class='newsuneditable' id='rolename' value='"+rolename+"' readonly='readonly'></input></td>";
			        if(islive ==1)
			        str+="<td><input class='newsuneditable' id='rolename' value='"+"是"+"' readonly='readonly'></input></td>";
			        else
			         str+="<td><input class='newsuneditable' id='rolename' value='"+"否"+"' readonly='readonly'></input></td>";
			        str+="<td class='newseditable'>"+
						"<a href='#' class='tablelink open' id='delete"+roleid+"'> 启用</a>"+
						"<a href='#' class='tablelink close' id='delete"+roleid+"'> 不启用</a>"+
						"<a href='#' class='tablelink delete' id='delete"+roleid+"'> 删除</a>"+
						"</td></tr>"; 
				}
			}
			tbody.html(str);
		},error:function(){
			alert("fail");
		}
	});
};
function changelive(roleid,flag){
	$.ajax({
		type:'post',
		url:'<%=basePath %>changgerolelive',
		data:{roleid:roleid,flag:flag},
		success:function(){
			location.reload();	
		},error:function(){
			alert("fail");
		}
	});
};



$(document).ready(function(){
		var pagenow = 1;
		var rownumber = 10;
		getallrole(pagenow,rownumber);	
	 	$('#addpower').click(function(){
	 		$('#ifAdd').show();
	 	});
	 	$('#cancelbtn').click(function(){
	 		$('#ifAdd').hide();
	 	});
	 	$('.tablelink.close').live('click',function(){
	 		var deleteid = $(this).attr("id");
	 		var roleid = deleteid.substring(6,deleteid.length);
	 		changelive(roleid,0);
	 	});
	 	$('.tablelink.open').live('click',function(){
	 		var deleteid = $(this).attr("id");
	 		var roleid = deleteid.substring(6,deleteid.length);
	 		changelive(roleid,1);
	 	})
	 	$('.tablelink.delete').live('click',function(){
			$('.tip').show();
			var deleteid = $(this).attr("id");
			var roleid = deleteid.substring(6,deleteid.length);
			$('#deleroleid').val(roleid);
	 	});
	 	$('#cancelDelete').click(function(){
	 		$('.tip').hide();
	 	});
	 	$('#nextpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
	 		if((pagenow+1)<=totalcount){
	 		pagenow +=1;
	 		getallrole(pagenow,rownumber);
	 		}
	 	});
	 	$('#prepage').click(function(){
	 		if((pagenow-1) != 0){
	 			pagenow -=1;
		 		getallrole(pagenow,rownumber);
	 		}
	 	});
	 	$('#lastpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
			pagenow = totalcount;
	 		getallrole(pagenow,rownumber);
	 	});
	 	$('#firstpage').click(function(){
	 		pagenow = 1;
	 		getallrole(pagenow,rownumber);
	 	});
	 	$('#addpower').click(function(){
	 		$('#ifAdd').show();
	 	});
	 	$('#cancel').click(function(){
	 		$('#ifAdd').hide();
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
    <input type="button" value="增加角色" id="addpower">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>角色ID<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>角色名</th>
        <th>是否启用</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="usertable">
        
        
 		<tr id="">
 		<td><input name="" type="checkbox" value="" id="  "/></td>
        <td><input class="newsuneditable" id="roleid" value="" readonly="readonly"></input></td> 
        <td><input class="newsuneditable" id="rolename" value="" readonly="readonly"></input></td>
        <td><input class="newsuneditable" id="rolename" value="" readonly="readonly"></input></td>
        <td class="newseditable">
			<a href="#" class="tablelink delete" id="delete"></a>
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
        <form action="<%=basePath %>deleterole">
        <input type="hidden" id="deleroleid" name="roleid">
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
		<form action="addrole" method="post">
		角色名:<input type="text" name="rolenames"><br/><br/>
		<input type="submit" value="确认">
		<input type="button" id='cancel' value="取消">
</form>
	</div>
</body>
</html>