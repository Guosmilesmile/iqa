package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.StartClassOverview;
import cn.edu.xmu.service.StartClassOverviewService;
import cn.edu.xmu.serviceimpl.StartClassOverviewServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.6 各教学单位课程开设情况
 * @author chunfeng
 *
 */
@WebServlet("/GetStartClassOverviewServlet")
public class GetStartClassOverviewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private StartClassOverviewService scoService = new StartClassOverviewServiceImpl();

	public GetStartClassOverviewServlet()
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
		GridDataModel<StartClassOverview> model = new GridDataModel<StartClassOverview>();

		String college = request.getParameter("college");
		
		List<StartClassOverview> scList = scoService.getStartClassOverview();
		
		int total = scList.size();
		model.setTotal(total);
		model.setRows(scList);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}

