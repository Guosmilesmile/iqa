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

import cn.edu.xmu.dao.CategoryExperimentDao;
import cn.edu.xmu.daoimpl.CategoryExperimentDaoImpl;
import cn.edu.xmu.entity.CategoryExperiment;
import cn.edu.xmu.table.CategoryExperimentTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddCategoryExperiment")
public class Sec_AddCategoryExperiment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddCategoryExperiment() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(CategoryExperimentTable.CE_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			int ce_serialnumber = serialnumber;
			String ce_college = college;

			CategoryExperimentDao ceDao = new CategoryExperimentDaoImpl();
			String ce_colleges = json.getString(CategoryExperimentTable.CE_COLLEGES);
			String ce_majorname = json.getString(CategoryExperimentTable.CE_MAJORNAME);
			String containexperiment = json.getString(CategoryExperimentTable.CE_CONTAINEXPERIMENT);
			String singleexperiment = json.getString(CategoryExperimentTable.CE_SINLGEEXPERIMENT);
			String nosingleexperiment = json
					.getString(CategoryExperimentTable.CE_NOSINGELEEXPERIMENT);
			String designexperiment = json.getString(CategoryExperimentTable.CE_DESIGNEXPERIMENT);
			String percentage = json.getString(CategoryExperimentTable.CE_PERCENTAGE);
			String ce_majornumebr = json.getString(CategoryExperimentTable.CE_MAJORNUMBER);
			int isnull = 0;
			if ("".equals(ce_colleges) || "".equals(ce_majorname) || "".equals(containexperiment)
					|| "".equals(singleexperiment) || "".equals(nosingleexperiment)
					|| "".equals(designexperiment) || "".equals(percentage)
					|| "".equals(ce_majornumebr)) {
				isnull = 1;
			}
			if ("".equals(ce_colleges) && "".equals(ce_majorname) && "".equals(containexperiment)
					&& "".equals(singleexperiment) && "".equals(nosingleexperiment)
					&& "".equals(designexperiment) && "".equals(percentage)
					&& "".equals(ce_majornumebr)) {
				out.println(false);
				return;
			}
			int ce_containexperiment = -999;
			if (!"".equals(containexperiment)) {
				ce_containexperiment = Integer.parseInt(containexperiment);
			}
			float ce_percentage = -999;
			if (!"".equals(percentage)) {
				ce_percentage = Float.parseFloat(percentage);
			}
			int ce_singleexperiment = -999;
			if (!"".equals(singleexperiment)) {
				ce_singleexperiment = Integer.parseInt(singleexperiment);
			}
			int ce_nosingleexperiment = -999;
			if (!"".equals(nosingleexperiment)) {
				ce_nosingleexperiment = Integer.parseInt(nosingleexperiment);
			}
			int ce_designexperiment = -999;
			if (!"".equals(designexperiment)) {
				ce_designexperiment = Integer.parseInt(designexperiment);
			}
			CategoryExperiment ce = new CategoryExperiment(ce_colleges, ce_majorname,
					ce_containexperiment, ce_singleexperiment, ce_nosingleexperiment,
					ce_designexperiment, ce_percentage, ce_majornumebr, ce_serialnumber,
					ce_college, isnull);
			ceDao.addRecord(ce);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
