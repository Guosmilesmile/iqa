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

import cn.edu.xmu.dao.GraduationSituationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.GraduationSituationDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.GraduationSituation;
import cn.edu.xmu.table.GraduationSituationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateGraduationSituationServlet
 */
@WebServlet("/Sec_UpdateGraduationSituationServlet")
public class Sec_UpdateGraduationSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGraduationSituationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		String patter = request.getParameter("patter");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String gs_id = json.getJSONObject(i).getString(
							GraduationSituationTable.GS_ID);
					
					String gs_comments = json.getJSONObject(i).getString(
							GraduationSituationTable.GS_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					
					params.put(GraduationSituationTable.GS_COMMENTS, gs_comments);

					GraduationSituationDao gsDao = new GraduationSituationDaoImpl();
					int result = gsDao.alterGraduationSituation(params, gs_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String gs_id = json.getString(
						GraduationSituationTable.GS_ID);
				String gs_importcollege = json
						.getString(GraduationSituationTable.GS_IMPORTCOLLEGE);
				String gs_major = json
						.getString(GraduationSituationTable.GS_MAJOR);
				String gs_majorcode = json
						.getString(GraduationSituationTable.GS_MAJORCODE);
				String gs_eductionalsystme = json
						.getString(GraduationSituationTable.GS_EDUCTIONALSYSTME);
				if(gs_eductionalsystme.equals(""))
					gs_eductionalsystme = "-999";
				
				String gs_degreecategory = json
						.getString(GraduationSituationTable.GS_DEGREECATEGORY);
				String gs_graduationnumber = json
						.getString(GraduationSituationTable.GS_GRADUATIONNUMBER);
				if(gs_graduationnumber.equals(""))
					gs_graduationnumber = "-999";
				
				String gs_total = json
						.getString(GraduationSituationTable.GS_TOTAL);
				if(gs_total.equals(""))
					gs_total = "-999";
				
				String gs_degreeyes = json
						.getString(GraduationSituationTable.GS_DEGREEYES);
				if(gs_degreeyes.equals(""))
					gs_degreeyes = "-999";
				
				String gs_degreeno = json
						.getString(GraduationSituationTable.GS_DEGREENO);
				if(gs_degreeno.equals(""))
					gs_degreeno = "-999";
				
				String gs_numberofcompletion = json
						.getString(GraduationSituationTable.GS_NUMBEROFCOMPLETION);
				if(gs_numberofcompletion.equals(""))
					gs_numberofcompletion = "-999";
				
				String gs_numberofincomplete = json
						.getString(GraduationSituationTable.GS_NUMBEROFINCOMPLETE);
				if(gs_eductionalsystme.equals(""))
					gs_eductionalsystme = "-999";
				
				String gs_minorcertificatecount = json
						.getString(GraduationSituationTable.GS_MINORCERTIFICATECOUNT);
				if(gs_minorcertificatecount.equals(""))
					gs_minorcertificatecount = "-999";
				
				String gs_minordegreecount = json
						.getString(GraduationSituationTable.GS_MINORDEGREECOUNT);
				if(gs_minordegreecount.equals(""))
					gs_minordegreecount = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(gs_importcollege.equals("") || gs_major.equals("") || gs_majorcode.equals("") || 
						gs_eductionalsystme.equals("-999") || gs_degreecategory.equals("") || gs_graduationnumber.equals("-999") ||
						gs_total.equals("-999") || gs_degreeyes.equals("-999") || gs_degreeno.equals("-999") || gs_numberofcompletion.equals("-999")|| 
						gs_numberofincomplete.equals("-999")|| gs_minorcertificatecount.equals("-999")|| gs_minordegreecount.equals("-999"))
					isnull = 1;
				
				String gs_comments = json.getString(
						GraduationSituationTable.GS_COMMENTS);
				Map<String, String> params = new HashMap<String, String>();
				params.put(GraduationSituationTable.GS_IMPORTCOLLEGE,
						gs_importcollege);
				params.put(GraduationSituationTable.GS_MAJOR,
						gs_major);
				params.put(GraduationSituationTable.GS_MAJORCODE,
						gs_majorcode);
				params.put(GraduationSituationTable.GS_EDUCTIONALSYSTME,
						gs_eductionalsystme);
				params.put(GraduationSituationTable.GS_DEGREECATEGORY,
						gs_degreecategory);
				params.put(GraduationSituationTable.GS_GRADUATIONNUMBER,
						gs_graduationnumber);
				params.put(GraduationSituationTable.GS_TOTAL,
						gs_total);
				params.put(GraduationSituationTable.GS_DEGREEYES,
						gs_degreeyes);
				params.put(GraduationSituationTable.GS_DEGREENO,
						gs_degreeno);
				params.put(GraduationSituationTable.GS_NUMBEROFCOMPLETION,
						gs_numberofcompletion);
				params.put(GraduationSituationTable.GS_NUMBEROFINCOMPLETE,
						gs_numberofincomplete);
				params.put(GraduationSituationTable.GS_MINORCERTIFICATECOUNT,
						gs_minorcertificatecount);
				params.put(GraduationSituationTable.GS_MINORDEGREECOUNT,
						gs_minordegreecount);
				params.put(GraduationSituationTable.ISNULL,
						isnull+"");


				GraduationSituationDao gsDao = new GraduationSituationDaoImpl();
				int result = gsDao.alterGraduationSituation(params, gs_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
