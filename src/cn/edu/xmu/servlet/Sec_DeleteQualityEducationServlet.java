package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.QualityEducationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.QualityEducationDaoimpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.QualityEducation;

/**
 * Servlet implementation class Sec_DeleteQualityEducationServlet
 */
@WebServlet("/Sec_DeleteQualityEducationServlet")
public class Sec_DeleteQualityEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteQualityEducationServlet() {
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
		String qe_id = request.getParameter("qeids");
		
		String qeid[] = qe_id.split(",");
		
		QualityEducationDao qeDao = new QualityEducationDaoimpl();
		boolean result = false;
		try {
			result = qeDao.batchDelete(qeid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
