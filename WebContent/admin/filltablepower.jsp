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
function getRoleFill(){
	$.ajax({
		type:'post',
		url:'<%=basePath %>getrolefill',
		data:{},
		success:function(ListJsonStr){
			var List = eval('(' + ListJsonStr + ')');
			var tbody = $('#usertable');
			var str ="";
			if(List.length > 0){
				for(var i=0;i<List.length;i++){
					var id = List[i].rr_id;
					var roleid = List[i].rr_roleid;
					var rolename = List[i].rr_rolename;
					var tableid=List[i].rr_tableid;
					var tablename = List[i].rr_tablename;
					str+="<tr id=''>"+
			 		"<td><input name='' type='checkbox' value='' id=''/></td>"+
			        "<td><input class='newsuneditable' id='roleid' value='"+roleid+"' readonly='readonly'></input></td>"+ 
			        "<td><input class='newsuneditable' id='rolename' value='"+rolename+"' readonly='readonly'></input></td>"+
			        "<td><input class='newsuneditable' id='tableid' value='"+tableid+"' readonly='readonly'></input></td>"+
			        "<td><input class='newsuneditable' id='tablename' value='"+tablename+"' readonly='readonly'></input></td>"+
			        "<td class='newseditable'>"+
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

var setting = {    
        check:{
            enable:true
        },
        /*data: {
            simpleData: {
                enable: true
            }
        }*/
        data:    {
            simpleData:{
                enable:true
            }
        },
       /*  callback:{
            onCheck:onCheck
        } */
        
    };
                
    var zNodes =[
       
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2",  open:false},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1"},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-13"}
    ];
    
        
   function getChildNodes() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		 var checkCount = zTree.getCheckedNodes(true);
		 var classpurview = "";
		 alert(checkCount.length);
           for(var i=0;i<checkCount.length;i++) {
                classpurview = "," + checkCount[i].pId;
           }
         alert(classpurview);
	}   




$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		gettablelist();
		getRoleFill();
		getallrole();
	 	$('#addroletable').click(function(){
	 		$('#ifAdd').show();
	 		 location.href = "demo.jsp";
	 	});
	 	$('#cancelbtn').click(function(){
	 		$('#ifAdd').hide();
	 	});
	 	$('.tablelink.delete').live('click',function(){
			$('.tip').show();
			var deleteid = $(this).attr("id");
			var fillpowerid = deleteid.substring(6,deleteid.length);
			$('#deleroleid').val(fillpowerid);
	 	});
	 	$('#cancelDelete').click(function(){
	 		$('.tip').hide();
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
    <input type="button" value="增加角色填报权限" id="addroletable">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>角色ID<i class="sort"><img src="<%=basePath %>images/px.gif" /></i></th>
        <th>角色名</th>
        <th>报表id</th>
        <th>报表名</th>
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
    	<div class="message">共<i class="blue">  </i>个纪录</div>
         <ul class="paginList">
        <li class="paginItem"><a href=getallnews.jsp?currentPage=1 target='_self' >首页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self'>上页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self'>下页</a></li>
        <li class="paginItem"><a href=getallnews.jsp?currentPage= target='_self' >末页</a></li>
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
        <form action="<%=basePath %>deleterolefillpower">
        <input type="hidden" id="deleroleid" name="fillpowerid">
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
	<ul id="treeDemo" class="ztree"></ul>
</body>
</html>