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

import cn.edu.xmu.dao.BenkePunishDao;
import cn.edu.xmu.daoimpl.BenkePunishDaoImpl;
import cn.edu.xmu.table.BenkePunishTable;


/**
 * Servlet implementation class Sec_UpdateBenkePunish
 */
@WebServlet("/Sec_UpdateBenkePunish")
public class Sec_UpdateBenkePunish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateBenkePunish() {
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
		//解码
		//String college = request.getParameter(BenkePunishTable.BP_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		System.out.println("====!!!!!!!!!");
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String bp_id = json.getJSONObject(i).getString(
							BenkePunishTable.BP_ID);
					String bp_comments = json.getJSONObject(i).getString(
							BenkePunishTable.BP_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(BenkePunishTable.BP_COMMENTS, bp_comments);
					BenkePunishDao bpDao = new BenkePunishDaoImpl();
					int result = bpDao.alterBenkePunish(params, bp_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String bp_id = json.getString(BenkePunishTable.BP_ID);
				String bp_college1 = json.getString(BenkePunishTable.BP_COLLEGE1);
				String bp_warning = json.getString(BenkePunishTable.BP_WARNING);
				String bp_demerit = json.getString(BenkePunishTable.BP_DEMERIT);
				String bp_probation = json.getString(BenkePunishTable.BP_PROBATION);
				String bp_expulsion = json.getString(BenkePunishTable.BP_EXPULSION);
				String bp_totalmount = json.getString(BenkePunishTable.BP_TOTALMOUNT);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(bp_college1.equals("")||bp_warning.equals("")||bp_demerit.equals("")||bp_probation.equals("")||
						bp_probation.equals("")||bp_expulsion.equals("")||bp_totalmount.equals(""))
					isnull = 1;
				
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(BenkePunishTable.BP_COMMENTS)){
					String bp_comments = json.getString(BenkePunishTable.BP_COMMENTS);
					params.put(BenkePunishTable.BP_COMMENTS, bp_comments);
				}
				if(json.has(BenkePunishTable.BP_DEADLINE)){
					String bp_deadline = json.getString(BenkePunishTable.BP_DEADLINE);
					params.put(BenkePunishTable.BP_DEADLINE, bp_deadline);
				}
				
				
				params.put(BenkePunishTable.BP_COLLEGE1, bp_college1);
				params.put(BenkePunishTable.BP_WARNING, bp_warning);
				params.put(BenkePunishTable.BP_DEMERIT, bp_demerit);
				params.put(BenkePunishTable.BP_PROBATION, bp_probation);
				params.put(BenkePunishTable.BP_EXPULSION, bp_expulsion);
				params.put(BenkePunishTable.BP_TOTALMOUNT, bp_totalmount);
				params.put(BenkePunishTable.ISNULL, isnull+"");
				
				BenkePunishDao bpDao = new BenkePunishDaoImpl();
				int result = bpDao.alterBenkePunish(params, bp_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
