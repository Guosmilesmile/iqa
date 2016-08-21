
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

import cn.edu.xmu.dao.TeacherPublishThesisDao;
import cn.edu.xmu.daoimpl.TeacherPublishThesisDaoImpl;
import cn.edu.xmu.entity.TeacherPublishThesis;
import cn.edu.xmu.table.TeacherPublishThesisTable;

@WebServlet("/Sec_AddTeacherPublishThesisServlet")
public class Sec_AddTeacherPublishThesisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeacherPublishThesisServlet() {
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
		String college = request.getParameter(TeacherPublishThesisTable.TPT_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		
		try {
			JSONObject json = new JSONObject(data);
			
			String total = json.getString(TeacherPublishThesisTable.TPT_TOTAL); 
			Integer tpt_total = -1;
			if(!total.equals("")) tpt_total = Integer.parseInt(total);
			String sci = json.getString(TeacherPublishThesisTable.TPT_SCI); 
			Integer tpt_sci = -1;
			if(!sci.equals("")) tpt_sci = Integer.parseInt(sci);
			String ssci = json.getString(TeacherPublishThesisTable.TPT_SSCI); 
			Integer tpt_ssci = -1;
			if(!ssci.equals("")) tpt_ssci = Integer.parseInt(ssci);
			String ei = json.getString(TeacherPublishThesisTable.TPT_EI); 
			Integer tpt_ei = -1;
			if(!ei.equals("")) tpt_ei = Integer.parseInt(ei);
			String istp = json.getString(TeacherPublishThesisTable.TPT_ISTP); 
			Integer tpt_istp = -1;
			if(!istp.equals("")) tpt_istp = Integer.parseInt(istp);
			String domesic = json.getString(TeacherPublishThesisTable.TPT_DOMESIC); 
			Integer tpt_domesic = -1;
			if(!domesic.equals("")) tpt_domesic = Integer.parseInt(domesic);
			int tpt_isnull = 0;
			if("".equals(total)||"".equals(sci)||"".equals(ssci)||"".equals(ei)
					|| "".equals(istp) || "".equals(domesic) )
			{
				tpt_isnull = 1;
			}
			if ("".equals(total)&&"".equals(sci)&&"".equals(ssci)&&"".equals(ei)
					&& "".equals(istp) && "".equals(domesic) )
			{
				result = -1;
				out.println(result);
				return;
			}
			TeacherPublishThesis teacherPublishThesis = new TeacherPublishThesis(
					tpt_total, tpt_sci, tpt_ssci, tpt_ei, tpt_istp, tpt_domesic, college, serialnumber,tpt_isnull);
			
			TeacherPublishThesisDao teacherPublishThesisDao = new TeacherPublishThesisDaoImpl();
			result = teacherPublishThesisDao.addTeacherPublishThesis(teacherPublishThesis);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}


