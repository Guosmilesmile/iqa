package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import cn.edu.xmu.dao.TeachResearchUnitDao;
import cn.edu.xmu.daoimpl.TeachResearchUnitDaoImpl;
import cn.edu.xmu.entity.TeachResearchUnit;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/*
 * 1-4 学校教学科研单位
 */
@WebServlet("/GetTeachResearchUnitServlet")
public class GetTeachResearchUnitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
//	private SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeachResearchUnitServlet(){
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
		TeachResearchUnitDao trdao = new TeachResearchUnitDaoImpl();
		GridDataModel<TeachResearchUnit> model = new GridDataModel<TeachResearchUnit>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	       
	    //String college = request.getParameter("college");
	       		       
	    /*Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put("college", college);
	    }*/
	    
	    int total = trdao.getTeachResearchUnitCount();
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    model.setRows(trdao.getAllTeachResearchUnit(start, end, sortStr, orderStr));
	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
		
		
	}
}



