<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[] cookies = request.getCookies();
/* String tableid = "";
String show = request.getAttribute("showtrue").toString();
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("tableid"))
		tableid = cookie.getValue();
} */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>4.1 专业培养方案学分结构</title>
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

function getData(queryParams){
	$('#grid').datagrid({
		url: '<%=basePath%>GetProfessionalCreditStructure',
		queryParams: queryParams,
		nowrap: true, //换行属性
		striped: true, //奇数偶数行颜色区分
		collapsible : true, //可折叠
		//pageSize: 5,//每页显示的记录条数，默认为10  
        //pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
		
		frozenColumns:[[
		    /* {field: 'ck',checkbox: true},   */
		    {field:'rowTitle',title:'学科门类',align:'left'} 
		]],
		
		columns: [[

			{field:'philosophy',title:'哲学',rowspan:1,},
			{field:'economic',title:'经济学',rowspan:1,},
			{field:'law',title:'法学',rowspan:1,},
			{field:'edu',title:'教育学',rowspan:1,},
			{field:'liter',title:'文学',rowspan:1,},
			{field:'history',title:'历史学',rowspan:1,},
			{field:'science',title:'理学',rowspan:1,},
			{field:'factory',title:'工学',rowspan:1,},
			{field:'farm',title:'农学',rowspan:1,},
			{field:'doctor',title:'医学',rowspan:1,},
			{field:'military',title:'军事学',rowspan:1,},
			{field:'manage',title:'管理学',rowspan:1,},
			{field:'art',title:'艺术学',rowspan:1,},
		]],
		
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
				id = rowData.mi_id;
	        	$('#grid').datagrid('beginEdit',rowIndex);
	        	//这边的rowIndex和行号是不一样的值，0------开始的
	        	doedit=rowIndex;
			}
			
		},
		
		//pagination: true,
		//rownumbers:true,
	});
	
	
	/* //页面属性设置
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        BeforeRefresh:function(){
			$(this).datagrid('reload'); 
			//获取数据库全部数据
		},
		
	}); */
	
	
}

	/*导出*/
	function _Export(){
		//需要向后台传递对象信息
		var rows = $("#grid").datagrid("getRows");
		var rowstr = JSON.stringify(rows);
		rowstr = encodeURI(rowstr);
		
		location.href="<%=basePath%>ExportProfessionalCreditStructure?college="+college;
	}

</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<h2>4.1专业培养方案学分结构</h2>
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>


