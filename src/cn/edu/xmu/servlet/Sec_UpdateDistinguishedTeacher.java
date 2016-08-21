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

import cn.edu.xmu.dao.DistinguishedTeacherDao;
import cn.edu.xmu.daoimpl.DistinguishedTeacherDaoImpl;
import cn.edu.xmu.table.DistinguishedTeacherTable;

/**
 * Servlet implementation class Sec_UpdateDistinguishedTeacher
 */
@WebServlet("/UpdateDistinguishedTeacher")
public class Sec_UpdateDistinguishedTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateDistinguishedTeacher() {
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
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		//String college = request.getParameter(DistinguishedTeacherTable.DT_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					//无serialnumber,deadline,college
					String dt_id = jsons.getJSONObject(i).getString(DistinguishedTeacherTable.DT_ID);
					String dt_comments = jsons.getJSONObject(i).getString(DistinguishedTeacherTable.DT_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(DistinguishedTeacherTable.DT_ID, dt_id);
					params.put(DistinguishedTeacherTable.DT_COMMENTS, dt_comments);
				
					DistinguishedTeacherDao dtDao = new DistinguishedTeacherDaoImpl();
					int result = dtDao.alterDistinguishedTeacher(params, dt_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String dt_id = json.getString(DistinguishedTeacherTable.DT_ID);
				String dt_sequencenumber = json.getString(DistinguishedTeacherTable.DT_SEQUENCENUMBER);
				if(dt_sequencenumber.equals(""))
					dt_sequencenumber = "-999";
				String dt_name = json.getString(DistinguishedTeacherTable.DT_NAME);
				String dt_collegename = json.getString(DistinguishedTeacherTable.DT_COLLEGENAME);
				String dt_country = json.getString(DistinguishedTeacherTable.DT_COUNTRY);
				if(dt_country.equals(""))
					dt_country = "-999";
				String dt_province = json.getString(DistinguishedTeacherTable.DT_PROVINCE);
				if(dt_province.equals(""))
					dt_province = "-999";
				String dt_school = json.getString(DistinguishedTeacherTable.DT_SCHOOL);
				if(dt_school.equals(""))
					dt_school = "-999";
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(dt_sequencenumber.equals("-999") || dt_name.equals("") || dt_collegename.equals("") ||
						dt_country.equals("-999") || dt_province.equals("-999") || dt_school.equals("-999"))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(DistinguishedTeacherTable.DT_ID, dt_id);
				params.put(DistinguishedTeacherTable.DT_SEQUENCENUMBER, dt_sequencenumber);
				params.put(DistinguishedTeacherTable.DT_NAME, dt_name);
				params.put(DistinguishedTeacherTable.DT_COLLEGENAME, dt_collegename);
				params.put(DistinguishedTeacherTable.DT_COUNTRY, dt_country);
				params.put(DistinguishedTeacherTable.DT_PROVINCE, dt_province);
				params.put(DistinguishedTeacherTable.DT_SCHOOL, dt_school);
				params.put(DistinguishedTeacherTable.ISNULL, isnull+"");
			
				DistinguishedTeacherDao dtDao = new DistinguishedTeacherDaoImpl();
				int result = dtDao.alterDistinguishedTeacher(params, dt_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
