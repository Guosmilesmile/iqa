package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TrainingInstitutionsDao;
import cn.edu.xmu.daoimpl.TrainingInstitutionsDaoImpl;


/**
 * Servlet implementation class DeleteSchoolExecutiveUnit
 */
@WebServlet("/Sec_DeleteTrainingInstitutions")
public class Sec_DeleteTrainingInstitutions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteTrainingInstitutions() {
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
		String ti_id = request.getParameter("tiids");
		
		String tiid[] = ti_id.split(",");
		
		TrainingInstitutionsDao tiDao = new TrainingInstitutionsDaoImpl();
		boolean result = false;
		try {
			result = tiDao.batchDelete(tiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
