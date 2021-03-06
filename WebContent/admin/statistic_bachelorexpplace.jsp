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
<title>3.2.3 本科实验、实习、实训场所情况</title>
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
			url: '<%=basePath%>GetBachelorExpPlaceServlet',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    		    /* {field: 'ck',checkbox: true},   */
			    		    {field:'rowTitle',title:'项目',align:'left'} 
			    		]],
			    		
			columns: [[
			    					
			   {title:"本科实验、实习、实训场所",colspan:5,rowspan:1},
			   {title:"校外实习、实训基地",colspan:2,rowspan:1},

			 ],[
				{field:'area',title:'面积（平方米）',rowspan:1,width:85},
				{field:'exphour',title:'学年度承担的实验教学人时数（人时）',rowspan:1,width:210},
				{field:'expcount',title:'学年度承担的实验教学人次数（人次）',rowspan:1,width:205},
				{field:'projectnum',title:'本科生实验、实习、实训项目数（个）',rowspan:1,width:205},
				{field:'capacity',title:'每次可容纳的学生数',rowspan:1,width:110},
				{field:'outschoolnum',title:'个数',rowspan:1},
				{field:'outcapacity',title:'平均每次可接纳学生数',rowspan:1},
				
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
		
		location.href="<%=basePath%>Statistic_ExportBachelorExpPlaceServlet?rowdata="+rowstr;
	}
</script>

</head>
<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>