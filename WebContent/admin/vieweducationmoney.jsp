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
		url: '<%=basePath%>geteducationmoney',
		sortName: 'em_serialnumber',
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
		
		columns: [
		          [
					{field:'em_colleges',title:'学院',sortable:true,width:120,rowspan:3},
					{title:'教育事业收入（万元）',sortable:true,width:120,colspan:7,rowspan:1},
					{title:'教育经费支出（万元）',sortable:true,width:120,colspan:7,rowspan:1},
					{field:'em_comments',title:'审核意见',sortable:true,width:120,rowspan:3,
						editor: { type: 'text'}
					},
		           ],
		           [
						{field:'em_intotal',title:'合计',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
							
						},
						{field:'em_inschoolfunds',title:'综合办学经费',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_inchange',title:'教改专项',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_instudentactivity',title:'学生活动费',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_inbuy',title:'修购专项',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_inselfraise',title:'学院自筹',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_indonations',title:'捐款',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outtotal',title:'合计',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outdaily',title:'教学日常运行支出',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outchange',title:'教学改革支出',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outbuild',title:'专业建设支出',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{title:'教育经费支出（万元）',sortable:true,width:120,colspan:3}
						,{field:'em_outother',title:'其他专项',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outstudentactivity',title:'学生活动经费支出',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outteacher',title:'教师培训进修专项支出',sortable:true,width:120,rowspan:2,
							//如果要实现编辑功能，需要添加下面的属性
						},
						
		            ],
		         	[
						{field:'em_outpractice',title:'其中：',sortable:true,width:120,
							//如果要实现编辑功能，需要添加下面的属性
							
						},
						{field:'em_outpracticeexperiment',title:'实践实验经费支出',sortable:true,width:120,
							//如果要实现编辑功能，需要添加下面的属性
						},
						{field:'em_outpracticeinter',title:'实践实习经费支出',sortable:true,width:120,
							//如果要实现编辑功能，需要添加下面的属性
						},
		         	 ],
		           ],
		
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
				id = rowData.em_id;
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
		$("#grid").datagrid('removeEditor', 'em_comments');
		$('div.datagrid-toolbar a').eq(2).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
	}
	
	if(<%=show %>==0 ){//单纯查看
		$('div.datagrid-toolbar a').eq(0).hide();
		$('div.datagrid-toolbar a').eq(1).hide();
		$('div.datagrid-toolbar a').eq(2).hide();
		
		$("#grid").datagrid('removeEditor', 'em_comments');
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
		unpasscolleges[0]=rows[0].em_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);


		for(var i = 0; i < rows.length; i++)
		{
			rows[i].em_comments = '';
		}
		var rowstr = JSON.stringify(rows);
		//alert(rowstr);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>Updateeducationmoney',
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
				if(rows[i].em_comments == "" ||rows[i].em_comments == null){
					
					rows[i].em_comments = '该记录填写有误！';
				} 
				if(unpasscolleges.indexOf(rows[i].em_college)<0)
				{
					unpasscolleges[j]=rows[i].em_college;
					j++;
				}

				/* alert(i);
				alert(rows[i].fe_comments); */
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>Updateeducationmoney',
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
		location.href="<%=basePath%>exporteducationmoney?tableid="+tableid+"&em_college="+encodeURI(encodeURI(college));
	}
	

	
	
	
	
</script>


</head>

<body bgcolor="#DDF3FF" class="h2">
<h2>附表2-10-2-1教育经费收支情况</h2>
	<table id="grid" class="easyui-datagrid"></table>

</body>
</html>


