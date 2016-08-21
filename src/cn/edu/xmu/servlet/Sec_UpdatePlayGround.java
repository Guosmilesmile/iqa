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

import cn.edu.xmu.dao.PlayGroundDao;
import cn.edu.xmu.daoimpl.PlayGroundDaoImpl;
import cn.edu.xmu.table.PlayGroundTable;


/**
 * Servlet implementation class Sec_UpdatePlayGround
 */
@WebServlet("/Sec_UpdatePlayGround")
public class Sec_UpdatePlayGround extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdatePlayGround() {
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
		//String college = request.getParameter(PlayGroundTable.PG_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String pg_id = json.getJSONObject(i).getString(
							PlayGroundTable.PG_ID);
					String pg_comments = json.getJSONObject(i).getString(
							PlayGroundTable.PG_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(PlayGroundTable.PG_COMMENTS, pg_comments);
					PlayGroundDao pgDao = new PlayGroundDaoImpl();
					int result = pgDao.alterPlayGround(params, pg_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String pg_id = json.getString(PlayGroundTable.PG_ID);
				String pg_campus = json.getString(PlayGroundTable.PG_CAMPUS);
				String pg_groundname = json.getString(PlayGroundTable.PG_GROUNDNAME);
				String pg_amount = json.getString(PlayGroundTable.PG_AMOUNT);
				String pg_indoorarea = json.getString(PlayGroundTable.PG_INDOORAREA);
				String pg_outdoorarea = json.getString(PlayGroundTable.PG_OUTDOORAREA);
				String pg_totalarea = json.getString(PlayGroundTable.PG_TOTALAREA);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(pg_campus.equals("")||pg_groundname.equals("")||pg_amount.equals("")||pg_indoorarea.equals("")||
						pg_indoorarea.equals("")||pg_totalarea.equals(""))
					isnull = 1;
				
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(PlayGroundTable.PG_COMMENTS)){
					String pg_comments = json.getString(PlayGroundTable.PG_COMMENTS);
					params.put(PlayGroundTable.PG_COMMENTS, pg_comments);
				}
				if(json.has(PlayGroundTable.PG_DEADLINE)){
					String pg_deadline = json.getString(PlayGroundTable.PG_DEADLINE);
					params.put(PlayGroundTable.PG_DEADLINE, pg_deadline);
				}
				params.put(PlayGroundTable.PG_CAMPUS, pg_campus);
				params.put(PlayGroundTable.PG_GROUNDNAME, pg_groundname);
				params.put(PlayGroundTable.PG_AMOUNT, pg_amount);
				params.put(PlayGroundTable.PG_INDOORAREA, pg_indoorarea);
				params.put(PlayGroundTable.PG_OUTDOORAREA, pg_outdoorarea);
				params.put(PlayGroundTable.PG_TOTALAREA, pg_totalarea);
				params.put(PlayGroundTable.ISNULL, isnull+"");
				
				PlayGroundDao pgDao = new PlayGroundDaoImpl();
				int result = pgDao.alterPlayGround(params, pg_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
