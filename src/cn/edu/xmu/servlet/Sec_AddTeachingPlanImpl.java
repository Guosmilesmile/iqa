package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.TeachingPlanImplDao;
import cn.edu.xmu.daoimpl.TeachingPlanImplDaoImpl;
import cn.edu.xmu.entity.TeachingPlanImpl;
import cn.edu.xmu.table.TeachingPlanImplTable;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况 date 2015-7-8
 *
 */
@WebServlet("/Sec_AddTeachingPlanImpl")
public class Sec_AddTeachingPlanImpl extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddTeachingPlanImpl()
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

		String data = request.getParameter("rowdata");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		System.out.println(data);
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(TeachingPlanImplTable.TPI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			JSONObject jsonObj = new JSONObject(data);
			TeachingPlanImplDao tpiDao = new TeachingPlanImplDaoImpl();
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);

			String tpi_institute = jsonObj.getString(TeachingPlanImplTable.TPI_INSTITUTE);
			String tpi_major = jsonObj.getString(TeachingPlanImplTable.TPI_MAJOR);
			String tpi_grade = jsonObj.getString(TeachingPlanImplTable.TPI_GRADE);
			String plancoursenum = jsonObj.getString(TeachingPlanImplTable.TPI_PLANCOURSENUM);
			String plancoursecredit = jsonObj.getString(TeachingPlanImplTable.TPI_PLANCOURSECREDIT);
			String actualcoursenum = jsonObj.getString(TeachingPlanImplTable.TPI_ACTUALCOURSENUM);
			String actualcoursecredit = jsonObj.getString(TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT);
			String newcoursenum = jsonObj.getString(TeachingPlanImplTable.TPI_NEWCOURSENUM);
			String newcoursecredit = jsonObj.getString(TeachingPlanImplTable.TPI_NEWCOURSECREDIT);
			String stopcoursenum = jsonObj.getString(TeachingPlanImplTable.TPI_STOPCOURSENUM);
			String stopcoursecredit = jsonObj.getString(TeachingPlanImplTable.TPI_STOPCOURSECREDIT);
			String advancelatercoursenum = jsonObj.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM);
			String advancelatercoursecredit = jsonObj.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT);

			int tpi_serialnumber = serialnumber;
			String tpi_college = college;
			String tpi_comments = "";
			int tpi_isnull = 0;
			if ("".equals(tpi_institute) || "".equals(tpi_major) || "".equals(tpi_grade) || "".equals(plancoursenum)
					|| "".equals(plancoursecredit) || "".equals(actualcoursenum) || "".equals(actualcoursecredit)
					|| "".equals(newcoursenum) || "".equals(newcoursecredit) || "".equals(stopcoursenum)
					|| "".equals(stopcoursecredit) || "".equals(advancelatercoursenum) || "".equals(advancelatercoursecredit))
				tpi_isnull = 1;
			if ("".equals(tpi_institute) && "".equals(tpi_major) && "".equals(tpi_grade) && "".equals(plancoursenum)
					&& "".equals(plancoursecredit) && "".equals(actualcoursenum) && "".equals(actualcoursecredit)
					&& "".equals(newcoursenum) && "".equals(newcoursecredit) && "".equals(stopcoursenum)
					&& "".equals(stopcoursecredit) && "".equals(advancelatercoursenum) && "".equals(advancelatercoursecredit))
			{
				out.print(-1);
				return;
			}
			int tpi_plancoursenum = -9;
			float tpi_plancoursecredit = -9;
			int tpi_actualcoursenum = -9;
			float tpi_actualcoursecredit = -9;
			int tpi_newcoursenum = -9;
			float tpi_newcoursecredit = -9;
			int tpi_stopcoursenum = -9;
			float tpi_stopcoursecredit = -9;
			float tpi_advancelatercoursecredit = -9;
			if(!"".equals(plancoursenum))
				tpi_plancoursenum = Integer.parseInt(plancoursenum);
			if(!"".equals(plancoursecredit))
				tpi_plancoursecredit = Float.parseFloat(plancoursecredit);
			if(!"".equals(actualcoursenum))
				tpi_actualcoursenum = Integer.parseInt(actualcoursenum);
			if(!"".equals(actualcoursecredit))
				tpi_actualcoursecredit = Float.parseFloat(actualcoursecredit);
			if(!"".equals(newcoursenum))
				tpi_newcoursenum = Integer.parseInt(newcoursenum);
			if(!"".equals(newcoursecredit))
				tpi_newcoursecredit = Float.parseFloat(newcoursecredit);
			if(!"".equals(stopcoursenum))
				tpi_stopcoursenum = Integer.parseInt(stopcoursenum);
			if(!"".equals(stopcoursecredit))
				tpi_stopcoursecredit = Float.parseFloat(stopcoursecredit);
			if(!"".equals(advancelatercoursecredit))
				tpi_advancelatercoursecredit = Float.parseFloat(advancelatercoursecredit);
			TeachingPlanImpl teachingPlanImpl = new TeachingPlanImpl(0, tpi_institute, tpi_major, tpi_grade,
					tpi_plancoursenum, tpi_plancoursecredit, tpi_actualcoursenum, tpi_actualcoursecredit,
					tpi_newcoursenum, tpi_newcoursecredit, tpi_stopcoursenum, tpi_stopcoursecredit,
					advancelatercoursenum, tpi_advancelatercoursecredit, tpi_serialnumber, null, tpi_college,
					tpi_comments, tpi_isnull);

			result = tpiDao.addTeachingPlanImpl(teachingPlanImpl);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}
}
