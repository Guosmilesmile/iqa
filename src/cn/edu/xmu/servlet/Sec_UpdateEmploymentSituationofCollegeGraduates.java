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

import cn.edu.xmu.dao.EmploymentSituationofCollegeGraduatesDao;
import cn.edu.xmu.daoimpl.EmploymentSituationofCollegeGraduatesDaoImpl;
import cn.edu.xmu.table.EmploymentSituationofCollegeGraduatesTable;

/**
 * Servlet implementation class Sec_UpdateEmploymentSituationofCollegeGraduates
 */
@WebServlet("/UpdateEmploymentSituationofCollegeGraduates")
public class Sec_UpdateEmploymentSituationofCollegeGraduates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateEmploymentSituationofCollegeGraduates() {
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
		//String college = request.getParameter(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE);
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
					String escg_id = jsons.getJSONObject(i).getString(EmploymentSituationofCollegeGraduatesTable.ESCG_ID);
					String escg_comments = jsons.getJSONObject(i).getString(EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS);

					
					Map<String,String> params= new HashMap<String, String>();
					params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_ID, escg_id);
					
					params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_COMMENTS, escg_comments);
				
					EmploymentSituationofCollegeGraduatesDao escgDao = new EmploymentSituationofCollegeGraduatesDaoImpl();
					int result = escgDao.alterEmploymentSituationofCollegeGraduates(params, escg_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String escg_id = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_ID);
				String escg_recommendgraduate = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE);
				if(escg_recommendgraduate.equals(""))
					escg_recommendgraduate = "-999";
				
				String escg_postgraduateexamin = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN);
				if(escg_postgraduateexamin.equals(""))
					escg_postgraduateexamin = "-999";
				String escg_postgraduateexamout = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT);
				if(escg_postgraduateexamout.equals(""))
					escg_postgraduateexamout = "-999";
				int escg_postgraduateexamsum = -999;
				if(!escg_postgraduateexamin.equals("-999") && !escg_postgraduateexamout.equals("-999"))
					escg_postgraduateexamsum = Integer.valueOf(escg_postgraduateexamin)+Integer.valueOf(escg_postgraduateexamout);
				
				String escg_studyabroad = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD);
				if(escg_studyabroad.equals(""))
					escg_studyabroad = "-999";
				
				String escg_gov = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV);
				if(escg_gov.equals(""))
					escg_gov = "-999";
				String escg_institution = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION);
				if(escg_institution.equals(""))
					escg_institution = "-999";
				String escg_enterprise = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE);
				if(escg_enterprise.equals(""))
					escg_enterprise = "-999";
				String escg_troops = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS);
				if(escg_troops.equals(""))
					escg_troops = "-999";
				String escg_flexibleemployment = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT);
				if(escg_flexibleemployment.equals(""))
					escg_flexibleemployment = "-999";
				String esce_entrance = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE);
				if(esce_entrance.equals(""))
					esce_entrance = "-999";
				String escg_nationallocalprojectemployment = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT);
				if(escg_nationallocalprojectemployment.equals(""))
					escg_nationallocalprojectemployment = "-999";
				String escg_others = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS);
				if(escg_others.equals(""))
					escg_others = "-999";
				int escg_employmentsum = -999;
				if(!escg_gov.equals("-999") && !escg_institution.equals("-999") && !escg_enterprise.equals("-999") && !escg_troops.equals("-999") && 
						!escg_flexibleemployment.equals("-999") && !esce_entrance.equals("-999") && !escg_nationallocalprojectemployment.equals("-999") && !escg_others.endsWith("-999"))
					escg_employmentsum = Integer.valueOf(escg_gov)+Integer.valueOf(escg_institution)+
					Integer.valueOf(escg_enterprise)+Integer.valueOf(escg_troops)+
					Integer.valueOf(escg_flexibleemployment)+Integer.valueOf(esce_entrance)+
					Integer.valueOf(escg_nationallocalprojectemployment)+Integer.valueOf(escg_others);
				
				int isnull = 0;
				if(escg_postgraduateexamin.equals("-999") || escg_postgraduateexamout.equals("-999")|| escg_gov.equals("-999") || escg_institution.equals("-999") || escg_enterprise.equals("-999") || escg_troops.equals("-999") || escg_flexibleemployment.equals("-999") || esce_entrance.equals("-999") || escg_nationallocalprojectemployment.equals("-999") || escg_others.equals("-999") || escg_studyabroad.equals("-999") || escg_recommendgraduate.equals("-999"))
					isnull = 1;

				
				Map<String,String> params= new HashMap<String, String>();
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_ID, escg_id);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE, escg_recommendgraduate);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMSUM, escg_postgraduateexamsum+"");
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN, escg_postgraduateexamin);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT, escg_postgraduateexamout);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD, escg_studyabroad);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_EMPLOYMENTSUM, escg_employmentsum+"");
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV, escg_gov);

				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION, escg_institution);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE, escg_enterprise);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS, escg_troops);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT, escg_flexibleemployment);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE, esce_entrance);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT, escg_nationallocalprojectemployment);
				params.put(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS, escg_others);
				params.put(EmploymentSituationofCollegeGraduatesTable.ISNULL, isnull+"");
			
				EmploymentSituationofCollegeGraduatesDao escgDao = new EmploymentSituationofCollegeGraduatesDaoImpl();
				int result = escgDao.alterEmploymentSituationofCollegeGraduates(params, escg_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
