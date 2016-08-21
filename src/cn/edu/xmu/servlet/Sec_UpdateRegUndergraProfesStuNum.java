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

import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
@WebServlet("/Sec_UpdateRegUndergraProfesStuNum")
public class Sec_UpdateRegUndergraProfesStuNum extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateRegUndergraProfesStuNum()
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
			RegUndergraProfesStuNumDao regUndergraProfesStuNumDao = new RegUndergraProfesStuNumDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					JSONObject json = jsons.getJSONObject(i);
					String rupsn_id = json.getString(RegUndergraProfesStuNumTable.RUPSN_ID);
					String rupsn_comments = json.getString(RegUndergraProfesStuNumTable.RUPSN_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(RegUndergraProfesStuNumTable.RUPSN_ID, rupsn_id);
					params.put(RegUndergraProfesStuNumTable.RUPSN_COMMENTS, rupsn_comments);
					int result = regUndergraProfesStuNumDao.alterRegUndergraProfesStuNum(params, rupsn_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String rupsn_id = json.getString(RegUndergraProfesStuNumTable.RUPSN_ID);
				String rupsn_schprofescode = json.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE);
				String rupsn_schprofesname = json.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME);
				String rupsn_edusystem = json.getString(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM);
				// String rupsn_atschtotal =
				// json.getString(RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL);
				String rupsn_gradeone = json.getString(RegUndergraProfesStuNumTable.RUPSN_GRADEONE);
				String rupsn_gradetwo = json.getString(RegUndergraProfesStuNumTable.RUPSN_GRADETWO);
				String rupsn_gradethree = json.getString(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE);
				String rupsn_gradefour = json.getString(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR);
				String rupsn_abgradefive = json.getString(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE);
				int atschtotal = 0;
				String rupsn_minor = json.getString(RegUndergraProfesStuNumTable.RUPSN_MINOR);
				String rupsn_doubledegree = json.getString(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE);
				String rupsn_intonumber = json.getString(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER);
				String rupsn_outnumber = json.getString(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER);
				String rupsn_comments = json.getString(RegUndergraProfesStuNumTable.RUPSN_COMMENTS);
				String rupsn_isnull = "0";
				if ("".equals(rupsn_schprofescode) || "".equals(rupsn_schprofesname) || "".equals(rupsn_edusystem)
						|| "".equals(rupsn_gradeone) || "".equals(rupsn_gradetwo) || "".equals(rupsn_gradethree)
						|| "".equals(rupsn_gradefour) || "".equals(rupsn_abgradefive) || "".equals(rupsn_minor)
						|| "".equals(rupsn_doubledegree) || "".equals(rupsn_intonumber) || "".equals(rupsn_outnumber))
					rupsn_isnull = "1";
				if (rupsn_gradeone != null && !"".equals(rupsn_gradeone))
					atschtotal += Integer.parseInt(rupsn_gradeone);
				if (rupsn_gradetwo != null && !"".equals(rupsn_gradetwo))
					atschtotal += Integer.parseInt(rupsn_gradetwo);
				if (rupsn_gradethree != null && !"".equals(rupsn_gradethree))
					atschtotal += Integer.parseInt(rupsn_gradethree);
				if (rupsn_gradefour != null && !"".equals(rupsn_gradefour))
					atschtotal += Integer.parseInt(rupsn_gradefour);
				if (rupsn_abgradefive != null && !"".equals(rupsn_abgradefive))
					atschtotal += Integer.parseInt(rupsn_abgradefive);
				if (rupsn_edusystem == null || "".equals(rupsn_edusystem))
					rupsn_edusystem = "-9";
				if (rupsn_gradeone == null || "".equals(rupsn_gradeone))
					rupsn_gradeone = "-9";
				if (rupsn_gradetwo == null || "".equals(rupsn_gradetwo))
					rupsn_gradetwo = "-9";
				if (rupsn_gradethree == null || "".equals(rupsn_gradethree))
					rupsn_gradethree = "-9";
				if (rupsn_gradefour == null || "".equals(rupsn_gradefour))
					rupsn_gradefour = "-9";
				if (rupsn_abgradefive == null || "".equals(rupsn_abgradefive))
					rupsn_abgradefive = "-9";
				if (rupsn_minor == null || "".equals(rupsn_minor))
					rupsn_minor = "-9";
				if (rupsn_doubledegree == null || "".equals(rupsn_doubledegree))
					rupsn_doubledegree = "-9";
				if (rupsn_intonumber == null || "".equals(rupsn_intonumber))
					rupsn_intonumber = "-9";
				if (rupsn_outnumber == null || "".equals(rupsn_outnumber))
					rupsn_outnumber = "-9";
				String rupsn_atschtotal = Integer.toString(atschtotal);

				Map<String, String> params = new HashMap<String, String>();
				params.put(RegUndergraProfesStuNumTable.RUPSN_ID, rupsn_id);
				params.put(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE, rupsn_schprofescode);
				params.put(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME, rupsn_schprofesname);
				params.put(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM, rupsn_edusystem);
				params.put(RegUndergraProfesStuNumTable.RUPSN_ATSCHTOTAL, rupsn_atschtotal);
				params.put(RegUndergraProfesStuNumTable.RUPSN_GRADEONE, rupsn_gradeone);
				params.put(RegUndergraProfesStuNumTable.RUPSN_GRADETWO, rupsn_gradetwo);
				params.put(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE, rupsn_gradethree);
				params.put(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR, rupsn_gradefour);
				params.put(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE, rupsn_abgradefive);
				params.put(RegUndergraProfesStuNumTable.RUPSN_MINOR, rupsn_minor);
				params.put(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE, rupsn_doubledegree);
				params.put(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER, rupsn_intonumber);
				params.put(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER, rupsn_outnumber);
				params.put(RegUndergraProfesStuNumTable.RUPSN_COMMENTS, rupsn_comments);
				params.put(RegUndergraProfesStuNumTable.RUPSN_ISNULL, rupsn_isnull);
				int result = regUndergraProfesStuNumDao.alterRegUndergraProfesStuNum(params, rupsn_id);

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
