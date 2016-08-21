package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
@WebServlet("/Sec_DeleteRegUndergraProfesStuNum")
public class Sec_DeleteRegUndergraProfesStuNum extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sec_DeleteRegUndergraProfesStuNum()
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
		String rupsn_id = request.getParameter("rupsnids");

		String rupsnid[] = rupsn_id.split(",");

		RegUndergraProfesStuNumDao regUndergraProfesStuNumDao = new RegUndergraProfesStuNumDaoImpl();
		boolean result = false;
		try
		{
			result = regUndergraProfesStuNumDao.batchDelete(rupsnid);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		System.out.println("删除纪录的结果" + result);

		out.print(result);
	}
}
