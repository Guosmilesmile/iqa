<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>专任教师信息列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<script type='text/javascript' src='/IQA/dwr/interface/DanWeiDao.js'></script>
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
    var gender = [{ "value": "0", "text": "男" }, 
                   { "value": "1", "text": "女" }, 
                   { "value": "2", "text": "未说明" }];
    
    var workstate = [{ "value": "0", "text": "当年离职" }, 
                     { "value": "1", "text": "在职" }];
    
    var education = [{ "value": "0", "text": "博士研究生" }, 
                         { "value": "1", "text": "硕士研究生" }, 
                         { "value": "2", "text": "大学本科" },
                         { "value": "3", "text": "专科生及以下" }];
    var degree = [{ "value": "0", "text": "博士" }, 
                            { "value": "1", "text": "硕士" }, 
                            { "value": "2", "text": "学士" },
                            { "value": "3", "text": "无学位" }];
	var educationsource = [{ "value": "0", "text": "本校" }, 
                  { "value": "1", "text": "外校（境内）" }, 
                  { "value": "2", "text": "外校（境外）" }];
    
    var professionaltitle = [{ "value": "0", "text": "副教授" }, 
                            { "value": "1", "text": "讲师" }, 
                            { "value": "2", "text": "教授" },
                            { "value": "3", "text": "其他初级" }, 
                            { "value": "4", "text": "其他副高级" }, 
                            { "value": "5", "text": "其他正高级" },
                            { "value": "6", "text": "其他中级" }, 
                            { "value": "7", "text": "未评级" }, 
                            { "value": "8", "text": "助教" }];
    
    var subjectcategory = [{"value":"1","text":"安全科学与工程"},
                           {"value":"2","text":"兵器科学与技术"},
                           {"value":"3","text":"材料科学与工程"},
                           {"value":"4","text":"草学"},
                           {"value":"5","text":"测绘科学与技术"},
                           {"value":"6","text":"城乡规划学"},
                           {"value":"7","text":"畜牧学"},
                           {"value":"8","text":"船舶与海洋工程"},
                           {"value":"9","text":"大气科学"},
                           {"value":"10","text":"地理学"},
                           {"value":"11","text":"地球物理学"},
                           {"value":"12","text":"地质学"},
                           {"value":"13","text":"地质资源与地质工程"},
                           {"value":"14","text":"电气工程"},
                           {"value":"15","text":"电子科学与技术"},
                           {"value":"16","text":"动力工程及工程热物理"},
                           {"value":"17","text":"法学"},
                           {"value":"18","text":"纺织科学与工程"},
                           {"value":"19","text":"风景园林学"},
                           {"value":"20","text":"工商管理"},
                           {"value":"21","text":"公安技术"},
                           {"value":"22","text":"公安学"},
                           {"value":"23","text":"公共管理"},
                           {"value":"24","text":"公共卫生与预防医学"},
                           {"value":"25","text":"管理科学与工程"},
                           {"value":"26","text":"光学工程"},
                           {"value":"27","text":"海洋科学"},
                           {"value":"28","text":"航空宇航科学与技术"},
                           {"value":"29","text":"核科学与技术"},
                           {"value":"30","text":"护理学"},
                           {"value":"31","text":"化学"},
                           {"value":"32","text":"化学工程与技术"},
                           {"value":"33","text":"环境科学与工程"},
                           {"value":"34","text":"机械工程"},
                           {"value":"35","text":"基础医学"},
                           {"value":"36","text":"计算机科学与技术"},
                           {"value":"37","text":"建筑学"},
                           {"value":"38","text":"交通运输工程"},
                           {"value":"39","text":"教育学"},
                           {"value":"40","text":"军队政治工作学"},
                           {"value":"41","text":"军队指挥学"},
                           {"value":"42","text":"军事后勤学"},
                           {"value":"43","text":"军事思想及军事历史"},
                           {"value":"44","text":"军事训练学"},
                           {"value":"45","text":"军事装备学"},
                           {"value":"46","text":"军制学"},
                           {"value":"47","text":"考古学"},
                           {"value":"48","text":"科学技术史"},
                           {"value":"49","text":"控制科学与工程"},
                           {"value":"50","text":"口腔医学"},
                           {"value":"51","text":"矿业工程"},
                           {"value":"52","text":"理论经济学"},
                           {"value":"53","text":"力学"},
                           {"value":"54","text":"林学"},
                           {"value":"55","text":"林业工程"},
                           {"value":"56","text":"临床医学"},
                           {"value":"57","text":"马克思主义理论"},
                           {"value":"58","text":"美术学"},
                           {"value":"59","text":"民族学"},
                           {"value":"60","text":"农林经济管理"},
                           {"value":"61","text":"农业工程"},
                           {"value":"62","text":"农业资源与环境"},
                           {"value":"63","text":"轻工技术与工程"},
                           {"value":"64","text":"软件工程"},
                           {"value":"65","text":"设计学"},
                           {"value":"66","text":"社会学"},
                           {"value":"67","text":"生态学"},
                           {"value":"68","text":"生物工程"},
                           {"value":"69","text":"生物学"},
                           {"value":"70","text":"生物医学工程"},
                           {"value":"71","text":"石油与天然气工程"},
                           {"value":"72","text":"食品科学与工程"},
                           {"value":"73","text":"世界史"},
                           {"value":"74","text":"兽医学"},
                           {"value":"75","text":"数学"},
                           {"value":"76","text":"水产"},
                           {"value":"77","text":"水利工程"},
                           {"value":"78","text":"特种医学"},
                           {"value":"79","text":"体育学"},
                           {"value":"80","text":"天文学"},
                           {"value":"81","text":"统计学"},
                           {"value":"82","text":"图书情报与档案管理"},
                           {"value":"83","text":"土木工程"},
                           {"value":"84","text":"外国语言文学"},
                           {"value":"85","text":"物理学"},
                           {"value":"86","text":"戏剧与影视学"},
                           {"value":"87","text":"系统科学"},
                           {"value":"88","text":"心理学"},
                           {"value":"89","text":"新闻传播学"},
                           {"value":"90","text":"信息与通信工程"},
                           {"value":"91","text":"药学"},
                           {"value":"92","text":"冶金工程"},
                           {"value":"93","text":"医学技术"},
                           {"value":"94","text":"仪器科学与技术"},
                           {"value":"95","text":"艺术学理论"},
                           {"value":"96","text":"音乐与舞蹈学"},
                           {"value":"97","text":"应用经济学"},
                           {"value":"98","text":"园艺学"},
                           {"value":"99","text":"战略学"},
                           {"value":"100","text":"战术学"},
                           {"value":"101","text":"战役学"},
                           {"value":"102","text":"哲学"},
                           {"value":"103","text":"政治学"},
                           {"value":"104","text":"植物保护"},
                           {"value":"105","text":"中国史"},
                           {"value":"106","text":"中国语言文学"},
                           {"value":"107","text":"中西医结合"},
                           {"value":"108","text":"中药学"},
                           {"value":"109","text":"中医学"},
                           {"value":"110","text":"作物学"},
                           {"value":"111","text":"金融（专业学位）"},
                           {"value":"112","text":"应用统计（专业学位）"},
                           {"value":"113","text":"税务（专业学位）"},
                           {"value":"114","text":"国际商务（专业学位）"},
                           {"value":"115","text":"保险（专业学位）"},
                           {"value":"116","text":"资产评估（专业学位）"},
                           {"value":"117","text":"审计（专业学位）"},
                           {"value":"118","text":"法律（专业学位）"},
                           {"value":"119","text":"社会工作（专业学位）"},
                           {"value":"120","text":"警务（专业学位）"},
                           {"value":"121","text":"教育（专业学位）"},
                           {"value":"122","text":"体育（专业学位）"},
                           {"value":"123","text":"汉语国际教育（专业学位）"},
                           {"value":"124","text":"应用心理（专业学位）"},
                           {"value":"125","text":"翻译（专业学位）"},
                           {"value":"126","text":"新闻与传播（专业学位）"},
                           {"value":"127","text":"出版（专业学位）"},
                           {"value":"128","text":"文物与博物馆（专业学位）"},
                           {"value":"129","text":"建筑学（专业学位）"},
                           {"value":"130","text":"工程（专业学位）"},
                           {"value":"131","text":"城市规划（专业学位）"},
                           {"value":"132","text":"农业推广（专业学位）"},
                           {"value":"133","text":"兽医（专业学位）"},
                           {"value":"134","text":"风景园林（专业学位）"},
                           {"value":"135","text":"林业（专业学位）"},
                           {"value":"136","text":"临床医学（专业学位）"},
                           {"value":"137","text":"口腔医学（专业学位）"},
                           {"value":"138","text":"公共卫生（专业学位）"},
                           {"value":"139","text":"护理（专业学位）"},
                           {"value":"140","text":"药学（专业学位）"},
                           {"value":"141","text":"中药学（专业学位）"},
                           {"value":"142","text":"军事（专业学位）"},
                           {"value":"143","text":"工商管理（专业学位）"},
                           {"value":"144","text":"公共管理（专业学位）"},
                           {"value":"145","text":"会计（专业学位）"},
                           {"value":"146","text":"旅游管理（专业学位）"},
                           {"value":"147","text":"图书情报（专业学位）"},
                           {"value":"148","text":"工程管理（专业学位）"},
                           {"value":"149","text":"艺术（专业学位）"}];
    
    
    var tutortype = [{ "value": "0", "text": "博士硕士导师" }, 
                       { "value": "1", "text": "博士导师" }, 
                       { "value": "2", "text": "硕士导师" },
					   { "value": "3", "text": "无" }];
					   
	 var booleanenum = [{ "value": "0", "text": "否" }, 
                       { "value": "1", "text": "是" }];
					   
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
			queryParams = {"ftti_college":college};	
		}
		else{
			queryParams = {};
		}
		$('#cancelDelete').click(function(){
	 		$('.tip').hide();
	 	});
		getData(queryParams);
	});
    
    function getdanwei(){
    	var danweiname;
    	DWREngine.setAsync(false);
		DanWeiDao.getAllDanWei(function callback(data){ 
			danweiname = data;
		});
		return danweiname;
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
			url: '<%=basePath%>Sec_GetFullTimeTeachers',
			sortName: 'ftti_serialnumber',
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
			    {field:'ftti_id',title:'ID',sortable:true,width:80},
				{field:'ftti_name',title:'姓名',sortable:true,width:120,editor: { type: 'validatebox'}},
				{field:'ftti_worknumber',title:'工号',sortable:true,width:120,editor: { type: 'validatebox'}},
				{field:'ftti_gender',title:'性别',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:gender
							}
						}
				},
				{field:'ftti_birthday',title:'出生年月',sortable:true,width:120, formatter:myformatter,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'datebox', options: {editable:false}}},
				{field:'ftti_inschooldate',title:'入校时间',sortable:true,width:120,formatter:myformatter,
					//如果要实现编辑功能，需要添加下面的属性
					editor: { type: 'datebox', options: {editable:false}}},
				{field:'ftti_workstate',title:'任职状态',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:workstate
							}
						}
				},
				{field:'ftti_departmentnumber',title:'单位号',sortable:true,width:120,editor: { type: 'validatebox'}},
				{field:'ftti_departmentname',title:'单位名称',sortable:true,width:170,//加入开始=================================================================
					   editor : {  
	                        type : 'combobox',  
	                        options : {  
	                            valueField : 'danweiname',  
	                            textField : 'danweiname',  
	                            data : getdanwei(),  
	                            onChange : function (newValue, oldValue) {  
	                               //danweitemp="软件学院";
	                               var tt = newValue.split("#");
	                               var row = $('#grid').datagrid('getSelected');  
	                               var rindex = $('#grid').datagrid('getRowIndex', row);  
	                               var ed1 = $('#grid').datagrid('getEditor', { index: rindex, field: 'ftti_departmentnumber' });
	                               var ed2 = $('#grid').datagrid('getEditor', { index: rindex, field: 'ftti_departmentname' });
	                               if(tt.length > 1)
	                            	   $(ed1.target).val(tt[1]);
	                               $(ed2.target).combobox('setValue', tt[0]);
	                            } 
	                        }  
	                    }
				},
				{field:'ftti_education',title:'学历',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:education
							}
						}
				},
				{field:'ftti_degree',title:'最高学位',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:degree
							}
						}
				},
				{field:'ftti_educationsource',title:'学缘',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:educationsource
							}
						}
				},
				{field:'ftti_professionaltitle',title:'专业技术职称',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:professionaltitle
							}
						}
				},
				{field:'ftti_subjectcategory',title:'学科类别',sortable:true,width:170,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:subjectcategory
							}
						}
				},
				{field:'ftti_ifdoublequalifiedteacher',title:'是否双师型',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:booleanenum
							}
						}
				},
				{field:'ftti_ifengineeringbackground',title:'是否具有工程背景',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:booleanenum
							}
						}
				},
				{field:'ftti_ifindustrybackground',title:'是否具有行业背景',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:booleanenum
							}
						}
				},
				{field:'ftti_tutortype',title:'导师类别',sortable:true,width:100,
					editor: 
						{ 
							type : 'combobox',  
							options : {  
							valueField: "text", textField: "text"  ,
							data:tutortype
							}
						}
				},
				{field:'ftti_comments',title:'审核意见',sortable:true,width:120}
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
					//==============添加两行==================================================
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
				serialnumber = row.ftti_serialnumber + 1;
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
				serialnumber = row.ftti_serialnumber;
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
					var ftti_id = [];
					for(var i = 0; i < rows.length; ++i)
						{
							ftti_id[i] = rows[i].ftti_id;
						}						
					$.post("<%=basePath%>Sec_DeleteFullTimeTeacher", {fttiids: ftti_id.toString()},
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
					rows[i].ftti_birthday = myparser(rows[i].ftti_birthday);
					rows[i].ftti_inschooldate = myparser(rows[i].ftti_inschooldate);
				}
				var rowstr = JSON.stringify(rows);
				
				if (inserted.length < 1 && updated.length < 1 && deleted.length<1) {  
		            editRow = undefined;  
		            $('#grid').datagrid('unselectAll');  
		            return;  
		        }  
				var url = '';
	            if (inserted.length > 0) {
	                url = '<%=basePath%>Sec_AddFullTimeTeacher';
	            }
	            if (updated.length > 0) {
	                url = '<%=basePath%>Sec_UpdateFullTimeTeacher';
	            }
		        
		        rowstr = encodeURI(rowstr);
		        if(!collegeflag){
		        	college = encodeURI(college);
					collegeflag=true;	        	
		        }
		        
		        $.ajax({
		        	url: url,
		        	data: {rowdata:rowstr, serialnumber:serialnumber,ftti_college:college},
		        	dataType: 'json',
		        	success : function(r){
		        		if(r.result==1){
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
		        				$.messager.alert('错误', r.error, 'error');
		        			}
		        			if(inserted.length>0){
		        				$.messager.alert('错误', r.error, 'error');
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
	//---------------------------------------分隔符
	function _getnullcount(){
		$.messager.confirm("操作警告", "提交后数据将不可修改！！", function(data){
			if(data){
				$.post("<%=basePath%>Sec_GetFullTimeTeachers", {isnull: 1,sort:'ftti_serialnumber',order:'asc',ftti_college: college},
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
					$("#ssearchDialog").attr("action","Sec_ImportFullTimeTeacher?tableid="+tableid+"&college="+encodeURI(encodeURI(college)));
		        }
				else{
					$("#ssearchDialog").attr("action","Sec_ImportFullTimeTeacher?tableid="+tableid+"&college="+encodeURI(college));
				}
				$("#ttableid").val(tableid);
	}
	//下载模板
		function _downloadModel(){
			var tableid = getCookie('tableid');
			location.href="<%=basePath%>downloadModelServlet?tableid="+tableid;
	
	}
	//更改datebox的日期格式
    function myformatter(val, row) {
    	if(val != null && val != ""){
    		var date = new Date(val);
            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    	}
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
	<h2>表3-1-1专任教师基本信息</h2>
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