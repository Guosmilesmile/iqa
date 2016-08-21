<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>全校性实际使用的教室列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<!-- 过滤需要导入改js -->

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
			queryParams = {"sauc_college":college};	
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
			url: '<%=basePath%>Sec_GetSchActUseClassroom',
			sortName: 'sauc_deadline,sauc_region,sauc_serialnumber',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			pageSize: 50,//每页显示的记录条数，默认为10  
	        pageList: [5,10,15,20,25,50,100],//可以设置每页记录条数的列表
	        onLoadSuccess: onLoadSuccess,
	        rowStyler: function(index,row){
				if (row.sauc_site=="小计" || row.sauc_region=="合计"){
					return 'background-color:#6293BB;color:#fff;font-weight:bold;';
				}
			},
			frozenColumns:[[
			    {field: 'ck', checkbox: true},          
			]],
			
			columns: [[
			    {field:'sauc_id',title:'ID',sortable:true,width:80,rowspan:2},
				{field:'sauc_region',title:'校区',sortable:true,width:150,rowspan:2,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox'}
				},
				{field:'sauc_site',title:'地点',sortable:true,width:150,rowspan:2,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'validatebox'}
				},
			    {title:'小计',colspan:2},
			    {title:'多媒体教室',colspan:2},
				{title:'普通教室',colspan:2},
			    {title:'计算机房',colspan:2},
				{title:'语音教室',colspan:2},
			    {title:'其他类型',colspan:2},
			    {field:'sauc_comments',title:'审核意见',sortable:true,width:120,rowspan:2}
			    ],[
				
				{field:'sauc_subtotalroom',title:'间数',sortable:true,width:60},
				{field:'sauc_subtotalseat',title:'座位数',sortable:true,width:60},
				{field:'sauc_multiroom',title:'间数',sortable:true,width:55,
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_multiseat',title:'座位数',sortable:true,width:55,
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_regularroom',title:'间数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_regularseat',title:'座位数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_computerroom',title:'间数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_computerseat',title:'座位数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_voiceroom',title:'间数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_voiceseat',title:'座位数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_otherroom',title:'间数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
				},
				{field:'sauc_otherseat',title:'座位数',sortable:true,width:55,
					//如果要实现编辑功能，需要添加下面的属性
					editor: {
						 type: 'numberbox',
					options: {
						min: 0,
						max: 999999999,
						precision: 0,//精确
						}
					}
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
					serialnumber = rowData.sauc_serialnumber;
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
				serialnumber = row.sauc_serialnumber + 1;
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
				serialnumber = row.sauc_serialnumber;
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
					var sauc_id = []; 
					var sauc_region = [];
					var j = 0;
					for(var i = 0; i < rows.length; ++i)
					{
						sauc_id[i] = rows[i].sauc_id;
						if(sauc_region.indexOf(rows[i].sauc_region) == -1)
						{
							sauc_region[j] = rows[i].sauc_region;
							j++;
						}
					}						
					$.post("<%=basePath%>Sec_DeleteSchActUseClassroom", {saucids: sauc_id.toString(),sauc_college:college,saucregions:sauc_region.toString()},
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
	                url = '<%=basePath%>Sec_AddSchActUseClassroom';
	            }
	            if (updated.length > 0) {
	                url = '<%=basePath%>Sec_UpdateSchActUseClassroom';
	            }
		        
		        rowstr = encodeURI(rowstr);
		        if(!collegeflag){
		        	college = encodeURI(college);
					collegeflag=true;	        	
		        }
		        
		        $.ajax({
		        	url: url,
		        	data: {rowdata:rowstr, serialnumber:serialnumber,sauc_college:college},
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
				$.post("<%=basePath%>Sec_GetSchActUseClassroom", {isnull: 1,sort:'sauc_serialnumber',order:'asc',sauc_college:college},
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
					$("#ssearchDialog").attr("action","Sec_ImportSchActUseClassroom?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
		        }
				else{
					$("#ssearchDialog").attr("action","Sec_ImportSchActUseClassroom?tableid="+tableid+"&college="+encodeURI(college));
				}
				$("#ttableid").val(tableid);
	}
	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid=" + tableid;

	}
	//var subtotalroom = 0, subtotalseat = 0, multiroom = 0, multiseat = 0, regularroom = 0, regularseat = 0, computerroom = 0, computerseat = 0, voiceroom = 0, voiceseat = 0, otherroom = 0, otherseat = 0;
	function onLoadSuccess(data) {
		//将校区相同的单元格合并
		var rows = data.rows;
		var tempRegion = "";//存储校区字符串
		var tempSite = 0;//存储小计下标
		var tempI = 0;//第一个不同校区下标
		var tempSpan = 0;//要合并的单元格行数
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].sauc_region == "合计")
				continue;
			// 			rows[i].sauc_subtotalroom=rows[i].sauc_multiroom+rows[i].sauc_regularroom+rows[i].sauc_computerroom+rows[i].sauc_voiceroom+rows[i].sauc_otherroom;
			// 			rows[i].sauc_subtotalseat=rows[i].sauc_multiseat+rows[i].sauc_regularseat+rows[i].sauc_computerseat+rows[i].sauc_voiceseat+rows[i].sauc_otherseat;
			// 			$('#grid').datagrid('updateRow',{index:i,row:rows[i]});
			//将小计安排在校区的最低端
			if (tempRegion != rows[i].sauc_region) {
				if (tempRegion != "") {
					if (tempSite != 0 && i - 1 != tempSite) {
						var subtotal = rows[tempSite];
						var temp = rows[i - 1];
						$('#grid').datagrid('getData').rows[i - 1] = subtotal;
						$('#grid').datagrid('getData').rows[tempSite] = temp;
						$('#grid').datagrid('refreshRow', i - 1);
						$('#grid').datagrid('refreshRow', tempSite);
					}
					// 					tempSpan++;
					// 					$('#grid').datagrid('insertRow', {
					// 						index : i,
					// 						row : {
					// 							"sauc_id" : "",
					// 							"sauc_region" : tempRegion,
					// 							"sauc_site" : "小计",
					// 							"sauc_subtotalroom" : subtotalroom,
					// 							"sauc_subtotalseat" : subtotalseat,
					// 							"sauc_multiroom" : multiroom,
					// 							"sauc_multiseat" : multiseat,
					// 							"sauc_regularroom" : regularroom,
					// 							"sauc_regularseat" : regularseat,
					// 							"sauc_computerroom" : computerroom,
					// 							"sauc_computerseat" : computerseat,
					// 							"sauc_voiceroom" : voiceroom,
					// 							"sauc_voiceseat" : voiceseat,
					// 							"sauc_otherroom" : otherroom,
					// 							"sauc_otherseat" : otherseat,
					// 							"sauc_comments" : ""
					// 						}
					// 					});
					$(this).datagrid('mergeCells', {
						index : tempI,
						field : 'sauc_region',
						rowspan : tempSpan
					});
					// 					i++;
				}
				tempRegion = rows[i].sauc_region;
				tempI = i;
				tempSpan = 0;
				// 				initialData();
			}
			tempSpan++;
			if (rows[i].sauc_site == "小计")
				tempSite = i;
			// 			addData(rows[i]);
		}
		if (rows.length > 0) {
			// 			tempSpan++;
			// 			$('#grid').datagrid('insertRow', {
			// 				index : rows.length,
			// 				row : {
			// 					"sauc_id" : "",
			// 					"sauc_region" : tempRegion,
			// 					"sauc_site" : "小计",
			// 					"sauc_subtotalroom" : subtotalroom,
			// 					"sauc_subtotalseat" : subtotalseat,
			// 					"sauc_multiroom" : multiroom,
			// 					"sauc_multiseat" : multiseat,
			// 					"sauc_regularroom" : regularroom,
			// 					"sauc_regularseat" : regularseat,
			// 					"sauc_computerroom" : computerroom,
			// 					"sauc_computerseat" : computerseat,
			// 					"sauc_voiceroom" : voiceroom,
			// 					"sauc_voiceseat" : voiceseat,
			// 					"sauc_otherroom" : otherroom,
			// 					"sauc_otherseat" : otherseat,
			// 					"sauc_comments" : ""
			// 				}
			// 			});
			if (tempSite!=0 &&i - 1 != tempSite) {
				var subtotal = rows[tempSite];
				var temp = rows[i - 1];
				$('#grid').datagrid('getData').rows[i - 1] = subtotal;
				$('#grid').datagrid('getData').rows[tempSite] = temp;
				$('#grid').datagrid('refreshRow', i - 1);
				$('#grid').datagrid('refreshRow', tempSite);
			}
			$(this).datagrid('mergeCells', {
				index : tempI,
				field : 'sauc_region',
				rowspan : tempSpan
			});
		}
		// 		initialData();
		//var deadline=rows[0].sauc_deadline;
		// 		for (var i = 0; i < rows.length; i++) {
		// 			if (rows[i].sauc_site == "小计") {
		// 				addData(rows[i]);
		// 			}
		// 		}
		// 		if(rows.length > 0)
		// 		$('#grid').datagrid('insertRow', {
		// 			index : 0,
		// 			row : {
		// 				"sauc_id" : "",
		// 				"sauc_region" : "合计",
		// 				"sauc_site" : "",
		// 				"sauc_subtotalroom" : subtotalroom,
		// 				"sauc_subtotalseat" : subtotalseat,
		// 				"sauc_multiroom" : multiroom,
		// 				"sauc_multiseat" : multiseat,
		// 				"sauc_regularroom" : regularroom,
		// 				"sauc_regularseat" : regularseat,
		// 				"sauc_computerroom" : computerroom,
		// 				"sauc_computerseat" : computerseat,
		// 				"sauc_voiceroom" : voiceroom,
		// 				"sauc_voiceseat" : voiceseat,
		// 				"sauc_otherroom" : otherroom,
		// 				"sauc_otherseat" : otherseat,
		// 				"sauc_comments" : ""
		// 			}
		// 		});
		doedit = undefined;
	}
	// 	function initialData() {
	// 		subtotalroom = 0;
	// 		subtotalseat = 0;
	// 		multiroom = 0;
	// 		multiseat = 0;
	// 		regularroom = 0;
	// 		regularseat = 0;
	// 		computerroom = 0;
	// 		computerseat = 0;
	// 		voiceroom = 0;
	// 		voiceseat = 0;
	// 		otherroom = 0;
	// 		otherseat = 0;
	// 	}
	// 	function addData(row) {
	// 		subtotalroom += row.sauc_subtotalroom;
	// 		subtotalseat += row.sauc_subtotalseat;
	// 		multiroom += row.sauc_multiroom;
	// 		multiseat += row.sauc_multiseat;
	// 		regularroom += row.sauc_regularroom;
	// 		regularseat += row.sauc_regularseat;
	// 		computerroom += row.sauc_computerroom;
	// 		computerseat += row.sauc_computerseat;
	// 		voiceroom += row.sauc_voiceroom;
	// 		voiceseat += row.sauc_voiceseat;
	// 		otherroom += row.sauc_otherroom;
	// 		otherseat += row.sauc_otherseat;
	// 	}
</script>

</head>

<body bgcolor="#DDF3FF" class="h2">
<h2>附表2-3-1全校性实际使用的教室（时点）</h2>
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