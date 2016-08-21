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

import cn.edu.xmu.dao.SuperMajorDao;
import cn.edu.xmu.daoimpl.SuperMajorDaoImpl;
import cn.edu.xmu.entity.SuperMajor;
import cn.edu.xmu.table.SuperMajorTable;

@WebServlet("/UpdateSuperMajor")
public class UpdateSuperMajor extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSuperMajor() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String sm_id = json.getString(SuperMajorTable.SM_ID);
			String sm_name = json.getString(SuperMajorTable.SM_NAME);
			String sm_number = json.getString(SuperMajorTable.SM_NUMBER);
			String sm_class = json.getString(SuperMajorTable.SM_CLASS);
			String c_startyear = json.getString(SuperMajorTable.C_STARTYEAR);
			String p_startyear = json.getString(SuperMajorTable.P_STARTYEAR);
			String s_startyear = json.getString(SuperMajorTable.S_STARTYEAR);
			String respon_person = json.getString(SuperMajorTable.RESPON_PERSON);
			String college = json.getString(SuperMajorTable.SM_COLLEGE);
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(SuperMajorTable.SM_NAME, sm_name);
			params.put(SuperMajorTable.SM_NUMBER, sm_number);
			params.put(SuperMajorTable.SM_CLASS, sm_class);
			params.put(SuperMajorTable.C_STARTYEAR, c_startyear);
			params.put(SuperMajorTable.P_STARTYEAR, p_startyear);
			params.put(SuperMajorTable.S_STARTYEAR, s_startyear);
			params.put(SuperMajorTable.RESPON_PERSON, respon_person);
			params.put(SuperMajorTable.SM_COLLEGE, college);
			
			SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
			int result = superMajorDao.alterSuperMajor(params, sm_id);
			
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
