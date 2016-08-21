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

import cn.edu.xmu.dao.FixedAssetsDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.FixedAssetsDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.FixedAssets;
import cn.edu.xmu.table.FixedAssetsTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateFixedAssetsServlet
 */
@WebServlet("/Sec_UpdateFixedAssetsServlet")
public class Sec_UpdateFixedAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateFixedAssetsServlet() {
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
					String fa_id = json.getJSONObject(i).getString(
							FixedAssetsTable.FA_ID);
					
					String fa_comments = json.getJSONObject(i).getString(
							FixedAssetsTable.FA_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(FixedAssetsTable.FA_COMMENTS, fa_comments);

					FixedAssetsDao faDao = new FixedAssetsDaoImpl();
					int result = faDao.alterFixedAssets(params, fa_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String fa_id = json.getString(FixedAssetsTable.FA_ID);
				String fa_importcollege = json
						.getString(FixedAssetsTable.FA_IMPORTCOLLEGE);
				String fa_fixedassetssum = json
						.getString(FixedAssetsTable.FA_FIXEDASSETSSUM);
				if(fa_fixedassetssum.equals(""))
					fa_fixedassetssum = "-999";
				
				String fa_equipmentassetssum = json
						.getString(FixedAssetsTable.FA_EQUIPMENTASSETSSUM);
				if(fa_equipmentassetssum.equals(""))
					fa_equipmentassetssum = "-999";
				
				String fa_newassets = json
						.getString(FixedAssetsTable.FA_NEWASSETS);
				if(fa_newassets.equals(""))
					fa_newassets = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(fa_importcollege.equals("") || fa_fixedassetssum.equals("-999") || fa_equipmentassetssum.equals("-999") || 
						fa_newassets.equals("-999")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(FixedAssetsTable.FA_IMPORTCOLLEGE, fa_importcollege);
				params.put(FixedAssetsTable.FA_FIXEDASSETSSUM,
						fa_fixedassetssum);
				params.put(FixedAssetsTable.FA_EQUIPMENTASSETSSUM,
						fa_equipmentassetssum);
				params.put(FixedAssetsTable.FA_NEWASSETS, fa_newassets);
				params.put(FixedAssetsTable.ISNULL, isnull+"");

				FixedAssetsDao faDao = new FixedAssetsDaoImpl();
				int result = faDao.alterFixedAssets(params, fa_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
