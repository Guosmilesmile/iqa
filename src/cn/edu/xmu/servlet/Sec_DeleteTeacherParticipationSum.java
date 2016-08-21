package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.daoimpl.TeacherParticipationSumDaoImpl;
/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-5
 *
 */
@WebServlet("/Sec_DeleteTeacherParticipationSum")
public class Sec_DeleteTeacherParticipationSum extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteTeacherParticipationSum()
	{
		super();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String tps_id = request.getParameter("tpsids");

		String tpsid[] = tps_id.split(",");

		TeacherParticipationSumDao teacherParticipationSumDao = new TeacherParticipationSumDaoImpl();
		boolean result = false;
		try
		{
			result = teacherParticipationSumDao.batchDelete(tpsid);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		System.out.println("删除纪录的结果" + result);

		out.print(result);
	}
}
