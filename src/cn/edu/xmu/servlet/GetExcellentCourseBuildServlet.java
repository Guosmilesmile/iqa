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

import cn.edu.xmu.entity.ExcellentCourseBuild;
import cn.edu.xmu.service.ExcellentCourseBuildService;
import cn.edu.xmu.serviceimpl.ExcellentCourseBuildServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.9 精品（优秀）课程（群）建设
 * @author yue
 *
 */
@WebServlet("/GetExcellentCourseBuildServlet")
public class GetExcellentCourseBuildServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ExcellentCourseBuildService ecbService = new ExcellentCourseBuildServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetExcellentCourseBuildServlet(){
    	super();
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		this.doPost(request, response);		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<ExcellentCourseBuild> model = new GridDataModel<ExcellentCourseBuild>();
		
	    String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
	    
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    if (deadline != null) {
	    	queryParams.put("deadline", deadline);
		}
	    
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put("college", college);
			}
	    	else{
	    		/*college = null;
	    		queryParams.put("college", college);*/
	    	}
	    }
	    else {
			queryParams = null;
		}
	    ExcellentCourseBuild ecb = ecbService.getExcellentCourseBuild(queryParams);
	    List<ExcellentCourseBuild>  ecbs = new ArrayList<ExcellentCourseBuild>();
	    ecbs .add(ecb);
	    		
	    
	    int total = ecbs.size();
	    model.setTotal(total);
	    model.setRows(ecbs);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
