package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StudentHomeDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StudentHomeDaoImpl;
import cn.edu.xmu.entity.StudentHome;

/**
 * Servlet implementation class Sec_DeleteStudentHomeServlet
 */
@WebServlet("/Sec_DeleteStudentHomeServlet")
public class Sec_DeleteStudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteStudentHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String sh_id = request.getParameter("shids");
		
		String shid[] = sh_id.split(",");
		
		StudentHomeDao shDao = new StudentHomeDaoImpl();
		boolean result = false;
		try {
			result = shDao.batchDelete(shid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
