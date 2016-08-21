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

import cn.edu.xmu.dao.ExpTeachCenterDao;
import cn.edu.xmu.daoimpl.ExpTeachCenterDaoImpl;
import cn.edu.xmu.table.ExpTeachCenterTable;



/**
 * Servlet implementation class Sec_UpdateExpTeachCenter
 */
@WebServlet("/Sec_UpdateExpTeachCenter")
public class Sec_UpdateExpTeachCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateExpTeachCenter() {
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
		//String college = request.getParameter(ExpTeachCenterTable.ETC_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String etc_id = json.getJSONObject(i).getString(
							ExpTeachCenterTable.ETC_ID);
					String etc_comments = json.getJSONObject(i).getString(
							ExpTeachCenterTable.ETC_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(ExpTeachCenterTable.ETC_COMMENTS, etc_comments);
					ExpTeachCenterDao etcDao = new ExpTeachCenterDaoImpl();
					int result = etcDao.alterExpTeachCenter(params, etc_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String etc_id = json.getString(ExpTeachCenterTable.ETC_ID);
				String etc_expteachcentername = json.getString(ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME);
				
				String etc_subjectname = json.getString(ExpTeachCenterTable.ETC_SUBJECTNAME);
				String etc_subjectcode = json.getString(ExpTeachCenterTable.ETC_SUBJECTCODE);
				String etc_levelnum = json.getString(ExpTeachCenterTable.ETC_LEVELNUM);
				String etc_levelname = json.getString(ExpTeachCenterTable.ETC_LEVELNAME);
				
				String etc_starttime = json.getString(ExpTeachCenterTable.ETC_STARTTIME);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(etc_expteachcentername.equals("")||etc_subjectname.equals("")||etc_subjectcode.equals("")||etc_levelnum.equals("")||etc_levelname.equals("")||
						etc_starttime.equals(""))
					isnull = 1;
				
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(ExpTeachCenterTable.ETC_COMMENTS)){
					String etc_comments = json.getString(ExpTeachCenterTable.ETC_COMMENTS);
					params.put(ExpTeachCenterTable.ETC_COMMENTS, etc_comments);
				}
				if(json.has(ExpTeachCenterTable.ETC_DEADLINE)){
					String etc_deadline = json.getString(ExpTeachCenterTable.ETC_DEADLINE);
					params.put(ExpTeachCenterTable.ETC_DEADLINE, etc_deadline);
				}
				params.put(ExpTeachCenterTable.ETC_EXPTEACHCENTERNAME, etc_expteachcentername);
				params.put(ExpTeachCenterTable.ETC_SUBJECTNAME, etc_subjectname);
				params.put(ExpTeachCenterTable.ETC_SUBJECTCODE, etc_subjectcode);
				params.put(ExpTeachCenterTable.ETC_LEVELNUM, etc_levelnum);
				params.put(ExpTeachCenterTable.ETC_LEVELNAME, etc_levelname);
				if(!etc_starttime.equals("")){//如果时间为空则不写入
					params.put(ExpTeachCenterTable.ETC_STARTTIME, etc_starttime);
				}
				else{
					params.put(ExpTeachCenterTable.ETC_STARTTIME, "1800-1-1");
				}
				params.put(ExpTeachCenterTable.ISNULL, isnull+"");
				
				ExpTeachCenterDao etcDao = new ExpTeachCenterDaoImpl();
				int result = etcDao.alterExpTeachCenter(params, etc_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
