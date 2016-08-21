package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.NetworkBooks;
import cn.edu.xmu.entity.TeachingHouse;
import cn.edu.xmu.service.NetworkBooksService;
import cn.edu.xmu.serviceimpl.NetworkBooksServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;
/**
 * 
 * @author xiaoping 3.2.4校园网、图书情况 date 2015-8-5
 */
@WebServlet("/GetNetworkBooksServlet")
public class GetNetworkBooksServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private NetworkBooksService nbs = new NetworkBooksServiceImpl();

	public GetNetworkBooksServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter writer = response.getWriter();
		GridDataModel<NetworkBooks> model = new GridDataModel<>();

		String college = request.getParameter("college");
		String deadline = request.getParameter("deadline");

		String roleid = request.getParameter("roleid");

		Map queryParams = new HashMap();
		if (deadline != null)
		{
			queryParams.put("deadline", deadline);
		}

		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put("college", college);
			} 
//			else
//			{
//				  college = null; queryParams.put("college", college);
//			}
		} else
		{
			queryParams = null;
		}

		List<NetworkBooks> content = nbs.get(queryParams);

		int total = content.size();
		model.setTotal(total);
		model.setRows(content);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));
	}
}
