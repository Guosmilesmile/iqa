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

import cn.edu.xmu.dao.ExpertLectureDao;
import cn.edu.xmu.daoimpl.ExpertLectureDaoImpl;
import cn.edu.xmu.entity.ExpertLecture;
import cn.edu.xmu.table.ExpertLectureTable;

/**
 * Servlet implementation class Sec_AddExpertLecture
 */
@WebServlet("/AddExpertLecture")
public class Sec_AddExpertLecture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddExpertLecture() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(ExpertLectureTable.EL_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String el_collegename = json.getString(ExpertLectureTable.EL_COLLEGENAME);
			String el_title = json.getString(ExpertLectureTable.EL_TITLE);
			String el_grade = json.getString(ExpertLectureTable.EL_GRADE);
			String el_term = json.getString(ExpertLectureTable.EL_TERM);
			String el_type = json.getString(ExpertLectureTable.EL_TYPE);

			String el_name = json.getString(ExpertLectureTable.EL_NAME);
			String el_prorank = json.getString(ExpertLectureTable.EL_PRORANK);
			String el_unit = json.getString(ExpertLectureTable.EL_UNIT);
			String el_area = json.getString(ExpertLectureTable.EL_AREA);
			String el_remark = json.getString(ExpertLectureTable.EL_REMARK);
			
			int el_serialnumber = serialnumber;
			String el_college = college;
			String el_comments = "";
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(el_collegename.equals("") || el_title.equals("") || el_grade.equals("") || el_term.equals("") ||
					el_type.equals("") || el_name.equals("") || el_prorank.equals("") || el_unit.equals("") ||
					el_area.equals("") || el_remark.equals(""))
				isnull = 1;
			
			if(el_collegename.equals("") && el_title.equals("") && el_grade.equals("") && el_term.equals("") &&
					el_type.equals("") && el_name.equals("") && el_prorank.equals("") && el_unit.equals("") &&
					el_area.equals("") && el_remark.equals(""))
				return;
			
			ExpertLecture el = new ExpertLecture(el_collegename, el_title,
					el_grade, el_term, el_type, el_name,
					el_prorank, el_unit, el_area,
					el_remark, el_serialnumber, 
					el_college, el_comments, isnull);			
			
			ExpertLectureDao elDao = new ExpertLectureDaoImpl();
			elDao.addRecord(el);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
