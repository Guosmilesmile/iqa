<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String userid ="";
	Cookie[] cookies =request.getCookies();
	for(Cookie cookie : cookies){
    if(cookie.getName().equals("userid"))
    	userid=cookie.getValue();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>
<link href="<%=basePath%>css/filltable.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
<style type="text/css">
#tableids {
    background-color:transparent; 
    width:100px; 
    line-height:30px; 
    text-indent:8px;
    border:1px;
    border-bottom-style:none;
    border-top-style:none;
    border-left-style:none;
    border-right-style:none;
    disabled:disabled
    }
#searchDialog {
	background-color: white;
	border: 1px solid black;
	margin: 0 auto;
	z-index: 99999999999;
	position: absolute;
	left: 200px;
	top: 150px;
	height: 500px;
	width: 500px;
	display: none;
}

#ssearchDialog {
	z-index: 99999999;
}

#closepublish {
	position: absolute;
	left: 500px;
	top: 150px;
	width: 280px;
	height: 165px;
	z-index: 999;
	padding-left: 150px;
	padding-top: 25px;
	background-color: whitesmoke;
	display: none;
}
#topublishall {
	position: absolute;
	left: 500px;
	top: 150px;
	width: 200px;
	height: 100px;
	z-index: 999;
	padding-left: 150px;
	padding-top: 25px;
	background-color: whitesmoke;
	display: none;
}
#topublishpage {
	position: absolute;
	left: 500px;
	top: 150px;
	width: 200px;
	height: 100px;
	z-index: 999;
	padding-left: 150px;
	padding-top: 25px;
	background-color: whitesmoke;
	display: none;
}

#ifDelete {
	position: absolute;
	left: 500px;
	top: 150px;
}

