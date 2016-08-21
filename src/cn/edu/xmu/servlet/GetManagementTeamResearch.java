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

import cn.edu.xmu.entity.ManagementTeamResearch;
import cn.edu.xmu.service.ManagementTeamResearchService;
import cn.edu.xmu.serviceimpl.ManagementTeamResearchServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zhantu
 * 6.3 教学管理队伍教学研究情况
 */
@WebServlet("/GetManagementTeamResearch")
public class GetManagementTeamResearch extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ManagementTeamResearchService managementTeamResearchService = new ManagementTeamResearchServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetManagementTeamResearch(){
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
		GridDataModel<ManagementTeamResearch> model = new GridDataModel<ManagementTeamResearch>();
		
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
	    
	    ManagementTeamResearch managementTeamResearch= managementTeamResearchService.getManagementTeamResearch(queryParams);
	    List<ManagementTeamResearch> managementTeamResearchs = new ArrayList<ManagementTeamResearch>();
	    managementTeamResearchs.add(managementTeamResearch);
	    
	    
	    int total = managementTeamResearchs.size();
	    model.setTotal(total);
	    model.setRows(managementTeamResearchs);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
