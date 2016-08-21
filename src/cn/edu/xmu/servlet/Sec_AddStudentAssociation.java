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

import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.daoimpl.StudentAssociationDaoImpl;
import cn.edu.xmu.entity.StudentAssociation;
import cn.edu.xmu.table.StudentAssociationTable;

/**
 * Servlet implementation class Sec_AddStudentAssociation
 */
@WebServlet("/AddStudentAssociation")
public class Sec_AddStudentAssociation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddStudentAssociation() {
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
		String college = request.getParameter(StudentAssociationTable.SA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		String temp = "";
		try {
			JSONObject json = new JSONObject(data);
			
			temp = json.getString(StudentAssociationTable.SA_SCIENCECOUNT);
			Integer sa_sciencecount = null;
			if(!temp.equals(""))
				sa_sciencecount = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_HUMANISTICCOUNT);
			Integer sa_humanisticcount = null;
			if(!temp.equals(""))
				sa_humanisticcount = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_SPORTSCOUNT);
			Integer sa_sportscount = null;
			if(!temp.equals(""))
				sa_sportscount = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_LITERATUREARTCOUNT);
			Integer sa_literatureartcount = null;
			if(!temp.equals(""))
				sa_literatureartcount = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_OTHERCOUNT);
			Integer sa_othercount = null;
			if(!temp.equals(""))
				sa_othercount = Integer.valueOf(temp);
			Integer sa_sumcount = null;
			if(sa_sciencecount!=null && sa_humanisticcount!=null && sa_sportscount!=null &&
					sa_literatureartcount!=null && sa_othercount!=null)
				sa_sumcount = sa_sciencecount+sa_humanisticcount+sa_sportscount+sa_literatureartcount+sa_othercount;
			
			temp = json.getString(StudentAssociationTable.SA_SCIENCEPERSON);
			Integer sa_scienceperson = null;
			if(!temp.equals(""))
				sa_scienceperson = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_HUMANISTICPERSON);
			Integer sa_humanisticperson = null;
			if(!temp.equals(""))
				sa_humanisticperson = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_SPORTSPERSON);
			Integer sa_sportsperson = null;
			if(!temp.equals(""))
				sa_sportsperson = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_LITERATUREARTPERSON);
			Integer sa_literatureartperson = null;
			if(!temp.equals(""))
				sa_literatureartperson = Integer.valueOf(temp);
			temp = json.getString(StudentAssociationTable.SA_OTHERPERSON);
			Integer sa_otherperson = null;
			if(!temp.equals(""))
				sa_otherperson = Integer.valueOf(temp);
			Integer sa_sumperson = null;
			if(sa_scienceperson!=null && sa_humanisticperson!=null && sa_sportsperson!=null &&
					sa_literatureartperson!=null && sa_otherperson!=null)
				sa_sumperson = sa_scienceperson+sa_humanisticperson+sa_sportsperson+sa_literatureartperson+sa_otherperson;
			
			int isnull = 0;
			if(sa_sciencecount==null || sa_humanisticcount==null || sa_sportscount==null || sa_literatureartcount==null || 
					sa_othercount==null || sa_scienceperson==null || sa_humanisticperson==null || 
					sa_sportsperson==null || sa_literatureartperson==null || sa_otherperson==null)
				isnull = 1;
			if(sa_sciencecount==null && sa_humanisticcount==null && sa_sportscount==null && sa_literatureartcount==null && 
					sa_othercount==null && sa_scienceperson==null && sa_humanisticperson==null && 
					sa_sportsperson==null && sa_literatureartperson==null && sa_otherperson==null)
				return;
			int sa_serialnumber = serialnumber;
			String sa_college = college;
			String sa_comments = "";
			StudentAssociation sa = new StudentAssociation(
					sa_sumcount, sa_sciencecount,
					sa_humanisticcount, sa_sportscount,
					sa_literatureartcount, sa_othercount, sa_sumperson,
					sa_scienceperson, sa_humanisticperson, sa_sportsperson,
					sa_literatureartperson, sa_otherperson,
					sa_serialnumber, sa_college, sa_comments, isnull);			
			System.out.println("add: "+sa_sumcount+" "+ sa_sciencecount+" "+
					sa_humanisticcount+" "+ sa_sportscount+" "+
					sa_literatureartcount+" "+ sa_othercount+" "+ sa_sumperson+" "+
					sa_scienceperson+" "+ sa_humanisticperson+" "+ sa_sportsperson+" "+
					sa_literatureartperson+" "+ sa_otherperson+" "+
					sa_serialnumber+" "+ sa_college+" "+ sa_comments+" "+ isnull); 
			StudentAssociationDao saDao = new StudentAssociationDaoImpl();
			saDao.addRecord(sa);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
