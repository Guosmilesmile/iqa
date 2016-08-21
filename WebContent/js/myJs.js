// JavaScript Document
function displaySubMenu(li) { 
var subMenu = li.getElementsByTagName("ul")[0]; 
subMenu.style.display = "block"; 
} 
function hideSubMenu(li) { 
var subMenu = li.getElementsByTagName("ul")[0]; 
subMenu.style.display = "none"; 
} 
function change_bg(obj){
var a=document.getElementsByClassName("header")[0].getElementsByTagName("a");
for(var i=0;i<a.length;i++){a[i].className="";}
obj.className="current";
}

//play
function play(){
	var oDiv = $("#play");  //外部盒子
	var count = $("#play ul li").length;  //内部图片数量
	var countwidth = $("#play ul li").width();  //图片边框宽度
	var oUl = $("#play ul").css("width",count*countwidth);  //ul li总宽度
	var now = 0;
	var next = $("#next");
	var prev = $("#prev");
	//点击按钮数量
	for(var i = 0; i < count; i++){
		$("#play ol").append("<li>" + Number(i+1) + "</li>");
		$("#play ol li:first").addClass("active");
	}
	//左右点击图片获取
	var nI = $("#play ul li:nth-child(2)").find("img").attr("src");
	$(".nextImg img").attr("src",nI);
	var pI = $("#play ul li:last-child").find("img").attr("src");
	$(".prevImg img").attr("src",pI);
	//按钮点击事件
	var aBtn = $("#play ol li");
	aBtn.each(function(index){
		$(this).click(function(){
			clearInterval(timer);
			tab(index);
			nextImg();
			prevImg();
			timer=setInterval(autoRun,2000);
		});
	});
	//图片循环事件
	function tab(index){
		now = index;
		aBtn.removeClass("active");
		aBtn.eq(index).addClass("active");
		oUl.stop(true,false).animate({"left":-countwidth * now},400);
	}
	//下一张按钮图片切换
	function nextImg(){
		var d = $("#play ul li").find("img").eq(now+1).attr("src");
		var nI = $("#play ul li:nth-child(1)").find("img").attr("src");
		$(".nextImg").find("img").attr("src",d);
		if(now==count-1){
			$(".nextImg").find("img").attr("src",nI);
		}
	}
	//上一张图片按钮切换
	function prevImg(){
		var f = $("#play ul li").find("img").eq(now-1).attr("src");
		$(".prevImg").find("img").attr("src",f);
	}
	
	//下一张点击事件
	next.click(function(){
		clearInterval(timer);
		now++;
		if(now==count){
			now=0;
		}
		tab(now);
		nextImg();
		prevImg();
		timer=setInterval(autoRun, 2000);
	});
	//上一张点击事件
	prev.click(function(){
		clearInterval(timer);
		now--;
		if(now==-1){
			now=count-1;
		}
		tab(now);
		nextImg();
		prevImg();
		timer=setInterval(autoRun, 2000);
	});
	//自动轮播定义
	function autoRun(){
		now++;
		if(now==count){
			now=0;
		}
		tab(now);
		nextImg();
		prevImg();
	};
	var timer=setInterval(autoRun, 2000);
};

