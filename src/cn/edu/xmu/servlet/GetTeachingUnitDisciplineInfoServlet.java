package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.TeachingUnitDisciplineInfo;
import cn.edu.xmu.service.TeachingUnitDisciplineInfoService;
import cn.edu.xmu.serviceimpl.TeachingUnitDisciplineInfoServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zsj
 * 1.6 教学单位学科专业概览
 */
@WebServlet("/GetTeachingUnitDisciplineInfoServlet")
public class GetTeachingUnitDisciplineInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private TeachingUnitDisciplineInfoService teachingUnitDisciplineInfoService = new TeachingUnitDisciplineInfoServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeachingUnitDisciplineInfoServlet(){
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
		GridDataModel<TeachingUnitDisciplineInfo> model = new GridDataModel<TeachingUnitDisciplineInfo>();
		
		
	    
	    String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
	    
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    System.out.println(deadline);
	    System.out.println(deadline == null);
	    if (deadline != null && !deadline.equals("")) {
	    	queryParams.put("deadline", deadline);
		}
	    
	    System.out.println(queryParams);
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
	    
	    
	   
	    List<TeachingUnitDisciplineInfo> teachingUnitDisciplineInfos = teachingUnitDisciplineInfoService.getTeachingUnitDisciplineInfos(queryParams);;
	    
	    int total = teachingUnitDisciplineInfos.size();
	    model.setTotal(total);
	    model.setRows(teachingUnitDisciplineInfos);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
