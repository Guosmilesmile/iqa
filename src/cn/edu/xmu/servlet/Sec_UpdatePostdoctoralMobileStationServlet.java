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

import cn.edu.xmu.dao.PostdoctoralMobileStationDao;
import cn.edu.xmu.daoimpl.PostdoctoralMobileStationDaoImpl;
import cn.edu.xmu.table.PostdoctoralMobileStationTable;

/**
 * 表4-1-2  博士后流动站   (时点)
 * @author yue
 *
 */
@WebServlet("/Sec_UpdatePostdoctoralMobileStationServlet")
public class Sec_UpdatePostdoctoralMobileStationServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdatePostdoctoralMobileStationServlet() {
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
					String pms_id = jsons.getJSONObject(i).getString(PostdoctoralMobileStationTable.PMS_ID);
					String pms_comments = jsons.getJSONObject(i).getString(PostdoctoralMobileStationTable.PMS_COMMENTS);
					params.put(PostdoctoralMobileStationTable.PMS_COMMENTS, pms_comments);
					PostdoctoralMobileStationDao pmsDao = new PostdoctoralMobileStationDaoImpl();
					int result = pmsDao.alterPostdoctoralMobileStation(params, pms_id);
					out.println(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map <String,String> params= new HashMap<String, String>();
				String pms_id = json.getString(PostdoctoralMobileStationTable.PMS_ID);
				String pms_departmentnumber = json.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER);
				String pms_departmentname = json.getString(PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME);
				String pms_stationname = json.getString(PostdoctoralMobileStationTable.PMS_STATIONNAME); 
				String pms_comments = json.getString(PostdoctoralMobileStationTable.PMS_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(pms_departmentnumber.equals("")||pms_departmentname.equals("")||pms_stationname.equals(""))
					isnull = 1;
				
				params.put(PostdoctoralMobileStationTable.PMS_DEPARTMENTNUMBER, pms_departmentnumber);
				params.put(PostdoctoralMobileStationTable.PMS_DEPARTMENTNAME,pms_departmentname);
				params.put(PostdoctoralMobileStationTable.PMS_STATIONNAME,pms_stationname);
				params.put(PostdoctoralMobileStationTable.PMS_COMMENTS, pms_comments);
				params.put(PostdoctoralMobileStationTable.ISNULL, isnull+"");
				PostdoctoralMobileStationDao pmsDao = new PostdoctoralMobileStationDaoImpl();
				int result = pmsDao.alterPostdoctoralMobileStation(params, pms_id);
				out.println(result);
			}
		}catch (JSONException e) {
				e.printStackTrace();
		}finally{
				out.close();
		}
	}
				
				
			
}
