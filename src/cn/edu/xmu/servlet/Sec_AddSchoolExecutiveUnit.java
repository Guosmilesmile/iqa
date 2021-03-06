package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddSchoolExecutiveUnit")
public class Sec_AddSchoolExecutiveUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddSchoolExecutiveUnit() {
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
		String college = request.getParameter(SchoolExecutiveUnitTable.SEU_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String seu_departmentname = json.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME);
			String seu_departmentnumber = json.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER);
			String seu_function = json.getString(SchoolExecutiveUnitTable.SEU_FUNCTION);
			String seu_bosshead = json.getString(SchoolExecutiveUnitTable.SEU_BOSSHEAD);
			int seu_serialnumber = serialnumber;
			String seu_college = college;
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(seu_departmentname.equals("")||seu_departmentnumber.equals("")||seu_function.equals("")||seu_bosshead.equals(""))
				isnull = 1;
			
			SchoolExecutiveUnit seu = new SchoolExecutiveUnit(seu_departmentname,
					seu_departmentnumber, seu_function, seu_bosshead, seu_serialnumber, seu_college, isnull);			
			
			SchoolExecutiveUnitDao seuDao = new SchoolExecutiveUnitDaoImpl();
			seuDao.addRecord(seu);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
