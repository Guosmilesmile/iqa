<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布消息</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/select.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/my.js"></script>
<script type="text/javascript" src="<%=basePath%>kindeditor/kindeditor-min.js" charset="UTF-8"></script>

<script type="text/javascript">
		KE.show({
			 id : "editor",
		     width : "800px",
		     height : "200px",		    
		     resizeMode : 1,
		     allowFileManager : true,
		     /*图片上传的SERVLET路径*/
		     imageUploadJson : "${pageContext.request.contextPath}/uploadImage.html", 
		     /*图片管理的SERVLET路径*/     
		     fileManagerJson : "${pageContext.request.contextPath}/uploadImgManager.html",
		     /*允许上传的附件类型*/
		     accessoryTypes : "doc|xls|pdf|txt|ppt|rar|zip",
		     /*附件上传的SERVLET路径*/
		     accessoryUploadJson : "${pageContext.request.contextPath}/uploadaccessoryover"
		});
	</script>

</head>
<body>
	<div class="place">
       <span>位置：</span>
       <ul class="placeul">
          <li><a href="#">首页</a></li>
          <li><a href="#">系统设置</a></li>
       </ul>
    </div>
    
    <div class="formtext">Hi，<b>admin</b>，欢迎使用滑动新闻发布功能！</div>
   	 	<form action="<%=basePath%>publishslidenews" method="post" enctype="multipart/form-data">
        	<ul class="forminfo">     
             	<li>
                	<label>新闻标题<b>*</b></label>
                	<input class="dfinput" name="sn_titles" type="text"  placeholder="请填写新闻标题" style="width:518px;"/>
               </li>
               <li>
                	<label>语言<b>*</b></label>
                	<div>
                		<select  name="sn_version" id = "sn_version">
                			<option selected="selected">中文</option>
                			<option >English</option> 
                		</select>
                	</div>
             	</li>
               <li><label>通知内容<b>*</b></label>
                   <textarea id="editor" name="sn_content" rows="" cols="" style="width: 800px; height: 200px; visibility: hidden;">
		 			</textarea>
               </li> 
               <li><label>添加滚动图片<b>*</b></label>建议像素为1024*173</li>
               <li>
                   <input type="file" name="UplodeName" id = "UploadName" value="">
               </li>
      			<li><label>&nbsp;</label><input name="Submit" type="submit" value="提交"/></li>
            </ul>
      	</form>
    
</body>
</html>