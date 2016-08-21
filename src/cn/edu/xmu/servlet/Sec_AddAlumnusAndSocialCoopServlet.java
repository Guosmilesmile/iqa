package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hslf.record.OutlineTextRefAtom;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.AlumnusAndSocialCoopDao;
import cn.edu.xmu.daoimpl.AlumnusAndSocialCoopDaoImpl;
import cn.edu.xmu.entity.AlumnusAndSocialCoop;
import cn.edu.xmu.table.AlumnusAndSocialCoppTable;

@WebServlet("/Sec_AddAlumnusAndSocialCoopServlet")
public class Sec_AddAlumnusAndSocialCoopServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddAlumnusAndSocialCoopServlet()
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
		String college = request.getParameter(AlumnusAndSocialCoppTable.AS_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			String alumnusamount =  json.getString(AlumnusAndSocialCoppTable.AS_ALUMNUSAMOUNT);
			int as_alumnusamount = -999;
			if(!alumnusamount.equals(""))
				as_alumnusamount = Integer.valueOf(alumnusamount);
			String domesticalumnus = json.getString(AlumnusAndSocialCoppTable.AS_DOMESTICALUMNUS);
			int as_domesticalumnus = -999;
			if(!domesticalumnus.equals(""))
				as_domesticalumnus = Integer.valueOf(domesticalumnus);
			String overseaalumnus = json.getString(AlumnusAndSocialCoppTable.AS_OVERSEAALUMNUS);
			int as_overseaalumnus = -999;
			if(!overseaalumnus.equals(""))
				as_overseaalumnus = Integer.valueOf(overseaalumnus);
			String agencyamount = json.getString(AlumnusAndSocialCoppTable.AS_AGENCYAMOUNT);
			int as_agencyamount = -999;
			if(!agencyamount.equals(""))
				as_agencyamount = Integer.valueOf(agencyamount);
			String academic = json.getString(AlumnusAndSocialCoppTable.AS_ACADEMIC);
			int as_academic = -999;
			if(!academic.equals(""))
				as_academic = Integer.valueOf(academic);
			String industry = json.getString(AlumnusAndSocialCoppTable.AS_INDUSTRY);
			int as_industry = -999;
			if(!industry.equals(""))
				as_industry = Integer.valueOf(industry);
			String government = json.getString(AlumnusAndSocialCoppTable.AS_GOVERNMENT);
			int as_government = -999;
			if(!government.equals(""))
				as_government = Integer.valueOf(government);
			
			int isnull = 0;
			if(alumnusamount.equals("") || domesticalumnus.equals("") || overseaalumnus.equals("") || 
					agencyamount.equals("") || academic.equals("") || industry.equals("") ||
					government.equals("") )
				isnull = 1;
			if(alumnusamount.equals("") && domesticalumnus.equals("") && overseaalumnus.equals("") && 
					agencyamount.equals("") && academic.equals("") && industry.equals("") &&
					government.equals("") )
				{
					out.print(false);
					return;
				}
			int as_serialnumber = serialnumber;
			String as_college = college;
			String as_comments = "";
			AlumnusAndSocialCoop as = new AlumnusAndSocialCoop(as_alumnusamount, as_domesticalumnus,
					as_overseaalumnus, as_agencyamount, as_academic, as_industry, as_government,
					as_serialnumber,  as_college, as_comments,isnull);
			AlumnusAndSocialCoopDao asDao = new AlumnusAndSocialCoopDaoImpl();
			asDao.addAlumnusAndSocialCoopRecord(as);
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
