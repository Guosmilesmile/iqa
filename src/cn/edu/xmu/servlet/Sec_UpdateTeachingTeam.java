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

import cn.edu.xmu.dao.TeachingTeamDao;
import cn.edu.xmu.daoimpl.TeachingTeamDaoImpl;
import cn.edu.xmu.table.TeachingTeamTable;

/**
 * Servlet implementation class Sec_UpdateTeachingTeam
 */
@WebServlet("/UpdateTeachingTeam")
public class Sec_UpdateTeachingTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeachingTeam() {
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
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		//String college = request.getParameter(TeachingTeamTable.TT_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					//无serialnumber,deadline,college
					String tt_id = jsons.getJSONObject(i).getString(TeachingTeamTable.TT_ID);
					String tt_comments = jsons.getJSONObject(i).getString(TeachingTeamTable.TT_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(TeachingTeamTable.TT_ID, tt_id);
					params.put(TeachingTeamTable.TT_COMMENTS, tt_comments);
				
					TeachingTeamDao ttDao = new TeachingTeamDaoImpl();
					int result = ttDao.alterTeachingTeam(params, tt_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String tt_id = json.getString(TeachingTeamTable.TT_ID);
				String tt_sequencenumber = json.getString(TeachingTeamTable.TT_SEQUENCENUMBER);
				if(tt_sequencenumber.equals(""))
					tt_sequencenumber = "-999";
				String tt_collegename = json.getString(TeachingTeamTable.TT_COLLEGENAME);
				String tt_name = json.getString(TeachingTeamTable.TT_NAME);
				String tt_leader = json.getString(TeachingTeamTable.TT_LEADER);
				String tt_country = json.getString(TeachingTeamTable.TT_COUNTRY);
				if(tt_country.equals(""))
					tt_country = "-999";
				String tt_province = json.getString(TeachingTeamTable.TT_PROVINCE);
				if(tt_province.equals(""))
					tt_province = "-999";
			
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(tt_sequencenumber.equals("-999") || tt_collegename.equals("") || tt_name.equals("") ||
						tt_leader.equals("") || tt_country.equals("-999") || tt_province.equals("-999"))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(TeachingTeamTable.TT_ID, tt_id);
				params.put(TeachingTeamTable.TT_SEQUENCENUMBER, tt_sequencenumber);
				params.put(TeachingTeamTable.TT_COLLEGENAME, tt_collegename);
				params.put(TeachingTeamTable.TT_NAME, tt_name);
				params.put(TeachingTeamTable.TT_LEADER, tt_leader);
				params.put(TeachingTeamTable.TT_COUNTRY, tt_country);
				params.put(TeachingTeamTable.TT_PROVINCE, tt_province);
				params.put(TeachingTeamTable.ISNULL, isnull+"");
			
				TeachingTeamDao ttDao = new TeachingTeamDaoImpl();
				int result = ttDao.alterTeachingTeam(params, tt_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
