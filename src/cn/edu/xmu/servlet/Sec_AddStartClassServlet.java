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

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.entity.StartClass;
import cn.edu.xmu.table.StartClassTable;

/**
 * Servlet implementation class AddStartClassServlet
 */
@WebServlet("/Sec_AddStartClassServlet")
public class Sec_AddStartClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStartClassServlet() {
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
						    
			    String sc_number = json.getString(StartClassTable.SC_NUMBER); //开课号
			    String sc_coursenum = json.getString(StartClassTable.SC_COURSENUM); //课程号
				String sc_coursecategory = json.getString(StartClassTable.SC_COURSECATEGORY); //课程类别
				String sc_campus = json.getString(StartClassTable.SC_CAMPUS); //校区
				String totalhour = json.getString(StartClassTable.SC_TOTALHOUR); //总学时
				Integer sc_totalhour = -1;
				if(!"".equals(totalhour)) sc_totalhour = Integer.parseInt(totalhour);
				String totalcredit = json.getString(StartClassTable.SC_TOTALCREDIT); //学分
				Float sc_totalcredit = (float) -1.0;
				if(!"".equals(totalcredit)) sc_totalcredit = Float.parseFloat(totalcredit);
				String sc_evaluationmode = json.getString(StartClassTable.SC_EVALUATIONMODE); //考核方式
				String sc_teachobject = json.getString(StartClassTable.SC_TEACHOBJECT); //授课对象
				String sc_arrange = json.getString(StartClassTable.SC_ARRANGE); //安排情况
				String sc_yearandsemester = json.getString(StartClassTable.SC_YEARANDSEMESTER); //学年学期
				String sc_collegename = json.getString(StartClassTable.SC_COLLEGENAME); //授课院
				
				String sc_coursename = json.getString(StartClassTable.SC_COURSENAME); //课程名称
				String sc_teacher = json.getString(StartClassTable.SC_TEACHER); //授课教师
				String sc_isoutsideteacher = json.getString(StartClassTable.SC_ISOUTSIDETEACHER); //是否校外专家
				String sc_teachernumber = json.getString(StartClassTable.SC_TEACHERNUMBER); //授课教师工号
				String sc_teachertitle = json.getString(StartClassTable.SC_TEACHERTITLE); //职称
				String studentnum = json.getString(StartClassTable.SC_STUDENTNUM); //本科学生数
				Integer sc_studentnum = -1;
				if(!"".equals(studentnum)) sc_studentnum = Integer.parseInt(studentnum);
				String sc_isenglish = json.getString(StartClassTable.SC_ISENGLISH); //英语授课情况
				String sc_website = json.getString(StartClassTable.SC_WEBSITE); //网络教学平台网站
				String sc_teachmaterial = json.getString(StartClassTable.SC_TEACHMATERIAL); //教材使用情况
				String materialspecies = json.getString(StartClassTable.SC_MATERIALSPECIES); //使用教材种数
				Integer sc_materialspecies = -1;
				if(!"".equals(materialspecies)) sc_materialspecies = Integer.parseInt(materialspecies);
				String sc_ismagong = json.getString(StartClassTable.SC_ISMAGONG); //是否使用马工教材
				String sc_isstandard = json.getString(StartClassTable.SC_ISSTANDARD); //是否使用规划教材
				String sc_foreignmaterial = json.getString(StartClassTable.SC_FOREIGNMATERIAL); //境外教材使用情况
				String sc_m_name = json.getString(StartClassTable.SC_M_NAME); //教材名称
				String sc_m_auther = json.getString(StartClassTable.SC_M_AUTHER);//作者
				String sc_m_publisher = json.getString(StartClassTable.SC_M_PUBLISHER); //出版社
			    String sc_m_country = json.getString(StartClassTable.SC_M_COUNTRY); //所属国家
				String m_publishyear = json.getString(StartClassTable.SC_M_PUBLISHYEAR); //出版年
				Integer sc_m_publishyear = -1;
				if(!"".equals(m_publishyear)) sc_m_publishyear = Integer.parseInt(m_publishyear);
				
				int sc_isnull = 0;
				if("".equals(sc_number) || "".equals(sc_coursenum) || "".equals(sc_coursecategory) || "".equals(sc_campus) || "".equals(totalhour) || "".equals(totalcredit) || "".equals(sc_evaluationmode)
						 || "".equals(	sc_teachobject) || "".equals( sc_arrange) || "".equals( sc_yearandsemester) || "".equals( sc_collegename) || "".equals( sc_coursename) || "".equals( sc_teacher) || "".equals( sc_isoutsideteacher) 
						 || "".equals(sc_teachernumber) || "".equals(sc_teachertitle) || "".equals(studentnum) || "".equals(sc_isenglish) || "".equals(sc_website) || "".equals( sc_teachmaterial) || "".equals(materialspecies) 
						 || "".equals(sc_ismagong) || "".equals( sc_isstandard) || "".equals( sc_foreignmaterial) )
				{
					sc_isnull = 1;
			    }
				if(!"".equals( sc_foreignmaterial)&&!"无".equals( sc_foreignmaterial)){
					if("".equals( sc_m_name) || "".equals(sc_m_auther) || "".equals(sc_m_publisher) || "".equals(sc_m_country) || "".equals( m_publishyear)){
						sc_isnull = 1;
					}
				}		
				if ("".equals(sc_number) && "".equals(sc_coursenum) && "".equals(sc_coursecategory) && "".equals(sc_campus) && "".equals(totalhour) && "".equals(totalcredit) && "".equals(sc_evaluationmode)
						 && "".equals(	sc_teachobject) && "".equals( sc_arrange) && "".equals( sc_yearandsemester) && "".equals( sc_collegename) && "".equals( sc_coursename) && "".equals( sc_teacher) && "".equals( sc_isoutsideteacher) 
						 && "".equals(sc_teachernumber) && "".equals(sc_teachertitle) && "".equals(studentnum) && "".equals(sc_isenglish) && "".equals(sc_website) && "".equals( sc_teachmaterial) && "".equals(materialspecies) 
						 && "".equals(sc_ismagong) && "".equals( sc_isstandard) && "".equals( sc_foreignmaterial)&&"".equals( sc_m_name) && "".equals(sc_m_auther) && "".equals(sc_m_publisher) && "".equals(sc_m_country) && "".equals( m_publishyear) )
				{
																	
					result = -1;
					out.println(result);
					return;
				}
				
				String sc_college = request.getParameter(StartClassTable.SC_COLLEGE); //授课院
				sc_college = URLDecoder.decode(sc_college,"UTF-8");
				
				
			StartClass startClass = new StartClass(
					sc_number, sc_coursenum, sc_coursecategory, sc_campus, sc_totalhour, sc_totalcredit, sc_evaluationmode,
					sc_teachobject, sc_arrange, sc_yearandsemester, sc_collegename, sc_coursename, sc_teacher, sc_isoutsideteacher, 
					sc_teachernumber, sc_teachertitle, sc_studentnum, sc_isenglish, sc_website, sc_teachmaterial, sc_materialspecies, 
					sc_ismagong, sc_isstandard, sc_foreignmaterial, sc_m_name, sc_m_auther, sc_m_publisher, sc_m_country, sc_m_publishyear, 					
					sc_college,serialnumber, sc_isnull);

			StartClassDao startClassDao = new StartClassDaoImpl();
			result = startClassDao.addStartClass(startClass);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
