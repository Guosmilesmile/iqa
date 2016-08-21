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

import cn.edu.xmu.entity.UnitPersonnelStructure;
import cn.edu.xmu.service.UnitPersonnelStructureService;
import cn.edu.xmu.serviceimpl.UnitPersonnelStructureServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zhantu
 * 附表5 各教学单位实验系列职称人员结构
 */
@WebServlet("/GetUnitPersonnelStructure")
public class GetUnitPersonnelStructure extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UnitPersonnelStructureService unitPersonnelStructureService = new UnitPersonnelStructureServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetUnitPersonnelStructure(){
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
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GridDataModel<UnitPersonnelStructure> model = new GridDataModel<UnitPersonnelStructure>();
		
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
	    
	    List<UnitPersonnelStructure> unitPersonnelStructures = unitPersonnelStructureService.getUnitPersonnelStructure(queryParams);	    
	    
	    int total = unitPersonnelStructures.size();
	    model.setTotal(total);
	    model.setRows(unitPersonnelStructures);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
