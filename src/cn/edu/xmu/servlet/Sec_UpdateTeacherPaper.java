package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
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

import cn.edu.xmu.dao.TeacherPaperDao;
import cn.edu.xmu.daoimpl.TeacherPaperDaoImpl;
import cn.edu.xmu.table.TeacherPaperTable;




/**
 */
@WebServlet("/Sec_UpdateTeacherPaper")
public class Sec_UpdateTeacherPaper extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherPaper() {
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
					String tp_id = json.getString(TeacherPaperTable.TP_ID);
					String tp_colleges = json.getString(TeacherPaperTable.TP_COLLEGES);
					String tp_papertitle = json.getString(TeacherPaperTable.TP_PAPERTITLE);
					String tp_paperclasses =json.getString(TeacherPaperTable.TP_PAPERCLASSES);
					String tp_authors = json.getString(TeacherPaperTable.TP_AUTHORS);
					String tp_serial = json.getString(TeacherPaperTable.TP_SERIAL);
					String tp_authorclasses = json.getString(TeacherPaperTable.TP_AUTHORCLASSES);
					String tp_publication = json.getString(TeacherPaperTable.TP_PUBLICATION);
					String tp_year = json.getString(TeacherPaperTable.TP_YEAR);
					String tp_month = json.getString(TeacherPaperTable.TP_MONTH);
					String tp_classes = json.getString(TeacherPaperTable.TP_CLASSES);
					int tp_serialnumber = json.getInt(TeacherPaperTable.TP_SERIALNUMBER);
					String tp_college = json.getString(TeacherPaperTable.TP_COLLEGE);
					String tp_comments = json.getString(TeacherPaperTable.TP_COMMENTS);
					
					
					params.put(TeacherPaperTable.TP_COLLEGES, tp_colleges);
					params.put(TeacherPaperTable.TP_PAPERTITLE, tp_papertitle);
					params.put(TeacherPaperTable.TP_PAPERCLASSES, tp_paperclasses );
					params.put(TeacherPaperTable.TP_AUTHORS,tp_authors  );
					params.put(TeacherPaperTable.TP_SERIAL,tp_serial);
					params.put(TeacherPaperTable.TP_AUTHORCLASSES,tp_authorclasses);
					params.put(TeacherPaperTable.TP_PUBLICATION, tp_publication);
					params.put(TeacherPaperTable.TP_YEAR,tp_year );
					params.put(TeacherPaperTable.TP_MONTH,tp_month );
					params.put(TeacherPaperTable.TP_CLASSES, tp_classes);
					params.put(TeacherPaperTable.TP_SERIALNUMBER, tp_serialnumber+"");
					params.put(TeacherPaperTable.TP_COLLEGE, tp_college);
					params.put(TeacherPaperTable.TP_COMMENTS, tp_comments );
					
					TeacherPaperDao apdao = new TeacherPaperDaoImpl();
					int result = apdao.alterTeacherPaper(params, tp_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				String tp_id = json.getString(TeacherPaperTable.TP_ID);
				String tp_colleges = json.getString(TeacherPaperTable.TP_COLLEGES);
				String tp_papertitle = json.getString(TeacherPaperTable.TP_PAPERTITLE);
				String tp_paperclasses =json.getString(TeacherPaperTable.TP_PAPERCLASSES);
				String tp_authors = json.getString(TeacherPaperTable.TP_AUTHORS);
				String tp_serial = json.getString(TeacherPaperTable.TP_SERIAL);
				String tp_authorclasses = json.getString(TeacherPaperTable.TP_AUTHORCLASSES);
				String tp_publication = json.getString(TeacherPaperTable.TP_PUBLICATION);
				String tp_year = json.getString(TeacherPaperTable.TP_YEAR);
				String tp_month = json.getString(TeacherPaperTable.TP_MONTH);
				String tp_classes = json.getString(TeacherPaperTable.TP_CLASSES);
				int tp_serialnumber = json.getInt(TeacherPaperTable.TP_SERIALNUMBER);
				String tp_college = json.getString(TeacherPaperTable.TP_COLLEGE);
				String tp_comments = json.getString(TeacherPaperTable.TP_COMMENTS);
				
				
				int isnull = 0;
				if ("".equals(tp_colleges) || "".equals(tp_papertitle) || "".equals(tp_paperclasses) || "".equals(tp_authors)
						|| "".equals(tp_serial) || "".equals(tp_authorclasses) || "".equals(tp_publication) || "".equals(tp_year)
						|| "".equals(tp_month) || "".equals(tp_classes))
					isnull = 1;
				params.put(TeacherPaperTable.TP_COLLEGES, tp_colleges);
				params.put(TeacherPaperTable.TP_PAPERTITLE, tp_papertitle);
				params.put(TeacherPaperTable.TP_PAPERCLASSES, tp_paperclasses );
				params.put(TeacherPaperTable.TP_AUTHORS,tp_authors  );
				params.put(TeacherPaperTable.TP_SERIAL,tp_serial);
				params.put(TeacherPaperTable.TP_AUTHORCLASSES,tp_authorclasses);
				params.put(TeacherPaperTable.TP_PUBLICATION, tp_publication);
				params.put(TeacherPaperTable.TP_YEAR,tp_year );
				params.put(TeacherPaperTable.TP_MONTH,tp_month );
				params.put(TeacherPaperTable.TP_CLASSES, tp_classes);
				params.put(TeacherPaperTable.TP_SERIALNUMBER, tp_serialnumber+"");
				params.put(TeacherPaperTable.TP_COLLEGE, tp_college);
				params.put(TeacherPaperTable.TP_COMMENTS, tp_comments );
				params.put("isnull",isnull+"");
				TeacherPaperDao apdao = new TeacherPaperDaoImpl();
				int result = apdao.alterTeacherPaper(params, tp_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
