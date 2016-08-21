package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.HighLevelTalentDao;
import cn.edu.xmu.daoimpl.HighLevelTalentDaoImpl;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_DeleteHighLevelTalent")
public class Sec_DeleteHighLevelTalent extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteHighLevelTalent()
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
		String hlt_id = request.getParameter("hltids");
		
		String hltid[] = hlt_id.split(",");
		
		HighLevelTalentDao highLevelTalentDao = new HighLevelTalentDaoImpl();
		boolean result = false;
		try {
			result = highLevelTalentDao.batchDelete(hltid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
