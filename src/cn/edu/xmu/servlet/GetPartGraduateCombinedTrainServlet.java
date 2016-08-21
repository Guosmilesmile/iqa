package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.GraduateCombinedTrain;
import cn.edu.xmu.service.GraduateCombinedTrainService;
import cn.edu.xmu.serviceimpl.GraduateCombinedTrainServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 4.4 毕业综合训练情况
 * @author chunfeng
 *
 */
@WebServlet("/GetPartGraduateCombinedTrainServlet")
public class GetPartGraduateCombinedTrainServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private GraduateCombinedTrainService gctService = new GraduateCombinedTrainServiceImpl();

	public GetPartGraduateCombinedTrainServlet()
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
		GridDataModel<GraduateCombinedTrain> model = new GridDataModel<GraduateCombinedTrain>();

		String college = request.getParameter("college");
		String deadline = request.getParameter("deadline");		
		String roleid = request.getParameter("roleid");
		int size = Integer.parseInt(request.getParameter("size"));
		boolean isSortByRate = Boolean.parseBoolean(request.getParameter("isSortByRate"));
					
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
		} else
		{
			queryParams = null;
		}
		
		List<GraduateCombinedTrain> gctList = gctService.getGraduateCombinedTrain(queryParams);
		//System.out.println("获取前五之前："+gctList);
		List<GraduateCombinedTrain> fList = gctService.getFrontGraduateCombinedTrain(size, isSortByRate, gctList);
		//System.out.println("前五："+fList);
		//System.out.println("获取后五之前："+gctList);
		List<GraduateCombinedTrain> bList = gctService.getBottomGraduateCombinedTrain(size, isSortByRate, gctList);
		//System.out.println("后五："+bList);
		//System.out.println("获取后五之后："+gctList);
		List<GraduateCombinedTrain> totalList = new ArrayList<GraduateCombinedTrain>();
		
		for(int i=0; i<fList.size(); i++)
		    totalList.add(fList.get(i));
		//System.out.println("添加前五之后："+totalList);
		for(int j=0; j<bList.size(); j++)
		    totalList.add(bList.get(j));
		
		int total = totalList.size();
		model.setTotal(total);
		model.setRows(totalList);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));

	}
}
