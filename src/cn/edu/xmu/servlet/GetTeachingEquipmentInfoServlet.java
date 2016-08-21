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

import cn.edu.xmu.entity.TeachingEquipmentInfo;
import cn.edu.xmu.service.TeachingEquipmentInfoService;
import cn.edu.xmu.serviceimpl.TeachingEquipmentInfoServiceImpl;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 3.2.2教学、科研仪器设备情况
 * @author yue
 *
 */
@WebServlet("/GetTeachingEquipmentInfoServlet")
public class GetTeachingEquipmentInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private TeachingEquipmentInfoService teiService = new TeachingEquipmentInfoServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeachingEquipmentInfoServlet(){
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
		GridDataModel<TeachingEquipmentInfo> model = new GridDataModel<TeachingEquipmentInfo>();
		
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
	    List<TeachingEquipmentInfo>  teis = new ArrayList<TeachingEquipmentInfo>();
	    teis =	teiService.getTeachingEquipmentInfo(queryParams);
	    		
	    
	    int total = teis.size();
	    model.setTotal(total);
	    model.setRows(teis);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
