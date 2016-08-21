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

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.table.GraduateAndDoctoralTable;


/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateGraduateAndDoctoralServlet")
public class Sec_UpdateGraduateAndDoctoralServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGraduateAndDoctoralServlet() {
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
	
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		

		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String gd_id = jsons.getJSONObject(i).getString(GraduateAndDoctoralTable.GD_ID);
					String gd_comments = jsons.getJSONObject(i).getString(GraduateAndDoctoralTable.GD_COMMENTS);		
					params.put(GraduateAndDoctoralTable.GD_COMMENTS,gd_comments);
			
					GraduateAndDoctoralDao gdDao = new GraduateAndDoctoralDaoImpl();
					int result = gdDao.alterGraduateAndDoctoral(params, gd_id);
									
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				
				Map<String,String> params= new HashMap<String, String>();
				String gd_id = json.getString(GraduateAndDoctoralTable.GD_ID);
				String gd_name = json.getString(GraduateAndDoctoralTable.GD_NAME);
				String gd_code = json.getString(GraduateAndDoctoralTable.GD_CODE);			
				String gd_departmentname = json.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNAME);			
				String gd_departmentnumber = json.getString(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER);			
				String gd_type = json.getString( GraduateAndDoctoralTable.GD_TYPE);
				String gd_comments = json.getString(GraduateAndDoctoralTable.GD_COMMENTS);					
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(gd_name.equals("")||gd_code.equals("")||gd_departmentname.equals("")||gd_departmentnumber.equals("")||
						gd_type.equals(""))
					isnull = 1;
				
				
				params.put(GraduateAndDoctoralTable.GD_NAME,gd_name);
				params.put(GraduateAndDoctoralTable.GD_CODE,gd_code);
				params.put(GraduateAndDoctoralTable.GD_DEPARTMENTNAME,gd_departmentname);
				params.put(GraduateAndDoctoralTable.GD_DEPARTMENTNUMBER,gd_departmentnumber);
				params.put(GraduateAndDoctoralTable.GD_TYPE,gd_type);
				params.put(GraduateAndDoctoralTable.GD_COMMENTS,gd_comments);
				params.put(GraduateAndDoctoralTable.ISNULL, isnull+"");
				GraduateAndDoctoralDao gdDao = new GraduateAndDoctoralDaoImpl();
				int result = gdDao.alterGraduateAndDoctoral(params, gd_id);
								
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
}
