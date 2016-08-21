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

import cn.edu.xmu.entity.BachelorExpPlace;
import cn.edu.xmu.service.BachelorExpPlaceService;
import cn.edu.xmu.serviceimpl.BachelorExpPlaceServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 
 * @author xiaoping 3.2.3本科实验、实习、实训场所情况 date 2015-8-4
 *
 */
@WebServlet("/GetBachelorExpPlaceServlet")
public class GetBachelorExpPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BachelorExpPlaceService beps = new BachelorExpPlaceServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBachelorExpPlaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<BachelorExpPlace> model = new GridDataModel<>();
		
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
//		    	else{
//		    		college = null;
//		    		queryParams.put("college", college);
//		    	}
		    }
		    else {
				queryParams = null;
			}
		    
		    List<BachelorExpPlace> content = beps.get(queryParams);
		    
			 int total = content.size();
			 model.setTotal(total);
			 model.setRows(content);
			 System.out.println(FastJsonTool.createJsonString(model));
			 writer.write(FastJsonTool.createJsonString(model));
	}

}
