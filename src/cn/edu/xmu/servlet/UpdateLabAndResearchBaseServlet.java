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

import cn.edu.xmu.dao.LabAndResearchBaseDao;
import cn.edu.xmu.daoimpl.LabAndResearchBaseDaoImpl;
import cn.edu.xmu.table.LabAndResearchBaseTable;


/**
 * 
 * @author zsj
 * 1-5实验室和科研基地 
 */
@WebServlet("/UpdateLabAndResearchBaseServlet")
public class UpdateLabAndResearchBaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LabAndResearchBaseDao labAndResearchBaseDao = new LabAndResearchBaseDaoImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLabAndResearchBaseServlet() {
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
					Map<String,String> params= new HashMap<String, String>();
					String lr_id = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_ID);
					
					String lr_name = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_NAME);
					String lr_type = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_TYPE);
					String lr_ifbuildtogether = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
					String lr_ifopentonongraduate = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
					String lr_comments = jsons.getJSONObject(i).getString(LabAndResearchBaseTable.LR_COMMENTS);
					//String college = json.getString(LabAndResearchBaseTable.LR_COLLEGE);
					
					
					params.put(LabAndResearchBaseTable.LR_NAME,lr_name);
					params.put(LabAndResearchBaseTable.LR_TYPE,lr_type);
					params.put(LabAndResearchBaseTable.LR_IFBUILDTOGETHER,lr_ifbuildtogether);
					params.put(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE,lr_ifopentonongraduate);
					params.put(LabAndResearchBaseTable.LR_COMMENTS, lr_comments);
					int result = labAndResearchBaseDao.alterLabAndResearchBase(params, lr_id);
					
					out.print(result);
				}
			}else {
				JSONObject json = new JSONObject(data);
				System.out.println(json);
				Map<String,String> params= new HashMap<String, String>();
				
				String lr_id = json.getString(LabAndResearchBaseTable.LR_ID);
				
				String lr_name = json.getString(LabAndResearchBaseTable.LR_NAME);
				String lr_type = json.getString(LabAndResearchBaseTable.LR_TYPE);
				String lr_ifbuildtogether = json.getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
				String lr_ifopentonongraduate = json.getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
				String lr_comments = json.getString(LabAndResearchBaseTable.LR_COMMENTS);
				//String college = json.getString(LabAndResearchBaseTable.LR_COLLEGE);
				
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(lr_name.equals("") || lr_type.equals("") || lr_ifbuildtogether.equals("") || 
						lr_ifopentonongraduate.equals(""))
					isnull = 1;
				
				
				params.put(LabAndResearchBaseTable.LR_NAME,lr_name);
				params.put(LabAndResearchBaseTable.LR_TYPE,lr_type);
				params.put(LabAndResearchBaseTable.LR_IFBUILDTOGETHER,lr_ifbuildtogether);
				params.put(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE,lr_ifopentonongraduate);
				params.put(LabAndResearchBaseTable.LR_COMMENTS, lr_comments);
				params.put(LabAndResearchBaseTable.ISNULL, isnull+"");
				
				int result = labAndResearchBaseDao.alterLabAndResearchBase(params, lr_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}

