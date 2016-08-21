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

import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.GuidingIdeologyofSchoolDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.GuidingIdeologyofSchool;
import cn.edu.xmu.table.GuidingIdeologyofSchoolTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zhantu
 * Servlet implementation class GetGuidingIdeologyofSchool
 */
@WebServlet("/GetGuidingIdeologyofSchool")
public class Sec_GetGuidingIdeologyofSchool extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetGuidingIdeologyofSchool() {
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
		
		GuidingIdeologyofSchoolDao gisdao = new GuidingIdeologyofSchoolDaoImpl();
		GridDataModel<GuidingIdeologyofSchool> model = new GridDataModel<GuidingIdeologyofSchool>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    
	    String college = request.getParameter("gis_college");
	    String isnull = request.getParameter("isnull");
	    String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(GuidingIdeologyofSchoolTable.GIS_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(GuidingIdeologyofSchoolTable.GIS_COLLEGE, college);
	    	}
	    }
	    else {
			queryParams = null;
		}
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(GuidingIdeologyofSchoolTable.ISNULL, isnull);
	    }
	    int total = gisdao.getGuidingIdeologyofSchoolCount(queryParams);
	    
	    
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
	    model.setRows(gisdao.getGuidingIdeologyofSchool(start, end, sortStr, orderStr, queryParams, deadLine));
	    
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
