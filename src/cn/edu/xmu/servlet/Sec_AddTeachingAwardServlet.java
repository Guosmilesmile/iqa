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

import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.entity.TeachingAward;
import cn.edu.xmu.table.TeachingAwardTable;

@WebServlet("/Sec_AddTeachingAwardServlet")
public class Sec_AddTeachingAwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachingAwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		String college = request.getParameter(TeachingAwardTable.TA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String ta_projectname = json.getString(TeachingAwardTable.TA_PROJECTNAME);   
			String ta_compere  = json.getString(TeachingAwardTable.TA_COMPERE); 
			String ta_comperenumber = json.getString(TeachingAwardTable.TA_COMPERENUMBER); 
		 	String ta_level = json.getString(TeachingAwardTable.TA_LEVEL);  
			String ta_windate = json.getString(TeachingAwardTable.TA_WINDATE); 
			String ta_grantunit = json.getString(TeachingAwardTable.TA_GRANTUNIT);  
			int ta_isnull = 0;
			if("".equals(ta_projectname)||"".equals(ta_compere)||"".equals(ta_comperenumber)||"".equals(ta_level)
					||"".equals(ta_windate)||"".equals(ta_grantunit))
			{
				ta_isnull = 1;
			}
			if("".equals(ta_projectname)&&"".equals(ta_compere)&&"".equals(ta_comperenumber)&&"".equals(ta_level)
					&&"".equals(ta_windate)&&"".equals(ta_grantunit))
			{
				result = -1;
				out.println(result);
				return;
			}
			TeachingAward teachingAward = new TeachingAward(ta_projectname,ta_compere,ta_comperenumber,ta_level,
					ta_windate, ta_grantunit, college,serialnumber, ta_isnull);
			
			TeachingAwardDao teachingAwardDao = new TeachingAwardDaoImpl();
			result = teachingAwardDao.addTeachingAward(teachingAward);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
