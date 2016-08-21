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

import cn.edu.xmu.dao.AdmissionCriteriaAndNumberDao;
import cn.edu.xmu.daoimpl.AdmissionCriteriaAndNumberDaoImpl;
import cn.edu.xmu.table.AdmissionCriteriaAndNumberTable;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateAdmissionCriteriaAndNumberServlet")
public class Sec_UpdateAdmissionCriteriaAndNumberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateAdmissionCriteriaAndNumberServlet() {
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
					String acn_id = jsons.getJSONObject(i).getString(AdmissionCriteriaAndNumberTable.ACN_ID);
					String acn_comments = jsons.getJSONObject(i).getString(AdmissionCriteriaAndNumberTable.ANC_COMMENTS);
					params.put(AdmissionCriteriaAndNumberTable.ANC_COMMENTS, acn_comments);
					
					AdmissionCriteriaAndNumberDao acnDao = new AdmissionCriteriaAndNumberDaoImpl();
					int result  = acnDao.alterAdmissionCriteriaAndNumber(params, acn_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String acn_id = json.getString(AdmissionCriteriaAndNumberTable.ACN_ID);
				String acn_province = json.getString(AdmissionCriteriaAndNumberTable.ACN_PROVINCE);
				String acn_batch = json.getString(AdmissionCriteriaAndNumberTable.ACN_BATCH);
				String acn_artsadmission = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION);
				String acn_scienceadmission = json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION);
				String acn_artsminctrline = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE);
				String acn_scienceminctrline = json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE);
				String acn_artsavgscore = json.getString(AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE);
				String acn_scienceavgscore = json.getString(AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE);
				String acn_instruction = json.getString(AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION);
				String acn_comments = json.getString(AdmissionCriteriaAndNumberTable.ANC_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(acn_province.equals("") || acn_batch.equals("") || acn_artsadmission.equals("") || 
						acn_scienceadmission.equals("") || acn_artsminctrline.equals("") || acn_scienceminctrline.equals("") ||
						acn_artsavgscore.equals("") || acn_scienceavgscore.equals("") )
					isnull = 1;
				
				if(acn_artsadmission.equals(""))
					acn_artsadmission = "-999";
				if(acn_scienceadmission.equals(""))
					acn_scienceadmission = "-999";
				if(acn_artsminctrline.equals(""))
					acn_artsminctrline = "-999";
				if(acn_scienceminctrline.equals(""))
					acn_scienceminctrline = "-999";
				if(acn_artsavgscore.equals(""))
					acn_artsavgscore = "-999";
				if(acn_scienceavgscore.equals(""))
					acn_scienceavgscore = "-999";
				
				params.put(AdmissionCriteriaAndNumberTable.ACN_PROVINCE, acn_province);
				params.put(AdmissionCriteriaAndNumberTable.ACN_BATCH, acn_batch);
				params.put(AdmissionCriteriaAndNumberTable.ACN_ARTSADMISSION, acn_artsadmission);
				params.put(AdmissionCriteriaAndNumberTable.ACN_SCIENCEADMISSION,acn_scienceadmission);
				params.put(AdmissionCriteriaAndNumberTable.ACN_ARTSMINCTRLINE, acn_artsminctrline);
				params.put(AdmissionCriteriaAndNumberTable.ACN_SCIENCEMINCTRLINE, acn_scienceminctrline);
				params.put(AdmissionCriteriaAndNumberTable.ACN_ARTSAVGSCORE, acn_artsavgscore);
				params.put(AdmissionCriteriaAndNumberTable.ACN_SCIENCEAVGSCORE, acn_scienceavgscore);
				params.put(AdmissionCriteriaAndNumberTable.ACN_INSTRUCTION, acn_instruction);
				params.put(AdmissionCriteriaAndNumberTable.ANC_COMMENTS, acn_comments);
				params.put(AdmissionCriteriaAndNumberTable.ISNULL, isnull+"");
				AdmissionCriteriaAndNumberDao acnDao = new AdmissionCriteriaAndNumberDaoImpl();
				int result = acnDao.alterAdmissionCriteriaAndNumber(params, acn_id);
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
