package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.table.UserTable;

/**
 * Servlet implementation class ChangeLiveServlet
 */
@WebServlet("/ChangeLiveServlet")
public class ChangeLiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeLiveServlet() {
		super();
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String userid = request.getParameter("userid");
		String islve = request.getParameter("liveid");
		Map<String, String> params1 = new HashMap<String, String>();
		Map<String, String> params2 = new HashMap<String, String>();
		if (islve.equals("1")) {
			params1.put(UserTable.U_ISLIVE, "0");
		} else {
			params1.put(UserTable.U_ISLIVE, "1");
		}
		params2.put(UserTable.U_USERID, userid);
		UserDao userDao = new UserDaoImpl();
		int flag = userDao.updateRecord(UserTable.TABLE_NAME, params1, params2);
		System.out.println(flag);
		if (flag == 1) {
			request.getRequestDispatcher("/admin/success.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/admin/fail.jsp").forward(request,
					response);
		}
	}

}
