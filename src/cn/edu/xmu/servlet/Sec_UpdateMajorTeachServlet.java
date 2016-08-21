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

import cn.edu.xmu.dao.MajorTeachDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.MajorTeachDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.MajorTeach;
import cn.edu.xmu.table.MajorTeachTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_UpdateMajorTeachServlet
 */
@WebServlet("/Sec_UpdateMajorTeachServlet")
public class Sec_UpdateMajorTeachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateMajorTeachServlet() {
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
					String mt_id = json.getJSONObject(i).getString(
							MajorTeachTable.MT_ID);
					
					String mt_comments = json.getJSONObject(i).getString(
							MajorTeachTable.MT_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(MajorTeachTable.MT_COMMENTS, mt_comments);

					MajorTeachDao mtDao = new MajorTeachDaoImpl();
					int result = mtDao.alterMajorTeach(params, mt_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				String mt_id = json.getString(MajorTeachTable.MT_ID);
				String mt_majornameinschool = json
						.getString(MajorTeachTable.MT_MAJORNAMEINSCHOOL);
				String mt_majorcodeinschool = json
						.getString(MajorTeachTable.MT_MAJORCODEINSCHOOL);
				String mt_coursecode = json
						.getString(MajorTeachTable.MT_COURSECODE);
				String mt_coursenature = json
						.getString(MajorTeachTable.MT_COURSENATURE);
				String mt_credits = json.getString(MajorTeachTable.MT_CREDITS);
				if(mt_credits.equals(""))
					mt_credits = "-999";
				
				String mt_grade = json.getString(MajorTeachTable.MT_GRADE);
				String mt_comments = json
						.getString(MajorTeachTable.MT_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(mt_majornameinschool.equals("") || mt_majorcodeinschool.equals("") || mt_coursecode.equals("") || 
						mt_coursenature.equals("") || mt_credits.equals("-999") || mt_grade.equals("")  )
					isnull = 1;
				
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(MajorTeachTable.MT_MAJORNAMEINSCHOOL,
						mt_majornameinschool);
				params.put(MajorTeachTable.MT_MAJORCODEINSCHOOL,
						mt_majorcodeinschool);
				params.put(MajorTeachTable.MT_COURSECODE, mt_coursecode);
				params.put(MajorTeachTable.MT_COURSENATURE, mt_coursenature);
				params.put(MajorTeachTable.MT_CREDITS, mt_credits);
				params.put(MajorTeachTable.MT_GRADE, mt_grade);
				params.put(MajorTeachTable.MT_COMMENTS, mt_comments);
				params.put(MajorTeachTable.ISNULL, isnull+"");

				MajorTeachDao mtDao = new MajorTeachDaoImpl();
				int result = mtDao.alterMajorTeach(params, mt_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
