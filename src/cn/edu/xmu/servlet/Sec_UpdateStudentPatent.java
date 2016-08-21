package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import cn.edu.xmu.dao.StudentPatentDao;
import cn.edu.xmu.daoimpl.StudentPatentDaoImpl;
import cn.edu.xmu.table.StudentPatentTable;




/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateStudentPatent")
public class Sec_UpdateStudentPatent extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStudentPatent() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					Map<String,String> params= new HashMap<String, String>();
					String sp_id = json.getString(StudentPatentTable.SP_ID);
					String sp_comments = json.getString(StudentPatentTable.SP_COMMENTS);
					params.put(StudentPatentTable.SP_COMMENTS, sp_comments );
					
					StudentPatentDao apdao = new StudentPatentDaoImpl();
					int result = apdao.alterStudentPatent(params, sp_id);
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				String sp_id = json.getString(StudentPatentTable.SP_ID);
				String sp_colleges = json.getString(StudentPatentTable.SP_COLLEGES);
				String sp_patentname = json.getString(StudentPatentTable.SP_PATENTNAME);
				String sp_number = json.getString(StudentPatentTable.SP_NUMBER);
				String sp_time = json.getString(StudentPatentTable.SP_TIME);
				String sp_authors = json.getString(StudentPatentTable.SP_AUTHORS);
				String sp_major = json.getString(StudentPatentTable.SP_MAJOR);
				String sp_grade =json.getString(StudentPatentTable.SP_GRADE);
				String sp_serial = json.getString(StudentPatentTable.SP_SERIAL);
				String sp_remark = json.getString(StudentPatentTable.SP_REMARK);
				String sp_comments = json.getString(StudentPatentTable.SP_COMMENTS);
				
				int isnull = 0;
				if("".equals(sp_colleges)||"".equals(sp_patentname)||"".equals(sp_number)||"".equals(sp_grade)||"".equals(sp_authors)||"".equals(sp_serial)||"".equals(sp_major)||"".equals(sp_remark)||"".equals(sp_time))
					isnull=1;
				
				if(sp_time.equals(""))
					sp_time = "1800-01-01";
				
				params.put(StudentPatentTable.SP_COLLEGES, sp_colleges);
				params.put(StudentPatentTable.SP_PATENTNAME, sp_patentname);
				params.put(StudentPatentTable.SP_GRADE, sp_grade );
				params.put(StudentPatentTable.SP_AUTHORS,sp_authors  );
				params.put(StudentPatentTable.SP_MAJOR,sp_major );
				params.put(StudentPatentTable.SP_SERIAL, sp_serial);
				params.put(StudentPatentTable.SP_TIME,sp_time );
				params.put(StudentPatentTable.SP_NUMBER, sp_number);
				params.put(StudentPatentTable.SP_REMARK, sp_remark );
				params.put(StudentPatentTable.SP_COMMENTS, sp_comments );
				params.put("isnull", isnull+"");
				
				StudentPatentDao apdao = new StudentPatentDaoImpl();
				int result = apdao.alterStudentPatent(params, sp_id);
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
