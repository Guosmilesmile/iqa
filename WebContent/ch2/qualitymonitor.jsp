<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>厦门大学内部质量保障网</title>
    <link rel="shortcut icon" href="../img/LOGO.ico" >
    <link href="../css/xmu_base.css" type="text/css" rel="stylesheet">
	<link href="../css/style-define.css" type="text/css" rel="stylesheet" >
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="../js/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/commonJs.js"></script>
	<script type="text/javascript">
	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(var i = 0; i < strs.length; i ++) { 
				theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
			} 
		} 
		return theRequest; 
	} 
    </script>
    <script>
        startList=function(){
            var navRoot=document.getElementById("header-nav");
            var allLi=navRoot.getElementsByTagName("li");
            var sdnav=document.getElementById("nav-sd-bg");
            for(i=0;i<allLi.length;i++){
                node=allLi[i];
                node.onmouseover=function(){
                    if(this.className=="")
                        sdnav.style.visibility="visible";
                    this.className+="current";
                }
                node.onmouseout=function(){
                    this.className=this.className.replace("current","");
                    sdnav.style.visibility="hidden";
                }
            }
        }
        window.onload=startList;
    </script>
    <script>
    function getReports(menuId, listId){
    	var Request = new Object();
    	Request = GetRequest();
    	//var menuId = 23;
    	$.ajax({
            type: 'post',
            url: "<%=basePath%>getallnews",
            data: {
            	pagination:"false",
            	rows:20,
            	menuId:menuId,
            	n_subclass:"3",
            	order:"asc"
            },
            success: function(newsListJsonStr){
            
            	var ul_body = $("#reportsList_"+listId);           	
            	var str ="";
            	var newsList = eval('(' + newsListJsonStr + ')');
            	if(newsList.length > 0){
            		for (var i = 0 ;i < newsList.length;i++){
            			if(newsList[i].n_content.indexOf('title="pdf.png"')>0)
        				{
        				var a=newsList[i].n_content.indexOf("href");
        				var b=newsList[i].n_content.indexOf('"',a+6);       			
        				var content=newsList[i].n_content.substring(a+6,b);
        				
            		    str +="<li><a href='"+content+"' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"' target=\"view_window\">"+newsList[i].n_titles+
            		    "</a></li>"; 
        				}
        			else{
            			if(newsList[i].n_content == ""){
            				
            			    str +="<li><a href='#'  id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"'>"+newsList[i].n_titles+
            		        "</a>"
            		        +"</li>"; 
            			}
            			else{
            				str +="<li><a href='<%=basePath%>ch/document.jsp?contentId="+newsList[i].n_id+"' id='readContent"+newsList[i].n_id+"' title='"+newsList[i].n_summary+"' target=\"view_window\">"+newsList[i].n_titles+
            		        "</a>"
            		        +"</li>";
            			}
        			}

              	}
            		ul_body.html(str);           	
            	}
    		},
            error: function(){}
            	
    	});
    }
    
    $(document).ready(function () {
//     	getReports(23,0);
//     	getReports(24,1);
//     	getReports(25,2);
//     	getReports(39,3);
//     	getReports(40,4);
    });
    </script>
    <script type="text/javascript">
    function navbarChange(id,count) 
    { 
    	var divName='QM_';
    	var i=0;
    	for(i=0;i<count;i++) 
    	{ 
    		document.getElementById(divName+i).style.display="none"; 
    	} 
    	document.getElementById(divName+id).style.display="block";
    }
    </script>
	
</head>

