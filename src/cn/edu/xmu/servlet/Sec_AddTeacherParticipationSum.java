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

import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.daoimpl.TeacherParticipationSumDaoImpl;
import cn.edu.xmu.entity.TeacherParticipationSum;
import cn.edu.xmu.table.TeacherParticipationSumTable;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-8
 *
 */
@WebServlet("/Sec_AddTeacherParticipationSum")
public class Sec_AddTeacherParticipationSum extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddTeacherParticipationSum()
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
		String college = request.getParameter(TeacherParticipationSumTable.TPS_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			JSONObject jsonObj = new JSONObject(data);
			TeacherParticipationSumDao tpsDao = new TeacherParticipationSumDaoImpl();
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);

			String tps_particollege = jsonObj.getString(TeacherParticipationSumTable.TPS_PARTICOLLEGE);

			String schskillcompecourtyardnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM);
			String schskillcompeschoolnum = jsonObj.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM);
			String schskillcompespecialnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM);
			String schskillcompefirstnum = jsonObj.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM);
			String schskillcompesecnum = jsonObj.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM);
			String provinskillcompepartinum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM);
			String provinskillcompespecialnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM);
			String provinskillcompefirstnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM);
			String provinskillcompesecnum = jsonObj.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM);
			String countryskillcompepartinum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM);
			String countryskillcompespecialnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM);
			String countryskillcompefirstnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM);
			String countryskillcompesecnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM);
			String countrymicrocompepartinum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM);
			String countrymicrocompespecialnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM);
			String countrymicrocompefirstnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM);
			String countrymicrocompesecnum = jsonObj
					.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM);
			String tps_preparer = "";
			// String tps_preparer =
			// jsonObj.getString(TeacherParticipationSumTable.TPS_PREPARER);
			int tps_serialnumber = serialnumber;
			String tps_college = college;
			String tps_comments = "";
			int tps_isnull = 0;
			if ("".equals(tps_particollege) || "".equals(schskillcompecourtyardnum) || "".equals(schskillcompeschoolnum)
					|| "".equals(schskillcompespecialnum) || "".equals(schskillcompefirstnum)
					|| "".equals(schskillcompesecnum) || "".equals(provinskillcompepartinum)
					|| "".equals(provinskillcompespecialnum) || "".equals(provinskillcompefirstnum)
					|| "".equals(provinskillcompesecnum) || "".equals(countryskillcompepartinum)
					|| "".equals(countryskillcompespecialnum) || "".equals(countryskillcompefirstnum)
					|| "".equals(countryskillcompesecnum) || "".equals(countrymicrocompepartinum)
					|| "".equals(countrymicrocompespecialnum) || "".equals(countrymicrocompefirstnum)
					|| "".equals(countrymicrocompesecnum))
				tps_isnull = 1;
			if ("".equals(tps_particollege) && "".equals(schskillcompecourtyardnum) && "".equals(schskillcompeschoolnum)
					&& "".equals(schskillcompespecialnum) && "".equals(schskillcompefirstnum)
					&& "".equals(schskillcompesecnum) && "".equals(provinskillcompepartinum)
					&& "".equals(provinskillcompespecialnum) && "".equals(provinskillcompefirstnum)
					&& "".equals(provinskillcompesecnum) && "".equals(countryskillcompepartinum)
					&& "".equals(countryskillcompespecialnum) && "".equals(countryskillcompefirstnum)
					&& "".equals(countryskillcompesecnum) && "".equals(countrymicrocompepartinum)
					&& "".equals(countrymicrocompespecialnum) && "".equals(countrymicrocompefirstnum)
					&& "".equals(countrymicrocompesecnum))
			{
				out.print(-1);
				return;
			}
			int tps_schskillcompecourtyardnum = -9;
			int tps_schskillcompeschoolnum = -9;
			int tps_schskillcompespecialnum = -9;
			int tps_schskillcompefirstnum = -9;
			int tps_schskillcompesecnum = -9;
			int tps_provinskillcompepartinum = -9;
			int tps_provinskillcompespecialnum = -9;
			int tps_provinskillcompefirstnum = -9;
			int tps_provinskillcompesecnum = -9;
			int tps_countryskillcompepartinum = -9;
			int tps_countryskillcompespecialnum = -9;
			int tps_countryskillcompefirstnum = -9;
			int tps_countryskillcompesecnum = -9;
			int tps_countrymicrocompepartinum = -9;
			int tps_countrymicrocompespecialnum = -9;
			int tps_countrymicrocompefirstnum = -9;
			int tps_countrymicrocompesecnum = -9;
			if (!"".equals(schskillcompecourtyardnum))
				tps_schskillcompecourtyardnum = Integer.parseInt(schskillcompecourtyardnum);
			if (!"".equals(schskillcompeschoolnum))
				tps_schskillcompeschoolnum = Integer.parseInt(schskillcompeschoolnum);
			if (!"".equals(schskillcompespecialnum))
				tps_schskillcompespecialnum = Integer.parseInt(schskillcompespecialnum);
			if (!"".equals(schskillcompefirstnum))
				tps_schskillcompefirstnum = Integer.parseInt(schskillcompefirstnum);
			if (!"".equals(schskillcompesecnum))
				tps_schskillcompesecnum = Integer.parseInt(schskillcompesecnum);
			if (!"".equals(provinskillcompepartinum))
				tps_provinskillcompepartinum = Integer.parseInt(provinskillcompepartinum);
			if (!"".equals(provinskillcompespecialnum))
				tps_provinskillcompespecialnum = Integer.parseInt(provinskillcompespecialnum);
			if (!"".equals(provinskillcompefirstnum))
				tps_provinskillcompefirstnum = Integer.parseInt(provinskillcompefirstnum);
			if (!"".equals(provinskillcompesecnum))
				tps_provinskillcompesecnum = Integer.parseInt(provinskillcompesecnum);
			if (!"".equals(countryskillcompepartinum))
				tps_countryskillcompepartinum = Integer.parseInt(countryskillcompepartinum);
			if (!"".equals(countryskillcompespecialnum))
				tps_countryskillcompespecialnum = Integer.parseInt(countryskillcompespecialnum);
			if (!"".equals(countryskillcompefirstnum))
				tps_countryskillcompefirstnum = Integer.parseInt(countryskillcompefirstnum);
			if (!"".equals(countryskillcompesecnum))
				tps_countryskillcompesecnum = Integer.parseInt(countryskillcompesecnum);
			if (!"".equals(countrymicrocompepartinum))
				tps_countrymicrocompepartinum = Integer.parseInt(countrymicrocompepartinum);
			if (!"".equals(countrymicrocompespecialnum))
				tps_countrymicrocompespecialnum = Integer.parseInt(countrymicrocompespecialnum);
			if (!"".equals(countrymicrocompefirstnum))
				tps_countrymicrocompefirstnum = Integer.parseInt(countrymicrocompefirstnum);
			if (!"".equals(countrymicrocompesecnum))
				tps_countrymicrocompesecnum = Integer.parseInt(countrymicrocompesecnum);
			
			TeacherParticipationSum teacherParticipationSum = new TeacherParticipationSum(0, tps_particollege,
					tps_schskillcompecourtyardnum, tps_schskillcompeschoolnum, tps_schskillcompespecialnum,
					tps_schskillcompefirstnum, tps_schskillcompesecnum, tps_provinskillcompepartinum,
					tps_provinskillcompespecialnum, tps_provinskillcompefirstnum, tps_provinskillcompesecnum,
					tps_countryskillcompepartinum, tps_countryskillcompespecialnum, tps_countryskillcompefirstnum,
					tps_countryskillcompesecnum, tps_countrymicrocompepartinum, tps_countrymicrocompespecialnum,
					tps_countrymicrocompefirstnum, tps_countrymicrocompesecnum, tps_preparer, tps_serialnumber, null,
					tps_college, tps_comments, tps_isnull);

			result = tpsDao.addTeacherParticipationSum(teacherParticipationSum);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}
}
