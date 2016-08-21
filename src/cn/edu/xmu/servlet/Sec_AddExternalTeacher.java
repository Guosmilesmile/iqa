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

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ExternalTeacherDao;
import cn.edu.xmu.daoimpl.ExternalTeacherDaoImpl;
import cn.edu.xmu.entity.ExternalTeacher;
import cn.edu.xmu.table.ExternalTeacherTable;

/**
 * 
 * @author zhantu
 *
 */
@WebServlet("/AddExternalTeacher")
public class Sec_AddExternalTeacher extends HttpServlet{
	private static final long serialVersionUID = 1L;
    //private String majorList;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddExternalTeacher() {
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
		String college = request.getParameter(ExternalTeacherTable.ET_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String et_name = json.getString(ExternalTeacherTable.ET_NAME);
			String et_worknumber = json.getString(ExternalTeacherTable.ET_WORKNUMBER);
			String et_sex = json.getString(ExternalTeacherTable.ET_SEX);
			
			String et_birthtemp = json.getString(ExternalTeacherTable.ET_BIRTH);
			Date et_birth = Date.valueOf("1800-1-1");//不填则默认存1800-1-1
			if(!et_birthtemp.equals(""))
				et_birth = Date.valueOf(et_birthtemp);
			
			String et_appointmenttemp = json.getString(ExternalTeacherTable.ET_APPOINTMENT);
			Date et_appointment = Date.valueOf("1800-1-1");
			if(!et_appointmenttemp.equals(""))
				et_appointment = Date.valueOf(et_appointmenttemp);
			
			String et_situation = json.getString(ExternalTeacherTable.ET_SITUATION);
			String term = json.getString(ExternalTeacherTable.ET_TERM);
			int et_term = -999;
			if(!term.equals(""))
				et_term = Integer.valueOf(term);
			
			String et_departmentnumber = json.getString(ExternalTeacherTable.ET_DEPARTMENTNUMBER);
			String et_education = json.getString(ExternalTeacherTable.ET_EDUCATION);
			String et_topeducation = json.getString(ExternalTeacherTable.ET_TOPEDUCATION);
			String et_professional = json.getString(ExternalTeacherTable.ET_PROFESSIONAL);
			String et_subject = json.getString(ExternalTeacherTable.ET_SUBJECT);
			String et_job = json.getString(ExternalTeacherTable.ET_JOB);
			String et_teacher = json.getString(ExternalTeacherTable.ET_TEACHER);
			String et_area = json.getString(ExternalTeacherTable.ET_AREA);
			String et_documentnumber = json.getString(ExternalTeacherTable.ET_DOCUMENTNUMBER);
			String et_departmentname = json.getString(ExternalTeacherTable.ET_DEPARTMENTNAME);
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(et_name.equals("") || et_worknumber.equals("") || et_sex.equals("") || 
					et_birthtemp.equals("") || et_appointmenttemp.equals("") || et_situation.equals("") ||
					term.equals("") || et_departmentnumber.equals("") || et_education.equals("") ||
					et_topeducation.equals("") || et_professional.equals("") || et_subject.equals("") ||
					et_job.equals("") || et_teacher.equals("") || et_area.equals("") ||
					et_documentnumber.equals("") || et_departmentname.equals(""))
				isnull = 1;
			
			String et_comments = "";
			String et_college = college;
			int et_serialnumber = serialnumber;
					
			if(et_name.equals("") && et_worknumber.equals("") && et_sex.equals("") && 
					et_birthtemp.equals("") && et_appointmenttemp.equals("") && et_situation.equals("") &&
					term.equals("") && et_departmentnumber.equals("") && et_education.equals("") &&
					et_topeducation.equals("") && et_professional.equals("") && et_subject.equals("") &&
					et_job.equals("") && et_teacher.equals("") && et_area.equals("") &&
					et_documentnumber.equals("") && et_departmentname.equals(""))
				return;
			
			ExternalTeacher et = new ExternalTeacher(et_name, et_worknumber,
					et_sex, et_birth, et_appointment,
					et_situation, et_term, et_departmentnumber,
					et_education, et_topeducation,
					et_professional, et_subject,
					 et_job, et_teacher, et_area,
					et_serialnumber, et_comments, et_college, et_documentnumber, et_departmentname, isnull);

			ExternalTeacherDao externalTeacherDao = new ExternalTeacherDaoImpl();
			externalTeacherDao.addRecord(et);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
