package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.daoimpl.CoverBuildingAreaDaoImpl;

@WebServlet("/DeleteCoverBuildingArea")
public class Sec_DeleteCoverBuildingArea extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sec_DeleteCoverBuildingArea()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String cba_id = request.getParameter("cbaids");
		
		String cbaid[] = cba_id.split(",");
		
		CoverBuildingAreaDao coverBuildingAreaDao = new CoverBuildingAreaDaoImpl();
		boolean result = false;
		try {
			result = coverBuildingAreaDao.batchDelete(cbaid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
	
	

}
