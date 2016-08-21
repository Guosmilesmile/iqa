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

import cn.edu.xmu.dao.AcademicPaperDao;
import cn.edu.xmu.daoimpl.AcademicPaperDaoImpl;
import cn.edu.xmu.entity.AcademicPaper;
import cn.edu.xmu.table.AcademicPaperTable;



/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateAcademicPaper")
public class Sec_UpdateAcademicPaper extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateAcademicPaper() {
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
					String ap_id = json.getString(AcademicPaperTable.AP_ID);
					String ap_comments = json.getString(AcademicPaperTable.AP_COMMENTS);
					
					params.put(AcademicPaperTable.AP_COMMENTS, ap_comments );
					
					AcademicPaperDao apdao = new AcademicPaperDaoImpl();
					int result = apdao.alterAcademicPaper(params, ap_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				String ap_id = json.getString(AcademicPaperTable.AP_ID);
				String ap_colleges = json.getString(AcademicPaperTable.AP_COLLEGES);
				String ap_papertitle = json.getString(AcademicPaperTable.AP_PAPERTITLE);
				String ap_grade =json.getString(AcademicPaperTable.AP_GRADE);
				String ap_name = json.getString(AcademicPaperTable.AP_NAME);
				String ap_major = json.getString(AcademicPaperTable.AP_MAJOR);
				String ap_publication = json.getString(AcademicPaperTable.AP_PUBLICATION);
				String ap_time = json.getString(AcademicPaperTable.AP_TIME);
				String ap_classes = json.getString(AcademicPaperTable.AP_CLASSES);
				String ap_remark = json.getString(AcademicPaperTable.AP_REMARK);
				int ap_serialnumber = json.getInt(AcademicPaperTable.AP_SERIALNUMBER);
				String ap_college = json.getString(AcademicPaperTable.AP_COLLEGE);
				String ap_comments = json.getString(AcademicPaperTable.AP_COMMENTS);
				
				
				System.out.println("++"+ap_comments+"+++");
					int isnull = 0;
					if ("".equals(ap_colleges) || "".equals(ap_papertitle)
							|| "".equals(ap_grade) || "".equals(ap_name)
							|| "".equals(ap_major) || "".equals(ap_publication) || "".equals(ap_time)
							|| "".equals(ap_classes)) {
						isnull = 1;
					}
					if(ap_time.equals(""))
						ap_time = "1800-01-01";
					
				params.put(AcademicPaperTable.AP_COLLEGES, ap_colleges);
				params.put(AcademicPaperTable.AP_PAPERTITLE, ap_papertitle);
				params.put(AcademicPaperTable.AP_GRADE, ap_grade );
				params.put(AcademicPaperTable.AP_NAME,ap_name  );
				params.put(AcademicPaperTable.AP_MAJOR,ap_major );
				params.put(AcademicPaperTable.AP_PUBLICATION, ap_publication);
				params.put(AcademicPaperTable.AP_TIME,ap_time+"" );
				params.put(AcademicPaperTable.AP_CLASSES, ap_classes);
				params.put(AcademicPaperTable.AP_REMARK, ap_remark );
				params.put(AcademicPaperTable.AP_SERIALNUMBER, ap_serialnumber+"");
				params.put(AcademicPaperTable.AP_COLLEGE, ap_college);
				params.put(AcademicPaperTable.AP_COMMENTS, ap_comments );
				params.put("isnull", isnull+"");
				
				AcademicPaperDao apdao = new AcademicPaperDaoImpl();
				int result = apdao.alterAcademicPaper(params, ap_id);
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
