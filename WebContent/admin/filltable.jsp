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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>css/filltable.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/page.js"></script>

<script>
function getCookie(objName){//获取指定名称的cookie的值 
	var arrStr = document.cookie.split("; "); 
	for(var i = 0;i < arrStr.length;i ++){ 
	var temp = arrStr[i].split("="); 
	if(temp[0] == objName) return unescape(temp[1]); 
	} 
};

	function getuserfill(userid,pagenow,rownumber){
		$.ajax({
			type:'post',
			url:'<%=basePath %>getuserfill',
			async: false,
			data:{userid:userid},
			success:function(ListJsonStr){
				var List = eval('(' + ListJsonStr + ')');
				var tbody = $('#usertable');
				var str ="";
				if(List.length > 0){
					List = pagebean(List,'blue',pagenow,rownumber);
					var sonflag= 0;
					var turns = 0;
					var fatherid = -1;
					for(var i=0;i<List.length;i++){
						var tableid = List[i].tableid;
						var tablename = List[i].tablename;
						var fillsituation = List[i].rf_fillsituation;
						var reviewsituation = List[i].rf_reviewsituation;
						var reviewcontent = List[i].rf_reviewcontent;
						var sonid = List[i].sonid;
						var nexttableid=-1;
						if(i!=List.length-1){
							nexttableid = List[i+1].tableid;
						}
						if(sonid==nexttableid){
							sonflag =1;
							if(turns==0){
								fatherid = tableid;
							}
							turns++;
						}else{
							if(turns!=0){
								sonflag=0;
							}else{
								sonflag=2;
							}
							turns = 0;
							
						}
						if(sonflag==1){
							str+="<tr id=''>"+
							"<td><input type='button' class='showson' id='btnshow_"+tableid+"' value='+'></td>"+
							"<td><input style='width:80px;' class='newsuneditable' id='tableid' value='"+tableid+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
							"readonly='readonly'></input></td>";
							if(fillsituation==0)
							str+="<td><input class='newsuneditable' id='tablename' value='未提交'"+
							"readonly='readonly'></input></td>";
							else if(fillsituation==1)
							str+="<td><input class='newsuneditable' id='tablename' value='已提交'"+
							"readonly='readonly'></input></td>";
							if(reviewsituation==0)
								str+="<td><input class='newsuneditable' id='tablename' value='未审核'"+
								"readonly='readonly'></input></td>";
							else if(reviewsituation==2)
								str+="<td><input class='newsuneditable' id='tablename' value='一级审核通过'"+
								"readonly='readonly'></input></td>";
							else if(reviewsituation==3)
								str+="<td><input class='newsuneditable' id='tablename' value='二级审核通过'"+
								"readonly='readonly'></input></td>";
							else if(reviewsituation==4)
									str+="<td><input class='newsuneditable' id='tablename' value='一级审核不通过'"+
									"readonly='readonly'></input></td>";
							else if(reviewsituation==5)
										str+="<td><input class='newsuneditable' id='tablename' value='二级审核不通过'"+
										"readonly='readonly'></input></td>";
							if(fillsituation!=1) 
							str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
							"id='fill_"+tableid+"'>填报</a></td></tr>";
							
							/* else if(fillsituation==0 && reviewsituation==4)
								str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
								"id='fill_"+tableid+"'>填报    </a><a href='#' class='tablelink content'"+
								"id='content_"+tableid+"'>审核意见</a><input type='hidden' id='reviewcontent_"+tableid+"' value="+reviewcontent+"></td>"; */
							else
							str+="<td class='newseditable'></a></td></tr>";
						}else if(sonflag==2){
							str+="<tr id=''>"+
							"<td></td>"+
							"<td><input style='width:80px;' class='newsuneditable' id='tableid' value='"+tableid+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
							"readonly='readonly'></input></td>";
							if(fillsituation==0)
							str+="<td><input class='newsuneditable' id='tablename' value='未提交'"+
							"readonly='readonly'></input></td>";
							else if(fillsituation==1)
							str+="<td><input class='newsuneditable' id='tablename' value='已提交'"+
							"readonly='readonly'></input></td>";
							if(reviewsituation==0)
								str+="<td><input class='newsuneditable' id='tablename' value='未审核'"+
								"readonly='readonly'></input></td>";
							/* else if(reviewsituation==1)
								str+="<td><input class='newsuneditable' id='tablename' value='不通过'"+
								"readonly='readonly'></input></td>"; */
							else if(reviewsituation==2)
								str+="<td><input class='newsuneditable' id='tablename' value='一级审核通过'"+
								"readonly='readonly'></input></td>";
							else if(reviewsituation==3)
								str+="<td><input class='newsuneditable' id='tablename' value='二级审核通过'"+
								"readonly='readonly'></input></td>";
								else if(reviewsituation==4)
									str+="<td><input class='newsuneditable' id='tablename' value='一级审核不通过'"+
									"readonly='readonly'></input></td>";
								else if(reviewsituation==5)
									str+="<td><input class='newsuneditable' id='tablename' value='二级审核不通过'"+
									"readonly='readonly'></input></td>";
							if(fillsituation!=1) 
							str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
							"id='fill_"+tableid+"'>填报</a></td></tr>";
							/* else if(fillsituation==0 && reviewsituation==1)
								str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
								"id='fill_"+tableid+"'>填报    </a><a href='#' class='tablelink content'"+
								"id='content_"+tableid+"'>审核意见</a><input type='hidden' id='reviewcontent_"+tableid+"' value="+reviewcontent+"></td>";
							 */else
							str+="<td class='newseditable'></a></td></tr>";
						}else{
							str+="<tr class='sontr_"+fatherid+"' style='display:none;'>"+
							"<td></td>"+
							"<td><input style='width:80px;' class='newsuneditable' id='tableid' value='"+tableid+"'"+
							"readonly='readonly'></input></td>"+
							"<td><input class='newsuneditable' id='tablename' value='"+tablename+"'"+
							"readonly='readonly'></input></td>";
							if(fillsituation==0)
							str+="<td><input class='newsuneditable' id='tablename' value='未提交'"+
							"readonly='readonly'></input></td>";
							else if(fillsituation==1)
							str+="<td><input class='newsuneditable' id='tablename' value='已提交'"+
							"readonly='readonly'></input></td>";
							if(reviewsituation==0)
								str+="<td><input class='newsuneditable' id='tablename' value='未审核'"+
								"readonly='readonly'></input></td>";
							/* else if(reviewsituation==1)
								str+="<td><input class='newsuneditable' id='tablename' value='不通过'"+
								"readonly='readonly'></input></td>"; */
							else if(reviewsituation==2)
								str+="<td><input class='newsuneditable' id='tablename' value='一级审核通过'"+
								"readonly='readonly'></input></td>";
							else if(reviewsituation==3)
								str+="<td><input class='newsuneditable' id='tablename' value='二级审核通过'"+
								"readonly='readonly'></input></td>";
								else if(reviewsituation==4)
									str+="<td><input class='newsuneditable' id='tablename' value='一级审核不通过'"+
									"readonly='readonly'></input></td>";
								else if(reviewsituation==5)
									str+="<td><input class='newsuneditable' id='tablename' value='二级审核不通过'"+
									"readonly='readonly'></input></td>";
							if(fillsituation!=1 ){
								str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
								"id='fill_"+tableid+"'>填报</a></td></tr>";
							} /* || reviewsituation==0 || reviewsituation==4 || reviewsituation==5 */
							/* else if(fillsituation==0 &&reviewsituation==1)
								str+="<td class='newseditable'><a href='#' class='tablelink edit'"+
								"id='fill_"+tableid+"'>填报    </a><a href='#' class='tablelink content'"+
								"id='content_"+tableid+"'>审核意见</a><input type='hidden' id='reviewcontent_"+tableid+"' value="+reviewcontent+"></td>"; */
							else
							str+="<td class='newseditable'></a></td></tr>";
						}
						
						
					}
				}
				tbody.html(str); 
			},error:function(){
				alert("fail");
			}
		});
	};
	$(document).ready(function() {
		$('#chooseul li:eq(0)').css('border', '1px solid black');
		var userid  = getCookie('userid');
		var username = getCookie('username');
		var pagenow = 1;
		var rownumber = 10;
		username = decodeURI(username);
		getuserfill(userid,pagenow,rownumber);
		
		$('.tablelink.edit').live('click',function(){
			var fillid = $(this).attr('id');
			var tableid = fillid.substring(5,fillid.length);
			//location.href="addSuperMajor.jsp";
			location.href = "../gotofilltable?tableid="+tableid;
		});
		$('#nextpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
	 		if((pagenow+1)<=totalcount){
	 		pagenow +=1;
	 		getuserfill(userid,pagenow,rownumber);
	 		}
	 	});
	 	$('#prepage').click(function(){
	 		if((pagenow-1) != 0){
	 			pagenow -=1;
	 			getuserfill(userid,pagenow,rownumber);
	 		}
	 	});
	 	$('#lastpage').click(function(){
	 		var total = $('#blue').html();
			var totalcount = Math.ceil(total/rownumber);
			pagenow = totalcount;
			getuserfill(userid,pagenow,rownumber);
	 	});
	 	$('#firstpage').click(function(){
	 		pagenow = 1;
	 		getuserfill(userid,pagenow,rownumber);
	 	});
		$('.tablelink.content').live('click',function(){
			 var id = $(this).attr("id").substring(8);
			 id="#reviewcontent_"+id;
			 alert($(id).val());
		});
		$('.showson').live("click",function(){
			if($(this).val()=="+"){
				$(this).val("-");
				var id = $(this).attr("id").substring(8);
				var showbody = ".sontr_"+id;
				$(showbody).fadeIn();
			}else if($(this).val()=="-"){
				$(this).val("+");
				var id = $(this).attr("id").substring(8);
				var showbody = ".sontr_"+id;
				$(showbody).fadeOut();
			}
		});
		
	});
</script>
<style type="text/css">
.showson{
	height:16px;
	padding:0px;
	width: 10px;
}

</style>
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
	</div>



	</div>
	<div id='tiptwo' class='tip'>
		<div id='tipone' class='tip'>
			<div class="rightinfo">

				<table class="tablelist">
					<thead>
						<tr>
							<th><input name="" type="checkbox" value=""
								/></th>
							<th>报表id<i class="sort"><img
									src="<%=basePath%>images/px.gif" /></i></th>
							<th>表名</th>
							<th>提交状态</th>
							<th>审核状态</th>
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
	
</body>
</html>