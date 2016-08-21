/*获取列表内容*/
function getLatestNews(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnewsgetallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"1"
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#newslist");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#newsModule").show();
        		for (var i = 0 ;i < newsList.length;i++){
        			
        			if(newsList[i].n_imgurl==""){
        				newsList[i].n_imgurl="<%=basePath%>images/newModel/display_pic.png";
        			}
        			newsList[i].n_imgurl="<%=basePath%>images/newModel/display_pic.png";
        			str +="<li> <img class=displayimg src=<%=basePath%>"+newsList[i].n_imgurl+"><h3>"
        				+newsList[i].n_titles+"</h3>"
            		    +"<p class='desc_p'>2014-11-11</p></li>";


        			if (i==1) {
					str += " <div class='clear'></div>";
					}
        			
          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>ch/document.jsp?contentId="+id;
   				});
        	}
        	else{//新闻内容为空
        		$("#newsModule").hide();
        	}
		},
        error: function(){alert("wrong");}
        	
	});
}

function getLatestFilesOrReports(ulName,n_subclass){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:n_subclass,//"2":文件，"3":报告
        },
        success: function(newsListJsonStr){
        	var ul_body = $(ulName);//"#fileList"
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#FileModule").show();
        		for (var i = 0 ;i < newsList.length;i++){

        		    str +="<li><div class='description'><h3>"+newsList[i].n_titles+
        		    "</h3>"
        		    +"</div><p class='desc_p'>"+newsList[i].n_publishtime+"</p></li>";

          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="document.jsp?contentId="+id;
   				})
        	}
        	else{
        		$("#FileModule").hide();
        	}
		},
        error: function(){alert("wrong");}
        	
	});
}

function getLatestReports(){
	var Request = new Object();
	Request = GetRequest();
	var menuId = Request['id'];
	$.ajax({
        type: 'post',
        url: "<%=basePath%>getallnews",
        data: {
        	pagination:"false",
        	rows:4,
        	menuId:menuId,
        	n_subclass:"3",
        },
        success: function(newsListJsonStr){
        	var ul_body = $("#reportsList");
        	var str ="";
        	var newsList = eval('(' + newsListJsonStr + ')');
        	if(newsList.length > 0){
        		$("#reportModule").show();
        		for (var i = 0 ;i < newsList.length;i++){

        		    str +="<li><div class='description'><h3>"+newsList[i].n_titles+
        		    "</h3>"
        		    +"</div><p class='desc_p'>2014-11-11</p></li>";

          	}
        		ul_body.html(str);
        		$(".readContent").click(function(){
   					var id = $(this).attr("id").toString().substring(11);
   					window.location.href="<%=basePath%>ch/document.jsp?contentId="+id;
   				})
        	}
        	else{
        		$("#reportModule").hide();
        	}
		},
        error: function(){alert("wrong");}
        	
	});
}