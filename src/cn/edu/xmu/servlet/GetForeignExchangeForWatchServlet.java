package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import cn.edu.xmu.util.FastJsonTool;

@WebServlet("/GetForeignExchangeForWatch")
public class GetForeignExchangeForWatchServlet extends HttpServlet{

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
		
		List<ForeignExchange> foreignExchanges = null;
		/*if (school != null) {
			studentlList = studentService.getStudentBySchool(school);
		}else if (major != null) {
			studentlList = studentService.getStudentByMajor(major);
		}else {
			studentlList = studentService.getallStudents();
		}*/
		ForeignExchangeDao foreignEchangeDao = new ForeignExchangeDaoImpl();
		foreignExchanges = foreignEchangeDao.getAllForeignExchanges();
		System.out.println(FastJsonTool.createJsonString(foreignExchanges));
		out.print(FastJsonTool.createJsonString(foreignExchanges));
	}
}
