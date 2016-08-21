
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

import cn.edu.xmu.dao.TeacherPublishWritingDao;
import cn.edu.xmu.daoimpl.TeacherPublishWritingDaoImpl;
import cn.edu.xmu.entity.TeacherPublishWriting;
import cn.edu.xmu.table.TeacherPublishWritingTable;

@WebServlet("/Sec_AddTeacherPublishWritingServlet")
public class Sec_AddTeacherPublishWritingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeacherPublishWritingServlet() {
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
		String college = request.getParameter(TeacherPublishWritingTable.TPW_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result =0 ;
		
		try {
			JSONObject json = new JSONObject(data);
			
			String total = json.getString(TeacherPublishWritingTable.TPW_TOTAL);  
			Integer tpw_total = -1;
			if(!total.equals("")) tpw_total = Integer.parseInt(total);
			String monograph = json.getString(TeacherPublishWritingTable.TPW_MONOGRAPH);
			Integer tpw_monograph = -1;
			if(!monograph.equals("")) tpw_monograph = Integer.parseInt(monograph);
			String translation = json.getString(TeacherPublishWritingTable.TPW_TRANSLATION);
			Integer tpw_translation = -1;
			if(!translation.equals("")) tpw_translation = Integer.parseInt(translation);
			String compile = json.getString(TeacherPublishWritingTable.TPW_COMPILE); 
			Integer tpw_compile = -1;
			if(!compile.equals("")) tpw_compile = Integer.parseInt(compile);
			String other = json.getString(TeacherPublishWritingTable.TPW_OTHER); 
			Integer tpw_other = -1;
			if(!other.equals("")) tpw_other = Integer.parseInt(other);
			
			int tpw_isnull = 0;
			if("".equals(total)||"".equals(monograph)||"".equals(translation)||"".equals(compile)
					|| "".equals(other)  )
			{
				tpw_isnull = 1;
			}
			if ("".equals(total)&&"".equals(monograph)&&"".equals(translation)&&"".equals(compile)
					&& "".equals(other) )
			{
				result = -1;
				out.println(result);
				return;
			}
			TeacherPublishWriting teacherPublishWriting = new TeacherPublishWriting(
					tpw_total, tpw_monograph, tpw_translation, tpw_compile, tpw_other, college, serialnumber, tpw_isnull);
			
			TeacherPublishWritingDao teacherPublishWritingDao = new TeacherPublishWritingDaoImpl();
			result = teacherPublishWritingDao.addTeacherPublishWriting(teacherPublishWriting);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}


