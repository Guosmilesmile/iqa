package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ExpTeachCenterDao;
import cn.edu.xmu.daoimpl.ExpTeachCenterDaoImpl;
import cn.edu.xmu.entity.ExpTeachCenter;
import cn.edu.xmu.table.ExpTeachCenterTable;

/**
 * Servlet implementation class Sec_AddExpTeachCenter
 */
@WebServlet("/Sec_AddExpTeachCenter")
public class Sec_AddExpTeachCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddExpTeachCenter() {
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
		String college = request.getParameter(ExpTeachCenterTable.ETC_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String etc_expteachcentername = json.getString(ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME);
			
			String etc_subjectname = json.getString(ExpTeachCenterTable.ETC_SUBJECTNAME);
			String etc_subjectcode = json.getString(ExpTeachCenterTable.ETC_SUBJECTCODE);
			String levelnum = json.getString(ExpTeachCenterTable.ETC_LEVELNUM);
			int etc_levelnum = 0;
			if(!levelnum.equals(""))etc_levelnum = Integer.parseInt(levelnum);
			String etc_levelname = json.getString(ExpTeachCenterTable.ETC_LEVELNAME);
			
			String time = json.getString(ExpTeachCenterTable.ETC_STARTTIME);
			int etc_starttime = Integer.valueOf(time);
			int etc_serialnumber = serialnumber;
			String etc_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(etc_expteachcentername.equals("")||etc_subjectname.equals("")||etc_subjectcode.equals("")
					||levelnum.equals("")||etc_levelname.equals("")||time.equals(""))
				isnull = 1;
			
			
			ExpTeachCenter etc = new ExpTeachCenter(etc_expteachcentername, etc_subjectname, etc_subjectcode,
					etc_levelnum, etc_levelname, etc_starttime, etc_college, isnull);
			
			ExpTeachCenterDao etcDao = new ExpTeachCenterDaoImpl();
			etcDao.addRecord(etc);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
