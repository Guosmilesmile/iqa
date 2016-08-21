package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;

/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteGraduateAndDoctoralServlet")
public class Sec_DeleteGraduateAndDoctoralServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteGraduateAndDoctoralServlet() {
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
		String gd_id = request.getParameter("gdids");
		
		String gdid[] = gd_id.split(",");
		
		GraduateAndDoctoralDao gdDao = new GraduateAndDoctoralDaoImpl();
		boolean result = false;
		try{
			result = gdDao.batchDelete(gdid);
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("删除记录的结果"+result);
		out.print(result);
	}
}
