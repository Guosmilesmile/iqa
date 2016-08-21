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

import cn.edu.xmu.dao.BenkeMentalHealthDao;
import cn.edu.xmu.daoimpl.BenkeMentalHealthDaoImpl;
import cn.edu.xmu.table.BenkeMentalHealthTable;


/**
 * Servlet implementation class Sec_UpdateBenkeMentalHealth
 */
@WebServlet("/Sec_UpdateBenkeMentalHealth")
public class Sec_UpdateBenkeMentalHealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateBenkeMentalHealth() {
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
		//解码
		//String college = request.getParameter(BenkeMentalHealthTable.BMH_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String bmh_id = json.getJSONObject(i).getString(
							BenkeMentalHealthTable.BMH_ID);
					String bmh_comments = json.getJSONObject(i).getString(
							BenkeMentalHealthTable.BMH_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(BenkeMentalHealthTable.BMH_COMMENTS, bmh_comments);
					BenkeMentalHealthDao bmhDao = new BenkeMentalHealthDaoImpl();
					int result = bmhDao.alterBenkeMentalHealth(params, bmh_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String bmh_id = json.getString(BenkeMentalHealthTable.BMH_ID);
				String bmh_college1 = json.getString(BenkeMentalHealthTable.BMH_COLLEGE1);
				String bmh_inschoolamount = json.getString(BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT);
				String bmh_health = json.getString(BenkeMentalHealthTable.BMH_HEALTH);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(bmh_college1.equals("")||bmh_inschoolamount.equals("")||bmh_health.equals(""))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(BenkeMentalHealthTable.BMH_COMMENTS)){
					String bmh_comments = json.getString(BenkeMentalHealthTable.BMH_COMMENTS);
					params.put(BenkeMentalHealthTable.BMH_COMMENTS, bmh_comments);
				}
				if(json.has(BenkeMentalHealthTable.BMH_DEADLINE)){
					String bmh_deadline = json.getString(BenkeMentalHealthTable.BMH_DEADLINE);
					params.put(BenkeMentalHealthTable.BMH_DEADLINE, bmh_deadline);
				}
				params.put(BenkeMentalHealthTable.BMH_COLLEGE1, bmh_college1);
				params.put(BenkeMentalHealthTable.BMH_INSCHOOLAMOUNT, bmh_inschoolamount);
				params.put(BenkeMentalHealthTable.BMH_HEALTH, bmh_health);
				
				params.put(BenkeMentalHealthTable.ISNULL, isnull+"");
				
				BenkeMentalHealthDao bmhDao = new BenkeMentalHealthDaoImpl();
				int result = bmhDao.alterBenkeMentalHealth(params, bmh_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
