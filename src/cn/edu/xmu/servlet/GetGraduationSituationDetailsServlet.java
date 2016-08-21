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

import cn.edu.xmu.entity.GraduationSituationDetails;
import cn.edu.xmu.service.GraduationSituationDetailsService;
import cn.edu.xmu.serviceimpl.GraduationSituationDetailsServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

@WebServlet("/GetGraduationSituationDetailsServlet")
public class GetGraduationSituationDetailsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private GraduationSituationDetailsService gsdService = new GraduationSituationDetailsServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetGraduationSituationDetailsServlet(){
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
		GridDataModel<GraduationSituationDetails> model = new GridDataModel<GraduationSituationDetails>();
		
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
	    List<GraduationSituationDetails>  gsds = new ArrayList<GraduationSituationDetails>();
	    gsds =	gsdService.getGraduationSituationDetails(queryParams);
	    		
	    
	    int total = gsds.size();
	    model.setTotal(total);
	    model.setRows(gsds);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
