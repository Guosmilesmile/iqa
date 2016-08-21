package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.PublishProductDao;
import cn.edu.xmu.daoimpl.PublishProductDaoImpl;
import cn.edu.xmu.entity.PublishProduct;
import cn.edu.xmu.table.PublishProductTable;


/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddPublishProduct")
public class Sec_AddPublishProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddPublishProduct() {
		super();
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
		int serialnumber = Integer.parseInt(request
				.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(PublishProductTable.PP_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			
			String ap_colleges = json.getString(PublishProductTable.PP_COLLEGES);
			String ap_papertitle =json.getString(PublishProductTable.PP_PRODUCTNAME);
			String ap_grade = json.getString(PublishProductTable.PP_GRADE);
			String ap_name =json.getString(PublishProductTable.PP_NAME);
			String ap_major = json.getString(PublishProductTable.PP_MAJOR);
			String ap_publication = json.getString(PublishProductTable.PP_PUBLICATION);
			String time = json.getString(PublishProductTable.PP_TIME);
			String ap_remark= json.getString(PublishProductTable.PP_REMARK);
			String ap_comments ="";
			
			
			Date ap_time=Date.valueOf("1800-1-1");
			if(!"".equals(time))
			 ap_time = Date.valueOf(time);
			int isnull = 0;
			if ("".equals(ap_colleges) || "".equals(ap_papertitle)
					|| "".equals(ap_grade) || "".equals(ap_name)
					|| "".equals(ap_major) || "".equals(ap_publication) || "".equals(time)) {
				isnull = 1;
			}
			if ("".equals(ap_colleges) && "".equals(ap_papertitle)
					&& "".equals(ap_grade) && "".equals(ap_name)
					&& "".equals(ap_major) && "".equals(ap_publication) && "".equals(time)) {
				out.println(false);
				return;
			}
			PublishProduct ap = new PublishProduct(ap_colleges, ap_papertitle,
					ap_grade, ap_name, ap_major, ap_publication, ap_time,
					ap_remark, serialnumber, college,
					ap_comments,isnull);
			PublishProductDao apDao = new PublishProductDaoImpl();
			apDao.addRecord(ap);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
