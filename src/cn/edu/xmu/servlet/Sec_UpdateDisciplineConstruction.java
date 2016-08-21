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

import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.table.DisciplineConstructionTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateDisciplineConstruction")
public class Sec_UpdateDisciplineConstruction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateDisciplineConstruction() {
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
		String patter = request.getParameter("patter");
		// int serialnumber =
		// Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		
		/*String college = request
				.getParameter(DisciplineConstructionTable.DC_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");*/

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);

		try {

			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);

				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					String dc_id = json
							.getString(DisciplineConstructionTable.DC_ID);
					String dc_doctorstation = json
							.getString(DisciplineConstructionTable.DC_DOCTORSTATION);
					String dc_docgrantone = json
							.getString(DisciplineConstructionTable.DC_DOCGRANTONE);
					String dc_docgranttwo = json
							.getString(DisciplineConstructionTable.DC_DOCGRANTTWO);
					String dc_masgrantone = json
							.getString(DisciplineConstructionTable.DC_MASGRANTONE);
					String dc_masgranttwo = json
							.getString(DisciplineConstructionTable.DC_MASGRANTTWO);
					String dc_undertotal = json
							.getString(DisciplineConstructionTable.DC_UNDERTOTAL);
					String dc_undernew = json
							.getString(DisciplineConstructionTable.DC_UNDERNEW);
					String dc_junior = json
							.getString(DisciplineConstructionTable.DC_JUNIOR);
					String dc_comment = json
							.getString(DisciplineConstructionTable.DC_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(DisciplineConstructionTable.DC_DOCTORSTATION,
							dc_doctorstation + "");
					params.put(DisciplineConstructionTable.DC_DOCGRANTONE,
							dc_docgrantone + "");
					params.put(DisciplineConstructionTable.DC_DOCGRANTTWO,
							dc_docgranttwo + "");
					params.put(DisciplineConstructionTable.DC_MASGRANTONE,
							dc_masgrantone + "");
					params.put(DisciplineConstructionTable.DC_MASGRANTTWO,
							dc_masgranttwo + "");
					params.put(DisciplineConstructionTable.DC_UNDERTOTAL,
							dc_undertotal + "");
					params.put(DisciplineConstructionTable.DC_UNDERNEW,
							dc_undernew + "");
					params.put(DisciplineConstructionTable.DC_JUNIOR, dc_junior
							+ "");
					params.put(DisciplineConstructionTable.DC_COMMENTS, dc_comment
							+ "");
					//params.put(DisciplineConstructionTable.DC_COLLEGE, college);

					DisciplineConstructionDao disciplineConstructionDao = new DisciplineConstructionDaoImpl();
					int result = disciplineConstructionDao
							.alterDisciplineConstruction(params, dc_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String dc_id = json
						.getString(DisciplineConstructionTable.DC_ID);
				String dc_doctorstation = json
						.getString(DisciplineConstructionTable.DC_DOCTORSTATION);
				String dc_docgrantone = json
						.getString(DisciplineConstructionTable.DC_DOCGRANTONE);
				String dc_docgranttwo = json
						.getString(DisciplineConstructionTable.DC_DOCGRANTTWO);
				String dc_masgrantone = json
						.getString(DisciplineConstructionTable.DC_MASGRANTONE);
				String dc_masgranttwo = json
						.getString(DisciplineConstructionTable.DC_MASGRANTTWO);
				String dc_undertotal = json
						.getString(DisciplineConstructionTable.DC_UNDERTOTAL);
				String dc_undernew = json
						.getString(DisciplineConstructionTable.DC_UNDERNEW);
				String dc_junior = json
						.getString(DisciplineConstructionTable.DC_JUNIOR);
				String dc_comment = json
						.getString(DisciplineConstructionTable.DC_COMMENTS);

				int isnull = 0;
				if ("".equals(dc_doctorstation) || "".equals(dc_docgrantone)
						|| "".equals(dc_docgranttwo) || "".equals(dc_masgrantone)
						|| "".equals(dc_masgranttwo) || "".equals(dc_undertotal) || "".equals(dc_undernew)
						|| "".equals(dc_junior)) {
					isnull = 1;
				}
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(DisciplineConstructionTable.DC_DOCTORSTATION,
						dc_doctorstation + "");
				params.put(DisciplineConstructionTable.DC_DOCGRANTONE,
						dc_docgrantone + "");
				params.put(DisciplineConstructionTable.DC_DOCGRANTTWO,
						dc_docgranttwo + "");
				params.put(DisciplineConstructionTable.DC_MASGRANTONE,
						dc_masgrantone + "");
				params.put(DisciplineConstructionTable.DC_MASGRANTTWO,
						dc_masgranttwo + "");
				params.put(DisciplineConstructionTable.DC_UNDERTOTAL,
						dc_undertotal + "");
				params.put(DisciplineConstructionTable.DC_UNDERNEW, dc_undernew
						+ "");
				params.put(DisciplineConstructionTable.DC_JUNIOR, dc_junior
						+ "");
				params.put(DisciplineConstructionTable.DC_COMMENTS, dc_comment
						+ "");
				params.put("isnull",isnull+"");
				//params.put(DisciplineConstructionTable.DC_COLLEGE, college);

				DisciplineConstructionDao disciplineConstructionDao = new DisciplineConstructionDaoImpl();
				int result = disciplineConstructionDao
						.alterDisciplineConstruction(params, dc_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
