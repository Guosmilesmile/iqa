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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StudentHomeDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StudentHomeDaoImpl;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StudentHome;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StudentHomeTable;

/**
 * Servlet implementation class Sec_AddStudentHomeServlet
 */
@WebServlet("/Sec_AddStudentHomeServlet")
public class Sec_AddStudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStudentHomeServlet() {
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
		//解码
		String college = request.getParameter(StudentHomeTable.SH_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String diningroomarea = json.getString(StudentHomeTable.SH_DININGROOMAREA);
			float sh_diningroomarea = -999;
			if (diningroomarea!=null && !"".equals(diningroomarea)) {
				sh_diningroomarea = Float.parseFloat(diningroomarea);
			}
			
			String diningrooncount = json.getString(StudentHomeTable.SH_DININGROONCOUNT);
			int sh_diningrooncount = -999;
			if(!diningrooncount.equals(""))
				sh_diningrooncount = Integer.valueOf(diningrooncount);
			
			
			String dormitoryarea = json.getString(StudentHomeTable.SH_DORMITORYAREA);
			float sh_dormitoryarea = -999;
			if (dormitoryarea!=null && !"".equals(dormitoryarea)) {
				sh_dormitoryarea = Float.parseFloat(dormitoryarea);
			}
			
			String dormitorycount = json.getString(StudentHomeTable.SH_DORMITORYCOUNT);
			int sh_dormitorycount = -999;
			if(!dormitorycount.equals(""))
				sh_dormitorycount = Integer.valueOf(dormitorycount);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(diningroomarea.equals("") || diningrooncount.equals("") || dormitoryarea.equals("") || 
					dormitorycount.equals("")  )
				isnull = 1;
			
			int sh_serialnumber = serialnumber;
			String sh_college = college;
			String sh_comments = "";
			if(diningroomarea.equals("") && diningrooncount.equals("") && dormitoryarea.equals("") && 
					dormitorycount.equals("")  )
				return;
			StudentHome sh = new StudentHome(sh_diningroomarea,
					sh_diningrooncount, sh_dormitoryarea,sh_dormitorycount, sh_serialnumber,sh_college,sh_comments,isnull);			
			StudentHomeDao shDao = new StudentHomeDaoImpl();
			shDao.addRecord(sh);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
