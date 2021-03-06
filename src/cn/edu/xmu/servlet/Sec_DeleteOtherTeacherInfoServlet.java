package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;

/*
 * 3-1-3 其他师资情况
 * @author sihan
 */
@WebServlet("/Sec_DeleteOtherTeacherInfoServlet")
public class Sec_DeleteOtherTeacherInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteOtherTeacherInfoServlet() {
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
		String oti_id = request.getParameter("otiids");
		
		String otiid[] = oti_id.split(",");
		
		OtherTeacherInfoDao mid = new OtherTeacherInfoDaoImpl();
		boolean result = false;
		try {
			result = mid.batchDelete(otiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		//System.out.println(otiid);
		
		out.print(result);

	}	

}
