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

import cn.edu.xmu.dao.ExpertLectureDao;
import cn.edu.xmu.daoimpl.ExpertLectureDaoImpl;
import cn.edu.xmu.table.ExpertLectureTable;

/**
 * Servlet implementation class Sec_UpdateExpertLecture
 */
@WebServlet("/UpdateExpertLecture")
public class Sec_UpdateExpertLecture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateExpertLecture() {
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
		//String college = request.getParameter(ExpertLectureTable.EL_COLLEGE);
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
					String el_id = jsons.getJSONObject(i).getString(ExpertLectureTable.EL_ID);
					String el_comments = jsons.getJSONObject(i).getString(ExpertLectureTable.EL_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(ExpertLectureTable.EL_ID, el_id);
					params.put(ExpertLectureTable.EL_COMMENTS, el_comments);
				
					ExpertLectureDao elDao = new ExpertLectureDaoImpl();
					int result = elDao.alterExpertLecture(params, el_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String el_id = json.getString(ExpertLectureTable.EL_ID);

				String el_collegename = json.getString(ExpertLectureTable.EL_COLLEGENAME);
				String el_title = json.getString(ExpertLectureTable.EL_TITLE);
				String el_grade = json.getString(ExpertLectureTable.EL_GRADE);
				String el_term = json.getString(ExpertLectureTable.EL_TERM);
				String el_type = json.getString(ExpertLectureTable.EL_TYPE);

				String el_name = json.getString(ExpertLectureTable.EL_NAME);
				String el_prorank = json.getString(ExpertLectureTable.EL_PRORANK);
				String el_unit = json.getString(ExpertLectureTable.EL_UNIT);
				String el_area = json.getString(ExpertLectureTable.EL_AREA);
				String el_remark = json.getString(ExpertLectureTable.EL_REMARK);
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(el_collegename.equals("") || el_title.equals("") || el_grade.equals("") || el_term.equals("") ||
						el_type.equals("") || el_name.equals("") || el_prorank.equals("") || el_unit.equals("") ||
						el_area.equals("") || el_remark.equals(""))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(ExpertLectureTable.EL_ID, el_id);
				params.put(ExpertLectureTable.EL_COLLEGENAME, el_collegename);
				params.put(ExpertLectureTable.EL_TITLE, el_title);
				params.put(ExpertLectureTable.EL_GRADE, el_grade);
				params.put(ExpertLectureTable.EL_TERM, el_term);
				params.put(ExpertLectureTable.EL_TYPE, el_type);

				params.put(ExpertLectureTable.EL_NAME, el_name);
				params.put(ExpertLectureTable.EL_PRORANK, el_prorank);
				params.put(ExpertLectureTable.EL_UNIT, el_unit);
				params.put(ExpertLectureTable.EL_AREA, el_area);
				params.put(ExpertLectureTable.EL_REMARK, el_remark);
				params.put(ExpertLectureTable.ISNULL, isnull+"");
			
				ExpertLectureDao elDao = new ExpertLectureDaoImpl();
				int result = elDao.alterExpertLecture(params, el_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
