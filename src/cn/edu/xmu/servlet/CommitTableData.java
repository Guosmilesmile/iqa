package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.table.UserFillTable;
import cn.edu.xmu.table.UserTable;

/**
 * Servlet implementation class CommitTableData
 */
@WebServlet("/CommitTableData")
public class CommitTableData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitTableData() {
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
		
		PrintWriter out = response.getWriter();
		
		String tableid = request.getParameter("tableid");
		String userid = request.getParameter("userid");
		Map queryParams = new HashMap();
		
		UserRoleDao userRoleDao = new UserRoleDaoImpl();
		int roleid = userRoleDao.getRoleidByUserid(userid);
		queryParams.put(UserFillTable.RF_TABLEID, tableid);
		queryParams.put(UserFillTable.RF_FILLROLEID,roleid+"");
		Map<String, String> quMap = new HashMap<String, String>();
		quMap.put(UserFillTable.RF_FILLSITUATION,"1");
		quMap.put(UserFillTable.RF_REVIEWSITUATION,"0");
		UserFillDao ufd = new UserFillDaoImpl();
		int result = ufd.updateRecord(UserFillTable.TABLE_NAME, quMap, queryParams);
		out.print(result);
		System.out.println("result="+result);
		out.close();
	}

}
