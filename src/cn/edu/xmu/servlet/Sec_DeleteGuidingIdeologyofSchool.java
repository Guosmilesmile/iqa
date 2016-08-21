package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.daoimpl.GuidingIdeologyofSchoolDaoImpl;

/**
 * Servlet implementation class DeleteGuidingIdeologyofSchool
 */
@WebServlet("/DeleteGuidingIdeologyofSchool")
public class Sec_DeleteGuidingIdeologyofSchool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteGuidingIdeologyofSchool() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String gis_id = request.getParameter("gisids");
		
		String gisid[] = gis_id.split(",");
		
		GuidingIdeologyofSchoolDao gisDao = new GuidingIdeologyofSchoolDaoImpl();
		boolean result = false;
		try {
			result = gisDao.batchDelete(gisid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
