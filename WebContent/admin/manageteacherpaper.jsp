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

<title>查看列表</title>
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
    var paperclassess = [{ "value": "0", "text": "教学研究论文" }, 
                   { "value": "1", "text": "教学管理论文" }];
    var serials = [{ "value": "0", "text": "1" }, 
                    { "value": "1", "text": "2" },
                    { "value": "2", "text": "3" },
                    { "value": "3", "text": "4" },
                    { "value": "4", "text": "5" },
                    { "value": "5", "text": "6" },
                    { "value": "6", "text": "7" },
                    { "value": "7", "text": "8" },
                    { "value": "8", "text": "9" },
                    { "value": "9", "text": "10" }
    ];
    
    var authorclassess= [{ "value": "0", "text": "教师" }, 
                        { "value": "1", "text": "教学管理人员" }];
    var years= [{ "value": "0", "text": "2013" }, 
                        { "value": "1", "text": "2014" }];
    var months= [{ "value": "0", "text": "1月" }, 
                { "value": "1", "text": "2月" },
                { "value": "2", "text": "3月" }, 
                { "value": "3", "text": "4月" }, 
                { "value": "4", "text": "5月" }, 
                { "value": "5", "text": "6月" }, 
                { "value": "6", "text": "7月" }, 
                { "value": "7", "text": "8月" }, 
                { "value": "8", "text": "9月" }, 
                { "value": "9", "text": "10月" }, 
                { "value": "10", "text": "11月" }, 
                { "value": "11", "text": "12月" }
    ];
    var classess= [{ "value": "0", "text": "核心刊物" }, 
                { "value": "1", "text": "普通刊物" }];
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
			queryParams = {"college":college};	
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
					editor: { type: 'validatebox',  }
				},
				{field:'tp_papertitle',title:'论文题目',sortable:true,width:120,
					editor: { type: 'validatebox',  }
				},
				{field:'tp_paperclasses',title:'论文类别',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:paperclassess ,
                         editable:false ,
						}
					}
				},
				{field:'tp_authors',title:'作者',sortable:true,width:120,
					editor: { type: 'validatebox',  }
				},
				{field:'tp_serial',title:'作者排序',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:serials,
                         editable:false ,
						}
					}
				},
				{field:'tp_authorclasses',title:'作者类别',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:authorclassess,
                         editable:false ,
						}
					}
				},
				{field:'tp_publication',title:'刊物名称 ',sortable:true,width:120,
					editor: { type: 'validatebox',  }
				},
				{field:'tp_year',title:'发表年份',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:years,
                         editable:false ,
						}
					}
				},
				{field:'tp_month',title:'发表时间',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:months,
                         editable:false ,
						}
					}
				},
				
				{field:'tp_classes',title:'刊物类别',sortable:true,width:120,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:classess,
                         editable:false ,
						}
					}
				},
				{field:'tp_comments',title:'审核意见',sortable:true,width:120,
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
					serialnumber = rowData.tp_serialnumber;
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
	
	function _getnullcount(){
		$.messager.confirm("操作警告", "提交后数据将不可修改！！", function(data){
			if(data){
				$.post("<%=basePath%>getteacherpaper", {isnull: 1,sort:'tp_serialnumber',order:'asc',college:college},
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
				serialnumber = row.tp_serialnumber + 1;
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
				serialnumber = row.tp_serialnumber;
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
					var tp_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							tp_id[i] = rows[i].tp_id;
						}						
					$.post("<%=basePath%>deleteteacherpaper", {seuids: tp_id.toString()},
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
		            url = '<%=basePath%>addteacherpaper';  
		        }  
		        if (updated.length > 0) {  
		        	url = '<%=basePath%>updateteacherpaper';   
		        }  
		        
		        rowstr = encodeURI(rowstr);
		        if(!collegeflag){
		        	college = encodeURI(college);
					collegeflag=true;	        	
		        }
		        
		        $.ajax({
		        	url: url,
		        	data: {rowdata:rowstr, serialnumber:serialnumber,tp_college:college},
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
			$("#ssearchDialog").attr("action","importteacherpaper?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
        }
		else{
			$("#ssearchDialog").attr("action","importteacherpaper?tableid="+tableid+"&college="+encodeURI(college));
		}
		$("#ttableid").val(tableid);
	}

	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid="+tableid;
	
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
		function myparser(s) {
			var ss = (s.split('/'));
			if(ss.length > 1)
				return ss[2] + "-" + ss[0] + "-" + ss[1];
			else return s;
		}
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="grid"></table>
	
	<div id="searchDialog" class="tip" icon="icon-save"
		style="padding: 50px; width: 300px; height: 150px;"><%-- ?tableid=<%=tableid%> --%>
	 <form id="ssearchDialog" name="formName" action=""  method="post" enctype="multipart/form-data">
  	 	<input type="hidden" id="ttableid" name="tableid">
  	 	<input type="file" name="myfile1"><br>
   	 	<input type="submit" value="导入">
   	 	<input name="" type="button" class="cancel" id="cancelDelete" value="取消"/>
  	 </form> 
	</div>
	
</body>
</html>







