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

import cn.edu.xmu.dao.UndergraEnrollRateDao;
import cn.edu.xmu.daoimpl.UndergraEnrollRateDaoImpl;
import cn.edu.xmu.table.UndergraEnrollRateTable;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateUndergraEnrollRateServlet")
public class Sec_UpdateUndergraEnrollRateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraEnrollRateServlet() {
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
					String uer_id = jsons.getJSONObject(i).getString(UndergraEnrollRateTable.UER_ID);
					String uer_comments = jsons.getJSONObject(i).getString(UndergraEnrollRateTable.UER_COMMENTS);
					params.put(UndergraEnrollRateTable.UER_COMMENTS, uer_comments);
					
					UndergraEnrollRateDao uerDao = new UndergraEnrollRateDaoImpl();
					int result = uerDao.alterUndergraEnrollRate(params, uer_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String uer_id = json.getString(UndergraEnrollRateTable.UER_ID);
				String uer_institute = json.getString(UndergraEnrollRateTable.UER_INSTITUTE);
				String uer_admission = json.getString(UndergraEnrollRateTable.UER_ADMISSION);
				String uer_firstmajorrate = json.getString(UndergraEnrollRateTable.UER_FIRSTMAJORRATE);
				String uer_unfirstmajorrate = json.getString(UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE);
				String uer_adjustrate = json.getString(UndergraEnrollRateTable.UER_ADJUSTRATE);
				String uer_comments = json.getString(UndergraEnrollRateTable.UER_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(uer_institute.equals("")||uer_admission.equals("")||uer_firstmajorrate.equals("")||
						uer_unfirstmajorrate.equals("")||uer_adjustrate.equals(""))
					isnull = 1;
				
				if(uer_firstmajorrate.equals(""))
					uer_firstmajorrate = "-999";
				if(uer_unfirstmajorrate.equals(""))
					uer_unfirstmajorrate = "-999";
				if(uer_adjustrate.equals(""))
					uer_adjustrate = "-999";
				
				params.put(UndergraEnrollRateTable.UER_INSTITUTE, uer_institute);
				params.put(UndergraEnrollRateTable.UER_ADMISSION, uer_admission);
				params.put(UndergraEnrollRateTable.UER_FIRSTMAJORRATE, uer_firstmajorrate);
				params.put(UndergraEnrollRateTable.UER_UNFIRSTMAJORRATE,uer_unfirstmajorrate);
				params.put(UndergraEnrollRateTable.UER_ADJUSTRATE, uer_adjustrate);
				params.put(UndergraEnrollRateTable.UER_COMMENTS, uer_comments);
				params.put(UndergraEnrollRateTable.ISNULL, isnull+"");
				
				UndergraEnrollRateDao uerDao = new UndergraEnrollRateDaoImpl();
				int result = uerDao.alterUndergraEnrollRate(params, uer_id);
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
