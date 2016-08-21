<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${param.language}" scope="session"/>
<fmt:setBundle basename="ApplicationResources" var="mykey" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Multi Layer Products - free website templates</title>
<meta name="keywords" content="multi layer, products, free website templates, XHTML CSS" />
<meta name="description" content="Multi Layer Products - free website template provided by templatemo.com" />
<link href="./css/templatemo_style.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery.ennui.contentslider.css" rel="stylesheet" type="text/css" media="screen,projection" />
</head>
<body>
<div id="templatemo_header_wrapper">

	<div id="templatemo_header">
    
    	<div id="site_title">
            <h1><a href="#" target="_parent">
                <img src="./images/templatemo_logo.png" alt="Web Templates" />
                <span>厦门大学本科教学质量保障体系</span>
            </a></h1>
        </div>
        
        <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="Search" alt="Search" id="searchbutton" title="Search" />
            </form>
        </div>
        <div class="cleaner"></div>
	</div><!-- end of header -->
    
   <div id="templatemo_menu">
		<div  class="kb">
            <ul id="nav">
	    <li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down">首页</span></a>
		</li>
		
           <li class="top"><a href="evaluate.jsp" id="contacts" class="top_link"><span class="down">本科评估</span></a>
		</li>
		
		<li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down">课程评价</span></a>
		</li>
		
	 <li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down">学习经历</span></a>
		</li>			
		
	 <li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down">学习经历</span></a>
		</li>	
		
		<li class="top"><a href="#nogo27" id="contacts" class="top_link"><span class="down">教学研究</span></a>
		</li>
		
		<li class="top"><a href="login.jsp" id="contacts" class="top_link"><span class="down">登陆</span></a>
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
                                    <h2>概况概况</h2>
                                    <p>Aliquam erat volutpat. Maecenas eget nisl id nisi consequat ultrices et  eu nunc. Praesent ac leo vel dolor rutrum egestas. Aliquam suscipit  vulputate arcu, quis congue ipsum laoreet sed.</p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                               <a href="http://www.mycodes.net/" target="_parent"><img src="images/slider/templatemo_slide02.jpg" alt="Template 1" /></a>
								</div>
                                                                
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>Suspendisse sed odio ut mi auctor blandit</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="./images/slider/templatemo_slide01.jpg" alt="Template 2" /></a>
                                </div>
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>Suspendisse sed odio ut mi auctor blandit</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="./images/slider/templatemo_slide03.jpg" alt="Template 3" /></a>
                                </div>
                            </div><!-- End cs_article -->
                            
                            <div class="cs_article">
                                <div class="left">
                                    <h2>Suspendisse sed odio ut mi auctor blandit</h2>
                                    <p>Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. </p>
                                    
                                    <div class="button"><a href="#">Read more</a></div>
                               	</div>
                                <div class="right">
                                <a href="#" target="_parent"><img src="./images/slider/templatemo_slide04.jpg" alt="Template 4" /></a>
                                </div>
                            </div><!-- End cs_article -->
                      
                        </div><!-- End cs_slider -->
                    </div><!-- End cs_wrapper -->
                </div><!-- End contentslider -->
                
                <!-- Site JavaScript -->
                <script type="text/javascript" src="./js/jquery-1.3.1.min.js"></script>
                <script type="text/javascript" src="./js/jquery.easing.1.3.js"></script>
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
    
    	<h1><fmt:message  bundle="${mykey}" key="report"/></h1>
        
        <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent aliquam velit a magna sodales quis elementum ipsum auctor. Quisque sem orci, malesuada eu  blandit et, bibendum facilisis mi. Vestibulum justo tortor, congue ut  cursus vel, fringilla a felis.</p>
        
        <div class="cleaner_h30"></div>
    
        <div class="services_section">
        
            <h4><a href="#">我是报告1</a></h4>

          <div class="services_content">
            
       	    <div class="right"><img src="./images/service_01.png" alt="image 1" /></div>
                
                <div class="left">
                	<p>tpat. Donec ac aliquam neque. Sed tellus diam,  consequat nec volutpat et, cursus ac nisi. Mauris in risus in diam  </p>
		    </div>
                
                <div class="cleaner"></div>
            </div>
            
            

        </div>
        
         <div class="services_section">
        
            <h4><a href="#">Duis convallis mauris a sapien</a></h4>
            
	      <div class="services_content">
            
           	  <div class="right">
                	<img src="./images/service_02.png" alt="image 2" />
                </div>
                
                <div class="left">
                
                	<p>Suspendisse pulvinar nulla sed sapien egestas suscipit. Lorem ipsum  dolor sit amet, consectetur adipiscing elit. Pellentesque habitant  morbi tristique senectus et netus et <a href="#">malesuada</a> fames ac turpis egestas.  Aenean in erat odio, in tempor quam.</p>
            </div>
                
                <div class="cleaner"></div>
            </div>
        </div>
        
        <div class="services_section">
        
            <h4><a href="#">Vestibulum ante ipsum primis</a></h4>

        <div class="services_content">
            
           	  <div class="right">
                	<img src="./images/service_03.png" alt="image 3" />
                </div>
                
                <div class="left">
                
                	<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per  inceptos himenaeos. Fusce consequat, erat vel <a href="#">vulputate malesuada</a>,  ipsum ligula porttitor purus, eget porta odio diam eget tellus. Nulla  tincidunt auctor justo non interdum. Cras sed diam nisi. Integer quis  purus augue.</p>
            </div>
                
                <div class="cleaner"></div>
            </div>
        </div>
        
    </div> <!-- end of templatemo_content -->
        
       <div id="templatemo_sidebar">

           <div id="news_section">
                
                <h2>文件文件哦</h2>
   
                <div class="news_box">
                    <a href="#">我是文件1</a>
             
                </div>
                
                <div class="news_box">
                    <a href="#">我是文件2</a>
                 
                </div>
                
                <div class="news_box">
                    <a href="#">我是文件2</a>
                   
                </div>
                
                 <div class="news_box">
                    <a href="#">我是文件4</a>
                   
                </div>
                
                <div class="cleaner"></div>  
                   
            </div>
            
             <div class="section_rss_twitter">
        
                <div class="rss_twitter twitter">
                <a href="#" target="_parent">FOLLOW US <span>on Twitter</span></a>
                </div>
                
                <div class="margin_bottom_20"></div>
                
                <div class="rss_twitter rss">
                <a href="#">SUBSCRIBE <span>rss feeds</span></a>
                </div>
            
            </div>
        
            <div class="cleaner"></div>
        </div> <!-- end of sidebar -->

	<div class="cleaner"></div>
</div>
</div>
</div>
        
<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">

        <ul class="footer_menu">
            <li><a href="index.html">Home</a></li>
            <li><a href="products.html">Products</a></li>
            <li><a href="blog.html">Blog</a></li>
            <li><a href="gallery.html">Gallery</a></li>
            <li class="last_menu"><a href="contact.html">Contact</a></li>
        </ul>
    
        Copyright (c) 2048 <a href="#">Your Company Name</a> | 
        Designed by <a href="#" target="_parent">Free Website Templates</a> | 
        Validate XHTML &amp; 
        		 CSS
    
</div> <!-- end of footer -->

</div> <!-- end of footer_wrapper -->

</body>
</html>