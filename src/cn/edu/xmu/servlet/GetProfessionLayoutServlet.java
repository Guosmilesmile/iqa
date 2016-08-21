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

import cn.edu.xmu.entity.ProfessionLayout;
import cn.edu.xmu.service.ProfessionLayoutService;
import cn.edu.xmu.serviceimpl.ProfessionLayoutServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 
 * @author xiaoping 1.5 专业布局概览 date 2015-8-5
 *
 */
@WebServlet("/GetProfessionLayoutServlet")
public class GetProfessionLayoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessionLayoutService pls = new ProfessionLayoutServiceImpl();
	
	public GetProfessionLayoutServlet(){
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
		GridDataModel<ProfessionLayout> model = new GridDataModel<>();
		
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
		    
		 List<ProfessionLayout> content = pls.get(queryParams);
		 
		 int total = content.size();
		 model.setTotal(total);
		 model.setRows(content);
		 System.out.println(FastJsonTool.createJsonString(model));
		 writer.write(FastJsonTool.createJsonString(model));
	
	}
}
