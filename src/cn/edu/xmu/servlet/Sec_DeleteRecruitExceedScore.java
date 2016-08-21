package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RecruitExceedScoreDao;
import cn.edu.xmu.daoimpl.RecruitExceedScoreDaoImpl;
/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
@WebServlet("/Sec_DeleteRecruitExceedScore")
public class Sec_DeleteRecruitExceedScore extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_DeleteRecruitExceedScore()
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
		String res_id = request.getParameter("resids");
		
		String resid[] = res_id.split(",");
		
		RecruitExceedScoreDao resDao = new RecruitExceedScoreDaoImpl();
		boolean result = false;
		try {
			result = resDao.batchDelete(resid);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		System.out.println("删除纪录的结果"+result);
		out.print(result);
	}
}
