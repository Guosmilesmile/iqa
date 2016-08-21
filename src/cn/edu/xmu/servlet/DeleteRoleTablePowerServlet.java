package cn.edu.xmu.servlet;

import java.io.IOException;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleTablePowerOneDao;


import cn.edu.xmu.dao.RoleDao;
import cn.edu.xmu.daoimpl.RoleTablePowerOneDaoImpl;

/**
 * Servlet implementation class DeleteRoleFillPowerServlet
 */
@WebServlet("/DeleteRoleTablePowerServlet")
public class DeleteRoleTablePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoleTablePowerServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String rpid = request.getParameter("rpid");
		System.out.println(rpid);
		RoleTablePowerOneDao roleTablePowerDao = new RoleTablePowerOneDaoImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("rp_id", rpid);
		int flag = roleTablePowerDao.deleteRecord("rp_roletablepower", params);
		request.getRequestDispatcher("/admin/tablepower.jsp").forward(request,
				response);
		/*if (flag == 1) {
			request.getRequestDispatcher("/admin/success.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/admin/fail.jsp").forward(request,
					response);
		}*/
	}

}
