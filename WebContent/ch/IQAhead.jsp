<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
String pt = request.getContextPath();
String bp = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pt+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厦门大学本科教学质量保障体系</title>
<style type="text/css"></style>
<script type="text/javascript" src="<%=bp%>js/jquery-1.3.1.min.js"></script>
<script src="<%=bp%>js/myJs.js"></script>
<link rel="stylesheet" href="<%=bp%>css/mycss-cn.css">
<script type="text/javascript">

function load(){
	$("#navigation > li >a").bind('click',function(){
		var list=$("#navigation > li >a");
		for(var i=0;i<list.length;i++){list[i].className="";}
		$(this).addClass('current');
	});
	
}

function getAllMenus(){
	$.ajax({
        type: 'get',
        url: "<%=bp%>getMenu",
        data: {
        	headerMenu:1,
        },
        success: function(menuListJsonStr){
        	var ul_body = $("#navigation");
        	var str ="";
        	var menuList = eval('(' + menuListJsonStr + ')');
        	if(menuList.length > 0){
        		for (var i = 0 ;i < menuList.length;i++){
        			jspName = menuList[i].mb_en_name.replace(/[ ]/g,"");
        			
        			if(jspName == "FacultyDevelopment"){    
    					//当点击一级菜单为“教师fazhan”时，直接链接到具体内容的页面
        				str += "<li ><a href='http://ctld.xmu.edu.cn/' id='readOverview"+menuList[i].mb_id+"'>"+menuList[i].mb_ch_name+"</a></li> ";
    				}
        			else str += "<li onmouseover='displaySubMenu(this)' onmouseout='hideSubMenu(this)' > <a  href='<%=bp%>ch/"+jspName+".jsp?id="+menuList[i].mb_id+"' onclick='change_bg(this)'>"+menuList[i].mb_ch_name+"</a> <ul>";
        			var subMenuList = menuList[i].subMenus;
        			for (var j = 0; j < subMenuList.length; j++) {
        				subJspName = subMenuList[j].smb_en_name.replace(/[ ]/g,"");
        				if(subJspName == "Overview"){    
        					//当点击二级菜单为“概况”时，直接链接到具体内容的页面
        					str += "<li ><a href='#' class='readOverview' id='readOverview"+menuList[i].mb_id+"'>"+subMenuList[j].smb_ch_name+"</a></li> ";
        				}
        				else{
        					str += "<li><a href='<%=bp%>ch/"+jspName+"_"+subJspName+".jsp?id="+menuList[i].mb_id+"'>"+subMenuList[j].smb_ch_name+"</a></li> ";
        				}
        				
					}
        			str += "</ul>";
        			}
        			str += "</li>";
        			ul_body.html(str);
        			
        			//用于点击概况后的事件
            		$(".readOverview").click(function(){
       					var menuId = $(this).attr("id").toString().substring(12);
       					
       					var id = "";
       					$.ajax({
       				        type: 'post',
       				        url: "<%=bp%>getallnews",
       				        data: {
       				        	pagination:"false",
       				        	rows:4,
       				        	menuId:menuId,
       				        	n_subclass:"5"
       				        },
       				        success: function(newsListJsonStr){       				   
       				        	var newsList = eval('(' + newsListJsonStr + ')'); 
       				        	if(newsList.length > 0){
       				        	  id += newsList[0].n_id;
       				        	}
       				        	
       				        	if(id == ""){
       				        	     alert("该栏目暂时没有概况内容"); 
       				        	}
       				        	else{
       				        		window.location.href="<%=bp%>ch/document.jsp?contentId="+id;
       				        	}
       						},
       				        error: function(){/* alert("wrong"); */}       				        	
       					});       					
       				});
        			//点击概况后的事件结束
            		load();
        	}
		},
        error: function(){/* alert("wrong"); */}
	});
}
$(document).ready(function(){
	getAllMenus();
});
</script>
</head>
<body>
<div class="header">
<div class="top"></div>
<a href="<%=bp%>ch/index.jsp"><img class="logo" src="<%=bp%>/images/newModel/logo.png"/></a>
<ul id="navigation"> 
</ul>
<div class="info">
<div class="clear"></div>
  <input id="headerbutton" type="submit" value="" />
  <input id="headerinput" type="text" name="search" />
<div class="clear"></div>
 <input id="headerlogin" type="submit" value="login" />
 <a class="headerLink" href="<%=bp%>en/index.jsp">English</a>
</div>
</div>
</body>
</html>