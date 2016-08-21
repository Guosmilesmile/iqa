package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TeachingPlanImplDao;
import cn.edu.xmu.daoimpl.TeachingPlanImplDaoImpl;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况 date 2015-7-8
 *
 */
@WebServlet("/Sec_DeleteTeachingPlanImpl")
public class Sec_DeleteTeachingPlanImpl extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteTeachingPlanImpl()
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
		String tpi_id = request.getParameter("tpiids");
		
		String tpiid[] = tpi_id.split(",");
		
		TeachingPlanImplDao teachingPlanImplDao = new TeachingPlanImplDaoImpl();
		boolean result = false;
		try {
			result = teachingPlanImplDao.batchDelete(tpiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
