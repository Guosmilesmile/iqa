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

import cn.edu.xmu.dao.GoInterConferDao;
import cn.edu.xmu.daoimpl.GoInterConferDaoImpl;
import cn.edu.xmu.entity.GoInterConfer;
import cn.edu.xmu.table.GoInterConferTable;

/**
 * Servlet implementation class Sec_AddGoInterConfer
 */
@WebServlet("/Sec_AddGoInterConfer")
public class Sec_AddGoInterConfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGoInterConfer() {
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
		String college = request.getParameter(GoInterConferTable.GIC_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			
			String gic_college1 = json.getString(GoInterConferTable.GIC_COLLEGE1);
			
			String gic_major = json.getString(GoInterConferTable.GIC_MAJOR);
			String gic_grade = json.getString(GoInterConferTable.GIC_GRADE);
			String gic_stunum = json.getString(GoInterConferTable.GIC_STUNUM);
			String gic_name = json.getString(GoInterConferTable.GIC_NAME);
			String gic_intername = json.getString(GoInterConferTable.GIC_INTERNAME);
			String gic_paperortitle = json.getString(GoInterConferTable.GIC_PAPERORTITLE);
			
			int gic_serialnumber = serialnumber;
			String gic_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(gic_college1.equals("")||gic_major.equals("")||gic_grade.equals("")||gic_stunum.equals("")||gic_name.equals("")||
					gic_intername.equals("")||gic_paperortitle.equals(""))
				isnull = 1;
			
			
			GoInterConfer gic = new GoInterConfer(gic_college1, gic_major, gic_grade, gic_stunum, gic_name,
					gic_intername, gic_paperortitle, gic_serialnumber, gic_college, isnull);
			
			GoInterConferDao gicDao = new GoInterConferDaoImpl();
			gicDao.addRecord(gic);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