#publishall {
	float: right;
	width: 60px;
	height: 30px;
}
#publishpage {
	float: right;
	width: 60px;
	height: 30px;
}
#closeall {
	float: right;
	width: 60px;
	height: 30px;
}
</style>
<script>
function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
};

	function gettablelist(pagenow,rownumber){
		$.ajax({
			type:'post',
			url:'<%=basePath %>gettablelist',
			data:{},
			success:function(ListJsonStr){
				var List = eval('(' + ListJsonStr + ')');
				var tbody = $('#usertable');
				var str ="";
				List = pagebean(List,'blue',pagenow,rownumber);
				if(List.length > 0){
					for(var i=0;i<List.length;i++){
						var tableid = List[i].t_tableid;
						var tablename = List[i].t_tablename;
						var t_publish = List[i].t_publish;
						var deadline = List[i].datetime;
						var d ="";
						var day = "";
						var month = "";
						var year = "";
						if(typeof  deadline=== 'undefined'){
							
						}else{
						 	d = new Date(deadline);
							day = d.getDate();
							month = d.getMonth()+1;
							year = d.getFullYear();
						}
						
						
						str+="<tr id=''>"+
							"<td><input name='' type='checkbox' value='"+tableid+"' id='' /></td>"+
							"<td><input class='tableids' id='tableid' value='"+tableid+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+year+"-"+month+"-"+day+"'"+
							"readonly='readonly'></input></td>";
							if(t_publish==0)
							
							str+="<td><input class='newsuneditable' id='tablename' value='未发布'"+
							"readonly='readonly'></input></td>"+
							"<td class='newseditable'><a href='#' class='tablelink publish'"+
							"id='publish_"+tableid+"'>发布 　</a><a href='#' class='tablelink import'"+
							"id='import_"+tableid+"'>　导入</a></td></tr>";
							else if(t_publish==1)
							str+="<td><input class='newsuneditable' id='tablename' value='已发布'"+
							"readonly='readonly'></input></td>"+
							"<td class='newseditable'><a href='#' class='tablelink canclepublish'"+
							"id='publish_"+tableid+"'>取消发布</a></td></tr>";
							
					}
				}
				tbody.html(str); 
			},error:function(){
				alert("fail");
			}
		});
	};
	
	function changepublish(flag,tableid){
		$.ajax({
			type:'post',
			url:'<%=basePath %>changepublish',
			data:{flag:flag,tableid:tableid},
			success:function(){
				alert('success');
				location.reload();
			},error:function(){
				alert('fail!');
			}
		});
	};
	function changeimport(tableid){
		$.ajax({
			type:'post',
			url:'<%=basePath %>changeImportServlet',
			data:{tableid:tableid},
			success:function(){
				alert('success');
				location.reload();
			},error:function(){
				alert('fail!');
			}
		});
	};
	$(document).ready(function() {
		$("#ssearchDialog").hide();
		$('#chooseul li:eq(0)').css('border', '1px solid black');
		var userid  = getCookie('userid');
		var username = getCookie('username');
		username = decodeURI(username);
		var pagenow = 1;
		var rownumber = 10;
		gettablelist(pagenow,rownumber);
		$('#closeall').live('click',function(){
				$('#closepublish').show();		
				/* var tableid = $(this).attr('id');
				tableid = tableid.substring(8);
				$('#ttableid').val(tableid);
				$('#closepublish').show(); */
				/* changepublish(1,tableid); */
		});	
		$('#publishall').live('click',function(){
			$('#topublishall').show();		
	    });	
		$('#publishpage').live('click',function(){
		    var tableid = "";
		     $('.tableids').each(function(){
					tableid += $(this).val();		    	    
	 				tableid += ",";
	 		});	 				
		    // tableid = tableid.substring(0,tableid.lenght()-1);
			$('#pagetableid').val(tableid); 
			$('#topublishpage').show();		
	    });	
		$('#canpublish').click(function(){
			$('#closepublish').hide();
		});
		$('#canpublishall').click(function(){
			$('#topublishall').hide();
		});
		$('#canpublishpage').click(function(){
			$('#topublishpage').hide();
		});
		$('.tablelink.publish').live('click',function(){
			var tableid = $(this).attr('id');
			tableid = tableid.substring(8);
			changepublish(1,tableid);
		});
		$('.tablelink.canclepublish').live('click',function(){
			var tableid = $(this).attr('id');
			tableid = tableid.substring(8);
			$('#untableid').val(tableid);
			$('#ifDelete').show();
		});	
		$('#cancelDelete').click(function(){
			$('#ifDelete').hide();
		})
		//导入
		$('.tablelink.import').live('click',function(){
			var tableid = $(this).attr('id');
			tableid = tableid.substring(7);
			$("#searchDialog").show();
			$('#ssearchDialog').show();
			$("#ssearchDialog").attr("action","<%=basePath%>changeImportServlet?tableid="+tableid);
			$("#ttableid").val(tableid);
		});	
		
		$('#nextpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
	 		if((pagenow+1)<=totalcount){
	 		pagenow +=1;
	 		gettablelist(pagenow,rownumber);
	 		}
	 	});
	 	$('#prepage').click(function(){
	 		if((pagenow-1) != 0){
	 			pagenow -=1;
	 			gettablelist(pagenow,rownumber);
	 		}
	 	});
	 	$('#lastpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
			pagenow = totalcount;
			gettablelist(pagenow,rownumber);
	 	});
	 	$('#firstpage').click(function(){
	 		pagenow = 1;
	 		gettablelist(pagenow,rownumber);
	 	});
		$('.tablelink.edit').live('click',function(){
			var fillid = $(this).attr('id');
			var tableid = fillid.substring(5,fillid.length);
			//location.href="addSuperMajor.jsp";
			location.href = "../gotofilltable?tableid="+tableid;
		});
		
	
		
	});
