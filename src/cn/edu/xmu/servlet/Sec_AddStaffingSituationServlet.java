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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StaffingSituationDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StaffingSituationDaoImpl;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StaffingSituation;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StaffingSituationTable;

/**
 * Servlet implementation class AddStaffingSituationServlet
 */
@WebServlet("/AddStaffingSituationServlet")
public class Sec_AddStaffingSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStaffingSituationServlet() {
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
		String college = request.getParameter(StaffingSituationTable.SS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			String ss_condition1 = json.getString(StaffingSituationTable.SS_CONDITION1);
			String ss_condition2 = json.getString(StaffingSituationTable.SS_CONDITION2);
			
			String teachercount = json.getString(StaffingSituationTable.SS_TEACHERCOUNT);
			int ss_teachercount = -999;
			if(!teachercount.equals(""))
				ss_teachercount = Integer.valueOf(teachercount);
			
			String fulltimestaffcount = json.getString(StaffingSituationTable.SS_FULLTIMESTAFFCOUNT);
			int ss_fulltimestaffcount = -999;
			if(!fulltimestaffcount.equals(""))
				ss_fulltimestaffcount = Integer.valueOf(fulltimestaffcount);
			
			String facultycount = json.getString(StaffingSituationTable.SS_FACULTYCOUNT);
			int ss_facultycount = -999;
			if(!facultycount.equals(""))
				ss_facultycount = Integer.valueOf(facultycount);
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ss_condition1.equals("") || ss_condition2.equals("") || teachercount.equals("") || 
					fulltimestaffcount.equals("") || facultycount.equals("") )
				isnull = 1;
			
			int ss_serialnumber = serialnumber;
			String ss_college = college;
			String ss_comments = "";
			if(ss_condition1.equals("") && ss_condition2.equals("") && teachercount.equals("") && 
					fulltimestaffcount.equals("") && facultycount.equals("") )
			return;
			StaffingSituation ss = new StaffingSituation(ss_condition1,ss_condition2,ss_teachercount,
					ss_fulltimestaffcount, ss_facultycount, ss_serialnumber,ss_college,ss_comments,isnull);			
			StaffingSituationDao ssDao = new StaffingSituationDaoImpl();
			ssDao.addRecord(ss);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
