package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.LabAndResearchBaseDao;
import cn.edu.xmu.daoimpl.LabAndResearchBaseDaoImpl;
import cn.edu.xmu.entity.LabAndResearchBase;
import cn.edu.xmu.table.LabAndResearchBaseTable;

/**
 * 
 * @author zsj
 * 1-5实验室和科研基地 
 */
@WebServlet("/AddLabAndResearchBaseServlet")
public class AddLabAndResearchBaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LabAndResearchBaseDao labAndResearchBaseDao = new LabAndResearchBaseDaoImpl();
	
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
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String lr_name = json.getString(LabAndResearchBaseTable.LR_NAME);
			String lr_type = json
					.getString(LabAndResearchBaseTable.LR_TYPE);
			String lr_ifbuildtogether = json.getString(LabAndResearchBaseTable.LR_IFBUILDTOGETHER);
			String lr_ifopentonongraduate = json
					.getString(LabAndResearchBaseTable.LR_IFOPENTONONGRADUATE);
			
			String college = request.getParameter("lr_college");
			college = URLDecoder.decode(college,"UTF-8");
			
			int lr_serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
			//String lr_comments = json.getString(LabAndResearchBaseTable.LR_COMMENTS);
			
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(lr_name.equals("") || lr_type.equals("") || lr_ifbuildtogether.equals("") || 
					lr_ifopentonongraduate.equals(""))
				isnull = 1;
			
			
			if(lr_name.equals("") && lr_type.equals("") && lr_ifbuildtogether.equals("") && 
					lr_ifopentonongraduate.equals(""))
				return;

			
			
			LabAndResearchBase labAndResearchBase = new LabAndResearchBase(lr_name, lr_type, lr_ifbuildtogether, lr_ifopentonongraduate, lr_serialnumber, null, college, "",isnull);
			
			labAndResearchBaseDao.addLabAndResearchBase(labAndResearchBase);			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