</script>
<title>Insert title here</title>
</head>
<body>

	<input type='hidden' id='topuserid' values='<%=userid%>'>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>admin/myInfo.jsp">首页></a></li>
			<li><a href="#">用户管理></a></li>
			<li><a href="#">表格填报></a></li>
			<li><a href="#" id='tablewhere'>填报表格</a></li>
		</ul>
		
		<input type="button" value="全部发布" id="publishall">
		<input type="button" value="发布当前页" id="publishpage">
		<input type="button" value="全部关闭" id="closeall">
		
	</div>



	</div>

	<div id='tiptwo' class='tip'>

		<div id='tipone' class='tip'>
			<div class="rightinfo">

				<table class="tablelist">
					<thead>
						<tr>
							<th><input name="" type="checkbox" value=""
								checked="checked" /></th>
							<th>报表id<i class="sort"><img
									src="<%=basePath%>images/px.gif" /></i></th>
							<th>表名</th>
							<th>截止时间</th>
							<th>发布状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="usertable">


						<!-- <tr id="">
							<td><input name="" type="checkbox" value="" id="  " /></td>
							<td><input class="newsuneditable" id="userid" value="12"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="username" value="4-2-3优势专业情况"
								readonly="readonly"></input></td>
							<td><input class="newsuneditable" id="islive" value="未提交"
								readonly="readonly"></input></td>
							<td class="newseditable"><a href="#" class="tablelink editt"
								id="edit">填报</a></td>
						</tr> -->

					</tbody>
				</table>


				<div class="pagin">
					<div class="message">
						共<i class="blue" id='blue'> </i>个纪录
					</div>
					<ul class="paginList">
						<li class="paginItem"><a id='firstpage' href="#">首页</a></li>
						<li class="paginItem"><a id='prepage' href="#">上页</a></li>
						<li class="paginItem"><a id='nextpage' href="#">下页</a></li>
						<li class="paginItem"><a id='lastpage' href="#">末页</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 全部关闭 -->
	<form id="closepublish" action="<%=basePath %>changepublish"
		method="post">
		<input type="hidden" id="flag" name="flag" value="2"> <input
			type="hidden" id="ttableid" name="tableid"></br>
		</br>
		</br>
		</br> <input class="easyui-datebox" name="datetime"></input></br>
		</br> <input type="submit" value="确定"> <input type="button"
			id="canpublish" value="取消">
	</form>
	
	<!-- 全部发布 -->
	<form id="topublishall" action="<%=basePath %>changepublish"
		method="post">
		<input type="hidden" id="flag" name="flag" value="3"> <input
			type="hidden" id="ttableid" name="tableid"></br>
		</br>
		</br>
		</br>
		</br> <input type="submit" value="确定"> <input type="button"
			id="canpublishall" value="取消">
	</form>
	
	<!-- 发布当前页 -->
	<form id="topublishpage" action="<%=basePath %>changepublish"
		method="post">
		<input type="hidden" id="flag" name="flag" value="4"> <input
			type="hidden" id="pagetableid" name="tableid"></br>
		</br>
		</br>
		</br>
		</br> <input type="submit" value="确定"> <input type="button"
			id="canpublishpage" value="取消">
	</form>

	<div class="tip" style="display: none;" id="ifDelete">
		<div class="tiptop">
			<span>提示信息</span><a></a>
		</div>
		<div class="tipinfo">
			<span><img src="<%=basePath %>images/ticon.png" /></span>
			<div class="tipright">
				<p>是否确认取消发布 ？</p>
				<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
			</div>
		</div>
		<div class="tipbtn">
			<form action="<%=basePath %>changepublish" method="post">
				<input type="hidden" id="flag" name="flag" value="0"> <input
					name="tableid" id="untableid" type="hidden" /> 
					<input class="easyui-datebox" name="datetime"></input>
					</br>
					</br>
					</br>
					<input name=""
					type="submit" class="sure" value="确定" />&nbsp; <input name=""
					type="button" class="cancel" id="cancelDelete" value="取消" />
			</form>
		</div>
	</div>





	<div id="searchDialog" icon="icon-save"
		style="padding: 50px; width: 300px; height: 150px;">
		<%-- ?tableid=<%=tableid%> --%>
		<form id="ssearchDialog" name="formName" action="" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="ttableid" name="tableid"> <input
				type="file" name="myfile1"><br> <input type="submit"
				value="导入">
		</form>
	</div>
</body>
</html>