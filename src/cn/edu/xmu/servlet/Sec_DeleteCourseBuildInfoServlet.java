package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;

/**
 * 附表5-2-3课程建设情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteCourseBuildInfoServlet")
public class Sec_DeleteCourseBuildInfoServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteCourseBuildInfoServlet() {
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
		String cbi_id = request.getParameter("cbiids");
		
		String cbiid[] = cbi_id.split(",");
		
		CourseBuildInfoDao cbiDao = new CourseBuildInfoDaoImpl();
		boolean result = false;
		try {
			result = cbiDao.batchDelete(cbiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}	
}
