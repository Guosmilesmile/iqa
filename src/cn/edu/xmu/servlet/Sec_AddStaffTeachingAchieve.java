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

import cn.edu.xmu.dao.StaffTeachingAchieveDao;
import cn.edu.xmu.daoimpl.StaffTeachingAchieveDaoImpl;
import cn.edu.xmu.entity.StaffTeachingAchieve;
import cn.edu.xmu.table.StaffTeachingAchieveTable;

/**
 * Servlet implementation class Sec_AddStaffTeachingAchieve
 */
@WebServlet("/AddStaffTeachingAchieve")
public class Sec_AddStaffTeachingAchieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStaffTeachingAchieve() {
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
		String college = request.getParameter(StaffTeachingAchieveTable.STA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);
			
			temp = json.getString(StaffTeachingAchieveTable.STA_YEAR);
			int sta_year = -999;
			if(!temp.equals(""))
				sta_year = Integer.valueOf(temp);
			
			String sta_session = json.getString(StaffTeachingAchieveTable.STA_SESSION);
			String sta_collegename = json.getString(StaffTeachingAchieveTable.STA_COLLEGENAME);
			String sta_projectname = json.getString(StaffTeachingAchieveTable.STA_PROJECTNAME);
			String sta_persons = json.getString(StaffTeachingAchieveTable.STA_PERSONS);

			String sta_country = json.getString(StaffTeachingAchieveTable.STA_COUNTRY);
			String sta_province = json.getString(StaffTeachingAchieveTable.STA_PROVINCE);
			String sta_school = json.getString(StaffTeachingAchieveTable.STA_SCHOOL);
			String sta_ifstaffattend = json.getString(StaffTeachingAchieveTable.STA_IFSTAFFATTEND);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(sta_year==-999 || sta_session.equals("") || sta_collegename.equals("") ||sta_projectname.equals("") ||
					sta_persons.equals("") || sta_country.equals("") || sta_province.equals("") || sta_school.equals("") ||
					sta_ifstaffattend.equals(""))
				isnull = 1;
			
			if(sta_year==-999 && sta_session.equals("") && sta_collegename.equals("") &&sta_projectname.equals("") &&
					sta_persons.equals("") && sta_country.equals("") && sta_province.equals("") && sta_school.equals("") &&
					sta_ifstaffattend.equals(""))
				return;
			
			int sta_serialnumber = serialnumber;
			String sta_college = college;
			String sta_comments = "";
			StaffTeachingAchieve sta = new StaffTeachingAchieve(sta_year, sta_session,
					sta_collegename, sta_projectname, sta_persons,
					sta_country, sta_province, sta_school,
					sta_ifstaffattend, sta_serialnumber, 
					sta_college, sta_comments, isnull);			
			
			StaffTeachingAchieveDao staDao = new StaffTeachingAchieveDaoImpl();
			staDao.addRecord(sta);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
