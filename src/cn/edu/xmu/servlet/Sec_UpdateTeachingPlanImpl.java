package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

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
import cn.edu.xmu.table.TeachingPlanImplTable;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况 date 2015-7-8
 *
 */
@WebServlet("/Sec_UpdateTeachingPlanImpl")
public class Sec_UpdateTeachingPlanImpl extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateTeachingPlanImpl()
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
		String patter = request.getParameter("patter");
		// 解码
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			TeachingPlanImplDao teachingPlanImplDao = new TeachingPlanImplDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					JSONObject json = jsons.getJSONObject(i);
					String tpi_id = json.getString(TeachingPlanImplTable.TPI_ID);
					String tpi_comments = json.getString(TeachingPlanImplTable.TPI_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(TeachingPlanImplTable.TPI_ID, tpi_id);
					params.put(TeachingPlanImplTable.TPI_COMMENTS, tpi_comments);
					int result = teachingPlanImplDao.alterTeachingPlanImpl(params, tpi_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String tpi_id = json.getString(TeachingPlanImplTable.TPI_ID);
				String tpi_institute = json.getString(TeachingPlanImplTable.TPI_INSTITUTE);
				String tpi_major = json.getString(TeachingPlanImplTable.TPI_MAJOR);
				String tpi_grade = json.getString(TeachingPlanImplTable.TPI_GRADE);
				String tpi_plancoursenum = json.getString(TeachingPlanImplTable.TPI_PLANCOURSENUM);
				String tpi_plancoursecredit = json.getString(TeachingPlanImplTable.TPI_PLANCOURSECREDIT);
				String tpi_actualcoursenum = json.getString(TeachingPlanImplTable.TPI_ACTUALCOURSENUM);
				String tpi_actualcoursecredit = json.getString(TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT);
				String tpi_newcoursenum = json.getString(TeachingPlanImplTable.TPI_NEWCOURSENUM);
				String tpi_newcoursecredit = json.getString(TeachingPlanImplTable.TPI_NEWCOURSECREDIT);
				String tpi_stopcoursenum = json.getString(TeachingPlanImplTable.TPI_STOPCOURSENUM);
				String tpi_stopcoursecredit = json.getString(TeachingPlanImplTable.TPI_STOPCOURSECREDIT);
				String tpi_advancelatercoursenum = json.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM);
				String tpi_advancelatercoursecredit = json
						.getString(TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT);
				String tpi_comments = json.getString(TeachingPlanImplTable.TPI_COMMENTS);
				String tpi_isnull = "0";
				if ("".equals(tpi_institute) || "".equals(tpi_major) || "".equals(tpi_grade) || "".equals(tpi_plancoursenum)
						|| "".equals(tpi_plancoursecredit) || "".equals(tpi_actualcoursenum) || "".equals(tpi_actualcoursecredit)
						|| "".equals(tpi_newcoursenum) || "".equals(tpi_newcoursecredit) || "".equals(tpi_stopcoursenum)
						|| "".equals(tpi_stopcoursecredit) || "".equals(tpi_advancelatercoursenum) || "".equals(tpi_advancelatercoursecredit))
					tpi_isnull = "1";
				if("".equals(tpi_plancoursenum))
					tpi_plancoursenum = "-9";
				if("".equals(tpi_plancoursecredit))
					tpi_plancoursecredit = "-9";
				if("".equals(tpi_actualcoursenum))
					tpi_actualcoursenum = "-9";
				if("".equals(tpi_actualcoursecredit))
					tpi_actualcoursecredit = "-9";
				if("".equals(tpi_newcoursenum))
					tpi_newcoursenum = "-9";
				if("".equals(tpi_newcoursecredit))
					tpi_newcoursecredit = "-9";
				if("".equals(tpi_stopcoursenum))
					tpi_stopcoursenum = "-9";
				if("".equals(tpi_stopcoursecredit))
					tpi_stopcoursecredit = "-9";
				if("".equals(tpi_advancelatercoursecredit))
					tpi_advancelatercoursecredit = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(TeachingPlanImplTable.TPI_ID, tpi_id);
				params.put(TeachingPlanImplTable.TPI_INSTITUTE, tpi_institute);
				params.put(TeachingPlanImplTable.TPI_MAJOR, tpi_major);
				params.put(TeachingPlanImplTable.TPI_GRADE, tpi_grade);
				params.put(TeachingPlanImplTable.TPI_PLANCOURSENUM, tpi_plancoursenum);
				params.put(TeachingPlanImplTable.TPI_PLANCOURSECREDIT, tpi_plancoursecredit);
				params.put(TeachingPlanImplTable.TPI_ACTUALCOURSENUM, tpi_actualcoursenum);
				params.put(TeachingPlanImplTable.TPI_ACTUALCOURSECREDIT, tpi_actualcoursecredit);
				params.put(TeachingPlanImplTable.TPI_NEWCOURSENUM, tpi_newcoursenum);
				params.put(TeachingPlanImplTable.TPI_NEWCOURSECREDIT, tpi_newcoursecredit);
				params.put(TeachingPlanImplTable.TPI_STOPCOURSENUM, tpi_stopcoursenum);
				params.put(TeachingPlanImplTable.TPI_STOPCOURSECREDIT, tpi_stopcoursecredit);
				params.put(TeachingPlanImplTable.TPI_ADVANCELATERCOURSENUM, tpi_advancelatercoursenum);
				params.put(TeachingPlanImplTable.TPI_ADVANCELATERCOURSECREDIT, tpi_advancelatercoursecredit);
				params.put(TeachingPlanImplTable.TPI_COMMENTS, tpi_comments);
				params.put(TeachingPlanImplTable.TPI_ISNULL, tpi_isnull);
				int result = teachingPlanImplDao.alterTeachingPlanImpl(params, tpi_id);

				out.print(result);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}
	}
}
