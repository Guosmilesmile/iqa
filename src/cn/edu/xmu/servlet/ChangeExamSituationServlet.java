package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import cn.edu.xmu.dao.RoleTablePowerDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.RoleTablePowerDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.table.UserFillTable;

/**
 * Servlet implementation class ChangeExamSituationServlet
 */
@WebServlet("/ChangeExamSituationServlet")
public class ChangeExamSituationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeExamSituationServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		int tableid = Integer.parseInt(request.getParameter("id"));//这是什么id??---->tableid
		int flag = Integer.parseInt(request.getParameter("flag"));
		String userid = request.getParameter("userid");
		//添加=====================
		String unpasscolleges = request.getParameter("unpasscolleges");
		String []colleges = null; 
		if(unpasscolleges != null)
			colleges = unpasscolleges.substring(2, unpasscolleges.length()-2).split("\",\"");
		
		int reviewsituation = 0;//审核状态
		
		//根据userid获取roleid
		UserRoleDao userRoleDao = new UserRoleDaoImpl();
		int user_roleid = userRoleDao.getRoleidByUserid(userid);
		//根据roleid和tableid获取审核权限
		RoleTablePowerDao roleTablePowerDao = new RoleTablePowerDaoImpl();
		int powerid = roleTablePowerDao.getPoweridByRoleidAndTableid(user_roleid, tableid);	
		System.out.println("flag-------------------"+flag);
		UserFillDao ufd = new UserFillDaoImpl();
		List<Integer> roleIds = roleTablePowerDao.getFillRoleIdByColleges(colleges);
		if (flag == 1) {//通过
			if (powerid == 3) {//一级审核
				reviewsituation = 2;//一级审核通过
			}else if(powerid == 4){//二级审核
				reviewsituation = 3;//二级审核通过
			}
		}else {//不通过
			if (powerid == 3) {//一级审核
				reviewsituation = 4;//一级审核不通过
			}else if(powerid == 4){//二级审核
				reviewsituation = 5;//二级审核不通过
			}
			
			//还需要修改提交状态
			Map queryParams = new HashMap();
			
			//int roleid = userRoleDao.getRoleidByUserid(userid);
			queryParams.put(UserFillTable.RF_TABLEID, tableid+"");
			
			//8888888888888888888888888888888888888888888888888888888获取，getroleidbycolleges
			for (Integer roleid : roleIds) {
				queryParams.put(UserFillTable.RF_FILLROLEID,roleid+"");
				Map<String, String> quMap = new HashMap<String, String>();
				quMap.put(UserFillTable.RF_FILLSITUATION,"0");
				//quMap.put(UserFillTable.RF_REVIEWSITUATION,"0");
				
				int result = ufd.updateRecord(UserFillTable.TABLE_NAME, quMap, queryParams);
			}
			
		}
		UserDao userDao = new UserDaoImpl();
		int highestPowerId = userDao.getHighestPower(userid);
		if(flag==1)
			flag=0;
		System.out.println("reviewsituation-------------------"+reviewsituation);
		int b = ufd.UpdateReview(userid, tableid, flag, roleIds, reviewsituation);
		System.out.println("b-----------------"+b);
		
		out.print(highestPowerId);
		
	}

}
