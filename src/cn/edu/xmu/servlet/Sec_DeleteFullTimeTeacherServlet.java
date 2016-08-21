package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;

/**
 * 
 * @author xiaoping 数据报表3-1-1 专任教师基本信息表 date 2015-6-29
 *
 */
@WebServlet("/Sec_DeleteFullTimeTeacher")
public class Sec_DeleteFullTimeTeacherServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sec_DeleteFullTimeTeacherServlet()
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String ftti_id = request.getParameter("fttiids");
		
		String fttiid[] = ftti_id.split(",");
		
		FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
		boolean result = false;
		try {
			result = fullTimeTeacherInfoDao.batchDelete(fttiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
