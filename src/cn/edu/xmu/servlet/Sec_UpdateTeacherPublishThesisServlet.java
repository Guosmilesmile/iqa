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

import cn.edu.xmu.dao.TeacherPublishThesisDao;
import cn.edu.xmu.daoimpl.TeacherPublishThesisDaoImpl;
import cn.edu.xmu.table.TeacherPublishThesisTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateTeacherPublishThesisServlet")
public class Sec_UpdateTeacherPublishThesisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateTeacherPublishThesisServlet() {
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
					   int tpt_id = jsons.getJSONObject(i).getInt(TeacherPublishThesisTable.TPT_ID);
					   					 
					   String tpt_comments = jsons.getJSONObject(i).getString(TeacherPublishThesisTable.TPT_COMMENTS);
								
					   Map<String,String> params= new HashMap<String, String>();
					    params.put(TeacherPublishThesisTable.TPT_ID, tpt_id+"");
					    
					    params.put(TeacherPublishThesisTable.TPT_COMMENTS, tpt_comments);
					
					    TeacherPublishThesisDao TeacherPublishThesisDao = new TeacherPublishThesisDaoImpl();
					    int result = TeacherPublishThesisDao.alterTeacherPublishThesis(params, tpt_id+"");
					    out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
						  			    			   
			    int tpt_id = json.getInt(TeacherPublishThesisTable.TPT_ID);
			    String total = json.getString(TeacherPublishThesisTable.TPT_TOTAL); 
				Integer tpt_total = -1;
				if(!total.equals("")) tpt_total = Integer.parseInt(total);
				String sci = json.getString(TeacherPublishThesisTable.TPT_SCI); 
				Integer tpt_sci = -1;
				if(!sci.equals("")) tpt_sci = Integer.parseInt(sci);
				String ssci = json.getString(TeacherPublishThesisTable.TPT_SSCI); 
				Integer tpt_ssci = -1;
				if(!ssci.equals("")) tpt_ssci = Integer.parseInt(ssci);
				String ei = json.getString(TeacherPublishThesisTable.TPT_EI); 
				Integer tpt_ei = -1;
				if(!ei.equals("")) tpt_ei = Integer.parseInt(ei);
				String istp = json.getString(TeacherPublishThesisTable.TPT_ISTP); 
				Integer tpt_istp = -1;
				if(!istp.equals("")) tpt_istp = Integer.parseInt(istp);
				String domesic = json.getString(TeacherPublishThesisTable.TPT_DOMESIC); 
				Integer tpt_domesic = -1;
				if(!domesic.equals("")) tpt_domesic = Integer.parseInt(domesic);
				int tpt_isnull = 0;
				if("".equals(total)||"".equals(sci)||"".equals(ssci)||"".equals(ei)
						|| "".equals(istp) || "".equals(domesic) )
				{
					tpt_isnull = 1;
				}
				 				 
				   String tpt_comments = json.getString(TeacherPublishThesisTable.TPT_COMMENTS);
							
				   Map<String,String> params= new HashMap<String, String>();
				    params.put(TeacherPublishThesisTable.TPT_ID, tpt_id+"");
				    params.put(TeacherPublishThesisTable.TPT_TOTAL, tpt_total+"");
				    params.put(TeacherPublishThesisTable.TPT_SCI, tpt_sci+"");
				    params.put(TeacherPublishThesisTable.TPT_SSCI, tpt_ssci+"");
				    params.put(TeacherPublishThesisTable.TPT_EI, tpt_ei+"");
				    params.put(TeacherPublishThesisTable.TPT_ISTP, tpt_istp+"");
				    params.put(TeacherPublishThesisTable.TPT_DOMESIC, tpt_domesic+"");				   				
				    params.put(TeacherPublishThesisTable.TPT_COMMENTS, tpt_comments);
				    params.put(TeacherPublishThesisTable.TPT_ISNULL, tpt_isnull+"");
				
				    TeacherPublishThesisDao TeacherPublishThesisDao = new TeacherPublishThesisDaoImpl();
				    int result = TeacherPublishThesisDao.alterTeacherPublishThesis(params, tpt_id+"");
				    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
