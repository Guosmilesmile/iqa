<%@page import="cn.edu.xmu.table.SuperMajorTable"%>
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

<title>查看境外交流表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">

<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/kendo/js/kendo.all.min.js"></script>
<!-- <script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>  -->
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>

<!-- 过滤需要导入改js -->
<script type="text/javascript" src="<%=path%>/js/easyUI/datagrid-filter.js"></script>


<style type="text/css">
.box-pdf{
position: relative;
top: -10px;
} 

.create-chart{
position: relative;
top: 25px;
}
.chart-output{
	position: absolute;
	left: 850px;
	top: 260px;
	width: 400px;
}
.chart-options{
	position: absolute;
	left: 850px;
	top: 40px;
	width: 125px;
}
.demo-section {
background-color: rgb(221, 243, 255);
       width: 460px;
       height: 300px;
	position: absolute;
	left: 320px;
	top: 3px;
         }
         .demo-section h2 {
       text-transform: uppercase;
       font-size: 1em;
       margin-bottom: 30px;
         }
         #cap {
             float: left;
             width: 242px;
             height: 225px;
             margin: 20px 30px 30px 0;
             background-image: url('../content/web/dropdownlist/cap.png');
             background-repeat: no-repeat;
             background-color: transparent;
         }
         .black-cap {
             background-position: 0 0;
         }
         .grey-cap {
             background-position: 0 -225px;
         }
         .orange-cap {
             background-position: 0 -450px;
         }
         #options {
             padding: 1px 0 30px 30px;
         }
         #options h3 {
             font-size: 1em;
             font-weight: bold;
             margin: 25px 0 8px 0;
         }
         #get {
             margin-top: 25px;
         }
