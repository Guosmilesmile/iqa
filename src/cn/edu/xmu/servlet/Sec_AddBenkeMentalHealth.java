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

import cn.edu.xmu.dao.BenkeMentalHealthDao;
import cn.edu.xmu.daoimpl.BenkeMentalHealthDaoImpl;
import cn.edu.xmu.entity.BenkeMentalHealth;
import cn.edu.xmu.table.BenkeMentalHealthTable;

/**
 * Servlet implementation class Sec_AddBenkeMentalHealth
 */
@WebServlet("/Sec_AddBenkeMentalHealth")
public class Sec_AddBenkeMentalHealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddBenkeMentalHealth() {
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
		String college = request.getParameter(BenkeMentalHealthTable.BMH_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			
			String bmh_college1 = json.getString(BenkeMentalHealthTable.BMH_COLLEGE1);
			
			String inschoolamount = json.getString(BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT);
			int bmh_inschoolamount = 0;
			if(!inschoolamount.equals("")&&null!=inschoolamount){
				bmh_inschoolamount = Integer.parseInt(inschoolamount);
			}
			String bmh_health = json.getString(BenkeMentalHealthTable.BMH_HEALTH);
			
			int bmh_serialnumber = serialnumber;
			String bmh_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(bmh_college1.equals("")||inschoolamount.equals("")||bmh_health.equals(""))
				isnull = 1;
			
			
			BenkeMentalHealth bmh = new BenkeMentalHealth(bmh_college1, bmh_inschoolamount, bmh_health, bmh_serialnumber,
					bmh_college, isnull);
			
			BenkeMentalHealthDao bmhDao = new BenkeMentalHealthDaoImpl();
			bmhDao.addRecord(bmh);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
