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

import cn.edu.xmu.entity.ManagerStrcture;
import cn.edu.xmu.service.ManagerStructureService;
import cn.edu.xmu.serviceimpl.ManagerStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

@WebServlet("/GetManagerStructureServlet")
public class GetManagerStructureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerStructureService mss = new ManagerStructureServiceImpl();
	
	public GetManagerStructureServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		this.doPost(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<ManagerStrcture> model = new GridDataModel<>();
		
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
	    
	    List<ManagerStrcture> content = mss.get(queryParams);
	    
	    int total = 1;
	    model.setTotal(total);
		model.setRows(content);
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));
	}

}
