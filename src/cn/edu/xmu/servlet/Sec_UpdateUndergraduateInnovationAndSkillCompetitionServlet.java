package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.UndergraduateInnovationAndSkillCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateInnovationAndSkillCompetitionDaoImpl;
import cn.edu.xmu.table.UndergraduateInnovationAndSkillCompetitionTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateUndergraduateInnovationAndSkillCompetitionServlet")
public class Sec_UpdateUndergraduateInnovationAndSkillCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraduateInnovationAndSkillCompetitionServlet() {
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
		String patter = request.getParameter("patter");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {					
					   int uiasc_id = jsons.getJSONObject(i).getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID);
					 
					    String uiasc_comments = jsons.getJSONObject(i).getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS);
					
					
					Map<String,String> params= new HashMap<String, String>();
					 params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID, uiasc_id+"");
					 
					    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS, uiasc_comments);
					
					UndergraduateInnovationAndSkillCompetitionDao undergraduateInnovationAndSkillCompetitionDao = new UndergraduateInnovationAndSkillCompetitionDaoImpl();
					int result = undergraduateInnovationAndSkillCompetitionDao.alterUndergraduateInnovationAndSkillCompetition(params, uiasc_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
			
			    int uiasc_id = json.getInt(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID);
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
			    String uiasc_comments = json.getString(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS);
			 
			
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ID, uiasc_id+"");
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COLLEGENAME, uiasc_collegename);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMPETITIONNAME, uiasc_competitionname);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_AWARDGRADE, uiasc_awardgrade);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PRIZELEVEL, uiasc_prizelevel);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_PERSONALORTEAM, uiasc_personalorteam);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNAME, uiasc_studentname);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_STUDENTNUM, uiasc_studentnum+"");
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_WINDATE, uiasc_windate);
			   
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_COMMENTS, uiasc_comments);
			    params.put(UndergraduateInnovationAndSkillCompetitionTable.UIASC_ISNULL, uiasc_isnull+"");
			
			UndergraduateInnovationAndSkillCompetitionDao undergraduateInnovationAndSkillCompetitionDao = new UndergraduateInnovationAndSkillCompetitionDaoImpl();
			int result = undergraduateInnovationAndSkillCompetitionDao.alterUndergraduateInnovationAndSkillCompetition(params, uiasc_id+"");
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
