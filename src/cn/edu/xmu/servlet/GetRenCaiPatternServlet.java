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

import cn.edu.xmu.dao.RenCaiPatternDao;
import cn.edu.xmu.daoimpl.RenCaiPatternDaoImpl;
import cn.edu.xmu.entity.RenCaiPattern;
import cn.edu.xmu.table.RenCaiPatternTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 统计时用的
 * @author chunfeng
 *
 */
@WebServlet("/GetRenCaiPatternServlet")
public class GetRenCaiPatternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRenCaiPatternServlet() {
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
		
		RenCaiPatternDao rcpdao = new RenCaiPatternDaoImpl();
		GridDataModel<RenCaiPattern> model = new GridDataModel<RenCaiPattern>();
				
	    String college = request.getParameter("college");
	       		       
	    Map queryParams = new HashMap();
	    if(college != null && !"".equals(college)){
	    	queryParams.put(RenCaiPatternTable.RCP_COLLEGE, college);
	    }
	    
	    int total = rcpdao.getRenCaiPatternCount(queryParams);	    	   	
	    model.setTotal(total);
	    model.setRows(rcpdao.getRenCaiPattern(queryParams));
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