</style>
<script>
    var tableid,userid,showtrue;

    $(function(){
    	//$("#searchDialog").hide();
		//获取数据的查询参数----过滤数据
		//------------------------------------要加获取tableid的方法=======================
		tableid = getCookie('tableid');
		userid = getCookie('userid');
		showtrue=getCookie('showtrue');
		
		var college = getCookie('deptxi');
		var queryParams;
		
		if(college!=""&&college!=undefined)
		{
			college = decodeURI(college);
			queryParams = {"college":college};	
		}
		else{
			queryParams = {};
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
	function getData(queryParams){

		$('#grid').datagrid({
			url: '<%=basePath%>getsupermajors',
			sortName: 'sm_serialnumber',
			sortOrder: 'asc',
			queryParams: queryParams,
			nowrap: true, //换行属性
			striped: true, //奇数偶数行颜色区分
			collapsible : true, //可折叠
			
			columns: [[
				/* {field:'fe_id',title:'ID',sortable:true,}, */
				{field:'sm_name',title:'校内专业名称',sortable:true,},
				{field:'sm_number',title:'校内专业代码',sortable:true,},
				{field:'sm_class',title:'类型',sortable:true,},
				{field:'c_startyear',title:'国家级起始年份',sortable:true,},
				{field:'p_startyear',title:'省级起始年份',sortable:true,},
				{field:'s_startyear',title:'校级起始年份',sortable:true,},
				{field:'respon_person',title:'负责人',sortable:true,}
			]],
			
			
			
			toolbar:[
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
			pagination: true,
			rownumbers:true,
		});
		
		
		//**************过滤
		$('#grid').datagrid('enableFilter', [/* {
			field:'fe_id',
			type:'numberbox',
			options:{precision:1},
			op:['equal','notequal','less','greater','lessorequal']
		}, */{
			field:'sm_name',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'sm_number',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'sm_class',
			type:'combobox',
			options:{
				panelHeight:'auto',
				data:[{value:'',text:'All'},
				      {value:"国家特色专业",text:"国家特色专业"},
				      {value:'省品牌专业',text:"省品牌专业"},
				      {value:'省示范专业',text:"省示范专业"},
				      {value:'重点建设专业',text:"重点建设专业"},
				      {value:'地方优势专业',text:"地方优势专业"},
				     ],
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
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'p_startyear',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'s_startyear',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		},{
			field:'respon_person',
			type:'text',
			options:{precision:1},
			op:['equal','notequal']
		}]);
		
		//***********************************************过滤结束--------------------
		//页面属性设置
		var p = $('#grid').datagrid('getPager');
		$(p).pagination({
			pageSize: 10, //每页条目数量
			
		});
		
		if(showtrue==0){
			$('div.datagrid-toolbar a').eq(0).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
		}
	};
	
	function _Pass(){
		var flag="1";
		var roleid = getCookie('watchroleid');
		var tableid = getCookie('watchtableid');
		$.ajax({
			type:'post',
			url:'<%=basePath%>changeexamsituation',
			data:{id:tableid,flag:flag,userid:userid,roleid:roleid},
			success:function(){
				location.href="<%=basePath%>admin/examinetable.jsp";
			},error:function(){
				alert('fail');
			}
			
		});
	};
	function _UnPass(){
		var flag= "2";
		var roleid = getCookie('watchroleid');
		var tableid = getCookie('watchtableid');
		$.ajax({
			type:'post',
			url:'<%=basePath%>changeexamsituation',
			data:{id:tableid,flag:flag,userid:userid,roleid:roleid},
			success:function(){
				location.href="<%=basePath%>admin/examinetable.jsp";
			},error:function(){
				alert('fail');
			}
			
		});
	}
	function _Export(){
		var tableid = getCookie('watchtableid');
		location.href="./exportServlet?tableid="+tableid;
	}
	
/*****关于图表的部分******/
$(document).ready(function() {
	         var dataX = [
	             { text: "编号", value: "1" },
	         ]; 
	         var dataY = [
	             { text: "编号", value: "1" },
	         ]; 
	         
	         var dataPie = [
	             { text: "类型", value: "1" },
	     	]; 
	         
	         // create DropDownList from input HTML element
	         $("#pieX").kendoDropDownList({
	             dataTextField: "text",
	             dataValueField: "value",
	             dataSource: dataPie,
	             index: 0,
	             change: onChange
	         });
	         
	         $("#nonPieX").kendoDropDownList({
	             dataTextField: "text",
	             dataValueField: "value",
	             dataSource: dataX,
	             index: 0,
	             change: onChange
	         });
	         
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
	                     //proxyURL: "http://demos.telerik.com/kendo-ui/service/export"
	                 });
	             });
	         });

	         $(".export-img").click(function() {
	             var chart = $("#chart").getKendoChart();
	             chart.exportImage().done(function(data) {
	                 kendo.saveAs({
	                     dataURI: data,
	                     fileName: "chart.png",
	                    //proxyURL: "http://demos.telerik.com/kendo-ui/service/export"
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
	         		if(pieValue == 1){//类型
	         			attribute = "<%=SuperMajorTable.SM_CLASS %>";
	         			title = "优势专业类型分布比例";
	         		}
	         		<%-- else if(pieValue == 2){//所属学院
	         			attribute = "<%=SuperMajorTable.COLLEGE %>";
	         			title = "优势专业所属学院分布比例";
	         		} --%>
	         		$.ajax({
	      	           type: 'post',
	      	           url: "<%=basePath %>gotodrawpie",
	      	           data: {
	      	        	  tableName:"<%=SuperMajorTable.TABLE_NAME %>",
	      	        	  attribute:attribute,//统计属性
	      	        	  chartType:"pie",
	      	        	 // college:college,
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
	     </script>
	     </head>
	     <body>
	     <div id="example">
	     <div id="grid"></div>

	     <div style="width:400px;float: left;position:relative;">
	     <!--     <h2>Customize your Kendo Cap</h2>
	         <div id="cap" class="black-cap"></div> -->
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
	     	       <!--  <div class="box-col">
	     	            <button class='export-svg k-button'>Export as SVG 保存为SVG</button>
	     	        </div> -->
	     	    </div>
	     	  </div>
	     </div>

	         
</body>
</html>


