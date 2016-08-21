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

import cn.edu.xmu.dao.StaffTeachingAchieveDao;
import cn.edu.xmu.daoimpl.StaffTeachingAchieveDaoImpl;
import cn.edu.xmu.table.StaffTeachingAchieveTable;

/**
 * Servlet implementation class Sec_UpdateStaffTeachingAchieve
 */
@WebServlet("/UpdateStaffTeachingAchieve")
public class Sec_UpdateStaffTeachingAchieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStaffTeachingAchieve() {
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
		//String college = request.getParameter(StaffTeachingAchieveTable.STA_COLLEGE);
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
					//无serialnumber,deadline,college
					String sta_id = jsons.getJSONObject(i).getString(StaffTeachingAchieveTable.STA_ID);
					String sta_comments = jsons.getJSONObject(i).getString(StaffTeachingAchieveTable.STA_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(StaffTeachingAchieveTable.STA_ID, sta_id);
					params.put(StaffTeachingAchieveTable.STA_COMMENTS, sta_comments);
				
					StaffTeachingAchieveDao staDao = new StaffTeachingAchieveDaoImpl();
					int result = staDao.alterStaffTeachingAchieve(params, sta_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String sta_id = json.getString(StaffTeachingAchieveTable.STA_ID);

				String sta_year = json.getString(StaffTeachingAchieveTable.STA_YEAR);
				if(sta_year.equals(""))
					sta_year = "-999";
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
				if(sta_year.equals("-999") || sta_session.equals("") || sta_collegename.equals("") ||sta_projectname.equals("") ||
						sta_persons.equals("") || sta_country.equals("") || sta_province.equals("") || sta_school.equals("") ||
						sta_ifstaffattend.equals(""))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(StaffTeachingAchieveTable.STA_ID, sta_id);
				
				params.put(StaffTeachingAchieveTable.STA_YEAR, sta_year);
				params.put(StaffTeachingAchieveTable.STA_SESSION, sta_session);
				params.put(StaffTeachingAchieveTable.STA_COLLEGENAME, sta_collegename);
				params.put(StaffTeachingAchieveTable.STA_PROJECTNAME, sta_projectname);
				params.put(StaffTeachingAchieveTable.STA_PERSONS, sta_persons);

				params.put(StaffTeachingAchieveTable.STA_COUNTRY, sta_country);
				params.put(StaffTeachingAchieveTable.STA_PROVINCE, sta_province);
				params.put(StaffTeachingAchieveTable.STA_SCHOOL, sta_school);
				params.put(StaffTeachingAchieveTable.STA_IFSTAFFATTEND, sta_ifstaffattend);
				
				params.put(StaffTeachingAchieveTable.ISNULL, isnull+"");
			
				StaffTeachingAchieveDao staDao = new StaffTeachingAchieveDaoImpl();
				int result = staDao.alterStaffTeachingAchieve(params, sta_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
