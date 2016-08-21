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

import cn.edu.xmu.entity.UndergraduateAwardLoanInfo;
import cn.edu.xmu.service.UndergraduateAwardLoanInfoService;
import cn.edu.xmu.serviceimpl.UndergraduateAwardLoanInfoServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

@WebServlet("/GetUndergraduateAwardLoanInfoServlet")
public class GetUndergraduateAwardLoanInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UndergraduateAwardLoanInfoService ualiService = new UndergraduateAwardLoanInfoServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetUndergraduateAwardLoanInfoServlet(){
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
		GridDataModel<UndergraduateAwardLoanInfo> model = new GridDataModel<UndergraduateAwardLoanInfo>();
		
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
	    
	    UndergraduateAwardLoanInfo uali = ualiService.getUndergraduateAwardLoanInfo(queryParams);
	    List<UndergraduateAwardLoanInfo> uals = new ArrayList<UndergraduateAwardLoanInfo>();
	    uals.add(uali);
	    
	    
	    int total = uals.size();
	    model.setTotal(total);
	    model.setRows(uals);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
