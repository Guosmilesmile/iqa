
<%@ page language="java"
	import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宣传列表管理</title>
<link href="<%=basePath %>css/managerole.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
#commit {
	float: right;
	width: 62px;
	height: 30px;
}
</style>
<script type="text/javascript"
	src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
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
        
    
       function gettablelistlike(like,pagenow,rownumber){
    		$.ajax({
    			type:'post',
    			url:'<%=basePath %>gettablelistlike',
    			data:{like:like},
    			success:function(ListJsonStr){
    				var List = eval('(' + ListJsonStr + ')');
    				var tbody = $('#usertable');
    				var str ="";
    				if(List.length>0){
    					List = pagebean(List,'blue',pagenow,rownumber);
    					for(var i=0;i<List.length;i++){
    						var tableid=List[i].t_tableid;
    						var tablename=List[i].t_tablename;
    						str+=	"<tr id=''>"+
    				        "<td><input class='newsuneditable' id='tableid' value='"+tableid+"' readonly='readonly'></input></td>"+ 
    				        "<td><input class='newsuneditable' id='tablename' value='"+tablename+"' readonly='readonly'></input></td>"+
    				        "<td class='newseditable'>"+
    						"<input name='' class='powerwatch' id='watch_"+tableid+"'  type='checkbox'/></td>"+
    						"<td class='newseditable'>"+
    						"<input name='' class='powerfill' id='fill_"+tableid+"' type='checkbox'  id=''/></td>"+
    						"<td class='newseditable'>"+
    						"<input name='' class='powerexamone' id='examone_"+tableid+"' type='checkbox'  id=''/></td>"+
    						"<td class='newseditable'>"+
    						"<input name='' class='powerexamtwo' id='examtwo_"+tableid+"' type='checkbox'  id=''/></td>"+
    						"</tr>"; 
    					}
    				}
    				tbody.html(str);
    			},error:function(){alert("fail!");}
    			});
    	};
     
    	function Commit(){
    		var roleid=$('#addrolename').val();
    		var watchstr="";
	 		var fillstr="";
	 		var examonestr="";
	 		var examtwostr="";
	 		$('.powerwatch').each(function(){
	 			if($(this).attr("checked")){
	 				watchstr+=$(this).attr("id").substring(6)+",";
	 			}
	 		});
	 		$('.powerfill').each(function(){
	 			if($(this).attr("checked")){
	 				fillstr+=$(this).attr("id").substring(5)+",";
	 			}
	 		});
	 		$('.powerexamone').each(function(){
	 			if($(this).attr("checked")){
	 				examonestr+=$(this).attr("id").substring(8)+",";
	 			}
	 		});
	 		$('.powerexamtwo').each(function(){
	 			if($(this).attr("checked")){
	 				examtwostr+=$(this).attr("id").substring(8)+",";
	 			}
	 		});
	 		$.ajax({
	 			type:'post',
    			url:'<%=basePath %>addroletablepowers',
    			data:{roleid:roleid,watch:watchstr,fill:fillstr,examone:examonestr,examtwo:examtwostr},
    			success:function(){
    				location.href ="<%=basePath%>admin/tablepower.jsp";
    			},error:function(Message){
    				alert("fail!");
    			}
	 		});
	 		
	 		
	 		
	 		
	 		
    	}
    	
    	
    	
      
        $(document).ready(function(){
        	var pagenow = 1;
    		var rownumber = 15;
    		var like="";
        	getallrole();
        	gettablelistlike(like,pagenow,rownumber);
        	$('#nextpage').click(function(){
    	 		var total = $('#blue').html();
    			var totalcount = Math.ceil(total/rownumber);
    	 		if((pagenow+1)<=totalcount){
    	 		pagenow +=1;
    	 		gettablelistlike(like,pagenow,rownumber);
    	 		}
    	 	});
    	 	$('#prepage').click(function(){
    	 		if((pagenow-1) != 0){
    	 			pagenow -=1;
    	 			gettablelistlike(like,pagenow,rownumber);
    	 		}
    	 	});
    	 	$('#lastpage').click(function(){
    	 		var total = $('#blue').html();
    			var totalcount = Math.ceil(total/rownumber);
    			pagenow = totalcount;
    			gettablelistlike(like,pagenow,rownumber);
    	 	});
    	 	$('#firstpage').click(function(){
    	 		pagenow = 1;
    	 		gettablelistlike(like,pagenow,rownumber);
    	 	});
    	 	$('#watchall').click(function(){
    	 		$('.powerwatch').each(function(){
    	 			if($(this).attr("checked")){
    	 				$(this).attr("checked","");
    	 			}else{
    	 				$(this).attr("checked","checked");
    	 			}
    	 		});
    	 	});
    	 	$('#fillall').click(function(){
    	 		$('.powerfill').each(function(){
    	 			if($(this).attr("checked")){
    	 				$(this).attr("checked","");
    	 			}else{
    	 				$(this).attr("checked","checked");
    	 			};
    	 			$('.powerfill').each(function(){
	    	 			var str = $(this).attr("id").substring(5);
	        	 		var one = "#examone_"+str;
	        	 		var two = "#examtwo_"+str;
	        	 		var three = "#watch_"+str;
	        	 		if($(this).attr("checked")){
	        	 			$(one).attr("disabled","true");
	        	 			$(two).attr("disabled","true");
	        	 			$(three).attr("disabled","true");
	        	 			$(one).attr("checked","");
	        	 			$(two).attr("checked","");
	        	 			$(three).attr("checked","");
	        	 		}else{
	        	 			$(one).attr("disabled","");
	        	 			$(two).attr("disabled","");
	        	 			$(three).attr("disabled","");
	        	 		}
    	 			});
    	 			
    	 			
    	 			
    	 		});
    	 	});
    	 	$('#examallone').click(function(){
    	 		$('.powerexamone').each(function(){
    	 			if($(this).attr("checked")){
    	 				$(this).attr("checked","");
    	 			}else{
    	 				$(this).attr("checked","checked");
    	 			}
    	 		});
    	 		$('.powerexamone').each(function(){
    	 			var str = $(this).attr("id").substring(8);
        	 		var one = "#fill_"+str;
        	 		var two = "#examtwo_"+str;
        	 		var three = "#watch_"+str;
        	 		if($(this).attr("checked")){
        	 			$(one).attr("disabled","true");
        	 			$(two).attr("disabled","true");
        	 			$(three).attr("disabled","true");
        	 			$(one).attr("checked","");
        	 			$(two).attr("checked","");
        	 			$(three).attr("checked","");
        	 		}else{
        	 			$(one).attr("disabled","");
        	 			$(two).attr("disabled","");
        	 			$(three).attr("disabled","");
        	 		}    	 		
        	 	});
    	 	});
    	 	$('#examalltwo').click(function(){
    	 		$('.powerexamtwo').each(function(){
    	 			if($(this).attr("checked")){
    	 				$(this).attr("checked","");
    	 			}else{
    	 				$(this).attr("checked","checked");
    	 			}
    	 		});
    	 		$('.powerexamtwo').each(function(){
    	 			var str = $(this).attr("id").substring(8);
        	 		var one = "#fill_"+str;
        	 		var two = "#examone_"+str;
        	 		var three = "#watch_"+str;
        	 		if($(this).attr("checked")){
        	 			$(one).attr("disabled","true");
        	 			$(two).attr("disabled","true");
        	 			$(three).attr("disabled","true");
        	 			$(one).attr("checked","");
        	 			$(two).attr("checked","");
        	 			$(three).attr("checked","");
        	 		}else{
        	 			$(one).attr("disabled","");
        	 			$(two).attr("disabled","");
        	 			$(three).attr("disabled","");
        	 		}    	 		
    	 		});
    	 	});
    	 	$('#commit').click(function(){
    	 		Commit();
    	 	});
    	 	$('#search').click(function(){
    	 		like = $('#searchtext').val();
    	 		gettablelistlike(like,pagenow,rownumber);
    	 	});
    	 	$('.powerfill').live('click',function(){
    	 		var str = $(this).attr("id").substring(5);
    	 		var one = "#examone_"+str;
    	 		var two = "#examtwo_"+str;
    	 		var three = "#watch_"+str;
    	 		if($(this).attr("checked")){
    	 			$(one).attr("disabled","true");
    	 			$(two).attr("disabled","true");
    	 			$(three).attr("disabled","true");
    	 		}else{
    	 			$(one).attr("disabled","");
    	 			$(two).attr("disabled","");
    	 			$(three).attr("disabled","");
    	 		}
    	 	});
    	 	$('.powerexamone').live('click',function(){
    	 		var str = $(this).attr("id").substring(8);
    	 		var one = "#fill_"+str;
    	 		var two = "#watch_"+str;
    	 		var three = "#examtwo_"+str;
    	 		if($(this).attr("checked")){
    	 			$(one).attr("disabled","true");
    	 			$(two).attr("disabled","true");
    	 			$(three).attr("disabled","true");
    	 		}else{
    	 			$(one).attr("disabled","");
    	 			$(two).attr("disabled","");
    	 			$(three).attr("disabled","");
    	 		}
    	 	});
    	 	$('.powerexamtwo').live('click',function(){
    	 		var str = $(this).attr("id").substring(8);
    	 		var one = "#fill_"+str;
    	 		var two = "#watch_"+str;
    	 		var three = "#examone_"+str;
    	 		if($(this).attr("checked")){
    	 			$(one).attr("disabled","true");
    	 			$(two).attr("disabled","true");
    	 			$(three).attr("disabled","true");
    	 		}else{
    	 			$(one).attr("disabled","");
    	 			$(two).attr("disabled","");
    	 			$(three).attr("disabled","");
    	 		}
    	 	});
        });
    </SCRIPT>
