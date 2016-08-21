<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String value = (String)request.getSession().getAttribute("language"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${param.language}" scope="session"/>
<fmt:setBundle basename="ApplicationResources" var="mykey" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Multi Layer - free website templates</title>
<meta name="keywords" content="multi layer, free website templates, XHTML CSS" />
<meta name="description" content="Multi Layer - free website template provided by templatemo.com" />
<link href="./css/templatemo_style.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery.ennui.contentslider.css" rel="stylesheet" type="text/css" media="screen,projection" />
<link href="./css/forlist.css" rel="stylesheet" type="text/css" media="screen,projection" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/jquery-1.3.1.min.js"></script>
<script type="text/javascript">
function getLatestNews(){
	$.ajax({
        type: 'get',
        url: "getallnews",
        data: {
        	pagination:"false"
        },
        success: function(newsListJsonStr){
        	var dl_body = $("#newslist");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		for (var i = 0 ;i < newsList.length;i++){
        			var publishTime = new Date(parseInt(newsList[i].n_publishtime));
        			//var publishTime = new Date(parseInt(newsList[i].n_publishtime)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        		 	var year = publishTime.getFullYear();
        			var month = publishTime.getMonth() + 1;
        			var date = publishTime.getDate();
        			var hour = publishTime.getHours();
        			var minute = publishTime.getMinutes();
        			var second = publishTime.getSeconds(); 
        			//str += "<dd>"+year+"年"+month+"月"+date+"日"+hour+"时"+minute+"分"+second+"秒"+"</dd><dt><a href='#'>"+newsList[i].n_titles+"</a></dt>";
        			//str += "<dd>"+publishTime+"</dd><dt><a href='#'>"+newsList[i].n_titles+"</a></dt>";
        			str += "<dd>"+year+"年"+month+"月"+date+"日"+"</dd><dt><a href='#'>"+newsList[i].n_titles+"</a></dt>";
          	}
        		dl_body.html(str);
        	}
		},
        error: function(){alert("wrong");}
        	
	});
}

$(document).ready(function () { 
	getLatestNews();
	$("#moreNews").click(function(){
		var value='<%=session.getAttribute("language")%>';
		$("#language").val = value;
		$("#getnews").submit();
	});
	
	$("#zhongwen").click(function(){
		$.ajax({
	        type: 'get',
	        url: "setlanguagesession",
	        data: {
	        	type:"chinese"
	        },});
	});
	
	$("#yingwen").click(function(){
		$.ajax({
	        type: 'get',
	        url: "setlanguagesession",
	        data: {
	        	type:"english"
	        },});
	});
	$("#shabi").click(function(){
		alert("sssssssss");
		var value='<%=session.getAttribute("language")%>';
		alert(value);
	});
});

stuHover = function() {
	var cssRule;
	var newSelector;
	for (var i = 0; i < document.styleSheets.length; i++)
		for (var x = 0; x < document.styleSheets[i].rules.length ; x++)
			{
			cssRule = document.styleSheets[i].rules[x];
			if (cssRule.selectorText.indexOf("LI:hover") != -1)
			{
				 newSelector = cssRule.selectorText.replace(/LI:hover/gi, "LI.iehover");
				document.styleSheets[i].addRule(newSelector , cssRule.style.cssText);
			}
		}
	var getElm = document.getElementById("nav").getElementsByTagName("LI");
	for (var i=0; i<getElm.length; i++) {
		getElm[i].onmouseover=function() {
			this.className+=" iehover";
		};
		getElm[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" iehover\\b"), "");
		};
	}
};
if (window.attachEvent) window.attachEvent("onload", stuHover);
</script>
</head>

<body>
<div id="templatemo_header_wrapper">

	<div id="templatemo_header">
    
    	<div id="site_title">
            <h1><a href="#" target="_parent">
                <img src="images/templatemo_logo.png" alt="Web Templates" />
                <span>厦门大学本科教学质量保障体系</span>
            </a>
            </h1>
        </div>
        
        <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="搜索" alt="Search" id="searchbutton" title="Search" />
      <!--       <input type="button" name="Search" value="登陆" alt="Search" id="searchbutton" title="Search" onclick="window.open('more_news.jsp')"/> --> 
                <a id = "zhongwen" href="index.jsp?language=zh_CN">中文</a>|
                <a id = "yingwen" href="index.jsp?language=en_US">英文</a>   
            </form>
        </div>
        
        
        <div class="cleaner"></div>
	</div><!-- end of header -->
    	<div id="templatemo_menu">
		<div  class="kb">
            <ul id="nav">
            
	    <li class="top"><a href="#" id="shabi" class="top_link">
	    	<span class="down">
	    		<fmt:message  bundle="${mykey}" key="index"/>
	    	</span></a>
		</li>
		
           <li class="top"><a  id="test1" class="top_link"><span class="down"><fmt:message  bundle="${mykey}" key="evaluate"/></span></a>
		</li>
		
		<li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down"><fmt:message  bundle="${mykey}" key="courseevaluate"/></span></a>
		</li>
		
	 <li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down"><fmt:message  bundle="${mykey}" key="experience"/></span></a>
		</li>			
		
		
		<li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down"><fmt:message  bundle="${mykey}" key="rearch"/></span></a>
		</li>
		
		<li class="top"><a href="login.jsp" id="contacts" class="top_link"><span class="down"><fmt:message  bundle="${mykey}" key="login"/></span></a>
		</li>
            </ul>
        </div>			
    	</div><!-- end of templatemo_menu -->
        
        <div id="templatemo_banner">
            
                <div id="one" class="contentslider">
                    <div class="cs_wrapper">
                        <div class="cs_slider">
                        
                            <div class="cs_article">
                            	
                                <div class="left">
                                    <h2>资讯一</h2>
                                    <p>Aliquam erat volutpat. Maecenas eget nisl id nisi consequat ultrices et  eu nunc. Praesent ac leo vel dolor rutrum egestas. Aliquam suscipit  vulputate arcu, quis congue ipsum laoreet sed.</p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                               <a href="http://www.mycodes.net/" target="_parent"><img src="images/slider/templatemo_slide02.jpg" alt="Template 1" /></a>
								</div>
                                                                
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>资讯二</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="images/slider/templatemo_slide01.jpg" alt="Template 2" /></a>
                                </div>
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>资讯三</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="images/slider/templatemo_slide03.jpg" alt="Template 3" /></a>
                                </div>
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>资讯四</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="images/slider/templatemo_slide04.jpg" alt="Template 4" /></a>
                                </div>
                            </div><!-- End cs_article -->
                      
                        </div><!-- End cs_slider -->
                    </div><!-- End cs_wrapper -->
                </div><!-- End contentslider -->
                
                <!-- Site JavaScript -->
                <script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
                <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
                <script type="text/javascript" src="js/jquery.ennui.contentslider.js"></script>
                <script type="text/javascript">
                    $(function() {
                    $('#one').ContentSlider({
                    width : '920px',
                    height : '200px',
                    speed : 800,
                    easing : 'easeInOutBack'
                    });
                    });
                </script>
                <div class="cleaner"></div>
   
        </div>

