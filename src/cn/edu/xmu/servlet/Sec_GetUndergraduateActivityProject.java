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

import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.UndergraduateActivityProjectDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.UndergraduateActivityProject;
import cn.edu.xmu.table.UndergraduateActivityProjectTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * @author zhantu
 * Servlet implementation class GetUndergraduateActivityProject
 */
@WebServlet("/GetUndergraduateActivityProject")
public class Sec_GetUndergraduateActivityProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetUndergraduateActivityProject() {
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
		
		UndergraduateActivityProjectDao uapdao = new UndergraduateActivityProjectDaoImpl();
		GridDataModel<UndergraduateActivityProject> model = new GridDataModel<UndergraduateActivityProject>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    
	    String college = request.getParameter("uap_college");
	    String isnull = request.getParameter("isnull");
	    String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");  
	    
	    Map queryParams = new HashMap();
	    Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
	    
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(UndergraduateActivityProjectTable.UAP_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(UndergraduateActivityProjectTable.UAP_COLLEGE, college);
	    	}
	    }
	    else {
			queryParams = null;
		}

	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(UndergraduateActivityProjectTable.ISNULL, isnull);
	    }
	    int total = uapdao.getUndergraduateActivityProjectCount(queryParams);
	    
	    
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
	    model.setRows(uapdao.getUndergraduateActivityProject(start, end, sortStr, orderStr, queryParams, deadLine));
	    
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
