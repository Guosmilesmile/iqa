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

import cn.edu.xmu.entity.ProPracticeTeach;
import cn.edu.xmu.service.ProPracticeTeachService;
import cn.edu.xmu.serviceimpl.ProPracticeTeachServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 
 * @author xiaoping 附表10 各专业实践教学情况 date 2015-8-3
 *
 */
@WebServlet("/GetProPracticeTeachServlet")
public class GetProPracticeTeachServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private ProPracticeTeachService pptService = new ProPracticeTeachServiceImpl();
	
	public GetProPracticeTeachServlet()
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter writer = response.getWriter();
		GridDataModel<ProPracticeTeach> model = new GridDataModel<ProPracticeTeach>();

		String college = request.getParameter("college");
		String deadline = request.getParameter("deadline");
		String tempSize = request.getParameter("size");
		String tempIsGetByPracticeRate = request.getParameter("isGetByPracticeRate");
		String roleid = request.getParameter("roleid");
		int size = -1;
		boolean isGetByPracticeRate = false;
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
//				/*
//				 * college = null; queryParams.put("college", college);
//				 */
//			}
		} else
		{
			queryParams = null;
		}
		if(tempSize!=null && !"".equals(tempSize))
			size = Integer.parseInt(tempSize);
		if(tempIsGetByPracticeRate != null && !"".equals(tempIsGetByPracticeRate))
			isGetByPracticeRate = Boolean.parseBoolean(tempIsGetByPracticeRate);
		List<ProPracticeTeach> undergraduateAdmissions = pptService.getProPracticeTeach(isGetByPracticeRate, queryParams);

		int total = undergraduateAdmissions.size();
		model.setTotal(total);
		model.setRows(undergraduateAdmissions);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}