<body class="style-bright">
    <div id="wrapper">
         <header>
            <div class="header-wrapper">
                <div id="header-logo">
                    <div style="display: inline">
                        <img id="img-LOGO" src="../img/LOGO.png">
                        <div style="display: inline-block">
                            <img id="img-XMU" src="../img/xmu-word.png">
                            <img id="img-IQAWeb" src="../img/IQAWeb.png">
                            <img id="img-XMUIQA" src="../img/XMUIQA.png">
                        </div>
                    </div>
                    <div style="margin: -200px 180px 0 0;text-align: right;vertical-align: top;">
                        <img src="../img/header-logo.png" style="height: 250px">
                    </div>
                    <div style="position:absolute;z-index: 999;">
                        <div style="margin: -100px 0 0 800px;position: relative;width: 285px">
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group" style="width: 204px;">
                                    <input id="key" style="width:146px; height: 24px;background: rgba(255,255,255,0.8);display: inline;padding: 0;"type="text" class="form-control" placeholder="请输入要检索的内容">
                                    <a href="javascript:search()" class="search-icon"></a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div id="header-nav">
                        <ul>
                            <li class="no-sub"><a href="Index.jsp">首页</a></li>
                            <li><a href="iqa_inform.jsp?id=1">新闻公告</a>
                            	<ul style="">
									<li><a href="iqa_inform.jsp?id=1">通知公告</a></li>
									<li><a href="iqa_inform.jsp?id=2">热点新闻</a></li>
									<li><a href="iqa_inform.jsp?id=3">图片新闻</a></li>
								</ul>
                            </li>
                            <li>
                                <a href="iqa_overview.jsp?id=1">IQA体系</a>
								<ul style="left:-150px">
									<li><a href="iqa_overview.jsp?id=1">理论体系</a></li>
									<li><a href="iqa_overview.jsp?id=2">监控流程</a></li>
									<li><a href="iqa_overview.jsp?id=3">组织架构</a></li>
									<li><a href="iqa_overview.jsp?id=4">联合国IQA项目</a></li>
								</ul>
                            </li>
                            <li>
                            	<a href="iqa_file.jsp">政策文件</a>
                            	<ul style="left:-220px">
									<li><a href="./iqa_file.jsp?id=1">上级文件</a></li>
									<li><a href="./iqa_file.jsp?id=2">厦门大学本科教学管理制度</a></li>
									<li><a href="./iqa_file.jsp?id=4">厦门大学新生学业导航</a></li>
									<li><a href="./iqa_file.jsp?id=5">厦门大学教师手册</a></li>
								</ul>
                            </li>
							<li >
								<a href="qualitymonitor.jsp?id=0">质量监控</a>
								<ul style="left:-400px">
									<li><a href="qualitymonitor.jsp?id=0" style="padding-left:20px;padding-right:20px;">年度校内自我评估</a></li>
									<li><a href="qualitymonitor.jsp?id=1" style="padding-left:20px;padding-right:20px;">本科课程教学评测</a></li>
									<li><a href="qualitymonitor.jsp?id=2" style="padding-left:20px;padding-right:20px;">学生学习经历调查</a></li>
									<li><a href="qualitymonitor.jsp?id=3" style="padding-left:20px;padding-right:20px;">常态数据动态监测</a></li>
									<li><a href="qualitymonitor.jsp?id=4" style="padding-left:20px;padding-right:20px;">日常教学质量监督</a></li>
								</ul>
							</li>
							<li>
								<a href="iqa_vertify.jsp?id=1">审核评估</a>
								<ul style="left:-100px">
									<li><a href="iqa_vertify.jsp?id=1">上级文件</a></li>
									<li><a href="iqa_vertify.jsp?id=2">通知公告</a></li>
								</ul>
							</li>
							<li class="no-sub"><a href="iqa_document.jsp">文档下载</a></li>
					  		<li class="no-sub"><a href="../login.jsp">用户登录</a></li> 
						</ul>
                    </div>
                </div>
            </div>
            <div id="nav-bg"></div>
            <div id="nav-sd-bg" style="visibility:hidden;"></div>
            <div style="clear: both"></div>
        </header>
        <!-- MAIN-->
         <div id="main" class="container container-fluid">
            <br class="row-fluid">
                <div id="sub-nav" class="span3">
                    <span class="sub-nav-title">质量监控</span>
                    <!-- left menu ================================================== -->
                    <ul>
                       <li><a href="#" onclick="navbarChange('0',5)"><i class="icon icon-chevron-right"></i>年度校内自我评估</a></li>
                       <li><a href="#" onclick="navbarChange('1',5)"><i class="icon icon-chevron-right"></i>本科课程教学测评</a></li>
                       <li><a href="#" onclick="navbarChange('2',5)"><i class="icon icon-chevron-right"></i>学生学习经历调查</a></li>
                       <li><a href="#" onclick="navbarChange('3',5)"><i class="icon icon-chevron-right"></i>常态数据动态监测</a></li>
                       <li><a href="#" onclick="navbarChange('4',5)"><i class="icon icon-chevron-right"></i>日常教学质量监督</a></li>
                    </ul>
                </div>       
                <div id="main-content" class="span9" >
                    <div id="QM_0">
                      <h3>年度校内自我评估</h3>
                      <p>2006年起，厦门大学参照教育部的做法，把教学评估作为衡量教学质量的“尺子”，建立了较为完善的“自我检查、相互观摩、典型示范、及时整改”的自我评估机制，评估内容涉及了新教师、新专业、教学计划、课堂教学、实践教学、考试、毕业论文（设计）、学生学习状态、教学改革、教学经费等各个方面，形成对教育质量的全方位、常态化监测。学校每年选取若干影响教学质量的关键指标，采用学院自查和学校专家组检查的方式开展自我评估，针对评估后暴露出的问题，采取“个别问题针对性解决，综合性问题通盘解决，复杂性问题探索解决”的思路，第二年年初，学校以评估反馈会或教学工作布置会的形式将评估结果反馈至各学院及相关单位，并督促整改。</p>                      
                      <div style="text-align:center;"><img style="width:300px; heigth:300px;  " src="../img/qm_educationevaluation.png"/></div>
                      <div style="text-align:center;">图：年度校内自我评估流程</div>
