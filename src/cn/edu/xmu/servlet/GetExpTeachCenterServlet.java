package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.ExpTeachCenterDao;
import cn.edu.xmu.daoimpl.ExpTeachCenterDaoImpl;
import cn.edu.xmu.entity.ExpTeachCenter;
import cn.edu.xmu.table.ExpTeachCenterTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetSchoolExeUnit
 */
@WebServlet("/GetExpTeachCenterServlet")
public class GetExpTeachCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetExpTeachCenterServlet() {
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
		
		
		//编码统一
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//输入流
		PrintWriter writer = response.getWriter();
		
		ExpTeachCenterDao rcpdao = new ExpTeachCenterDaoImpl();
		GridDataModel<ExpTeachCenter> model = new GridDataModel<ExpTeachCenter>();
		
	    String college = request.getParameter("college");
	       		       
	    Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put(ExpTeachCenterTable.ETC_COLLEGE, college);
	    }
	    
	    System.out.println("=======");
	    
	    int total = rcpdao.getExpTeachCenterCount(queryParams);
	    model.setTotal(total);
	    model.setRows(rcpdao.getExpTeachCenter(queryParams));
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
