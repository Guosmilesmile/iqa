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

import cn.edu.xmu.dao.QualityEducationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.QualityEducationDaoimpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.QualityEducation;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.QualityEducationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_AddQualityEducationServlet
 */
@WebServlet("/Sec_AddQualityEducationServlet")
public class Sec_AddQualityEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddQualityEducationServlet() {
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
		String college = request.getParameter(QualityEducationTable.QE_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String diathesisdeveloping = json.getString(QualityEducationTable.QE_DIATHESISDEVELOPING);
			int qe_diathesisdeveloping = -999;
			if(!diathesisdeveloping.equals(""))
				qe_diathesisdeveloping = Integer.valueOf(diathesisdeveloping);
			
			String qualificationtraining = json.getString(QualityEducationTable.QE_QUALIFICATIONTRAINING);
			int qe_qualificationtraining = -999;
			if(!qualificationtraining.equals(""))
				qe_qualificationtraining = Integer.valueOf(qualificationtraining);
			
			String course = json.getString(QualityEducationTable.QE_COURSE);
			int qe_course = -999;
			if(!course.equals(""))
				qe_course = Integer.valueOf(course);
			
			String basecount = json.getString(QualityEducationTable.QE_BASECOUNT);
			int qe_basecount = -999;
			if(!basecount.equals(""))
				qe_basecount = Integer.valueOf(basecount);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(diathesisdeveloping.equals("") || qualificationtraining.equals("") || course.equals("") || 
					basecount.equals("")  )
				isnull = 1;
			
			int qe_serialnumber = serialnumber;
			String qe_college = college;
			String qe_comments = "";
			
			if(diathesisdeveloping.equals("") &&qualificationtraining.equals("") && course.equals("") && 
					basecount.equals("")  )
				return;
			QualityEducation qe = new QualityEducation(qe_diathesisdeveloping,
					qe_qualificationtraining, qe_course, qe_basecount,qe_serialnumber,qe_college,qe_comments,isnull);			
			QualityEducationDao qeDao = new QualityEducationDaoimpl();
			qeDao.addRecord(qe);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