</div> <!-- end of header_wrapper -->

<div id="templatemo_content_wrapper_outer">
<div id="templatemo_content_wrapper_inner">
<div id="templatemo_content_wrapper">

	<div id="templatemo_content">

        <h2><fmt:message  bundle="${mykey}" key="latestnews"/></h2>
        <!-- ################################################### -->
			<dl class="list" id="newslist"> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
			</dl>        
        <!-- ################################################### -->
        
        
        
       	<div class="cleaner"></div>
        <div class="button float_l" id="moreNews"><a><fmt:message  bundle="${mykey}" key="readmore"/></a></div>
        <div class="cleaner_h40"></div>  
            <!-- <h2>Testimonials</h2>
            
            <blockquote>
            <p>In ac libero urna. Suspendisse sed odio ut mi auctor blandit. Duis luctus nulla metus, a vulputate mauris. Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor.</p>
            
            <cite>Web Designer - <span><a href="http://www.mycodes.net" target="_parent" title="源码之家">源码之家</a></span></cite></blockquote>
            
            <div class="cleaner_h40"></div> -->
            
            <h2><fmt:message  bundle="${mykey}" key="latestreport"/></h2>
            <div id="recent_project">
              <!--  <ul>
                    <li>
                        <a href="#" title="Pro"><img src="images/gallery/thumbnail/image_04.jpg" alt="web template 1" /></a><h5>Professional</h5>
                    </li>
                    <li>
                        <a href="#" title="Motor"><img src="images/gallery/thumbnail/image_06.jpg" alt="web template 2" /></a><h5>Motorcycle</h5>
                    </li>
                </ul>  -->
                <dl class="list"> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
					<dd>2008年5月25日 </dd><dt><a href="#">床前明月光</a></dt> 
				</dl> 
                <div class="cleaner"></div>
                <div class="button float_l"><a href="more_report.jsp"><fmt:message  bundle="${mykey}" key="readmore"/></a></div>
        	</div>
            

            
            
        </div> <!-- end of templatemo_content -->
        
        <div id="templatemo_sidebar">

           <div id="news_section">
                
                <h2>最新问卷</h2>
   
                <div class="news_box">
                    <a href="#">我是问卷一</a>
                  <p>我是问卷一啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</p>
                </div>
                
                <div class="news_box">
                    <a href="#">问卷二</a>
                    <p>我是问卷啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</p>
                </div>
                
                <div class="news_box">
                    <a href="#">问卷三</a>
                    <p>我是问卷啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</p>
                </div>
                
                <div class="cleaner"></div>  
                   
            </div>
            
            <!-- <div class="section_rss_twitter">
        
                <div class="rss_twitter twitter">
                <a href="#" target="_parent">FOLLOW US <span>on Twitter</span></a>
                </div>
                
                <div class="margin_bottom_20"></div>
                
                <div class="rss_twitter rss">
                <a href="#">SUBSCRIBE <span>rss feeds</span></a>
                </div>
            
            </div> -->
        
        	<div class="section_rss_twitter">
         		<div class="rss_twitter twitter">
                <a href="#" target="_parent">联系我们
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 <span> 嘿嘿嘿嘿</span>
                 </a>
                </div>
            
            </div> 
            <div class="cleaner"></div>
        </div> <!-- end of sidebar -->

	<div class="cleaner"></div>
</div>
</div>
</div>
<form id="getnews" action="getallnews" method="post">
	<input type="hidden" id = "language" >
</form>

  <body>
    <a href="./toHtml">生成静态页面</a>
  </body>
        
<!-- <div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">

        <ul class="footer_menu">
            <li><a href="index.html">Home</a></li>
            <li><a href="products.html">Products</a></li>
        Validate XHTML &amp; 
        		 CSS
    
</div> end of footer

</div> --> <!-- end of footer_wrapper -->
<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>