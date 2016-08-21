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

import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

/**
 * 
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdateTeachingResearchAndReformProjectServlet")
public class Sec_UpdateTeachingResearchAndReformProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeachingResearchAndReformProjectServlet() {
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
					String trarp_id = jsons.getJSONObject(i).getString(TeachingResearchAndReformProjectTable.TRARP_ID); 				   
					String trarp_comments = jsons.getJSONObject(i).getString(TeachingResearchAndReformProjectTable.TRARP_COMMENTS); 
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(TeachingResearchAndReformProjectTable.TRARP_ID, trarp_id);					 
					params.put(TeachingResearchAndReformProjectTable.TRARP_COMMENTS, trarp_comments);
					
					TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
					int result = teachingResearchAndReformProjectDao.alterTeachingResearchAndReformProject(params, trarp_id);
					
					out.print(result);
				}
			}else {
				
			
			JSONObject json = new JSONObject(data);
			
			String trarp_id = json.getString(TeachingResearchAndReformProjectTable.TRARP_ID); 
			String trarp_projectname = json.getString(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME);   
			String trarp_compere  = json.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERE); 
			String trarp_comperenumber = json.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER); 
		 	String trarp_level = json.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);  
			String trarp_setupdate = json.getString(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE); 
			String trarp_checkdate = json.getString(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE);  
			String funds = json.getString(TeachingResearchAndReformProjectTable.TRARP_FUNDS);
			Float trarp_funds = (float) -1.0;
			if(!funds.equals("")) trarp_funds = Float.parseFloat(funds);
		    String jointeachers = json.getString(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS);
		    Integer trarp_jointeachers = -1;
			if(!jointeachers.equals("")) trarp_jointeachers = Integer.parseInt(jointeachers);
			int trarp_isnull = 0;
			if("".equals(trarp_projectname)||"".equals(trarp_compere)||"".equals(trarp_comperenumber)||"".equals(trarp_level)
					|| "".equals(trarp_setupdate) || "".equals(trarp_checkdate) || "".equals(funds) || "".equals(jointeachers))
			{
				trarp_isnull = 1;
			}

			String trarp_comments = json.getString(TeachingResearchAndReformProjectTable.TRARP_COMMENTS); 
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME, trarp_projectname);
			params.put(TeachingResearchAndReformProjectTable.TRARP_COMPERE, trarp_compere);
			params.put(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER, trarp_comperenumber);
			params.put(TeachingResearchAndReformProjectTable.TRARP_LEVEL, trarp_level);
			params.put(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE, trarp_setupdate);
			params.put(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE, trarp_checkdate);
			params.put(TeachingResearchAndReformProjectTable.TRARP_FUNDS, trarp_funds+"");
			params.put(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS, trarp_jointeachers+"");
			params.put(TeachingResearchAndReformProjectTable.TRARP_COMMENTS, trarp_comments);
			params.put(TeachingResearchAndReformProjectTable.TRARP_ISNULL, trarp_isnull+"");
			
			TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
			int result = teachingResearchAndReformProjectDao.alterTeachingResearchAndReformProject(params, trarp_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

