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

import cn.edu.xmu.dao.TrainingInstitutionsDao;
import cn.edu.xmu.daoimpl.TrainingInstitutionsDaoImpl;
import cn.edu.xmu.entity.TrainingInstitutions;
import cn.edu.xmu.table.TrainingInstitutionsTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddTrainingInstitutions")
public class Sec_AddTrainingInstitutions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddTrainingInstitutions() {
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
		String college = request
				.getParameter(TrainingInstitutionsTable.TI_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);
			Date ti_time = null;
			int ti_peoplecount = 0;
			int ti_serialnumber = serialnumber;
			String ti_college = college;
			String ti_comments = "";
			String ti_name = json.getString(TrainingInstitutionsTable.TI_NAME);
			String ti_departmentnumber = json
					.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNUMBER);
			String ti_departmentname = json
					.getString(TrainingInstitutionsTable.TI_DEPAERTMENTNAME);
			String ti_projectname = json
					.getString(TrainingInstitutionsTable.TI_PROJECTNAME);
			String ti_object = json
					.getString(TrainingInstitutionsTable.TI_OBJECT);
			String date = json.getString(TrainingInstitutionsTable.TI_TIME);
			if (!"".equals(date))
				ti_time = Date.valueOf(json
						.getString(TrainingInstitutionsTable.TI_TIME));
			String peoplecount = json
					.getString(TrainingInstitutionsTable.TI_PEOPLECOUNT);
			if (!"".equals(peoplecount))
				ti_peoplecount = Integer.parseInt(peoplecount);
			int isnull = 0;
			if ("".equals(ti_name) || "".equals(ti_departmentnumber)
					|| "".equals(ti_departmentname)
					|| "".equals(ti_projectname) || "".equals(date)
					|| "".equals(ti_object) || "".equals(ti_projectname))
				isnull = 1;
			TrainingInstitutions ti = new TrainingInstitutions(ti_name,
					ti_departmentnumber, ti_departmentname, ti_projectname,
					ti_object, ti_time, ti_peoplecount, ti_serialnumber,
					ti_college, ti_comments,isnull);

			
			TrainingInstitutionsDao tiDao = new TrainingInstitutionsDaoImpl();
			tiDao.addRecord(ti);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
}
