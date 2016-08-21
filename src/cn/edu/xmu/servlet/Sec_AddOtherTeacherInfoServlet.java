package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.enums.DaoShiLeiBie;
import cn.edu.xmu.enums.ShiZiLeiBie;
import cn.edu.xmu.enums.WorkingState;
import cn.edu.xmu.enums.XingBie;
import cn.edu.xmu.enums.XueKeLeiBie;
import cn.edu.xmu.enums.XueLi;
import cn.edu.xmu.enums.ZhuanYeJiShuZhiCheng;
import cn.edu.xmu.enums.ZuiGaoXueWei;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.StringAndDate;

/*
 * 3-1-3 其他师资情况
 */
@WebServlet("/Sec_AddOtherTeacherInfoServlet")
public class Sec_AddOtherTeacherInfoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddOtherTeacherInfoServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));

		String college = request.getParameter(OtherTeacherInfoTable.OTI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;

		String worknumberError = "";
		Map params = new HashMap();
		try
		{
			JSONObject json = new JSONObject(data);

			OtherTeacherInfoDao otid = new OtherTeacherInfoDaoImpl();
			FullTimeTeacherInfoDao fttid = new FullTimeTeacherInfoDaoImpl();

			String oti_worknumber = json.getString(OtherTeacherInfoTable.OTI_WORKNUMBER);

			// 验证是否有重复
			if (oti_worknumber != null && !"".equals(oti_worknumber))
			{
				result = otid.getOtherTeacherInfoCountByWorknumber(oti_worknumber, null);
				if (result > 0)
				{
					result = -1;
					worknumberError = "工号" + oti_worknumber + "与其他教师表冲突";
				}

				result = fttid.getCountByWorkNumber(oti_worknumber, null);
				if (result > 0)
				{
					result = -1;
					worknumberError = "工号" + oti_worknumber + "与专任教师表冲突";
				}
			}

			if (result <= 0 && !"".equals(worknumberError))
			{
				params.clear();
				params.put("result", result);
				params.put("error", worknumberError);
				String jsonObj = FastJsonTool.createJsonString(params);
				out.print(jsonObj);
				return;
			}

			String oti_name = json.getString(OtherTeacherInfoTable.OTI_NAME);
			String oti_sex = json.getString(OtherTeacherInfoTable.OTI_SEX);
			String birthday = json.getString(OtherTeacherInfoTable.OTI_BIRTHDAY);
			Date oti_birthday = Date.valueOf("1800-01-01");
			if (birthday != null && !birthday.equals(""))
				oti_birthday = Date.valueOf(birthday);
			String inschooldate = json.getString(OtherTeacherInfoTable.OTI_INSCHOOLDATE);
			Date oti_inschooldate = Date.valueOf("1800-01-01");
			if (inschooldate != null && !"".equals(inschooldate))
				oti_inschooldate = Date.valueOf(inschooldate);
			String oti_workstate = json.getString(OtherTeacherInfoTable.OTI_WORKSTATE);
			String oti_teachertype = json.getString(OtherTeacherInfoTable.OTI_TEACHERTYPE);
			String oti_departmentnumber = json.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER);
			String oti_departmentname = json.getString(OtherTeacherInfoTable.OTI_DEPARTMENTNAME);
			String oti_education = json.getString(OtherTeacherInfoTable.OTI_EDUCATION);
			String oti_degree = json.getString(OtherTeacherInfoTable.OTI_DEGREE);
			String oti_professionaltitle = json.getString(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
			String oti_subjectcategory = json.getString(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
			String oti_tutorcategory = json.getString(OtherTeacherInfoTable.OTI_TUTORCATEGORY);
			String oti_comments = "";
			String oti_college = college;
			int oti_serialnumber = serialnumber;
			int oti_isnull = 0;
			if ("".equals(oti_name) || "".equals(oti_worknumber) || "".equals(oti_sex) || "".equals(birthday)
					|| "".equals(inschooldate) || "".equals(oti_workstate) || "".equals(oti_departmentnumber)
					|| "".equals(oti_departmentname) || "".equals(oti_education) || "".equals(oti_degree)
					|| "".equals(oti_professionaltitle) || "".equals(oti_subjectcategory) || "".equals(oti_workstate)
					|| "".equals(oti_tutorcategory))

				oti_isnull = 1;

			if ("".equals(oti_name) && "".equals(oti_worknumber) && "".equals(oti_sex) && "".equals(birthday)
					&& "".equals(inschooldate) && "".equals(oti_workstate) && "".equals(oti_departmentnumber)
					&& "".equals(oti_departmentname) && "".equals(oti_education) && "".equals(oti_degree)
					&& "".equals(oti_professionaltitle) && "".equals(oti_subjectcategory) && "".equals(oti_workstate)
					&& "".equals(oti_tutorcategory))
			{
				params.clear();
				params.put("result", -1);
				params.put("error", "不能添加空记录");
				out.print(FastJsonTool.createJsonString(params));
				return;
			}

			OtherTeacherInfo oti = new OtherTeacherInfo(oti_name, oti_worknumber, oti_sex, oti_birthday,
					oti_inschooldate, oti_workstate, oti_teachertype, oti_departmentnumber, oti_departmentname,
					oti_education, oti_degree, oti_professionaltitle, oti_subjectcategory, oti_tutorcategory,
					oti_college, oti_serialnumber, oti_comments, oti_isnull);

			result = otid.addRecord(oti);

		} catch (JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}

		params.clear();
		params.put("result", result);
		if (result <= 0)
			params.put("error", "数据添加失败，请查看数据是否符合规范");
		String json = FastJsonTool.createJsonString(params);
		out.print(json);

	}

}
