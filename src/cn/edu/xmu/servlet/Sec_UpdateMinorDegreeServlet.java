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

import cn.edu.xmu.dao.MinorDegreeDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.MinorDegreeDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.MinorDegree;
import cn.edu.xmu.table.MinorDegreeTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateMinorDegreeServlet
 */
@WebServlet("/Sec_UpdateMinorDegreeServlet")
public class Sec_UpdateMinorDegreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateMinorDegreeServlet() {
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
					String md_id = json.getJSONObject(i).getString(
							MinorDegreeTable.MD_ID);
					
					String md_comments = json.getJSONObject(i).getString(
							MinorDegreeTable.MD_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(MinorDegreeTable.MD_COMMENTS, md_comments);

					MinorDegreeDao mdDao = new MinorDegreeDaoImpl();
					int result = mdDao.alterMinorDegree(params, md_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String md_id = json.getString(MinorDegreeTable.MD_ID);
				String md_order = json.getString(MinorDegreeTable.MD_ORDER);
				if(md_order.equals(""))
					md_order = "-999";
				
				String md_importcollege = json
						.getString(MinorDegreeTable.MD_IMPORTCOLLEGE);
				String md_major = json.getString(MinorDegreeTable.MD_MAJOR);
				String md_minordegreecount = json
						.getString(MinorDegreeTable.MD_MINORDEGREEECOUNT);
				if(md_minordegreecount.equals(""))
					md_minordegreecount = "-999";
				
				String md_minorcertificatecount = json
						.getString(MinorDegreeTable.MD_MINORCERTIFICATECOUNT);
				if(md_minorcertificatecount.equals(""))
					md_minorcertificatecount = "-999";
				
				String md_comments = json
						.getString(MinorDegreeTable.MD_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(md_order.equals("999") || md_importcollege.equals("") || md_major.equals("") || 
						md_minordegreecount.equals("-999") || md_minorcertificatecount.equals("-999") )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(MinorDegreeTable.MD_ORDER, md_order);
				params.put(MinorDegreeTable.MD_IMPORTCOLLEGE, md_importcollege);
				params.put(MinorDegreeTable.MD_MAJOR, md_major);
				params.put(MinorDegreeTable.MD_MINORDEGREEECOUNT, md_minordegreecount);
				params.put(MinorDegreeTable.MD_MINORCERTIFICATECOUNT, md_minorcertificatecount);
				params.put(MinorDegreeTable.MD_COMMENTS, md_comments);
				params.put(MinorDegreeTable.ISNULL, isnull+"");

				MinorDegreeDao mdDao = new MinorDegreeDaoImpl();
				int result = mdDao.alterMinorDegree(params, md_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
