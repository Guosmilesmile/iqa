package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/*
 * 3-3 管理人员基本信息
 */
@WebServlet("/GetManagerInfoServlet")
public class GetManagerInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
//	private SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetManagerInfoServlet(){
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
		ManagerInfoDao midao = new ManagerInfoDaoImpl();
		GridDataModel<ManagerInfo> model = new GridDataModel<ManagerInfo>();
				
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	 
	    int total = midao.getManagerInfoCount(null);
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    Date deadLine = new Date(0);
	    Map queryParams = new HashMap();
	    
	    model.setRows(midao.getManagerInfo(start, end, sortStr, orderStr,queryParams,deadLine));	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}


}
