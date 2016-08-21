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

import cn.edu.xmu.dao.GraduationSituationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.GraduationSituationDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.GraduationSituation;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.GraduationSituationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_AddGraduationSituationServlet
 */
@WebServlet("/Sec_AddGraduationSituationServlet")
public class Sec_AddGraduationSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGraduationSituationServlet() {
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
		//解码
		String college = request.getParameter(GraduationSituationTable.GS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String gs_importcollege = json.getString(GraduationSituationTable.GS_IMPORTCOLLEGE);
			String gs_major = json.getString(GraduationSituationTable.GS_MAJOR);
			String gs_majorcode = json.getString(GraduationSituationTable.GS_MAJORCODE);
			String eductionalsystme = json.getString(GraduationSituationTable.GS_EDUCTIONALSYSTME);
			int gs_eductionalsystme = -999;
			if(!eductionalsystme.equals(""))
				gs_eductionalsystme = Integer.valueOf(eductionalsystme);
			
			String gs_degreecategory = json.getString(GraduationSituationTable.GS_DEGREECATEGORY);
			
			String graduationnumber = json.getString(GraduationSituationTable.GS_GRADUATIONNUMBER);
			int gs_graduationnumber = -999;
			if(!graduationnumber.equals(""))
				gs_graduationnumber = Integer.valueOf(graduationnumber);
			
			String total = json.getString(GraduationSituationTable.GS_TOTAL);
			int gs_total = -999;
			if(!total.equals(""))
				gs_total = Integer.valueOf(total);
			
			String degreeyes = json.getString(GraduationSituationTable.GS_DEGREEYES);
			int gs_degreeyes = -999;
			if(!degreeyes.equals(""))
				gs_degreeyes = Integer.valueOf(degreeyes);
			
			String degreeno = json.getString(GraduationSituationTable.GS_DEGREENO);
			int gs_degreeno = -999;
			if(!degreeno.equals(""))
				gs_degreeno = Integer.valueOf(degreeno);
			
			String numberofcompletion = json.getString(GraduationSituationTable.GS_NUMBEROFCOMPLETION);
			int gs_numberofcompletion = -999;
			if(!numberofcompletion.equals(""))
				gs_numberofcompletion = Integer.valueOf(numberofcompletion);
			
			String numberofincomplete = json.getString(GraduationSituationTable.GS_NUMBEROFINCOMPLETE);
			int gs_numberofincomplete = -999;
			if(!numberofincomplete.equals(""))
				gs_numberofincomplete = Integer.valueOf(numberofincomplete);
			
			String minorcertificatecount = json.getString(GraduationSituationTable.GS_MINORCERTIFICATECOUNT);
			int gs_minorcertificatecount = -999;
			if(!minorcertificatecount.equals(""))
				gs_minorcertificatecount = Integer.valueOf(minorcertificatecount);
			
			String minordegreecount = json.getString(GraduationSituationTable.GS_MINORDEGREECOUNT);
			int gs_minordegreecount = -999;
			if(!minordegreecount.equals(""))
				gs_minordegreecount = Integer.valueOf(minordegreecount);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(gs_importcollege.equals("") || gs_major.equals("") || gs_majorcode.equals("") || 
					eductionalsystme.equals("") || gs_degreecategory.equals("") || graduationnumber.equals("") ||
					total.equals("") || degreeyes.equals("") || degreeno.equals("") || numberofcompletion.equals("")|| 
					numberofincomplete.equals("")|| minorcertificatecount.equals("")|| minordegreecount.equals(""))
				isnull = 1;
			
			int gs_serialnumber = serialnumber;
			String gs_college = college;
			String gs_comments = "";
			if(gs_importcollege.equals("") && gs_major.equals("") && gs_majorcode.equals("") && 
					eductionalsystme.equals("") && gs_degreecategory.equals("") && graduationnumber.equals("")&&
					total.equals("") && degreeyes.equals("") && degreeno.equals("") && numberofcompletion.equals("")&& 
					numberofincomplete.equals("")&& minorcertificatecount.equals("")&& minordegreecount.equals(""))
				return;
			GraduationSituation gs = new GraduationSituation(gs_importcollege,
					gs_major, gs_majorcode,gs_eductionalsystme, gs_degreecategory,gs_graduationnumber,gs_total,
					gs_degreeyes,gs_degreeno,gs_numberofcompletion,gs_numberofincomplete,gs_minorcertificatecount,
					gs_minordegreecount,gs_serialnumber,gs_college,gs_comments,isnull);			
			GraduationSituationDao gsDao = new GraduationSituationDaoImpl();
			gsDao.addRecord(gs);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
