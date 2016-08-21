package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.MajorTeacherStructure;
import cn.edu.xmu.service.MajorTeacherStructureService;
import cn.edu.xmu.serviceimpl.MajorTeacherStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zhantu
 * 2.4 主讲教师本科授课情况
 */
public class GetMajorTeacherStructureTOP5LOW5 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MajorTeacherStructureService majorTeacherStructureService = new MajorTeacherStructureServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetMajorTeacherStructureTOP5LOW5(){
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
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<MajorTeacherStructure> model = new GridDataModel<MajorTeacherStructure>();
		
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
	    
	    List<MajorTeacherStructure> majorTeacherStructure = majorTeacherStructureService.getMajorTeacherStructureTOP5LOW5(queryParams);
	    
	    
	    int total = majorTeacherStructure.size();
	    model.setTotal(total);
	    model.setRows(majorTeacherStructure);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
