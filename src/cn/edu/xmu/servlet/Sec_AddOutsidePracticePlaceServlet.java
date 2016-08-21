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

import cn.edu.xmu.dao.OutsidePracticePlaceDao;
import cn.edu.xmu.daoimpl.OutsidePracticePlaceDaoImpl;
import cn.edu.xmu.entity.OutsidePracticePlace;
import cn.edu.xmu.table.OutsidePracticePlaceTable;

@WebServlet("/Sec_AddOutsidePracticePlaceServlet")
public class Sec_AddOutsidePracticePlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddOutsidePracticePlaceServlet() {
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
			
			
			String opp_placename = json.getString(OutsidePracticePlaceTable.OPP_PLACENAME);   
			String opp_collegename  = json.getString(OutsidePracticePlaceTable.OPP_COLLEGENAME);
		
			String opp_address = json.getString(OutsidePracticePlaceTable.OPP_ADDRESS); 
		 	String opp_majors = json.getString(OutsidePracticePlaceTable.OPP_MAJORS);  
			String studentspertime = json.getString(OutsidePracticePlaceTable.OPP_STUDENTSPERTIME); 
			Integer opp_studentspertime = -1;
			if(!"".equals(studentspertime)) opp_studentspertime = Integer.parseInt(studentspertime);
			String studentsthisyear = json.getString(OutsidePracticePlaceTable.OPP_STUDENTSTHISYEAR); 
			Integer opp_studentsthisyear = -1;
			if(!"".equals(studentsthisyear)) opp_studentsthisyear = Integer.parseInt(studentsthisyear);
			String opp_month = json.getString(OutsidePracticePlaceTable.OPP_MONTH); 
			String opp_cooperator = json.getString(OutsidePracticePlaceTable.OPP_COOPERATOR);  
			String opp_setupdate = json.getString(OutsidePracticePlaceTable.OPP_SETUPDATE); 
			String opp_level = json.getString(OutsidePracticePlaceTable.OPP_LEVEL);
			String accumulatedstudents = json.getString(OutsidePracticePlaceTable.OPP_ACCUMULATEDSTUDENTS);
		    Integer opp_accumulatedstudents = -1;
			if(!"".equals(accumulatedstudents)) opp_accumulatedstudents = Integer.parseInt(accumulatedstudents);
				
			int opp_isnull = 0;
			if("".equals(opp_placename)||"".equals(opp_collegename)||"".equals(opp_address)||"".equals(opp_majors)
					|| "".equals(studentspertime) || "".equals(studentsthisyear) || "".equals(opp_month) 
					|| "".equals(opp_cooperator) ||"".equals(opp_setupdate)|| "".equals(opp_level) ||"".equals(accumulatedstudents))
			{
				opp_isnull = 1;
			}
			if ("".equals(opp_placename)&&"".equals(opp_collegename)&&"".equals(opp_address)&&"".equals(opp_majors)
					&& "".equals(studentspertime) && "".equals(studentsthisyear) && "".equals(opp_month) 
					&& "".equals(opp_cooperator) && "".equals(opp_setupdate)&& "".equals(opp_level) &&"".equals(accumulatedstudents))
			{
				result = -1;
				out.println(result);
				return;
			}
			
			String opp_college  = request.getParameter(OutsidePracticePlaceTable.OPP_COLLEGE);
			opp_college = URLDecoder.decode(opp_college,"UTF-8");
			OutsidePracticePlace outsidePracticePlace = new OutsidePracticePlace( opp_placename, opp_collegename, opp_address, opp_majors, 
		            opp_studentspertime, opp_studentsthisyear, opp_month, opp_cooperator, opp_setupdate, opp_level, 
		            opp_accumulatedstudents, opp_college, serialnumber, opp_isnull);
			
			OutsidePracticePlaceDao outsidePracticePlaceDao = new OutsidePracticePlaceDaoImpl();
			result = outsidePracticePlaceDao.addOutsidePracticePlace(outsidePracticePlace);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
