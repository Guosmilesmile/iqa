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

import cn.edu.xmu.entity.TeachResearchReform;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.service.TeachResearchReformService;
import cn.edu.xmu.serviceimpl.TeachResearchReformServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetTeachResearchReformServlet
 */
@WebServlet("/GetTeachResearchReformServlet")
public class GetTeachResearchReformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachResearchReformService teachResearchReformService = new TeachResearchReformServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeachResearchReformServlet() {
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
		GridDataModel<TeachResearchReform> model = new GridDataModel<TeachResearchReform>();
		
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
	    
	    List<TeachResearchReform> teachResearchReforms = teachResearchReformService.getTeachResearchReforms(queryParams);
	    
	    
	    int total = teachResearchReforms.size();
	    model.setTotal(total);
	    model.setRows(teachResearchReforms);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
