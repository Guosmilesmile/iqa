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

import cn.edu.xmu.dao.TeachingTeamDao;
import cn.edu.xmu.daoimpl.TeachingTeamDaoImpl;
import cn.edu.xmu.entity.TeachingTeam;
import cn.edu.xmu.table.TeachingTeamTable;

/**
 * Servlet implementation class Sec_AddTeachingTeam
 */
@WebServlet("/AddTeachingTeam")
public class Sec_AddTeachingTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachingTeam() {
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
		String college = request.getParameter(TeachingTeamTable.TT_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp;
		try {
			JSONObject json = new JSONObject(data);

			temp = json.getString(TeachingTeamTable.TT_SEQUENCENUMBER);
			int tt_sequencenumber = -999;
			if(!temp.equals(""))
				tt_sequencenumber = Integer.valueOf(temp);
			
			String tt_collegename = json.getString(TeachingTeamTable.TT_COLLEGENAME);
			String tt_name = json.getString(TeachingTeamTable.TT_NAME);
			String tt_leader = json.getString(TeachingTeamTable.TT_LEADER);
			temp = json.getString(TeachingTeamTable.TT_COUNTRY);
			int tt_country = -999;
			if(!temp.equals(""))
				tt_country = Integer.valueOf(temp);
			
			temp = json.getString(TeachingTeamTable.TT_PROVINCE);
			int tt_province = -999;
			if(!temp.equals(""))
				tt_province = Integer.valueOf(temp);
			
			int tt_serialnumber = serialnumber;
			String tt_college = college;
			String tt_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(tt_sequencenumber==-999 || tt_collegename.equals("") || tt_name.equals("") ||
					tt_leader.equals("") || tt_country==-999 || tt_province==-999)
				isnull = 1;
			
			if(tt_sequencenumber==-999 && tt_collegename.equals("") && tt_name.equals("") &&
					tt_leader.equals("") && tt_country==-999 && tt_province==-999)
				return;
			
			TeachingTeam tt = new TeachingTeam(tt_sequencenumber, tt_collegename,
					tt_name, tt_leader,tt_country, tt_province,
					tt_serialnumber, tt_college, tt_comments, isnull);			
			
			TeachingTeamDao ttDao = new TeachingTeamDaoImpl();
			ttDao.addRecord(tt);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
