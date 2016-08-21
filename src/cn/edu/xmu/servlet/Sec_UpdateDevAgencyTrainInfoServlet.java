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

import cn.edu.xmu.dao.DevAgencyTrainInfoDao;
import cn.edu.xmu.daoimpl.DevAgencyTrainInfoDaoImpl;
import cn.edu.xmu.table.DevAgencyTrainInfoTable;

/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateDevAgencyTrainInfoServlet")
public class Sec_UpdateDevAgencyTrainInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateDevAgencyTrainInfoServlet() {
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
					String dati_id = jsons.getJSONObject(i).getString(DevAgencyTrainInfoTable.DATI_ID);
					String dati_comments = jsons.getJSONObject(i).getString(DevAgencyTrainInfoTable.DATI_COMMENTS);
					params.put(DevAgencyTrainInfoTable.DATI_COMMENTS, dati_comments);
					
					DevAgencyTrainInfoDao datiDao = new DevAgencyTrainInfoDaoImpl();
					int result  =datiDao.alterDevAgencyTrainInfo(params, dati_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String dati_id = json.getString(DevAgencyTrainInfoTable.DATI_ID);
				String dati_name = json.getString(DevAgencyTrainInfoTable.DATI_NAME);
				String dati_departmentnuame = json.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME);
				String dati_departmentnumber = json.getString(DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER);
				String dati_workplan = json.getString(DevAgencyTrainInfoTable.DATI_WORKPLAN);
				String dati_traintimes = json.getString(DevAgencyTrainInfoTable.DATI_TRAINTIMES);
				String dati_traintrips = json.getString(DevAgencyTrainInfoTable.DATI_TRAINTRIPS);
				String dati_comments = json.getString(DevAgencyTrainInfoTable.DATI_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(dati_name.equals("")||dati_departmentnuame.equals("")||dati_departmentnumber.equals("")||dati_workplan.equals("")||dati_traintimes.equals("")||
						dati_traintrips.equals(""))
					isnull = 1;
				
				
				if(dati_traintimes.equals(""))
					dati_traintimes = "-999";
				if(dati_traintrips.equals(""))
					dati_traintrips = "-999";
				
				
				params.put(DevAgencyTrainInfoTable.DATI_NAME, dati_name);
				params.put(DevAgencyTrainInfoTable.DATI_DEPARTMENTNAME, dati_departmentnuame);
				params.put(DevAgencyTrainInfoTable.DATI_DEPARTMENTNUMBER, dati_departmentnumber);
				params.put(DevAgencyTrainInfoTable.DATI_WORKPLAN, dati_workplan);
				params.put(DevAgencyTrainInfoTable.DATI_TRAINTIMES,dati_traintimes);
				params.put(DevAgencyTrainInfoTable.DATI_TRAINTRIPS, dati_traintrips);
				params.put(DevAgencyTrainInfoTable.DATI_COMMENTS, dati_comments);
				params.put(DevAgencyTrainInfoTable.ISNULL, isnull+"");
				DevAgencyTrainInfoDao datiDao = new DevAgencyTrainInfoDaoImpl();
				int result  =datiDao.alterDevAgencyTrainInfo(params, dati_id);
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
