package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.HighLevelResearchTeamDao;
import cn.edu.xmu.daoimpl.HighLevelResearchTeamDaoImpl;
/**
 * 
 * @author xiaoping 表3-4-2 高层次研究团队 (时点) date 2015-7-9
 *
 */
@WebServlet("/Sec_DeleteHighLevelResearchTeam")
public class Sec_DeleteHighLevelResearchTeam extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteHighLevelResearchTeam()
	{
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String hlrt_id = request.getParameter("hlrtids");
		
		String hlrtid[] = hlrt_id.split(",");
		
		HighLevelResearchTeamDao highLevelResearchTeamDao = new HighLevelResearchTeamDaoImpl();
		boolean result = false;
		try {
			result = highLevelResearchTeamDao.batchDelete(hlrtid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
