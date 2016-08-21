<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();	
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.box-pdf{
position: relative;
top: -10px;
} 
.box-pdf{
position: relative;
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
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=basePath%>kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>kendo/js/kendo.all.min.js"></script>
<script src="<%=basePath%>js/myJs.js"></script>
<script type="text/javascript">
$(function () {
	$.ajax({  
        type:"POST",  
        dataType:"JSON", 
        url: "<%=basePath %>getstudent",
        data: {
     	  //chartType:"chart",
        },
       success:function(result){  
       	var data = result;  
	
    $("#grid").kendoGrid({
    	dataSource:{  
            data:data,
            batch: true,
            pageSize: 10,
        },
       /*  filterable: {
            mode: "row",
            operators: {
                string: {
                    startswith: "首部匹配",
                    eq: "完全匹配",
                    neq: "完全不匹配",
                    contain: "包含匹配"
                }
            }
        },  */
        pageable: {
            pageSizes: true,
            buttonCount: 5,
            messages: {
                display: "显示{0}-{1}条，共{2}条",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页",
                refresh: "刷新"
            }
        },
        columns: [{
            field: "id",
            title: "编号",
            width: 80,
            filterable: {
                cell: {
                    showOperators: false
                }
            } 
        }, {
            field: "id_num",
            title: "学号",
            filterable: {
                cell: {
                    operator: "gte"
                }
            }
        }, {
            field: "name",
            title: "姓名",
            filterable: {
                cell: {
                    operator: "contains"
                }
            }
        }, {
            field: "school",
            title: "学院"
        }, {
            field: "major",
            title: "专业"
        }, {
            field: "sex",
            title: "性别",
        }, {
            field: "city",
            title: "所在地"
        }, {
            field: "birthday",
            title: "生日",
            format: "{0:MM/dd/yyyy}"
        }]
    });
    
   },});
});


$(document).ready(function() {
    var dataX = [
        { text: "编号", value: "1" },
    ]; 
    var dataY = [
        { text: "编号", value: "1" },
    ]; 
    var dataPie = [
    { text: "学院", value: "1" },
    { text: "专业", value: "2" },
    { text: "性别", value: "3" }
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
    	alert("ok");
    	var picType = document.getElementById("chartType");
    	var picTypevalue = picType.options[picType.selectedIndex].value;
    	alert("chartTypeValue"+ picTypevalue);
    	var pieX = $("#pieX").data("kendoDropDownList");
    	var pieValue = pieX.value();
    	alert("pieValue"+pieValue);
    	var x = $("#nonPieX").data("kendoDropDownList");
    	var xValue = x.value();
    	alert("xValue"+xValue);
    	var y = $("#nonPieY").data("kendoDropDownList");
    	var yValue = y.value();
    	alert("yValue"+yValue);
    	if(picTypevalue == 1)//饼状图
    	{
    		var attribute;
    		var title;
    		if(pieValue == 1){//学院
    			attribute = "school";
    			title = "学生所在学院分布比例";
    		}else if(pieValue == 2){//专业
    			attribute = "major";
    			title = "学生专业分布比例";
    		}else{//性别
    			attribute = "sex";
    			title = "学生性别分布比例";
    		}
    		$.ajax({
 	           type: 'post',
 	           url: "<%=basePath %>pieforstudentservlet",
 	           data: {
 	        	  attribute:attribute,//统计属性
 	        	  chartType:"pie"
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
       		<option value="2">折线图</option>
       		<option value="3">柱状图</option>
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