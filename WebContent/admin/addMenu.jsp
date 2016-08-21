<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增栏目</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='/IQA/dwr/interface/MenuDao.js'></script>
<script type='text/javascript' src='/IQA/dwr/engine.js'></script>
<script type='text/javascript' src='/IQA/dwr/util.js'></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.1.min.js"></script>
<script type="text/javascript">
function getmenu(){
	MenuDao.getAllMenu(function callback(data){ 
		for(o in data){
			document.getElementById("mainMenu").options.add(new Option(data[o].mb_ch_name,data[o].mb_id));
		}
	});
}
function clearMenu(){
	document.getElementById("mainMenu").options.length = 0;
}
$(document).ready(function() {
	$("#menuType").change(function(){
		var menuValue = $("#menuType").val(); //获取Select选择的Value 
		var menuText=$("#menuType").find("option:selected").text(); //获取Select选择的Text 
		//alert(menuValue);
		if(menuValue == 2)//选择子栏目
		{
			$("#mainMenu").show();
			/*加载所有的主栏目*/
			getmenu();
			
		}
		else{
			$("#mainMenu").hide();
			clearMenu();
		}
		$("#type").attr('value',menuValue);
		//alert($("#type").attr('value'));
	}); 
	
	$("#mainMenu").change(function(){
		var menuText=$("#mainMenu").find("option:selected").text();
		$("#belongMain").attr('value',menuText);
		
	}); 
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

	<div class="formbody">
		<div id="usual1" class="usual">
			<div id="tab1" class="tabson">
				<div class="formtext">
					Hi，<b>admin</b>，欢迎您使用新增栏目功能！
				</div>
				<form action="<%=basePath%>addMenu" method="post">
					<ul class="forminfo">
						<li><label>栏目类别<b>*</b></label>
							<div >
								<select id="menuType">
									<option selected="selected" value='0'>请选择类别</option>
									<option value='1'>主栏目</option>
									<option value='2'>子栏目</option>
								</select>
								
								<select style="display: none;" id="mainMenu">
									<option selected="selected">请选择所属主栏目</option>
								</select>
							</div>
							<input class="dfinput" id="type" name="type" type="hidden" style="width: 518px;" />
							<input class="dfinput" id="belongMain" name="belongMain" type="hidden" style="width: 518px;" />
						</li>
							
						<li><label>栏目名称<b>*</b></label> <input class="dfinput"
							name="mb_ch_name" type="text" placeholder="请填写栏目中文名称"
							style="width: 518px;" /></li>
						<li><label>英文名称<b>*</b></label> <input class="dfinput"
							name="mb_en_name" type="text" placeholder="请填写栏目英文名称"
							style="width: 518px;" /></li>
						<li><label>&nbsp;</label><input name="Submit" type="submit"
							value="提交" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<!-- <script type="text/javascript"> 
         $("#usual1 ul").idTabs(); 
        </script> -->
</body>
</html>