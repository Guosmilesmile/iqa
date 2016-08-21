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

import cn.edu.xmu.dao.EnrollCategoryInfoDao;
import cn.edu.xmu.daoimpl.EnrollCategoryInfoDaoImpl;
import cn.edu.xmu.entity.EnrollCategoryInfo;
import cn.edu.xmu.table.EnrollCategoryInfoTable;

@WebServlet("/Sec_AddEnrollCategoryInfoservlet")
public class Sec_AddEnrollCategoryInfoservlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddEnrollCategoryInfoservlet() {
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
		String college = request.getParameter("eci_college");
		//解码
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
			
		try {
			JSONObject json = new JSONObject(data);
			
			String plannumber = json.getString(EnrollCategoryInfoTable.ECI_PLANNUMBER);
			Integer eci_plannumber = -999;
			if(!plannumber.equals(""))
				eci_plannumber = Integer.valueOf(plannumber);
			String enrollnumber = json.getString(EnrollCategoryInfoTable.ECI_ENROLLNUMBER);
			Integer eci_enrollnumber = -999;
			if(!enrollnumber.equals(""))
				eci_enrollnumber = Integer.valueOf(enrollnumber);
			String registernumber = json.getString(EnrollCategoryInfoTable.ECI_REGISTERNUMBER);
			Integer eci_registernumber = -999;
			if(!registernumber.equals(""))
				eci_registernumber = Integer.valueOf(registernumber);
			String indrecruitmentnumber = json.getString(EnrollCategoryInfoTable.ECI_INDRECRUITMENTNUMBER);
			Integer eci_indrecruitmentnumber = -999;
			if(!indrecruitmentnumber.equals(""))
				eci_indrecruitmentnumber = Integer.valueOf(indrecruitmentnumber);
			String specialnumber= json.getString(EnrollCategoryInfoTable.ECI_SPECIALNUMBER);
			Integer eci_specialnumber = -999;
			if(!specialnumber.equals(""))
				eci_specialnumber = Integer.valueOf(specialnumber);
			String provincenumber= json.getString(EnrollCategoryInfoTable.ECI_PROVINCENUMBER);
			Integer eci_provincenumber = -999;
			if(!provincenumber.equals(""))
				eci_provincenumber = Integer.valueOf(provincenumber);
			String newspecialitynumber = json.getString(EnrollCategoryInfoTable.ECI_NEWSPECIALITYNUMBER);
			Integer eci_newspecialitynumber = -999;
			if(!newspecialitynumber.equals(""))
				eci_newspecialitynumber = Integer.valueOf(newspecialitynumber);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(plannumber.equals("")||enrollnumber.equals("")||registernumber.equals("")||indrecruitmentnumber.equals("")||
					specialnumber.equals("")||provincenumber.equals("")||newspecialitynumber.equals(""))
				isnull = 1;
			if(plannumber.equals("")&&enrollnumber.equals("")&&registernumber.equals("")&&indrecruitmentnumber.equals("")&&
					specialnumber.equals("")&&provincenumber.equals("")&&newspecialitynumber.equals(""))
			{
				out.println(false);
				return ;
			}
			int eci_serialnumber = serialnumber;
			String eci_college = college;
			String eci_comments = "";
			EnrollCategoryInfo eci = new EnrollCategoryInfo(eci_plannumber, eci_enrollnumber,
					eci_registernumber, eci_indrecruitmentnumber, eci_specialnumber,
					eci_provincenumber, eci_newspecialitynumber, eci_college, eci_serialnumber,
					eci_comments,isnull);
			EnrollCategoryInfoDao eciDao = new EnrollCategoryInfoDaoImpl();
			eciDao.addEnrollCategoryInfoRecord(eci);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
