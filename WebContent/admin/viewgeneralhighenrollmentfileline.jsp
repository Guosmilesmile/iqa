<%@page import="cn.edu.xmu.table.GeneralHighEnrollmentfileLineTable"%>
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

<title>厦门大学普高招生各省份出档线高出本一线分值</title>
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
		queryParams = {"ghel_college":college};	
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
		url: '<%=basePath%>getgeneralhighenrollmentfileline',
		sortName: 'ghel_id',
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
			    
				{field:'ghel_id',title:'ID',sortable:true,width:80},
				{field:'ghel_type',title:'类型',sortable:true,width:120,},
				{field:'ghel_hainan',title:'海南',sortable:true,width:80,},
				{field:'ghel_xinjiang',title:'新疆',sortable:true,width:80,},
				{field:'ghel_xizangshao',title:'西藏(少)',sortable:true,width:80,},
				{field:'ghel_yunnan',title:'云南',sortable:true,width:80,},
				{field:'ghel_shanxishan',title:'陕西',sortable:true,width:80,},
				{field:'ghel_tianjin',title:'天津',sortable:true,width:80,},
				{field:'ghel_ningxia',title:'宁夏',sortable:true,width:80,},
				{field:'ghel_guizhou',title:'贵州',sortable:true,width:80,},
				{field:'ghel_liaoning',title:'辽宁',sortable:true,width:80,},
				{field:'ghel_xizanghan',title:'西藏(汉)',sortable:true,width:80,},
				{field:'ghel_jilin',title:'吉林',sortable:true,width:80,},
				{field:'ghel_guangxi',title:'广西',sortable:true,width:80,},
				{field:'ghel_zhejiang',title:'浙江',sortable:true,width:80,},
				{field:'ghel_chongqing',title:'重庆',sortable:true,width:80,},
				{field:'ghel_anhui',title:'安徽',sortable:true,width:80,},
				{field:'ghel_heilongjiang',title:'黑龙江',sortable:true,width:80,},
				{field:'ghel_jiangxi',title:'江西',sortable:true,width:80,},
				{field:'ghel_sichuan',title:'四川',sortable:true,width:80,},
				{field:'ghel_beijing',title:'北京',sortable:true,width:80,},
				{field:'ghel_henan',title:'河南',sortable:true,width:80,},
				{field:'ghel_hunan',title:'湖南',sortable:true,width:80,},
				{field:'ghel_shanghai',title:'上海',sortable:true,width:80,},
				{field:'ghel_fujian',title:'福建',sortable:true,width:80,},
				{field:'ghel_shandong',title:'山东',sortable:true,width:80,},
				{field:'ghel_hebei',title:'河北',sortable:true,width:80,},
				{field:'ghel_hubei',title:'湖北',sortable:true,width:80,},
				{field:'ghel_guangdong',title:'广东',sortable:true,width:80,},
				{field:'ghel_jiangsu',title:'江苏',sortable:true,width:80,},
				{field:'ghel_shanxijin',title:'山西',sortable:true,width:80,},
				{field:'ghel_neimenggu',title:'内蒙古',sortable:true,width:80,},
				{field:'ghel_qinghai',title:'青海',sortable:true,width:80,},
				{field:'ghel_gansu',title:'甘肃',sortable:true,width:80,},
				{field:'ghel_comments',title:'审核意见',sortable:true,width:120,
					editor: {type: 'text',}
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
				id = rowData.ghel_id;
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
		$("#grid").datagrid('removeEditor', 'ghel_comments');
		$('div.datagrid-toolbar a').eq(2).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
	}
	if(<%=show %>==0 ){//单纯查看
		
		$('div.datagrid-toolbar a').eq(0).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
		$('div.datagrid-toolbar a').eq(2).hide();
		
		$("#grid").datagrid('removeEditor', 'ghel_comments');  
		
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
		unpasscolleges[0]=rows[0].ghel_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].ghel_comments = '';
			rows[i].ghel_deadline = userDate(rows[i].ghel_deadline);
		}
		var rowstr = JSON.stringify(rows);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>updategeneralhighenrollmentfileline',
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
				if(rows[i].ghel_comments == "" ||rows[i].ghel_comments == null){
					
					rows[i].ghel_comments = '该记录填写有误！';

				}
				rows[i].ghel_deadline = userDate(rows[i].ghel_deadline);
				if(unpasscolleges.indexOf(rows[i].ghel_college)<0)
				{
					unpasscolleges[j]=rows[i].ghle_college;
					j++;
				}
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>updategeneralhighenrollmentfileline',
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
		location.href="<%=basePath%>exportgeneralhighenrollmentfileline?tableid="+tableid+"&ghel_college="+encodeURI(encodeURI(college));
	}
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<h2>附表6-1-5-2厦门大学普高招生各省份出档线高出本一线分值（时点）</h2>
	<table id="grid" class = "easyui-datagrid"></table>
</body>
</html>