<%@page import="cn.edu.xmu.table.ForeignExchangeTable"%>
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

<title>查看专业基本情况表</title>
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
var gradee = [{ "value": "0", "text": "2008" }, 
              { "value": "1", "text": "2009" },
              { "value": "2", "text": "2010" },
              { "value": "3", "text": "2011" },
              { "value": "4", "text": "2012" },
              { "value": "5", "text": "2013" }];
 var classess =[{ "value": "0", "text": "核心刊物" },
                { "value": "1", "text": "普通刊物" }]; 
$(function(){
	//获取数据的查询参数----过滤数据
	//------------------------------------要加获取tableid的方法=======================
	tableid = getCookie('tableid');
	userid = getCookie('userid');
	
	//获取数据的查询参数----过滤数据
	//college = getCookie('deptxi');
			<%String filldeptxi = request.getAttribute("deptxi").toString();%>
			college = "<%=filldeptxi%>";

	var queryParams;
	
	if(college!=""&&college!=undefined)
	{
		college = decodeURI(college);
		if(college=="\"\"")college="";
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
function getData(queryParams){
	$('#grid').datagrid({
		url: '<%=basePath%>getteacherpaper',
		sortName: 'tp_serialnumber',
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
					{field:'tp_id',title:'ID',sortable:true,width:80},
					{field:'tp_colleges',title:'学院',sortable:true,width:120,
					},
					{field:'tp_papertitle',title:'论文题目',sortable:true,width:120,
					},
					{field:'tp_paperclasses',title:'论文类别',sortable:true,width:120,
					},
					{field:'tp_authors',title:'作者',sortable:true,width:120,
						
					},
					{field:'tp_serial',title:'作者排序',sortable:true,width:120,
					},
					{field:'tp_authorclasses',title:'作者类别',sortable:true,width:120,
					},
					{field:'tp_publication',title:'刊物名称 ',sortable:true,width:120,
					},
					{field:'tp_year',title:'发表年份',sortable:true,width:120,
					},
					{field:'tp_month',title:'发表时间',sortable:true,width:120,
					},
					
					{field:'tp_classes',title:'刊物类别',sortable:true,width:120,
					},
					{field:'tp_comments',title:'审核意见',sortable:true,width:120,
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
			
			//if(doedit==undefined)
			//{					
				id = rowData.tp_id;
	        	$('#grid').datagrid('beginEdit',rowIndex);
	        	//这边的rowIndex和行号是不一样的值，0------开始的
	        	doedit=rowIndex;
			//}
			
		},
		
		pagination: true,
		rownumbers:true,
	});
	<%
	String conpcom = request.getAttribute("conpcom").toString();
	%>
	if(true==<%=conpcom%>){
		$("#grid").datagrid('removeEditor', 'tp_comments');
		$('div.datagrid-toolbar a').eq(2).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
	}
	if(<%=show %>==0 ){//单纯查看
		$('div.datagrid-toolbar a').eq(0).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
		$('div.datagrid-toolbar a').eq(2).hide();
		
		$("#grid").datagrid('removeEditor', 'tp_comments');
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
		unpasscolleges[0]=rows[0].tp_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].tp_comments = '';
		}
		var rowstr = JSON.stringify(rows);
		//alert(rowstr);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>updateteacherpaper',
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
		var roleid = getCookie('watchroleid');
		var tableid = getCookie('tableid');
		$.ajax({
			type:'post',
			url:'<%=basePath%>changeexamsituation',
			data:{id:tableid,flag:flag,userid:userid,unpasscolleges:unpasscollegesstr},
			success:function(r){
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
		var unpasscolleges = new Array(),j=0;

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
				if(rows[i].tp_comments == "" ||rows[i].tp_comments == null){
					
					rows[i].tp_comments = '该记录填写有误！';
				} 
				if(unpasscolleges.indexOf(rows[i].tp_college)<0)
				{
					unpasscolleges[j]=rows[i].tp_college;
					j++;
				}
				/* alert(i);
				alert(rows[i].fe_comments); */
			}
			for(var i = 0; i < rows.length; i++)
			{
				rows[i].tp_time = myparser(rows[i].tp_time);
			}
			var rowstr = JSON.stringify(rows);
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>updateteacherpaper',
		        	type:'post',
		        	data: {rowdata:rowstr,patter:"batch"},
		        	dataType: 'json',
		        	success : function(r){
		        		//alert(r);
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

				success:function(r){
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
		location.href="<%=basePath%>exportteacherpaper?tableid="+tableid;
	}
	//更改datebox的日期格式
	function myformatter(value, row, index) {
		//return new Date(parseInt(value)).toLocaleString().replace(/年|月/g, "-")
		//		.replace(/日/g, " ");
		var myDate = new Date(parseInt(value));
		var year = myDate.getFullYear();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		return year + '-' + month + '-' + day;
	}
	//时间戳转换成八位日期2014-5-5 
	function myparser(uData){
	  var myDate = new Date(uData);
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth() + 1;
	  var day = myDate.getDate();
	  return year + '-' + month + '-' + day;
	}
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
<h2>附表7-1-2教师、教学管理人员发表教学论文情况</h2>
	<table id="grid" class = "easyui-datagrid"></table>
</body>
</html>


