<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>XMU IQA</title>
<script type='text/javascript' src='/IQA/dwr/interface/NewsDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>

<script type="text/javascript" src="../js/jquery-1.3.1.min.js"></script>
<script src="../js/myJs.js"></script>
<link rel="stylesheet" href="../kindeditor/skins/default.css">
<style type="text/css"></style>
<script type="text/javascript">
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}
function getNewsContent(){
	var Request = new Object();
	Request = GetRequest();
	var id = Request['contentId'];
	NewsDao.getNewsRecordById(id, function callback(data){
	var id = data.n_id;
	var author = data.n_author;
	var mainclass = data.n_class;
	var subclass = data.n_subclass;
	var summary = data.n_summary;
	var content = data.n_content;
	var title = data.n_titles;
	var version = data.n_version;
	//var publishTime = data.n_publishtime;
	//var year = publishTime.getFullYear();
	//var month = publishTime.getMonth() + 1;
	//var date = publishTime.getDate();
	//var hour = publishTime.getHours();
	//var minute = publishTime.getMinutes();
	//var second = publishTime.getSeconds(); 
	
//	var time = "发布时间："+year +"年"+month+"月"+date+"日    "+hour+"时"+minute+"分"+second+"秒";
	document.getElementById("newsTitle").innerHTML = title;
	//document.getElementById("publishTime").innerHTML = time;
	document.getElementById("newsContent").innerHTML = content;
});
}

$(document).ready(function (){
	getNewsContent();
});

</script>
</head>
<body>
<jsp:include page="IQAhead-en.jsp"></jsp:include>
    <!-- end .header -->
<div style="position: relative;width:1025px;margin: 0 auto;">
<div class="container">
  <div class="newscontent">
  <p class="newstitle" id="newsTitle"></p>
  <p class="newssubtitle" id="publishTime"></p>
  <hr />
  <p  id="newsContent"></p>
   <div class="attachment">
   <a href="#"></a>
   </div>
  </div>
   <!-- end .content -->
  </div>
  <!-- end .container -->
  </div>
<jsp:include page="IQAtail-en.jsp"></jsp:include>
</body>
</html>