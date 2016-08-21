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

import cn.edu.xmu.dao.TeacherResearchNumberDao;
import cn.edu.xmu.daoimpl.TeacherResearchNumberDaoImpl;
import cn.edu.xmu.table.TeacherResearchNumberTable;

/**
 * Servlet implementation class Sec_UpdateTeacherResearchNumber
 */
@WebServlet("/UpdateTeacherResearchNumber")
public class Sec_UpdateTeacherResearchNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherResearchNumber() {
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
		//String college = request.getParameter(TeacherResearchNumberTable.TRN_COLLEGE);
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
					String trn_id = jsons.getJSONObject(i).getString(TeacherResearchNumberTable.TRN_ID);
					
					String trn_comments = jsons.getJSONObject(i).getString(TeacherResearchNumberTable.TRN_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(TeacherResearchNumberTable.TRN_ID, trn_id);
					
					params.put(TeacherResearchNumberTable.TRN_COMMENTS, trn_comments);
				
					TeacherResearchNumberDao trnDao = new TeacherResearchNumberDaoImpl();
					int result = trnDao.alterTeacherResearchNumber(params, trn_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String trn_id = json.getString(TeacherResearchNumberTable.TRN_ID);
				
				String trn_hrznprojectnum = json.getString(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM);
				if(trn_hrznprojectnum.equals(""))
					trn_hrznprojectnum = "-999";
				String trn_hrznhumanitiesnum = json.getString(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM);
				if(trn_hrznhumanitiesnum.equals(""))
					trn_hrznhumanitiesnum = "-999";
				String trn_vtclprojectnum = json.getString(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM);
				if(trn_vtclprojectnum.equals(""))
					trn_vtclprojectnum = "-999";
				String trn_vtclhumanitiesnum = json.getString(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM);
				if(trn_vtclhumanitiesnum.equals(""))
					trn_vtclhumanitiesnum = "-999";
				int trn_projectnum = -999;
				if(!trn_hrznprojectnum.equals("-999") && !trn_vtclprojectnum.equals("-999"))
					trn_projectnum = Integer.valueOf(trn_hrznprojectnum)+Integer.valueOf(trn_vtclprojectnum);
				
				String trn_hrznprojectcost = json.getString(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST);
				if(trn_hrznprojectcost.equals(""))
					trn_hrznprojectcost = "-999";
				String trn_hrznhumanitiescost = json.getString(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST);
				if(trn_hrznhumanitiescost.equals(""))
					trn_hrznhumanitiescost = "-999";
				String trn_vtclprojectcost = json.getString(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST);
				if(trn_vtclprojectcost.equals(""))
					trn_vtclprojectcost = "-999";
				String trn_vtclhumanitiescost = json.getString(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST);
				if(trn_vtclhumanitiescost.equals(""))
					trn_vtclhumanitiescost = "-999";
				float trn_projectcost = -999;
				if(!trn_hrznprojectcost.equals("-999") && !trn_vtclprojectcost.equals("-999"))
					trn_projectcost = Float.valueOf(trn_hrznprojectcost)+Float.valueOf(trn_vtclprojectcost);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(trn_hrznprojectnum.equals("-999") || trn_hrznhumanitiesnum.equals("-999") || trn_vtclprojectnum.equals("-999") ||
						trn_vtclhumanitiesnum.equals("-999") || trn_hrznprojectcost.equals("-999") ||
								trn_hrznhumanitiescost.equals("-999") || trn_vtclprojectcost.equals("-999") || trn_vtclhumanitiescost.equals("-999"))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(TeacherResearchNumberTable.TRN_ID, trn_id);
				
				params.put(TeacherResearchNumberTable.TRN_PROJECTNUM, trn_projectnum+"");
				params.put(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM, trn_hrznprojectnum);
				params.put(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM, trn_hrznhumanitiesnum);
				params.put(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM, trn_vtclprojectnum);
				params.put(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM, trn_vtclhumanitiesnum);


				params.put(TeacherResearchNumberTable.TRN_PROJECTCOST, trn_projectcost+"");
				params.put(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST, trn_hrznprojectcost);
				params.put(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST, trn_hrznhumanitiescost);
				params.put(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST, trn_vtclprojectcost);
				params.put(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST, trn_vtclhumanitiescost);
				params.put(TeacherResearchNumberTable.ISNULL, isnull+"");
				
			
				TeacherResearchNumberDao trnDao = new TeacherResearchNumberDaoImpl();
				int result = trnDao.alterTeacherResearchNumber(params, trn_id);
			
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
