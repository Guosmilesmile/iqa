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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;
import cn.edu.xmu.table.CourseBuildInfoTable;

@WebServlet("/Sec_UpdateCourseBuildInfoServlet")
public class Sec_UpdateCourseBuildInfoServlet extends HttpServlet{
private static final long serialVersionUID = 1L;

/**
 * @see HttpServlet#HttpServlet()
 */
public Sec_UpdateCourseBuildInfoServlet() {
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
	String patter = request.getParameter("patter");
	//String serialnumber  = request.getParameter("serialnumber");
	
	data = URLDecoder.decode(data,"UTF-8");
	data = data.substring(1, data.length()-1);
	

	try {
		if (patter!=null && "batch".equals(patter)) {
			System.out.println("批量更新");
			//审核部分更新，可以批量
			data="["+data+"]";
			JSONArray jsons = new JSONArray(data);
			for (int i = 0; i < jsons.length(); i++) {
				Map<String,String> params= new HashMap<String, String>();
		String cbi_id = jsons.getJSONObject(i).getString(CourseBuildInfoTable.CBI_ID);
		String cbi_comments = jsons.getJSONObject(i).getString(CourseBuildInfoTable.CBI_COMMENTS);		
		params.put(CourseBuildInfoTable.CBI_COMMENTS,cbi_comments);
		
		CourseBuildInfoDao cbiDao = new CourseBuildInfoDaoImpl();
		int result = cbiDao.alterCourseBuildInfo(params, cbi_id);
		
		out.print(result);
			}
		}
		else{
			JSONObject json = new JSONObject(data);
			Map<String,String> params= new HashMap<String, String>();
			
			String cbi_id = json.getString(CourseBuildInfoTable.CBI_ID);
			String cbi_institute = json.getString( CourseBuildInfoTable.CBI_INSTITUTE);
			String cbi_name = json.getString( CourseBuildInfoTable.CBI_NAME);
			String cbi_chargeperson = json.getString( CourseBuildInfoTable.CBI_CHARGEPERSON);
			String cbi_type = json.getString( CourseBuildInfoTable.CBI_TYPE);
			String cbi_grade = json.getString( CourseBuildInfoTable.CBI_GRADE);
			String cbi_approvaltime = json.getString( CourseBuildInfoTable.CBI_APPROVALTIME);
			String cbi_comments = json.getString(CourseBuildInfoTable.CBI_COMMENTS);	
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(cbi_institute.equals("")||cbi_name.equals("")||cbi_chargeperson.equals("")||cbi_type.equals("")||
					cbi_grade.equals("")||cbi_approvaltime.equals(""))
				isnull = 1;
			
			if(cbi_approvaltime.equals(""))
				cbi_approvaltime = "1800-01-01";
			
			params.put(CourseBuildInfoTable.CBI_INSTITUTE,cbi_institute);
			params.put(CourseBuildInfoTable.CBI_NAME,cbi_name);
			params.put(CourseBuildInfoTable.CBI_CHARGEPERSON,cbi_chargeperson);
			params.put(CourseBuildInfoTable.CBI_TYPE,cbi_type);
			params.put(CourseBuildInfoTable.CBI_GRADE,cbi_grade);
			params.put(CourseBuildInfoTable.CBI_APPROVALTIME,cbi_approvaltime);
			params.put(CourseBuildInfoTable.CBI_COMMENTS,cbi_comments);
			params.put(CourseBuildInfoTable.ISNULL, isnull+"");
			
			CourseBuildInfoDao cbiDao = new CourseBuildInfoDaoImpl();
			int result = cbiDao.alterCourseBuildInfo(params, cbi_id);
			
			out.print(result);
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}finally{
		out.close();
	}
	
	
}
}
