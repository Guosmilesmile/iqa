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

import cn.edu.xmu.dao.UndergraduateArtAndSportCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateArtAndSportCompetitionDaoImpl;
import cn.edu.xmu.table.UndergraduateArtAndSportCompetitionTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateUndergraduateArtAndSportCompetitionServlet")
public class Sec_UpdateUndergraduateArtAndSportCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraduateArtAndSportCompetitionServlet() {
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
					   int uaasc_id = jsons.getJSONObject(i).getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ID);
					  
					    String uaasc_comments = jsons.getJSONObject(i).getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS);
					
					
					Map<String,String> params= new HashMap<String, String>();
					 params.put(UndergraduateArtAndSportCompetitionTable.UAASC_ID, uaasc_id+"");
					  
					    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS, uaasc_comments);
					
					UndergraduateArtAndSportCompetitionDao undergraduateArtAndSportCompetitionDao = new UndergraduateArtAndSportCompetitionDaoImpl();
					int result = undergraduateArtAndSportCompetitionDao.alterUndergraduateArtAndSportCompetition(params, uaasc_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
			
			    int uaasc_id = json.getInt(UndergraduateArtAndSportCompetitionTable.UAASC_ID);
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
			    String uaasc_comments = json.getString(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS);
			
			
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_ID, uaasc_id+"");
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_COLLEGENAME, uaasc_collegename);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONNAME, uaasc_competitionname);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_COMPETITIONCATEGORY, uaasc_competitioncategory);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_AWARDGRADE, uaasc_awardgrade);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_PRIZELEVEL, uaasc_prizelevel);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_PERSONALORTEAM, uaasc_personalorteam);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNAME, uaasc_studentname);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_STUDENTNUM, uaasc_studentnum+"");
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_WINDATE, uaasc_windate);
			   
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_COMMENTS, uaasc_comments);
			    params.put(UndergraduateArtAndSportCompetitionTable.UAASC_ISNULL, uaasc_isnull+"");
			
			UndergraduateArtAndSportCompetitionDao undergraduateArtAndSportCompetitionDao = new UndergraduateArtAndSportCompetitionDaoImpl();
			int result = undergraduateArtAndSportCompetitionDao.alterUndergraduateArtAndSportCompetition(params, uaasc_id+"");
			out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
