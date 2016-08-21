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
<title>附表3 各教学单位专任教师结构</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">

<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/kendo/js/kendo.all.min.js"></script>
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
			url: '<%=basePath%>Sec_GetManagerInfoServlet',
			sortName: 'mi_id',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    		    /* {field: 'ck',checkbox: true},   */			    		   
			    		    {field:'rowTitle',title:'',align:'left'} 

			    		]],
			    		
			columns: [[
						{field:'total',title:"序号",rowspan:3,},
						{field:'total',title:"单位",rowspan:3,},
						{field:'total',title:"专任教师数",width:120,rowspan:3,},
						
						{title:"职称",width:500,colspan:3,},
						{title:"学位",width:500,colspan:3,},
						{title:"年龄",width:500,colspan:4,},
						{title:"学缘",width:500,colspan:3,},
					],[
						{field:'zhenggaoji',title:'教授',rowspan:2,},
						{field:'fugaoji',title:'副教授',rowspan:2,},
						{field:'zhongji',title:'其他',rowspan:2,},
						
						{field:'chuji',title:'博士',rowspan:2,},
						{field:'noTitle',title:'硕士',rowspan:2,},
						{field:'doctor',title:'其他',rowspan:2,},
						
						{field:'master',title:'35岁及以下',rowspan:2,},
						{field:'bachelor',title:'36-45',rowspan:2,},
						{field:'noDegree',title:'46-55',rowspan:2,},
						{field:'below35',title:'56岁及以上',rowspan:2,},
						
						{field:'between36_45',title:'本校',rowspan:2,},
						{title:"学缘",width:500,colspan:2,},
					   ],[
						{field:'between36_45',title:'本校',rowspan:1,},
						{field:'between36_45',title:'本校',rowspan:1,},
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
	};
		//更改datebox的日期格式
		function myformatter(value, row, index) {
			if(value != null && value != ""){
	    		var date = new Date(value);
	            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	    	}
		};
		function myparser(s) {
			var ss = (s.split('/'));
			if(ss.length > 1)
				return ss[2] + "-" + ss[0] + "-" + ss[1];
			else return s;
		};
</script>

</head>
<body bgcolor="#DDF3FF" class = "h2">
<h2>附表3 各教学单位专任教师结构</h2>
	<table id="grid" class = "easyui-datagrid"></table>

</body>
</html>