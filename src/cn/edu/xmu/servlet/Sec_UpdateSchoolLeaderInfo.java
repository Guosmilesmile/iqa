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

import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.table.SchoolLeaderInfoTable;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_UpdateSchoolLeaderInfo")
public class Sec_UpdateSchoolLeaderInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateSchoolLeaderInfo()
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
			SchoolLeaderInfoDao sliDao = new SchoolLeaderInfoDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String sli_id = jsons.getJSONObject(i).getString(SchoolLeaderInfoTable.SLI_ID);
					String sli_comments = jsons.getJSONObject(i).getString(SchoolLeaderInfoTable.SLI_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(SchoolLeaderInfoTable.SLI_ID, sli_id);
					params.put(SchoolLeaderInfoTable.SLI_COMMENTS, sli_comments);
					
					int result = sliDao.alterSchoolLeaderInfo(params, sli_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String sli_id = json.getString(SchoolLeaderInfoTable.SLI_ID);
				String sli_sequencenumber = json.getString(SchoolLeaderInfoTable.SLI_SEQUENCENUMBER);
				String sli_name = json.getString(SchoolLeaderInfoTable.SLI_NAME);
				String sli_worknumber = json.getString(SchoolLeaderInfoTable.SLI_WORKNUMBER);
				String sli_position = json.getString(SchoolLeaderInfoTable.SLI_POSITION);
				String sli_gender = json.getString(SchoolLeaderInfoTable.SLI_GENDER);
				String sli_birthday = json.getString(SchoolLeaderInfoTable.SLI_BIRTHDAY);
				String sli_inschooldate = json.getString(SchoolLeaderInfoTable.SLI_INSCHOOLDATE);
				String sli_education = json.getString(SchoolLeaderInfoTable.SLI_EDUCATION);
				String sli_degree = json.getString(SchoolLeaderInfoTable.SLI_DEGREE);
				String sli_professionaltitle = json.getString(SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE);
				String sli_responsibility = json.getString(SchoolLeaderInfoTable.SLI_RESPONSIBILITY);
				String sli_studyworkresume = json.getString(SchoolLeaderInfoTable.SLI_STUDYWORKRESUME);
				String sli_deadline = (new Date(System.currentTimeMillis())).toString();
				String sli_comments = json.getString(SchoolLeaderInfoTable.SLI_COMMENTS);
				String sli_isnull = "0";
				if ("".equals(sli_sequencenumber) || "".equals(sli_name) || "".equals(sli_worknumber)
						|| "".equals(sli_position) || "".equals(sli_gender) || "".equals(sli_birthday)
						|| "".equals(sli_inschooldate) || "".equals(sli_education) || "".equals(sli_degree)
						|| "".equals(sli_professionaltitle) || "".equals(sli_responsibility) || "".equals(sli_studyworkresume))
					sli_isnull = "1";
				if(sli_sequencenumber.equals(""))
					sli_sequencenumber = "-9";
				if("".equals(sli_birthday))
					sli_birthday = "1800-1-1";
				if("".equals(sli_inschooldate))
					sli_inschooldate = "1800-1-1";
				Map<String, String> params = new HashMap<String, String>();
				params.put(SchoolLeaderInfoTable.SLI_ID, sli_id);
				params.put(SchoolLeaderInfoTable.SLI_SEQUENCENUMBER, sli_sequencenumber);
				params.put(SchoolLeaderInfoTable.SLI_NAME, sli_name);
				params.put(SchoolLeaderInfoTable.SLI_WORKNUMBER, sli_worknumber);
				params.put(SchoolLeaderInfoTable.SLI_POSITION, sli_position);
				params.put(SchoolLeaderInfoTable.SLI_GENDER, sli_gender);
				params.put(SchoolLeaderInfoTable.SLI_BIRTHDAY, sli_birthday);
				params.put(SchoolLeaderInfoTable.SLI_INSCHOOLDATE, sli_inschooldate);
				params.put(SchoolLeaderInfoTable.SLI_EDUCATION, sli_education);
				params.put(SchoolLeaderInfoTable.SLI_DEGREE, sli_degree);
				params.put(SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE, sli_professionaltitle);
				params.put(SchoolLeaderInfoTable.SLI_RESPONSIBILITY, sli_responsibility);
				params.put(SchoolLeaderInfoTable.SLI_STUDYWORKRESUME, sli_studyworkresume);
				params.put(SchoolLeaderInfoTable.SLI_DEADLINE, sli_deadline);
				params.put(SchoolLeaderInfoTable.SLI_COMMENTS, sli_comments);
				params.put(SchoolLeaderInfoTable.SLI_ISNULL, sli_isnull);
				int result = sliDao.alterSchoolLeaderInfo(params, sli_id);

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
