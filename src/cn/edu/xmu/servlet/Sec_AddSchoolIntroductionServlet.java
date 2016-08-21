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

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SchoolIntroductionDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SchoolIntroductionDaoimpl;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SchoolIntroduction;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SchoolIntroductionTable;

/**
 * Servlet implementation class Sec_AddSchoolIntroductionServlet
 */
@WebServlet("/Sec_AddSchoolIntroductionServlet")
public class Sec_AddSchoolIntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddSchoolIntroductionServlet() {
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
		String college = request.getParameter(SchoolIntroductionTable.SI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String si_schoolname = json.getString(SchoolIntroductionTable.SI_SCHOOLNAME);
			String si_schoolcode = json.getString(SchoolIntroductionTable.SI_SCHOOLCODE);
			String si_Englishname = json.getString(SchoolIntroductionTable.SI_ENGLISHNAME);
			String si_campustype = json.getString(SchoolIntroductionTable.SI_CAMPUSTYPE);
			String si_campusnature = json.getString(SchoolIntroductionTable.SI_CAMPUSNATURE);
			String si_host = json.getString(SchoolIntroductionTable.SI_HOST);
			String si_department = json.getString(SchoolIntroductionTable.SI_DEPARTMENT);
			String si_website = json.getString(SchoolIntroductionTable.SI_WEBSITE);
			String si_admissionsbatches = json.getString(SchoolIntroductionTable.SI_ADMISSIONSBATCHES);
			String si_educationstartyear = json.getString(SchoolIntroductionTable.SI_EDUCATIONSTARTYEAR);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(si_schoolname.equals("") || si_schoolcode.equals("") || si_Englishname.equals("") || 
					si_campustype.equals("") || si_campusnature.equals("") || si_host.equals("") ||
					si_department.equals("") || si_website.equals("") || si_admissionsbatches.equals("")|| 
					si_educationstartyear.equals("") )
				isnull = 1;
			
			int si_serialnumber = serialnumber;
			String si_college = college;
			String si_comments = "";
			
			if(si_schoolname.equals("") && si_schoolcode.equals("") && si_Englishname.equals("") && 
					si_campustype.equals("") && si_campusnature.equals("") && si_host.equals("") &&
					si_department.equals("") && si_website.equals("") && si_admissionsbatches.equals("")&& 
					si_educationstartyear.equals("") )
				return;
			SchoolIntroduction si = new SchoolIntroduction(si_schoolname,
					si_schoolcode, si_Englishname, si_campustype, si_campusnature, si_host, si_department,si_website,si_admissionsbatches,si_educationstartyear,si_serialnumber,si_college,si_comments,isnull);			
			SchoolIntroductionDao siDao = new SchoolIntroductionDaoimpl();
			siDao.addRecord(si);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
