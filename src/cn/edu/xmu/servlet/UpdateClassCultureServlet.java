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

import cn.edu.xmu.dao.ClassCultureDao;
import cn.edu.xmu.daoimpl.ClassCultureDaoImpl;
import cn.edu.xmu.table.ClassCultureTable;

/**
 * Servlet implementation class UpdateClassCultureServlet
 */
@WebServlet("/UpdateClassCultureServlet")
public class UpdateClassCultureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClassCultureServlet() {
        super();
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
		String patter = request.getParameter("patter");
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter)){
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					JSONObject json =  jsons.getJSONObject(i);
					int c_id = json.getInt(ClassCultureTable.C_ID);
					String c_classname = json.getString(ClassCultureTable.C_CLASSNAME);
					String c_classnumber = json.getString(ClassCultureTable.C_CLASSNUMBER);
					int c_shunttime = json.getInt(ClassCultureTable.C_SHUNTTIME);
					String c_departmentnumber = json.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
					String c_majorname = json.getString(ClassCultureTable.C_MAJORNAME);
					String c_majornumber = json.getString(ClassCultureTable.C_MAJORNUMBER);
					String c_comments = json.getString(ClassCultureTable.C_COMMENTS);
					
					params.put(ClassCultureTable.C_ID, c_id+"");
					params.put(ClassCultureTable.C_CLASSNAME, c_classname);
					params.put(ClassCultureTable.C_CLASSNUMBER, c_classnumber);
					params.put(ClassCultureTable.C_SHUNTTIME, c_shunttime+"");
					params.put(ClassCultureTable.C_DEPARTMENTNUMBER, c_departmentnumber+"");
					params.put(ClassCultureTable.C_MAJORNAME, c_majorname);
					params.put(ClassCultureTable.C_MAJORNUMBER, c_majornumber);
					params.put(ClassCultureTable.C_COMMENTS,c_comments);
					ClassCultureDao classCultureDao = new ClassCultureDaoImpl();
					int result = classCultureDao.alterClassCulture(params, c_id+"");
					
				}
			}
		else{
			JSONObject json = new JSONObject(data);
			int c_id = json.getInt(ClassCultureTable.C_ID);
			String c_classname = json.getString(ClassCultureTable.C_CLASSNAME);
			String c_classnumber = json.getString(ClassCultureTable.C_CLASSNUMBER);
			String shunttime = json.getString(ClassCultureTable.C_SHUNTTIME);
			String c_departmentname = json.getString(ClassCultureTable.C_DEPARTMENAME);
			String c_departmentnumber = json.getString(ClassCultureTable.C_DEPARTMENTNUMBER);
			String c_majorname = json.getString(ClassCultureTable.C_MAJORNAME);
			String c_majornumber = json.getString(ClassCultureTable.C_MAJORNUMBER);
			String c_comments = json.getString(ClassCultureTable.C_COMMENTS);
			int c_shunttime =0;
			if("".equals(shunttime))
				c_shunttime = Integer.parseInt(shunttime);
			
			int isnull = 0;
			if ("".equals(c_classname) || "".equals(c_classnumber)
					|| "".equals(shunttime) || "".equals(c_departmentname) || "".equals(c_departmentnumber)
					|| "".equals(c_majorname) || "".equals(c_majornumber)) {
				isnull=1;
			}
			
			
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(ClassCultureTable.C_ID, c_id+"");
			params.put(ClassCultureTable.C_CLASSNAME, c_classname);
			params.put(ClassCultureTable.C_CLASSNUMBER, c_classnumber);
			params.put(ClassCultureTable.C_SHUNTTIME, c_shunttime+"");
			params.put(ClassCultureTable.C_DEPARTMENAME, c_departmentname+"");
			params.put(ClassCultureTable.C_DEPARTMENTNUMBER, c_departmentnumber+"");
			params.put(ClassCultureTable.C_MAJORNAME, c_majorname);
			params.put(ClassCultureTable.C_MAJORNUMBER, c_majornumber);
			params.put(ClassCultureTable.C_COMMENTS,c_comments);
			params.put("isnull",isnull+"");
			ClassCultureDao classCultureDao = new ClassCultureDaoImpl();
			int result = classCultureDao.alterClassCulture(params, c_id+"");
			out.print(result);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
