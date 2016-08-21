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

import cn.edu.xmu.entity.AdvantageMajorOverview;
import cn.edu.xmu.service.AdvantageMajorOverviewService;
import cn.edu.xmu.serviceimpl.AdvantageMajorOverviewServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.4 优势专业概览
 * @author chunfeng
 *
 */
@WebServlet("/GetAdvantageMajorOverviewServlet")
public class GetAdvantageMajorOverviewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private AdvantageMajorOverviewService amoService = new AdvantageMajorOverviewServiceImpl();

	public GetAdvantageMajorOverviewServlet()
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
		GridDataModel<AdvantageMajorOverview> model = new GridDataModel<AdvantageMajorOverview>();

		String college = request.getParameter("college");
		
		List<AdvantageMajorOverview> amoList = amoService.getAdvantageMajorOverview();

		int total = amoList.size();
		model.setTotal(total);
		model.setRows(amoList);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}

