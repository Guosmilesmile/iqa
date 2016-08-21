<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();	
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻信息统计测试页面</title>
<link rel="stylesheet" href="kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.default.min.css" />

<script src="kendo/js/jquery.min.js"></script>
<script src="kendo/js/kendo.all.min.js"></script>
<style>
	.demo-section {
	    width: 600px;
	}
	.demo-section p {
	    margin: 0 0 30px;
	    line-height: 40px;
	}
	.demo-section p .k-button {
	    margin: 0 5px;
	}
	.k-primary {
	    min-width: 150px;
	}                
</style>
<script type="text/javascript">
$(document).ready(function () {
	function createChart(data) {
		var myobj=eval(data);
	 	//alert(myobj);
	 	/* alert(myobj[0].categories);  
	 	alert(myobj[0].data);  
	 	alert(myobj[0].name);
	 */
	 	var data=new Array();
	    var category=new Array();
	 	for(var i=0;i<myobj.length;i++){ 
	 		data[i]=myobj[i].value;
	 		category[i]=myobj[i].category;
	 	   }
	 
	    $("#chart").kendoChart({
	        title: {
	            text: "新闻类型数量 (条)"
	        },
	        legend: {
	            position: "bottom"
	        },
	        seriesDefaults: {
	            type: "column",
	            stack: true
	        },
	        series: [{
	            name: "新闻数量",//数据标识
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
	
	function createPieChart(data) {
		var myobj=eval(data);
 	  /*  alert(myobj);
 	   for(var i=0;i<myobj.length;i++){  
 	      alert(myobj[i].category);  
 	      alert(myobj[i].value);  
 	   }  */
		
        $("#pieChart").kendoChart({
            title: {
                position: "bottom",
                text: "新闻分类数量比例"
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
	
    $(document).bind("kendo:skinChange", createPieChart);
    $(document).bind("kendo:skinChange", createChart);
	$(".options").bind("change", refresh);
    
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
    
    $(".export-svg").click(function() {
        var chart = $("#chart").getKendoChart();
        chart.exportSVG().done(function(data) {
            kendo.saveAs({
                dataURI: data,
                fileName: "chart.svg",
               //proxyURL: "http://demos.telerik.com/kendo-ui/service/export"
            });
        });
    });
    
    
    $(".k-primary").kendoButton();
    $("#textButton").kendoButton(); 
    $("#newsPie").click(function(){
    	$.ajax({
	           type: 'post',
	           url: "<%=basePath %>countnews",
	           data: {
	        	  chartType:"pie",
	           },
	           success: function(msg){
	        	//alert(msg);
	        	//JSON字串
	        	createPieChart(msg);
	       		//$("#outputOptions").show();
				},
				
			});
    });
    
    $("#newsChart").click(function(){
    	$.ajax({
	           type: 'post',
	           url: "<%=basePath %>countnews",
	           data: {
	        	  chartType:"chart",
	           },
	           success: function(msg){
	        	//alert(msg);
	        	//JSON字串
	        	//createChart(msg);
	        	createChart(msg);
	        	$("#outputOptions").show();
	        	$("#chartOptions").show();
				},
				
			});
    });
});
</script>
</head>
<body>
	<div id="example">
		<div class="demo-section k-header">
			<div>
	             <h4>新闻信息统计选项</h4>
	           	 <p>
	              	<button id="newsPie" class="k-primary">新闻分类饼图</button>
	              	<button id="newsChart" class="k-primary">新闻分类折线图</button>
	             </p>
	        </div>
		</div>
		
		<!-- 饼图 -->
		<div class="demo-section k-content">
        	<div id="pieChart" style="background: center no-repeat url('../content/shared/styles/world-map.png');"></div>
    	</div>
    	
    	<!-- 柱状图和折线图 -->
    	<div class="demo-section k-content">
        	<div id="chart"></div>
    	</div>
	    <div class="box" style="display:none;" id="chartOptions">
	        <div class="box-col">
	            <h4>API Functions 图样选择</h4>
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
	    <div class="box" style="display:none;" id="outputOptions">
	        <h4>Advanced Export options 高级输出选项</h4>
	        <div class="box-col">
	            <button class='export-pdf k-button'>Export as PDF 保存为PDF</button>
	        </div>
	        <div class="box-col">
	            <button class='export-img k-button'>Export as Image 保存为PNG</button>
	        </div>
	        <div class="box-col">
	            <button class='export-svg k-button'>Export as SVG 保存为SVG</button>
	        </div>
	    </div>
	</div>
</body>
</html>