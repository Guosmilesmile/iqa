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

import cn.edu.xmu.dao.AdvancedIndividualDao;
import cn.edu.xmu.daoimpl.AdvancedIndividualDaoImpl;
import cn.edu.xmu.table.AdvancedIndividualTable;

/**
 * Servlet implementation class Sec_UpdateAdvancedIndividualServlet
 */
@WebServlet("/Sec_UpdateAdvancedIndividualServlet")
public class Sec_UpdateAdvancedIndividualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateAdvancedIndividualServlet() {
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
					String ai_id = json.getJSONObject(i).getString(
							AdvancedIndividualTable.AI_ID);
					
					String ai_comments = json.getJSONObject(i).getString(
							AdvancedIndividualTable.AI_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(AdvancedIndividualTable.AI_COMMENTS, ai_comments);
					AdvancedIndividualDao aiDao = new AdvancedIndividualDaoImpl();
					int result = aiDao.alterAdvancedIndividual(params, ai_id);
					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String ai_id = json.getString(AdvancedIndividualTable.AI_ID);
				String ai_order = json
						.getString(AdvancedIndividualTable.AI_ORDER);
				if(ai_order.equals(""))
					ai_order = "-999";
				
				String ai_importcollege = json
						.getString(AdvancedIndividualTable.AI_IMPORTCOLLEGE);
				String ai_name = json
						.getString(AdvancedIndividualTable.AI_NAME);
				String ai_honoryear = json
						.getString(AdvancedIndividualTable.AI_HONORYEAR);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ai_order.equals("-999") || ai_importcollege.equals("") || ai_name.equals("") || 
						ai_honoryear.equals("")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(AdvancedIndividualTable.AI_ORDER, ai_order);
				params.put(AdvancedIndividualTable.AI_IMPORTCOLLEGE,
						ai_importcollege);
				params.put(AdvancedIndividualTable.AI_NAME, ai_name);
				params.put(AdvancedIndividualTable.AI_HONORYEAR, ai_honoryear);
				params.put(AdvancedIndividualTable.ISNULL, isnull+"");
				AdvancedIndividualDao aiDao = new AdvancedIndividualDaoImpl();
				int result = aiDao.alterAdvancedIndividual(params, ai_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
