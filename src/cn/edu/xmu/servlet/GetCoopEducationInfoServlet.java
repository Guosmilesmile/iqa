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

import cn.edu.xmu.entity.CoopEducationInfo;
import cn.edu.xmu.service.CoopEducationInfoService;
import cn.edu.xmu.serviceimpl.CoopEducationInfoServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.10 合作办学情况
 * @author yue
 *
 */
@WebServlet("/GetCoopEducationInfoServlet")
public class GetCoopEducationInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private CoopEducationInfoService ceiService = new CoopEducationInfoServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetCoopEducationInfoServlet(){
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
		GridDataModel<CoopEducationInfo> model = new GridDataModel<CoopEducationInfo>();
		
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
	    CoopEducationInfo cei = ceiService.getCoopEducationInfo(queryParams);
	    List<CoopEducationInfo>  ceis = new ArrayList<CoopEducationInfo>();
	    ceis.add(cei);
	    		
	    
	    int total = ceis.size();
	    model.setTotal(total);
	    model.setRows(ceis);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