</head>
<body>


	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath %>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">用户管理></a></li>
			<li><a href="#">管理表格权限</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		角色: <select name="roleid" id="addrolename"></select> 搜索: <input
			type="text" id="searchtext"><input type="button" id="search"
			value="搜索"> <input type="button" id="commit" value="提交">
		</br> </br>
		<table class="tablelist">
			<thead>
				<tr>
					<th>表格id<i class="sort"><img
							src="<%=basePath %>images/px.gif" /></i></th>
					<th>表名</th>
					<th>查看<input name="" type="checkbox" id="watchall" /></th>
					<th>填报<input name="" type="checkbox" id="fillall" /></th>
					<th>一级审核<input name="" type="checkbox" id="examallone" /></th>
					<th>二级审核<input name="" type="checkbox" id="examalltwo" /></th>
				</tr>
			</thead>
			<tbody id="usertable">


				<tr id="">
					<td><input name="" type="checkbox" value="" id="  " /></td>
					<td><input class="newsuneditable" id="roleid" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="rolename" value=""
						readonly="readonly"></input></td>
					<td><input class="newsuneditable" id="rolename" value=""
						readonly="readonly"></input></td>
					<td class="newseditable"><a href="#" class="tablelink delete"
						id="delete"></a></td>
				</tr>

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
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>
			<div class="tipinfo">
				<span><img src="<%=basePath %>images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认删除该项 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<form action="<%=basePath %>deleterole">
					<input type="hidden" id="deleroleid" name="roleid"> <input
						name="" type="submit" class="sure" value="确定" />&nbsp; <input
						name="" type="button" class="cancel" id="cancelDelete" value="取消" />
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
			角色名:<input type="text" name="rolenames"><br /> <br /> <input
				type="submit" value="确认"> <input type="button" id='cancel'
				value="取消">
		</form>
	</div>
</body>
</html>