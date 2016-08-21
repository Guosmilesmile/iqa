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

import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.daoimpl.StudentAssociationDaoImpl;
import cn.edu.xmu.table.StudentAssociationTable;

/**
 * Servlet implementation class Sec_UpdateStudentAssociation
 */
@WebServlet("/UpdateStudentAssociation")
public class Sec_UpdateStudentAssociation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateStudentAssociation() {
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
		String patter = request.getParameter("patter");
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		//String college = request.getParameter(StudentAssociationTable.SA_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if(patter!=null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					//无serialnumber,deadline,college
					String sa_id = jsons.getJSONObject(i).getString(StudentAssociationTable.SA_ID);
					
					String sa_comments = jsons.getJSONObject(i).getString(StudentAssociationTable.SA_COMMENTS);
				
					Map<String,String> params= new HashMap<String, String>();
					params.put(StudentAssociationTable.SA_ID, sa_id);
					params.put(StudentAssociationTable.SA_COMMENTS, sa_comments);
				
					StudentAssociationDao saDao = new StudentAssociationDaoImpl();
					int result = saDao.alterStudentAssociation(params, sa_id);
				
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
			
				String sa_id = json.getString(StudentAssociationTable.SA_ID);

				String sa_sciencecount = json.getString(StudentAssociationTable.SA_SCIENCECOUNT);
				String sa_humanisticcount = json.getString(StudentAssociationTable.SA_HUMANISTICCOUNT);
				String sa_sportscount = json.getString(StudentAssociationTable.SA_SPORTSCOUNT);
				String sa_literatureartcount = json.getString(StudentAssociationTable.SA_LITERATUREARTCOUNT);
				String sa_othercount = json.getString(StudentAssociationTable.SA_OTHERCOUNT);
				Integer sa_sumcount_temp = null;
				if(!sa_sciencecount.equals("") && !sa_humanisticcount.equals("") && !sa_sportscount.equals("") &&
						!sa_literatureartcount.equals("") && !sa_othercount.equals(""))
					sa_sumcount_temp = Integer.valueOf(sa_sciencecount) +
										Integer.valueOf(sa_humanisticcount)+
										Integer.valueOf(sa_sportscount)+
										Integer.valueOf(sa_literatureartcount)+
										Integer.valueOf(sa_othercount);
				String sa_sumcount = "";
				if(sa_sumcount_temp!=null)
					sa_sumcount = String.valueOf(sa_sumcount_temp);
				
				String sa_scienceperson = json.getString(StudentAssociationTable.SA_SCIENCEPERSON);
				String sa_humanisticperson = json.getString(StudentAssociationTable.SA_HUMANISTICPERSON);
				String sa_sportsperson = json.getString(StudentAssociationTable.SA_SPORTSPERSON);
				String sa_literatureartperson = json.getString(StudentAssociationTable.SA_LITERATUREARTPERSON);
				String sa_otherperson = json.getString(StudentAssociationTable.SA_OTHERPERSON);
				Integer sa_sumperson_temp = null;
				if(!sa_scienceperson.equals("") && !sa_humanisticperson.equals("") && !sa_sportsperson.equals("") &&
						!sa_literatureartperson.equals("") && !sa_otherperson.equals(""))
					sa_sumperson_temp = Integer.valueOf(sa_scienceperson) +
										Integer.valueOf(sa_humanisticperson)+
										Integer.valueOf(sa_sportsperson)+
										Integer.valueOf(sa_literatureartperson)+
										Integer.valueOf(sa_otherperson);
				String sa_sumperson = "";
				if(sa_sumperson_temp!=null)
					sa_sumperson = String.valueOf(sa_sumperson_temp);
				
				int isnull = 0;
				if(sa_sciencecount.equals("") || sa_humanisticcount.equals("") || sa_sportscount.equals("") || sa_literatureartcount.equals("") || 
						sa_othercount.equals("") || sa_scienceperson.equals("") || sa_humanisticperson.equals("") || 
						sa_sportsperson.equals("") || sa_literatureartperson.equals("") || sa_otherperson.equals(""))
					isnull = 1;
			
				Map<String,String> params= new HashMap<String, String>();
				params.put(StudentAssociationTable.SA_ID, sa_id);
				if(!sa_sumcount.equals(""))
				params.put(StudentAssociationTable.SA_SUMCOUNT, sa_sumcount);
				if(!sa_sciencecount.equals(""))
				params.put(StudentAssociationTable.SA_SCIENCECOUNT, sa_sciencecount);
				if(!sa_humanisticcount.equals(""))
				params.put(StudentAssociationTable.SA_HUMANISTICCOUNT, sa_humanisticcount);
				if(!sa_sportscount.equals(""))
				params.put(StudentAssociationTable.SA_SPORTSCOUNT, sa_sportscount);
				if(!sa_literatureartcount.equals(""))
				params.put(StudentAssociationTable.SA_LITERATUREARTCOUNT, sa_literatureartcount);
				if(!sa_othercount.equals(""))
				params.put(StudentAssociationTable.SA_OTHERCOUNT, sa_othercount);

				if(!sa_sumperson.equals(""))
				params.put(StudentAssociationTable.SA_SUMPERSON, sa_sumperson);
				if(!sa_scienceperson.equals(""))
				params.put(StudentAssociationTable.SA_SCIENCEPERSON, sa_scienceperson);
				if(!sa_humanisticperson.equals(""))
				params.put(StudentAssociationTable.SA_HUMANISTICPERSON, sa_humanisticperson);
				if(!sa_sportsperson.equals(""))
				params.put(StudentAssociationTable.SA_SPORTSPERSON, sa_sportsperson);
				if(!sa_literatureartperson.equals(""))
				params.put(StudentAssociationTable.SA_LITERATUREARTPERSON, sa_literatureartperson);
				if(!sa_otherperson.equals(""))
				params.put(StudentAssociationTable.SA_OTHERPERSON, sa_otherperson);
				params.put(StudentAssociationTable.ISNULL, isnull+"");
				
			
				StudentAssociationDao saDao = new StudentAssociationDaoImpl();
				int result = saDao.alterStudentAssociation(params, sa_id);
				
				out.print(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
