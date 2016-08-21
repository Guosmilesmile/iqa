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

import cn.edu.xmu.dao.TeacherPaperDao;
import cn.edu.xmu.daoimpl.TeacherPaperDaoImpl;
import cn.edu.xmu.entity.TeacherPaper;
import cn.edu.xmu.table.TeacherPaperTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddTeacherPaper")
public class Sec_AddTeacherPaper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddTeacherPaper() {
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
		String college = request.getParameter(TeacherPaperTable.TP_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			String tp_colleges = json.getString(TeacherPaperTable.TP_COLLEGES);
			String tp_papertitle = json
					.getString(TeacherPaperTable.TP_PAPERTITLE);
			String tp_paperclasses = json
					.getString(TeacherPaperTable.TP_PAPERCLASSES);
			String tp_authors = json.getString(TeacherPaperTable.TP_AUTHORS);
			String tp_serial = json.getString(TeacherPaperTable.TP_SERIAL);
			String tp_authorclasses = json
					.getString(TeacherPaperTable.TP_AUTHORCLASSES);
			String tp_publication = json
					.getString(TeacherPaperTable.TP_PUBLICATION);
			String tp_year = json.getString(TeacherPaperTable.TP_YEAR);
			String tp_month = json.getString(TeacherPaperTable.TP_MONTH);
			String tp_classes = json.getString(TeacherPaperTable.TP_CLASSES);
			String tp_comments = "";

			int isnull = 0;
			if ("".equals(tp_colleges) || "".equals(tp_papertitle) || "".equals(tp_paperclasses) || "".equals(tp_authors)
					|| "".equals(tp_serial) || "".equals(tp_authorclasses) || "".equals(tp_publication) || "".equals(tp_year)
					|| "".equals(tp_month) || "".equals(tp_classes))
				isnull = 1;
			TeacherPaper ap = new TeacherPaper(tp_colleges, tp_papertitle,
					tp_paperclasses, tp_authors, tp_serial, tp_authorclasses,
					tp_publication, tp_year, tp_month, tp_classes,
					serialnumber, college, tp_comments,isnull);
			TeacherPaperDao apDao = new TeacherPaperDaoImpl();

			apDao.addRecord(ap);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
