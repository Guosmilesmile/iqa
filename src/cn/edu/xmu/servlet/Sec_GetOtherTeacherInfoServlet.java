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

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.OtherTeacherInfo;
import cn.edu.xmu.table.ExternalTeacherTable;
import cn.edu.xmu.table.FullTimeTeacherInfoTable;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/*
 * 3-1-3 其他师资情况
 */
@WebServlet("/Sec_GetOtherTeacherInfoServlet")
public class Sec_GetOtherTeacherInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();

//	private SuperMajorDao superMajorDao = new SuperMajorDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetOtherTeacherInfoServlet(){
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
		
		OtherTeacherInfoDao otidao = new OtherTeacherInfoDaoImpl();
		GridDataModel<OtherTeacherInfo> model = new GridDataModel<OtherTeacherInfo>();
				
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    String college = request.getParameter("oti_college");
	    String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");  
	    String isnull = request.getParameter("isnull");

	    //System.out.println(college); 
	    
	    Map queryParams = new HashMap();
	    Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
	    System.out.println("deadline:"+deadLine);
	    
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(OtherTeacherInfoTable.OTI_ISNULL, isnull);
	    }
	    //add something for viewtable
	    if(college != null && !"".equals(college)){
	    	
	    	if (college.contains("学院")) {
	    		queryParams.put(OtherTeacherInfoTable.OTI_COLLEGE, college);
			}
	    	else {
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(OtherTeacherInfoTable.OTI_COLLEGE, college);
	    	}
	    }else {
			queryParams = null;
		}
	    int total = otidao.getOtherTeacherInfoCount(queryParams);
	    
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
	    model.setRows(otidao.getAllOtherTeacherInfo(start, end, sortStr, orderStr,queryParams));	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
		
	}

}
