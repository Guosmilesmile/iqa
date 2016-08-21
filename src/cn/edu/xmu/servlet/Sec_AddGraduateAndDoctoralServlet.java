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

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.entity.GraduateAndDoctoral;
import cn.edu.xmu.table.GraduateAndDoctoralTable;

/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
@WebServlet("/Sec_AddGraduateAndDoctoralServlet")
public class Sec_AddGraduateAndDoctoralServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGraduateAndDoctoralServlet() {
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
		String college = request.getParameter("gd_college");
		//解码
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
			
		try {
			JSONObject json = new JSONObject(data);
			
			String gd_name = json.getString(GraduateAndDoctoralTable.GD_NAME);			
			String gd_code = json.getString(GraduateAndDoctoralTable.GD_CODE);			
			String gd_departmentname = json.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNAME);
			String gd_departmentnumber = json.getString( GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER);
			String gd_type = json.getString(GraduateAndDoctoralTable.GD_TYPE);
			
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(gd_name.equals("")||gd_code.equals("")||gd_departmentname.equals("")||gd_departmentnumber.equals("")||
					gd_type.equals(""))
				isnull = 1;
			if(gd_name.equals("")&&gd_code.equals("")&&gd_departmentname.equals("")&&gd_departmentnumber.equals("")&&
					gd_type.equals(""))
			{
				out.println(false);
				return;
			}
			int gd_serialnumber = serialnumber;
			String gd_college = college;
			String gd_comments = "";
			GraduateAndDoctoral gd = new GraduateAndDoctoral( gd_name, gd_code, gd_departmentname, 
					gd_departmentnumber, gd_type, gd_serialnumber, gd_college, gd_comments,isnull);
			
			GraduateAndDoctoralDao gdDao = new GraduateAndDoctoralDaoImpl();
			gdDao.addGraduateAndDoctoralRecord(gd);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
