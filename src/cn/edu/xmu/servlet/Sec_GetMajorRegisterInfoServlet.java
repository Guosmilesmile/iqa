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

import cn.edu.xmu.dao.MajorRegisterInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.MajorRegisterInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.MajorRegisterInfo;
import cn.edu.xmu.table.MajorRegisterInfoTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_GetMajorRegisterInfoServlet")
public class Sec_GetMajorRegisterInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TableListDao tableListDao = new TableListDaoImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_GetMajorRegisterInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletReqsuest request, HttpServletResponse
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
		MajorRegisterInfoDao mriDao = new MajorRegisterInfoDaoImpl();
		GridDataModel<MajorRegisterInfo> model = new GridDataModel<MajorRegisterInfo>();

		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		String sortStr = request.getParameter("sort");
		String orderStr = request.getParameter("order");

		String college = request.getParameter("mri_college");
	    String isnull = request.getParameter("isnull");
	    
		String tableid = request.getParameter("tableid");   
	    String roleid = request.getParameter("roleid");

		Map queryParams = new HashMap();
		Date deadLine = tableListDao.getTableDate(tableid);
	    if(isnull != null && !"".equals(isnull)){
	    	queryParams.put(MajorRegisterInfoTable.ISNULL, isnull);
	    }
		if (college != null && !"".equals(college)) {
			if (college.contains("学院")) {//具体的学院才过滤
	    		queryParams.put(MajorRegisterInfoTable.MRI_COLLEGE, college);
			}
	    	else{//类似教务处，根据填报人的roleid->userid->depxi
	    		//根据roleid获取userid
	    		UserRoleDao userRoleDao = new UserRoleDaoImpl();
	    		String userid = userRoleDao.getUseridByRoleid(roleid);
	    		//根据userid获取对应的学院
	    		UserDao userDao = new UserDaoImpl();
	    		college = userDao.getDepxiByUserid(userid);
	    		queryParams.put(MajorRegisterInfoTable.MRI_COLLEGE, college);
	    	}
		}
		else{
			queryParams = null;
		}
		
	    
		int total = mriDao.getMajorRegisterInfoCount(queryParams);
		if(null == pageStr){
	    	pageStr = "1";
	    }if(null == rowsStr){
	    	rowsStr=total+"";
	    }
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		System.out.println("当前第" + page + "页" + rows + "rows");
		int end = rows;
		end = end > total ? total : end;
		model.setTotal(total);
		model.setRows(mriDao.getMajorRegisterInfo(start, end, sortStr, orderStr, queryParams, deadLine));
		
		System.out.println(FastJsonTool.createJsonString(model));
		writer.write(FastJsonTool.createJsonString(model));
	}
}
