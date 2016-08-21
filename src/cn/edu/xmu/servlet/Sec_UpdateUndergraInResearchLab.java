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

import cn.edu.xmu.dao.UndergraInResearchLabDao;
import cn.edu.xmu.daoimpl.UndergraInResearchLabDaoImpl;
import cn.edu.xmu.table.UndergraInResearchLabTable;
/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
@WebServlet("/Sec_UpdateUndergraInResearchLab")
public class Sec_UpdateUndergraInResearchLab extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_UpdateUndergraInResearchLab()
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
			UndergraInResearchLabDao uirlDao = new UndergraInResearchLabDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String uirl_id = jsons.getJSONObject(i).getString(UndergraInResearchLabTable.UIRL_ID);
					String uirl_comments = jsons.getJSONObject(i).getString(UndergraInResearchLabTable.UIRL_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(UndergraInResearchLabTable.UIRL_ID, uirl_id);
					params.put(UndergraInResearchLabTable.UIRL_COMMENTS, uirl_comments);
					int result = uirlDao.alterUndergraInResearchLab(params, uirl_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String uirl_id = json.getString(UndergraInResearchLabTable.UIRL_ID);
				String uirl_institute = json.getString(UndergraInResearchLabTable.UIRL_INSTITUTE);
				String uirl_major = json.getString(UndergraInResearchLabTable.UIRL_MAJOR);
				String uirl_grade = json.getString(UndergraInResearchLabTable.UIRL_GRADE);
				String uirl_name = json.getString(UndergraInResearchLabTable.UIRL_NAME);
				String uirl_days = json.getString(UndergraInResearchLabTable.UIRL_DAYS);
				String uirl_times = json.getString(UndergraInResearchLabTable.UIRL_TIMES);
				String uirl_totalhours = json.getString(UndergraInResearchLabTable.UIRL_TOTALHOURS);
				String uirl_laboratoryname = json.getString(UndergraInResearchLabTable.UIRL_LABORATORYNAME);
				String uirl_laboratorydirector = json.getString(UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR);
				String uirl_tutor = json.getString(UndergraInResearchLabTable.UIRL_TUTOR);
				String uirl_tutortitle = json.getString(UndergraInResearchLabTable.UIRL_TUTORTITLE);
				String uirl_ispartiresearchproject = json
						.getString(UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT);
				String uirl_researchprojectname = json.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME);
				String uirl_researchprojectlevel = json.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL);
				String uirl_createprojectname = json.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME);
				String uirl_createprojecttype = json.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE);
				String uirl_createprojectlevel = json.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL);
				String uirl_paper = json.getString(UndergraInResearchLabTable.UIRL_PAPER);
				String uirl_patent = json.getString(UndergraInResearchLabTable.UIRL_PATENT);
				String uirl_note = json.getString(UndergraInResearchLabTable.UIRL_NOTE);
				String uirl_comments = json.getString(UndergraInResearchLabTable.UIRL_COMMENTS);
				String uirl_isnull = "0";
				if ("".equals(uirl_institute) || "".equals(uirl_major) || "".equals(uirl_grade)
						|| "".equals(uirl_name) || "".equals(uirl_days) || "".equals(uirl_times)
						|| "".equals(uirl_totalhours) || "".equals(uirl_laboratoryname) || "".equals(uirl_laboratorydirector)
						|| "".equals(uirl_tutor) || "".equals(uirl_tutortitle) || "".equals(uirl_ispartiresearchproject)
						|| "".equals(uirl_researchprojectname) || "".equals(uirl_researchprojectlevel) || "".equals(uirl_createprojectname)
						|| "".equals(uirl_createprojecttype) || "".equals(uirl_createprojectlevel) || "".equals(uirl_paper)
						|| "".equals(uirl_patent))
					uirl_isnull = "1";
				if("".equals(uirl_days))
					uirl_days = "-9";
				if("".equals(uirl_times))
					uirl_times = "-9";
				if("".equals(uirl_totalhours))
					uirl_totalhours = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(UndergraInResearchLabTable.UIRL_ID, uirl_id);
				params.put(UndergraInResearchLabTable.UIRL_INSTITUTE, uirl_institute);
				params.put(UndergraInResearchLabTable.UIRL_MAJOR, uirl_major);
				params.put(UndergraInResearchLabTable.UIRL_GRADE, uirl_grade);
				params.put(UndergraInResearchLabTable.UIRL_NAME, uirl_name);
				params.put(UndergraInResearchLabTable.UIRL_DAYS, uirl_days);
				params.put(UndergraInResearchLabTable.UIRL_TIMES, uirl_times);
				params.put(UndergraInResearchLabTable.UIRL_TOTALHOURS, uirl_totalhours);
				params.put(UndergraInResearchLabTable.UIRL_LABORATORYNAME, uirl_laboratoryname);
				params.put(UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR, uirl_laboratorydirector);
				params.put(UndergraInResearchLabTable.UIRL_TUTOR, uirl_tutor);
				params.put(UndergraInResearchLabTable.UIRL_TUTORTITLE, uirl_tutortitle);
				params.put(UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT, uirl_ispartiresearchproject);
				params.put(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME, uirl_researchprojectname);
				params.put(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL, uirl_researchprojectlevel);
				params.put(UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME, uirl_createprojectname);
				params.put(UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE, uirl_createprojecttype);
				params.put(UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL, uirl_createprojectlevel);
				params.put(UndergraInResearchLabTable.UIRL_PAPER, uirl_paper);
				params.put(UndergraInResearchLabTable.UIRL_PATENT, uirl_patent);
				params.put(UndergraInResearchLabTable.UIRL_NOTE, uirl_note);
				params.put(UndergraInResearchLabTable.UIRL_COMMENTS, uirl_comments);
				params.put(UndergraInResearchLabTable.UIRL_ISNULL, uirl_isnull);
				int result = uirlDao.alterUndergraInResearchLab(params, uirl_id);
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
