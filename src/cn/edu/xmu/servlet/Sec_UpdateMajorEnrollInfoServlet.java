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

import cn.edu.xmu.dao.MajorEnrollInfoDao;
import cn.edu.xmu.daoimpl.MajorEnrollInfoDaoImpl;
import cn.edu.xmu.table.MajorEnrollInfoTable;

/**
 * 附表6-1-6-1  各专业（大类）招生情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_UpdateMajorEnrollInfoServlet")
public class Sec_UpdateMajorEnrollInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateMajorEnrollInfoServlet() {
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
					String mei_id = jsons.getJSONObject(i).getString(MajorEnrollInfoTable.MEI_ID);
					String mei_comments = jsons.getJSONObject(i).getString(MajorEnrollInfoTable.MEI_COMMENTS);
					params.put(MajorEnrollInfoTable.MEI_COMMENTS, mei_comments);
					
					MajorEnrollInfoDao meiDao = new MajorEnrollInfoDaoImpl();
					int result  =meiDao.alterMajorEnrollInfo(params, mei_id);
					out.print(result);
				} 
			}
			else{
				JSONObject json = new JSONObject(data);				
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();

				String mei_id = json.getString(MajorEnrollInfoTable.MEI_ID);
				String mei_majorcode = json.getString(MajorEnrollInfoTable.MEI_MAJORCODE);
				String mei_majorname = json.getString(MajorEnrollInfoTable.MEI_MAJORNAME);
				String mei_plannumber = json.getString( MajorEnrollInfoTable.MEI_PLANNUMBER);
				String mei_enrollnumber = json.getString(MajorEnrollInfoTable.MEI_ENROLLNUMBER);
				String mei_comments = json.getString(MajorEnrollInfoTable.MEI_COMMENTS);
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(mei_majorcode.equals("")||mei_majorname.equals("")||
						mei_plannumber.equals("")||mei_enrollnumber.equals(""))
					isnull = 1;
				
				if(mei_plannumber.equals(""))
					mei_plannumber = "-999";
				if(mei_enrollnumber.equals(""))
					mei_enrollnumber = "-999";

				
				params.put(MajorEnrollInfoTable.MEI_MAJORCODE, mei_majorcode);
				params.put(MajorEnrollInfoTable.MEI_MAJORNAME, mei_majorname);
				params.put(MajorEnrollInfoTable.MEI_PLANNUMBER, mei_plannumber);
				params.put(MajorEnrollInfoTable.MEI_ENROLLNUMBER,mei_enrollnumber);
				params.put(MajorEnrollInfoTable.MEI_COMMENTS, mei_comments);
				params.put(MajorEnrollInfoTable.ISNULL, isnull+"");
				
				MajorEnrollInfoDao meiDao = new MajorEnrollInfoDaoImpl();
				int result = meiDao.alterMajorEnrollInfo(params, mei_id);
				out.print(result);
			}
		
	}catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
