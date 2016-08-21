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
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateSchoolNetServlet
 */
@WebServlet("/Sec_UpdateSchoolNetServlet")
public class Sec_UpdateSchoolNetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateSchoolNetServlet() {
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
					String sn_id = json.getJSONObject(i).getString(
							SchoolNetTable.SN_ID);
					
					String sn_comments = json.getJSONObject(i).getString(
							SchoolNetTable.SN_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
				
					params.put(SchoolNetTable.SN_COMMENTS, sn_comments);

					SchoolNetDao snDao = new SchoolNetDaoimpl();
					int result = snDao.alterSchoolNet(params, sn_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String sn_id = json.getString(SchoolNetTable.SN_ID);
				String sn_backbonebandwidth = json
						.getString(SchoolNetTable.SN_BACKBONEBANDWIDTH);
				if(sn_backbonebandwidth.equals(""))
					sn_backbonebandwidth = "-999";
				
				String sn_exportbandwidth = json
						.getString(SchoolNetTable.SN_EXPORTBANDWIDTH);
				if(sn_exportbandwidth.equals(""))
					sn_exportbandwidth = "-999";
				
				String sn_informationcount = json
						.getString(SchoolNetTable.SN_INFORMATIONCOUNT);
				if(sn_informationcount.equals(""))
					sn_informationcount = "-999";
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(sn_backbonebandwidth.equals("") || sn_exportbandwidth.equals("") || sn_informationcount.equals("")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(SchoolNetTable.SN_BACKBONEBANDWIDTH,
						sn_backbonebandwidth);
				params.put(SchoolNetTable.SN_EXPORTBANDWIDTH,
						sn_exportbandwidth);
				params.put(SchoolNetTable.SN_INFORMATIONCOUNT,
						sn_informationcount);
				params.put(SchoolNetTable.ISNULL,
						isnull+"");
				
				SchoolNetDao snDao = new SchoolNetDaoimpl();
				int result = snDao.alterSchoolNet(params, sn_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
