package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GovermentalUnitDao;
import cn.edu.xmu.daoimpl.GovermentalUnitDaoImpl;
import cn.edu.xmu.entity.GovermentalUnit;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/*
 * 1-3 学校相关行政单位
 */
@WebServlet("/GetGovermentalUnitServlet")
public class GetGovermentalUnitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
//	private SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetGovermentalUnitServlet(){
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
		GovermentalUnitDao gudao = new GovermentalUnitDaoImpl();
		GridDataModel<GovermentalUnit> model = new GridDataModel<GovermentalUnit>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	       
	    //String college = request.getParameter("college");
	       		       
	    /*Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put("college", college);
	    }*/
	    
	    int total = gudao.getGovermentalUnitCount();
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    model.setRows(gudao.getAllGovermentalUnit(start, end, sortStr, orderStr));
	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
		
		
	}
}



