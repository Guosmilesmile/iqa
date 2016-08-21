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

import cn.edu.xmu.entity.UndergraduateAdmission;
import cn.edu.xmu.service.UndergraduateAdmissionService;
import cn.edu.xmu.serviceimpl.UndergraduateAdmissionServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 
 * @author xiaoping 附表13 各专业（大类）本科生招生情况 date 2015-7-17
 *
 */
@WebServlet("/GetUndergraduateAdmissionServlet")
public class GetUndergraduateAdmissionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UndergraduateAdmissionService uaService = new UndergraduateAdmissionServiceImpl();

	public GetUndergraduateAdmissionServlet()
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
		GridDataModel<UndergraduateAdmission> model = new GridDataModel<UndergraduateAdmission>();

		String college = request.getParameter("college");
		String deadline = request.getParameter("deadline");
		String tempSize = request.getParameter("size");
		String tempIsSortByRate = request.getParameter("isSortByRate");
		String roleid = request.getParameter("roleid");
		int size = -1;
		boolean isSortByRate = false;
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
			} else
			{
				/*
				 * college = null; queryParams.put("college", college);
				 */
			}
		} else
		{
			queryParams = null;
		}
		if(tempSize!=null && !"".equals(tempSize))
			size = Integer.parseInt(tempSize);
		if(tempIsSortByRate != null && !"".equals(tempIsSortByRate))
			isSortByRate = Boolean.parseBoolean(tempIsSortByRate);
		List<UndergraduateAdmission> undergraduateAdmissions = uaService.getUndergraduateAdmission(size, isSortByRate, queryParams);

		int total = undergraduateAdmissions.size();
		model.setTotal(total);
		model.setRows(undergraduateAdmissions);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}
