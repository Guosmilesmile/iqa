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

import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.table.TeachResearchUnitTable;

/*
 * 1-4 修改教学科研单位
 */
@WebServlet("/UpdateTeachResearchUnitServlet")
public class UpdateTeachResearchUnitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeachResearchUnitServlet() {
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
			
			String tr_id = json.getString(TeachResearchUnitTable.TR_ID);
			String tr_name = json.getString(TeachResearchUnitTable.TR_NAME);
			String tr_number = json.getString(TeachResearchUnitTable.TR_NUMBER);
			String respon_person = json.getString(TeachResearchUnitTable.TR_RESPONPERSON);
			//String college = json.getString(SuperMajorTable.COLLEGE);
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(TeachResearchUnitTable.TR_NAME, tr_name);
			params.put(TeachResearchUnitTable.TR_NUMBER, tr_number);
			params.put(TeachResearchUnitTable.TR_RESPONPERSON, respon_person);
			//params.put(SuperMajorTable.COLLEGE, college);
			
			TeachResearchUnitDao teachResearchUnitDao = new TeachResearchUnitDaoImpl();
			int result = teachResearchUnitDao.alterTeachResearchUnit(params, tr_id);
			
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
