package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.daoimpl.TeacherTrainingExchangeDaoImpl;

/**
 * Servlet implementation class DeleteTeacherTrainingExchange
 */
@WebServlet("/DeleteTeacherTrainingExchange")
public class Sec_DeleteTeacherTrainingExchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteTeacherTrainingExchange() {
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
		String tte_id = request.getParameter("tteids");
		
		String tteid[] = tte_id.split(",");
		
		TeacherTrainingExchangeDao tteDao = new TeacherTrainingExchangeDaoImpl();
		boolean result = false;
		try {
			result = tteDao.batchDelete(tteid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
