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

import cn.edu.xmu.dao.TeachingAwardDao;
import cn.edu.xmu.daoimpl.TeachingAwardDaoImpl;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.TeachingAwardTable;

/**
 * 
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdateTeachingAwardServlet")
public class Sec_UpdateTeachingAwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeachingAwardServlet() {
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
					String ta_id = jsons.getJSONObject(i).getString(TeachingAwardTable.TA_ID);    
					String ta_comments = jsons.getJSONObject(i).getString(TeachingAwardTable.TA_COMMENTS); 
					
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(ForeignExchangeTable.FE_ID, ta_id);					 
					params.put(ForeignExchangeTable.FE_COMMENTS, ta_comments);
					
					TeachingAwardDao teachingAwardDao = new TeachingAwardDaoImpl();
					int result = teachingAwardDao.alterTeachingAward(params, ta_id);
					
					out.print(result);
				}
			}else {
				
			
			JSONObject json = new JSONObject(data);
			
			String ta_id = json.getString(TeachingAwardTable.TA_ID); 
			String ta_projectname = json.getString(TeachingAwardTable.TA_PROJECTNAME);   
			String ta_compere  = json.getString(TeachingAwardTable.TA_COMPERE); 
			String ta_comperenumber = json.getString(TeachingAwardTable.TA_COMPERENUMBER); 
		 	String ta_level = json.getString(TeachingAwardTable.TA_LEVEL);  
			String ta_windate = json.getString(TeachingAwardTable.TA_WINDATE); 
			String ta_grantunit = json.getString(TeachingAwardTable.TA_GRANTUNIT);  
		
			String ta_comments = json.getString(TeachingAwardTable.TA_COMMENTS); 
			int ta_isnull = 0;
		    if("".equals(ta_id)||"".equals(ta_projectname)||"".equals(ta_compere)||"".equals(ta_comperenumber)
					|| "".equals(ta_level) || "".equals(ta_windate) || "".equals(ta_grantunit))
			{
				ta_isnull = 1;
			}
		    
			Map<String,String> params= new HashMap<String, String>();
			params.put(TeachingAwardTable.TA_PROJECTNAME, ta_projectname);
			params.put(TeachingAwardTable.TA_COMPERE, ta_compere);
			params.put(TeachingAwardTable.TA_COMPERENUMBER, ta_comperenumber);
			params.put(TeachingAwardTable.TA_LEVEL, ta_level);
			params.put(TeachingAwardTable.TA_WINDATE, ta_windate);
			params.put(TeachingAwardTable.TA_GRANTUNIT, ta_grantunit);
			params.put(TeachingAwardTable.TA_COMMENTS, ta_comments);
			params.put(TeachingAwardTable.TA_ISNULL, ta_isnull+"");
			
			TeachingAwardDao teachingAwardDao = new TeachingAwardDaoImpl();
			int result = teachingAwardDao.alterTeachingAward(params, ta_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

