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
import cn.edu.xmu.entity.LabStuffInfo;
import cn.edu.xmu.entity.TeacherStructure;
import cn.edu.xmu.service.LabStuffInfoService;
import cn.edu.xmu.service.TeacherStructureService;
import cn.edu.xmu.serviceimpl.LabStuffServiceImpl;
import cn.edu.xmu.serviceimpl.TeacherStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zsj
 * 2.8 学校实验系列人员结构
 */
@WebServlet("/GetLabStuffInfoServlet")
public class GetLabStuffInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LabStuffInfoService labStuffInfoService = new LabStuffServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetLabStuffInfoServlet(){
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
		GridDataModel<LabStuffInfo> model = new GridDataModel<LabStuffInfo>();
		
		
	    
	    String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
	    
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    if (deadline != null) {
	    	queryParams.put("deadline", deadline);
		}
	    
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("院")) {//具体的学院才过滤
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
	    
	   
	    List<LabStuffInfo> labStuffInfos = labStuffInfoService.getLabStuffInfos(queryParams);
	    
	    int total = 2;
	    model.setTotal(total);
	    model.setRows(labStuffInfos);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
