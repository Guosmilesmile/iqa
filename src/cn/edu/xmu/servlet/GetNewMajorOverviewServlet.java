package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.NewMajorOverview;
import cn.edu.xmu.service.NewMajorOverviewService;
import cn.edu.xmu.serviceimpl.NewMajorOverviewServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.5 新设专业概览
 * @author chunfeng
 *
 */
@WebServlet("/GetNewMajorOverviewServlet")
public class GetNewMajorOverviewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private NewMajorOverviewService nmoService = new NewMajorOverviewServiceImpl();

	public GetNewMajorOverviewServlet()
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
		GridDataModel<NewMajorOverview> model = new GridDataModel<NewMajorOverview>();

		String college = request.getParameter("college");
		
		List<NewMajorOverview> nmoList = nmoService.getNewMajorOverview();

		int total = nmoList.size();
		model.setTotal(total);
		model.setRows(nmoList);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}

