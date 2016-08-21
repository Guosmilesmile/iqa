package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ImportantStudyDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.ImportantStudyDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.ImportantStudy;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddImportantStudy")
public class Sec_AddImportantStudy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddImportantStudy() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber = Integer.parseInt(request
				.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(ImportantStudyTable.IP_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		System.out.println(college);
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			int ip_studynumber = 0;
			JSONObject json = new JSONObject(data);
			String studynumber = json
					.getString(ImportantStudyTable.IP_STUDYNUMBER);
			if (!"".equals(studynumber))
				ip_studynumber = Integer.parseInt(studynumber);
			String ip_studyname = json
					.getString(ImportantStudyTable.IP_STUDYNAME);
			String ip_departmentnumber = json
					.getString(ImportantStudyTable.IP_DEPARTMENTNUMBER);
			String ip_departmentname = json
					.getString(ImportantStudyTable.IP_DEPARTMENTNAME);
			String ip_category = json
					.getString(ImportantStudyTable.IP_CATEGORY);
			String ip_level = json.getString(ImportantStudyTable.IP_LEVEL);
			int isnull = 0;
			if ("".equals(studynumber) || "".equals(ip_studyname)
					|| "".equals(ip_departmentnumber)
					|| "".equals(ip_departmentname) || "".equals(ip_category)
					|| "".equals(ip_level)) {
				isnull = 1;
			}

			ImportantStudy importantStudy = new ImportantStudy(ip_studynumber,
					ip_studyname, ip_departmentnumber, ip_departmentname,
					ip_category, ip_level, serialnumber, college,isnull);
			ImportantStudyDao importantStudyDao = new ImportantStudyDaoImpl();
			importantStudyDao.addImportantStudy(importantStudy);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
