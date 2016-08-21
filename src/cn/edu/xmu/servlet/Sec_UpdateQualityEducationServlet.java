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

import cn.edu.xmu.dao.QualityEducationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.QualityEducationDaoimpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.QualityEducation;
import cn.edu.xmu.table.QualityEducationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateQualityEducationServlet
 */
@WebServlet("/Sec_UpdateQualityEducationServlet")
public class Sec_UpdateQualityEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateQualityEducationServlet() {
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
					String qe_id = json.getJSONObject(i).getString(
							QualityEducationTable.QE_ID);
					
					String qe_comments = json.getJSONObject(i).getString(
							QualityEducationTable.QE_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(QualityEducationTable.QE_COMMENTS, qe_comments);

					QualityEducationDao qeDao = new QualityEducationDaoimpl();
					int result = qeDao.alterQualityEducation(params, qe_id);
					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String qe_id = json.getString(
						QualityEducationTable.QE_ID);
				String qe_diathesisdeveloping = json
						.getString(QualityEducationTable.QE_DIATHESISDEVELOPING);
				if(qe_diathesisdeveloping.equals(""))
					qe_diathesisdeveloping = "-999";
				
				String qe_qualificationtraining = json
						.getString(QualityEducationTable.QE_QUALIFICATIONTRAINING);
				if(qe_qualificationtraining.equals(""))
					qe_qualificationtraining = "-999";
				
				String qe_course = json
						.getString(QualityEducationTable.QE_COURSE);
				if(qe_course.equals(""))
					qe_course = "-999";
				
				String qe_basecount = json
						.getString(QualityEducationTable.QE_BASECOUNT);
				if(qe_basecount.equals(""))
					qe_basecount = "-999";
				
				int isnull = 0;
				if(qe_diathesisdeveloping.equals("-999") || qe_qualificationtraining.equals("-999") || qe_course.equals("-999") || 
						qe_basecount.equals("-999")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(QualityEducationTable.QE_DIATHESISDEVELOPING,
						qe_diathesisdeveloping);
				params.put(QualityEducationTable.QE_QUALIFICATIONTRAINING,
						qe_qualificationtraining);
				params.put(QualityEducationTable.QE_COURSE,
						qe_course);
				params.put(QualityEducationTable.QE_BASECOUNT,
						qe_basecount);
				params.put(QualityEducationTable.ISNULL,
						isnull+"");

				QualityEducationDao qeDao = new QualityEducationDaoimpl();
				int result = qeDao.alterQualityEducation(params, qe_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
