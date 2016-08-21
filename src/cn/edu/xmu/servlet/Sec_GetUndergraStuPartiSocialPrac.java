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

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraStuPartiSocialPracDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;
/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-10
 *
 */
@WebServlet("/Sec_GetUndergraStuPartiSocialPrac")
public class Sec_GetUndergraStuPartiSocialPrac extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();
	public Sec_GetUndergraStuPartiSocialPrac()
	{
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		UndergraStuPartiSocialPracDao uspspDao = new UndergraStuPartiSocialPracDaoImpl();
		GridDataModel<UndergraStuPartiSocialPrac> model = new GridDataModel<UndergraStuPartiSocialPrac>();
		
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	       
	    String college = request.getParameter("uspsp_college");
	    String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");
	    String isnull = request.getParameter("isnull");
	    System.out.println("tableid:"+tableid);
	    Map queryParams = new HashMap();
	    Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
	    System.out.println("deadline:"+deadLine);
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(UndergraStuPartiSocialPracTable.USPSP_ISNULL, isnull);
	    }
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(UndergraStuPartiSocialPracTable.USPSP_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(UndergraStuPartiSocialPracTable.USPSP_COLLEGE, college);
	    	}
	    }
	    else {
			queryParams = null;
		}
	    
	    int total = uspspDao.getUndergraStuPartiSocialPracCount(queryParams);
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
	    model.setRows(uspspDao.getUndergraStuPartiSocialPracs(start, end, sortStr, orderStr, queryParams, deadLine));	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}
}
