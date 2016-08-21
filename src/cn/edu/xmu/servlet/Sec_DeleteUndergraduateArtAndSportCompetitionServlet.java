package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.xmu.dao.UndergraduateArtAndSportCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateArtAndSportCompetitionDaoImpl;

/**
 * Servlet implementation class DeleteUndergraduateArtAndSportCompetitionServlet
 */
@WebServlet("/Sec_DeleteUndergraduateArtAndSportCompetitionServlet")
public class Sec_DeleteUndergraduateArtAndSportCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteUndergraduateArtAndSportCompetitionServlet() {
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
		String uaasc_id = request.getParameter("uaascids");
		
		if(uaasc_id == null ) return;
		String uaascid[] = uaasc_id.split(",");
		UndergraduateArtAndSportCompetitionDao undergraduateArtAndSportCompetitionDao = new UndergraduateArtAndSportCompetitionDaoImpl();
		boolean result = false;
		try {
			result = undergraduateArtAndSportCompetitionDao.batchDelete(uaascid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
	}

}
