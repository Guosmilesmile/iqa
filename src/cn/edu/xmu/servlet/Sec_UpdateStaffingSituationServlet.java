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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StaffingSituationDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StaffingSituationDaoImpl;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StaffingSituationTable;

/**
 * Servlet implementation class Sec_UpdateStaffingSituationServlet
 */
@WebServlet("/Sec_UpdateStaffingSituationServlet")
public class Sec_UpdateStaffingSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateStaffingSituationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String ss_id = json.getJSONObject(i).getString(
							StaffingSituationTable.SS_ID);
					
					String ss_comments = json.getJSONObject(i).getString(
							StaffingSituationTable.SS_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(StaffingSituationTable.SS_COMMENTS, ss_comments);

					StaffingSituationDao ssDao = new StaffingSituationDaoImpl();
					int result = ssDao.alterStaffingSituation(params, ss_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String ss_id = json.getString(StaffingSituationTable.SS_ID);
				String ss_condition1 = json.getString(
						StaffingSituationTable.SS_CONDITION1);
				String ss_condition2 = json.getString(
						StaffingSituationTable.SS_CONDITION2);
				String ss_teachercount = json
						.getString(StaffingSituationTable.SS_TEACHERCOUNT);
				if(ss_teachercount.equals(""))
					ss_teachercount = "-999";
				
				String ss_fulltimestaffcount = json
						.getString(StaffingSituationTable.SS_FULLTIMESTAFFCOUNT);
				if(ss_fulltimestaffcount.equals(""))
					ss_fulltimestaffcount = "-999";
				
				String ss_facultycount = json
						.getString(StaffingSituationTable.SS_FACULTYCOUNT);
				if(ss_facultycount.equals(""))
					ss_facultycount = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ss_condition1.equals("") || ss_condition2.equals("") || ss_teachercount.equals("-999") || 
						ss_fulltimestaffcount.equals("-999") || ss_facultycount.equals("-999") )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(StaffingSituationTable.SS_CONDITION1,
						ss_condition1);
				params.put(StaffingSituationTable.SS_CONDITION2,
						ss_condition2);
				params.put(StaffingSituationTable.SS_TEACHERCOUNT,
						ss_teachercount);
				params.put(StaffingSituationTable.SS_FULLTIMESTAFFCOUNT,
						ss_fulltimestaffcount);
				params.put(StaffingSituationTable.SS_FACULTYCOUNT,
						ss_facultycount);
				params.put(StaffingSituationTable.ISNULL,
						isnull+"");
				

				StaffingSituationDao ssDao = new StaffingSituationDaoImpl();
				int result = ssDao.alterStaffingSituation(params, ss_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
