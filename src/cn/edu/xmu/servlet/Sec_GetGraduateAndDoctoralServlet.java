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

import cn.edu.xmu.dao.GraduateAndDoctoralDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.GraduateAndDoctoralDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.GraduateAndDoctoral;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
@WebServlet("/Sec_GetGraduateAndDoctoralServlet")
public class Sec_GetGraduateAndDoctoralServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetGraduateAndDoctoralServlet(){
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
		GraduateAndDoctoralDao gdDao = new GraduateAndDoctoralDaoImpl();

		GridDataModel<GraduateAndDoctoral> model = new GridDataModel<GraduateAndDoctoral>();
				
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    
	    String college = request.getParameter("gd_college");
	    
	    String isnull = request.getParameter("isnull");
	    
	    String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");  
	    System.out.println("tableid:"+tableid);
	    Map queryParams = new HashMap();
	    Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
	    
	    System.out.println("deadline:"+deadLine);
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(GraduateAndDoctoralTable.ISNULL, isnull);
	    }
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(GraduateAndDoctoralTable.GD_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(GraduateAndDoctoralTable.GD_COLLEGE, college);
	    	}
	    }
	    else {
			queryParams = null;
		}
	   
	    
	    int total = gdDao.getGraduateAndDoctoralCount(queryParams);
	    if(null == pageStr){
	    	pageStr = "1";
	    }if(null == rowsStr){
	    	rowsStr=total+"";
	    }
	    
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    model.setRows(gdDao.getGraduateAndDoctoral(start, end, sortStr, orderStr,queryParams,deadLine));	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}

}
