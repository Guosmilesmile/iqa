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
		
		//college = getCookie('deptxi');
		<%String filldeptxi = request.getAttribute("deptxi").toString();%>
	    college = "<%=filldeptxi%>";
		var queryParams;
		
		if(college!=""&&college!=undefined)
		{
			college = decodeURI(college);
			queryParams = {"college":college,"tableid":tableid, "userid":userid};	
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
			url: '<%=basePath%>Sec_GetAllForeignExchangeServlet',
			sortName: 'fe_serialnumber',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			frozenColumns:[[
						    {field: 'ck', checkbox: true},          
						]],
			columns: [[
				/* {field:'fe_id',title:'ID',sortable:true,}, */
				{field:'fe_collegename',title:'所属学院',sortable:true,},
				{field:'fe_projectname',title:'交流项目名称',sortable:true,},
				{field:'fe_iscsc',title:'是否留学基金委项目',sortable:true,},
				{field:'fe_country',title:'所在国家或地区',sortable:true,},
				{field:'fe_school',title:'合作学校',sortable:true,},
				{field:'fe_level',title:'级别',sortable:true,},
				{field:'fe_time',title:'交流期限',sortable:true,},
				{field:'fe_selftoforeign',title:'本校到外校学生数',sortable:true,},
				{field:'fe_foreigntoself',title:'外校到本校学生数',sortable:true,},
				
				{field:'fe_college',title:'填报学院',sortable:true,},
				{field:'fe_deadline',title:'填报期限',sortable:true,formatter:formatterdate,},//日期样式
				{field:'fe_comments',title:'审核意见',sortable:true,width:120,
					editor: { type: 'validatebox'}
				}
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
																	
							id = rowData.fe_id;
				        	$('#grid').datagrid('beginEdit',rowIndex);
				        	//这边的rowIndex和行号是不一样的值，0------开始的
				        	doedit=rowIndex;
						
						
					},
					
					
			pagination: true,
			rownumbers:true,
		});


		
		//***********************************************过滤结束--------------------
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
			$("#grid").datagrid('removeEditor', 'fe_comments');
			$('div.datagrid-toolbar a').eq(2).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
		}
		if(<%=show %>==0){//单纯查看
			$('div.datagrid-toolbar a').eq(0).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
			$('div.datagrid-toolbar a').eq(2).hide();
			
			$("#grid").datagrid('removeEditor', 'fe_comments');
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
		unpasscolleges[0]=rows[0].fe_college;
		var unpasscollegesstr = JSON.stringify(unpasscolleges);
		
		for(var i = 0; i < rows.length; i++)
		{
			rows[i].fe_comments = '';
		}
		var rowstr = JSON.stringify(rows);
		//alert(rowstr);
		//更新记录
		$.ajax({
	        	url: '<%=basePath%>Sec_UpdateForeignExchangeServlet',
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
				if(rows[i].fe_comments == "" ||rows[i].fe_comments == null){
					
					rows[i].fe_comments = '该记录填写有误！';
				} 
				if(unpasscolleges.indexOf(rows[i].fe_college)<0)
				{
					unpasscolleges[j]=rows[i].fe_college;
					j++;
				}
			}
			var unpasscollegesstr = JSON.stringify(unpasscolleges);
			var rowstr = JSON.stringify(rows);
			//更新记录
			$.ajax({
		        	url: '<%=basePath%>Sec_UpdateForeignExchangeServlet',
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
		location.href="<%=basePath%>Sec_ExportForeignExchangeServlet?tableid="+tableid+"&college="+encodeURI(encodeURI(college));
	}
	
/*****关于图表的部分******/
<%-- $(document).ready(function() {
    var dataX = [
        { text: "编号", value: "1" },
    ]; 
    var dataY = [
        { text: "编号", value: "1" },
    ]; 
    var dataPie = [
    { text: "所在国家或地区", value: "1" },
    { text: "合作学校", value: "2" },
    { text: "级别", value: "3" },
    { text: "交流期限", value: "4" }
	]; 
    
	//饼状图变量
    $("#pieX").kendoDropDownList({
        dataTextField: "text",
        dataValueField: "value",
        dataSource: dataPie,
        index: 0,
        change: onChange
    });
    //非饼状图自变量
    $("#nonPieX").kendoDropDownList({
        dataTextField: "text",
        dataValueField: "value",
        dataSource: dataX,
        index: 0,
        change: onChange
    });
    //非饼状图因变量
    $("#nonPieY").kendoDropDownList({
        dataTextField: "text",
        dataValueField: "value",
        dataSource: dataY,
        index: 0,
        change: onChange
    });

    // create DropDownList from select HTML element
    $("#chartType").kendoDropDownList();

    function onChange() {
        
    };
    $(".export-pdf").click(function() {
        var chart = $("#chart").getKendoChart();
        chart.exportPDF({ paperSize: "auto", margin: { left: "1cm", top: "1cm", right: "1cm", bottom: "1cm" } }).done(function(data) {
            kendo.saveAs({
                dataURI: data,
                fileName: "chart.pdf",
            });
        });
    });

    $(".export-img").click(function() {
        var chart = $("#chart").getKendoChart();
        chart.exportImage().done(function(data) {
            kendo.saveAs({
                dataURI: data,
                fileName: "chart.png",
            });
        });
    });
    
    $(document).bind("kendo:skinChange", createPieChart);
    $(document).bind("kendo:skinChange", createChart); 
	$(".options").bind("change", refresh);
	
    $("#createChart").click(function() {
    	var picType = document.getElementById("chartType");
    	var picTypevalue = picType.options[picType.selectedIndex].value;
    	var pieX = $("#pieX").data("kendoDropDownList");
    	var pieValue = pieX.value();
    	var x = $("#nonPieX").data("kendoDropDownList");
    	var xValue = x.value();
    	var y = $("#nonPieY").data("kendoDropDownList");
    	var yValue = y.value();
    	if(picTypevalue == 1)//饼状图
    	{
    		var attribute;
    		var title;
    		if(pieValue == 1){//所在国家或地区
    			attribute = "fe_country";
    			title = "外出交流所在国家或地区分布比例";
    		}else if(pieValue == 2){//合作学校
    			attribute = "fe_school";
    			title = "合作学校分布比例";
    		}else if(pieValue == 3){//级别
    			attribute = "fe_level";
    			title = "交流级别分布比例";
    		}
    		else{//交流期限
    			attribute = "fe_time";
    			title = "交流期限分布比例";
    		}
    		$.ajax({
 	           type: 'post',
 	           url: "<%=basePath %>gotodrawpie",
 	           data: {
 	        	  tableName:"<%=ForeignExchangeTable.TABLE_NAME %>",
 	        	  attribute:attribute,//统计属性
 	        	  chartType:"pie",
 	        		 //college:college,
 	           },
 	           success: function(msg){
 	        	  $("#chartOptions").hide();
 	        	  createPieChart(title,msg);
 	        	 $("#outputOptions").show();
 			},
 				
 			});
    	}
    	if(picTypevalue == 2)//折线图
    	{
    		var attributeX;
    		var attributeY;
    		var title;
    		var name;
    		if(xValue == 1){//编号
    			attributeX = "id";
    			if(yValue == 1){//编号
    				attributeY = "id";
    				title = "学生编号与学生编号的关系(可以注明单位)";
    				name = "学生编号";
    			}
    		}
    		/*例举其他可能的属性选择*/
    		$.ajax({
 	           type: 'post',
 	           url: "<%=basePath %>pieforstudentservlet",
 	           data: {
 	        	  chartType:"chart",
 	        	  attributeX:attributeX,//统计属性x
 	        	  attributeY:attributeY,//统计属性
 	           },
 	           success: function(msg){
 	        	createChart(title,name,msg,"line");
 	        	$("#outputOptions").show();
 	        	$("#chartOptions").show();
 				},
 				
 			});
    	}
    	if(picTypevalue == 3)//柱状图
    	{
    		createChart(title,name,msg,"column");
    	}
    });
});


function chartTypeChange(){
	var picType = document.getElementById("chartType");
	var picTypevalue = picType.options[picType.selectedIndex].value;
	if(picTypevalue == 1){//饼状图
		$("#pie").show();
		$("#nonPie1").hide();
		$("#nonPie2").hide();
	}
	else{
		$("#pie").hide();
		$("#nonPie1").show();
		$("#nonPie2").show();
	}
}

function createPieChart(title,data) {
	var myobj=eval(data);
    $("#chart").kendoChart({
        title: {
            position: "bottom",
            text: title,
        },
        legend: {
            visible: false
        },
        chartArea: {
            background: ""
        },
        seriesDefaults: {
            labels: {
                visible: true,
                background: "transparent",
                template: "#= category #: \n #= value#%"
            }
        },
        series: [{
            type: "pie",
            startAngle: 150,
            data: myobj,
        }],
        tooltip: {
            visible: true,
            format: "{0}%"
        }
    });
}
function refresh() {
    var chart = $("#chart").data("kendoChart"),
        series = chart.options.series,
        type = $("input[name=seriesType]:checked").val(),
        stack = $("#stack").prop("checked");

    for (var i = 0, length = series.length; i < length; i++) {
        series[i].stack = stack;
        series[i].type = type;
    };

    chart.refresh();
}

function createChart(title,name,data,type) {
	var myobj=eval(data);
 	var data=new Array();
    var category=new Array();
 	for(var i=0;i<myobj.length;i++){ 
 		data[i]=myobj[i].value;
 		category[i]=myobj[i].category;
	}
 
    $("#chart").kendoChart({
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            type: type,
            stack: true
        },
        series: [{
            name: name,//数据标识
            data: data,

            // Line chart marker type
            //markers: { type: "square" }
        }/* , {
            name: "Unique visitors",
            data: [52000, 34000, 23000, 48000, 67000, 83000]
        } */],
        valueAxis: {
            line: {
                visible: false
            }
        },
        categoryAxis: {
            categories: category,//横轴
            majorGridLines: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            format: "{0}"
        }
    });
}
	 --%>
	
	
	
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
    <h2>表6-2-2-1本科生境外交流情况</h2>
	<table id="grid" class = "easyui-datagrid"></table>



<div style="width:400px;float: left;position:relative;">
    <div id="options">
    	<h3>图表类型</h3>
	    <select id="chartType" onchange="chartTypeChange()">
	        <option selected="selected">请选择图表类型</option> 
       		<option value="1">饼状图</option>
       		<!-- <option value="2">折线图</option>
       		<option value="3">柱状图</option> -->
	    </select>
	    
	    <div id="pie" style='display:none;'>
    	<h3>统计变量</h3>
    	<input id="pieX" value="1" />
    	</div>
    	
    	<div id="nonPie1" style='display:none;'>
    	<h3>自变量</h3>
    	<input id="nonPieX" value="1" />
    	</div>
    	
    	
    	<div id="nonPie2" style='display:none;'>
    	<h3>因变量</h3>
    	<input id="nonPieY" value="1" />
    	</div>

		<div class="create-chart">
    	<button class="k-button" id="createChart" >生成图表</button>
    	</div>
    </div>
   <!-- 饼图、柱状图和折线图 -->
    	<div class="demo-section k-content">
        	<div id="chart"></div>
    	</div>
    	 <div class="box chart-options" style="display:none;" id="chartOptions">
	        <div class="box-col">
	            <h4>图样选择</h4>
	            <ul class="options">
	                <li>
	                    <input id="typeColumn" name="seriesType"
	                                type="radio" value="column" checked="checked" autocomplete="off" />
	                    <label for="typeColumn">柱状图</label>
	                </li>
	                <li>
	                    <input id="typeBar" name="seriesType"
	                                type="radio" value="bar" autocomplete="off" />
	                    <label for="typeBar">条形图</label>
	                </li>
	                <li>
	                    <input id="typeLine" name="seriesType"
	                                type="radio" value="line" autocomplete="off" />
	                    <label for="typeLine">折线图</label>
	                </li>
	                <!-- <li>
	                    <input id="stack" type="checkbox" autocomplete="off" checked="checked" />
	                    <label for="stack">Stacked</label>
	                </li> -->
	            </ul>
	           <!--  <p>
	                <strong>refresh()</strong> will be called on each configuration change
	            </p> -->
	        </div>
	    </div>
	    <div class="box chart-output" style="display:none;" id="outputOptions">
	        <h4>高级输出选项</h4>
	        <div class="box-col box-pdf">
	            <button class='export-pdf k-button'>保存为PDF</button>
	        </div>
	        <div class="box-col box-png">
	            <button class='export-img k-button'>保存为PNG</button>
	        </div>
	    </div>
</div>
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


