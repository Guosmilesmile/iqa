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
import cn.edu.xmu.service.DegreeSpotService;
import cn.edu.xmu.serviceimpl.DegreeSpotServiceImpl;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zsj
 * 概要2 学位点概况
 */
@WebServlet("/GetDegreeSpotServlet")
public class GetDegreeSpotServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DegreeSpotService degreeSpotService = new DegreeSpotServiceImpl();
	private TableListDao tableListDao = new TableListDaoImpl();	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GetDegreeSpotServlet(){
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
		GridDataModel<DegreeSpot> model = new GridDataModel<DegreeSpot>();
		
		
	    
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
	    
	    DegreeSpot degreeSpot = degreeSpotService.getDegreeSpot(queryParams);
	   
	    List<DegreeSpot> degreeSpots = new ArrayList<DegreeSpot>();
	    degreeSpots.add(degreeSpot);
	    
	    int total = degreeSpots.size();
	    model.setTotal(total);
	    model.setRows(degreeSpots);	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}
}
