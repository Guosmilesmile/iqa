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

import cn.edu.xmu.dao.StudentCenterDao;
import cn.edu.xmu.daoimpl.StudentCenterDaoImpl;
import cn.edu.xmu.table.StudentCenterTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateStudentCenterServlet")
public class Sec_UpdateStudentCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStudentCenterServlet() {
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
					   int sc_id = jsons.getJSONObject(i).getInt(StudentCenterTable.SC_ID);
					  			 
					   String sc_comments = jsons.getJSONObject(i).getString(StudentCenterTable.SC_COMMENTS);
								
					   Map<String,String> params= new HashMap<String, String>();
					    params.put(StudentCenterTable.SC_ID, sc_id+"");
					  
					    params.put(StudentCenterTable.SC_COMMENTS, sc_comments);
					
					    StudentCenterDao studentCenterDao = new StudentCenterDaoImpl();
					    int result = studentCenterDao.alterStudentCenter(params, sc_id+"");
					    out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						  			    
			    int sc_id = json.getInt(StudentCenterTable.SC_ID);
				 String sc_project = json.getString(StudentCenterTable.SC_PROJECT);
				 String quantity = json.getString(StudentCenterTable.SC_QUANTITY); 
					Integer sc_quantity = -1;
					if(!"".equals(quantity)) sc_quantity = Integer.parseInt(quantity);
					String area = json.getString(StudentCenterTable.SC_AREA);
					Float sc_area = (float) -1.0;
					if(!"".equals(area)) sc_area = Float.parseFloat(area); 
					
					int sc_isnull = 0;
					if("".equals(sc_project)||"".equals(quantity)||"".equals(area)){
						sc_isnull = 1;
					}
						   
							 
				   String sc_comments = json.getString(StudentCenterTable.SC_COMMENTS);
							
				   Map<String,String> params= new HashMap<String, String>();
				   params.put(StudentCenterTable.SC_ID, sc_id+"");
				    params.put(StudentCenterTable.SC_PROJECT, sc_project);
				    params.put(StudentCenterTable.SC_QUANTITY, sc_quantity+"");
				    params.put(StudentCenterTable.SC_AREA, sc_area+"");				    
				    params.put(StudentCenterTable.SC_COMMENTS, sc_comments);
				    params.put(StudentCenterTable.SC_ISNULL, sc_isnull+"");
				    
				    StudentCenterDao studentCenterDao = new StudentCenterDaoImpl();
				    int result = studentCenterDao.alterStudentCenter(params, sc_id+"");
				    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
