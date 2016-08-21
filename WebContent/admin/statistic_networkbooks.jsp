<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3.2.4 校园网、图书情况</title>
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
			url: '<%=basePath%>GetNetworkBooksServlet',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    		    /* {field: 'ck',checkbox: true},   */
			    		    {field:'rowTitle',title:'项目',align:'left'} 
			    		]],
			    		
			columns: [[
				{field:'mainband',title:'校园网主干带宽(Mbps)',rowspan:1},
				{field:'outband',title:'校园网出口带宽（Mbps）',rowspan:1},
				{field:'networkaccess',title:'网络接入信息点数量（个）',rowspan:1},
				{field:'paperbooktotal',title:'纸质图书总量（册）',rowspan:1},
				{field:'paperbookperstudent',title:'生均纸质图书（册）',rowspan:1},
				{field:'ebooktotal',title:'电子图书数量（种）',rowspan:1},
				{field:'ebookperstudent',title:'生均电子图书（种）',rowspan:1},
				{field:'newpaperbookthatyear',title:'当年新增纸质图书（册）',rowspan:1},
				{field:'newpaperbookthatyearperstudent',title:'生均年进纸质图书（册）',rowspan:1},
				{field:'newebookthatyear',title:'当年新增电子图书（种）',rowspan:1},
				{field:'bookcurrencythatyear',title:'当年图书流通量（本次）',rowspan:1,},
				{field:'paperjournaltotal',title:'纸质期刊数量（份）',rowspan:1},
				{field:'paperjournaltype',title:'纸质期刊种类数（种）',rowspan:1},
				{field:'ejournaltype',title:'电子期刊种类（种）',rowspan:1},
				{field:'databasenumber',title:'数据库（个）',rowspan:1}
				
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
		
		location.href="<%=basePath%>Statistic_ExportNetworkBooksServlet?rowdata="+rowstr;
	}
</script>

</head>
<body bgcolor="#DDF3FF" class = "h2">
<h2>3.2.4 校园网、图书情况</h2>
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>