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
<title>编辑栏目</title>
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
		$("#number"+id).removeClass().addClass('editable');
		$("#number"+id).removeAttr('readOnly');
		$("#ch_name"+id).removeClass().addClass('editable');
		$("#ch_name"+id).removeAttr('readOnly');
		$("#en_name"+id).removeClass().addClass('editable');
		$("#en_name"+id).removeAttr('readOnly');
	});
	$(".confirmAlter").click(function(){
		var id = $(this).attr("id").toString().substring(12);
		var ch_name = $("#ch_name"+id).attr("value");
		var en_name = $("#en_name"+id).attr("value")
		$("#number"+id).removeClass().addClass('uneditable');
		$("#number"+id).attr("readOnly",true);
		$("#ch_name"+id).removeClass().addClass('uneditable');
		$("#ch_name"+id).attr("readOnly",true);
		$("#en_name"+id).removeClass().addClass('uneditable');
		$("#en_name"+id).attr("readOnly",true);
		
		/*修改菜单*/
		 $.ajax({
	            type: 'get',
	            url: "<%=basePath %>alterMenu",
	            data: {
	            	menuId: id,
	            	ch_name: ch_name,
	            	en_name: en_name,
	            	type:"main",
	            },
	            success: function(msg){
	            	if(msg == "ok"){
	            		alert("ok");
	            		/*应该提示修改成功*/
	            	}
	        		
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
	                alert(XMLHttpRequest.status);
	                alert(XMLHttpRequest.readyState);
	                alert(textStatus);
	            },
	 		});
		
	});
	/*修改子栏目*/
	$(".subEdit").click(function(){
		var editId = $(this).attr("id").toString().substring(0,1);
		var mainId = $(this).attr("id").toString().substring(5);
		$("#"+editId+"ch_name"+mainId).removeClass().addClass('editable');
		$("#"+editId+"ch_name"+mainId).removeAttr('readOnly');
		$("#"+editId+"en_name"+mainId).removeClass().addClass('editable');
		$("#"+editId+"en_name"+mainId).removeAttr('readOnly');
	});
	$(".subConfirmAlter").click(function(){
		var editId = $(this).attr("id").toString().substring(0,1);
		var mainId = $(this).attr("id").toString().substring(13);
		
		var ch_name = $("#"+editId+"ch_name"+mainId).attr("value");
		var en_name = $("#"+editId+"en_name"+mainId).attr("value")
		
		$("#"+editId+"ch_name"+mainId).removeClass().addClass('uneditable');
		$("#"+editId+"ch_name"+mainId).attr("readOnly",true);
		$("#"+editId+"en_name"+mainId).removeClass().addClass('uneditable');
		$("#"+editId+"en_name"+mainId).attr("readOnly",true);
		
		/*修改菜单*/
		 $.ajax({
	            type: 'get',
	            url: "<%=basePath %>alterMenu",
	            data: {
	            	menuId: editId,
	            	ch_name: ch_name,
	            	en_name: en_name,
	            	type:"sub",
	            },
	            success: function(msg){
	            	if(msg == "ok"){
	            		alert("ok");
	            		/*应该提示修改成功*/
	            	}
	        		
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
	                alert(XMLHttpRequest.status);
	                alert(XMLHttpRequest.readyState);
	                alert(textStatus);
	            },
	 		});
		
	});
	
	
	/*子栏目显示与隐藏的控制*/
	$(".hideOrShow").click(function(){
		var id = $(this).attr("id").toString().substring(7);
		var hideOrShow = $(".subMenuContent"+id).attr("name");
		if(hideOrShow == "hide"){
			$(".subMenuContent"+id).show();
			$(".subMenuContent"+id).attr("name","show");
		}
		if(hideOrShow == "show"){
			$(".subMenuContent"+id).hide();
			$(".subMenuContent"+id).attr("name","hide");
		}
		
		
	});
	/*子栏目删除*/
	$(".subDelete").click(function(){
		var subId = $(this).attr("id").toString().substring(0,1);
		var mainId = $(this).attr("id").toString().substring(7);
		alert("mainId" + mainId);
		
		$("#ifDelete").show();
		$("#confirmDelete").attr("deleteId",subId);
		$("#confirmDelete").attr("mainId",mainId);
		$("#confirmDelete").attr("delType","sub");//删除子栏目
	});
	/*主栏目删除*/
	$(".delete").click(function(){
		var id = $(this).attr("id").toString().substring(6);
		$("#ifDelete").show();
		$("#confirmDelete").attr("deleteId",id);
		$("#confirmDelete").attr("delType","main");//删除主栏目
	});
	
	//确认删除
	$("#confirmDelete").click(function(){
		/*删除菜单*/
		var type = $(this).attr("delType");//子栏目 or主栏目
		var id = $(this).attr("deleteId");
		var mainId = $(this).attr("mainId");
		 $.ajax({
	            type: 'get',
	            url: "<%=basePath %>deleteMenu",
	            data: {
	            	menuId:id,
	            	type:type,
	            },
	            success: function(msg){
	            	if(msg == "ok"){
	            		if(type == "main"){
	            			$("#menuContent"+id).remove();
	            			/*主栏目下的全部子栏目一并删除*/
	            			$(".subMenuContent"+mainId).remove();
	            		}
	            		if(type == "sub"){
	            			$("#"+id+"subMenuContent"+mainId).remove();
	            		}
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
    <li><a href="#">栏目管理></a></li>
    <li><a href="#">编辑栏目</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
<!--     <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div> -->
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <!-- <th><input name="" type="checkbox" value="" checked="checked"/></th> -->
        <th>编号<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>中文名称</th>
        <th>英文名称</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        <%
			List<Menu> menuList = (List<Menu>)request.getAttribute("menuList");
			if(!menuList.isEmpty() && menuList.size() > 0){
			int i = 0;
 			for(Menu menu : menuList){
 				i++;
 				int id = menu.getMb_id();
 				String ch_name = menu.getMb_ch_name();
 				String en_name = menu.getMb_en_name();
 				List<SubMenu> subMenus = menu.getSubMenus();
 		%>
 		<tr id="menuContent<%=menu.getMb_id() %>" class="menuContent">
 		<%-- <td><input name="" type="checkbox" value="" id="checkBox<%=menu.getMb_id() %>"/></td> --%>
        <td class="hideOrShow" style="text-align:center;" id="1234567"><%=menu.getMb_id() %></td> 
        <td><input class="uneditable hideOrShow" id="ch_name<%=menu.getMb_id() %>" value="<%=menu.getMb_ch_name() %>" readonly="readonly"></input></td>
        <td><input class="uneditable hideOrShow" id="en_name<%=menu.getMb_id() %>" value="<%=menu.getMb_en_name() %>" readonly="readonly"></input></td>
        <td>
        	<a href="#" class="tablelink edit" id="edit<%=menu.getMb_id() %>">修改</a>
			<a href="#" class="tablelink delete" id="delete<%=menu.getMb_id() %>"> 删除</a>
			<a href="#" class="tablelink confirmAlter" id="confirmAlter<%=menu.getMb_id() %>">   确认</a>
			</td>
 		</tr>
 		<%
 			for(SubMenu subMenu : subMenus){
 			%>
 				<tr id="<%=subMenu.getSmb_id() %>subMenuContent<%=menu.getMb_id() %>" style="display: none;" class="subMenuContent<%=menu.getMb_id() %>" name="hide">
 				<td></td>
 				<!-- <td></td> -->
        		<%-- <td><input class="uneditable" style="text-align:center;" id="<%=subMenu.getSmb_id() %>number<%=menu.getMb_id() %>" value="<%=subMenu.getSmb_id() %>" readonly="readonly"></input></td> --%> 
       			<td><input class="uneditable" style="text-align:center;" id="<%=subMenu.getSmb_id() %>ch_name<%=menu.getMb_id() %>" value="<%=subMenu.getSmb_ch_name() %>" readonly="readonly"></input></td>
      		 	<td><input class="uneditable" style="text-align:center;" id="<%=subMenu.getSmb_id() %>en_name<%=menu.getMb_id() %>" value="<%=subMenu.getSmb_en_name() %>" readonly="readonly"></input></td>
        		<td>
        			<a href="#" class="tablelink subEdit" id="<%=subMenu.getSmb_id() %>edit<%=menu.getMb_id() %>">修改</a>
					<a href="#" class="tablelink subDelete" id="<%=subMenu.getSmb_id() %>delete<%=menu.getMb_id() %>"> 删除</a>
					<a href="#" class="tablelink subConfirmAlter" id="<%=subMenu.getSmb_id() %>confirmAlter<%=menu.getMb_id() %>">   确认</a>
				</td>
 				</tr>
 			<%
 			}
 		%> 
 		<%
 			}}
        %>
        
        
        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue"> <%=menuList.size() %> </i>个主菜单</div>
       <!--  <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul> -->
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
    </div>

    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>