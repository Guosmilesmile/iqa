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

import cn.edu.xmu.dao.StartClassDao;
import cn.edu.xmu.daoimpl.StartClassDaoImpl;
import cn.edu.xmu.table.StartClassTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateStartClassServlet")
public class Sec_UpdateStartClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStartClassServlet() {
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
					  
					  int sc_id = jsons.getJSONObject(i).getInt(StartClassTable.SC_ID);					  
					  String sc_comments = jsons.getJSONObject(i).getString(StartClassTable.SC_COMMENTS);//审核意见
									
					    Map<String,String> params= new HashMap<String, String>();
					    params.put(StartClassTable.SC_ID,sc_id+"");												    
						params.put(StartClassTable.SC_COMMENTS,sc_comments);//审核意见					    					    
					    				
					StartClassDao startClassDao = new StartClassDaoImpl();
					int result = startClassDao.alterStartClass(params, sc_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						    			    
                int sc_id = json.getInt(StartClassTable.SC_ID);
			    
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
						 || "".equals(sc_teachernumber) || "".equals(sc_teachertitle) || "".equals(sc_studentnum) || "".equals(sc_isenglish) || "".equals(sc_website) || "".equals( sc_teachmaterial) || "".equals(sc_materialspecies) 
						 || "".equals(sc_ismagong) || "".equals( sc_isstandard) || "".equals( sc_foreignmaterial) )
				{
					sc_isnull = 1;
				}
			      
				if(!"".equals( sc_foreignmaterial)&&!"无".equals( sc_foreignmaterial)){
					if("".equals( sc_m_name) || "".equals(sc_m_auther) || "".equals(sc_m_publisher) || "".equals(sc_m_country) || "".equals( m_publishyear)){
						sc_isnull = 1;
					}
				}
				
			  	String sc_comments = json.getString(StartClassTable.SC_COMMENTS);//审核意见
										
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(StartClassTable.SC_ID,sc_id+"");
				   params.put(StartClassTable.SC_NUMBER,sc_number); //开课号
				   params.put(StartClassTable.SC_COURSENUM,sc_coursenum); //课程号
				   params.put(StartClassTable.SC_COURSECATEGORY,sc_coursecategory); //课程类别
					params.put(StartClassTable.SC_CAMPUS,sc_campus); //校区
					params.put(StartClassTable.SC_TOTALHOUR,sc_totalhour+""); //总学时
					params.put(StartClassTable.SC_TOTALCREDIT,sc_totalcredit+""); //学分
					params.put(StartClassTable.SC_EVALUATIONMODE,sc_evaluationmode); //考核方式
					params.put(StartClassTable.SC_TEACHOBJECT,sc_teachobject); //授课对象
					params.put(StartClassTable.SC_ARRANGE,sc_arrange); //安排情况
					params.put(StartClassTable.SC_YEARANDSEMESTER,sc_yearandsemester); //学年学期
					params.put(StartClassTable.SC_COLLEGENAME,sc_collegename ); //授课院
					params.put(StartClassTable.SC_COURSENAME,sc_coursename); //课程名称
					params.put(StartClassTable.SC_TEACHER,sc_teacher); //授课教师
					params.put(StartClassTable.SC_ISOUTSIDETEACHER,sc_isoutsideteacher); //是否校外专家
					params.put(StartClassTable.SC_TEACHERNUMBER,sc_teachernumber); //授课教师工号
					params.put(StartClassTable.SC_TEACHERTITLE,sc_teachertitle); //职称
					params.put(StartClassTable.SC_STUDENTNUM,sc_studentnum+""); //本科学生数
					params.put(StartClassTable.SC_ISENGLISH,sc_isenglish); //英语授课情况
					params.put(StartClassTable.SC_WEBSITE,sc_website); //网络教学平台网站
					params.put(StartClassTable.SC_TEACHMATERIAL,sc_teachmaterial); //教材使用情况
					params.put(StartClassTable.SC_MATERIALSPECIES,sc_materialspecies+""); //使用教材种数
					params.put(StartClassTable.SC_ISMAGONG,sc_ismagong); //是否使用马工教材
					params.put(StartClassTable.SC_ISSTANDARD,sc_isstandard); //是否使用规划教材
					params.put(StartClassTable.SC_FOREIGNMATERIAL,sc_foreignmaterial); //境外教材使用情况
					params.put(StartClassTable.SC_M_NAME,sc_m_name); //教材名称
					params.put(StartClassTable.SC_M_AUTHER,sc_m_auther);//作者
					params.put(StartClassTable.SC_M_PUBLISHER,sc_m_publisher); //出版社
				    params.put(StartClassTable.SC_M_COUNTRY,sc_m_country); //所属国家
					params.put(StartClassTable.SC_M_PUBLISHYEAR,sc_m_publishyear+""); //出版年						    
				  	params.put(StartClassTable.SC_COMMENTS,sc_comments);//审核意见		
				  	params.put(StartClassTable.SC_ISNULL,sc_isnull+"");
				  	
			    StartClassDao startClassDao = new StartClassDaoImpl();
			    int result = startClassDao.alterStartClass(params, sc_id+"");
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
