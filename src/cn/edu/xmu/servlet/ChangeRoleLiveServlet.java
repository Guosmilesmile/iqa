package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleDao;
import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.RoleDaoImpl;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.table.RoleTable;
import cn.edu.xmu.table.UserTable;

/**
 * Servlet implementation class ChangeRoleLiveServlet
 */
@WebServlet("/ChangeRoleLiveServlet")
public class ChangeRoleLiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRoleLiveServlet() {
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
		response.setContentType("text/html;Charset=UTF-8");
		System.out.println("okkk");
		String roleid = request.getParameter("roleid");
		String flag= request.getParameter("flag");
		Map<String, String> params1 = new HashMap<String, String>();
		Map<String, String> params2 = new HashMap<String, String>();
		params1.put(RoleTable.R_ISLIVE, flag);
		params2.put(RoleTable.R_ROLEID, roleid);
		RoleDao roleDao = new RoleDaoImpl();
		int flagg = roleDao.updateRecord(RoleTable.TABLE_NAME, params1, params2);
		
	}

}
