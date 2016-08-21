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

import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.daoimpl.GuidingIdeologyofSchoolDaoImpl;
import cn.edu.xmu.table.GuidingIdeologyofSchoolTable;

/**
 * Servlet implementation class Sec_UpdateGuidingIdeologyofSchool
 */
@WebServlet("/UpdateGuidingIdeologyofSchool")
public class Sec_UpdateGuidingIdeologyofSchool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGuidingIdeologyofSchool() {
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
		//String college = request.getParameter(GuidingIdeologyofSchoolTable.GIS_COLLEGE);
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
					String gis_id = jsons.getJSONObject(i).getString(GuidingIdeologyofSchoolTable.GIS_ID);
					String gis_comments = jsons.getJSONObject(i).getString(GuidingIdeologyofSchoolTable.GIS_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(GuidingIdeologyofSchoolTable.GIS_ID, gis_id);
					params.put(GuidingIdeologyofSchoolTable.GIS_COMMENTS, gis_comments);
				
					GuidingIdeologyofSchoolDao gisDao = new GuidingIdeologyofSchoolDaoImpl();
					int result = gisDao.alterGuidingIdeologyofSchool(params, gis_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String gis_id = json.getString(GuidingIdeologyofSchoolTable.GIS_ID);
				
				String gis_mottocontent = json.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT);
				String gis_mottoremark = json.getString(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK);
				String gis_positiongoalcontent = json.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT);
				String gis_positiongoalremark = json.getString(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK);
				String gis_strategy = json.getString(GuidingIdeologyofSchoolTable.GIS_STRATEGY);
				String gis_discipline = json.getString(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE);
				String gis_professional = json.getString(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL);
				String gis_teacher = json.getString(GuidingIdeologyofSchoolTable.GIS_TEACHER);

				int isnull = 0;
				if(gis_mottocontent.equals("") || gis_mottoremark.equals("") || gis_positiongoalcontent.equals("") || 
						gis_positiongoalremark.equals("") || gis_strategy.equals("") || gis_discipline.equals("") || 
						gis_professional.equals("") || gis_teacher.equals(""))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(GuidingIdeologyofSchoolTable.GIS_ID, gis_id);
				params.put(GuidingIdeologyofSchoolTable.GIS_MOTTOCONTENT, gis_mottocontent);
				params.put(GuidingIdeologyofSchoolTable.GIS_MOTTOREMARK, gis_mottoremark);
				params.put(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALCONTENT, gis_positiongoalcontent);
				params.put(GuidingIdeologyofSchoolTable.GIS_POSITIONGOALREMARK, gis_positiongoalremark);
				params.put(GuidingIdeologyofSchoolTable.GIS_STRATEGY, gis_strategy);
				params.put(GuidingIdeologyofSchoolTable.GIS_DISCIPLINE, gis_discipline);
				params.put(GuidingIdeologyofSchoolTable.GIS_PROFESSIONAL, gis_professional);
				params.put(GuidingIdeologyofSchoolTable.GIS_TEACHER, gis_teacher);
				
				params.put(GuidingIdeologyofSchoolTable.ISNULL, isnull+"");
			
				GuidingIdeologyofSchoolDao gisDao = new GuidingIdeologyofSchoolDaoImpl();
				int result = gisDao.alterGuidingIdeologyofSchool(params, gis_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
