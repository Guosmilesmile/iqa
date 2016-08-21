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

import cn.edu.xmu.dao.BenkePunishDao;
import cn.edu.xmu.daoimpl.BenkePunishDaoImpl;
import cn.edu.xmu.entity.BenkePunish;
import cn.edu.xmu.table.BenkePunishTable;

/**
 * Servlet implementation class Sec_AddBenkePunish
 */
@WebServlet("/Sec_AddBenkePunish")
public class Sec_AddBenkePunish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddBenkePunish() {
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
		String college = request.getParameter(BenkePunishTable.BP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			
			String bp_college1 = json.getString(BenkePunishTable.BP_COLLEGE1);
			
			String bp_warning = json.getString(BenkePunishTable.BP_WARNING);
			String bp_demerit = json.getString(BenkePunishTable.BP_DEMERIT);
			String bp_probation = json.getString(BenkePunishTable.BP_PROBATION);
			String bp_expulsion = json.getString(BenkePunishTable.BP_EXPULSION);
			String totalmount = json.getString(BenkePunishTable.BP_TOTALMOUNT);
			int bp_totalmount = 0;
			if(!totalmount.equals("")&&null!=totalmount){
				bp_totalmount = Integer.parseInt(totalmount);
			}
			
			int bp_serialnumber = serialnumber;
			String bp_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(bp_college1.equals("")||bp_warning.equals("")||bp_demerit.equals("")||bp_probation.equals("")||bp_expulsion.equals("")||
					totalmount.equals(""))
				isnull = 1;
			
			
			BenkePunish bp = new BenkePunish(bp_college1, bp_warning, bp_demerit, bp_probation, bp_expulsion, 
					bp_totalmount, bp_serialnumber, bp_college, isnull);
			
			BenkePunishDao bpDao = new BenkePunishDaoImpl();
			bpDao.addRecord(bp);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
