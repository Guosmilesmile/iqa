package cn.edu.xmu.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.entity.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		String username = "";
		String deptxi = "";
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDaoImpl();
		int number = userDao.isLogin(userid, password);
		if (number != 1) {
			response.sendRedirect("login.jsp");
		} else {
			userDao = new UserDaoImpl();
			User user = new User();
			user = userDao.getAllInfo(userid);
			username = user.getU_username();
			deptxi = user.getU_deptxi();
			int[] power = new int[4];
			power = userDao.getAllPower(userid);
			Cookie cookie;
			System.out.println("start");
			for (int i = 0; i < 4; i++) {
				System.out.println(power[i]);
			}
			if (power[1] == 1) {
				cookie = new Cookie("manageuser", "1");
				response.addCookie(cookie);
			} else {
				cookie = new Cookie("manageuser", "0");
				response.addCookie(cookie);
			}
			if (power[2] == 1) {
				cookie = new Cookie("managerole", "1");
				response.addCookie(cookie);
			} else {
				cookie = new Cookie("managerole", "0");
				response.addCookie(cookie);
			}
			if (power[3] == 1) {
				cookie = new Cookie("managetable", "1");
				response.addCookie(cookie);
			} else {
				cookie = new Cookie("managetable", "0");
				response.addCookie(cookie);
			}
			cookie = new Cookie("userid", userid);
			response.addCookie(cookie);
			cookie = new Cookie("username", URLEncoder.encode(URLEncoder
					.encode(username, "UTF-8")));
			response.addCookie(cookie);
			if(!"".equals(deptxi))
			cookie = new Cookie("deptxi", URLEncoder.encode(URLEncoder.encode(
					deptxi, "UTF-8")));
			else{
				cookie = new Cookie("deptxi","");
			}
			response.addCookie(cookie);
			
			int highestPower = userDao.getHighestPower(userid);
			cookie = new Cookie("highestPower", highestPower+"");
			response.addCookie(cookie);
			
			response.sendRedirect("admin/main.jsp");
		}
	}
}
