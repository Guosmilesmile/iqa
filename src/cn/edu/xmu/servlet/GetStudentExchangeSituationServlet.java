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

import cn.edu.xmu.entity.StudentExchangeSituation;
import cn.edu.xmu.entity.TeachersReformAndAchieve;
import cn.edu.xmu.service.StudentExchangeSituationService;
import cn.edu.xmu.serviceimpl.StudentAssociationServiceImpl;
import cn.edu.xmu.serviceimpl.StudentExchangeSituationServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * luo
 * Servlet implementation class GetStudentExchangeSituationServlet
 */
@WebServlet("/GetStudentExchangeSituationServlet")
public class GetStudentExchangeSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentExchangeSituationService studentExchangeSituationService = new StudentExchangeSituationServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentExchangeSituationServlet() {
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
		GridDataModel<StudentExchangeSituation> model = new GridDataModel<StudentExchangeSituation>();
		
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
	    
	    StudentExchangeSituation studentExchangeSituation = studentExchangeSituationService.getStudentExchangeSituation(queryParams);
	    List<StudentExchangeSituation> studentExchangeSituations = new ArrayList<StudentExchangeSituation>();
	    studentExchangeSituations.add(studentExchangeSituation);
	    
	    
	    int total = studentExchangeSituations.size();
	    model.setTotal(total);
	    model.setRows(studentExchangeSituations);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
