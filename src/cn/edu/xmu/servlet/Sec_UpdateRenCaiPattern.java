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

import cn.edu.xmu.dao.RenCaiPatternDao;
import cn.edu.xmu.daoimpl.RenCaiPatternDaoImpl;
import cn.edu.xmu.table.RenCaiPatternTable;


/**
 * Servlet implementation class Sec_UpdateRenCaiPattern
 */
@WebServlet("/Sec_UpdateRenCaiPattern")
public class Sec_UpdateRenCaiPattern extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateRenCaiPattern() {
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
		//String college = request.getParameter(RenCaiPatternTable.RCP_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String rcp_id = json.getJSONObject(i).getString(
							RenCaiPatternTable.RCP_ID);
					String rcp_comments = json.getJSONObject(i).getString(
							RenCaiPatternTable.RCP_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(RenCaiPatternTable.RCP_COMMENTS, rcp_comments);
					RenCaiPatternDao rcpDao = new RenCaiPatternDaoImpl();
					int result = rcpDao.alterRenCaiPattern(params, rcp_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String rcp_id = json.getString(RenCaiPatternTable.RCP_ID);
				String rcp_college1 = json.getString(RenCaiPatternTable.RCP_COLLEGE1);
				
				String rcp_project = json.getString(RenCaiPatternTable.RCP_PROJECT);
				String rcp_head = json.getString(RenCaiPatternTable.RCP_HEAD);
				String rcp_type = json.getString(RenCaiPatternTable.RCP_TYPE);
				String rcp_level = json.getString(RenCaiPatternTable.RCP_LEVEL);
				
				String rcp_starttime = json.getString(RenCaiPatternTable.RCP_STARTTIME);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(rcp_college1.equals("")||rcp_project.equals("")||rcp_head.equals("")||rcp_type.equals("")||rcp_level.equals("")||
						rcp_starttime.equals(""))
					isnull = 1;
				
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(RenCaiPatternTable.RCP_COMMENTS)){
					String rcp_comments = json.getString(RenCaiPatternTable.RCP_COMMENTS);
					params.put(RenCaiPatternTable.RCP_COMMENTS, rcp_comments);
				}
				if(json.has(RenCaiPatternTable.RCP_DEADLINE)){
					String rcp_deadline = json.getString(RenCaiPatternTable.RCP_DEADLINE);
					params.put(RenCaiPatternTable.RCP_DEADLINE, rcp_deadline);
				}
				params.put(RenCaiPatternTable.RCP_COLLEGE1, rcp_college1);
				params.put(RenCaiPatternTable.RCP_PROJECT, rcp_project);
				params.put(RenCaiPatternTable.RCP_HEAD, rcp_head);
				params.put(RenCaiPatternTable.RCP_TYPE, rcp_type);
				params.put(RenCaiPatternTable.RCP_LEVEL, rcp_level);
				if(!rcp_starttime.equals("")){//如果时间为空则不写入
					params.put(RenCaiPatternTable.RCP_STARTTIME, rcp_starttime);
				}
				params.put(RenCaiPatternTable.ISNULL, isnull+"");
				
				RenCaiPatternDao rcpDao = new RenCaiPatternDaoImpl();
				int result = rcpDao.alterRenCaiPattern(params, rcp_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
