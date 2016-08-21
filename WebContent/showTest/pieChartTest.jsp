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
<title>Insert title here</title>
<link rel="stylesheet" href="kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.default.min.css" />

<script src="kendo/js/jquery.min.js"></script>
<script src="kendo/js/kendo.all.min.js"></script>
<script type="text/javascript">
        function createChart() {
            $("#chart").kendoChart({
                title: {
                    position: "bottom",
                    text: "Share of Internet Population Growth, 2007 - 2012"
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
                    data:[{
                        category: "Asia",
                        value: 53.8,
                        color: "#9de219"
                    },{
                        category: "Europe",
                        value: 16.1,
                        color: "#90cc38"
                    },{
                        category: "Latin America",
                        value: 11.3,
                        color: "#068c35"
                    },{
                        category: "Africa",
                        value: 9.6,
                        color: "#006634"
                    },{
                        category: "Middle East",
                        value: 5.2,
                        color: "#004d38"
                    },{
                        category: "North America",
                        value: 3.6,
                        color: "#033939"
                    }]
                }],
                tooltip: {
                    visible: true,
                    format: "{0}%"
                }
            });
        }

        $(document).ready(createChart);
        $(document).bind("kendo:skinChange", createChart);
    </script>


</head>

<body>
<div id="example">
    <div class="demo-section k-content">
        <div id="chart" style="background: center no-repeat url('../content/shared/styles/world-map.png');"></div>
    </div>
</div>

</body>
</html>