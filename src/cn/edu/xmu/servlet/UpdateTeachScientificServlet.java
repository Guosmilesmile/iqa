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

import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.dao.TeachScientificDao;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.daoimpl.TeachScientificDaoImpl;
import cn.edu.xmu.table.TeachResearchUnitTable;
import cn.edu.xmu.table.TeachScientificTable;

/**
 * 
 * @author luo 更新学校教学科研单位 date 2015-06-29
 */
@WebServlet("/UpdateTeachScientificServlet")
public class UpdateTeachScientificServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeachScientificServlet() {
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
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {

					String ts_id = json.getJSONObject(i).getString(TeachScientificTable.TS_ID);
					
					String ts_comments = json.getJSONObject(i)
							.getString(TeachScientificTable.TS_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(TeachScientificTable.TS_COMMENTS, ts_comments);
					TeachScientificDao teachScientificDao = new TeachScientificDaoImpl();
					int result = teachScientificDao.alterTeachScientific(
							params, ts_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String ts_id = json.getString(TeachScientificTable.TS_ID);
				String ts_name = json.getString(TeachScientificTable.TS_NAME);
				String ts_number = json
						.getString(TeachScientificTable.TS_NUMBER);
				String ts_head = json.getString(TeachScientificTable.TS_HEAD);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ts_name.equals("") || ts_number.equals("") || ts_head.equals("")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(TeachScientificTable.TS_NAME, ts_name);
				params.put(TeachScientificTable.TS_NUMBER, ts_number);
				params.put(TeachScientificTable.TS_HEAD, ts_head);
				params.put(TeachScientificTable.ISNULL, isnull+"");
				TeachScientificDao teachScientificDao = new TeachScientificDaoImpl();
				int result = teachScientificDao.alterTeachScientific(params,
						ts_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
