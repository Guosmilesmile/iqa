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

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.dao.SchoolIntroductionDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.daoimpl.SchoolIntroductionDaoimpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.SchoolExecutiveUnit;
import cn.edu.xmu.entity.SchoolIntroduction;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;
import cn.edu.xmu.table.SchoolIntroductionTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class Sec_GetSchoolIntroductionServlet
 */
@WebServlet("/Sec_GetSchoolIntroductionServlet")
public class Sec_GetSchoolIntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_GetSchoolIntroductionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 编码统一
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 输入流
		PrintWriter writer = response.getWriter();
		SchoolIntroductionDao sidao = new SchoolIntroductionDaoimpl();
		GridDataModel<SchoolIntroduction> model = new GridDataModel<SchoolIntroduction>();

		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		String sortStr = request.getParameter("sort");
		String orderStr = request.getParameter("order");
		String roleid = request.getParameter("roleid");

		String college = request.getParameter("si_college");
		String isnull = request.getParameter("isnull");
		Map queryParams = new HashMap();
		if (college != null && !"".equals(college)) {
			if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(SchoolIntroductionTable.SI_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(SchoolIntroductionTable.SI_COLLEGE, college);
	    	}
		}else{
			queryParams = null;
		}
		if(isnull != null && !"".equals(isnull)){
		    queryParams.put(SchoolIntroductionTable.ISNULL, isnull);
		}
		int total = sidao.getSchoolIntroductionCount(queryParams);

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
		model.setRows(sidao.getAllSchoolIntroduction(start, end, sortStr,
				orderStr, queryParams));
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));
	}

}
