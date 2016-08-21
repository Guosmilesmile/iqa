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

import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.DisciplineConstruction;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddDisciplineConstruction")
public class Sec_AddDisciplineConstruction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddDisciplineConstruction() {
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
				.getParameter(DisciplineConstructionTable.DC_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			int dc_doctorstation = 0;
			int dc_docgrantone = 0;
			int dc_docgranttwo = 0;
			int dc_masgrantone = 0;
			int dc_masgranttwo = 0;
			int dc_undertotal = 0;
			int dc_undernew = 0;
			int dc_junior = 0;
			// int dc_id = json.getInt(DisciplineConstructionTable.DC_ID);
			String doctorstation = json
					.getString(DisciplineConstructionTable.DC_DOCTORSTATION);
			String docgrantone = json
					.getString(DisciplineConstructionTable.DC_DOCGRANTONE);
			String docgranttwo = json
					.getString(DisciplineConstructionTable.DC_DOCGRANTTWO);
			String masgrantone = json
					.getString(DisciplineConstructionTable.DC_MASGRANTONE);
			String masgranttwo = json
					.getString(DisciplineConstructionTable.DC_MASGRANTTWO);
			String undertotal = json
					.getString(DisciplineConstructionTable.DC_UNDERTOTAL);
			String undernew = json
					.getString(DisciplineConstructionTable.DC_UNDERNEW);
			String junior = json
					.getString(DisciplineConstructionTable.DC_JUNIOR);

			if (!"".equals(doctorstation))
				dc_doctorstation = Integer.parseInt(doctorstation);
			if (!"".equals(docgrantone))
				dc_docgrantone = Integer.parseInt(docgrantone);
			if (!"".equals(docgranttwo))
				dc_docgranttwo = Integer.parseInt(docgranttwo);
			if (!"".equals(masgrantone))
				dc_masgrantone = Integer.parseInt(masgrantone);
			if (!"".equals(masgranttwo))
				dc_masgranttwo = Integer.parseInt(masgranttwo);
			if (!"".equals(undertotal))
				dc_undertotal = Integer.parseInt(undertotal);
			if (!"".equals(undernew))
				dc_undernew = Integer.parseInt(undernew);
			if (!"".equals(junior))
				dc_junior = Integer.parseInt(junior);
			String dc_college = college;

			int isnull = 0;
			if ("".equals(doctorstation) || "".equals(docgrantone)
					|| "".equals(docgranttwo) || "".equals(masgrantone)
					|| "".equals(masgranttwo) || "".equals(undertotal) || "".equals(undernew)
					|| "".equals(junior)) {
				isnull = 1;
			}
			DisciplineConstruction dc = new DisciplineConstruction(
					dc_doctorstation, dc_docgrantone, dc_docgranttwo,
					dc_masgrantone, dc_masgranttwo, dc_undertotal, dc_undernew,
					dc_junior, serialnumber, dc_college,isnull);
			DisciplineConstructionDao disciplineConstructionDao = new DisciplineConstructionDaoImpl();
			disciplineConstructionDao.addRecord(dc);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
}
