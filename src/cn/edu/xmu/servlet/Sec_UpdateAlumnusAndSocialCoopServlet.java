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

import cn.edu.xmu.dao.AlumnusAndSocialCoopDao;
import cn.edu.xmu.daoimpl.AlumnusAndSocialCoopDaoImpl;
import cn.edu.xmu.table.AlumnusAndSocialCoppTable;

/*
 * 1-7
 */
@WebServlet("/Sec_UpdateAlumnusAndSocialCoopServlet")
public class Sec_UpdateAlumnusAndSocialCoopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateAlumnusAndSocialCoopServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));

		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if(patter!= null && "batch".equals(patter)){
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";	
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
			String as_id = jsons.getJSONObject(i).getString(AlumnusAndSocialCoppTable.AS_ID);
			String as_comments = jsons.getJSONObject(i).getString(AlumnusAndSocialCoppTable.AS_COMMENTS);
			params.put(AlumnusAndSocialCoppTable.AS_COMMENTS, as_comments);
			
			AlumnusAndSocialCoopDao asDao = new AlumnusAndSocialCoopDaoImpl();
			int result = asDao.alterAlumnusAndSocialCoop(params, as_id);
			
			out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				String as_id = json.getString(AlumnusAndSocialCoppTable.AS_ID);
				String as_alumnusamount = json.getString(AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT);
				String as_domesticalumnus = json.getString(AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS);
				String as_overseaalumnus = json.getString(AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS);
				String as_agencyamount = json.getString(AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT);
				String as_academic = json.getString(AlumnusAndSocialCoppTable.AS_ACADEMIC);
				String as_industry = json.getString(AlumnusAndSocialCoppTable.AS_INDUSTRY);
				String as_government = json.getString(AlumnusAndSocialCoppTable.AS_GOVERNMENT);
				String as_comments = json.getString(AlumnusAndSocialCoppTable.AS_COMMENTS);
			
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(as_alumnusamount.equals("") || as_domesticalumnus.equals("") || as_overseaalumnus.equals("") || 
						as_agencyamount.equals("") || as_academic.equals("") || as_industry.equals("") ||
						as_government.equals("") ){
					isnull = 1;
					}
				if(as_alumnusamount.equals("") || as_alumnusamount == null)
					as_alumnusamount = "-999";
				if(as_domesticalumnus.equals("") || as_domesticalumnus == null)
					as_domesticalumnus = "-999";
				if(as_overseaalumnus.equals("") || as_overseaalumnus == null)
					as_overseaalumnus = "-999";
				if(as_agencyamount.equals("") || as_agencyamount == null)
					as_agencyamount = "-999";
				if(as_academic.equals("") || as_academic == null)
					as_academic = "-999";
				if(as_industry.equals("") || as_industry == null)
					as_industry = "-999";
				if(as_government.equals("") || as_government == null)
					as_government = "-999";
				Map<String,String> params= new HashMap<String, String>();
				params.put(AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT, as_alumnusamount);
				params.put(AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS, as_domesticalumnus);
				params.put(AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS, as_overseaalumnus);
				params.put(AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT,as_agencyamount);
				params.put(AlumnusAndSocialCoppTable.AS_ACADEMIC, as_academic);
				params.put(AlumnusAndSocialCoppTable.AS_INDUSTRY, as_industry);
				params.put(AlumnusAndSocialCoppTable.AS_GOVERNMENT, as_government);
				params.put(AlumnusAndSocialCoppTable.AS_COMMENTS, as_comments);
				params.put(AlumnusAndSocialCoppTable.ISNULL, isnull+"");
				
				
				AlumnusAndSocialCoopDao asDao = new AlumnusAndSocialCoopDaoImpl();
				int result = asDao.alterAlumnusAndSocialCoop(params, as_id);
			
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
