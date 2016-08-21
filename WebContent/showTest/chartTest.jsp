<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();	
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style type="text/css"></style>
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=basePath %>kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=basePath%>kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>kendo/js/kendo.all.min.js"></script>
<script src="<%=basePath%>js/myJs.js"></script>
<script type="text/javascript">
function createChart() {
    $("#chart").kendoChart({
        title: {
            text: "这里是图表的名称 /单位/"
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            type: "column",
            stack: true
        },
        series: [{
            name: "Total Visits",//数据标识
            data: [56000, 63000, 74000, 91000, 117000, 138000],

            // Line chart marker type
            markers: { type: "square" }
        }, {
            name: "Unique visitors",
            data: [52000, 34000, 23000, 48000, 67000, 83000]
        }],
        valueAxis: {
            line: {
                visible: false
            }
        },
        categoryAxis: {
            categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],//横轴
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

$(document).ready(function() {
    createChart();
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
});

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
</script>
</head>
<body>
<div id="example">
    <div class="demo-section k-content">
        <div id="chart"></div>
    </div>
    <div class="box">
        <div class="box-col">
            <h4>API Functions</h4>
            <ul class="options">
                <li>
                    <input id="typeColumn" name="seriesType"
                                type="radio" value="column" checked="checked" autocomplete="off" />
                    <label for="typeColumn">Columns</label>
                </li>
                <li>
                    <input id="typeBar" name="seriesType"
                                type="radio" value="bar" autocomplete="off" />
                    <label for="typeBar">Bars</label>
                </li>
                <li>
                    <input id="typeLine" name="seriesType"
                                type="radio" value="line" autocomplete="off" />
                    <label for="typeLine">Lines</label>
                </li>
                <li>
                    <input id="stack" type="checkbox" autocomplete="off" checked="checked" />
                    <label for="stack">Stacked</label>
                </li>
            </ul>
           <!--  <p>
                <strong>refresh()</strong> will be called on each configuration change
            </p> -->
        </div>
    </div>
    <div class="box">
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