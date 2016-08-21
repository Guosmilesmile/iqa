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

import cn.edu.xmu.dao.ExternalTeacherDao;
import cn.edu.xmu.daoimpl.ExternalTeacherDaoImpl;
import cn.edu.xmu.table.ExternalTeacherTable;

@WebServlet("/UpdateExternalTeacher")
public class Sec_UpdateExternalTeacher extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateExternalTeacher() {
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
		String patter = request.getParameter("patter");
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		//String college = request.getParameter(ExternalTeacherTable.ET_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					String et_id = jsons.getJSONObject(i).getString(ExternalTeacherTable.ET_ID);
					
					String et_comments = jsons.getJSONObject(i).getString(ExternalTeacherTable.ET_COMMENTS);
					
					Map<String,String> params= new HashMap<String, String>();
					params.put(ExternalTeacherTable.ET_COMMENTS, et_comments);
					//params.put(ExternalTeacherTable.ET_COLLEGE, college);
					
					ExternalTeacherDao etdao = new ExternalTeacherDaoImpl();
					int result = etdao.alterExternalTeacher(params, et_id+"");
				
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				//无serinum deadline college
				String et_id = json.getString(ExternalTeacherTable.ET_ID);
				String et_name = json.getString(ExternalTeacherTable.ET_NAME);
				String et_worknumber = json.getString(ExternalTeacherTable.ET_WORKNUMBER);
				String et_sex = json.getString(ExternalTeacherTable.ET_SEX);
			
				String et_birth = json.getString(ExternalTeacherTable.ET_BIRTH);
				if(et_birth.equals(""))
					et_birth = "1800-01-01";
				
				String et_appointment = json.getString(ExternalTeacherTable.ET_APPOINTMENT);
				if(et_appointment.equals(""))
					et_appointment = "1800-01-01";
			
				String et_situation = json.getString(ExternalTeacherTable.ET_SITUATION);
				String et_term = json.getString(ExternalTeacherTable.ET_TERM);
				if(et_term.equals(""))
					et_term = "-999";
				
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
						et_birth.equals("1800-01-01") || et_appointment.equals("1800-01-01") || et_situation.equals("") ||
						et_term.equals("-999") || et_departmentnumber.equals("") || et_education.equals("") ||
						et_topeducation.equals("") || et_professional.equals("") || et_subject.equals("") ||
						et_job.equals("") || et_teacher.equals("") || et_area.equals("") ||
						et_documentnumber.equals("") || et_departmentname.equals(""))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(ExternalTeacherTable.ET_NAME, et_name);
				params.put(ExternalTeacherTable.ET_WORKNUMBER, et_worknumber);
				params.put(ExternalTeacherTable.ET_SEX, et_sex);
				params.put(ExternalTeacherTable.ET_BIRTH, et_birth);
				params.put(ExternalTeacherTable.ET_APPOINTMENT, et_appointment);
				params.put(ExternalTeacherTable.ET_SITUATION, et_situation);
				params.put(ExternalTeacherTable.ET_TERM, et_term);
				params.put(ExternalTeacherTable.ET_DEPARTMENTNUMBER, et_departmentnumber);
				params.put(ExternalTeacherTable.ET_EDUCATION, et_education);
				params.put(ExternalTeacherTable.ET_TOPEDUCATION, et_topeducation);
				params.put(ExternalTeacherTable.ET_PROFESSIONAL, et_professional);
				params.put(ExternalTeacherTable.ET_SUBJECT, et_subject);
				params.put(ExternalTeacherTable.ET_JOB, et_job);
				params.put(ExternalTeacherTable.ET_TEACHER, et_teacher);
				params.put(ExternalTeacherTable.ET_AREA, et_area);
				params.put(ExternalTeacherTable.ET_DOCUMENTNUMBER, et_documentnumber);
				params.put(ExternalTeacherTable.ET_DEPARTMENTNAME, et_departmentname);
				params.put(ExternalTeacherTable.ISNULL, isnull+"");
				//params.put(ExternalTeacherTable.ET_COLLEGE, college);
			
				ExternalTeacherDao etdao = new ExternalTeacherDaoImpl();
				int result = etdao.alterExternalTeacher(params, et_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
