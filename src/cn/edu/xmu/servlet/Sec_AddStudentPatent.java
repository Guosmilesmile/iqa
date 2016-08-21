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

import cn.edu.xmu.dao.StudentPatentDao;
import cn.edu.xmu.daoimpl.StudentPatentDaoImpl;
import cn.edu.xmu.entity.StudentPatent;
import cn.edu.xmu.table.StudentPatentTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddStudentPatent")
public class Sec_AddStudentPatent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddStudentPatent() {
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
		String college = request.getParameter(StudentPatentTable.SP_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");

		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try {
			JSONObject json = new JSONObject(data);

			String sp_colleges = json.getString(StudentPatentTable.SP_COLLEGES);
			String sp_patentname = json
					.getString(StudentPatentTable.SP_PATENTNAME);
			String sp_number = json
					.getString(StudentPatentTable.SP_NUMBER);
			String sp_grade = json.getString(StudentPatentTable.SP_GRADE);
			String sp_author = json.getString(StudentPatentTable.SP_AUTHORS);
			String sp_serial = json.getString(StudentPatentTable.SP_SERIAL);
			String sp_major = json.getString(StudentPatentTable.SP_MAJOR);
			String time= json
					.getString(StudentPatentTable.SP_TIME);
			String sp_remark = json.getString(StudentPatentTable.SP_REMARK);
			
			Date sp_time =Date.valueOf("1800-1-1");
			if(!"".equals(time))
				sp_time = Date.valueOf(time);
			int isnull = 0;
			if("".equals(sp_colleges)||"".equals(sp_patentname)||"".equals(sp_number)||"".equals(sp_grade)||"".equals(sp_author)||"".equals(sp_serial)||"".equals(sp_major)||"".equals(time))
				isnull=1;
			if("".equals(sp_colleges)&&"".equals(sp_patentname)&&"".equals(sp_number)&&"".equals(sp_grade)&&"".equals(sp_author)&&"".equals(sp_serial)&&"".equals(sp_major)&&"".equals(time))
			{
				out.println(false);
				return;
			}
			StudentPatentDao spDao = new StudentPatentDaoImpl();
			StudentPatent sp = new StudentPatent(sp_colleges, sp_patentname,
					sp_number, sp_grade, sp_author, sp_serial, sp_major,
					sp_time, sp_remark, serialnumber, college,isnull);
			spDao.addRecord(sp);

			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
			out.println(false);
		} finally {
			out.close();
		}

	}
}
