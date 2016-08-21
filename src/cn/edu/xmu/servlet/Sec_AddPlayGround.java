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

import cn.edu.xmu.dao.PlayGroundDao;
import cn.edu.xmu.daoimpl.PlayGroundDaoImpl;
import cn.edu.xmu.entity.PlayGround;
import cn.edu.xmu.table.PlayGroundTable;

/**
 * Servlet implementation class Sec_AddPlayGround
 */
@WebServlet("/Sec_AddPlayGround")
public class Sec_AddPlayGround extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddPlayGround() {
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
		String college = request.getParameter(PlayGroundTable.PG_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			
			//int pg_id = Integer.parseInt(json.getString(PlayGroundTable.PG_ID));
			String pg_campus = json.getString(PlayGroundTable.PG_CAMPUS);
			
			String pg_groundname = json.getString(PlayGroundTable.PG_GROUNDNAME);
			String amount = json.getString(PlayGroundTable.PG_AMOUNT);
			int pg_amount=0;
			if(!amount.equals("")&&null!=amount){
				pg_amount = Integer.parseInt(amount);
			}
			String pg_indoorarea = json.getString(PlayGroundTable.PG_INDOORAREA);
			String pg_outdoorarea = json.getString(PlayGroundTable.PG_OUTDOORAREA);
			String pg_totalarea = json.getString(PlayGroundTable.PG_TOTALAREA);
			
			int pg_serialnumber = serialnumber;
			String pg_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(pg_campus.equals("")||pg_groundname.equals("")||amount.equals("")||pg_indoorarea.equals("")||pg_outdoorarea.equals("")||
					pg_totalarea.equals(""))
				isnull = 1;
			
			
			PlayGround pg = new PlayGround(pg_campus, pg_groundname, pg_amount, pg_indoorarea, pg_outdoorarea, 
					pg_totalarea, pg_serialnumber, pg_college, isnull);
			
			PlayGroundDao pgDao = new PlayGroundDaoImpl();
			pgDao.addRecord(pg);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
