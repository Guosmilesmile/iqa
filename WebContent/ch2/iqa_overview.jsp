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
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="../css/xmu_base.css" type="text/css" rel="stylesheet">
	<link href="../css/style-define.css" type="text/css" rel="stylesheet" >
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="../js/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/commonJs.js"></script>
	<script type="text/javascript">
	String.prototype.format = String.prototype.f = function () {
        var s = this,
            i = arguments.length;

        while (i--) {
            s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
        }
        return s;
    };
    /*日期数据格式化函数*/
    function formatterdate(val) {
    	if(val != null && val != ""){
    		var date = new Date(val);
            return date.getFullYear() + '-' + addZero((date.getMonth() + 1)) + '-' + addZero(date.getDate());
    	}
    }
  //将月份和日不足10的再前面补零
    function addZero(s) {
        return s < 10 ? '0' + s: s;
    }
    
	function toPage(currentPage,n_subclass){
		window.location.href="GetCh2NewsList?page="+currentPage+"&n_subclass="+n_subclass;
	}
	
	var pageSize = 15;//每页的新闻数量
	var total = 0;//总的新闻数
	var currentPage = 1;//当前页码
	var pageNum = 7;//控制最多显示7个页面数
	var pageHalf = pageNum/2;//暂时等于3，表示中间页面两边均等的页面数
	var pages = 1;//总的页数
	function getQuestionnaires(){
		$.ajax({
	        type: 'post',
	        url: "<%=basePath%>GetCH2NewsList",
	        data: {
	        	pagination:"true",
	        	rows:pageSize,
	        	menuId:36,
	        	page:currentPage,
	        },
	        async: false,
	        dataType:'json',
	        success: function(newsListJson){
	        	var div_body = $("#iqaProjects");
	        	var newsList = newsListJson.rows;
	        	total = newsListJson.total;
	        	if(newsList.length > 0){
	        		var trList = "";
	            	var tr = "<tr ><td><i class='icon icon-exclamation-sign'></td> <td style='padding:5px 5px;width:500px;display:inline-block;border-style:None;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;'><a href='{0}'>{1}</a></td><td>{2}</td></tr>";
	            	for(var i = 0; i < newsList.length; i++)
	            	{
	            		var tempTitle = "";
	            		if(newsList[i].n_titles.length > 26)
	            			tempTitle = newsList[i].n_titles.substr(0,26)+"...";
	            		else
	            			tempTitle = newsList[i].n_titles;
	            		trList = trList+tr.format("Third-Page.jsp?contentId="+newsList[i].n_id, tempTitle, formatterdate(newsList[i].n_publishtime));
	            	}
	        		div_body.html(trList);
	        		$(".digg").html("");
	        		//翻页
	        		pages=parseInt(total/pageSize);
	        		if(total%pageSize != 0)
	        			pages+=1;
	        		pageHalf=parseInt(pageNum/2);
	        		if(currentPage==1)
	        		{
	        			$(".digg").append("<a class='disabled'>首页 </a><a class='disabled'>上一页</a>");
	        		}else{
	        			$(".digg").append("<a class='pageBtn' id='pageBtnfirst'>首页 </a><a class='pageBtn' id='pageBtnpre'>上一页</a>");
	        		}
	        		if(pageNum<pages)
	        		{
	        			if(currentPage<=pageHalf+1){
	        				for(var i=1;i<=pageNum;i++)
	        				{
	        					if(i == currentPage)
	        						$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
	        					else
	        						$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
	        				}
	        			}else if((pages-currentPage) < pageHalf){
	        		        for(var i=pages-(pageNum-1);i<=pages;i++){
	        					if(i == currentPage){
	        		               	$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
	        		            }else{
	        		               	$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
	        		            }
	        		        }
	        		    }else{
	        		        for(var i=currentPage-pageHalf;i<=parseInt(currentPage)+pageHalf;i++){
	        					if(i==currentPage){
	        						$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
	        		            }else{
	        		               	$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
	        		            }
	        		        }
	        		    }
	        		}else{
	        		    for(var i=1;i<=pages;i++){
	        				if(i==currentPage){
	        					$(".digg").append("<a class='pageBtn current' id='pageBtn"+i+"'>"+i+"</a>");
	        				}else{
	        					$(".digg").append("<a class='pageBtn' id='pageBtn"+i+"'>"+i+"</a>");
	        		        }
	        		    }
	        		}
	        		if(currentPage==pages){
	        		    $(".digg").append("<a class='disabled'>下一页</a><a class='disabled'>尾页</a>");
	        		}else{
	        		    $(".digg").append("<a class='pageBtn'  id='pageBtnnext'>下一页</a><a class='pageBtn'  id='pageBtnlast'>尾页</a>");
	        		}
	        		
	        		$(".readQuestionnaire").click(function(){
	   				//	var id = $(this).attr("id").toString().substring(11);
	   					window.location.href="<%=basePath%>login.jsp";
	   				});
	        		$('.pageBtn').click(function(){
	        	 		var id = $(this).attr("id").toString().substring(7);
	        	 		/* alert(id); */
	        	 		if(id == 'first'){
	        	 			//跳转到第一页
	        	 			currentPage = 1;
	        	 		}else if(id == 'last'){
	        	 			//跳转到最后一页
	        	 			currentPage = pages;
	        	 		}else if(id == 'pre'){
	        	 			//跳转到上一页
	        	 			currentPage --;
	        	 		}else if(id == 'next'){
	        	 			//跳转到下一页
	        	 			currentPage ++;
	        	 		}else{
	        	 			//跳转到第id页
	        	 			currentPage = id;
	        	 		}
	        	 		 if(currentPage <= pages){
	        	 			getQuestionnaires();
	        	 		} 
	        	 	});
	        	}
	        	else{
	        		var emptyStr = "<tr><td><div class='question'><h3>暂无相关信息</h3>"
	        		    +"</div></td></tr>";
	    		    div_body.html(emptyStr);
	        	}
			},
	        error: function(){}
		});
	}
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
	
	function changeContent(id)
    {
    	if(id==1)
    	{
    		document.getElementById("main-content").innerHTML="<h3>理论体系</h3>"+
    		"<p>厦门大学从2006年起，建立常态化的“年检”制度。近十年来，按照边探索、边实践、边完善的思路，逐渐探索出具有厦门大学特色，以自我评估为主体，以常态数据监控为手段，以信息反馈和质量持续提升为目标，贯通培养目标、培养模式、过程监控、培养结果等人才培养全过程的内部质量保障系统（Internal Quality Assurance，简称IQA)。学校认为，教学质量保障体系是一个动态的、可分解、可操作、可控制的相对闭环管理流程，它通过整合内部各类教学资源、协调教学过程的各个环节，构成一个在教学质量上能够实现自我约束、自我激励、自我改进、自我发展的有效运行机制。学校在具体质量保障过程中形成了如下基本原则：</p>"+
    		"<p>1.以人为本原则。教育质量的核心是人才培养质量，学校坚持以人为本，各种监测手段和方法的使用，最终目标应落到提高课堂教学效果和学生学习效果上，这是评价质量保障体系是否有效的重要标志。</p>"+
    		"<p>2.整体性原则。对于教学质量管控，从目标设计、模式选择、过程监控和结果输出等进行整体监测与评估。确立质量保障是学校一个整体系统工程。</p>"+
    		"<p>3.可控性原则。教学质量有标准和底线，是可操作的、可控制的。通过设定指标和测定具体数据，以教学状态数据为依据，对教学质量进行量化监控。</p>"+
    		"<p>4.持续性原则。内部质量保障体系建设的路径、方法选择和手段应用是经济节约的，充分调动和利用现有教育资源并发挥其最大效益，最终能够使整个质量监测可持续发展。</p>";
    	}
    	else if(id==2)
    	{
    		document.getElementById("main-content").innerHTML="<h3>监控流程</h3>"+
                "<p>厦门大学经过不断探索实践，形成了以年度自我评估为“抓手”，以常态数据监测为支撑，以日常教学监控、课程教学评价、学生学习反馈以及毕业生跟踪调查等为手段，涵盖人才培养全过程的“自我检查→自我诊断→自我反馈→自我整改”的质量保障监控流程。</p>"+
				"<img src=\"../img/iqajiankong.png\" style=\"width:100%\"/>";
    	}
    	else if(id==3)
    	{
    		document.getElementById("main-content").innerHTML="<h3>组织架构</h3>"+
                "<p>厦门大学建立了以“四维联动”为特征的教学质量保证组织架构。由分管校领导牵头，教务处、教师发展中心、研究生院、学生处、团委、院系等多机构合作，教学督导组、教师、学生、用人单位等多主体参与，教学管理、学生事务管理、行政管理、学术管理等多系统整合，学术委员会、教学委员会、院系工作组多层次评估，共同协力确保学校本科教学质量。</p>"+
                "<img src=\"../img/iqazuzhi.png\" style=\"width:100%\"/>";
    	}
    	else if(id==4)
    	{
    		document.getElementById("main-content").innerHTML="<h3>联合国IQA项目</h3>"+
                "<table id=\"iqaProjects\"></table><div class=\"digg\"></div>";
    		getQuestionnaires();
    	}
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
    <style type="text/css">
	DIV.digg A.disabled {
	BORDER-RIGHT: #eee 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #eee 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #eee 1px solid;
	COLOR: #ddd;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #eee 1px solid;
	}
	DIV.digg A.current {
	BORDER-RIGHT: #005590 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #005590 1px solid;
	PADDING-LEFT: 5px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #005590 1px solid;
	COLOR: #fff;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #005590 1px solid;
	BACKGROUND-COLOR: #005590;
	}
	DIV.digg A {
	BORDER-RIGHT: #aaaadd 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #aaaadd 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #aaaadd 1px solid;
	COLOR: #005590;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #aaaadd 1px solid;
	TEXT-DECORATION: none;
	cursor: pointer;
	}
	DIV.digg {
	margin-top:5px;float:right;
	}
	TD{
	padding:5px;
	}
	</style>
</head>

<body  class="style-bright">
    <div id="wrapper">
        <!--  Header-->
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
                    <span class="sub-nav-title">IQA体系</span>
                    <!-- left menu ================================================== -->
                    <ul>
                        <li><a href="iqa_overview.jsp?id=1"><i class="icon icon-chevron-right"></i>理论体系</a></li>
                        <li><a href="iqa_overview.jsp?id=2"><i class="icon icon-chevron-right"></i>监控流程</a></li>
                        <li><a href="iqa_overview.jsp?id=3"><i class="icon icon-chevron-right"></i>组织架构</a></li>
                        <li><a href="iqa_overview.jsp?id=4"><i class="icon icon-chevron-right"></i>联合国IQA项目</a></li>
                    </ul>
                </div>
                <div id="main-content" class="span9" >
                    <h3>理论体系</h3>
                    <p>厦门大学从2006年起，建立常态化的“年检”制度。近十年来，按照边探索、边实践、边完善的思路，逐渐探索出具有厦门大学特色，以自我评估为主体，以常态数据监控为手段，以信息反馈和质量持续提升为目标，贯通培养目标、培养模式、过程监控、培养结果等人才培养全过程的内部质量保障系统（Internal Quality Assurance，简称IQA)。学校认为，教学质量保障体系是一个动态的、可分解、可操作、可控制的相对闭环管理流程，它通过整合内部各类教学资源、协调教学过程的各个环节，构成一个在教学质量上能够实现自我约束、自我激励、自我改进、自我发展的有效运行机制。学校在具体质量保障过程中形成了如下基本原则：</p>
                    <p>1.以人为本原则。教育质量的核心是人才培养质量，学校坚持以人为本，各种监测手段和方法的使用，最终目标应落到提高课堂教学效果和学生学习效果上，这是评价质量保障体系是否有效的重要标志。</p>
					<p>2.整体性原则。对于教学质量管控，从目标设计、模式选择、过程监控和结果输出等进行整体监测与评估。确立质量保障是学校一个整体系统工程。</p>
					<p>3.可控性原则。教学质量有标准和底线，是可操作的、可控制的。通过设定指标和测定具体数据，以教学状态数据为依据，对教学质量进行量化监控。</p>
					<p>4.持续性原则。内部质量保障体系建设的路径、方法选择和手段应用是经济节约的，充分调动和利用现有教育资源并发挥其最大效益，最终能够使整个质量监测可持续发展。</p>
                </div>

                <div class="clearfix"></div>
            </div>
        <footer>
        <div class="footer-inner">
            <div style="display: inline-block">
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
changeContent(Request['id']);
</Script>
</body>
</html>