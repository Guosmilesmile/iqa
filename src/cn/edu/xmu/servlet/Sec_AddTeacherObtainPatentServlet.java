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

import cn.edu.xmu.dao.TeacherObtainPatentDao;
import cn.edu.xmu.daoimpl.TeacherObtainPatentDaoImpl;
import cn.edu.xmu.entity.TeacherObtainPatent;
import cn.edu.xmu.table.TeacherObtainPatentTable;

@WebServlet("/Sec_AddTeacherObtainPatentServlet")
public class Sec_AddTeacherObtainPatentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeacherObtainPatentServlet() {
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
		String college = request.getParameter(TeacherObtainPatentTable.TOP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String total = json.getString(TeacherObtainPatentTable.TOP_TOTAL);  
			Integer top_total = -1;
			if(!total.equals("")) top_total = Integer.parseInt(total);
			String invention = json.getString(TeacherObtainPatentTable.TOP_INVENTION); 
			Integer top_invention = -1;
			if(!invention.equals("")) top_invention = Integer.parseInt(invention);
			String utilitymodel = json.getString(TeacherObtainPatentTable.TOP_UTILITYMODEL); 
			Integer top_utilitymodel = -1;
			if(!utilitymodel.equals("")) top_utilitymodel = Integer.parseInt(utilitymodel);
				
			int top_isnull = 0;
			if("".equals(total)||"".equals(invention)||"".equals(utilitymodel))
			{
				top_isnull = 1;
			}
			if ("".equals(total)&&"".equals(invention)&&"".equals(utilitymodel))
			{
				result = -1;
				out.println(result);
				return;
			}
			TeacherObtainPatent teacherObtainPatent = new TeacherObtainPatent(
					top_total, top_invention, top_utilitymodel, college, serialnumber,top_isnull);
			
			TeacherObtainPatentDao teacherObtainPatentDao = new TeacherObtainPatentDaoImpl();
			result = teacherObtainPatentDao.addTeacherObtainPatent(teacherObtainPatent);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}


