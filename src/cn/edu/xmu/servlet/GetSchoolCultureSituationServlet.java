package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.SchoolCultureSituation;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.service.SchoolCultureSituationService;
import cn.edu.xmu.serviceimpl.SchoolCultureSituationServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * luo
 * Servlet implementation class SchoolCultureSituationServlet
 */
@WebServlet("/SchoolCultureSituationServlet")
public class GetSchoolCultureSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchoolCultureSituationService schoolCultureSituationService = new SchoolCultureSituationServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSchoolCultureSituationServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<SchoolCultureSituation> model = new GridDataModel<SchoolCultureSituation>();
		
	    String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
	    
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    if (deadline != null) {
	    	queryParams.put("deadline", deadline);
		}
	    
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put("college", college);
			}
	    	else{
	    		/*college = null;
	    		queryParams.put("college", college);*/
	    	}
	    }
	    else {
			queryParams = null;
		}
	    
	    SchoolCultureSituation schoolCultureSituation = schoolCultureSituationService.getSchoolCultureSituation(queryParams);
	    List<SchoolCultureSituation> schoolCultureSituations = new ArrayList<SchoolCultureSituation>();
	    schoolCultureSituations.add(schoolCultureSituation);
	    
	    int total = schoolCultureSituations.size();
	    model.setTotal(total);
	    model.setRows(schoolCultureSituations);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
