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

import cn.edu.xmu.dao.PracticePlaceDao;
import cn.edu.xmu.daoimpl.PracticePlaceDaoImpl;
import cn.edu.xmu.entity.PracticePlace;
import cn.edu.xmu.table.PracticePlaceTable;

@WebServlet("/Sec_AddPracticePlaceServlet")
public class Sec_AddPracticePlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddPracticePlaceServlet() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
					 
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
			
			int pp_isnull = 0;
			if("".equals(pp_placename)||"".equals(pp_collegename)||"".equals(area)||"".equals(pp_property)|| "".equals(pp_majors) 
					|| "".equals(hours) || "".equals(times) || "".equals(projectnum) ||"".equals(largeststudents))
			{
				pp_isnull = 1;
			}
			if ("".equals(pp_placename) && "".equals(pp_collegename) && "".equals(area) && "".equals(pp_property) &&  "".equals(pp_majors) 
					 && "".equals(hours)  && "".equals(times) && "".equals(projectnum) && "".equals(largeststudents))
			{
				result = -1;
				out.println(result);
				return;
			}
					
			String pp_college  = request.getParameter(PracticePlaceTable.PP_COLLEGE); //院系名称			
			pp_college = URLDecoder.decode(pp_college,"UTF-8");
			
			PracticePlace practicePlace = new PracticePlace(pp_placename, pp_collegename, pp_area, pp_property, pp_majors, 
					pp_hours, pp_times, pp_projectnum,pp_largeststudents,pp_college,serialnumber,pp_isnull);
			PracticePlaceDao practicePlaceDao = new PracticePlaceDaoImpl();
			result = practicePlaceDao.addPracticePlace(practicePlace);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
