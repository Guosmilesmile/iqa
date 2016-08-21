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

import cn.edu.xmu.dao.TeacherResearchNumberDao;
import cn.edu.xmu.daoimpl.TeacherResearchNumberDaoImpl;
import cn.edu.xmu.entity.TeacherResearchNumber;
import cn.edu.xmu.table.TeacherResearchNumberTable;

/**
 * Servlet implementation class Sec_AddTeacherResearchNumber
 */
@WebServlet("/AddTeacherResearchNumber")
public class Sec_AddTeacherResearchNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeacherResearchNumber() {
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
		String college = request.getParameter(TeacherResearchNumberTable.TRN_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp;
		try {
			JSONObject json = new JSONObject(data);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_HRZNPROJECTNUM);
			int trn_hrznprojectnum = -999;
			if(!temp.equals(""))
				trn_hrznprojectnum = Integer.valueOf(temp);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_HRZNHUMANITIESNUM);
			int trn_hrznhumanitiesnum = -999;
			if(!temp.equals(""))
				trn_hrznhumanitiesnum = Integer.valueOf(temp);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_VTCLPROJECTNUM);
			int trn_vtclprojectnum = -999;
			if(!temp.equals(""))
				trn_vtclprojectnum = Integer.valueOf(temp);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_VTCLHUMANITIESNUM);
			int trn_vtclhumanitiesnum = -999;
			if(!temp.equals(""))
				trn_vtclhumanitiesnum = Integer.valueOf(temp);
			
			int trn_projectnum = -999;
			if(trn_hrznprojectnum!=-999 && trn_vtclprojectnum!=-999)
				trn_projectnum = trn_hrznprojectnum+trn_vtclprojectnum;
			
			temp = json.getString(TeacherResearchNumberTable.TRN_HRZNPROJECTCOST);
			float trn_hrznprojectcost = -999;
			if(!temp.equals(""))
				trn_hrznprojectcost = Float.valueOf(temp);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_HRZNHUMANITIESCOST);
			float trn_hrznhumanitiescost = -999;
			if(!temp.equals(""))
				trn_hrznhumanitiescost = Float.valueOf(temp);

			temp = json.getString(TeacherResearchNumberTable.TRN_VTCLPROJECTCOST);
			float trn_vtclprojectcost = -999;
			if(!temp.equals(""))
				trn_vtclprojectcost = Float.valueOf(temp);
			
			temp = json.getString(TeacherResearchNumberTable.TRN_VTCLHUMANITIESCOST);
			float trn_vtclhumanitiescost = -999;
			if(!temp.equals(""))
				trn_vtclhumanitiescost = Float.valueOf(temp);
			
			float trn_projectcost = -999;
			if(trn_hrznprojectcost!=-999 && trn_vtclprojectcost!=-999)
				trn_projectcost = trn_hrznprojectcost+trn_vtclprojectcost;
			
			int trn_serialnumber = serialnumber;
			String trn_college = college;
			String trn_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(trn_hrznprojectnum==-999 || trn_hrznhumanitiesnum==-999 || trn_vtclprojectnum==-999 ||
					trn_vtclhumanitiesnum==-999 || trn_hrznprojectcost==-999 ||
							trn_hrznhumanitiescost==-999 || trn_vtclprojectcost==-999 || trn_vtclhumanitiescost==-999)
				isnull = 1;
			
			if(trn_hrznprojectnum==-999 && trn_hrznhumanitiesnum==-999 && trn_vtclprojectnum==-999 &&
					trn_vtclhumanitiesnum==-999 && trn_hrznprojectcost==-999 &&
							trn_hrznhumanitiescost==-999 && trn_vtclprojectcost==-999 && trn_vtclhumanitiescost==-999)
				return;
			
			TeacherResearchNumber nby = new TeacherResearchNumber(trn_projectnum, trn_hrznprojectnum,
					trn_hrznhumanitiesnum, trn_vtclprojectnum,
					trn_vtclhumanitiesnum, trn_projectcost,
					trn_hrznprojectcost, trn_hrznhumanitiescost,
					trn_vtclprojectcost, trn_vtclhumanitiescost,
					trn_serialnumber, trn_college, trn_comments, isnull);			
			
			TeacherResearchNumberDao nbyDao = new TeacherResearchNumberDaoImpl();
			nbyDao.addRecord(nby);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
