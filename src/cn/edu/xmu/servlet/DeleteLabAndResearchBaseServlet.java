package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.LabAndResearchBaseDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.daoimpl.LabAndResearchBaseDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
/**
 * 
 * @author zsj
 * 1-5实验室和科研基地 
 */
@WebServlet("/DeleteLabAndResearchBaseServlet")
public class DeleteLabAndResearchBaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private LabAndResearchBaseDao labAndResearchBaseDao = new LabAndResearchBaseDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLabAndResearchBaseServlet() {
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
		String mi_id = request.getParameter("miids");
		
		String miid[] = mi_id.split(",");
		
		boolean result = false;
		try {
			result = labAndResearchBaseDao.batchDelete(miid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}	
}


