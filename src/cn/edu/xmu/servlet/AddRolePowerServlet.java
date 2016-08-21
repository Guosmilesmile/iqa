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
import cn.edu.xmu.table.ManegeRolePower;

/**
 * Servlet implementation class AddRolePowerServlet
 */
@WebServlet("/AddRolePowerServlet")
public class AddRolePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRolePowerServlet() {
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
		String powerid = null;
		String roleid = null;
		String tableid = null;
		powerid = request.getParameter("select1");
		roleid = request.getParameter("roleid");
		tableid = request.getParameter("select2");
		RolePowerDao rolePowerDao = new RolePowerDapImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put(ManegeRolePower.RR_ROLEID, roleid);
		params.put(ManegeRolePower.RR_ROLEPOWERID, powerid);
		int flag = rolePowerDao.addRecord(ManegeRolePower.TABLE_NAME, params);
		if (flag == 1) {
			request.getRequestDispatcher("/admin/success.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/admin/fail.jsp").forward(request,
					response);
		}
	}

}
