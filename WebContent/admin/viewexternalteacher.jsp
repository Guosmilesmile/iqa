<%@page import="cn.edu.xmu.table.ExternalTeacherTable"%>
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

<title>3-1-2外聘教师基本信息</title>
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
	//college = getCookie('deptxi');
	<%String filldeptxi = request.getAttribute("deptxi").toString();%>
	college = "<%=filldeptxi%>";
	//**********************************修改部分
	
	var queryParams;
	
	if(college!=""&&college!=undefined)
	{
		college = decodeURI(college);
		queryParams = {"et_college":college};	
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
		url: '<%=basePath%>getexternalteachers',
		sortName: 'et_id',
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
				{field:'et_name',title:'姓名',sortable:true,width:120,},
				{field:'et_worknumber',title:'工号',sortable:true,width:120,},
				{field:'et_documentnumber',title:'证件号',sortable:true,width:120,
				},
				{field:'et_sex',title:'性别',sortable:true,width:120,},
				{field:'et_birth',title:'出生年月',sortable:true,formatter:formatterdate,width:120,},
				{field:'et_appointment',title:'聘任时间',sortable:true,formatter:formatterdate,width:120,},
				{field:'et_situation',title:'任职状态',sortable:true,width:120,},
				{field:'et_term',title:'聘期',sortable:true,width:120,},
				{field:'et_departmentnumber',title:'所属单位',sortable:true,width:120,},
				{field:'et_departmentname',title:'部门名字',sortable:true,width:120,},
				{field:'et_education',title:'学历',sortable:true,width:120,},
				{field:'et_topeducation',title:'最高学位',sortable:true,width:120,},
				{field:'et_professional',title:'专业技术职称',sortable:true,width:120,},
				{field:'et_subject',title:'学科类别',sortable:true,width:120,},
				{field:'et_job',title:'工作单位类别',sortable:true,width:120,},
				{field:'et_teacher',title:'导师类别',sortable:true,width:120,},
				{field:'et_area',title:'地区',sortable:true,width:120,},
				{field:'et_college',title:'所属学院',sortable:true,width:120,},
				{field:'et_deadline',title:'填报期限',sortable:true,formatter:formatterdate,width:120,},
				{field:'et_comments',title:'审核意见',sortable:true,width:120,
					editor: { type: 'validatebox'}
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
			
//			if(doedit==undefined)
//			{					
				id = rowData.et_id;
	        	$('#grid').datagrid('beginEdit',rowIndex);
	        	//这边的rowIndex和行号是不一样的值，0------开始的
	        	doedit=rowIndex;
//			}
			
		},
		
		pagination: true,
		rownumbers:true,
	});
	<%
	String conpcom = request.getAttribute("conpcom").toString();
	%>
	if(true==<%=conpcom%>){
		$("#grid").datagrid('removeEditor', 'et_comments');
		$('div.datagrid-toolbar a').eq(2).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
	}

	if(<%=show %>==0 ){//单纯查看
		
		$('div.datagrid-toolbar a').eq(0).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
		$('div.datagrid-toolbar a').eq(2).hide();
		
		$("#grid").datagrid('removeEditor', 'et_comments');  
		
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
		
		//********************增加的地方
		var unpasscolleges = new Array();
		unpasscolleges[0]=rows[0].et_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		//***************增加截止
		
		
		
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].et_comments = '';
			rows[i].et_birth = userDate(rows[i].et_birth);
			rows[i].et_appointment = userDate(rows[i].et_appointment);
			rows[i].et_deadline = userDate(rows[i].et_deadline);
		}
		
		var rowstr = JSON.stringify(rows);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>updateexternalteacher',
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
			data:{id:tableid,flag:flag,userid:userid,unpasscolleges:unpasscollegesstr},//*******将roleid换成unpasscolleges:unpasscollegesstr
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
				if(rows[i].et_comments == "" ||rows[i].et_comments == null){
					
					rows[i].et_comments = '该记录填写有误！';

				} 
				rows[i].et_birth = userDate(rows[i].et_birth);
				rows[i].et_appointment = userDate(rows[i].et_appointment);
				rows[i].et_deadline = userDate(rows[i].et_deadline);
				//添加================================================================================================
				if(unpasscolleges.indexOf(rows[i].et_college)<0)
				{
					unpasscolleges[j]=rows[i].et_college;
					j++;
				}
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			//添加====================================
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>updateexternalteacher',
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
				data:{id:tableid,flag:flag,userid:userid,unpasscolleges:unpasscollegesstr},//待修改
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
		location.href="<%=basePath%>exportexternalteacher?tableid="+tableid+"&et_college="+encodeURI(encodeURI(college));
		
	}
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<h2>3-1-2外聘教师基本信息</h2>
	<table id="grid" class = "easyui-datagrid"></table>
</body>
</html>


