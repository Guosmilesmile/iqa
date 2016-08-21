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

import cn.edu.xmu.dao.UndergraduateAcademicCompetitionDao;
import cn.edu.xmu.daoimpl.UndergraduateAcademicCompetitionDaoImpl;
import cn.edu.xmu.table.UndergraduateAcademicCompetitionTable;

/**
 * Servlet implementation class UpdateForeignExchangServlet
 */
@WebServlet("/Sec_UpdateUndergraduateAcademicCompetitionServlet")
public class Sec_UpdateUndergraduateAcademicCompetitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateUndergraduateAcademicCompetitionServlet() {
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
					   int uac_id = jsons.getJSONObject(i).getInt(UndergraduateAcademicCompetitionTable.UAC_ID);
					  
					    String uac_comments = jsons.getJSONObject(i).getString(UndergraduateAcademicCompetitionTable.UAC_COMMENTS);
					
					
					Map<String,String> params= new HashMap<String, String>();
					 params.put(UndergraduateAcademicCompetitionTable.UAC_ID, uac_id+"");
					  
					    params.put(UndergraduateAcademicCompetitionTable.UAC_COMMENTS, uac_comments);
					
					UndergraduateAcademicCompetitionDao undergraduateAcademicCompetitionDao = new UndergraduateAcademicCompetitionDaoImpl();
					int result = undergraduateAcademicCompetitionDao.alterUndergraduateAcademicCompetition(params, uac_id+"");
					out.print(result);
				}
			}else {
				
				//普通更新
				JSONObject json = new JSONObject(data);
			
			    int uac_id = json.getInt(UndergraduateAcademicCompetitionTable.UAC_ID);
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
				
			    String uac_comments = json.getString(UndergraduateAcademicCompetitionTable.UAC_COMMENTS);
			    
			    Map<String,String> params= new HashMap<String, String>();
			    params.put(UndergraduateAcademicCompetitionTable.UAC_ID, uac_id+"");
			    params.put(UndergraduateAcademicCompetitionTable.UAC_COLLEGENAME, uac_collegename);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_COMPETITIONNAME, uac_competitionname);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_AWARDGRADE, uac_awardgrade);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_PRIZELEVEL, uac_prizelevel);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_PERSONALORTEAM, uac_personalorteam);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_STUDENTNAME, uac_studentname);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_STUDENTNUM, uac_studentnum+"");
			    params.put(UndergraduateAcademicCompetitionTable.UAC_WINDATE, uac_windate);
			   
			    params.put(UndergraduateAcademicCompetitionTable.UAC_COMMENTS, uac_comments);
			    params.put(UndergraduateAcademicCompetitionTable.UAC_ISNULL, uac_isnull+"");

			UndergraduateAcademicCompetitionDao undergraduateAcademicCompetitionDao = new UndergraduateAcademicCompetitionDaoImpl();
			int result = undergraduateAcademicCompetitionDao.alterUndergraduateAcademicCompetition(params, uac_id+"");
			    out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
