<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[] cookies = request.getCookies();
String tableid = "";
String show = request.getAttribute("showtrue").toString();
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("tableid"))
		tableid = cookie.getValue();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>查看外聘教师基本信息表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">

<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/myeditor.js"></script>
<script>
var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
var tableid,userid;
var serialnumber =0,college;

$(function(){
	//获取数据的查询参数----过滤数据
	//------------------------------------要加获取tableid的方法=======================
	tableid = getCookie('tableid');
	userid = getCookie('userid');
	
	//获取数据的查询参数----过滤数据
	<%String filldeptxi = request.getAttribute("deptxi").toString();%>
	college = "<%=filldeptxi%>";
	var queryParams;
	
	if(college!=""&&college!=undefined)
	{
		college = decodeURI(college);
		queryParams = {"college":college};	
	}
	else{
		queryParams = {};
	} 
	getData(queryParams);
}); 

 function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
}; 

/*日期数据格式化函数*/
function formatterdate(val, row) {
	if(val != null && val != ""){
		var date = new Date(val);
        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	}
}

//时间戳转换成八位日期2014-5-5 
function userDate(uData){
  var myDate = new Date(uData);
  var year = myDate.getFullYear();
  var month = myDate.getMonth() + 1;
  var day = myDate.getDate();
  return year + '-' + month + '-' + day;
}


