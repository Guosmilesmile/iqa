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

import cn.edu.xmu.dao.UndergraduateArtAndSportCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateArtAndSportCompetitionDaoImpl;
import cn.edu.xmu.entity.UndergraduateArtAndSportCompetition;
import cn.edu.xmu.table.UndergraduateArtAndSportCompetitionTable;

/**
 * Servlet implementation class AddUndergraduateArtAndSportCompetitionServlet
 */
@WebServlet("/Sec_AddUndergraduateArtAndSportCompetitionServlet")
public class Sec_AddUndergraduateArtAndSportCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddUndergraduateArtAndSportCompetitionServlet() {
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
			
			String uaasc_collegename = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGENAME);		
			String uaasc_competitionname = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONNAME);
			String uaasc_competitioncategory = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONCATEGORY);
			String uaasc_awardgrade = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_AWARDGRADE);
			String uaasc_prizelevel  =json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL);
			String uaasc_personalorteam = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_PERSONALORTEAM);
			String uaasc_studentname  = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNAME);
			String studentnum = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNUM);
			Integer uaasc_studentnum = -1;
			if(!"".equals(studentnum)) uaasc_studentnum = Integer.parseInt(studentnum);
			String uaasc_windate = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_WINDATE);  
			int uaasc_isnull = 0;
			if("".equals(uaasc_collegename)||"".equals(uaasc_competitionname)||"".equals(uaasc_competitioncategory)||"".equals(uaasc_awardgrade)||"".equals(uaasc_prizelevel)
					|| "".equals(uaasc_personalorteam) || "".equals(uaasc_studentname) || "".equals(studentnum) || "".equals(uaasc_windate))
			{
				uaasc_isnull = 1;
			}
			if ("".equals(uaasc_collegename)&&"".equals(uaasc_competitionname)&&"".equals(uaasc_competitioncategory)&&"".equals(uaasc_awardgrade)&&"".equals(uaasc_prizelevel)
					&& "".equals(uaasc_personalorteam) && "".equals(uaasc_studentname) && "".equals(studentnum) && "".equals(uaasc_windate))
			{
				result = -1;
				out.println(result);
				return;
			}
			
			String uaasc_college = request.getParameter(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGE);
			uaasc_college = URLDecoder.decode(uaasc_college,"UTF-8");
			UndergraduateArtAndSportCompetition undergraduateArtAndSportCompetition = new UndergraduateArtAndSportCompetition(
					uaasc_collegename, uaasc_competitionname, uaasc_competitioncategory, uaasc_awardgrade, uaasc_prizelevel, uaasc_personalorteam,
				    uaasc_studentname, uaasc_studentnum, uaasc_windate, uaasc_college, serialnumber, uaasc_isnull);
			
			UndergraduateArtAndSportCompetitionDao undergraduateArtAndSportCompetitionDao = new UndergraduateArtAndSportCompetitionDaoImpl();
			result = undergraduateArtAndSportCompetitionDao.addUndergraduateArtAndSportCompetition(undergraduateArtAndSportCompetition);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
