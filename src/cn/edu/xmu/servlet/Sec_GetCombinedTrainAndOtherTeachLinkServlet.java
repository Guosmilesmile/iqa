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

import cn.edu.xmu.dao.CombinedTrainAndOtherTeachLinkDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.CombinedTrainAndOtherTeachLinkDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.entity.CombinedTrainAndOtherTeachLink;
import cn.edu.xmu.table.CombinedTrainAndOtherTeachLinkTable;
import cn.edu.xmu.table.ForeignExchangeTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetAllCombinedTrainAndOtherTeachLinkServlet
 */
@WebServlet("/Sec_GetCombinedTrainAndOtherTeachLinkServlet")
public class Sec_GetCombinedTrainAndOtherTeachLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private TableListDao tableListDao = new TableListDaoImpl();   
    /**   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetCombinedTrainAndOtherTeachLinkServlet() {
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
		CombinedTrainAndOtherTeachLinkDao combinedTrainAndOtherTeachLinkDao = new CombinedTrainAndOtherTeachLinkDaoImpl();
		GridDataModel<CombinedTrainAndOtherTeachLink> model = new GridDataModel<CombinedTrainAndOtherTeachLink>();
		
		String pageStr = request.getParameter("page");
	    String rowsStr = request.getParameter("rows");
	    String sortStr = request.getParameter("sort");
	    String orderStr = request.getParameter("order");
	    String college = request.getParameter("college");
	    System.out.println("college:"+college);
	    /*college = URLDecoder.decode(college,"UTF-8");*/
	    String tableid = request.getParameter("tableid");   
	    String userid = request.getParameter("userid");
	    String isnull = request.getParameter("isnull");
	    Date deadLine = tableListDao.getTableDate(tableid);
	    
	    Map queryParams = new HashMap();	
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_ISNULL, isnull);
	    }
	    if(college != null && !"".equals(college)){
	    	if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE, college);	    
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(CombinedTrainAndOtherTeachLinkTable.CTAOTL_COLLEGE, college);	    
	    	}
	    }
	   
	    int total = combinedTrainAndOtherTeachLinkDao.getCombinedTrainAndOtherTeachLinkCount(queryParams);
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
	    model.setRows(combinedTrainAndOtherTeachLinkDao.getCombinedTrainAndOtherTeachLink(start, end, sortStr, orderStr,queryParams,deadLine));
	    
	    
	    System.out.println(FastJsonTool.createJsonString(model));
	    writer.write(FastJsonTool.createJsonString(model));
	}

}
