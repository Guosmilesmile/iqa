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

import cn.edu.xmu.dao.StudentCenterDao;
import cn.edu.xmu.daoimpl.StudentCenterDaoImpl;
import cn.edu.xmu.entity.StudentCenter;
import cn.edu.xmu.table.StudentCenterTable;

@WebServlet("/Sec_AddStudentCenterServlet")
public class Sec_AddStudentCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStudentCenterServlet() {
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
		String college = request.getParameter(StudentCenterTable.SC_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String sc_project = json.getString(StudentCenterTable.SC_PROJECT); 
			String quantity = json.getString(StudentCenterTable.SC_QUANTITY); 
			Integer sc_quantity = -1;
			if(!"".equals(quantity)) sc_quantity = Integer.parseInt(quantity);
			String area = json.getString(StudentCenterTable.SC_AREA);
			Float sc_area = (float) -1.0;
			if(!"".equals(area)) sc_area = Float.parseFloat(area); 
			
			int sc_isnull = 0;
			if("".equals(sc_project)||"".equals(quantity)||"".equals(area)){
				sc_isnull = 1;
			}
			if("".equals(sc_project)&&"".equals(quantity)&&"".equals(area)){
				result = -1;
				out.println(result);
				return;
			}
			
			StudentCenter studentCenter = new StudentCenter(
					sc_project, sc_quantity,sc_area, college, serialnumber, sc_isnull);
			
			StudentCenterDao studentCenterDao = new StudentCenterDaoImpl();
			result = studentCenterDao.addStudentCenter(studentCenter);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}


