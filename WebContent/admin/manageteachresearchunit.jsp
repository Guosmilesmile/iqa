<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>表1-4 学校教学科研单位表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>

<script>

    var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
    var serialnumber =serialnumber; 
    var tableid,userid;
    
    $(function(){
		//获取数据的查询参数----过滤数据
		tableid = getCookie('tableid');
		userid = getCookie('userid');
		
		/* 
		var college = getCookie('deptxi');
		var queryParams;
		
		if(college!=""&&college!=undefined)
		{
			college = decodeURI(college);
			queryParams = {"college":college};	
		}
		else{
			queryParams = {};
		} */
		getData();
	}); 
    
     function getCookie(objName){//获取指定名称的cookie的值 
    	var arrStr = document.cookie.split("; "); 
    	for(var i = 0;i < arrStr.length;i ++){ 
    	var temp = arrStr[i].split("="); 
    	if(temp[0] == objName) return unescape(temp[1]); 
    	} 
    }; 
	function getData(){
		$('#grid').datagrid({
			url: '<%=basePath%>getteachresearchunit',
			sortName: 'tr_serialnumber',
			sortOrder: 'asc',
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    {field: 'ck', checkbox: true},          
			]],
			
			columns: [[
			    
				{field:'tr_id',title:'ID',sortable:true,},
				{field:'tr_name',title:'教学科研单位名称',sortable:true,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox', options: { required: true}}
				},
				{field:'tr_number',title:'单位号',sortable:true,
					editor: { type: 'validatebox',  }
				},
				{field:'tr_responperson',title:'单位负责人',sortable:true,
					editor: { type: 'validatebox',  }
				},
				
			]],
			
			toolbar:[
			   {//添加数据
				   text:"添加",
				   iconCls: "icon-add",
				   handler: _insertRow,
				
			   },'-',{//修改数据s
				   text:"编辑",
				   iconCls: "icon-edit",
				   handler: _editRow,
				   
			   },'-',{//删除数据
				   text: "删除",
				   iconCls: "icon-remove",
				   handler: _removeRow,
			   },'-',{//保存修改
				   text: "保存",
				   iconCls: "icon-save",
				   handler: _saveRows,
			   },'-',{
				   text: "提交",
				   iconCls: "icon-ok",
				   handler: _commitdata,
			   },'-',
			   
			],
			
			onAfterEdit: function(rowIndex,rowData,changes){
				doedit = undefined;
			},
			onDblClickRow:function(rowIndex, rowData){
				
				if(doedit==undefined)
				{					
					serialnumber = rowData.tr_serialnumber;
		        	$('#grid').datagrid('beginEdit',rowIndex);
		        	//这边的rowIndex和行号是不一样的值，0------开始的
		        	doedit=rowIndex;
				}
				
			},
			
			pagination: true,
			rownumbers:true,
		});
		
		//页面属性设置
		var p = $('#grid').datagrid('getPager');
		$(p).pagination({
			pageSize: 10, //每页条目数量
			
		});
	}
	
	
	//------------------------------------插入行数据-----------------------------------
	function _insertRow(){
		if(doedit != undefined){
			datagrid.datagrid('endEdit',doedit);
		}
		if(doedit == undefined){
			
			var row = $('#grid').datagrid('getSelected');
			var rowIndex = $('#grid').datagrid('getRowIndex', row);
			rowIndex = rowIndex + 1;
			serialnumber = row.tr_serialnumber + 1;
			
			$('#grid').datagrid('insertRow',{
				index: rowIndex,
				row: {
				}
			});
			$('#grid').datagrid('beginEdit',rowIndex);
			doedit = rowIndex;
		}
		
	};
	//------------------------------------编辑行数据-----------------------------------
	function _editRow(){
		var row = $('#grid').datagrid('getSelected');
		if(row){
			if(doedit!=null){
				$('#grid').datagrid('endEdit',doedit);
				
			}
			if(doedit == undefined){
				var rowIndex = $('#grid').datagrid('getRowIndex', row);
				serialnumber = row.tr_serialnumber;
				$('#grid').datagrid('beginEdit',rowIndex);
				doedit = rowIndex;
				
				$('#grid').datagrid('unselectAll');
			}
			
		};
	};
	//------------------------------------删除行数据-----------------------------------
	function _removeRow(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length <= 0){
			$.messger.alert('警告','您没有选择','error');
		}
		else if(rows.length >= 1){
			var tr_id = [];
			for(var i = 0; i < rows.length; ++i)
				{
					tr_id[i] = rows[i].tr_id;
				}						
			$.post("<%=basePath%>deleteteachresearchunit", {trids: tr_id.toString()},
				function (data, textStatus){
				
				if(data == 'true'){
						$.messager.show({ 
						title : '提示', 
						msg : '删除成功' 
					}); 
					
					}else{
						$.messager.show({ 
						title : '提示', 
						msg : '删除失败' 
					}); 
					
					}
				  $('#grid').datagrid('reload'); 
				}, "text");
 
		}
	};
	//------------------------------------保存行数据-----------------------------------
	function _saveRows(){
		
		$('#grid').datagrid('endEdit', doedit);
		var inserted = $('#grid').datagrid('getChanges', 'inserted');
		var updated = $('#grid').datagrid('getChanges', 'updated');
		var deleted = $('#grid').datagrid('getChanges','deleted');
		var rows = $('#grid').datagrid('getChanges');
		var rowstr = JSON.stringify(rows);
		
		if (inserted.length < 1 && updated.length < 1 && deleted.length<1) {  
            editRow = undefined;  
            datagrid.datagrid('unselectAll');  
            return;  
        }  

        var url = '';  
        if (inserted.length > 0) {  
            url = '<%=basePath%>addteachresearchunit';  
        }  
        if (updated.length > 0) {  
        	url = '<%=basePath%>updateteachresearchunit';   
        }  
        
        rowstr = encodeURI(rowstr);
        
        $.ajax({
        	url: url,
        	data: {rowdata:rowstr, serialnumber:serialnumber},
        	dataType: 'json',
        	success : function(r){
        		if(r){
        			$('#grid').datagrid('acceptChanges');
        			$.messager.show({
        				title: '成功',
        				msg: '操作成功'
        			});
        			doedit = undefined;
        			$('#grid').datagrid('reload');
        		}else{
        			$('#grid').datagrid('beginEdit',doedit);
        			$.message.alert('错误', r.msg, 'error');
        		}
        		
        		$('#grid').datagrid('unselectAll');
        	}
        	
        });      
	};
	
	//------------------------提交--------------------
	function _commitdata(){
		$.post("<%=basePath%>committabledata", {tableid: tableid, userid: userid},
				function (data, textStatus){
				
				if(data == '1'){
						$.messager.show({ 
						title : '提示', 
						msg : '提交成功' 
					}); 
					
					}else{
						$.messager.show({ 
						title : '提示', 
						msg : '提交失败' 
					}); 
					$('#grid').datagrid('reload'); 
					}
				}, "text");
	}
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid" class = "easyui-datagrid"></table>
</body>
</html>


