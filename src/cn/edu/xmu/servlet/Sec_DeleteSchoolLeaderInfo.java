package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_DeleteSchoolLeaderInfo")
public class Sec_DeleteSchoolLeaderInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteSchoolLeaderInfo()
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
		String sli_id = request.getParameter("sliids");
		
		String sliid[] = sli_id.split(",");
		
		SchoolLeaderInfoDao sliDao = new SchoolLeaderInfoDaoImpl();
		boolean result = false;
		try {
			result = sliDao.batchDelete(sliid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
