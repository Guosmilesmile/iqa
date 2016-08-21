package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.daoimpl.GeneralHighEnrollmentfileLineDaoImpl;

/**
 * Servlet implementation class DeleteGeneralHighEnrollmentfileLine
 */
@WebServlet("/DeleteGeneralHighEnrollmentfileLine")
public class Sec_DeleteGeneralHighEnrollmentfileLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteGeneralHighEnrollmentfileLine() {
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
		String ghel_id = request.getParameter("ghelids");
		
		String ghelid[] = ghel_id.split(",");
		
		GeneralHighEnrollmentfileLineDao ghelDao = new GeneralHighEnrollmentfileLineDaoImpl();
		boolean result = false;
		try {
			result = ghelDao.batchDelete(ghelid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
