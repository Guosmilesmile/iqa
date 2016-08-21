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

import cn.edu.xmu.dao.CourseBuildInfoDao;
import cn.edu.xmu.daoimpl.CourseBuildInfoDaoImpl;
import cn.edu.xmu.entity.CourseBuildInfo;
import cn.edu.xmu.table.CourseBuildInfoTable;

/**
 * 附表5-2-3课程建设情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_AddCourseBuildInfoServlet")
public class Sec_AddCourseBuildInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddCourseBuildInfoServlet()
    {
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
		String college = request.getParameter(CourseBuildInfoTable.CBI_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String cbi_institute = json.getString(CourseBuildInfoTable.CBI_INSTITUTE);
			String cbi_name = json.getString(CourseBuildInfoTable.CBI_NAME);
			String cbi_chargeperson = json.getString(CourseBuildInfoTable.CBI_CHARGEPERSON);
			String cbi_type = json.getString(CourseBuildInfoTable.CBI_TYPE);
			String cbi_grade = json.getString(CourseBuildInfoTable.CBI_GRADE);
			String approvaltime = json.getString( CourseBuildInfoTable.CBI_APPROVALTIME);
			Date cbi_approvaltime = Date.valueOf("1800-1-1");
			if(!approvaltime.equals(""))
				cbi_approvaltime = Date.valueOf(approvaltime);
			
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(cbi_institute.equals("")||cbi_name.equals("")||cbi_chargeperson.equals("")||cbi_type.equals("")||
					cbi_grade.equals("")||approvaltime.equals(""))
				isnull = 1;
			if(cbi_institute.equals("")&&cbi_name.equals("")&&cbi_chargeperson.equals("")&&cbi_type.equals("")&&
					cbi_grade.equals("")&&approvaltime.equals(""))
			{
				out.println(false);
				return;
			}
			int cbi_serialnumber = serialnumber;
			String cbi_college = college;
			String cbi_comments = "";
			
			
			
			CourseBuildInfo cbi = new CourseBuildInfo(  cbi_institute, cbi_name, cbi_chargeperson, 
					cbi_type, cbi_grade, cbi_approvaltime, cbi_serialnumber, cbi_college, cbi_comments,
					isnull);
			CourseBuildInfoDao cbiDao = new CourseBuildInfoDaoImpl();
			cbiDao.addCourseBuildInfoRecord(cbi);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