function getData(queryParams){
	$('#grid').datagrid({
		url: '<%=basePath%>Sec_GetGoInterConfer',
		sortName: 'gic_serialnumber',
		sortOrder: 'asc',
		queryParams: queryParams,
		nowrap: true, //换行属性
		striped: true, //奇数偶数行颜色区分
		collapsible : true, //可折叠
		pageSize: 5,//每页显示的记录条数，默认为10  
        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
		frozenColumns:[[
		    {field: 'ck', checkbox: true},          
		]],
		
		columns: [[
		    
			{field:'gic_id',title:'序号',sortable:true,width:80},
			{field:'gic_college1',title:'学院',sortable:true,width:120,
				//如果要实现编辑功能，需要添加下面的属性
			},
			{field:'gic_major',title:'专业',sortable:true,width:120,
			},
			{field:'gic_grade',title:'年级',sortable:true,width:100,
			},
			{field:'gic_stunum',title:'学号',sortable:true,width:120,
			},
			{field:'gic_name',title:'姓名',sortable:true,width:120,
			},
			{field:'gic_intername',title:'国际会议名称',sortable:true,width:120,
			},
			{field:'gic_paperortitle',title:'会议论文或大会发言题目',sortable:true,width:120,
				
			},
			{field:'gic_college',title:'填报学院',sortable:true,width:120,
				
			},
			{field:'gic_comments',title:'审核意见',sortable:true,width:120,
				editor: { type: 'validatebox',  }
			},
		]],
		
		toolbar:[
			{//全选---反选
				   text:"反选",
				   handler: _invertSelect,
			},'-',
		   {//--------------------------------通过
			   text: "通过",
			   iconCls: "icon-ok",
			   handler:_Pass, 
		   },'-',{//------------------------不通过
			   text: "不通过",
			   iconCls: "icon-cancel",
			   handler:_UnPass,
		   },'-',{//------------------------导出
			   text: "导出",
			   iconCls: "icon-redo",
			   handler:_Export, 
		   },'-',
		   
		],
		
		onAfterEdit: function(rowIndex,rowData,changes){
			doedit = undefined;
		},
		onDblClickRow:function(rowIndex, rowData){
			
			if(doedit==undefined)
			{					
				id = rowData.gic_id;
	        	$('#grid').datagrid('beginEdit',rowIndex);
	        	//这边的rowIndex和行号是不一样的值，0------开始的
	        	doedit=rowIndex;
			}
			
		},
		
		pagination: true,
		rownumbers:true,
	});
	
	if(<%=show %>==0 ){//单纯查看
		
		$('div.datagrid-toolbar a').eq(0).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
		$('div.datagrid-toolbar a').eq(2).hide();
		
		$("#grid").datagrid('removeEditor', 'gic_comments');  
		
		//隐藏最后一列
	}else{//审核查看
		//隐藏倒数第二列
	}
	
	//页面属性设置
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        BeforeRefresh:function(){
			$(this).datagrid('reload'); 
			//获取数据库全部数据
		},
		
	});
	
	
}
	//------------------------------------反选数据,审核时需要选择-----------------------------------
	function _invertSelect(){
	
	var rows = $('#grid').datagrid('getSelections');
	$('#grid').datagrid('selectAll');
	for(var i=0;i<rows.length;i++){
		var rowIndex = $('#grid').datagrid('getRowIndex', rows[i]);
		$('#grid').datagrid('unselectRow',rowIndex);
	}
	
	};
	
	
	function _Pass(){
		var rows = $('#grid').datagrid('getRows');
		var unpasscolleges = new Array();
		unpasscolleges[0]=rows[0].gic_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].gic_comments = '';
		}
		var rowstr = JSON.stringify(rows);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>Sec_UpdateGoInterConfer',
	        	type:'post',
	        	data: {rowdata:rowstr,patter:"batch"},
	        	dataType: 'json',
	        	success : function(r){
	        		if(r>1){
	        			$('#grid').datagrid('reload');
	        		}else{
	        			$('#grid').datagrid('beginEdit',doedit);
	        		}
	        		$('#grid').datagrid('unselectAll');
	        	}
	        	
	        });
		
		var flag="1";
		
		var tableid = getCookie('tableid');
		$.ajax({
			type:'post',
			url:'<%=basePath%>changeexamsituation',
			data:{id:tableid,flag:flag,userid:userid,unpasscolleges:unpasscollegesstr},
			success : function(r){
        		if(r==4){
        			location.href="<%=basePath%>admin/collecttable.jsp";
        		}else{
        			location.href="<%=basePath%>admin/examinetable.jsp";
        		}
			},error:function(){
				alert('fail');
			}
			
		});
	};
	function _UnPass(){
		var unpasscolleges = new Array(),j=0;//添加==========================
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length <= 0){
			$.messager.alert('提示','没有有误的记录被选中','info');
		}
		else{
			//--------------审核不通过的处理---------------------
			for(var i = 0; i < rows.length; i++)
			{
				//写入提示
				$('#grid').datagrid('acceptChanges');
				if(rows[i].gic_comments == "" ||rows[i].gic_comments == null){
					
					rows[i].gic_comments = '该记录填写有误！';

				} 
				rows[i].gic_deadline = userDate(rows[i].gic_deadline);
				if(unpasscolleges.indexOf(rows[i].gic_college)<0)
				{
					unpasscolleges[j]=rows[i].gic_college;
					j++;
				}
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>Sec_UpdateGoInterConfer',
		        	type:'post',
		        	data: {rowdata:rowstr,patter:"batch"},
		        	dataType: 'json',
		        	success : function(r){
		        		if(r>1){
		        			$('#grid').datagrid('reload');
		        		}else{
		        			$('#grid').datagrid('beginEdit',doedit);
		        		}
		        		$('#grid').datagrid('unselectAll');
		        	}
		        	
		        });
			//退回
			var flag= "2";
			var roleid = getCookie('watchroleid');
			var tableid = getCookie('tableid');
			$('#roleidd').val(roleid);
			$('#tableidd').val(tableid);
			$('#flagg').val(flag);
			$('#useridd').val(userid);
			$.ajax({
				type:'post',
				url:'<%=basePath%>changeexamsituation',
				data:{id:tableid,flag:flag,userid:userid,unpasscolleges:unpasscollegesstr},
				success : function(r){
	        		if(r==4){
	        			location.href="<%=basePath%>admin/collecttable.jsp";
	        		}else{
	        			location.href="<%=basePath%>admin/examinetable.jsp";
	        		}
				},error:function(){
					alert('fail');
				}
				
			}); 
		}
	}
	function _Export(){
		var tableid = getCookie('tableid');
		location.href="<%=basePath%>ExportGoInterConfer?tableid="+tableid;
	}
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid" class = "easyui-datagrid"></table>
</body>
</html>


