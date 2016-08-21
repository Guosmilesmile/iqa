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

import cn.edu.xmu.dao.OutsidePracticePlaceDao;
import cn.edu.xmu.daoimpl.OutsidePracticePlaceDaoImpl;
import cn.edu.xmu.table.OutsidePracticePlaceTable;

/**
 * 
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdateOutsidePracticePlaceServlet")
public class Sec_UpdateOutsidePracticePlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateOutsidePracticePlaceServlet() {
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
					String opp_id = jsons.getJSONObject(i).getString(OutsidePracticePlaceTable.OPP_ID); 					   
					String opp_comments = jsons.getJSONObject(i).getString(OutsidePracticePlaceTable.OPP_COMMENTS); 
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(OutsidePracticePlaceTable.OPP_ID, opp_id);			 
					params.put(OutsidePracticePlaceTable.OPP_COMMENTS, opp_comments);
					
					OutsidePracticePlaceDao outsidePracticePlaceDao = new OutsidePracticePlaceDaoImpl();
					int result = outsidePracticePlaceDao.alterOutsidePracticePlace(params, opp_id);
					
					out.print(result);
				}
			}else {
				
			
			JSONObject json = new JSONObject(data);
			
			String opp_id = json.getString(OutsidePracticePlaceTable.OPP_ID); 
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
			String opp_comments = json.getString(OutsidePracticePlaceTable.OPP_COMMENTS); 
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(OutsidePracticePlaceTable.OPP_PLACENAME, opp_placename);
			params.put(OutsidePracticePlaceTable.OPP_COLLEGENAME, opp_collegename);
			params.put(OutsidePracticePlaceTable.OPP_ADDRESS, opp_address);
			params.put(OutsidePracticePlaceTable.OPP_MAJORS, opp_majors);
			params.put(OutsidePracticePlaceTable.OPP_STUDENTSPERTIME, opp_studentspertime+"");
			params.put(OutsidePracticePlaceTable.OPP_STUDENTSTHISYEAR, opp_studentsthisyear+"");
			params.put(OutsidePracticePlaceTable.OPP_MONTH, opp_month);
			params.put(OutsidePracticePlaceTable.OPP_COOPERATOR, opp_cooperator);
			params.put(OutsidePracticePlaceTable.OPP_SETUPDATE, opp_setupdate);
			params.put(OutsidePracticePlaceTable.OPP_LEVEL, opp_level);
			params.put(OutsidePracticePlaceTable.OPP_ACCUMULATEDSTUDENTS, opp_accumulatedstudents+"");
			params.put(OutsidePracticePlaceTable.OPP_COMMENTS, opp_comments);
			params.put(OutsidePracticePlaceTable.OPP_ISNULL, opp_isnull+"");
			
			
			OutsidePracticePlaceDao outsidePracticePlaceDao = new OutsidePracticePlaceDaoImpl();
			int result = outsidePracticePlaceDao.alterOutsidePracticePlace(params, opp_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

