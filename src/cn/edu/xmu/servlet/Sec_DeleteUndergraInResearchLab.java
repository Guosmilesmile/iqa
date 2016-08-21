package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UndergraInResearchLabDao;
import cn.edu.xmu.daoimpl.UndergraInResearchLabDaoImpl;
/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
@WebServlet("/Sec_DeleteUndergraInResearchLab")
public class Sec_DeleteUndergraInResearchLab extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_DeleteUndergraInResearchLab()
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
		String uirl_id = request.getParameter("uirlids");
		
		String uirlid[] = uirl_id.split(",");
		
		UndergraInResearchLabDao uirlDao = new UndergraInResearchLabDaoImpl();
		boolean result = false;
		try {
			result = uirlDao.batchDelete(uirlid);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		System.out.println("删除纪录的结果"+result);
		out.print(result);
	}
}
