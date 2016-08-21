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

import cn.edu.xmu.dao.ListenSituationDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.daoimpl.ListenSituationDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.entity.ListenSituation;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.ListenSituationTable;
import cn.edu.xmu.table.SchoolNetTable;

/**
 * Servlet implementation class Sec_AddListenSituationServlet
 */
@WebServlet("/Sec_AddListenSituationServlet")
public class Sec_AddListenSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddListenSituationServlet() {
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
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(ListenSituationTable.LS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String ls_importcollege = json.getString(ListenSituationTable.LS_IMPORTCOLLEGE);
			String personnumber = json.getString(ListenSituationTable.LS_PERSONNUMBER);
			int ls_personnumber = -999;
			if(!personnumber.equals(""))
				ls_personnumber = Integer.valueOf(personnumber);
			
			String lessonnumber = json.getString(ListenSituationTable.LS_LESSONNUMBER);
			int ls_lessonnumber = -999;
			if(!lessonnumber.equals(""))
				ls_lessonnumber = Integer.valueOf(lessonnumber);
			
			String personnumber2 = json.getString(ListenSituationTable.LS_PERSONNUMBER2);
			int ls_personnumber2 = -999;
			if(!personnumber2.equals(""))
				ls_personnumber2 = Integer.valueOf(personnumber2);
			
			String lessonnumber2 = json.getString(ListenSituationTable.LS_LESSONNUMBER2);
			int ls_lessonnumber2 = -999;
			if(!lessonnumber2.equals(""))
				ls_lessonnumber2 = Integer.valueOf(lessonnumber2);
			
			String excellent = json.getString(ListenSituationTable.LS_EXCELLENT);
			int ls_excellent = -999;
			if(!excellent.equals(""))
				ls_excellent = Integer.valueOf(excellent);
			
			String good = json.getString(ListenSituationTable.LS_GOOD);
			int ls_good = -999;
			if(!good.equals(""))
				ls_good = Integer.valueOf(good);
			
			String medium = json.getString(ListenSituationTable.LS_MEDIUM);
			int ls_medium = -999;
			if(!medium.equals(""))
				ls_medium = Integer.valueOf(medium);
			
			String bad = json.getString(ListenSituationTable.LS_BAD);
			int ls_bad = -999;
			if(!bad.equals(""))
				ls_bad = Integer.valueOf(bad);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(ls_importcollege.equals("") || personnumber.equals("") || lessonnumber.equals("") || 
					personnumber2.equals("") || lessonnumber2.equals("") || excellent.equals("") ||
					good.equals("") || medium.equals("") || bad.equals("") )
				isnull = 1;
			
			int ls_serialnumber = serialnumber;
			String ls_college = college;
			String ls_comments = "";
			
			if(ls_importcollege.equals("") && personnumber.equals("") && lessonnumber.equals("") && 
					personnumber2.equals("") && lessonnumber2.equals("") && excellent.equals("") &&
					good.equals("") && medium.equals("") && bad.equals("") )
				return;
			ListenSituation ls = new ListenSituation(ls_importcollege,
					ls_personnumber, ls_lessonnumber, ls_personnumber2,ls_lessonnumber2,ls_excellent,
					ls_good,ls_medium,ls_bad,ls_serialnumber,ls_college,ls_comments,isnull);			
			ListenSituationDao lsDao = new ListenSituationDaoImpl();
			lsDao.addRecord(ls);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
