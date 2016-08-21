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
<script type='text/javascript' src='/IQA/dwr/interface/TeacherDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>

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
    var coursecategory = [{ "value": "0", "text": "公共必修课" }, 
                 { "value": "1", "text": "公共选修课" },
                 { "value": "2", "text": "专业必修课" },
                 { "value": "3", "text": "专业选修课" },
                 { "value": "4", "text": "其他教学环节" }];
    
    var campus = [{ "value": "0", "text": "思明" }, 
                  { "value": "1", "text": "翔安" }];
    
    var evaluationmode= [{ "value": "0", "text": "考查" }, 
                         { "value": "1", "text": "考试" }];
    
    var isoutsideteacher= [{ "value": "0", "text": "否" }, 
                           { "value": "1", "text": "是" }];
    
    var isenglish = [{ "value": "0", "text": "双语授课" }, 
                     { "value": "1", "text": "全英文授课" },
                     { "value": "2", "text": "否" }];
    
    var teachmaterial= [{ "value": "0", "text": "选用" }, 
                        { "value": "1", "text": "自编" }];
    
    var materialspecies = [{ "value": "0", "text": "0" }, 
                        { "value": "1", "text": "1" },
                        { "value": "2", "text": "2" },
                        { "value": "3", "text": "3" },
                        { "value": "4", "text": "4" },
                        { "value": "5", "text": "5" },
                        { "value": "6", "text": "6" }];
    
    var ismagong = [{ "value": "0", "text": "否"},
                    { "value": "1", "text": "马克思主义哲学"},
                    { "value": "2", "text": "马克思主义哲学史"},
                    { "value": "3", "text": "中国哲学史"},
                    { "value": "4", "text": "西方哲学史"},
                    { "value": "5", "text": "伦理学"},
                    { "value": "6", "text": "马克思恩格斯列宁哲学经典著作导读"},
                    { "value": "7", "text": "文学理论"},
                    { "value": "8", "text": "新闻学概论"},
                    { "value": "9", "text": "西方经济学"},
                    { "value": "10", "text": "《资本论》导读"},
                    { "value": "11", "text": "马克思主义政治经济学概论"},
                    { "value": "12", "text": "马克思主义经济学说史"},
                    { "value": "13", "text": "世界经济概论"},
                    { "value": "14", "text": "史学概论"},
                    { "value": "15", "text": "中国近代史"},
                    { "value": "16", "text": "中华人民共和国史"},
                    { "value": "17", "text": "世界现代史"},
                    { "value": "18", "text": "马克思恩格斯列宁历史理论经典著作导读"},
                    { "value": "19", "text": "政治学概论"},
                    { "value": "20", "text": "中国政治思想史"},
                    { "value": "21", "text": "西方政治思想史"},
                    { "value": "22", "text": "马克思主义发展史"},
                    { "value": "23", "text": "科学社会主义概论"},
                    { "value": "24", "text": "国际共产主义运动史"},
                    { "value": "25", "text": " 社会学概论"},
                    { "value": "26", "text": " 法理学"},
                    { "value": "27", "text": "宪法学"}];

    
    var isstandard = [{ "value": "0", "text": "否" }, 
                     { "value": "1", "text": "是" }];
    
    var foreignmaterial = [{ "value": "0", "text": "无" }, 
                           { "value": "1", "text": "境外原版" },
                           { "value": "2", "text": "国内翻译版" },
                           { "value": "3", "text": "自行影印版" }];
    
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
			url: '<%=basePath%>Sec_GetStartClassServlet',
			sortName: 'sc_serialnumber',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			frozenColumns:[[
			    {field: 'ck', checkbox: true},          
			]],
			
			columns: [[
			        
				{field:'sc_id',title:'ID',sortable:true,rowspan:2,},
				{field:'sc_number',title:'开课号',sortable:true,rowspan:2,					
					editor: { type: 'validatebox',}
				},
				{field:'sc_coursenum',title:'课程号',sortable:true,rowspan:2,				
					editor: { type: 'validatebox', }
				},
				{field:'sc_coursecategory',title:'课程类别',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:coursecategory,
                            editable:false ,
   						}}
				},
				{field:'sc_campus',title:'校区',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:campus,
                            editable:false ,
   						}
					}
				},
				{field:'sc_totalhour',title:'总学时',sortable:true,rowspan:2,
					editor: { type: 'numberbox',  
						options: {
							min: 0,
							precision: 0//精确
						}			
				    }
				},
				{field:'sc_totalcredit',title:'学分',sortable:true,rowspan:2,
					editor: { type: 'numberbox',  
						options: {
							min: 0,
							precision: 1//精确
						}			
				    }
				},
				{field:'sc_evaluationmode',title:'考核方式',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:evaluationmode,
                            editable:false ,
   						}
					}
				},
				{field:'sc_teachobject',title:'授课对象',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_arrange',title:'安排情况',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_yearandsemester',title:'学年学期',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_collegename',title:'授课院',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_coursename',title:'课程名称',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_teacher',title:'授课教师',sortable:true,rowspan:2,
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
                               var ed1 = $('#grid').datagrid('getEditor', { index: rindex, field: 'sc_teachernumber' });
                               var ed2 = $('#grid').datagrid('getEditor', { index: rindex, field: 'sc_teacher' });
                               if(tt.length > 1)
                            	   $(ed1.target).val(tt[1]);
                               $(ed2.target).combobox('setValue', tt[0]);
                            } 
                        }  
                    }  
				},
				{field:'sc_isoutsideteacher',title:'是否校外专家',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:isoutsideteacher,
                            editable:false ,
   						}
					}
				},
				
				{field:'sc_teachernumber',title:'授课教师工号',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_teachertitle',title:'职称',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_studentnum',title:'本科学生数',sortable:true,rowspan:2,
					editor: { 
                    	type: 'numberbox',  
						options: {
							min: 0,
							precision: 0//精确
						}		
                    }
				},
				{field:'sc_isenglish',title:'英语授课情况',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:isenglish,
                            editable:false ,
   						}
					}
				},
				{field:'sc_website',title:'网络教学平台网址',sortable:true,rowspan:2,
					editor: { type: 'validatebox',}
				},
				{field:'sc_teachmaterial',title:'教材使用情况',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:teachmaterial,
                            editable:false ,
   						}
					}
				},
				{field:'sc_materialspecies',title:'使用教材种数',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:materialspecies,
                            editable:false ,
   						}
					}
				},
				{field:'sc_ismagong',title:'是否使用马工教材',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:ismagong,
                            editable:false ,
   						}
					}
				},
				{field:'sc_isstandard',title:'是否使用规划教材',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:isstandard,
                            editable:false ,
   						}
					}
				},
				{field:'sc_foreignmaterial',title:'境外教材使用情况',sortable:true,rowspan:2,
					editor: { 
						type : 'combobox',  
                        options : {  
                            valueField: "text", textField: "text"  ,					
   						    data:foreignmaterial,
                            editable:false ,
   						}
					}
				},	
				{title:'使用境外教材填写区域',colspan:5},
				
				{field:'sc_comments',title:'审核意见',sortable:true,width:120,rowspan:2,
				},
			  ],[
                   {field:'sc_m_name',title:'教材名称',sortable:true,
	                    editor: { type: 'validatebox',}
                   },
                   {field:'sc_m_auther',title:'作者',sortable:true,
	                    editor: { type: 'validatebox',}
                   },
                   {field:'sc_m_publisher',title:'出版社',sortable:true,
	                    editor: { type: 'validatebox',}
                   },
                   {field:'sc_m_country',title:'出版社所属国家',sortable:true,
	                    editor: { type: 'validatebox',}
                   },
                   {field:'sc_m_publishyear',title:'出版年',sortable:true,
	                    editor: { 
	                    	type: 'numberbox',  
							options: {
								min: 0,
								precision: 0//精确
							}		
	                    }
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
					serialnumber = rowData.sc_serialnumber;
					doedit=rowIndex;
					$('#grid').datagrid('selectRow', doedit);
		        	$('#grid').datagrid('beginEdit',rowIndex);
		        	//这边的rowIndex和行号是不一样的值，0------开始的
		        	
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
			var rowIndex = $('#grid').datagrid('getRowIndex', row);
			if(row!=null){
				rowIndex = $('#grid').datagrid('getRowIndex', row);
				rowIndex = rowIndex + 1;
				serialnumber = row.sc_serialnumber + 1;
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
				serialnumber = row.sc_serialnumber;
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
					var sc_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							sc_id[i] = rows[i].sc_id;
						}				
			       $.post("<%=basePath%>Sec_DeleteStartClassServlet", {scids: sc_id.toString()},
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
            url = '<%=basePath%>Sec_AddStartClassServlet';  
        }  
        if (updated.length > 0) {  
        	url = '<%=basePath%>Sec_UpdateStartClassServlet';   
        }  
        
        rowstr = encodeURI(rowstr);
        if(!collegeflag){
        	college = encodeURI(college);
			collegeflag=true;	        	
        }
        
        $.ajax({
        	url: url,
        	data: {rowdata:rowstr, serialnumber:serialnumber, sc_college:college},
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
				$.post("<%=basePath%>Sec_GetStartClassServlet", {isnull: 1,sort:'sc_serialnumber',order:'asc',college:college},
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
					$("#ssearchDialog").attr("action","Sec_ImportStartClassServlet?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
		        }
				else{
					$("#ssearchDialog").attr("action","Sec_ImportStartClassServlet?tableid="+tableid+"&college="+encodeURI(college));
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
    <h2>附表5-1-1-1 开课情况表</h2>
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


