package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.AdmissionCriteriaAndNumberDao;
import cn.edu.xmu.daoimpl.AdmissionCriteriaAndNumberDaoImpl;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteAdmissionCriteriaAndNumberServlet")
public class Sec_DeleteAdmissionCriteriaAndNumberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteAdmissionCriteriaAndNumberServlet() {
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
		String acn_id = request.getParameter("acnids");
		
		String acnid[] = acn_id.split(",");
		
		AdmissionCriteriaAndNumberDao acnDao = new AdmissionCriteriaAndNumberDaoImpl();
		boolean result = false;
		try {
			result = acnDao.batchDelete(acnid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
