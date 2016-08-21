package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.daoimpl.GraduateEmployAsMajorDaoImpl;

/**
 * Servlet implementation class DeleteGraduateEmployAsMajorServlet
 */
@WebServlet("/Sec_DeleteGraduateEmployAsMajorServlet")
public class Sec_DeleteGraduateEmployAsMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteGraduateEmployAsMajorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String geam_id = request.getParameter("geamids");
		
		if(geam_id == null ) return;
		String geamid[] = geam_id.split(",");
		GraduateEmployAsMajorDao graduateEmployAsMajorDao = new GraduateEmployAsMajorDaoImpl();
		boolean result = false;
		try {
			result = graduateEmployAsMajorDao.batchDelete(geamid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
	}

}
