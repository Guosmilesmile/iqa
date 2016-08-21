package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.EnrollCategoryInfoDao;
import cn.edu.xmu.daoimpl.EnrollCategoryInfoDaoImpl;

/**
 * 表6-1-3  近一届本科生招生类别情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteEnrollCategoryInfoservlet")
public class Sec_DeleteEnrollCategoryInfoservlet extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteEnrollCategoryInfoservlet() {
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
		String eci_id = request.getParameter("eciids");
		
		String eciid[] = eci_id.split(",");
		
		EnrollCategoryInfoDao eciDao = new EnrollCategoryInfoDaoImpl();
		boolean result = false;
		try {
			result = eciDao.batchDelete(eciid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}	
}
