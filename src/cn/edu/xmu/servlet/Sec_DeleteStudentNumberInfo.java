package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
@WebServlet("/Sec_DeleteStudentNumberInfo")
public class Sec_DeleteStudentNumberInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteStudentNumberInfo()
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
		String sni_id = request.getParameter("sniids");
		
		String sniid[] = sni_id.split(",");
		
		StudentNumberInfoDao studentNumberInfoDao = new StudentNumberInfoDaoImpl();
		boolean result = false;
		try {
			result = studentNumberInfoDao.batchDelete(sniid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}

}
