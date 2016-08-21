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

import cn.edu.xmu.dao.DistinguishedTeacherDao;
import cn.edu.xmu.daoimpl.DistinguishedTeacherDaoImpl;
import cn.edu.xmu.entity.DistinguishedTeacher;
import cn.edu.xmu.table.DistinguishedTeacherTable;

/**
 * Servlet implementation class Sec_AddDistinguishedTeacher
 */
@WebServlet("/AddDistinguishedTeacher")
public class Sec_AddDistinguishedTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddDistinguishedTeacher() {
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
		String college = request.getParameter(DistinguishedTeacherTable.DT_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);

			temp = json.getString(DistinguishedTeacherTable.DT_SEQUENCENUMBER);
			int dt_sequencenumber = -999;
			if(!temp.equals(""))
				dt_sequencenumber = Integer.valueOf(temp);
			
			String dt_name = json.getString(DistinguishedTeacherTable.DT_NAME);
			String dt_collegename = json.getString(DistinguishedTeacherTable.DT_COLLEGENAME);
			temp = json.getString(DistinguishedTeacherTable.DT_COUNTRY);
			int dt_country = -999;
			if(!temp.equals(""))
				dt_country = Integer.valueOf(temp);
			
			temp = json.getString(DistinguishedTeacherTable.DT_PROVINCE);
			int dt_province = -999;
			if(!temp.equals(""))
				dt_province = Integer.valueOf(temp);
			
			temp = json.getString(DistinguishedTeacherTable.DT_SCHOOL);
			int dt_school = -999;
			if(!temp.equals(""))
				dt_school = Integer.valueOf(temp);
			
			int dt_serialnumber = serialnumber;
			String dt_college = college;
			String dt_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(dt_sequencenumber==-999 || dt_name.equals("") || dt_collegename.equals("") ||
					dt_country==-999 || dt_province==-999 || dt_school==-999)
				isnull = 1;
			
			if(dt_sequencenumber==-999 && dt_name.equals("") && dt_collegename.equals("") &&
					dt_country==-999 && dt_province==-999 && dt_school==-999)
				return;
			
			DistinguishedTeacher dt = new DistinguishedTeacher(dt_sequencenumber, dt_name,
					dt_collegename, dt_country, dt_province,
					dt_school, dt_serialnumber,
					dt_college, dt_comments, isnull);			
			
			DistinguishedTeacherDao dtDao = new DistinguishedTeacherDaoImpl();
			dtDao.addRecord(dt);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
