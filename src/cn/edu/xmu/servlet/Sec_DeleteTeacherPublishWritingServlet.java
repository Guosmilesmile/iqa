
package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.xmu.dao.TeacherPublishWritingDao;
import cn.edu.xmu.daoimpl.TeacherPublishWritingDaoImpl;

/**
 * Servlet implementation class DeleteForeignExchangeServlet
 */
@WebServlet("/Sec_DeleteTeacherPublishWritingServlet")
public class Sec_DeleteTeacherPublishWritingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteTeacherPublishWritingServlet() {
        super();
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
		String tpw_id = request.getParameter("tpwids");
		
		if(tpw_id == null ) return;
		String tpwid[] = tpw_id.split(",");
		TeacherPublishWritingDao teacherPublishWritingDao = new TeacherPublishWritingDaoImpl();
		boolean result = false;
		try {
			result = teacherPublishWritingDao.batchDelete(tpwid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
	}

}
