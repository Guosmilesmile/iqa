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

<title>查看境外交流表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/myeditor.js"></script>

<script>
    var tableid,userid;

    var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
    var college;
    
    $(function(){
    	//$("#searchDialog").hide();
		//获取数据的查询参数----过滤数据
		//------------------------------------要加获取tableid的方法=======================
		tableid = getCookie('tableid');
		
		userid = getCookie('userid');
		showtrue=getCookie('showtrue');
		
		<%String filldeptxi = request.getAttribute("deptxi").toString();%>
		college = "<%=filldeptxi%>";
		//college = getCookie('deptxi');
		var queryParams;
		
		if(college!=""&&college!=undefined)
		{
			college = decodeURI(college);
			queryParams = {"college":college, "tableid":tableid, "userid":userid};	
		}
		else{
			queryParams = {"tableid":tableid, "userid":userid};
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
			url: '<%=basePath%>Sec_GetUndergraduateAcademicCompetitionServlet',
			sortName: 'uac_serialnumber',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			frozenColumns:[[
						    {field: 'ck', checkbox: true},          
						]],
						columns: [[								    
									{field:'uac_id',title:'ID',sortable:true,width:80},
									{field:'uac_collegename',title:'学院',sortable:true,width:100,},
									{field:'uac_competitionname',title:'竞赛名称',sortable:true,width:100,},
									{field:'uac_awardgrade',title:'奖项等级',sortable:true,width:100,},
									{field:'uac_prizelevel',title:'获奖级别',sortable:true,},
									{field:'uac_personalorteam',title:'个人/团队',sortable:true,},
									{field:'uac_studentname',title:'获奖学生姓名',sortable:true,width:120,},
									{field:'uac_studentnum',title:'获奖学生人数',sortable:true,width:100,},
									{field:'uac_windate',title:'获奖时间（年、月）',sortable:true,},
									{field:'uac_college',title:'填报学院',sortable:true,width:100,},
									{field:'uac_comments',title:'审核意见',sortable:true,width:120,
										editor: { type: 'validatebox',}
									},
								]],
			
			
			
			toolbar:[{//全选---反选
				   text:"反选",
				   handler: _invertSelect,
			   },'-',
					   {//提交...............................................这边增加提交功能
						   text: "通过",
						   iconCls: "icon-ok",
						   handler:_Pass, 
					   },'-',{//------------------------导入
						   text: "不通过",
						   iconCls: "icon-cancel",
						   handler:_UnPass,
						   //-------------------------------填写导入方法名字
					   },'-',{//------------------------导入
						   text: "导出",
						   iconCls: "icon-redo",
						   handler:_Export, 
						   //-------------------------------填写导入方法名字
					   },'-',
					],
					onAfterEdit: function(rowIndex,rowData,changes){
						doedit = undefined;
					},
					
					onDblClickRow:function(rowIndex, rowData){
										
						serialnumber = rowData.uac_serialnumber;
			        	$('#grid').datagrid('beginEdit',rowIndex);
			        	//这边的rowIndex和行号是不一样的值，0------开始的
			        	doedit=rowIndex;
						
					},
					
			pagination: true,
			rownumbers:true,
		});


		
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
		
		<%
		String conpcom = request.getAttribute("conpcom").toString();
		%>
		if(true==<%=conpcom%>){
			$("#grid").datagrid('removeEditor', 'uac_comments');
			$('div.datagrid-toolbar a').eq(2).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
		}
		if(<%=show %>==0){//单纯查看
			$('div.datagrid-toolbar a').eq(0).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
			$('div.datagrid-toolbar a').eq(2).hide();
			
			$("#grid").datagrid('removeEditor', 'uac_comments');
			//隐藏最后一列
		}else{//审核查看
			//隐藏倒数第二列
		}
	};
	
	//------------------------------------反选数据,审核时需要选择-----------------------------------
	function _invertSelect(){
		var rows = $('#grid').datagrid('getSelections');
		$('#grid').datagrid('selectAll');
		for(var i=0;i<rows.length;i++){
			var rowIndex = $('#grid').datagrid('getRowIndex', rows[i]);
			$('#grid').datagrid('unselectRow',rowIndex);
		}
		
	}
	function _Pass(){
		var rows = $('#grid').datagrid('getRows');
		
		var unpasscolleges = new Array();
		unpasscolleges[0]=rows[0].uac_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].uac_comments = '';
		}
		var rowstr = JSON.stringify(rows);
		//alert(rowstr);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>Sec_UpdateUndergraduateAcademicCompetitionServlet',
	        	type:'post',
	        	data: {rowdata:rowstr,patter:"batch"},
	        	dataType: 'json',
	        	success : function(r){
	        		if(r=="1"){
	        			$('#grid').datagrid('reload');
	        		}else{
	        			$('#grid').datagrid('beginEdit',doedit);
	        		}
	        		$('#grid').datagrid('unselectAll');
	        	}
	        	
	        });
		
		var flag="1";
		//var roleid = getCookie('watchroleid');
		var tableid = getCookie('tableid');
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
				if(rows[i].uac_comments == "" ||rows[i].uac_comments == null){
					
					rows[i].uac_comments = '该记录填写有误！';
				} 
				if(unpasscolleges.indexOf(rows[i].uac_college)<0)
				{
					unpasscolleges[j]=rows[i].uac_college;
					j++;
				}
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>Sec_UpdateUndergraduateAcademicCompetitionServlet',
		        	type:'post',
		        	data: {rowdata:rowstr,patter:"batch"},
		        	dataType: 'json',
		        	success : function(r){
		        		//alert(r);
		        		if(r=="1"){
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
		location.href="<%=basePath%>Sec_ExportUndergraduateAcademicCompetitionServlet?tableid="+tableid+"&college="+encodeURI(encodeURI(college));
	}
	
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
    <h2>附表6-2-1-1   2014-2015学年本科生参加学科竞赛情况（省部级及以上）</h2>
	<table id="grid" class = "easyui-datagrid"></table>

	<div id="unpassmessage">
		<form action="<%=basePath%>changeexamsituation" method="post">
		<input type="hidden" id="tableidd" name="id">
		<input type="hidden" id="useridd" name="userid">
		<input type="hidden" id="roleidd" name="roleid">
		<input type="hidden" id="flagg" name="flag">
		请输入审核意见:
		<br><textarea rows="20" cols="60" name="unpassmessage"></textarea>
		<br>
		<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>


