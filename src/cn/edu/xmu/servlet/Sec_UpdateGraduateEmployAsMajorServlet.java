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

import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.daoimpl.GraduateEmployAsMajorDaoImpl;
import cn.edu.xmu.table.GraduateEmployAsMajorTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateGraduateEmployAsMajorServlet")
public class Sec_UpdateGraduateEmployAsMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGraduateEmployAsMajorServlet() {
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
					 
					   int geam_id = jsons.getJSONObject(i).getInt(GraduateEmployAsMajorTable.GEAM_ID);				
									
						String geam_comments =  jsons.getJSONObject(i).getString(GraduateEmployAsMajorTable.GEAM_COMMENTS);
						if(null == geam_comments){
							geam_comments = "";
						}
						
					Map<String,String> params= new HashMap<String, String>();
					 params.put(GraduateEmployAsMajorTable.GEAM_ID, geam_id+"");					  
					    params.put(GraduateEmployAsMajorTable.GEAM_COMMENTS, geam_comments);
					
					GraduateEmployAsMajorDao GraduateEmployAsMajorDao = new GraduateEmployAsMajorDaoImpl();
					int result = GraduateEmployAsMajorDao.alterGraduateEmployAsMajor(params, geam_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						   
			    int geam_id = json.getInt(GraduateEmployAsMajorTable.GEAM_ID);				
				String geam_majorcodeinschool = json.getString(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL);
				String geam_majornameinschool = json.getString(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL);				
				String graduatenum = json.getString(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
				Integer geam_graduatenum = -1;
				if(!"".equals(graduatenum)) geam_graduatenum = Integer.parseInt(graduatenum);
				
				
				int geam_isnull = 0;
				if("".equals(geam_majorcodeinschool)||"".equals(geam_majornameinschool)||"".equals(graduatenum))
				{
					geam_isnull = 1;
				}
				
				String geam_comments =  json.getString(GraduateEmployAsMajorTable.GEAM_COMMENTS);
				if(null == geam_comments){
					geam_comments = "";
				}
			
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(GraduateEmployAsMajorTable.GEAM_ID, geam_id+"");
			    params.put(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL, geam_majorcodeinschool);
			    params.put(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL, geam_majornameinschool);
			    params.put(GraduateEmployAsMajorTable.GEAM_GRADUATENUM, geam_graduatenum+"");
			   
			    params.put(GraduateEmployAsMajorTable.GEAM_COMMENTS, geam_comments);
			    params.put(GraduateEmployAsMajorTable.GEAM_ISNULL, geam_isnull+"");
			
			GraduateEmployAsMajorDao GraduateEmployAsMajorDao = new GraduateEmployAsMajorDaoImpl();
			int result = GraduateEmployAsMajorDao.alterGraduateEmployAsMajor(params, geam_id+"");	
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
