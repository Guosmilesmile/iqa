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

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.table.ForeignExchangeTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateForeignExchangeServlet")
public class Sec_UpdateForeignExchangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateForeignExchangeServlet() {
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
					   String fe_id = jsons.getJSONObject(i).getString(ForeignExchangeTable.FE_ID);					   
					   String fe_comments = jsons.getJSONObject(i).getString(ForeignExchangeTable.FE_COMMENTS);
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(ForeignExchangeTable.FE_ID, fe_id);					 
					params.put(ForeignExchangeTable.FE_COMMENTS, fe_comments);
					
					ForeignExchangeDao foreignEchangeDao = new ForeignExchangeDaoImpl();
					int result = foreignEchangeDao.alterForeignExchange(params, fe_id);
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
			
			    String fe_id = json.getString(ForeignExchangeTable.FE_ID);
			    String fe_collegename = json.getString(ForeignExchangeTable.FE_COLLEGENAME);
			    String fe_projectname = json.getString(ForeignExchangeTable.FE_PROJECTNAME);
			    String fe_iscsc = json.getString(ForeignExchangeTable.FE_ISCSC);
			    String fe_country  = json.getString(ForeignExchangeTable.FE_COUNTRY);
			    String fe_school = json.getString(ForeignExchangeTable.FE_SCHOOL);
			    String fe_level = json.getString(ForeignExchangeTable.FE_LEVEL);
			    String fe_time = json.getString(ForeignExchangeTable.FE_TIME);
			    String selftoforeign = json.getString(ForeignExchangeTable.FE_SELFTOFOREIGN);
			    Integer fe_selftoforeign = -1;
				if(!selftoforeign.equals("")) fe_selftoforeign = Integer.parseInt(selftoforeign);				
			    String foreigntoself = json.getString(ForeignExchangeTable.FE_FOREIGNTOSELF);
			    Integer fe_foreigntoself = -1;
				if(!foreigntoself.equals("")) fe_foreigntoself = Integer.parseInt(foreigntoself);
			    String fe_comments = json.getString(ForeignExchangeTable.FE_COMMENTS);
			    int fe_isnull = 0;
			    if("".equals(fe_collegename)||"".equals(fe_projectname)||"".equals(fe_iscsc)||"".equals(fe_country)
						|| "".equals(fe_school) || "".equals(fe_level) || "".equals(fe_time) || "".equals(selftoforeign) ||"".equals(foreigntoself))
				{
					fe_isnull = 1;
				}
			    
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(ForeignExchangeTable.FE_ID, fe_id);
			    params.put(ForeignExchangeTable.FE_COLLEGENAME, fe_collegename);
			    params.put(ForeignExchangeTable.FE_PROJECTNAME, fe_projectname);
			    params.put(ForeignExchangeTable.FE_ISCSC, fe_iscsc);
			    params.put(ForeignExchangeTable.FE_COUNTRY, fe_country);
			    params.put(ForeignExchangeTable.FE_SCHOOL, fe_school);
			    params.put(ForeignExchangeTable.FE_LEVEL, fe_level);
			    params.put(ForeignExchangeTable.FE_TIME, fe_time);
			    params.put(ForeignExchangeTable.FE_SELFTOFOREIGN, fe_selftoforeign+"");
			    params.put(ForeignExchangeTable.FE_FOREIGNTOSELF, fe_foreigntoself+"");
			    params.put(ForeignExchangeTable.FE_COMMENTS, fe_comments);
			    params.put(ForeignExchangeTable.FE_ISNULL, fe_isnull+"");
			    
			    ForeignExchangeDao foreignEchangeDao = new ForeignExchangeDaoImpl();
			    int result = foreignEchangeDao.alterForeignExchange(params, fe_id);
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
