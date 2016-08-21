package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.daoimpl.StudentAssociationDaoImpl;
import cn.edu.xmu.table.StudentAssociationTable;

/**
 * Servlet implementation class DeleteStudentAssociation
 */
@WebServlet("/DeleteStudentAssociation")
public class Sec_DeleteStudentAssociation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteStudentAssociation() {
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
		String sa_id = request.getParameter("saids");
		
		String said[] = sa_id.split(",");
		//解码
		String college = request.getParameter(StudentAssociationTable.SA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		StudentAssociationDao saDao = new StudentAssociationDaoImpl();
		boolean result = false;
		try {
			result = saDao.batchDelete(said);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
