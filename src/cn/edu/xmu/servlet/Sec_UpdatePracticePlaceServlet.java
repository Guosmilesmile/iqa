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

import cn.edu.xmu.dao.PracticePlaceDao;
import cn.edu.xmu.daoimpl.PracticePlaceDaoImpl;
import cn.edu.xmu.table.PracticePlaceTable;

/**
 * 
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdatePracticePlaceServlet")
public class Sec_UpdatePracticePlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdatePracticePlaceServlet() {
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
		String patter = request.getParameter("patter");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {					
					   String pp_id = jsons.getJSONObject(i).getString(PracticePlaceTable.PP_ID);					   
					   String pp_comments = jsons.getJSONObject(i).getString(PracticePlaceTable.PP_COMMENTS);
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(PracticePlaceTable.PP_ID, pp_id);					 
					params.put(PracticePlaceTable.PP_COMMENTS, pp_comments);
					
					PracticePlaceDao practicePlaceDao = new PracticePlaceDaoImpl();
					int result = practicePlaceDao.alterPracticePlace(params, pp_id);
					out.print(result);
				}
			}else {
				
			JSONObject json = new JSONObject(data);
			String pp_id = json.getString(PracticePlaceTable.PP_ID); 
			String pp_placename = json.getString(PracticePlaceTable.PP_PLACENAME);   //名称
			String pp_collegename  = json.getString(PracticePlaceTable.PP_COLLEGENAME); //院系名称
			String area = json.getString(PracticePlaceTable.PP_AREA);  //面积
			Float pp_area = (float) -1.0;
			if(!"".equals(area)) pp_area = Float.parseFloat(area);
			String pp_property = json.getString(PracticePlaceTable.PP_PROPERTY); //实验室性质
		 	String pp_majors = json.getString(PracticePlaceTable.PP_MAJORS);  //面向专业
			String hours = json.getString(PracticePlaceTable.PP_HOURS);  //学年度承担的实验教学人时数
			Integer pp_hours = -1;
			if(!"".equals(hours)) pp_hours = Integer.parseInt(hours);
			String times = json.getString(PracticePlaceTable.PP_TIMES); //学年度承担的实验教学人次数
			Integer pp_times = -1;
			if(!"".equals(times)) pp_times = Integer.parseInt(times);
			String projectnum = json.getString(PracticePlaceTable.PP_PROJECTNUM); //本科生实验实习实训项目数
			Integer pp_projectnum = -1;
			if(!"".equals(projectnum)) pp_projectnum = Integer.parseInt(projectnum);
			String largeststudents = json.getString(PracticePlaceTable.PP_LARGESTSTUDENTS);  //最大可容纳学生数
			Integer pp_largeststudents = -1;
			if(!"".equals(largeststudents)) pp_largeststudents = Integer.parseInt(largeststudents);
			
			String pp_comments = json.getString(PracticePlaceTable.PP_COMMENTS);
			int pp_isnull = 0;
			if("".equals(pp_placename)||"".equals(pp_collegename)||"".equals(area)||"".equals(pp_property)|| "".equals(pp_majors) 
					|| "".equals(hours) || "".equals(times) || "".equals(projectnum) ||"".equals(largeststudents))
			{
				pp_isnull = 1;
			}				
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(PracticePlaceTable.PP_PLACENAME, pp_placename);
			params.put(PracticePlaceTable.PP_COLLEGENAME, pp_collegename);
			params.put(PracticePlaceTable.PP_AREA, pp_area+"");
			params.put(PracticePlaceTable.PP_PROPERTY, pp_property);
			params.put(PracticePlaceTable.PP_MAJORS, pp_majors);
			params.put(PracticePlaceTable.PP_HOURS, pp_hours+"");
			params.put(PracticePlaceTable.PP_TIMES, pp_times+"");
			params.put(PracticePlaceTable.PP_PROJECTNUM, pp_projectnum+"");
			params.put(PracticePlaceTable.PP_LARGESTSTUDENTS, pp_largeststudents+"");
			params.put(PracticePlaceTable.PP_COMMENTS, pp_comments);
			params.put(PracticePlaceTable.PP_ISNULL, pp_isnull+"");
			
			PracticePlaceDao practicePlaceDao = new PracticePlaceDaoImpl();
			int result = practicePlaceDao.alterPracticePlace(params, pp_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

