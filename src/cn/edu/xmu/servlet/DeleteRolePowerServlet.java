package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RolePowerDao;
import cn.edu.xmu.daoimpl.RolePowerDapImpl;
import cn.edu.xmu.entity.RolePower;
import cn.edu.xmu.table.ManegeRolePower;

/**
 * Servlet implementation class DeleteRolePowerServlet
 */
@WebServlet("/DeleteRolePowerServlet")
public class DeleteRolePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRolePowerServlet() {
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
		String roleid = request.getParameter("roleid");
		String powerid = request.getParameter("powerid");
		RolePowerDao rolePowerDao = new RolePowerDapImpl();
		Map<String, String> param = new HashMap<String, String>();
		param.put("rr_roleid", roleid);
		param.put("rr_rolepowerid", powerid);
		int flag = rolePowerDao.deleteRecord(ManegeRolePower.TABLE_NAME, param);
		if (flag == 1) {
			request.getRequestDispatcher("/admin/success.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/admin/fail.jsp").forward(request,
					response);
		}
	}

}
