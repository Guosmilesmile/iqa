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

import cn.edu.xmu.dao.TeachersScientificAchievementsDao;
import cn.edu.xmu.daoimpl.TeachersScientificAchievementsDaoImpl;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateTeachersScientificAchievementsServlet")
public class Sec_UpdateTeachersScientificAchievementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeachersScientificAchievementsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {					
					   int tsa_id = jsons.getJSONObject(i).getInt(TeachersScientificAchievementsTable.TSA_ID);
					   String tsa_comments = jsons.getJSONObject(i).getString(TeachersScientificAchievementsTable.TSA_COMMENTS);
								
					   Map<String,String> params= new HashMap<String, String>();
					    params.put(TeachersScientificAchievementsTable.TSA_ID, tsa_id+"");
					    params.put(TeachersScientificAchievementsTable.TSA_COMMENTS, tsa_comments);
					
					    TeachersScientificAchievementsDao teachersScientificAchievementsDao = new TeachersScientificAchievementsDaoImpl();
					    int result = teachersScientificAchievementsDao.alterTeachersScientificAchievements(params, tsa_id+"");
					    out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						  			    
			    int tsa_id = json.getInt(TeachersScientificAchievementsTable.TSA_ID);
			    String total = json.getString(TeachersScientificAchievementsTable.TSA_TOTAL);
				Integer tsa_total = -1;
				if(!"".equals(total)) tsa_total = Integer.parseInt(total);
				String  nationallevel = json.getString(TeachersScientificAchievementsTable.TSA_NATIONALLEVEL); 
				Integer tsa_nationallevel = -1;
				if(!"".equals(nationallevel)) tsa_nationallevel = Integer.parseInt(nationallevel);
				String provinciallevel = json.getString(TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL); 
				Integer tsa_provinciallevel = -1;
				if(!"".equals(provinciallevel)) tsa_provinciallevel = Integer.parseInt(provinciallevel);						   
				   //String tsa_college = json.getString(TeachersScientificAchievementsTable.TSA_COLLEGE);					 
				   String tsa_comments = json.getString(TeachersScientificAchievementsTable.TSA_COMMENTS);
				   int tsa_isnull = 0;
					if("".equals(total)||"".equals(nationallevel)||"".equals(provinciallevel))
					{
						tsa_isnull = 1;
					}
					
				   Map<String,String> params= new HashMap<String, String>();
				    params.put(TeachersScientificAchievementsTable.TSA_ID, tsa_id+"");
				    params.put(TeachersScientificAchievementsTable.TSA_TOTAL, tsa_total+"");
				    params.put(TeachersScientificAchievementsTable.TSA_NATIONALLEVEL, tsa_nationallevel+"");
				    params.put(TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL, tsa_provinciallevel+"");				  
				    params.put(TeachersScientificAchievementsTable.TSA_COMMENTS, tsa_comments);
				    params.put(TeachersScientificAchievementsTable.TSA_ISNULL, tsa_isnull+"");
				    
				    TeachersScientificAchievementsDao teachersScientificAchievementsDao = new TeachersScientificAchievementsDaoImpl();
				    int result = teachersScientificAchievementsDao.alterTeachersScientificAchievements(params, tsa_id+"");
				    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
