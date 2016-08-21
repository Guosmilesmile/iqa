package cn.edu.xmu.servlet;

import java.io.IOException;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleFillDao;


import cn.edu.xmu.dao.RoleDao;
import cn.edu.xmu.daoimpl.RoleFillDaoImpl;

/**
 * Servlet implementation class DeleteRoleFillPowerServlet
 */
@WebServlet("/DeleteRoleFillPowerServlet")
public class DeleteRoleFillPowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoleFillPowerServlet() {
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
		String fillpowerid = request.getParameter("fillpowerid");
		System.out.println(fillpowerid);
		RoleFillDao roleFillDao = new RoleFillDaoImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("rr_id", fillpowerid);
		int flag = roleFillDao.deleteRecord("rr_rolefill", params);
		if (flag == 1) {
			request.getRequestDispatcher("/admin/success.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/admin/fail.jsp").forward(request,
					response);
		}
	}

}
