/*List为数据源，pagecountid为总记录对应的id，pagenow为当前页数，rownumber为每页显示记录的条数*/
function pagebean(List,pagecountid,pagenow,rownumber){//2 1 
	pagecountid = '#'+pagecountid;
	var countbody = $(pagecountid);
	countbody.html(List.length);
	var totalpage = Math.ceil(List.length/rownumber);//2
	if(pagenow>totalpage)//2>2?
		pagenow = totalpage;
	if(pagenow==0)
		pagenow =1;
	var list = new Array();
	var lastpage = (pagenow)*rownumber;//2*1=2
	if(lastpage>List.length)//2 > 2
		lastpage = List.length-1;
	else
		lastpage -=1; //1
	for(var i=(pagenow-1)*rownumber;i<=lastpage;i++){//1*1=1,1<=1
		list.push(List[i]);
	}
	return list;
}