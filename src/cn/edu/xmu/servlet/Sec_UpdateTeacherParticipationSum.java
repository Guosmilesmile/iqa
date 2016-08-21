package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
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

import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.daoimpl.TeacherParticipationSumDaoImpl;
import cn.edu.xmu.table.TeacherParticipationSumTable;
/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-8
 *
 */
@WebServlet("/Sec_UpdateTeacherParticipationSum")
public class Sec_UpdateTeacherParticipationSum extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateTeacherParticipationSum()
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
			TeacherParticipationSumDao teacherParticipationSumDao = new TeacherParticipationSumDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					JSONObject json = jsons.getJSONObject(i);
					String tps_id = json.getString(TeacherParticipationSumTable.TPS_ID);
					String tps_comments = json.getString(TeacherParticipationSumTable.TPS_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(TeacherParticipationSumTable.TPS_ID, tps_id);
					params.put(TeacherParticipationSumTable.TPS_COMMENTS, tps_comments);
					int result = teacherParticipationSumDao.alterTeacherParticipationSum(params, tps_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String tps_id = json.getString(TeacherParticipationSumTable.TPS_ID);
				String tps_particollege = json.getString(TeacherParticipationSumTable.TPS_PARTICOLLEGE);
				String tps_schskillcompecourtyardnum = json
						.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM);
				String tps_schskillcompeschoolnum = json
						.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM);
				String tps_schskillcompespecialnum = json
						.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM);
				String tps_schskillcompefirstnum = json.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM);
				String tps_schskillcompesecnum = json.getString(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM);
				String tps_provinskillcompepartinum = json
						.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM);
				String tps_provinskillcompespecialnum = json
						.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM);
				String tps_provinskillcompefirstnum = json
						.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM);
				String tps_provinskillcompesecnum = json
						.getString(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM);
				String tps_countryskillcompepartinum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM);
				String tps_countryskillcompespecialnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM);
				String tps_countryskillcompefirstnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM);
				String tps_countryskillcompesecnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM);
				String tps_countrymicrocompepartinum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM);
				String tps_countrymicrocompespecialnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM);
				String tps_countrymicrocompefirstnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM);
				String tps_countrymicrocompesecnum = json
						.getString(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM);
				String tps_preparer = "";// json.getString(TeacherParticipationSumTable.TPS_PREPARER);
				String tps_comments = json.getString(TeacherParticipationSumTable.TPS_COMMENTS);
				String tps_isnull = "0";
				if ("".equals(tps_particollege) || "".equals(tps_schskillcompecourtyardnum) || "".equals(tps_schskillcompeschoolnum)
						|| "".equals(tps_schskillcompespecialnum) || "".equals(tps_schskillcompefirstnum)
						|| "".equals(tps_schskillcompesecnum) || "".equals(tps_provinskillcompepartinum)
						|| "".equals(tps_provinskillcompespecialnum) || "".equals(tps_provinskillcompefirstnum)
						|| "".equals(tps_provinskillcompesecnum) || "".equals(tps_countryskillcompepartinum)
						|| "".equals(tps_countryskillcompespecialnum) || "".equals(tps_countryskillcompefirstnum)
						|| "".equals(tps_countryskillcompesecnum) || "".equals(tps_countrymicrocompepartinum)
						|| "".equals(tps_countrymicrocompespecialnum) || "".equals(tps_countrymicrocompefirstnum)
						|| "".equals(tps_countrymicrocompesecnum))
					tps_isnull = "1";
				if ("".equals(tps_schskillcompecourtyardnum))
					tps_schskillcompecourtyardnum = "-9";
				if ("".equals(tps_schskillcompeschoolnum))
					tps_schskillcompeschoolnum = "-9";
				if ("".equals(tps_schskillcompespecialnum))
					tps_schskillcompespecialnum = "-9";
				if ("".equals(tps_schskillcompefirstnum))
					tps_schskillcompefirstnum = "-9";
				if ("".equals(tps_schskillcompesecnum))
					tps_schskillcompesecnum = "-9";
				if ("".equals(tps_provinskillcompepartinum))
					tps_provinskillcompepartinum = "-9";
				if ("".equals(tps_provinskillcompespecialnum))
					tps_provinskillcompespecialnum = "-9";
				if ("".equals(tps_provinskillcompefirstnum))
					tps_provinskillcompefirstnum = "-9";
				if ("".equals(tps_provinskillcompesecnum))
					tps_provinskillcompesecnum = "-9";
				if ("".equals(tps_countryskillcompepartinum))
					tps_countryskillcompepartinum = "-9";
				if ("".equals(tps_countryskillcompespecialnum))
					tps_countryskillcompespecialnum = "-9";
				if ("".equals(tps_countryskillcompefirstnum))
					tps_countryskillcompefirstnum = "-9";
				if ("".equals(tps_countryskillcompesecnum))
					tps_countryskillcompesecnum = "-9";
				if ("".equals(tps_countrymicrocompepartinum))
					tps_countrymicrocompepartinum = "-9";
				if ("".equals(tps_countrymicrocompespecialnum))
					tps_countrymicrocompespecialnum = "-9";
				if ("".equals(tps_countrymicrocompefirstnum))
					tps_countrymicrocompefirstnum = "-9";
				if ("".equals(tps_countrymicrocompesecnum))
					tps_countrymicrocompesecnum = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(TeacherParticipationSumTable.TPS_ID, tps_id);
				params.put(TeacherParticipationSumTable.TPS_PARTICOLLEGE, tps_particollege);
				params.put(TeacherParticipationSumTable.TPS_SCHSKILLCOMPECOURTYARDNUM, tps_schskillcompecourtyardnum);
				params.put(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESCHOOLNUM, tps_schskillcompeschoolnum);
				params.put(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESPECIALNUM, tps_schskillcompespecialnum);
				params.put(TeacherParticipationSumTable.TPS_SCHSKILLCOMPEFIRSTNUM, tps_schskillcompefirstnum);
				params.put(TeacherParticipationSumTable.TPS_SCHSKILLCOMPESECNUM, tps_schskillcompesecnum);
				params.put(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEPARTINUM, tps_provinskillcompepartinum);
				params.put(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESPECIALNUM, tps_provinskillcompespecialnum);
				params.put(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPEFIRSTNUM, tps_provinskillcompefirstnum);
				params.put(TeacherParticipationSumTable.TPS_PROVINSKILLCOMPESECNUM, tps_provinskillcompesecnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEPARTINUM, tps_countryskillcompepartinum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESPECIALNUM, tps_countryskillcompespecialnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPEFIRSTNUM, tps_countryskillcompefirstnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYSKILLCOMPESECNUM, tps_countryskillcompesecnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEPARTINUM, tps_countrymicrocompepartinum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESPECIALNUM, tps_countrymicrocompespecialnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPEFIRSTNUM, tps_countrymicrocompefirstnum);
				params.put(TeacherParticipationSumTable.TPS_COUNTRYMICROCOMPESECNUM, tps_countrymicrocompesecnum);
				params.put(TeacherParticipationSumTable.TPS_PREPARER, tps_preparer);
				params.put(TeacherParticipationSumTable.TPS_COMMENTS, tps_comments);
				params.put(TeacherParticipationSumTable.TPS_ISNULL, tps_isnull);
				int result = teacherParticipationSumDao.alterTeacherParticipationSum(params, tps_id);

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
