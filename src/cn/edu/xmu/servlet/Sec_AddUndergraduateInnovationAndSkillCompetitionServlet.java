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

import cn.edu.xmu.dao.UndergraduateInnovationAndSkillCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateInnovationAndSkillCompetitionDaoImpl;
import cn.edu.xmu.entity.UndergraduateInnovationAndSkillCompetition;
import cn.edu.xmu.table.UndergraduateInnovationAndSkillCompetitionTable;

/**
 * Servlet implementation class AddUndergraduateInnovationAndSkillCompetitionServlet
 */
@WebServlet("/Sec_AddUndergraduateInnovationAndSkillCompetitionServlet")
public class Sec_AddUndergraduateInnovationAndSkillCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraduateInnovationAndSkillCompetitionServlet() {
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
			
			String uiasc_collegename = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGENAME);			
			String uiasc_competitionname = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMPETITIONNAME);
			String uiasc_awardgrade = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_AWARDGRADE);
			String uiasc_prizelevel  =json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL);
			String uiasc_personalorteam = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PERSONALORTEAM);
			String uiasc_studentname  = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNAME);
			String studentnum = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNUM);
			Integer uiasc_studentnum = -1;
			if(!"".equals(studentnum)) uiasc_studentnum = Integer.parseInt(studentnum);
			String uiasc_windate = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_WINDATE);  
			int uiasc_isnull = 0;
			if("".equals(uiasc_collegename)||"".equals(uiasc_competitionname)||"".equals(uiasc_awardgrade)||"".equals(uiasc_prizelevel)
					|| "".equals(uiasc_personalorteam) || "".equals(uiasc_studentname) || "".equals(studentnum) || "".equals(uiasc_windate))
			{
				uiasc_isnull = 1;
			}
			if ("".equals(uiasc_collegename)&&"".equals(uiasc_competitionname)&&"".equals(uiasc_awardgrade)&&"".equals(uiasc_prizelevel)
					&& "".equals(uiasc_personalorteam) && "".equals(uiasc_studentname) && "".equals(studentnum) && "".equals(uiasc_windate))
			{
				result = -1;
				out.println(result);
				return;
			}
			String uiasc_college = request.getParameter(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGE);
			uiasc_college = URLDecoder.decode(uiasc_college,"UTF-8");
			
			UndergraduateInnovationAndSkillCompetition undergraduateInnovationAndSkillCompetition = new UndergraduateInnovationAndSkillCompetition(
					uiasc_collegename, uiasc_competitionname, uiasc_awardgrade, uiasc_prizelevel, uiasc_personalorteam,
				    uiasc_studentname, uiasc_studentnum, uiasc_windate, uiasc_college, serialnumber, uiasc_isnull);
			
			UndergraduateInnovationAndSkillCompetitionDao undergraduateInnovationAndSkillCompetitionDao = new UndergraduateInnovationAndSkillCompetitionDaoImpl();
			result = undergraduateInnovationAndSkillCompetitionDao.addUndergraduateInnovationAndSkillCompetition(undergraduateInnovationAndSkillCompetition);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
