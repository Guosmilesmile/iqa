package cn.edu.xmu.servlet;
/**
 * @author zshbleaker
 */

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

import cn.edu.xmu.entity.BachelorExpPlace;
import cn.edu.xmu.entity.GoodCourseBuild;
import cn.edu.xmu.service.GoodCourseBuildService;
import cn.edu.xmu.serviceimpl.GoodCourseBuildServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

@WebServlet("/GetGoodCourseBuildServlet")
public class GetGoodCourseBuildServlet {
	private static final long serialVersionUID = 1L;
	private GoodCourseBuildService gcbs = new GoodCourseBuildServiceImpl();
	
	public GetGoodCourseBuildServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<GoodCourseBuild> model = new GridDataModel<>();
		
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
		    
		    List<GoodCourseBuild> content = gcbs.get(queryParams);
		    
			 int total = 1;
			 model.setTotal(total);
			 model.setRows(content);
			 System.out.println(FastJsonTool.createJsonString(model));
			 writer.write(FastJsonTool.createJsonString(model));
	}

}
