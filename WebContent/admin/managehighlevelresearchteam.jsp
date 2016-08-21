<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<base href="<%=basePath%>">
<title> 高层次研究团队 (时点)列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<script type='text/javascript' src='/IQA/dwr/interface/TeacherDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>

<style type="text/css">
#searchDialog {
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
    var hlrt_type = [{ "value": "1", "text": "国家自然科学基金委创新研究群体" }, 
                             { "value": "2", "text": "教育部创新团队" }, 
                             { "value": "3", "text": "省级高层次研究团队" }];
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
			queryParams = {"hlrt_college":college};	
		}
		else{
			queryParams = {};
		}
		$('#cancelDelete').click(function(){
	 		$('.tip').hide();
	 	});
		getData(queryParams);
	});
    
    function getTeacher(){
    	var teacherName;
    	DWREngine.setAsync(false);
		TeacherDao.getAllTeacher(function callback(data){ 
			teacherName = data;
		});
		return teacherName;
    }
    
    function getCookie(objName){//获取指定名称的cookie的值 
    	var arrStr = document.cookie.split("; "); 
    	for(var i = 0;i < arrStr.length;i ++){ 
    	var temp = arrStr[i].split("="); 
    	if(temp[0] == objName) return unescape(temp[1]); 
    	} 
    };
	function getData(queryParams){
		$('#grid').datagrid({
			filterBtnIconCls:'icon-filter',
			url: '<%=basePath%>Sec_GetHighLevelResearchTeam',
			sortName: 'hlrt_serialnumber',
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
			    
				{field:'hlrt_id',title:'ID',sortable:true,width:80},
				{field:'hlrt_researchdirection',title:'研究方向',sortable:true,width:120,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox'}
				},
				{field:'hlrt_head',title:'负责人',sortable:true,width:120,
					editor : {  
                        type : 'combobox',  
                        options : {  
                            valueField : 'teachername',  
                            textField : 'teachername',  
                            data : getTeacher(), 
                            onChange : function (newValue, oldValue) {  
                               var tt = newValue.split("#");
                               var row = $('#grid').datagrid('getSelected');  
                               var rindex = $('#grid').datagrid('getRowIndex', row);  
                               var ed1 = $('#grid').datagrid('getEditor', { index: rindex, field: 'hlrt_headnumber' });
                               var ed2 = $('#grid').datagrid('getEditor', { index: rindex, field: 'hlrt_head' });
                               if(tt.length > 1)
                            	   $(ed1.target).val(tt[1]);
                               $(ed2.target).combobox('setValue', tt[0]);
                            } 
                        }  
                    }  
				},
				{field:'hlrt_headnumber',title:'负责人工号',sortable:true,width:120,
					editor: { type: 'validatebox'  }
				},
				{field:'hlrt_type',title:'类型',sortable:true,width:215,
					editor: 
					{ 
						 type : 'combobox',  
                         options : {  
                         valueField: "text", textField: "text"  ,
						 data:hlrt_type,
                         editable:false ,
						}
					}
				},
				{field:'hlrt_acquisitiondate',title:'获得时间',sortable:true,width:120,formatter:myformatter,
					editor: { type: 'datebox'  }
				},
				{field:'hlrt_comments',title:'审核意见',sortable:true,width:120
				}
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
					doedit=rowIndex;
		        	$('#grid').datagrid('selectRow', doedit);
		        	$('#grid').datagrid('beginEdit',rowIndex);
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
		
		
		/**************过滤
			$('#grid').datagrid('enableFilter', [{
				field:'sm_id',
				type:'text',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'sm_name',
				type:'text',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'sm_number',
				type:'text',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'sm_class',
				type:'combobox',
				options:{
					panelHeight:'auto',
					data:[{value:'',text:'All'},
					      {value:"国家特色专业",text:"国家特色专业"},
					      {value:'省品牌专业',text:"省品牌专业"},
					      {value:'省示范专业',text:"省示范专业"},],
					onChange:function(value){
						if (value == ''){
							$('#grid').datagrid('removeFilterRule', 'sm_class');
						} else {
							$('#grid').datagrid('addFilterRule', {
								field: 'sm_class',
								op: 'equal',
								value: value
							});
						}
						$('#grid').datagrid('doFilter');
					}
				}
			},{
				field:'c_startyear',
				type:'numberbox',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'s_startyear',
				type:'numberbox',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'p_startyear',
				type:'numberbox',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'respon_person',
				type:'text',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			},{
				field:'sm_college',
				type:'text',
				options:{precision:1},
				op:['equal','notequal','less','greater','lessorequal']
			}]);
			*/
			//***********************************************过滤结束--------------------
			
		
		
		
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
				serialnumber = row.hlrt_serialnumber + 1;
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
			doedit = rowIndex;
			$('#grid').datagrid('selectRow', doedit);
			$('#grid').datagrid('beginEdit',rowIndex);
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
				serialnumber = row.hlrt_serialnumber;
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
					var hlrt_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							hlrt_id[i] = rows[i].hlrt_id;
						}						
					$.post("<%=basePath%>Sec_DeleteHighLevelResearchTeam", {hlrtids: hlrt_id.toString()},
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
				for(var i = 0; i < rows.length; i++)
				{
					rows[i].hlrt_acquisitiondate = myparser(rows[i].hlrt_acquisitiondate);
				}
				var rowstr = JSON.stringify(rows);
				
				if (inserted.length < 1 && updated.length < 1 && deleted.length<1) {  
		            editRow = undefined;  
		            $('#grid').datagrid('unselectAll');  
		            return;  
		        }  

		        var url = '';  
		        if (inserted.length > 0) {  
		            url = '<%=basePath%>Sec_AddHighLevelResearchTeam';  
		        }  
		        if (updated.length > 0) {  
		        	url = '<%=basePath%>Sec_UpdateHighLevelResearchTeam';   
		        }  
		        
		        rowstr = encodeURI(rowstr);
		        if(!collegeflag){
		        	college = encodeURI(college);
					collegeflag=true;	        	
		        }
		        $.ajax({
		        	url: url,
		        	data: {rowdata:rowstr, serialnumber:serialnumber,hlrt_college:college},
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
				$.post("<%=basePath%>Sec_GetHighLevelResearchTeam", {isnull: 1,sort:'hlrt_serialnumber',order:'asc',hlrt_college:college},
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
					$("#ssearchDialog").attr("action","Sec_ImportHighLevelResearchTeam?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
		        }
				else{
					$("#ssearchDialog").attr("action","Sec_ImportHighLevelResearchTeam?tableid="+tableid+"&college="+encodeURI(college));
				}
				$("#ttableid").val(tableid);
	}
	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid=" + tableid;

	}

	//更改datebox的日期格式
	function myformatter(value, row, index) {
		if(value != null && value != ""){
    		var date = new Date(value);
            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();}
	}
	function myparser(s) {
		var ss = (s.split('/'));
		if(ss.length > 1)
			return ss[2] + "-" + ss[0] + "-" + ss[1];
		else return s;
	}
</script>
</head>
<body bgcolor="#DDF3FF" class="h2">
<h2>表3-4-2高层次研究团队（时点）</h2>
	<table id="grid"></table>
	<div class="tip" id="searchDialog" icon="icon-save"
		style="padding: 50px; width: 300px; height: 150px;">
		<%-- ?tableid=<%=tableid%> --%>
		<form id="ssearchDialog" name="formName" action="" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="ttableid" name="tableid"> <input
				type="file" name="myfile1"><br> <input type="submit"
				value="导入">
		 <input name="" type="button"  class="cancel" id="cancelDelete" value="取消" />
		</form>
	</div>

</body>
</html>