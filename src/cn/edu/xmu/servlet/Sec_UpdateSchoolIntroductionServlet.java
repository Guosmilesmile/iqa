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

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SchoolIntroductionDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SchoolIntroductionDaoimpl;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SchoolIntroductionTable;

/**
 * Servlet implementation class Sec_SchoolIntroductionServlet
 */
@WebServlet("/Sec_SchoolIntroductionServlet")
public class Sec_UpdateSchoolIntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateSchoolIntroductionServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	//	String college = request
		//		.getParameter(SchoolIntroductionTable.SI_COLLEGE);
	//	college = URLDecoder.decode(college, "UTF-8");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String si_id = json.getJSONObject(i)
							.getString(SchoolIntroductionTable.SI_ID);
					
					String si_comments = json.getJSONObject(i)
							.getString(SchoolIntroductionTable.SI_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(SchoolIntroductionTable.SI_COMMENTS, si_comments);

					SchoolIntroductionDao siDao = new SchoolIntroductionDaoimpl();
					int result = siDao.alterSchoolIntroduction(params, si_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String si_id = json.getString(SchoolIntroductionTable.SI_ID);
				String si_schoolname = json
						.getString(SchoolIntroductionTable.SI_SCHOOLNAME);
				String si_schoolcode = json
						.getString(SchoolIntroductionTable.SI_SCHOOLCODE);
				String si_Englishname = json
						.getString(SchoolIntroductionTable.SI_ENGLISHNAME);
				String si_campustype = json
						.getString(SchoolIntroductionTable.SI_CAMPUSTYPE);
				String si_campusnature = json
						.getString(SchoolIntroductionTable.SI_CAMPUSNATURE);
				String si_host = json
						.getString(SchoolIntroductionTable.SI_HOST);
				String si_department = json
						.getString(SchoolIntroductionTable.SI_DEPARTMENT);
				String si_website = json
						.getString(SchoolIntroductionTable.SI_WEBSITE);
				String si_admissionsbatches = json
						.getString(SchoolIntroductionTable.SI_ADMISSIONSBATCHES);
				String si_educationstartyear = json
						.getString(SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(si_schoolname.equals("") || si_schoolcode.equals("") || si_Englishname.equals("") || 
						si_campustype.equals("") || si_campusnature.equals("") || si_host.equals("") ||
						si_department.equals("") || si_website.equals("") || si_admissionsbatches.equals("")|| 
						si_educationstartyear.equals("") )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(SchoolIntroductionTable.SI_SCHOOLNAME, si_schoolname);
				params.put(SchoolIntroductionTable.SI_SCHOOLCODE, si_schoolcode);
				params.put(SchoolIntroductionTable.SI_ENGLISHNAME,
						si_Englishname);
				params.put(SchoolIntroductionTable.SI_CAMPUSTYPE, si_campustype);
				params.put(SchoolIntroductionTable.SI_CAMPUSNATURE,
						si_campusnature);
				params.put(SchoolIntroductionTable.SI_HOST, si_host);
				params.put(SchoolIntroductionTable.SI_DEPARTMENT, si_department);
				params.put(SchoolIntroductionTable.SI_WEBSITE, si_website);
				params.put(SchoolIntroductionTable.SI_ADMISSIONSBATCHES,
						si_admissionsbatches);
				params.put(SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR,
						si_educationstartyear);
				params.put(SchoolIntroductionTable.ISNULL,
						isnull+"");
				
				
				SchoolIntroductionDao siDao = new SchoolIntroductionDaoimpl();
				int result = siDao.alterSchoolIntroduction(params, si_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
