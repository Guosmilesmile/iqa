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
import cn.edu.xmu.dao.StudentHomeDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StudentHomeDaoImpl;
import cn.edu.xmu.entity.StudentHome;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StudentHomeTable;

/**
 * Servlet implementation class Sec_UpdateStudentHomeServlet
 */
@WebServlet("/Sec_UpdateStudentHomeServlet")
public class Sec_UpdateStudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_UpdateStudentHomeServlet() {
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
					String sh_id = json.getJSONObject(i).getString(
							StudentHomeTable.SH_ID);
					
					String sh_comments = json.getJSONObject(i).getString(
							StudentHomeTable.SH_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					
					params.put(StudentHomeTable.SH_COMMENTS, sh_comments);

					StudentHomeDao shDao = new StudentHomeDaoImpl();
					int result = shDao.alterStudentHome(params, sh_id);

					out.print(result);
				}
			} else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String sh_id = json.getString(StudentHomeTable.SH_ID);
				String sh_diningroomarea = json
						.getString(StudentHomeTable.SH_DININGROOMAREA);
				if(sh_diningroomarea.equals(""))
					sh_diningroomarea = "-999";
				
				String sh_diningrooncount = json
						.getString(StudentHomeTable.SH_DININGROONCOUNT);
				if(sh_diningrooncount.equals(""))
					sh_diningrooncount = "-999";
				
				String sh_dormitoryarea = json
						.getString(StudentHomeTable.SH_DORMITORYAREA);
				if(sh_dormitoryarea.equals(""))
					sh_dormitoryarea = "-999";
				
				String sh_dormitorycount = json
						.getString(StudentHomeTable.SH_DORMITORYCOUNT);
				if(sh_dormitorycount.equals(""))
					sh_dormitorycount = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(sh_diningroomarea.equals("-999") || sh_diningrooncount.equals("-999") || sh_dormitoryarea.equals("-999") || 
						sh_dormitorycount.equals("-999")  )
					isnull = 1;
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(StudentHomeTable.SH_DININGROOMAREA,
						sh_diningroomarea);
				params.put(StudentHomeTable.SH_DININGROONCOUNT,
						sh_diningrooncount);
				params.put(StudentHomeTable.SH_DORMITORYAREA, sh_dormitoryarea);
				params.put(StudentHomeTable.SH_DORMITORYCOUNT,
						sh_dormitorycount);
				params.put(StudentHomeTable.ISNULL,
						isnull+"");

				StudentHomeDao shDao = new StudentHomeDaoImpl();
				int result = shDao.alterStudentHome(params, sh_id);

				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
