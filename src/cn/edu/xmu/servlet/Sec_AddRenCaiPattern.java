package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.RenCaiPatternDao;
import cn.edu.xmu.daoimpl.RenCaiPatternDaoImpl;
import cn.edu.xmu.entity.RenCaiPattern;
import cn.edu.xmu.table.RenCaiPatternTable;

/**
 * Servlet implementation class Sec_AddSchoolExecutiveUnit
 */
@WebServlet("/Sec_AddRenCaiPattern")
public class Sec_AddRenCaiPattern extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddRenCaiPattern() {
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
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(RenCaiPatternTable.RCP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			
			//int rcp_id = Integer.parseInt(json.getString(RenCaiPatternTable.RCP_ID));
			String rcp_college1 = json.getString(RenCaiPatternTable.RCP_COLLEGE1);
			
			String rcp_project = json.getString(RenCaiPatternTable.RCP_PROJECT);
			String rcp_head = json.getString(RenCaiPatternTable.RCP_HEAD);
			String rcp_type = json.getString(RenCaiPatternTable.RCP_TYPE);
			String rcp_level = json.getString(RenCaiPatternTable.RCP_LEVEL);
			
			Date rcp_starttime = null;
			if(!json.getString(RenCaiPatternTable.RCP_STARTTIME).equals("")){
				rcp_starttime = Date.valueOf(json.getString(RenCaiPatternTable.RCP_STARTTIME));
			}
			else{
				rcp_starttime=Date.valueOf("1800-1-1");
			}
			int rcp_serialnumber = serialnumber;
			String rcp_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(rcp_college1.equals("")||rcp_project.equals("")||rcp_head.equals("")||rcp_type.equals("")||rcp_level.equals("")||
					null==rcp_starttime)
				isnull = 1;
			
			
			RenCaiPattern rcp = new RenCaiPattern(rcp_college1, rcp_project, rcp_head, rcp_type, rcp_level,
					rcp_starttime, rcp_serialnumber, rcp_college, isnull);
			
			RenCaiPatternDao rcpDao = new RenCaiPatternDaoImpl();
			rcpDao.addRecord(rcp);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
