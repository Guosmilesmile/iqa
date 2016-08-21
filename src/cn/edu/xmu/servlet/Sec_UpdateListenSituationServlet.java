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

import cn.edu.xmu.dao.ListenSituationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.ListenSituationDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.table.ListenSituationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateListenSituationServlet
 */
@WebServlet("/Sec_UpdateListenSituationServlet")
public class Sec_UpdateListenSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateListenSituationServlet() {
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
					String ls_id = json.getJSONObject(i).getString(
							ListenSituationTable.LS_ID);
					
					String ls_comments = json.getJSONObject(i).getString(
							ListenSituationTable.LS_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(ListenSituationTable.LS_COMMENTS, ls_comments);

					ListenSituationDao lsDao = new ListenSituationDaoImpl();
					int result = lsDao.alterListenSituation(params, ls_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String ls_id = json.getString(ListenSituationTable.LS_ID);
				String ls_importcollege = json
						.getString(ListenSituationTable.LS_IMPORTCOLLEGE);
				String ls_personnumber = json
						.getString(ListenSituationTable.LS_PERSONNUMBER);
				if(ls_personnumber.equals(""))
					ls_personnumber = "-999";
				
				String ls_lessonnumber = json
						.getString(ListenSituationTable.LS_LESSONNUMBER);
				if(ls_lessonnumber.equals(""))
					ls_lessonnumber = "-999";
				
				String ls_personnumber2 = json
						.getString(ListenSituationTable.LS_PERSONNUMBER2);
				if(ls_personnumber2.equals(""))
					ls_personnumber2 = "-999";
				
				String ls_lessonnumber2 = json
						.getString(ListenSituationTable.LS_LESSONNUMBER2);
				if(ls_lessonnumber2.equals(""))
					ls_lessonnumber2 = "-999";
				
				String ls_excellent = json
						.getString(ListenSituationTable.LS_EXCELLENT);
				if(ls_excellent.equals(""))
					ls_excellent = "-999";
				
				String ls_good = json.getString(ListenSituationTable.LS_GOOD);
				if(ls_good.equals(""))
					ls_good = "-999";
				
				String ls_medium = json
						.getString(ListenSituationTable.LS_MEDIUM);
				if(ls_medium.equals(""))
					ls_medium = "-999";
				
				String ls_bad = json.getString(ListenSituationTable.LS_BAD);
				if(ls_bad.equals(""))
					ls_bad = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ls_importcollege.equals("") || ls_personnumber.equals("-999") || ls_lessonnumber.equals("-999") || 
						ls_personnumber2.equals("-999") || ls_lessonnumber2.equals("-999") || ls_excellent.equals("-999") ||
						ls_good.equals("-999") || ls_medium.equals("-999") || ls_bad.equals("-999") )
					isnull = 1;
				

				Map<String, String> params = new HashMap<String, String>();
				params.put(ListenSituationTable.LS_IMPORTCOLLEGE,
						ls_importcollege);
				params.put(ListenSituationTable.LS_PERSONNUMBER,
						ls_personnumber);
				params.put(ListenSituationTable.LS_LESSONNUMBER,
						ls_lessonnumber);
				params.put(ListenSituationTable.LS_PERSONNUMBER2,
						ls_personnumber2);
				params.put(ListenSituationTable.LS_LESSONNUMBER2,
						ls_lessonnumber2);
				params.put(ListenSituationTable.LS_EXCELLENT, ls_excellent);
				params.put(ListenSituationTable.LS_GOOD, ls_good);
				params.put(ListenSituationTable.LS_MEDIUM, ls_medium);
				params.put(ListenSituationTable.LS_BAD, ls_bad);
				params.put(ListenSituationTable.ISNULL, isnull+"");

				ListenSituationDao lsDao = new ListenSituationDaoImpl();
				int result = lsDao.alterListenSituation(params, ls_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
