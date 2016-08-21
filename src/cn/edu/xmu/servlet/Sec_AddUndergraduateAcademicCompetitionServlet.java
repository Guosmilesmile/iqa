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

import cn.edu.xmu.dao.UndergraduateAcademicCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateAcademicCompetitionDaoImpl;
import cn.edu.xmu.entity.UndergraduateAcademicCompetition;
import cn.edu.xmu.table.UndergraduateAcademicCompetitionTable;

/**
 * Servlet implementation class AddUndergraduateAcademicCompetitionServlet
 */
@WebServlet("/Sec_AddUndergraduateAcademicCompetitionServlet")
public class Sec_AddUndergraduateAcademicCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraduateAcademicCompetitionServlet() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		
		try {
			JSONObject json = new JSONObject(data);
			
			String uac_collegename = json.getString(UndergraduateAcademicCompetitionTable.UAC_COLLEGENAME);			
			String uac_competitionname = json.getString(UndergraduateAcademicCompetitionTable.UAC_COMPETITIONNAME);
			String uac_awardgrade = json.getString(UndergraduateAcademicCompetitionTable.UAC_AWARDGRADE);
			String uac_prizelevel  =json.getString(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL);
			String uac_personalorteam = json.getString(UndergraduateAcademicCompetitionTable.UAC_PERSONALORTEAM);
			String uac_studentname  = json.getString(UndergraduateAcademicCompetitionTable.UAC_STUDENTNAME);
			String studentnum = json.getString(UndergraduateAcademicCompetitionTable.UAC_STUDENTNUM);
			Integer uac_studentnum = -1;
			if(!"".equals(studentnum)) uac_studentnum = Integer.parseInt(studentnum);
			String uac_windate = json.getString(UndergraduateAcademicCompetitionTable.UAC_WINDATE);  
			
			int uac_isnull = 0;
			if("".equals(uac_collegename)||"".equals(uac_competitionname)||"".equals(uac_awardgrade)||"".equals(uac_prizelevel)
					|| "".equals(uac_personalorteam) || "".equals(uac_studentname) || "".equals(studentnum) || "".equals(uac_windate))
			{
				uac_isnull = 1;
			}
			if ("".equals(uac_collegename)&&"".equals(uac_competitionname)&&"".equals(uac_awardgrade)&&"".equals(uac_prizelevel)
					&& "".equals(uac_personalorteam) && "".equals(uac_studentname) && "".equals(studentnum) && "".equals(uac_windate))
			{
				result = -1;
				out.println(result);
				return;
			}
			String uac_college = request.getParameter(UndergraduateAcademicCompetitionTable.UAC_COLLEGE);
			uac_college = URLDecoder.decode(uac_college,"UTF-8");
			
			UndergraduateAcademicCompetition undergraduateAcademicCompetition = new UndergraduateAcademicCompetition(
					uac_collegename, uac_competitionname, uac_awardgrade, uac_prizelevel, uac_personalorteam,
				    uac_studentname, uac_studentnum, uac_windate, uac_college,serialnumber, uac_isnull);
			
			UndergraduateAcademicCompetitionDao undergraduateAcademicCompetitionDao = new UndergraduateAcademicCompetitionDaoImpl();
			result = undergraduateAcademicCompetitionDao.addUndergraduateAcademicCompetition(undergraduateAcademicCompetition);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
