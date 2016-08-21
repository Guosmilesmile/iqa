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

import cn.edu.xmu.dao.EnrollCategoryInfoDao;
import cn.edu.xmu.daoimpl.EnrollCategoryInfoDaoImpl;
import cn.edu.xmu.table.EnrollCategoryInfoTable;

@WebServlet("/Sec_UpdateEnrollCategoryInfoservlet")
public class Sec_UpdateEnrollCategoryInfoservlet extends HttpServlet{

private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateEnrollCategoryInfoservlet() {
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
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		

		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String eci_id = jsons.getJSONObject(i).getString(EnrollCategoryInfoTable.ECI_ID);
					String eci_comments = jsons.getJSONObject(i).getString(EnrollCategoryInfoTable.ECI_COMMENTS);
					params.put(EnrollCategoryInfoTable.ECI_COMMENTS, eci_comments);
					
					EnrollCategoryInfoDao eciDao = new EnrollCategoryInfoDaoImpl();
					int result = eciDao.alterEnrollCategoryInfo(params, eci_id);
			
			out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				
				String eci_id = json.getString(EnrollCategoryInfoTable.ECI_ID);
				String eci_plannumber = json.getString(EnrollCategoryInfoTable.ECI_PLANNUMBER);
				String eci_enrollnumber = json.getString(EnrollCategoryInfoTable.ECI_ENROLLNUMBER);
				String eci_registernumber = json.getString(EnrollCategoryInfoTable.ECI_REGISTERNUMBER);
				String eci_indrecruitmentnumber = json.getString(EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER);
				String eci_specialnumber = json.getString(EnrollCategoryInfoTable.ECI_SPECIALNUMBER);
				String eci_provincenumber = json.getString(EnrollCategoryInfoTable.ECI_PROVINCENUMBER);
				String eci_newspecialitynumber = json.getString(EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER);
				String eci_comments = json.getString(EnrollCategoryInfoTable.ECI_COMMENTS);
			
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(eci_plannumber.equals("")||eci_enrollnumber.equals("")||eci_registernumber.equals("")||eci_indrecruitmentnumber.equals("")||
						eci_specialnumber.equals("")||eci_provincenumber.equals("")||eci_newspecialitynumber.equals(""))
					isnull = 1;
				if(eci_plannumber.equals(""))
					eci_plannumber = "-999";
				if(eci_enrollnumber.equals(""))
					eci_enrollnumber = "-999";
				if(eci_registernumber.equals(""))
					eci_registernumber = "-999";
				if(eci_indrecruitmentnumber.equals(""))
					eci_indrecruitmentnumber = "-999";
				if(eci_specialnumber.equals(""))
					eci_specialnumber = "-999";
				if(eci_provincenumber.equals(""))
					eci_provincenumber = "-999";
				if(eci_newspecialitynumber.equals(""))
					eci_newspecialitynumber = "-999";		
				
				
				params.put(EnrollCategoryInfoTable.ECI_PLANNUMBER, eci_plannumber);
				params.put(EnrollCategoryInfoTable.ECI_ENROLLNUMBER, eci_enrollnumber);
				params.put(EnrollCategoryInfoTable.ECI_REGISTERNUMBER,eci_registernumber);
				params.put(EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER,eci_indrecruitmentnumber);
				params.put(EnrollCategoryInfoTable.ECI_SPECIALNUMBER, eci_specialnumber);
				params.put(EnrollCategoryInfoTable.ECI_PROVINCENUMBER, eci_provincenumber);
				params.put(EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER, eci_newspecialitynumber);
				params.put(EnrollCategoryInfoTable.ECI_COMMENTS, eci_comments);
				params.put(EnrollCategoryInfoTable.ISNULL, isnull+"");
				
				EnrollCategoryInfoDao eciDao = new EnrollCategoryInfoDaoImpl();
				int result = eciDao.alterEnrollCategoryInfo(params, eci_id);
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
}
