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
import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.TeachingQualityDaoImpl;
import cn.edu.xmu.entity.TeachingQuality;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.TeachingQualityTable;

/**
 * Servlet implementation class Sec_UpdateTeachingQualityServlet
 */
@WebServlet("/Sec_UpdateTeachingQualityServlet")
public class Sec_UpdateTeachingQualityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateTeachingQualityServlet() {
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
					String tq_id = json.getJSONObject(i).getString(
							TeachingQualityTable.TQ_ID);
					
					String tq_comments = json.getJSONObject(i).getString(
							TeachingQualityTable.TQ_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(TeachingQualityTable.TQ_COMMENTS, tq_comments);
					TeachingQualityDao tqDao = new TeachingQualityDaoImpl();
					int result = tqDao.alterTeachingQuality(params, tq_id);
					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String tq_id = json.getString(TeachingQualityTable.TQ_ID);
				String tq_project = json
						.getString(TeachingQualityTable.TQ_PROJECT);
				String tq_coverpercent = json
						.getString(TeachingQualityTable.TQ_COVERPERCENT);
				if(tq_coverpercent.equals(""))
					tq_coverpercent = "-999";
				
				String tq_excellent = json
						.getString(TeachingQualityTable.TQ_EXCELLENT);
				if(tq_excellent.equals(""))
					tq_excellent = "-999";
				
				String tq_good = json.getString(TeachingQualityTable.TQ_GOOD);
				if(tq_good.equals(""))
					tq_good = "-999";
				
				String tq_medium = json
						.getString(TeachingQualityTable.TQ_MEDIUN);
				if(tq_medium.equals(""))
					tq_medium = "-999";
				
				String tq_poor = json.getString(TeachingQualityTable.TQ_POOR);
				if(tq_poor.equals(""))
					tq_poor = "-999";
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(tq_project.equals("") || tq_coverpercent.equals("") || tq_excellent.equals("") || 
						tq_good.equals("") || tq_medium.equals("") || tq_poor.equals("") )
					isnull = 1;
				
				
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(TeachingQualityTable.TQ_PROJECT, tq_project);
				params.put(TeachingQualityTable.TQ_COVERPERCENT,
						tq_coverpercent);
				params.put(TeachingQualityTable.TQ_EXCELLENT, tq_excellent);
				params.put(TeachingQualityTable.TQ_GOOD, tq_good);
				params.put(TeachingQualityTable.TQ_MEDIUN, tq_medium);
				params.put(TeachingQualityTable.TQ_POOR, tq_poor);
				params.put(TeachingQualityTable.ISNULL, isnull+"");
				TeachingQualityDao tqDao = new TeachingQualityDaoImpl();
				int result = tqDao.alterTeachingQuality(params, tq_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
