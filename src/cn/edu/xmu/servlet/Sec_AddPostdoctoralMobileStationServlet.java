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


import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.daoimpl.PostdoctoralMobileStationDaoImpl;
import cn.edu.xmu.entity.PostdoctoralMobileStation;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;

/*
 * 表4-1-2  博士后流动站   (时点)
 */
@WebServlet("/Sec_AddPostdoctoralMobileStationServlet")
public class Sec_AddPostdoctoralMobileStationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
	public Sec_AddPostdoctoralMobileStationServlet()
	{
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
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		String college = request.getParameter("pms_college");
		//解码
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length()-1);
		try{
			JSONObject json = new JSONObject(data);
			
			String pms_departmentnumber = json.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER);
			String pms_departmentname = json.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME);
			String pms_stationname = json.getString(PostdoctoralMobileStationTable.PMS_STATIONNAME);
			int pms_serialnumber = serialnumber;
			String pms_college = college;
			String pms_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(pms_departmentnumber.equals("")||pms_departmentname.equals("")||pms_stationname.equals(""))
				isnull = 1;
			if(pms_departmentnumber.equals("")&&pms_departmentname.equals("")&&pms_stationname.equals(""))
			{
				out.print(false);
				return;
			}
			PostdoctoralMobileStation pms = new PostdoctoralMobileStation( pms_departmentnumber,
					pms_departmentname, pms_stationname, pms_serialnumber, pms_college, pms_comments,isnull);
			
			PostdoctoralMobileStationDao pmsDao = new PostdoctoralMobileStationDaoImpl();
			pmsDao.addPostdoctoralMobileStationRecord(pms);
			out.print(true);
		}catch(JSONException e){
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
