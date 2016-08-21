package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.table.ManagerInfoTableBack;

/*
 * 3-3 管理人员基本信息
 * chunfeng
 */
@WebServlet("/UpdateManagerInfoServletBack")
public class UpdateManagerInfoServletBack extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateManagerInfoServletBack() {
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
		String serialnumber  = request.getParameter("serialnumber");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		Map<String,String> params= new HashMap<String, String>();
		
		try {
			JSONObject json = new JSONObject(data);
			String mi_id = json.getString(ManagerInfoTableBack.MI_ID);
			
			String mi_name = json.getString( ManagerInfoTableBack.MI_NAME);
			params.put(ManagerInfoTableBack.MI_NAME,mi_name);
			String mi_teacherid = json.getString( ManagerInfoTableBack.MI_MANAGERID);
			params.put(ManagerInfoTableBack.MI_MANAGERID,mi_teacherid);
			String mi_sex = json.getString( ManagerInfoTableBack.MI_SEX);
			params.put(ManagerInfoTableBack.MI_SEX,mi_sex);
			String mi_birthday = json.getString( ManagerInfoTableBack.MI_BIRTHDAY);
			params.put(ManagerInfoTableBack.MI_BIRTHDAY,mi_birthday);
			String mi_inschooldate = json.getString( ManagerInfoTableBack.MI_INSCHOOLDATE);
			params.put(ManagerInfoTableBack.MI_INSCHOOLDATE,mi_inschooldate);
			String mi_class = json.getString( ManagerInfoTableBack.MI_CLASS);
			params.put(ManagerInfoTableBack.MI_CLASS,mi_class);
			String mi_unitnum = json.getString( ManagerInfoTableBack.MI_UNITNUM);
			params.put(ManagerInfoTableBack.MI_UNITNUM,mi_unitnum);
			String mi_unitname = json.getString( ManagerInfoTableBack.MI_UNITNAME);
			params.put(ManagerInfoTableBack.MI_UNITNAME,mi_unitname);
			String mi_edubackground = json.getString( ManagerInfoTableBack.MI_EDUBACKGROUND);
			params.put(ManagerInfoTableBack.MI_EDUBACKGROUND,mi_edubackground);
			String mi_highestoffering = json.getString( ManagerInfoTableBack.MI_HIGHESTOFFERING);
			params.put(ManagerInfoTableBack.MI_HIGHESTOFFERING,mi_highestoffering);
			String mi_professiontitle = json.getString( ManagerInfoTableBack.MI_PROFESSIONTITLE);
			params.put(ManagerInfoTableBack.MI_PROFESSIONTITLE,mi_professiontitle);
			String mi_duty = json.getString(ManagerInfoTableBack.MI_DUTY);
			params.put(ManagerInfoTableBack.MI_DUTY, mi_duty);
			params.put(ManagerInfoTableBack.MI_SERIALNUMBER, serialnumber);
			
			ManagerInfoDao mid = new ManagerInfoDaoImpl();
			int result = mid.alterManagerInfo(params, mi_id);
			
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
