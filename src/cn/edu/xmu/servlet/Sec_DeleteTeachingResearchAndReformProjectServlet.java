package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;

/**
 * 
 * @author chunfeng
 *
 */

@WebServlet("/Sec_DeleteTeachingResearchAndReformProjectServlet")
public class Sec_DeleteTeachingResearchAndReformProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteTeachingResearchAndReformProjectServlet() {
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
		String trarp_id = request.getParameter("trarpids");
		
		if(trarp_id == null ) return;
		String trarpid[] = trarp_id.split(",");
		TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
		boolean result = false;
		try {
			result = teachingResearchAndReformProjectDao.batchDelete(trarpid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
	}

}

