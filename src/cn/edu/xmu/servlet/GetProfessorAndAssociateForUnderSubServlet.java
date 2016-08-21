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

import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.ProfessorAndAssociateForUnderSub;
import cn.edu.xmu.entity.TeacherStructure;
import cn.edu.xmu.service.ProfessorAndAssociateForUnderSubService;
import cn.edu.xmu.service.TeacherStructureService;
import cn.edu.xmu.serviceimpl.ProfessorAndAssociateForUnderSubServiceImpl;
import cn.edu.xmu.serviceimpl.TeacherStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zsj
 * 2.2 教师队伍结构
 */
@WebServlet("/GetProfessorAndAssociateForUnderSubServlet")
public class GetProfessorAndAssociateForUnderSubServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ProfessorAndAssociateForUnderSubService professorAndAssociateForUnderSubService = new ProfessorAndAssociateForUnderSubServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfessorAndAssociateForUnderSubServlet(){
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
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<ProfessorAndAssociateForUnderSub> model = new GridDataModel<ProfessorAndAssociateForUnderSub>();
		
		
	    
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
	    
	   
	    List<ProfessorAndAssociateForUnderSub> professorAndAssociateForUnderSubs = professorAndAssociateForUnderSubService.getProfessorAndAssociateForUnderSub(queryParams);
	    
	    
	    int total = 1;
	    model.setTotal(total);
	    model.setRows(professorAndAssociateForUnderSubs);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
