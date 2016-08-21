<%@page import="cn.edu.xmu.table.ForeignExchangeTable"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[] cookies = request.getCookies();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>6.1 评教信息</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">

<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/kendo/js/kendo.all.min.js"></script>
<!-- <script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>  -->
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>

<!-- 过滤需要导入改js -->
<script type="text/javascript" src="<%=path%>/js/easyUI/datagrid-filter.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/myeditor.js"></script>

<style type="text/css">
#unpassmessage{
	position: absolute;
	z-index: 9999;
	left: 500px;
	top: 150px;
	background-color: white;
	width: 500px;
	padding: 50px;
	display: none;
}
.box-pdf{
position: relative;
top: -10px;
} 

.create-chart{
position: relative;
top: 25px;
}
.chart-output{
	position: absolute;
	left: 850px;
	top: 260px;
	width: 400px;
}
.chart-options{
	position: absolute;
	left: 850px;
	top: 40px;
	width: 125px;
}
.demo-section {
background-color: rgb(221, 243, 255);
       width: 460px;
       height: 300px;
	position: absolute;
	left: 320px;
	top: 3px;
         }
         .demo-section h2 {
       text-transform: uppercase;
       font-size: 1em;
       margin-bottom: 30px;
         }
         #cap {
             float: left;
             width: 242px;
             height: 225px;
             margin: 20px 30px 30px 0;
             background-image: url('../content/web/dropdownlist/cap.png');
             background-repeat: no-repeat;
             background-color: transparent;
         }
         .black-cap {
             background-position: 0 0;
         }
         .grey-cap {
             background-position: 0 -225px;
         }
         .orange-cap {
             background-position: 0 -450px;
         }
         #options {
             padding: 1px 0 30px 30px;
         }
         #options h3 {
             font-size: 1em;
             font-weight: bold;
             margin: 25px 0 8px 0;
         }
         #get {
             margin-top: 25px;
         }
</style>
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
	college = getCookie('deptxi');
	var queryParams;
	
	if(college!=""&&college!=undefined)
	{
		college = decodeURI(college);
		queryParams = {"tq_college":college};	
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
function getData(queryParams){
	$('#grid').datagrid({
		url: '<%=basePath%>getteachingqualityinfo',
		sortName: 'tq_id',
		sortOrder: 'asc',
		queryParams: queryParams,
		nowrap: true, //换行属性
		striped: true, //奇数偶数行颜色区分
		collapsible : true, //可折叠
		pageSize: 5,//每页显示的记录条数，默认为10  
        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
		
		frozenColumns:[[
		    //{field: 'ck', checkbox: true}, 
{field:'tq_project',title:'项目',},			
		]],
		
		columns: [
		          [
		    
		
			
			
			
			{field:'tq_coverpercent',title:'覆盖比例（%）',sortable:true,width:80, rowspan: 2},
			
			{title: '优（%）', colspan: 1},
			
			{title: '良好（%）', colspan: 1},
			
			{title: '中（%）', colspan: 1},
			
			{title: '差（%）', colspan: 1},
			
			{field:'tq_college',title:'学院',rowspan:2,
			},
		],
		[
            {field:'tq_excellent',title:'（90分及以上）',sortable:true,width:120,
			},
			{field:'tq_good',title:'（89-75分）',sortable:true,width:120,
			},
			{field:'tq_medium',title:'（74-60分）',sortable:true,width:120,
			},
			{field:'tq_poor',title:'（60分以下）',sortable:true,width:120,
			},

			]
		 ],
		
		toolbar:[
			{//------------------------导出
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
				id = rowData.tq_id;
	        	$('#grid').datagrid('beginEdit',rowIndex);
	        	//这边的rowIndex和行号是不一样的值，0------开始的
	        	doedit=rowIndex;
			}
			
		},
		
		//pagination: true,
		//rownumbers:true,
	});
	
	
}
	
	/*导出*/
	function _Export(){
		//需要向后台传递对象信息
		var rows = $("#grid").datagrid("getRows");
		var rowstr = JSON.stringify(rows);
		rowstr = encodeURI(rowstr);
		
		location.href="<%=basePath%>exportteachingqualityinfo?rowdata="+rowstr;
	}

	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>


