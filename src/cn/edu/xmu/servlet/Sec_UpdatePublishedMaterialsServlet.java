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

import cn.edu.xmu.dao.PublishedMaterialsDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.PublishedMaterialsDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.PublishedMaterials;
import cn.edu.xmu.table.PublishedMaterialsTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdatePublishedMaterialsServlet
 */
@WebServlet("/Sec_UpdatePublishedMaterialsServlet")
public class Sec_UpdatePublishedMaterialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdatePublishedMaterialsServlet() {
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
					String pm_id = json.getJSONObject(i).getString(
							PublishedMaterialsTable.PM_ID);
				
					String pm_comments = json.getJSONObject(i).getString(
							PublishedMaterialsTable.PM_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(PublishedMaterialsTable.PM_COMMENTS, pm_comments);

					PublishedMaterialsDao pmDao = new PublishedMaterialsDaoImpl();
					int result = pmDao.alterPublishedMaterials(params, pm_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String pm_id = json.getString(PublishedMaterialsTable.PM_ID);
				String pm_importcollege = json
						.getString(PublishedMaterialsTable.PM_IMPORTCOLLEGE);
				String pm_materialsname = json
						.getString(PublishedMaterialsTable.PM_MATERIALSNAME);
				String pm_author = json
						.getString(PublishedMaterialsTable.PM_AUTHOR);
				String pm_type = json
						.getString(PublishedMaterialsTable.PM_TYPE);
				String pm_publisher = json
						.getString(PublishedMaterialsTable.PM_PUBLISHER);
				String pm_publishyear = json
						.getString(PublishedMaterialsTable.PM_PUBLISHYEAR);
				String pm_engineeringmaterials = json
						.getString(PublishedMaterialsTable.PM_ENGINEERINGMATERIALS);
				String pm_forteaching = json
						.getString(PublishedMaterialsTable.PM_FORTEACHING);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(pm_importcollege.equals("") || pm_materialsname.equals("") || pm_author.equals("") || 
						pm_type.equals("") || pm_publisher.equals("") || pm_publishyear.equals("") ||
						pm_engineeringmaterials.equals("") || pm_forteaching.equals("")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(PublishedMaterialsTable.PM_IMPORTCOLLEGE,
						pm_importcollege);
				params.put(PublishedMaterialsTable.PM_MATERIALSNAME,
						pm_materialsname);
				params.put(PublishedMaterialsTable.PM_AUTHOR, pm_author);
				params.put(PublishedMaterialsTable.PM_TYPE, pm_type);
				params.put(PublishedMaterialsTable.PM_PUBLISHER, pm_publisher);
				params.put(PublishedMaterialsTable.PM_PUBLISHYEAR,
						pm_publishyear);
				params.put(PublishedMaterialsTable.PM_ENGINEERINGMATERIALS,
						pm_engineeringmaterials);
				params.put(PublishedMaterialsTable.PM_FORTEACHING,
						pm_forteaching);
				params.put(PublishedMaterialsTable.ISNULL,
						isnull+"");

				
				PublishedMaterialsDao pmDao = new PublishedMaterialsDaoImpl();
				int result = pmDao.alterPublishedMaterials(params, pm_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
