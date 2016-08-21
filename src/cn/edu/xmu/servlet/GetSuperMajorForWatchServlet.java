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

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.util.FastJsonTool;

@WebServlet("/GetSuperMajorForWatchServlet")
public class GetSuperMajorForWatchServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//String studyId = (String) request.getAttribute("id");
		//String school = (String) request.getAttribute("school");
		//String major = (String) request.getAttribute("major");
		
		String college = request.getParameter("college");
	       
	    Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put("college", college);
	    }
		
		List<SuperMajor> superMajors = null;
		/*if (school != null) {
			studentlList = studentService.getStudentBySchool(school);
		}else if (major != null) {
			studentlList = studentService.getStudentByMajor(major);
		}else {
			studentlList = studentService.getallStudents();
		}*/
		
        SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
		superMajors = superMajorDao.getAllSuperMajor(queryParams);
		System.out.println(FastJsonTool.createJsonString(superMajors));
		out.print(FastJsonTool.createJsonString(superMajors));
	}
}
