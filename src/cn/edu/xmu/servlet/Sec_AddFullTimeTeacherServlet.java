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

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.FastJsonTool;

/**
 * 
 * @author xiaoping 数据报表3-1-1 专任教师基本信息表 date 2015-6-29
 *
 */
@WebServlet("/Sec_AddFullTimeTeacher")
public class Sec_AddFullTimeTeacherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddFullTimeTeacherServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
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
		String college = request.getParameter(FullTimeTeacherInfoTable.FTTI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		// 暂存冲突的教师工号
		String workNumberError = "";
		Map queryParams = new HashMap();
		try
		{
			JSONObject jsonObj = new JSONObject(data);

			FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
			OtherTeacherInfoDao otiDao = new OtherTeacherInfoDaoImpl();

			String ftti_worknumber = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_WORKNUMBER);

			// 判断专任教师信息表和其他师资信息表中工号是否唯一
			if (ftti_worknumber != null && !"".equals(ftti_worknumber))
			{
				result = fullTimeTeacherInfoDao.getCountByWorkNumber(ftti_worknumber, null);
				if (result > 0)
				{
					result = -1;
					workNumberError = "工号" + ftti_worknumber + "与专任教师表中冲突";
				}
				result = otiDao.getOtherTeacherInfoCountByWorknumber(ftti_worknumber, null);
				if (result > 0)
				{
					result = -1;
					workNumberError = "工号" + ftti_worknumber + "与其他教师表中冲突";
				}
			}
			// 如果出现冲突的工号，要进行提示
			if (result <= 0 && !"".equals(workNumberError))
			{
				queryParams.clear();
				queryParams.put("result", result);
				queryParams.put("error", workNumberError);
				String json = FastJsonTool.createJsonString(queryParams);
				out.print(json);
				return;
			}
			int ftti_isnull = 0;
			String ftti_name = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_NAME);
			String ftti_gender = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_GENDER);
			String birthday = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_BIRTHDAY);
			Date ftti_birthday = Date.valueOf("1800-01-01");
			if (birthday != null && !birthday.equals(""))
				ftti_birthday = Date.valueOf(birthday);
			String inschooldate = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE);
			Date ftti_inschooldate = Date.valueOf("1800-01-01");
			if (inschooldate != null && !"".equals(inschooldate))
				ftti_inschooldate = Date.valueOf(inschooldate);
			String ftti_workstate = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_WORKSTATE);
			String ftti_departmentnumber = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);
			String ftti_departmentname = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
			String ftti_education = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_EDUCATION);
			String ftti_degree = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
			String ftti_educationsource = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
			String ftti_professionaltitle = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
			String ftti_subjectcategory = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY);
			String ftti_ifdoublequalifiedteacher = jsonObj
					.getString(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
			String ftti_ifengineeringbackground = jsonObj
					.getString(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
			String ftti_ifindustrybackground = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
			String ftti_tutortype = jsonObj.getString(FullTimeTeacherInfoTable.FTTI_TUTORTYPE);
			int ftti_serialnumber = serialnumber;
			String ftti_college = college;
			String ftti_comments = "";
			if ("".equals(ftti_name) || "".equals(ftti_worknumber) || "".equals(ftti_gender) || "".equals(birthday)
					|| "".equals(inschooldate) || "".equals(ftti_workstate) || "".equals(ftti_departmentnumber)
					|| "".equals(ftti_departmentname) || "".equals(ftti_education) || "".equals(ftti_degree)
					|| "".equals(ftti_educationsource) || "".equals(ftti_professionaltitle)
					|| "".equals(ftti_subjectcategory) || "".equals(ftti_ifdoublequalifiedteacher)
					|| "".equals(ftti_ifengineeringbackground) || "".equals(ftti_ifindustrybackground)
					|| "".equals(ftti_tutortype))
				ftti_isnull = 1;
			if ("".equals(ftti_name) && "".equals(ftti_worknumber) && "".equals(ftti_gender) && "".equals(birthday)
					&& "".equals(inschooldate) && "".equals(ftti_workstate) && "".equals(ftti_departmentnumber)
					&& "".equals(ftti_departmentname) && "".equals(ftti_education) && "".equals(ftti_degree)
					&& "".equals(ftti_educationsource) && "".equals(ftti_professionaltitle)
					&& "".equals(ftti_subjectcategory) && "".equals(ftti_ifdoublequalifiedteacher)
					&& "".equals(ftti_ifengineeringbackground) && "".equals(ftti_ifindustrybackground)
					&& "".equals(ftti_tutortype))
			{
				queryParams.clear();
				queryParams.put("result", -1);
				queryParams.put("error", "不能添加空记录");
				String json = FastJsonTool.createJsonString(queryParams);
				out.print(json);
				return;
			}
			FullTimeTeacherInfo fullTimeTeacherInfo = new FullTimeTeacherInfo(0, ftti_name, ftti_worknumber,
					ftti_gender, ftti_birthday, ftti_inschooldate, ftti_workstate, ftti_departmentnumber,
					ftti_departmentname, ftti_education, ftti_degree, ftti_educationsource, ftti_professionaltitle,
					ftti_subjectcategory, ftti_ifdoublequalifiedteacher, ftti_ifengineeringbackground,
					ftti_ifindustrybackground, ftti_tutortype, ftti_serialnumber, null, ftti_college, ftti_comments,
					ftti_isnull);
			result = fullTimeTeacherInfoDao.addFullTimeTeacher(fullTimeTeacherInfo);
		} catch (

		JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}
		queryParams.clear();
		queryParams.put("result", result);
		if (result <= 0)
			queryParams.put("error", "数据添加失败，请查看数据是否符合规范");
		String json = FastJsonTool.createJsonString(queryParams);
		out.print(json);
	}
}
