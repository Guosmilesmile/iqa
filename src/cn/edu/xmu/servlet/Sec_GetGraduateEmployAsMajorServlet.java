package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.GraduateEmployAsMajorDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.entity.GraduateEmployAsMajor;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.table.GraduateEmployAsMajorTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetAllGraduateEmployAsMajorServlet
 */
@WebServlet("/Sec_GetGraduateEmployAsMajorServlet")
public class Sec_GetGraduateEmployAsMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private TableListDao tableListDao = new TableListDaoImpl();     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetGraduateEmployAsMajorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		GraduateEmployAsMajorDao graduateEmployAsMajorDao = new GraduateEmployAsMajorDaoImpl();
		GridDataModel<GraduateEmployAsMajor> model = new GridDataModel<GraduateEmployAsMajor>();
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    String college = request.getParameter("college");
	   /* college = URLDecoder.decode(college,"UTF-8");*/
	    String tableid = request.getParameter("tableid");   
	    String userid = request.getParameter("userid");
	    String isnull = request.getParameter("isnull");
	    Date deadLine = tableListDao.getTableDate(tableid);
	    
	    Map queryParams = new HashMap();	
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(GraduateEmployAsMajorTable.GEAM_ISNULL, isnull);
	    }
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(GraduateEmployAsMajorTable.GEAM_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(GraduateEmployAsMajorTable.GEAM_COLLEGE, college);
	    	}
	    }
	   
	    int total = graduateEmployAsMajorDao.getGraduateEmployAsMajorCount(queryParams);
	    if(null == pageStr){
	    	pageStr = "1";
	    }if(null == rowsStr){
	    	rowsStr=total+"";
	    }
	    
	    int page = Integer.parseInt(pageStr);
	    int rows = Integer.parseInt(rowsStr);
	    int start = (page - 1)*rows ;
	    
	    System.out.println("当前第"+page+"页"+rows+"rows");
	    int end = rows;
	    end = end > total ? total : end;
	    model.setTotal(total);
	    model.setRows(graduateEmployAsMajorDao.getGraduateEmployAsMajors(start, end, sortStr, orderStr,queryParams,deadLine));
	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
