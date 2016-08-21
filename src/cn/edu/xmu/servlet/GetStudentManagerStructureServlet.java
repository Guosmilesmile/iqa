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

import cn.edu.xmu.entity.StudentManagerStructure;
import cn.edu.xmu.service.StudentManagerStructureService;
import cn.edu.xmu.serviceimpl.StudentManagerStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;
/**
 * 
 * @author xiaoping 5.3 学生管理人员结构 date 2015-7-18
 *
 */
@WebServlet("/GetStudentManagerStructureServlet")
public class GetStudentManagerStructureServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private StudentManagerStructureService tqmsService = new StudentManagerStructureServiceImpl();
	public GetStudentManagerStructureServlet()
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
			
			PrintWriter writer = response.getWriter();
			GridDataModel<StudentManagerStructure> model = new GridDataModel<StudentManagerStructure>();
			
			
		    
		    String college = request.getParameter("college");
		    String deadline = request.getParameter("deadline");
		    
		    String roleid = request.getParameter("roleid");  
		    
		    Map queryParams = new HashMap();
		    if (deadline != null) {
		    	queryParams.put("deadline", deadline);
			}
		    
		    
		    if(college != null && !"".equals(college)){
		    	if (college.contains("院")) {//具体的学院才过滤
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
		    
		   
		    List<StudentManagerStructure> sms = tqmsService.getStuManagerStructure(queryParams);
		    
		    
		    int total = sms.size();
		    model.setTotal(total);
		    model.setRows(sms);	    
		    System.out.println(FastJsonTool.createJsonString(model));
		    writer.write(FastJsonTool.createJsonString(model));
			
		}
}
