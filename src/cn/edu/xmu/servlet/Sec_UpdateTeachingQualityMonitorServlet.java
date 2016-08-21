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

import cn.edu.xmu.dao.TeachingQualityMonitorDao;
import cn.edu.xmu.daoimpl.TeachingQualityMonitorDaoImpl;
import cn.edu.xmu.table.TeachingQualityMonitorTable;

/**
 * 7-4
 * @author chunfeng
 *
 */
@WebServlet("/Sec_UpdateTeachingQualityMonitorServlet")
public class Sec_UpdateTeachingQualityMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeachingQualityMonitorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
					   String tqm_id = jsons.getJSONObject(i).getString(TeachingQualityMonitorTable.TQM_ID);					   
					   String tqm_comments = jsons.getJSONObject(i).getString(TeachingQualityMonitorTable.TQM_COMMENTS);
										
					Map<String,String> params= new HashMap<String, String>();
					params.put(TeachingQualityMonitorTable.TQM_ID, tqm_id);					 
					params.put(TeachingQualityMonitorTable.TQM_COMMENTS, tqm_comments);
					
					TeachingQualityMonitorDao teachingQualityMonitorDao = new TeachingQualityMonitorDaoImpl();
					int result = teachingQualityMonitorDao.alterTeachingQualityMonitor(params, tqm_id);
					out.print(result);
				}
			}else {
				
			JSONObject json = new JSONObject(data);
			String tqm_id = json.getString(TeachingQualityMonitorTable.TQM_ID); 
			String tqm_managesyscontent = json.getString(TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT);   
			String tqm_assessexe  = json.getString(TeachingQualityMonitorTable.TQM_ASSESSEXE); 
			String tqm_annualreport = json.getString(TeachingQualityMonitorTable.TQM_ANNUALREPORT);  
			
			String tqm_comments = json.getString(TeachingQualityMonitorTable.TQM_COMMENTS);
			int tqm_isnull = 0;
			if("".equals(tqm_id)||"".equals(tqm_managesyscontent)||"".equals(tqm_assessexe)||"".equals(tqm_annualreport))
			{
				tqm_isnull = 1;
			}				
			
			Map<String,String> params= new HashMap<String, String>();
			params.put(TeachingQualityMonitorTable.TQM_ID, tqm_id);
			params.put(TeachingQualityMonitorTable.TQM_MANAGESYSCONTENT, tqm_managesyscontent);
			params.put(TeachingQualityMonitorTable.TQM_ASSESSEXE, tqm_assessexe);
			params.put(TeachingQualityMonitorTable.TQM_ANNUALREPORT, tqm_annualreport);
			
			params.put(TeachingQualityMonitorTable.TQM_COMMENTS, tqm_comments);
			params.put(TeachingQualityMonitorTable.TQM_ISNULL, tqm_isnull+"");
			
			TeachingQualityMonitorDao teachingQualityMonitorDao = new TeachingQualityMonitorDaoImpl();
			int result = teachingQualityMonitorDao.alterTeachingQualityMonitor(params, tqm_id);
			
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

