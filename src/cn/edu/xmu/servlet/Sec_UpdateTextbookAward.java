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

import cn.edu.xmu.dao.TextbookAwardDao;
import cn.edu.xmu.daoimpl.TextbookAwardDaoImpl;
import cn.edu.xmu.table.TextbookAwardTable;

/**
 * Servlet implementation class Sec_UpdateTextbookAward
 */
@WebServlet("/UpdateTextbookAward")
public class Sec_UpdateTextbookAward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTextbookAward() {
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
		//String college = request.getParameter(TextbookAwardTable.TA_COLLEGE);
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
					String ta_id = jsons.getJSONObject(i).getString(TextbookAwardTable.TA_ID);
					String ta_comments = jsons.getJSONObject(i).getString(TextbookAwardTable.TA_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(TextbookAwardTable.TA_ID, ta_id);
					params.put(TextbookAwardTable.TA_COMMENTS, ta_comments);
				
					TextbookAwardDao taDao = new TextbookAwardDaoImpl();
					int result = taDao.alterTextbookAward(params, ta_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String ta_id = json.getString(TextbookAwardTable.TA_ID);
				String ta_collegename= json.getString(TextbookAwardTable.TA_COLLEGENAME);
				String ta_name= json.getString(TextbookAwardTable.TA_NAME);
				String ta_author= json.getString(TextbookAwardTable.TA_AUTHOR);
				String ta_publisher= json.getString(TextbookAwardTable.TA_PUBLISHER);
				String ta_prizename= json.getString(TextbookAwardTable.TA_PRIZENAME);
				String ta_grade= json.getString(TextbookAwardTable.TA_GRADE);
				String ta_type= json.getString(TextbookAwardTable.TA_TYPE);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(ta_collegename.equals("") || ta_name.equals("") || ta_author.equals("")||ta_publisher.equals("")||
						ta_prizename.equals("") || ta_grade.equals("") || ta_type.equals(""))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(TextbookAwardTable.TA_ID, ta_id);
				params.put(TextbookAwardTable.TA_COLLEGENAME, ta_collegename);
				params.put(TextbookAwardTable.TA_NAME, ta_name);
				params.put(TextbookAwardTable.TA_AUTHOR, ta_author);
				params.put(TextbookAwardTable.TA_PUBLISHER, ta_publisher);
				params.put(TextbookAwardTable.TA_PRIZENAME, ta_prizename);
				params.put(TextbookAwardTable.TA_GRADE, ta_grade);
				params.put(TextbookAwardTable.TA_TYPE, ta_type);
				params.put(TextbookAwardTable.ISNULL, isnull+"");
			
				TextbookAwardDao taDao = new TextbookAwardDaoImpl();
				int result = taDao.alterTextbookAward(params, ta_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
