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

import cn.edu.xmu.dao.EmploymentSituationofCollegeGraduatesDao;
import cn.edu.xmu.daoimpl.EmploymentSituationofCollegeGraduatesDaoImpl;
import cn.edu.xmu.entity.EmploymentSituationofCollegeGraduates;
import cn.edu.xmu.table.EmploymentSituationofCollegeGraduatesTable;

/**
 * Servlet implementation class Sec_AddEmploymentSituationofCollegeGraduates
 */
@WebServlet("/AddEmploymentSituationofCollegeGraduates")
public class Sec_AddEmploymentSituationofCollegeGraduates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddEmploymentSituationofCollegeGraduates() {
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
		String college = request.getParameter(EmploymentSituationofCollegeGraduatesTable.ESCG_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);

			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_RECOMMENDGRADUATE);
			int escg_recommendgraduate = -999;
			if(!temp.equals(""))
				escg_recommendgraduate = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMIN);
			int escg_postgraduateexamin = -999;
			if(!temp.equals(""))
				escg_postgraduateexamin = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_POSTGRADUATEEXAMOUT);
			int escg_postgraduateexamout = -999;
			if(!temp.equals(""))
				escg_postgraduateexamout = Integer.valueOf(temp);
			
			int escg_postgraduateexamsum = -999;
			if(escg_postgraduateexamin!=-999 && escg_postgraduateexamout!=-999)
				escg_postgraduateexamsum = escg_postgraduateexamin+escg_postgraduateexamout;
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_STUDYABROAD);
			int escg_studyabroad = -999;
			if(!temp.equals(""))
				escg_studyabroad = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_GOV);
			int escg_gov = -999;
			if(!temp.equals(""))
				escg_gov = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_INSTITUTION);
			int escg_institution = -999;
			if(!temp.equals(""))
				escg_institution = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_ENTERPRISE);
			int escg_enterprise = -999;
			if(!temp.equals(""))
				escg_enterprise = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_TROOPS);
			int escg_troops = -999;
			if(!temp.equals(""))
				escg_troops = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_FLEXIBLEEMPLOYMENT);
			int escg_flexibleemployment = -999;
			if(!temp.equals(""))
				escg_flexibleemployment = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCE_ENTRANCE);
			int esce_entrance = -999;
			if(!temp.equals(""))
				esce_entrance = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_NATIONALLOCALPROJECTEMPLOYMENT);
			int escg_nationallocalprojectemployment = -999;
			if(!temp.equals(""))
				escg_nationallocalprojectemployment = Integer.valueOf(temp);
			
			temp = json.getString(EmploymentSituationofCollegeGraduatesTable.ESCG_OTHERS);
			int escg_others = -999;
			if(!temp.equals(""))
				escg_others = Integer.valueOf(temp);
			
			int escg_employmentsum = -999;
			if(escg_gov!=-999 && escg_institution!=-999 && escg_enterprise!=-999 && escg_troops!=-999 && escg_flexibleemployment!=-999 && esce_entrance!=-999 && escg_nationallocalprojectemployment!=-999 && escg_others!=-999)
				escg_employmentsum = escg_gov+escg_institution+escg_enterprise+escg_troops+escg_flexibleemployment+esce_entrance+escg_nationallocalprojectemployment+escg_others;
			
			int escg_serialnumber = serialnumber;
			String escg_college = college;
			String escg_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(escg_postgraduateexamin==-999 || escg_postgraduateexamout==-999|| escg_gov==-999 || escg_institution==-999 || escg_enterprise==-999 || escg_troops==-999 || escg_flexibleemployment==-999 || esce_entrance==-999 || escg_nationallocalprojectemployment==-999 || escg_others==-999 || escg_studyabroad==-999 || escg_recommendgraduate==-999)
				isnull = 1;
			
			if(escg_postgraduateexamin==-999 && escg_postgraduateexamout==-999&& escg_gov==-999 && escg_institution==-999 && escg_enterprise==-999 && escg_troops==-999 && escg_flexibleemployment==-999 && esce_entrance==-999 && escg_nationallocalprojectemployment==-999 && escg_others==-999 && escg_studyabroad==-999 && escg_recommendgraduate==-999)
				return;
			
			EmploymentSituationofCollegeGraduates escg = new EmploymentSituationofCollegeGraduates(
					escg_recommendgraduate, escg_postgraduateexamsum,
					escg_postgraduateexamin, escg_postgraduateexamout,
					escg_studyabroad, escg_employmentsum, escg_gov,
					escg_institution, escg_enterprise, escg_troops,
					escg_flexibleemployment, esce_entrance,
					escg_nationallocalprojectemployment, escg_others,
					escg_serialnumber,  escg_college, escg_comments, isnull);			
			
			EmploymentSituationofCollegeGraduatesDao escgDao = new EmploymentSituationofCollegeGraduatesDaoImpl();
			escgDao.addRecord(escg);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
