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

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.SchoolAddressDao;
import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.daoimpl.SchoolAddressDaoImpl;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.table.SchoolAddressTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SuperMajorTable;

/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateSchoolAddress")
public class Sec_UpdateSchoolAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateSchoolAddress() {
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
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(SchoolAddressTable.SA_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String sa_id = json.getString(SchoolAddressTable.SA_ID);
			String sa_name = json.getString(SchoolAddressTable.SA_NAME);
			String sa_address = json.getString(SchoolAddressTable.SA_ADDRESS);
			String sa_comments = json.getString(SchoolAddressTable.SA_COMMENTS);
			
			int isnull=0;
			if("".equals(sa_name)||"".equals(sa_address)){
				isnull=1;
			}
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(SchoolAddressTable.SA_ID,  sa_id);
			params.put(SchoolAddressTable.SA_NAME, sa_name);
			params.put(SchoolAddressTable.SA_ADDRESS, sa_address);
			params.put(SchoolAddressTable.SA_COMMENTS, sa_comments);
			params.put("isnull", isnull+"");
			//params.put(SchoolAddressTable.SA_COLLEGE, college);
			
			SchoolAddressDao schoolAddressDao = new SchoolAddressDaoImpl();
			int result = schoolAddressDao.alterSchoolAddress(params, sa_id);
			
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
