package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import cn.edu.xmu.dao.PublishProductDao;
import cn.edu.xmu.daoimpl.PublishProductDaoImpl;
import cn.edu.xmu.table.PublishProductTable;




/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdatePublishProduct")
public class Sec_UpdatePublishProduct extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdatePublishProduct() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					Map<String,String> params= new HashMap<String, String>();
					String ap_id = json.getString(PublishProductTable.PP_ID);
					String ap_comments = json.getString(PublishProductTable.PP_COMMENTS);
					
					params.put(PublishProductTable.PP_COMMENTS, ap_comments );
					PublishProductDao apdao = new PublishProductDaoImpl();
					int result = apdao.alterPublishProduct(params, ap_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				String ap_id = json.getString(PublishProductTable.PP_ID);
				String ap_colleges = json.getString(PublishProductTable.PP_COLLEGES);
				String ap_papertitle = json.getString(PublishProductTable.PP_PRODUCTNAME);
				String ap_grade =json.getString(PublishProductTable.PP_GRADE);
				String ap_name = json.getString(PublishProductTable.PP_NAME);
				String ap_major = json.getString(PublishProductTable.PP_MAJOR);
				String ap_publication = json.getString(PublishProductTable.PP_PUBLICATION);
				String ap_time = json.getString(PublishProductTable.PP_TIME);
				String ap_remark = json.getString(PublishProductTable.PP_REMARK);
				int ap_serialnumber = json.getInt(PublishProductTable.PP_SERIALNUMBER);
				String ap_college = json.getString(PublishProductTable.PP_COLLEGE);
				int isnull = 0;
				if ("".equals(ap_colleges) || "".equals(ap_papertitle)
						|| "".equals(ap_grade) || "".equals(ap_name)
						|| "".equals(ap_major) || "".equals(ap_publication) || "".equals(ap_time)) {
					isnull = 1;
				}
				
				if(null==ap_time||ap_time.equals("")||ap_time.equals("NaN-NaN-NaN"))
					ap_time = "1800-01-01";
				params.put(PublishProductTable.PP_COLLEGES, ap_colleges);
				params.put(PublishProductTable.PP_PRODUCTNAME, ap_papertitle);
				params.put(PublishProductTable.PP_GRADE, ap_grade );
				params.put(PublishProductTable.PP_NAME,ap_name  );
				params.put(PublishProductTable.PP_MAJOR,ap_major );
				params.put(PublishProductTable.PP_PUBLICATION, ap_publication);
				params.put(PublishProductTable.PP_TIME,ap_time );
				params.put(PublishProductTable.PP_REMARK, ap_remark );
				params.put(PublishProductTable.PP_SERIALNUMBER, ap_serialnumber+"");
				params.put(PublishProductTable.PP_COLLEGE, ap_college);
				params.put("isnull", isnull+"");
				PublishProductDao apdao = new PublishProductDaoImpl();
				int result = apdao.alterPublishProduct(params, ap_id);
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
