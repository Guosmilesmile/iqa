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

import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_AddSchoolNetServlet
 */
@WebServlet("/Sec_AddSchoolNetServlet")
public class Sec_AddSchoolNetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddSchoolNetServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String college = request.getParameter(SchoolNetTable.SN_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String backbonebandwidth = json.getString(SchoolNetTable.SN_BACKBONEBANDWIDTH);
			int sn_backbonebandwidth = -999;
			if(!backbonebandwidth.equals(""))
				sn_backbonebandwidth = Integer.valueOf(backbonebandwidth);
			
			String exportbandwidth = json.getString(SchoolNetTable.SN_EXPORTBANDWIDTH);
			int sn_exportbandwidth = -999;
			if(!exportbandwidth.equals(""))
				sn_exportbandwidth = Integer.valueOf(exportbandwidth);
			
			String informationcount = json.getString(SchoolNetTable.SN_INFORMATIONCOUNT);
			int sn_informationcount = -999;
			if(!informationcount.equals(""))
				sn_informationcount = Integer.valueOf(informationcount);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(backbonebandwidth.equals("") || exportbandwidth.equals("") || informationcount.equals("")  )
				isnull = 1;
			
			int sn_serialnumber = serialnumber;
			String sn_college = college;
			String sn_comments = "";
			if(backbonebandwidth.equals("") && exportbandwidth.equals("") && informationcount.equals("")  )
				return;
			SchoolNet sn = new SchoolNet(sn_backbonebandwidth,
					sn_exportbandwidth, sn_informationcount, sn_serialnumber,sn_college,sn_comments,isnull);			
			SchoolNetDao snDao = new SchoolNetDaoimpl();
			snDao.addRecord(sn);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
