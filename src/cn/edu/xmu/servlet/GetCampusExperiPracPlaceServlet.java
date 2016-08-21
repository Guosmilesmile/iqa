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

import cn.edu.xmu.entity.CampusExperiPracPlace;
import cn.edu.xmu.service.CampusExperiPracPlaceService;
import cn.edu.xmu.serviceimpl.CampusExperiPracPlaceServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 
 * @author xiaoping 附表9 校内实验实习实训场所情况 date 2015-7-20
 *
 */
@WebServlet("/GetCampusExperiPracPlaceServlet")
public class GetCampusExperiPracPlaceServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private CampusExperiPracPlaceService campusExperiPracPlaceService = new CampusExperiPracPlaceServiceImpl();

	public GetCampusExperiPracPlaceServlet()
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
		GridDataModel<CampusExperiPracPlace> model = new GridDataModel<CampusExperiPracPlace>();

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

		List<CampusExperiPracPlace> campusExperiPracPlaces = campusExperiPracPlaceService
				.getCampusExperiPracPlace(queryParams);

		int total = campusExperiPracPlaces.size();
		model.setTotal(total);
		model.setRows(campusExperiPracPlaces);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}
