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

import cn.edu.xmu.dao.GoInterConferDao;
import cn.edu.xmu.daoimpl.GoInterConferDaoImpl;
import cn.edu.xmu.table.GoInterConferTable;


/**
 * Servlet implementation class Sec_UpdateGoInterConfer
 */
@WebServlet("/Sec_UpdateGoInterConfer")
public class Sec_UpdateGoInterConfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateGoInterConfer() {
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
		//String college = request.getParameter(GoInterConferTable.GIC_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String gic_id = json.getJSONObject(i).getString(
							GoInterConferTable.GIC_ID);
					String gic_comments = json.getJSONObject(i).getString(
							GoInterConferTable.GIC_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(GoInterConferTable.GIC_COMMENTS, gic_comments);
					GoInterConferDao gicDao = new GoInterConferDaoImpl();
					int result = gicDao.alterGoInterConfer(params, gic_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String gic_id = json.getString(GoInterConferTable.GIC_ID);
				String gic_college1 = json.getString(GoInterConferTable.GIC_COLLEGE1);
				String gic_major = json.getString(GoInterConferTable.GIC_MAJOR);
				String gic_grade = json.getString(GoInterConferTable.GIC_GRADE);
				String gic_stunum = json.getString(GoInterConferTable.GIC_STUNUM);
				String gic_name = json.getString(GoInterConferTable.GIC_NAME);
				String gic_intername = json.getString(GoInterConferTable.GIC_INTERNAME);
				String gic_paperortitle = json.getString(GoInterConferTable.GIC_PAPERORTITLE);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(gic_college1.equals("")||gic_major.equals("")||gic_grade.equals("")||gic_stunum.equals("")||
						gic_name.equals("")||gic_intername.equals("")||gic_paperortitle.equals(""))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(GoInterConferTable.GIC_COMMENTS)){
					String gic_comments = json.getString(GoInterConferTable.GIC_COMMENTS);
					params.put(GoInterConferTable.GIC_COMMENTS, gic_comments);
				}
				if(json.has(GoInterConferTable.GIC_DEADLINE)){
					String gic_deadline = json.getString(GoInterConferTable.GIC_DEADLINE);
					params.put(GoInterConferTable.GIC_DEADLINE, gic_deadline);
				}
				params.put(GoInterConferTable.GIC_COLLEGE1, gic_college1);
				params.put(GoInterConferTable.GIC_MAJOR, gic_major);
				params.put(GoInterConferTable.GIC_GRADE, gic_grade);
				params.put(GoInterConferTable.GIC_STUNUM, gic_stunum);
				params.put(GoInterConferTable.GIC_NAME, gic_name);
				params.put(GoInterConferTable.GIC_INTERNAME, gic_intername);
				params.put(GoInterConferTable.GIC_PAPERORTITLE, gic_paperortitle);
				params.put(GoInterConferTable.ISNULL, isnull+"");
				
				GoInterConferDao gicDao = new GoInterConferDaoImpl();
				int result = gicDao.alterGoInterConfer(params, gic_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
