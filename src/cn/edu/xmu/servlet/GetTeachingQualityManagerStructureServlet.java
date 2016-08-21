package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.DegreeSpot;
import cn.edu.xmu.entity.MajorInfo;
import cn.edu.xmu.entity.TeachingQualityManagerStructure;
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.service.TeachingQualityManagerStructureService;
import cn.edu.xmu.serviceimpl.DegreeSpotServiceImpl;
import cn.edu.xmu.serviceimpl.TeachingQualityManagerStructureServiceImpl;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zsj
 * 6.2 教学质量管理队伍结构
 */
@WebServlet("/GetTeachingQualityManagerStructureServlet")
public class GetTeachingQualityManagerStructureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private TeachingQualityManagerStructureService tqmsService = new TeachingQualityManagerStructureServiceImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeachingQualityManagerStructureServlet(){
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
		GridDataModel<TeachingQualityManagerStructure> model = new GridDataModel<TeachingQualityManagerStructure>();
		
		
	    
	    String college = request.getParameter("college");
	    String deadline = request.getParameter("deadline");
	    
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    if (deadline != null) {
	    	queryParams.put("deadline", deadline);
		}
	    
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("院")) {//具体的学院才过滤
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
	    
	   
	    List<TeachingQualityManagerStructure> tqms = tqmsService.getteManagerStructure(queryParams);
	    
	    
	    int total = tqms.size();
	    model.setTotal(total);
	    model.setRows(tqms);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
