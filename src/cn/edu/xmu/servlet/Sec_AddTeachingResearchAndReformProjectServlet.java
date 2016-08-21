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

import cn.edu.xmu.dao.TeachingResearchAndReformProjectDao;
import cn.edu.xmu.daoimpl.TeachingResearchAndReformProjectDaoImpl;
import cn.edu.xmu.entity.TeachingResearchAndReformProject;
import cn.edu.xmu.table.TeachingResearchAndReformProjectTable;

@WebServlet("/Sec_AddTeachingResearchAndReformProjectServlet")
public class Sec_AddTeachingResearchAndReformProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachingResearchAndReformProjectServlet() {
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
		String college = request.getParameter(TeachingResearchAndReformProjectTable.TRARP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String trarp_projectname = json.getString(TeachingResearchAndReformProjectTable.TRARP_PROJECTNAME);   
			String trarp_compere  = json.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERE); 
			String trarp_comperenumber = json.getString(TeachingResearchAndReformProjectTable.TRARP_COMPERENUMBER); 
		 	String trarp_level = json.getString(TeachingResearchAndReformProjectTable.TRARP_LEVEL);  
			String trarp_setupdate = json.getString(TeachingResearchAndReformProjectTable.TRARP_SETUPDATE); 
			String trarp_checkdate = json.getString(TeachingResearchAndReformProjectTable.TRARP_CHECKDATE);  
			String funds = json.getString(TeachingResearchAndReformProjectTable.TRARP_FUNDS);
			Float trarp_funds = (float) -1.0;
			if(!funds.equals("")) trarp_funds = Float.parseFloat(funds);
		    String jointeachers = json.getString(TeachingResearchAndReformProjectTable.TRARP_JOINTEACHERS);
		    Integer trarp_jointeachers = -1;
			if(!jointeachers.equals("")) trarp_jointeachers = Integer.parseInt(jointeachers);
			int trarp_isnull = 0;
			if("".equals(trarp_projectname)||"".equals(trarp_compere)||"".equals(trarp_comperenumber)||"".equals(trarp_level)
					|| "".equals(trarp_setupdate) || "".equals(trarp_checkdate) || "".equals(funds) || "".equals(jointeachers))
			{
				trarp_isnull = 1;
			}
			if ("".equals(trarp_projectname) && "".equals(trarp_compere) && "".equals(trarp_comperenumber) && "".equals(trarp_level)
					&&  "".equals(trarp_setupdate) && "".equals(trarp_checkdate) && "".equals(funds) && "".equals(jointeachers))
			{
				result = -1;
				out.println(result);
				return;
			}
			TeachingResearchAndReformProject teachingResearchAndReformProject = new TeachingResearchAndReformProject(trarp_projectname,trarp_compere,trarp_comperenumber,trarp_level,
					trarp_setupdate,trarp_checkdate,trarp_funds,trarp_jointeachers,college,serialnumber,trarp_isnull);
			
			TeachingResearchAndReformProjectDao teachingResearchAndReformProjectDao = new TeachingResearchAndReformProjectDaoImpl();
			result = teachingResearchAndReformProjectDao.addTeachingResearchAndReformProject(teachingResearchAndReformProject);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
