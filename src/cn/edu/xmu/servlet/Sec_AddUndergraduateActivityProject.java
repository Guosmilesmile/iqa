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

import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.daoimpl.UndergraduateActivityProjectDaoImpl;
import cn.edu.xmu.entity.UndergraduateActivityProject;
import cn.edu.xmu.table.UndergraduateActivityProjectTable;

/**
 * Servlet implementation class Sec_AddUndergraduateActivityProject
 */
@WebServlet("/AddUndergraduateActivityProject")
public class Sec_AddUndergraduateActivityProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraduateActivityProject() {
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
		String college = request.getParameter(UndergraduateActivityProjectTable.UAP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);
			
			String uap_projectname = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTNAME);
			String uap_unit = json.getString(UndergraduateActivityProjectTable.UAP_UNIT);
			String uap_projecttype = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTTYPE);
			temp = json.getString(UndergraduateActivityProjectTable.UAP_PERSONNUMBER);
			int uap_personnumber = -999;
			if(!temp.equals(""))
				uap_personnumber = Integer.valueOf(temp);
			temp = json.getString(UndergraduateActivityProjectTable.UAP_MAJORNUMBER);
			int uap_majornumber = -999;
			if(!temp.equals(""))
				uap_majornumber = Integer.valueOf(temp);
			temp = json.getString(UndergraduateActivityProjectTable.UAP_PROJECTNUMBER);
			int uap_projectnumber = -999;
			if(!temp.equals(""))
				uap_projectnumber = Integer.valueOf(temp);
			
			String uap_awardgrade = json.getString(UndergraduateActivityProjectTable.UAP_AWARDGRADE);
			String uap_awardtype = json.getString(UndergraduateActivityProjectTable.UAP_AWARDTYPE);
			String uap_awarddatetemp = json.getString(UndergraduateActivityProjectTable.UAP_AWARDDATE);
			Date uap_awarddate = Date.valueOf("1800-1-1");//不填则默认存1800-1-1
			if(!uap_awarddatetemp.equals(""))
				uap_awarddate = Date.valueOf(uap_awarddatetemp);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(uap_projectname.equals("") || uap_unit.equals("") || uap_projecttype.equals("") ||
					uap_personnumber==-999 || uap_majornumber==-999 || uap_projectnumber==-999 ||
					uap_awardgrade.equals("") || uap_awardtype.equals("") || uap_awarddatetemp.equals(""))
				isnull = 1;
			
			int uap_serialnumber = serialnumber;
			String uap_college = college;
			String uap_comments = "";
			UndergraduateActivityProject uap = new UndergraduateActivityProject( uap_projectname,
					uap_unit, uap_projecttype, uap_personnumber,
					uap_majornumber, uap_projectnumber, uap_awardgrade,
					uap_awardtype, uap_awarddate, uap_serialnumber,
					 uap_college, uap_comments, isnull);			
			
			UndergraduateActivityProjectDao uapDao = new UndergraduateActivityProjectDaoImpl();
			uapDao.addRecord(uap);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
