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

<script type='text/javascript' src='/IQA/dwr/interface/MenuDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/interface/SubMenuDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>kindeditor/kindeditor-min.js" charset="UTF-8"></script>
<script type="text/javascript">
	function getmenu(){
		var map={};
		MenuDao.getAllMenu(map,function callback(data){ 
			for(o in data){
				document.getElementById("n_class").options.add(new Option(data[o].mb_ch_name,data[o].mb_id));
			}
		});
	}
	
	function class_change(){
		
		document.getElementById("n_subclass").options.length = 0;
		var mainid = document.getElementById("n_class");
		var value1 = mainid.options[mainid.selectedIndex].text;
		document.getElementById("maintype").value = value1;
		
		var data = mainid.value;
		SubMenuDao.getAllByField("smb_mbid",data,function callback(list){
			for(o in list){
				document.getElementById("n_subclass").options.add(new Option(list[o].smb_ch_name,list[o].smb_id));
			}
			
			var subid = document.getElementById("n_subclass");
			var value =	subid.options[subid.selectedIndex].text;
			document.getElementById("subtype").value = value;
			
		});
	}
	function getsubtype(){
		var id = document.getElementById("n_subclass");
		var value =	id.options[id.selectedIndex].text;
		document.getElementById("subtype").value = value;
	}
</script>
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
 
<script type="text/javascript">

$(function() {
/* 注册添加按钮的点击事件 */
	$("#addFile").click(addFile);

});
var addFile = function() {

	var $file = $("<input type='file' name='UplodeName'/>");
	$("#fileUplodeDiv").append($file).append($("<br>"));
};
getmenu();

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
    
    <div class="formtext">Hi，<b>admin</b>，欢迎您使用新闻发布功能！</div>
   	 	<form action="<%=basePath%>publishnews" method="post" enctype="multipart/form-data">
        	<ul class="forminfo">     
            	 <li>
                	<label>所属栏目<b>*</b></label>
                	<div>
                		<select  name="n_class" id = "n_class" onchange="class_change()">
                			<option selected="selected">请选择所属栏目</option> 
                		</select>
                		子类别<b>*</b>
                		<select  name="n_subclass" id = "n_subclass" onchange="getsubtype()" >
                		</select>
                		<input type="hidden" id="maintype" name="maintype"/>
                		<input type="hidden" id="subtype" name="subtype"/>
                	</div>
             	</li>
             	<li>
                	<label>语言<b>*</b></label>
                	<div>
                		<select  name="n_version" id = "n_version">
                			<option selected="selected">请选择版本</option>
                			<option >中文</option>
                			<option >English</option> 
                		</select>
                	</div>
             	</li>
             	<li>
                	<label>新闻标题<b>*</b></label>
                	<input class="dfinput" name="n_titles" type="text"  placeholder="请填写新闻标题" style="width:518px;"/>
               </li>
               <li>
               		<label>新闻作者<b>*</b></label>
               		<input class="dfinput" name="n_author" type="text"  placeholder="请填写新闻作者"  style="width:250px;"/>
               		<input class="Wdate" id="d121" name = "n_publishtime" type="text" placeholder="请选择时间" onfocus="WdatePicker({isShowWeek:true})"style="width:150px;"/>
               </li>	
       			<li>
               		<label>新闻概要</label>
               		<input class="dfinput" name="n_summary" type="text"  placeholder="请填写新闻摘要"  style="width:518px;"/>
               </li>
               <li><label>通知内容<b>*</b></label>
                   <textarea id="editor" name="n_content" rows="" cols="" style="width: 800px; height: 200px; visibility: hidden;">
		 				
		 			</textarea>
               </li> 
               <li><label>添加图片<b>*</b></label><input type="button"	value="添加图片" id="addFile" ></li>
               <li>
                   <input type="file" name="UplodeName" value="">
				   <div id="fileUplodeDiv"></div>
               </li>
      			<li><label>&nbsp;</label><input name="Submit" type="submit" value="提交"/></li>
            </ul>
      	</form>
    
</body>
</html>