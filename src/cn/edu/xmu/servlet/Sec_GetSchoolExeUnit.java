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

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetSchoolExeUnit
 */
@WebServlet("/GetSchoolExeUnit")
public class Sec_GetSchoolExeUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetSchoolExeUnit() {
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
		
		SchoolExecutiveUnitDao seudao = new SchoolExecutiveUnitDaoImpl();
		GridDataModel<SchoolExecutiveUnit> model = new GridDataModel<SchoolExecutiveUnit>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    
	    String college = request.getParameter("college");
	  //*********fuzhu 
	    String isnull = request.getParameter("isnull");
	    
	    Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put(SchoolExecutiveUnitTable.SEU_COLLEGE, college);
	    }
	    
	    //***********8复制
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(SchoolExecutiveUnitTable.ISNULL, isnull);
	    }
	    
	    int total = seudao.getSchoolExecutiveUnitCount(queryParams);
	    
	    
	    if(null == pageStr){
	    	pageStr = "1";
	    }if(null == rowsStr){
	    	rowsStr=total+"";
	    }
	    
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    
	    System.out.println("当前第"+page+"页"+rows+"rows");
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    model.setRows(seudao.getAllSchoolExecutiveUnit(start, end, sortStr, orderStr, queryParams));
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
