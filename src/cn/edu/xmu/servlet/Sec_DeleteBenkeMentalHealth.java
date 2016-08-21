package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.BenkeMentalHealthDao;
import cn.edu.xmu.daoimpl.BenkeMentalHealthDaoImpl;


/**
 * Servlet implementation class DeleteBenkeMentalHealth
 */
@WebServlet("/Sec_DeleteBenkeMentalHealth")
public class Sec_DeleteBenkeMentalHealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteBenkeMentalHealth() {
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
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String bmh_id = request.getParameter("bmhids");
		
		String bmhid[] = bmh_id.split(",");
		
		BenkeMentalHealthDao bmhDao = new BenkeMentalHealthDaoImpl();
		boolean result = false;
		try {
			result = bmhDao.batchDelete(bmhid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
