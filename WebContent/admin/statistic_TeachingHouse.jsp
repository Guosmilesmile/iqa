<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3.2.1 教学行政用房情况</title>
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
			url: '<%=basePath%>GetTeachingHouseServlet',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    		    /* {field: 'ck',checkbox: true},   */
			    		    {field:'rowTitle',title:'项目',align:'left'} 
			    		]],
			    		
			columns: [[
			    					
			   {title:"教学行政用房",colspan:10,rowspan:1},
			   {title:"教室", colspan:7, rowspan:1},
			   {title:"运动场馆", colspan:2, rowspan:1},
			   {title:"学生活动中心", colspan:2, rowspan:1},
			 ],[
				{field:'areaTotal',title:'总面积（平方米）',rowspan:1,},
				{field:'asistHouse',title:'教学科研及辅助用房（平方米）',rowspan:1,},
				{field:'classroomArea',title:'其中：教室（平方米）',rowspan:1,},
				{field:'libraryArea',title:'其中：图书馆（平方米）',rowspan:1,},
				{field:'labArea',title:'其中：实验室、实习场所（平方米）',rowspan:1,},
				{field:'researchArea',title:'其中：专用科研用房（平方米）',rowspan:1,},
				{field:'gymArea',title:'其中：体育馆（平方米）',rowspan:1,},
				{field:'hallArea',title:'其中：会堂（平方米）',rowspan:1,},
				{field:'adminArea',title:'其中：行政用房（平方米）',rowspan:1,},
				{field:'avgArea',title:'生均教学行政用房面积（平方米/生）',rowspan:1,},
				
				{field:'classroomAmount',title:'数量（间）',rowspan:1,},
				{field:'englishComputerAmount',title:'其中：外语教学计算机机房（含语音室）（间）',rowspan:1,},
				{field:'multimediaAmount',title:'多媒体教室（间）',rowspan:1,},
				{field:'seatNumber',title:'座位数（个）',rowspan:1,},
				{field:'englishComputerNumber',title:'其中：外语教学计算机机房（含语音室）（个）',rowspan:1,},
				{field:'multimediaNumber',title:'多媒体教室（个）',rowspan:1,},
				{field:'perHundredNumber',title:'百名学生配多媒体教室和语音实验室座位数（个）',rowspan:1,},
				{field:'sportsArea',title:'面积（平方米）',rowspan:1,},
				{field:'sportsNumber',title:'体育馆数量（个）',rowspan:1,},
				{field:'studentsActivityArea',title:'面积（平方米）',rowspan:1,},
				{field:'studentsActitvityAmount',title:'数量（间）',rowspan:1,},
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
							id = rowData.mi_id;
				        	$('#grid').datagrid('beginEdit',rowIndex);
				        	//这边的rowIndex和行号是不一样的值，0------开始的
				        	doedit=rowIndex;
						}
						
					},
					
			});
	}
	
	function _Export(){
		//需要向后台传递对象信息
		var rows = $("#grid").datagrid("getRows");
		var rowstr = JSON.stringify(rows);
		rowstr = encodeURI(rowstr);
		
		location.href="<%=basePath%>Statistic_ExportTeachingHouseServlet?rowdata="+rowstr;
	}
	
	</script>
</head>

<body bgcolor="#DDF3FF" class = "h2">
<h2>3.2.1 教学行政用房情况</h2>
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>