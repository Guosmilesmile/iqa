
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

import cn.edu.xmu.dao.TeacherObtainPatentDao;
import cn.edu.xmu.daoimpl.TeacherObtainPatentDaoImpl;
import cn.edu.xmu.table.TeacherObtainPatentTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateTeacherObtainPatentServlet")
public class Sec_UpdateTeacherObtainPatentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherObtainPatentServlet() {
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
					   int top_id = jsons.getJSONObject(i).getInt(TeacherObtainPatentTable.TOP_ID);
					   				
					   String top_comments = jsons.getJSONObject(i).getString(TeacherObtainPatentTable.TOP_COMMENTS);
								
					   Map<String,String> params= new HashMap<String, String>();
					    params.put(TeacherObtainPatentTable.TOP_ID, top_id+"");
					   
					    params.put(TeacherObtainPatentTable.TOP_COMMENTS, top_comments);
					
					    TeacherObtainPatentDao teacherObtainPatentDao = new TeacherObtainPatentDaoImpl();
					    int result = teacherObtainPatentDao.alterTeacherObtainPatent(params, top_id+"");
					    out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						  			    
			    int top_id = json.getInt(TeacherObtainPatentTable.TOP_ID);
			    String total = json.getString(TeacherObtainPatentTable.TOP_TOTAL);  
				Integer top_total = -1;
				if(!total.equals("")) top_total = Integer.parseInt(total);
				String invention = json.getString(TeacherObtainPatentTable.TOP_INVENTION); 
				Integer top_invention = -1;
				if(!invention.equals("")) top_invention = Integer.parseInt(invention);
				String utilitymodel = json.getString(TeacherObtainPatentTable.TOP_UTILITYMODEL); 
				Integer top_utilitymodel = -1;
				if(!utilitymodel.equals("")) top_utilitymodel = Integer.parseInt(utilitymodel);
						   
				int top_isnull = 0;
				if("".equals(total)||"".equals(invention)||"".equals(utilitymodel))
				{
					top_isnull = 1;
				}				 
				   String top_comments = json.getString(TeacherObtainPatentTable.TOP_COMMENTS);
							
				   Map<String,String> params= new HashMap<String, String>();
				   params.put(TeacherObtainPatentTable.TOP_ID, top_id+"");
				    params.put(TeacherObtainPatentTable.TOP_TOTAL, top_total+"");
				    params.put(TeacherObtainPatentTable.TOP_INVENTION, top_invention+"");
				    params.put(TeacherObtainPatentTable.TOP_UTILITYMODEL, top_utilitymodel+"");
				    params.put(TeacherObtainPatentTable.TOP_ISNULL, top_isnull+"");
				    params.put(TeacherObtainPatentTable.TOP_COMMENTS, top_comments);
				
				    TeacherObtainPatentDao teacherObtainPatentDao = new TeacherObtainPatentDaoImpl();
				    int result = teacherObtainPatentDao.alterTeacherObtainPatent(params, top_id+"");
				    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
