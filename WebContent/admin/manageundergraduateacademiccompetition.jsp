<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[] cookies = request.getCookies();
String tableid = "";
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

<title>查看列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<!-- 过滤需要导入改js -->
<script type="text/javascript" src="<%=path%>/js/easyUI/datagrid-filter.js"></script>

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
    
   
    var level = [{ "value": "0", "text": "国际级" }, 
                 { "value": "1", "text": "国家级" },
                 { "value": "2", "text": "省部级" }];
    var personalorteam = [{ "value": "0", "text": "个人" }, 
                 { "value": "2", "text": "团队" }];
    
    
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
			queryParams = {"college":college, "tableid":tableid, "userid":userid};	
		}
		else{
			queryParams = {"tableid":tableid, "userid":userid};
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
				{field:'uac_collegename',title:'学院',sortable:true,width:100,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox', }
				},
				{field:'uac_competitionname',title:'竞赛名称',sortable:true,width:100,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox',}
				},
				{field:'uac_awardgrade',title:'奖项等级',sortable:true,width:100,
					editor: { type: 'validatebox',}
				},
				{field:'uac_prizelevel',title:'获奖级别',sortable:true,
					editor: { 
						type : 'combobox',  
                        options : {  
                        valueField: "text", textField: "text"  ,					
						 data:level,
                        editable:false ,
						}
					}
				},
				{field:'uac_personalorteam',title:'个人/团队',sortable:true,
					editor: { 
						type : 'combobox',  
                        options : {  
                        valueField: "text", textField: "text"  ,					
						 data:personalorteam,
                        editable:false ,
						}
					}
				},
				{field:'uac_studentname',title:'获奖学生姓名',sortable:true,width:120,
					editor: { type: 'validatebox',}
				},
				{field:'uac_studentnum',title:'获奖学生人数',sortable:true,width:100,
					editor: { 
						type: 'numberbox', 
						options: {
							min: 0,
							precision: 0//精确
						}
					}
				},
				{field:'uac_windate',title:'获奖时间（年、月）',sortable:true,
					editor: { type: 'validatebox',}
				},
				{field:'uac_comments',title:'审核意见',sortable:true,width:120,
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
					serialnumber = rowData.uac_serialnumber;
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
		
		
        $('.datagrid-header-check').find("input").css("display","none");
		
		$('.l-btn-icon.pagination-next').live("click",function(){
			//$('#grid').datagrid('reload'); 
		});
	}
	
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
			datagrid.datagrid('endEdit',doedit);
		}
		if(doedit == undefined){
			
			var row = $('#grid').datagrid('getSelected');
			var rowIndex;
			if(row!=null){
				rowIndex = $('#grid').datagrid('getRowIndex', row);
				rowIndex = rowIndex + 1;
				serialnumber = row.uac_serialnumber + 1;
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
				serialnumber = row.uac_serialnumber;
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
					var uac_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							uac_id[i] = rows[i].uac_id;
						}				
			       $.post("<%=basePath%>Sec_DeleteUndergraduateAcademicCompetitionServlet", {uacids: uac_id.toString()},
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
            datagrid.datagrid('unselectAll');  
            return;  
        }  

        var url = '';  
        if (inserted.length > 0) {  
            url = '<%=basePath%>Sec_AddUndergraduateAcademicCompetitionServlet';  
        }  
        if (updated.length > 0) {  
        	url = '<%=basePath%>Sec_UpdateUndergraduateAcademicCompetitionServlet';   
        }  
        
        rowstr = encodeURI(rowstr);
        if(!collegeflag){
        	college = encodeURI(college);
			collegeflag=true;	        	
        }
        
        
        $.ajax({
        	url: url,
        	data: {rowdata:rowstr, serialnumber:serialnumber, uac_college:college},
        	dataType: 'json',
        	success : function(r){
        		if(r=="1"){
        			$('#grid').datagrid('acceptChanges');
        			$.messager.alert('提示','操作成功','info');
        			doedit = undefined;
        			$('#grid').datagrid('reload');       			
        		}else{
        			$('#grid').datagrid('beginEdit',doedit);
        			$.messager.alert('错误', "操作失败", 'error');
        		}
        		$('#grid').datagrid('unselectAll');
        	}
        	
        });
	   } 
	});
        
	};
	
	function _getnullcount(){
		$.messager.confirm("操作警告", "提交后数据将不可修改！！", function(data){
			if(data){
				$.post("<%=basePath%>Sec_GetUndergraduateAcademicCompetitionServlet", {isnull: 1,sort:'uac_serialnumber',order:'asc',college:college},
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
						$.messager.show({ 
						title : '提示', 
						msg : '提交失败' 
					}); 
					$('#grid').datagrid('reload'); 
					}
				}, "text");
		}
	 });
	}
	//导入
	function _importdata(){

				$("#searchDialog").show();
				$('#ssearchDialog').show();
				if(!collegeflag){
					$("#ssearchDialog").attr("action","Sec_ImportUndergraduateAcademicCompetitionServlet?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
		        }
				else{
					$("#ssearchDialog").attr("action","Sec_ImportUndergraduateAcademicCompetitionServlet?tableid="+tableid+"&college="+encodeURI(college));
				}
				$("#ttableid").val(tableid);
	}
	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid=" + tableid;

	}
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
    <h2>附表6-2-1-1   2014-2015学年本科生参加学科竞赛情况（省部级及以上）</h2>
	<table id="grid" class = "easyui-datagrid"></table>
	
	<div class="tip" id="searchDialog"  icon="icon-save"
		style="padding: 50px; width: 300px; height: 150px;"><%-- ?tableid=<%=tableid%> --%>
	 <form id="ssearchDialog" name="formName" action=""  method="post" enctype="multipart/form-data">
  	 	<input type="hidden" id="ttableid" name="tableid">
  	 	<input type="hidden" id="ccollege" name="college">
  	 	<input type="file" name="myfile1"><br>
   	 	<input type="submit" value="导入">
   	 	<input name="" type="button" class="cancel" id="cancelDelete" value="取消"/>
  	 </form> 
	</div>
</body>
</html>


