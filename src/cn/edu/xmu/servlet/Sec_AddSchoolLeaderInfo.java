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

import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.entity.SchoolLeaderInfo;
import cn.edu.xmu.table.SchoolLeaderInfoTable;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_AddSchoolLeaderInfo")
public class Sec_AddSchoolLeaderInfo extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddSchoolLeaderInfo()
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
		String college = request.getParameter(SchoolLeaderInfoTable.SLI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			SchoolLeaderInfoDao sliDao = new SchoolLeaderInfoDaoImpl();
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);
			String sequencenumber = jsonObj.getString(SchoolLeaderInfoTable.SLI_SEQUENCENUMBER);
			int sli_sequencenumber = -9;
			if (sequencenumber != null && !"".equals(sequencenumber))
				sli_sequencenumber = Integer.parseInt(sequencenumber);
			String sli_name = jsonObj.getString(SchoolLeaderInfoTable.SLI_NAME);
			String sli_worknumber = jsonObj.getString(SchoolLeaderInfoTable.SLI_WORKNUMBER);
			String sli_position = jsonObj.getString(SchoolLeaderInfoTable.SLI_POSITION);
			String sli_gender = jsonObj.getString(SchoolLeaderInfoTable.SLI_GENDER);
			String birthday = jsonObj.getString(SchoolLeaderInfoTable.SLI_BIRTHDAY);
			Date sli_birthday = Date.valueOf("1800-1-1");
			if (birthday != null && !"".equals(birthday))
				sli_birthday = Date.valueOf(birthday);
			String inschooldate = jsonObj.getString(SchoolLeaderInfoTable.SLI_INSCHOOLDATE);
			Date sli_inschooldate = Date.valueOf("1800-1-1");
			if (inschooldate != null && !"".equals(inschooldate))
				sli_inschooldate = Date.valueOf(inschooldate);
			String sli_education = jsonObj.getString(SchoolLeaderInfoTable.SLI_EDUCATION);
			String sli_degree = jsonObj.getString(SchoolLeaderInfoTable.SLI_DEGREE);
			String sli_professionaltitle = jsonObj.getString(SchoolLeaderInfoTable.SLI_PROFESSIONALTITLE);
			String sli_responsibility = jsonObj.getString(SchoolLeaderInfoTable.SLI_RESPONSIBILITY);
			String sli_studyworkresume = jsonObj.getString(SchoolLeaderInfoTable.SLI_STUDYWORKRESUME);
			int sli_isnull = 0;
			if ("".equals(sequencenumber) || "".equals(sli_name) || "".equals(sli_worknumber)
					|| "".equals(sli_position) || "".equals(sli_gender) || "".equals(birthday)
					|| "".equals(inschooldate) || "".equals(sli_education) || "".equals(sli_degree)
					|| "".equals(sli_professionaltitle) || "".equals(sli_responsibility) || "".equals(sli_studyworkresume))
				sli_isnull = 1;
			if ("".equals(sequencenumber) && "".equals(sli_name) && "".equals(sli_worknumber)
					&& "".equals(sli_position) && "".equals(sli_gender) && "".equals(birthday)
					&& "".equals(inschooldate) && "".equals(sli_education) && "".equals(sli_degree)
					&& "".equals(sli_professionaltitle) && "".equals(sli_responsibility) && "".equals(sli_studyworkresume))
			{
				result = -1;
				out.print(result);
				return;
			}
			int sli_serialnumber = serialnumber;
			String sli_college = college;
			String sli_comments = "";

			SchoolLeaderInfo schoolLeaderInfo = new SchoolLeaderInfo(0, sli_sequencenumber, sli_name, sli_worknumber,
					sli_position, sli_gender, sli_birthday, sli_inschooldate, sli_education, sli_degree,
					sli_professionaltitle, sli_responsibility, sli_studyworkresume, sli_serialnumber, null, sli_college,
					sli_comments, sli_isnull);
			result = sliDao.addSchoolLeaderInfo(schoolLeaderInfo);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}

}
