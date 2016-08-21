package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.MinorDegreeDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.MinorDegreeDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.MinorDegree;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.table.MinorDegreeTable;
import cn.edu.xmu.table.PublishedMaterialsTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class Sec_GetMinorDegreeServlet
 */
@WebServlet("/Sec_GetMinorDegreeServlet")
public class Sec_GetMinorDegreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_GetMinorDegreeServlet() {
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
		        // 编码统一
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				// 输入流
				PrintWriter writer = response.getWriter();
				MinorDegreeDao mddao = new MinorDegreeDaoImpl();
				GridDataModel<MinorDegree> model = new GridDataModel<MinorDegree>();

				String pageStr = request.getParameter("page");
				String rowsStr = request.getParameter("rows");
				String sortStr = request.getParameter("sort");
				String orderStr = request.getParameter("order");

				String college = request.getParameter("md_college");
				String isnull = request.getParameter("isnull");
				String roleid = request.getParameter("roleid");

				Map queryParams = new HashMap();
				if (college != null && !"".equals(college)) {
					if (college.contains("学院")) {//具体的学院才过滤
			    		queryParams.put(MinorDegreeTable.MD_COLLEGE, college);
					}
			    	else{//类似教务处，根据填报人的roleid->userid->depxi
			    		//根据roleid获取userid
			    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
			    		String userid = userRoleDao.getUseridByRoleid(roleid);
			    		//根据userid获取对应的学院
			    		UserDao userDao = new UserDaoImpl();
			    		college = userDao.getDepxiByUserid(userid);
			    		queryParams.put(MinorDegreeTable.MD_COLLEGE, college);
			    	}
				}else{
					queryParams = null;
				}

				if(isnull != null && !"".equals(isnull)){
			    	queryParams.put(MinorDegreeTable.ISNULL, isnull);
			    }
				
				int total = mddao.getMinorDegreeCount(queryParams);

				if (null == pageStr) {
					pageStr = "1";
				}
				if (null == rowsStr) {
					rowsStr = total + "";
				}

				int page = Integer.parseInt(pageStr);
				int rows = Integer.parseInt(rowsStr);
				int start = (page - 1) * rows;

				System.out.println("当前第" + page + "页" + rows + "rows");
				int end = rows;
				end = end > total ? total : end;
				model.setTotal(total);
				model.setRows(mddao.getAllMinorDegree(start, end, sortStr, orderStr,
						queryParams));
				System.out.println(FastJsonTool.createJsonString(model));
				writer.write(FastJsonTool.createJsonString(model));
	}

}
