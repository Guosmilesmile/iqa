function search()
{
	var key = $("#key").val();
	if(key != null && key.length > 0)
	{
		if(key.indexOf("'") >= 0 || key.indexOf("--") >= 0)
		{
			alert("请勿输入无效字符，如‘、--等");
			return;
		}
		window.location.href="iqa_query.jsp?key="+key;
	}
}