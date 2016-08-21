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

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.StringAndDate;
/**
 * 
 * @author xiaoping 数据报表3-1-1 专任教师基本信息表 date 2015-6-29
 *
 */
@WebServlet("/Sec_UpdateFullTimeTeacher")
public class Sec_UpdateFullTimeTeacherServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateFullTimeTeacherServlet()
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

		System.out.println("==========");
		String data = request.getParameter("rowdata");
		String patter = request.getParameter("patter");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		// 暂存冲突的教师工号
		String workNumberError = "";
		Map resultMap = new HashMap();
		int result = -1;
		try
		{
			FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{

					String ftti_id = jsons.getJSONObject(i).getString(FullTimeTeacherInfoTable.FTTI_ID);
					String ftti_comments = jsons.getJSONObject(i).getString(FullTimeTeacherInfoTable.FTTI_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(FullTimeTeacherInfoTable.FTTI_ID, ftti_id);
					params.put(FullTimeTeacherInfoTable.FTTI_COMMENTS, ftti_comments);
					result = fullTimeTeacherInfoDao.alterFullTimeTeacher(params, ftti_id);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String ftti_id = json.getString(FullTimeTeacherInfoTable.FTTI_ID);
				String ftti_worknumber = json.getString(FullTimeTeacherInfoTable.FTTI_WORKNUMBER);
				workNumberError = chargeWorkNumber(ftti_id, ftti_worknumber, fullTimeTeacherInfoDao, resultMap);
				if (!"".equals(workNumberError))
				{
					out.print(FastJsonTool.createJsonString(resultMap));
					return;
				}
				String ftti_name = json.getString(FullTimeTeacherInfoTable.FTTI_NAME);

				String ftti_gender = json.getString(FullTimeTeacherInfoTable.FTTI_GENDER);
				String ftti_birthday = json.getString(FullTimeTeacherInfoTable.FTTI_BIRTHDAY);
				String ftti_inschooldate = json.getString(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE);
				String ftti_workstate = json.getString(FullTimeTeacherInfoTable.FTTI_WORKSTATE);
				String ftti_departmentnumber = json.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER);
				String ftti_departmentname = json.getString(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME);
				String ftti_education = json.getString(FullTimeTeacherInfoTable.FTTI_EDUCATION);
				String ftti_degree = json.getString(FullTimeTeacherInfoTable.FTTI_DEGREE);
				String ftti_educationsource = json.getString(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE);
				String ftti_professionaltitle = json.getString(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE);
				String ftti_subjectcategory = json.getString(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY);
				String ftti_ifdoublequalifiedteacher = json
						.getString(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER);
				String ftti_ifengineeringbackground = json
						.getString(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND);
				String ftti_ifindustrybackground = json.getString(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND);
				String ftti_tutortype = json.getString(FullTimeTeacherInfoTable.FTTI_TUTORTYPE);
				String ftti_comments = json.getString(FullTimeTeacherInfoTable.FTTI_COMMENTS);
				int ftti_isnull = 0;
				if ("".equals(ftti_name) || "".equals(ftti_worknumber) || "".equals(ftti_gender) || "".equals(ftti_birthday)
						|| "".equals(ftti_inschooldate) || "".equals(ftti_workstate) || "".equals(ftti_departmentnumber)
						|| "".equals(ftti_departmentname) || "".equals(ftti_education) || "".equals(ftti_degree)
						|| "".equals(ftti_educationsource) || "".equals(ftti_professionaltitle)
						|| "".equals(ftti_subjectcategory) || "".equals(ftti_ifdoublequalifiedteacher)
						|| "".equals(ftti_ifengineeringbackground) || "".equals(ftti_ifindustrybackground)
						|| "".equals(ftti_tutortype))
					ftti_isnull = 1;
				if(ftti_birthday == null || "".equals(ftti_birthday))
					ftti_birthday = "1800-1-1";
				if(ftti_inschooldate == null || "".equals(ftti_inschooldate))
					ftti_inschooldate = "1800-1-1";
				Map<String, String> params = new HashMap<String, String>();
				params.put(FullTimeTeacherInfoTable.FTTI_ID, ftti_id);
				params.put(FullTimeTeacherInfoTable.FTTI_NAME, ftti_name);
				params.put(FullTimeTeacherInfoTable.FTTI_WORKNUMBER, ftti_worknumber);
				params.put(FullTimeTeacherInfoTable.FTTI_GENDER, ftti_gender);
				params.put(FullTimeTeacherInfoTable.FTTI_BIRTHDAY, ftti_birthday);
				params.put(FullTimeTeacherInfoTable.FTTI_INSCHOOLDATE, ftti_inschooldate);
				params.put(FullTimeTeacherInfoTable.FTTI_WORKSTATE, ftti_workstate);
				params.put(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNUMBER, ftti_departmentnumber);
				params.put(FullTimeTeacherInfoTable.FTTI_DEPARTMENTNAME, ftti_departmentname);
				params.put(FullTimeTeacherInfoTable.FTTI_EDUCATION, ftti_education);
				params.put(FullTimeTeacherInfoTable.FTTI_DEGREE, ftti_degree);
				params.put(FullTimeTeacherInfoTable.FTTI_EDUCATIONSOURCE, ftti_educationsource);
				params.put(FullTimeTeacherInfoTable.FTTI_PROFESSIONALTITLE, ftti_professionaltitle);
				params.put(FullTimeTeacherInfoTable.FTTI_SUBJECTCATEGORY, ftti_subjectcategory);
				params.put(FullTimeTeacherInfoTable.FTTI_IFDOUBLEQUALIFIEDTEACHER, ftti_ifdoublequalifiedteacher);
				params.put(FullTimeTeacherInfoTable.FTTI_IFENGINEERINBACKGROUND, ftti_ifengineeringbackground);
				params.put(FullTimeTeacherInfoTable.FTTI_IFINDUSTRYBACKGROUND, ftti_ifindustrybackground);
				params.put(FullTimeTeacherInfoTable.FTTI_TUTORTYPE, ftti_tutortype);
				params.put(FullTimeTeacherInfoTable.FTTI_COMMENTS, ftti_comments);
				params.put(FullTimeTeacherInfoTable.FTTI_ISNULL, ftti_isnull+"");
				result = fullTimeTeacherInfoDao.alterFullTimeTeacher(params, ftti_id);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}
		resultMap.clear();
		resultMap.put("result", result);
		if (result <= 0)
		{
			resultMap.put("error", "数据修改失败，请查看数据是否符合规范");
		}
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
	}

	/**
	 * 
	 * @param workNumber
	 * @return
	 */
	protected String chargeWorkNumber(String id, String workNumber, FullTimeTeacherInfoDao fullTimeTeacherInfoDao, Map resultMap)
	{
		String error = "";
		int result = -1;
		OtherTeacherInfoDao otiDao = new OtherTeacherInfoDaoImpl();
		// 判断专任教师信息表和其他师资信息表中工号是否唯一
		if (workNumber != null && !"".equals(workNumber))
		{
			result = fullTimeTeacherInfoDao.getCountByWorkNumber(workNumber, id);
			if (result > 0)
			{
				result = -1;
				resultMap.put("result", result);
				error = "工号" + workNumber + "与专任教师表中冲突";
				resultMap.put("error", error);
				return error;
			}
			result = otiDao.getOtherTeacherInfoCountByWorknumber(workNumber,null);
			resultMap.clear();
			if (result > 0)
			{
				result = -1;
				resultMap.put("result", result);
				error = "工号" + workNumber + "与其他教师表中冲突";
				resultMap.put("error", error);
			}
		}
		return error;
	}
}
