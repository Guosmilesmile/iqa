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

import cn.edu.xmu.dao.SchoolAddressDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.SchoolAddressDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.SchoolAddress;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.SchoolAddressTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddSchoolAddress")
public class Sec_AddSchoolAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddSchoolAddress() {
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
		System.out.println(serialnumber);
		//解码
		String college = request.getParameter(SchoolAddressTable.SA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		System.out.println(college);
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
		
			String sa_name = json.getString(SchoolAddressTable.SA_NAME);
			String sa_address = json.getString(SchoolAddressTable.SA_ADDRESS);
			int isnull=0;
			if("".equals(sa_name)||"".equals(sa_address)){
				isnull=1;
			}
			SchoolAddress schoolAddress = new SchoolAddress(sa_name, sa_address, serialnumber, null, college, null,isnull);
			
			SchoolAddressDao schoolAddressDao = new SchoolAddressDaoImpl();
			schoolAddressDao.addRecord(schoolAddress);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
