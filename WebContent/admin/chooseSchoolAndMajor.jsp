<%@ page language="java" import="java.util.*,cn.edu.xmu.entity.*,cn.edu.xmu.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择要查看学生所属学院和专业</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
        function changeMajor(school){
        	//alert(school);
        	if(school == "数学"){
        		document.getElementById("major").innerHTML = "<option>--请选择专业--</option>"+
        			                                         "<option value='基础数学'>基础数学</option>"+
        		                                             "<option value='应用数学'>应用数学</option>";
        	}else if(school == "人文"){
        		document.getElementById("major").innerHTML = "<option>--请选择专业--</option>"+
                                                             "<option value='中文'>中文系</option>"+
                                                             "<option value='历史'>历史系</option>";
        	}else if(school == "软件"){
        		document.getElementById("major").innerHTML = "<option>--请选择专业--</option>"+
                                                             "<option value='软件工程'>软件工程</option>"+
                                                             "<option value='数字媒体艺术'>数字媒体艺术</option>";
        	}else if(school == "国际"){
        		document.getElementById("major").innerHTML = "<option>--请选择专业--</option>"+
                                                             "<option value='国际经济与贸易'>国际经济与贸易</option>";
                            
        	}
        }
</script>
</head>

<body>  
    <form action="<%=basePath %>getallstudent" method="post">
    <select name="school" id="school" onChange="changeMajor(this.value)"> 
       <option>--请选择学院--</option>
       <option value="数学">数学学院</option> 
       <option value="人文">人文学院</option> 
       <option value="软件">软件学院</option> 
       <option value="国际">国际学院</option> 
    </select> 
    <select name="major" id="major">
       <option>--请选择专业--</option>
    </select>
    <input name="submit" type="submit" value="确定"></input>
    </form>
</body>
</html>