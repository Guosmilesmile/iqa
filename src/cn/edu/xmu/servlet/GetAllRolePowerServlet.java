package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RolePowerDao;
import cn.edu.xmu.daoimpl.RolePowerDapImpl;
import cn.edu.xmu.entity.RolePower;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetAllRolePowerServlet
 */
@WebServlet("/GetAllRolePowerServlet")
public class GetAllRolePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllRolePowerServlet() {
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
		RolePowerDao rolePowerDao = new RolePowerDapImpl();
		List<RolePower> list = null;
		list = rolePowerDao.getRolePowers();
		response.getWriter().print(FastJsonTool.createJsonString(list));
	}

}
