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

<title>教学管理人员获教学成果奖情况（时点）</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<!-- 过滤需要导入改js -->

<style type="text/css">
	#searchDialog{
		background-color: white;
		border: 1px solid black;
		margin: 0 auto;
		display: none;
	}
</style>


<script>


	var alldata;//获取全部数据
	
	var flag =1;//加载变量
    var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
    var serialnumber =0,college,collegeflag=false;//**********************college需要改成全局变量 
    var tableid,userid;
    $(function(){
    	$("#ssearchDialog").hide();
		//获取数据的查询参数----过滤数据
		//------------------------------------要加获取tableid的方法=======================
		tableid = getCookie('tableid');
		userid = getCookie('userid');
		
		college = getCookie('deptxi');
		var queryParams;
		
		if(college!=""&&college!=undefined)
		{
			college = decodeURI(college);
			queryParams = {"sta_college":college};	
		}
		else{
			queryParams = {};
		}
		$('#cancelDelete').click(function(){
	 		$('.tip').hide();
	 	});
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
			url: '<%=basePath%>getstaffteachingachieve',
			sortName: 'sta_serialnumber',
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
			    
				{field:'sta_id',title:'ID',sortable:true,width:60},
				{field:'sta_year',title:'年度',sortable:true,width:60,
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 9999,
						precision: 0//精确
						}
					}
				},
				{field:'sta_session',title:'届',sortable:true,width:60,
					editor: {type: 'text',}
				},
				{field:'sta_collegename',title:'学院',sortable:true,width:100,
					editor: {type: 'text',}
				},
				{field:'sta_projectname',title:'项目名称',sortable:true,width:200,
					editor: {type: 'text',}
				},
				{field:'sta_persons',title:'主要完成者',sortable:true,width:250,
					editor: {type: 'text',}
				},
				{field:'sta_country',title:'国家级',sortable:true,width:100,
					editor: {type: 'text',}
				},
				{field:'sta_province',title:'省级',sortable:true,width:100,
					editor: {type: 'text',}
				},
				{field:'sta_school',title:'校级',sortable:true,width:100,
					editor: {type: 'text',}
				},
				{field:'sta_ifstaffattend',title:'教学管理人员是否参与',sortable:true,width:80,
					editor: {type: 'text',}
				},
				{field:'sta_comments',title:'审核意见',sortable:true,width:120,
				},
			]],
			
			toolbar:[
			   {//全选---反选
				   text:"反选",
				   handler: _invertSelect,
			   },'-',{//添加数据
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
			   },'-',{//提交...............................................这边增加提交功能
				   text: "提交",
				   iconCls: "icon-ok",
				   handler: _getnullcount,
			   },'-',{//------------------------导入
				   text: "导入",
				   iconCls: "icon-back",
				   handler:_importdata,
				   //-------------------------------填写导入方法名字
			   },'-',{//------------------------下载模板
				   text: "下载模板",
				   iconCls: "icon-back",
				   handler:_downloadModel,
				   //-------------------------------填写下载模板方法名字
			   },'-',
			 
			],
            
			onAfterEdit: function(rowIndex,rowData,changes){
				doedit = undefined;
			},
			onDblClickRow:function(rowIndex, rowData){
				
				if(doedit==undefined)
				{					
					serialnumber = rowData.sta_serialnumber;
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
			
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	        BeforeRefresh:function(){
				$(this).datagrid('reload'); 
				//获取数据库全部数据
			},
			
		});
		
		//**********加这行将toolbar的checkbox去掉
		
		
		$('.datagrid-header-check').find("input").css("display","none");
		
		$('.l-btn-icon.pagination-next').live("click",function(){
			//$('#grid').datagrid('reload'); 
		});
	};
	
	//------------------------------------反选数据-----------------------------------
	function _invertSelect(){
		
		var rows = $('#grid').datagrid('getSelections');
		$('#grid').datagrid('selectAll');
		for(var i=0;i<rows.length;i++){
			var rowIndex = $('#grid').datagrid('getRowIndex', rows[i]);
			$('#grid').datagrid('unselectRow',rowIndex);
		}
		
	};
	
	
	//------------------------------------插入行数据-----------------------------------
	function _insertRow(){
		if(doedit != undefined){
			$('#grid').datagrid('endEdit',doedit);
		}
		if(doedit == undefined){
			
			var row = $('#grid').datagrid('getSelected');
			var rowIndex = $('#grid').datagrid('getRowIndex', row);
			if(row!=null){
				rowIndex = $('#grid').datagrid('getRowIndex', row);
				rowIndex = rowIndex + 1;
				serialnumber = row.sta_serialnumber + 1;
			}
			else{
				rowIndex = 0;
				serialnumber = 1;
			}
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
				serialnumber = row.sta_serialnumber;
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
			$.messager.alert('警告','您没有选择','error');
		}
		else if(rows.length >= 1){
			$.messager.confirm("操作警告", "确定删除后将不可恢复！！", function(data){
				if(data){
					//原来代码开始的位置
					var sta_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							sta_id[i] = rows[i].sta_id;
						}						
					$.post("<%=basePath%>deletestaffteachingachieve", {staids: sta_id.toString()},
						function (data, textStatus){
						
						if(data == 'true'){
							$.messager.alert('提示','删除成功','info');
							$('#grid').datagrid('reload'); 
							} 
							else{
							$.messager.alert('提示','删除失败','fail');
							}
						}, "text");
					//原来代码结束位置
				}
			});
		}
	};
	//------------------------------------保存行数据-----------------------------------
	function _saveRows(){
		$.messager.confirm("操作警告", "确定保存后被修改的数据将不可恢复！！", function(data){
			if(data){
				$('#grid').datagrid('endEdit', doedit);
				var inserted = $('#grid').datagrid('getChanges', 'inserted');
				var updated = $('#grid').datagrid('getChanges', 'updated');
				var deleted = $('#grid').datagrid('getChanges','deleted');
				var rows = $('#grid').datagrid('getChanges');
				var rowstr = JSON.stringify(rows);
				
				if (inserted.length < 1 && updated.length < 1 && deleted.length<1) {  
		            editRow = undefined;  
		            $('#grid').datagrid('unselectAll');  
		            return;  
		        }  

		        var url = '';  
		        if (inserted.length > 0) {  
		            url = '<%=basePath%>addstaffteachingachieve';  
		        }  
		        if (updated.length > 0) {  
		        	url = '<%=basePath%>updatestaffteachingachieve';   
		        }  
		        
		        rowstr = encodeURI(rowstr);
		        if(!collegeflag){
		        	college = encodeURI(college);
					collegeflag=true;	        	
		        }
		        
		        $.ajax({
		        	url: url,
		        	data: {rowdata:rowstr, serialnumber:serialnumber,sta_college:college},
		        	dataType: 'json',
		        	success : function(r){
		        		if(r==1){
		        			if(updated.length>0){
		        				$('#grid').datagrid('acceptChanges');
		            			$.messager.alert('提示', "修改数据成功", 'info');
		        			}
		        			if(inserted.length>0){
		        				$('#grid').datagrid('acceptChanges');
		            			$.messager.alert('提示', "添加数据成功", 'info');
		        			}
		        			
		        			doedit = undefined;
		        			$('#grid').datagrid('reload');
		        		}else{
		        			$('#grid').datagrid('beginEdit',doedit);
		        			if(updated.length>0){
		        				$.messager.alert('错误', "修改数据失败", 'error');
		        			}
		        			if(inserted.length>0){
		        				$.messager.alert('错误', "添加数据失败", 'error');
		        			}
		        		}
		        		$('#grid').datagrid('unselectAll');
		        	}
		        	
		        });
			}
			else{
				//编辑失败后因为刷新了，所以要恢复焦点
				doedit = undefined;
				$('#grid').datagrid('reload');
			}
		});
		
        
	};
	function _getnullcount(){
		$.messager.confirm("操作警告", "提交后数据将不可修改！！", function(data){
			if(data){
				$.post("<%=basePath%>getstaffteachingachieve", {isnull: 1,sort:'sta_serialnumber',order:'asc', sta_college:college},
						function (data, textStatus){
					 data = $.parseJSON(data); 
					if(data.total==0){
						 _commitdata();
					}else{
						 $('#grid').datagrid('loadData',data);
					}
					}, "text");
			}
		});
	}
	function _commitdata(){
		$.messager.confirm("操作警告", "提交后数据将不可修改！！", function(data){
			if(data){
				$.post("<%=basePath%>committabledata", {tableid: tableid, userid: userid},
						function (data, textStatus){
						
						if(data == '1'){
							$.messager.alert('提示', "提交成功", 'info',function(){
								location.href="<%=basePath%>admin/filltable.jsp";	
							});
							
						}else{
							$.messager.alert('错误', "提交失败", 'error');
						}
						$('#grid').datagrid('reload'); 
					}, "text");
			}
		});
	
	}
	//导入
	function _importdata(){
		$("#searchDialog").show();
		$('#ssearchDialog').show();
		if(!collegeflag){
			$("#ssearchDialog").attr("action","importstaffteachingachieve?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
        }
		else{
			$("#ssearchDialog").attr("action","importstaffteachingachieve?tableid="+tableid+"&college="+encodeURI(college));
		}
		$("#ttableid").val(tableid);
	}
	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid="+tableid;
	
	}
	
	
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<h2>附表7-1-1教学管理人员获教学成果奖情况（时点）</h2>
	<table id="grid"></table>
	<div class="tip" id="searchDialog"  icon="icon-save"
		style="padding: 50px; width: 300px; height: 150px;"><%-- ?tableid=<%=tableid%> --%>
	 <form id="ssearchDialog" name="formName" action=""  method="post" enctype="multipart/form-data">
  	 	<input type="hidden" id="ttableid" name="tableid">
  	 	<input type="file" name="myfile1"><br>
   	 	<input type="submit" value="导入">
   	 	<input name="" type="button"  class="cancel" id="cancelDelete" value="取消" />
  	 </form> 
	</div>
	
</body>
</html>






