package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.MajorOverview;
import cn.edu.xmu.service.MajorOverviewService;
import cn.edu.xmu.serviceimpl.MajorOverviewServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 附表8各专业教师、学生情况概览
 * @author chunfeng
 *
 */
@WebServlet("/GetMajorStuAndTeacher")
public class GetMajorStuAndTeacher extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private MajorOverviewService moService = new MajorOverviewServiceImpl();

	public GetMajorStuAndTeacher()
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
		GridDataModel<MajorOverview> model = new GridDataModel<MajorOverview>();

		String college = request.getParameter("college");
		
		List<MajorOverview> moList = moService.getMajorOverview();

		int total = moList.size();
		model.setTotal(total);
		model.setRows(moList);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}

