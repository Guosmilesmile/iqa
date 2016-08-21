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

import cn.edu.xmu.dao.MinorDegreeDao;
import cn.edu.xmu.daoimpl.MinorDegreeDaoImpl;
import cn.edu.xmu.entity.MinorDegree;
import cn.edu.xmu.table.MinorDegreeTable;

/**
 * Servlet implementation class Sec_AddMinorDegreeServlet
 */
@WebServlet("/Sec_AddMinorDegreeServlet")
public class Sec_AddMinorDegreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddMinorDegreeServlet() {
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
		String college = request.getParameter(MinorDegreeTable.MD_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String order = json.getString(MinorDegreeTable.MD_ORDER);
			int md_order = -999;
			if(!order.equals(""))
				md_order = Integer.valueOf(order);
			
			String md_importcollege = json.getString(MinorDegreeTable.MD_IMPORTCOLLEGE);
			String md_major = json.getString(MinorDegreeTable.MD_MAJOR);
			String minordegreecount = json.getString(MinorDegreeTable.MD_MINORDEGREEECOUNT);
			int md_minordegreecount = -999;
			if(!minordegreecount.equals(""))
				md_minordegreecount = Integer.valueOf(minordegreecount);
			
			String minorcertificatecount = json.getString(MinorDegreeTable.MD_MINORCERTIFICATECOUNT);
			int md_minorcertificatecount = -999;
			if(!minorcertificatecount.equals(""))
				md_minorcertificatecount = Integer.valueOf(minorcertificatecount);
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(order.equals("") || md_importcollege.equals("") || md_major.equals("") || 
					minordegreecount.equals("") || minorcertificatecount.equals("") )
				isnull = 1;
			
			int md_serialnumber = serialnumber;
			String md_college = college;
			String md_comments = "";
			
			if(order.equals("") && md_importcollege.equals("") && md_major.equals("")&& 
					minordegreecount.equals("") && minorcertificatecount.equals("") )
				return;
			MinorDegree md = new MinorDegree(md_order,md_importcollege,
					md_major, md_minordegreecount, md_minorcertificatecount,md_serialnumber,md_college,md_comments,isnull);			
			MinorDegreeDao mdDao = new MinorDegreeDaoImpl();
			mdDao.addRecord(md);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
