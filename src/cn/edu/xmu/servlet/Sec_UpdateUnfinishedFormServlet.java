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

import cn.edu.xmu.dao.UnfinishedFormDao;
import cn.edu.xmu.daoimpl.UnfinishedFormDaoImpl;
import cn.edu.xmu.table.UnfinishedFormTable;

/**
 * 未填表格说明
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdateUnfinishedFormServlet")
public class Sec_UpdateUnfinishedFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUnfinishedFormServlet() {
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
					   String uf_id = jsons.getJSONObject(i).getString(UnfinishedFormTable.UF_ID);					   
					   String uf_comments = jsons.getJSONObject(i).getString(UnfinishedFormTable.UF_COMMENTS);
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(UnfinishedFormTable.UF_ID, uf_id);					 
					params.put(UnfinishedFormTable.UF_COMMENTS, uf_comments);
					
					UnfinishedFormDao unfinishedFormDao = new UnfinishedFormDaoImpl();
					int result = unfinishedFormDao.alterUnfinishedForm(params, uf_id);
					out.print(result);
				}
			}else {
				
			JSONObject json = new JSONObject(data);
			String uf_id = json.getString(UnfinishedFormTable.UF_ID); 
			String uf_formname = json.getString(UnfinishedFormTable.UF_FORMNAME);   
			String uf_accountfor  = json.getString(UnfinishedFormTable.UF_ACCOUNTFOR); 
			
			String uf_comments = json.getString(UnfinishedFormTable.UF_COMMENTS);
			int uf_isnull = 0;
			if("".equals(uf_id)||"".equals(uf_formname)||"".equals(uf_accountfor))
			{
				uf_isnull = 1;
			}				
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(UnfinishedFormTable.UF_ID, uf_id);
			params.put(UnfinishedFormTable.UF_FORMNAME, uf_formname);
			params.put(UnfinishedFormTable.UF_ACCOUNTFOR, uf_accountfor);
			params.put(UnfinishedFormTable.UF_COMMENTS, uf_comments);
			params.put(UnfinishedFormTable.UF_ISNULL, uf_isnull+"");
			
			UnfinishedFormDao unfinishedFormDao = new UnfinishedFormDaoImpl();
			int result = unfinishedFormDao.alterUnfinishedForm(params, uf_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

