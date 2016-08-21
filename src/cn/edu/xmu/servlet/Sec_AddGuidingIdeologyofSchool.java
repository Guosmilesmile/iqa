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

import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.daoimpl.GuidingIdeologyofSchoolDaoImpl;
import cn.edu.xmu.entity.GuidingIdeologyofSchool;
import cn.edu.xmu.table.GuidingIdeologyofSchoolTable;

/**
 * Servlet implementation class Sec_AddGuidingIdeologyofSchool
 */
@WebServlet("/AddGuidingIdeologyofSchool")
public class Sec_AddGuidingIdeologyofSchool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGuidingIdeologyofSchool() {
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
		String college = request.getParameter(GuidingIdeologyofSchoolTable.GIS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String gis_mottocontent = json.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT);
			String gis_mottoremark = json.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK);
			String gis_positiongoalcontent = json.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT);
			String gis_positiongoalremark = json.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK);
			String gis_strategy = json.getString(GuidingIdeologyofSchoolTable.GIS_STRATEGY);
			String gis_discipline = json.getString(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE);
			String gis_professional = json.getString(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL);
			String gis_teacher = json.getString(GuidingIdeologyofSchoolTable.GIS_TEACHER);

			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(gis_mottocontent.equals("") || gis_mottoremark.equals("") || gis_positiongoalcontent.equals("") || 
					gis_positiongoalremark.equals("") || gis_strategy.equals("") || gis_discipline.equals("") || 
					gis_professional.equals("") || gis_teacher.equals(""))
				isnull = 1;
			
			if(gis_mottocontent.equals("") && gis_mottoremark.equals("") && gis_positiongoalcontent.equals("") && 
					gis_positiongoalremark.equals("") && gis_strategy.equals("") && gis_discipline.equals("") && 
					gis_professional.equals("") && gis_teacher.equals(""))
				return;
			
			int gis_serialnumber = serialnumber;
			String gis_college = college;
			String gis_comments = "";
			GuidingIdeologyofSchool gis = new GuidingIdeologyofSchool(gis_mottocontent,
					gis_mottoremark, gis_positiongoalcontent,
					gis_positiongoalremark, gis_strategy,
					gis_discipline, gis_professional, gis_teacher,
					gis_serialnumber, gis_college, gis_comments, isnull);			
			
			GuidingIdeologyofSchoolDao gisDao = new GuidingIdeologyofSchoolDaoImpl();
			gisDao.addRecord(gis);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