<!--                       <h4>厦门大学本科教学评估总结报告</h4>                       -->
            
<!--                       <ul id="reportsList_0"> -->
<!--                       </ul>                      -->
                    </div>
                    
                    <div id="QM_1" style="display:none">
                      <h3>本科课程教学测评</h3>
                      <p>《厦门大学章程》明确规定学生享有“对教师的教学效果进行测评，并提出意见和建议”的权利。本着“重在诊断、重在反馈、重在改进、重在提高”的原则，学校每学期以随堂测评的方式开展本科课程教学测评，根据测评结果编制分析报告并反馈相关学院和任课教师，对测评成绩较低的教师实行约谈、听课制度，帮助教师改进教学。评价内容注重学生学习过程和学习效果，包括上课守时、尊重学生、课前准备、课堂组织、成绩评定、时间把握、 批改作业、学习反馈、课堂讲课、学生收获、主动答疑、课堂讲解、课堂纪律、学生提问、知识掌握等17个子指标和关于课程改进建议的1个开放指标。</p>  
<!--                       <h4>厦门大学本科课程教学测评报告</h4> -->
<!--                       <ul id="reportsList_1"> -->
<!--                       </ul> -->
                                       
                    </div>
                    
                    <div id="QM_2"  style="display:none">
                      <h3>学生学习经历调查</h3>
                      <p>学校基于学生学习“增值”的理念，每年坚持开展新生、毕业生学习经历调查。调查内容包括学习兴趣、学习态度、学习方法、学习效率、课堂教学、课外科创活动、师生关系、教学管理制度安排、学生管理、后勤服务等。基于调查结果，形成“学生学习经历调查报告”，为进一步改进教学提供实证依据。新生学习经历调查关注学生个体学习体验，注重对学生学习过程、学习效果和学习期待的评估。毕业生学习经历调查关注学生个体学习体验， 注重学生对人才培养过程满意度及学生自我成长评价的评估。</p>   
<!--                       <h4>学生学习经历调查报告</h4> -->
<!--                       <ul id="reportsList_2"> -->
<!--                       </ul> -->
                    </div>
                    
                    <div id="QM_3"  style="display:none">
                      <h3>常态数据动态监测</h3>
                      <p>教学基本状态数据是对本科教育质量最直观的反映。早在90年代末，学校就开始动态采集教学基本状态数据。经过十几年的发展， 逐步形成一套教学运行状态与教学质量评价的数据依据与统计方式，依托教务管理信息化平台，动态采集教学运行基本状态数据，在对数据统计、 分析的基础上监测教学质量的状态与发展，形成每学年的报表及报告，定期总结前期教学改革的成就，分析存在的问题，实时、客观、常态化把控教学运行状态和教学质量情况，为人才培养的顶层设计提供科学依据。</p>                      
<!--                      <h4>厦门大学本科生教务基本情况统计表</h4> -->
<!--                       <ul id="reportsList_3"> -->
<!--                       </ul> -->
                     
<!--                      <h4>厦门大学本科教学质量报告</h4> -->
<!--                      <ul id="reportsList_4"> -->
<!--                       </ul> -->
                     
                    </div>
                    
                    <div id="QM_4"  style="display:none">
                      <h3>日常教学质量监督</h3>
                      <p>学校不断完善日常教学质量监督机制，建立包括教学委员会制度、教学督导制度、党政领导干部听课制度、日常教学检查制度与教学信息反馈制度等，形成行政管理人员、教师、学生以及社会用人单位多层级的相互制约、相互监督、共同参与的监督体系，实现对本科教学运行过程的实时监测、预警和调控。 </p>               
                    </div>
               </div>

                <div class="clearfix"></div>
            </div>
        </div>

		<footer>
        <div class="footer-inner">
            <div style="display: inline-block;">
                <img src="../img/footer-logo.png" style="margin-top: -25px;">
            </div>
            <div style="display: inline-block">
                <span>厦门大学教务处</span><span>电话：0592-2137315</span><br>
                <span>地址：福建省厦门市思明区思明南路422号</span><span>邮政编码：361005</span><br>
            </div>
        </div>
        <div class="footer-boot">
            <div style="float:right;margin-right: 200px;color:white;">
                <span>©2013</span>
                <span>厦门大学</span>
                <span>版权所有</span>
            </div>
        </div>
    </footer>

    </div>

<script src="../js/lib.js"></script>
<script src="../js/app.js"></script>
<Script language="javascript"> 
var Request = new Object();
Request = GetRequest();
navbarChange(Request['id'],5);
</Script>

</body>
</html>