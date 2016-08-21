package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ClassCultureDao;
import cn.edu.xmu.daoimpl.ClassCultureDaoImpl;
import cn.edu.xmu.entity.ClassCulture;
import cn.edu.xmu.table.ClassCultureTable;

/**
 * Servlet implementation class AddClassCultureServlet
 */
@WebServlet("/AddClassCultureServlet")
public class AddClassCultureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClassCultureServlet() {
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
		int serialnumber = Integer.parseInt(request
				.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(ClassCultureTable.C_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");

		System.out.println(college);
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		// 添加修改点击保存的时候需要判断,0表示完整，1表示缺失
		
		try {
			JSONObject json = new JSONObject(data);
			String c_classname = json.getString(ClassCultureTable.C_CLASSNAME);
			String c_classnumber = json
					.getString(ClassCultureTable.C_CLASSNUMBER);
			String shunttime = json.getString(ClassCultureTable.C_SHUNTTIME);
			int c_shunttime = 0;
			if (!shunttime.equals("")) {
				c_shunttime = Integer.parseInt(shunttime);
			}
			String c_departmentname = json
					.getString(ClassCultureTable.C_DEPARTMENAME);
			String c_departmentnumber = json
					.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
			String c_majorname = json.getString(ClassCultureTable.C_MAJORNAME);
			String c_majornumber = json
					.getString(ClassCultureTable.C_MAJORNUMBER);
			int isnull = 0;
			if ("".equals(c_classname) || "".equals(c_classnumber)
					|| "".equals(shunttime) ||"".equals(c_departmentname) || "".equals(c_departmentnumber) 
					|| "".equals(c_majorname) || "".equals(c_majornumber)) {
				isnull=1;
			}
			ClassCulture classCulture = new ClassCulture(c_classname,
					c_classnumber, c_shunttime,c_departmentname, c_departmentnumber,
					c_majornumber, c_majorname, serialnumber, null, college,
					null, isnull);
			ClassCultureDao classCultureDao = new ClassCultureDaoImpl();
			classCultureDao.addClassCulture(classCulture);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
