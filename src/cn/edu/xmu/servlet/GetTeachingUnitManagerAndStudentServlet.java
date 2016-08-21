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

import cn.edu.xmu.entity.TeachingUnitManagerAndStudent;
import cn.edu.xmu.service.TeachingUnitManagerAndStudentService;
import cn.edu.xmu.serviceimpl.TeachingUnitManagerAndStudentServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;
/**
 * 
 * @author xiaoping 5.4 各教学单位学生管理人员与学生情况 date 2015-7-17
 *
 */
@WebServlet("/GetTeachingUnitManagerAndStudentServlet")
public class GetTeachingUnitManagerAndStudentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public GetTeachingUnitManagerAndStudentServlet()
	{
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
			TeachingUnitManagerAndStudentService tumasService = new TeachingUnitManagerAndStudentServiceImpl();
			PrintWriter writer = response.getWriter();
			GridDataModel<TeachingUnitManagerAndStudent> model = new GridDataModel<TeachingUnitManagerAndStudent>();
			
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
		    
		    List<TeachingUnitManagerAndStudent> tumasList = tumasService.getTeachingUnitManagerAndStudent(queryParams);
		    int total = tumasList.size();
		    model.setTotal(total);
		    model.setRows(tumasList);	    
		    System.out.println(FastJsonTool.createJsonString(model));
		    writer.write(FastJsonTool.createJsonString(model));
		}
}
