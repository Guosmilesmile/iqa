package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.UndergraInResearchLabDao;
import cn.edu.xmu.daoimpl.UndergraInResearchLabDaoImpl;
import cn.edu.xmu.entity.UndergraInResearchLab;
import cn.edu.xmu.table.UndergraInResearchLabTable;

/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
@WebServlet("/Sec_AddUndergraInResearchLab")
public class Sec_AddUndergraInResearchLab extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddUndergraInResearchLab()
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
		String college = request.getParameter(UndergraInResearchLabTable.UIRL_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			UndergraInResearchLabDao uirlDao = new UndergraInResearchLabDaoImpl();
			String uirl_institute = jsonObj.getString(UndergraInResearchLabTable.UIRL_INSTITUTE);
			String uirl_major = jsonObj.getString(UndergraInResearchLabTable.UIRL_MAJOR);
			String uirl_grade = jsonObj.getString(UndergraInResearchLabTable.UIRL_GRADE);
			String uirl_name = jsonObj.getString(UndergraInResearchLabTable.UIRL_NAME);
			String days = jsonObj.getString(UndergraInResearchLabTable.UIRL_DAYS);
			String times = jsonObj.getString(UndergraInResearchLabTable.UIRL_TIMES);
			String totalhours = jsonObj.getString(UndergraInResearchLabTable.UIRL_TOTALHOURS);
			String uirl_laboratoryname = jsonObj.getString(UndergraInResearchLabTable.UIRL_LABORATORYNAME);
			String uirl_laboratorydirector = jsonObj.getString(UndergraInResearchLabTable.UIRL_LABORATORYDIRECTOR);
			String uirl_tutor = jsonObj.getString(UndergraInResearchLabTable.UIRL_TUTOR);
			String uirl_tutortitle = jsonObj.getString(UndergraInResearchLabTable.UIRL_TUTORTITLE);
			String uirl_ispartiresearchproject = jsonObj
					.getString(UndergraInResearchLabTable.UIRL_ISPARTIRESEARCHPROJECT);
			String uirl_researchprojectname = jsonObj.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTNAME);
			String uirl_researchprojectlevel = jsonObj.getString(UndergraInResearchLabTable.UIRL_RESEARCHPROJECTLEVEL);
			String uirl_createprojectname = jsonObj.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTNAME);
			String uirl_createprojecttype = jsonObj.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTTYPE);
			String uirl_createprojectlevel = jsonObj.getString(UndergraInResearchLabTable.UIRL_CREATEPROJECTLEVEL);
			String uirl_paper = jsonObj.getString(UndergraInResearchLabTable.UIRL_PAPER);
			String uirl_patent = jsonObj.getString(UndergraInResearchLabTable.UIRL_PATENT);
			String uirl_note = jsonObj.getString(UndergraInResearchLabTable.UIRL_NOTE);
			int uirl_serialnumber = serialnumber;
			String uirl_college = college;
			String uirl_comments = "";
			int uirl_isnull = 0;
			if ("".equals(uirl_institute) || "".equals(uirl_major) || "".equals(uirl_grade)
					|| "".equals(uirl_name) || "".equals(days) || "".equals(times)
					|| "".equals(totalhours) || "".equals(uirl_laboratoryname) || "".equals(uirl_laboratorydirector)
					|| "".equals(uirl_tutor) || "".equals(uirl_tutortitle) || "".equals(uirl_ispartiresearchproject)
					|| "".equals(uirl_researchprojectname) || "".equals(uirl_researchprojectlevel) || "".equals(uirl_createprojectname)
					|| "".equals(uirl_createprojecttype) || "".equals(uirl_createprojectlevel) || "".equals(uirl_paper)
					|| "".equals(uirl_patent))
				uirl_isnull = 1;
			if ("".equals(uirl_institute) && "".equals(uirl_major) && "".equals(uirl_grade)
					&& "".equals(uirl_name) && "".equals(days) && "".equals(times)
					&& "".equals(totalhours) && "".equals(uirl_laboratoryname) && "".equals(uirl_laboratorydirector)
					&& "".equals(uirl_tutor) && "".equals(uirl_tutortitle) && "".equals(uirl_ispartiresearchproject)
					&& "".equals(uirl_researchprojectname) && "".equals(uirl_researchprojectlevel) && "".equals(uirl_createprojectname)
					&& "".equals(uirl_createprojecttype) && "".equals(uirl_createprojectlevel) && "".equals(uirl_paper)
					&& "".equals(uirl_patent) && "".equals(uirl_note))
			{
				out.print(-1);
				return;
			}
			int uirl_days = -9;
			int uirl_times = -9;
			float uirl_totalhours = -9;
			if(!"".equals(days))
				uirl_days = Integer.parseInt(days);
			if(!"".equals(times))
				uirl_times = Integer.parseInt(times);
			if(!"".equals(totalhours))
				uirl_totalhours = Float.parseFloat(totalhours);
			UndergraInResearchLab undergraInResearchLab = new UndergraInResearchLab(0, uirl_institute, uirl_major,
					uirl_grade, uirl_name, uirl_days, uirl_times, uirl_totalhours, uirl_laboratoryname,
					uirl_laboratorydirector, uirl_tutor, uirl_tutortitle, uirl_ispartiresearchproject,
					uirl_researchprojectname, uirl_researchprojectlevel, uirl_createprojectname, uirl_createprojecttype,
					uirl_createprojectlevel, uirl_paper, uirl_patent, uirl_note, uirl_serialnumber, null, uirl_college,
					uirl_comments, uirl_isnull);

			result = uirlDao.addUndergraInResearchLab(undergraInResearchLab);
		} catch (JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}
		out.print(result);
	}
}
