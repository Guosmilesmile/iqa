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

import cn.edu.xmu.dao.TeacherPublishWritingDao;
import cn.edu.xmu.daoimpl.TeacherPublishWritingDaoImpl;
import cn.edu.xmu.table.TeacherPublishWritingTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateTeacherPublishWritingServlet")
public class Sec_UpdateTeacherPublishWritingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherPublishWritingServlet() {
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
					   int tpw_id = jsons.getJSONObject(i).getInt(TeacherPublishWritingTable.TPW_ID);
					   				 
					   String tpw_comments = jsons.getJSONObject(i).getString(TeacherPublishWritingTable.TPW_COMMENTS);
								
					   Map<String,String> params= new HashMap<String, String>();
					    params.put(TeacherPublishWritingTable.TPW_ID, tpw_id+"");
					    
					    params.put(TeacherPublishWritingTable.TPW_COMMENTS, tpw_comments);
					
					    TeacherPublishWritingDao TeacherPublishWritingDao = new TeacherPublishWritingDaoImpl();
					    int result = TeacherPublishWritingDao.alterTeacherPublishWriting(params, tpw_id+"");
					    out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						  			    			   
			    int tpw_id = json.getInt(TeacherPublishWritingTable.TPW_ID);
			    String total = json.getString(TeacherPublishWritingTable.TPW_TOTAL);  
				Integer tpw_total = -1;
				if(!total.equals("")) tpw_total = Integer.parseInt(total);
				String monograph = json.getString(TeacherPublishWritingTable.TPW_MONOGRAPH);
				Integer tpw_monograph = -1;
				if(!monograph.equals("")) tpw_monograph = Integer.parseInt(monograph);
				String translation = json.getString(TeacherPublishWritingTable.TPW_TRANSLATION);
				Integer tpw_translation = -1;
				if(!translation.equals("")) tpw_translation = Integer.parseInt(translation);
				String compile = json.getString(TeacherPublishWritingTable.TPW_COMPILE); 
				Integer tpw_compile = -1;
				if(!compile.equals("")) tpw_compile = Integer.parseInt(compile);
				String other = json.getString(TeacherPublishWritingTable.TPW_OTHER); 
				Integer tpw_other = -1;
				if(!other.equals("")) tpw_other = Integer.parseInt(other);
				int tpw_isnull = 0;
				if("".equals(total)||"".equals(monograph)||"".equals(translation)||"".equals(compile)
						|| "".equals(other)  )
				{
					tpw_isnull = 1;
				}
								 
				   String tpw_comments = json.getString(TeacherPublishWritingTable.TPW_COMMENTS);
							
				   Map<String,String> params= new HashMap<String, String>();
				    params.put(TeacherPublishWritingTable.TPW_ID, tpw_id+"");
				    params.put(TeacherPublishWritingTable.TPW_TOTAL, tpw_total+"");
				    params.put(TeacherPublishWritingTable.TPW_MONOGRAPH, tpw_monograph+"");
				    params.put(TeacherPublishWritingTable.TPW_TRANSLATION, tpw_translation+"");
				    params.put(TeacherPublishWritingTable.TPW_COMPILE, tpw_compile+"");
				    params.put(TeacherPublishWritingTable.TPW_OTHER, tpw_other+"");
								    
				    params.put(TeacherPublishWritingTable.TPW_COMMENTS, tpw_comments);
				    params.put(TeacherPublishWritingTable.TPW_ISNULL, tpw_isnull+"");
				    
				    TeacherPublishWritingDao TeacherPublishWritingDao = new TeacherPublishWritingDaoImpl();
				    int result = TeacherPublishWritingDao.alterTeacherPublishWriting(params, tpw_id+"");
				    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
